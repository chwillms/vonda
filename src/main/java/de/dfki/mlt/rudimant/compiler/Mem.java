/*
 * The Creative Commons CC-BY-NC 4.0 License
 *
 * http://creativecommons.org/licenses/by-nc/4.0/legalcode
 *
 * Creative Commons (CC) by DFKI GmbH
 *  - Bernd Kiefer <kiefer@dfki.de>
 *  - Anna Welker <anna.welker@dfki.de>
 *  - Christophe Biwer <christophe.biwer@dfki.de>
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

package de.dfki.mlt.rudimant.compiler;

import static de.dfki.mlt.rudimant.compiler.Utils.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;
import de.dfki.mlt.rudimant.common.*;
import de.dfki.mlt.rudimant.compiler.tree.ExpFuncCall;
import de.dfki.mlt.rudimant.compiler.tree.RTBlockNode;
import de.dfki.mlt.rudimant.compiler.tree.ToplevelBlock;
import de.dfki.mlt.rudimant.compiler.tree.VisitorType;

/**
 * this is rudimants memory, used for type checking
 *
 * @author Anna Welker
 */
public class Mem {
  private static Logger logger = LoggerFactory.getLogger(Mem.class);

  /** A stack of active class environments, representing the files that are
   *  currently processed.
   *
   *  When processing starts, the ClassEnv for the first source file must be
   *  added, then the Agent.rudi must be processed as if it was part of this
   *  file, and then normal processing commences.
   */
  private ToplevelBlock currentBlock;

  /** A stack of the active variable / function scopes */
  private Environment currentEnv;

  private int blockNesting = 0;

  private final RdfProxy _proxy;

  /** Info about all imports and rules (tree) */
  private BasicInfo currentInfo, rootInfo;

  /** the Java class that will be extended by the topmost rudi file to link
   *  the rule files into a Java project
   */
  private final String wrapperClass;

  /** The root package for the output */
  private final String[] rootPackage;

  /** the rudi file that represents the Agent (the topmost rudi file) */
  private ClassEnv topLevelClass;

  public Mem(RdfProxy proxy, String wrapper, String[] rootPkg) {
    currentEnv = null;
    currentBlock = null;
    currentInfo = null;
    _proxy = proxy;
    Type.setProxy(proxy);
    wrapperClass = wrapper;
    rootPackage = rootPkg;
  }

  /** Get the current ClassEnv (the one treated now) */
  private ClassEnv curClass() {
    return currentBlock.getClassEnv();
  }

  public String getTopLevelClass() {
    return topLevelClass.getName();
  }

  /** Return the root package spec */
  public String[] getTopLevelPackageSpec() {
    return rootPackage;
  }

  public String getWrapperClass() {
    return wrapperClass;
  }

  /** Return the sub-package spec of the current class */
  public String[] getPackageSpec() {
    return curClass().packageSpec();
  }

  // TODO the next two are candidates for refactoring, check call graph!
  public String getToplevelInstance() {
    return lowerCaseFirst(getTopLevelClass());
  }

  public boolean isNotToplevelClass() {
    return currentBlock.getParentClass() != null;
  }

  public RdfProxy getProxy() {
    return _proxy;
  }

  /** Enter a new (imported) class. Automatically enters a new variable scope
   *  (Environment), too.
   *
   * @param classname of the new class
   */
  public void enterClass(String classname, String[] pkg, Location loc) {
    if (currentEnv != null) {
      // create the new package specification (append new to old)
      String[] currentPkg = getPackageSpec();
      String[] newpkg =
          Arrays.copyOf(currentPkg, currentPkg.length + pkg.length);
      for (int i = 0, j = currentPkg.length; i < pkg.length; ++i, ++j) {
        newpkg[j] = pkg[i];
      }
      pkg = newpkg;
    }
    ClassEnv newEnv = new ClassEnv(classname, pkg);
    currentInfo = new ImportInfo(classname, pkg,
        (loc == null ? -1 : (loc.getLineNumber() + 1)), currentInfo);
    if (currentBlock == null) {
      topLevelClass = newEnv;
      rootInfo = currentInfo;
    }
    ToplevelBlock node = new ToplevelBlock();
    enterEnvironment(node);

    node.setClass(currentBlock, newEnv);
    currentBlock = node;
  }

  /** Leave processing of a class. To be called at the very end of processing
   *  the top-level class or import.
   */
  public void leaveClass() {
    ToplevelBlock node = currentBlock;
    currentBlock = currentBlock.getParentClass();
    currentInfo = currentInfo.getParent();

    leaveEnvironment(node);
  }

  /** get the name of the current class
   *
   * @return The name of the current class to be generated.
   */
  public String getClassName() { return curClass().getName(); }

  /** This is called during type check, and will return true if the rule is
   *  on the top level of a file, and not embedded into another rule or
   *  environment.
   */
  public boolean enterRule(String name, Location loc) {
    currentInfo = new RuleInfo(name, loc.getLineNumber() + 1, currentInfo);
    // The condition is: We're in the topmost environment of a file.
    curClass().enterRule((RuleInfo)currentInfo);
    return //curClass().enterRule((RuleInfo)currentInfo)
        currentInfo.getParent() instanceof ImportInfo
        && currentBlock.getBindings() == currentEnv;
  }

  /** This is called during generation */
  public RuleInfo enterRule(int id) {
    currentInfo = curClass().getRuleInfo(id);
    return (RuleInfo)currentInfo;
  }

  public void leaveRule() {
    currentInfo = currentInfo.getParent();
  }

  /** Return the info of the innermost rule we're in */
  public RuleInfo getCurrentRuleInfo() {
    return currentInfo instanceof RuleInfo ? (RuleInfo)currentInfo : null;
  }

  /** Add a function/method declaration, optionally with return and parameter
   *  types. If the types are not known, it's assumed they are null.
   *
   * @param funcname The name of the function
   * @param functype the return type of the function, or null
   * @param partypes the parameter types of the function's parameters
   * @param origin first element class, second rule origin
   */
  public void addFunction(String funcname, Type functype, Type calledUpon,
          List<Type> partypes) {
    currentEnv.addFunction(funcname, functype, calledUpon, partypes,
        getClassName());
  }

  /** Return the class where this function is defined */
  public String getFunctionOrigin(String funcname, List<Type> partypes){
    String origin = currentEnv.getFunctionOrigin(funcname, partypes);
    return (getClassName().equals(origin)) ? null : origin;
  }

  /** return the return type of the given function or method, or null if there
   *  is no such function
   *
   * @param funcname   the name of the function
   * @param calledUpon the class to which the method belongs, or null if it
   *        is a pure function
   * @param partypes   the parameter type list
   * @return its return type or null
   */
  public Type getFunctionRetType(String funcname, Type calledUpon, List<Type> partypes) {
    return currentEnv.getFunctionRetType(funcname, calledUpon, partypes);
  }

  /** Add a new variable declaration, providing the variable name and type
   *
   * @param variable
   * @param type
   * @return true if the variable is not already defined, false otherwise
   */
  public boolean addVariableDeclaration(String variable, Type type) {
    if (currentEnv.isVarDefined(variable)) {
      return false;
    }
    String origin = getClassName();
    currentEnv.put(variable, type, origin);
    logger.trace("Add var {}:{} [{}]", blockNesting, variable, type);
    return true;
  }

  public boolean variableExists(String variable) {
    return currentEnv.isVarDefined(variable);
  }

  /** get the class where the given variable was defined
   *
   * @param variable a variable name
   * @return the class it came from, null if not declared
   */
  public String getVariableOriginClass(String variable) {
    String origin = currentEnv.getOrigin(variable);
    if (getClassName().equals(origin))
      return null;

    return origin;
  }

  /** get the type of the given variable
   *
   * @param variable a variable name
   * @return the variable's type
   */
  public Type getVariableType(String variable) {
    return currentEnv.getType(variable);
  }

  /** enter a new Environment (variable binding level) in TypeVisitor */
  public void enterEnvironment(RTBlockNode node) {
    logger.trace("Enter level {}", blockNesting);
    currentEnv = node.enterEnvironment(currentEnv);
    ++blockNesting;
  }

  /** leave an environment (variable binding level) */
  public void leaveEnvironment(RTBlockNode node) {
    // restore the values in actual that we changed
    currentEnv = node.getParentBindings();
    --blockNesting;
    logger.trace("Leave level {}", blockNesting);
  }

  /** Return the set of needed classes to generate the proper import statements
   * @return A set of all needed external classes (because of variable or
   * function definitions from this class).
   */
  public Set<String> getNeededClasses() {
    // return all classes above me (import chain), and this class
    Set<String> result = new HashSet<>();
    ToplevelBlock tb = currentBlock;
    while(tb != null) {
      result.add(tb.getClassEnv().getName());
      tb = tb.getParentClass();
    }
    return result;
  }

  public BasicInfo getInfo() {
    return rootInfo;
  }

  public void registerParsingFailure(String failMessage, Location location) {
    BasicInfo current = currentInfo;
    while (! (current instanceof ImportInfo))
      current = current.getParent();
    ImportInfo info = (ImportInfo)current;
    info.setParsingFailure(new ErrorWarningInfo(failMessage, location));

  }

  public void registerError(String errorMessage, Location location) {
    BasicInfo current = currentInfo;
    while (! (current instanceof ImportInfo))
      current = current.getParent();
    ImportInfo info = (ImportInfo)current;
    info.getErrors().add(new ErrorWarningInfo(errorMessage, location));
  }

  public void registerWarning(String warnMessage, Location location) {
    BasicInfo current = currentInfo;
    while (! (current instanceof ImportInfo))
      current = current.getParent();
    ImportInfo info = (ImportInfo)current;
    info.getWarnings().add(new ErrorWarningInfo(warnMessage, location));
  }

  public void resolveGenericTypes(ExpFuncCall func, VisitorType visitor) {
    // idea: find those parameters that know their types and thereby resolve
    // some of the uncertainty. Add that knowledge to genericToType.
    // then, if this is not yet solved, let visitor visit the remaining
    // uncertain elements. On its way, it should insert every knowledge piece
    // from genericToType that is applicable.
    // Do that again and again, until list of unknown generic nodes is empty
    // or we do not find new information anymore
  }
}

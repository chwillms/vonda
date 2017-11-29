package de.dfki.mlt.rudimant.compiler;

public class Utils {
  public static String lowerCaseFirst(String s) {
    return s.substring(0, 1).toLowerCase() + s.substring(1);
  }

  public static boolean isBooleanOperator(String operator) {
    return operator.equals("&&") || operator.equals("||")
            || operator.equals("!");
  }

  /** return true if ==, !=, <=, >=, <, > */
  public static boolean isComparisonOperator(String operator) {
    char c = operator.charAt(0);
    if ("<>=!".indexOf(c) < 0) {
      return false;
    }
    return (operator.length() == 2 && operator.charAt(1) == '=')
        || (operator.length() == 1 && (c == '<' || c == '>'));
  }

  public static String getPackageName(String[] pkg) {
    StringBuffer sb = new StringBuffer();
    boolean first = true;
    for(String p : pkg) {
      if (!first) sb.append('.');
      else first = false;
      sb.append(p);
    }
    return sb.toString();
  }

  public static String getQualifiedName(String[] pkg, String name) {
    StringBuffer sb = new StringBuffer();
    for(String p : pkg) {
      sb.append(p).append('.');
    }
    sb.append(name);
    return sb.toString();
  }
}
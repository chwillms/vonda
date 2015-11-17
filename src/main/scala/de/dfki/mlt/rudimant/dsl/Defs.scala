package de.dfki.mlt.rudimant.dsl

import de.dfki.mlt.rudimant.Proposal

import scala.language.dynamics

trait Env

trait Body[+A] {

  def apply(): A

}

object Body {

  def apply[A](body: => A): Body[A] = {
    new Body[A] {
      override def apply() = body
    }
  }

}

trait Rule[A] {

  def applies(env: Env): Boolean

  protected def bodyFor(env: Env): Body[A]

  def tryApplying(env: Env): Option[Body[A]] = {
    if (applies(env)) {
      val body = bodyFor(env)
      Some(body)
    }
    else {
      None
    }
  }

  def apply(env: Env): Unit = {
    for (body <- tryApplying(env)) {
      body.apply()
    }
  }

}

trait If[A] extends Rule[A] {

  def condition: If.Condition

  def thenBranch: Body[A]
  def elseBranch: Body[A]

  override def bodyFor(env: Env) = {
    if (condition.evaluate(env)) thenBranch else elseBranch
  }

}

object If {

  trait Condition { outer =>

    import Condition._

    def evaluate(env: Env): Boolean

    def Then[A](body: => A): IfThen[A] = IfThen[A](this, Body(body))

    def And(right: => Boolean) = Condition.And(this, Atom({ () => right }))

    def Or(right: => Boolean) = Condition.Or(this, Atom({ () => right }))

  }

  object Condition {

    case class Atom(thunk: () => Boolean) extends Condition {
      override def evaluate(env: Env) = thunk()
    }

    case class And(left: Condition, right: Condition) extends Condition {
      override def evaluate(env: Env) = left.evaluate(env) && right.evaluate(env)
    }

    case class Or(left: Condition, right: Condition) extends Condition {
      override def evaluate(env: Env) = left.evaluate(env) || right.evaluate(env)
    }

  }

  case class IfThen[A](condition: Condition, thenBranch: Body[A]) extends If[A] { outer =>
    override def applies(env: Env) = condition.evaluate(env)

    def elseBranch = null

    def Else(body: => A): IfThenElse[A] = IfThenElse[A](condition, thenBranch, Body(body))
  }

  case class IfThenElse[A](condition: Condition, thenBranch: Body[A], elseBranch: Body[A]) extends If[A] {
    override def applies(env: Env) = true
  }

  def apply(cond: => Boolean): Condition = Condition.Atom({ () => cond })

}

trait PartialCondition[A, B]  {
  def Then(action: Action[A]): PartialRule[B] = ???

  def Do(body: A => Unit): PartialRule[B] = ???

  def -+>(action: Action[A]) = Then(action)

//  def ?(consumer: Cons[A, B]): TotalRule = ???

}

trait IfTrueCondition[A] {
  def Then(action: Action[A]): IfTrueRule[A] = ???

  def Do(body: A => Unit): IfTrueRule[A] = ???

  def -+>(action: Action[A]) = Then(action)
  def +:(action: Action[A]) = Then(action)

  def ?:(action: Action[A]) = Then(action)

//  def THEN(consumer: Cons[A, A]): TotalRule = ???
//  def ?(consumer: Cons[A, A]): TotalRule = ???
  def ?>(action: Action[A]) = Then(action)

//  def Then(cont: With[A] => _Rule): IfTrueRule[A] = ???
}

trait _Rule {

}

trait PartialRuleElse[B] extends _Rule {

  def Do(b: B => Unit): TotalRule = ???

}

trait PartialRule[B] extends _Rule {
//  def Else(b: Action[B]): TotalRule = ???
  def Else(): PartialRuleElse[B] = ???
//  def ElseDo(b: B => Unit) = Else(Action.DoAction(b))
//
//  def -->(b: Action[B]) = Else(b)
}

trait IfTrueRule[A] extends _Rule {
  def Else(b: Action[A]): TotalRule = ???
  def -->(b: Action[A]) = Else(b)
  def -:(b: Action[A]) = Else(b)
  def :>(b: Action[A]) = Else(b)
}

trait TotalRule extends _Rule {

}

trait With[A] {
  def If(f: A => Boolean): IfTrueCondition[A] = ??? //IfDefined[A] { case a if f(a) => a }
  def IfDefined[B](f: PartialFunction[A, B]): PartialCondition[B, A]

  def -?>(f: A => Boolean) = If(f)
  def -:>[B](f: PartialFunction[A, B]) = IfDefined(f)

  def Do(body: A => Unit): TotalRule = ???

//  def Propose(name: Proposal.Descriptor)(body: A => Unit): TotalRule = ???

}

object With {
  def apply[A](cond: => A): With[A] = ???
}

trait Action[+A] {
//  def Else(body: Action[A]): Action[A] = ???

//  def ::(body: Action[A]): Action[A] = ???

}

object Action {

  case class DoAction[A](body: A => Unit) extends Action[A] {

    def Else(action: Action[A]): Action[A] = ???

  }

  case class ProposeAction[A](desc: Proposal.Descriptor, body: A => Unit) extends Action[A] {
    def toProposal: Proposal = ???
  }

}

//object Do {
//  def apply[A](body: A => Unit) = Action.DoAction(body)
//}

object Re {
  def apply[A](body: With[A] => Unit): Action[A] = ???
}

object Propose {
  case class NamedProposalFactory[A](desc: Proposal.Descriptor) {
    def apply(body: A => Unit) = Action.ProposeAction(desc, body)
  }

  def apply[A](desc: Proposal.Descriptor) = NamedProposalFactory(desc)
}

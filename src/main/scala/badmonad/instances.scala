package badmonad

import cats.kernel.Eq
import cats.{Applicative, Eval, Monad, Traverse}
import cats.syntax.functor._

object instances {
  implicit val instancesForBadMonad: Monad[BadMonad] with Traverse[BadMonad] =
    new MonadForBadMonad with TraverseForBadMonad

  implicit def eqForBadMonad[A: Eq]: Eq[BadMonad[A]] = Eq.by(_.x)

  private def absurd[A]: A = {
    while (true) {}
    throw new Exception
  }

  private trait MonadForBadMonad extends Monad[BadMonad] {
    override def pure[A](x: A): BadMonad[A] = BadMonad(x)

    override def flatMap[A, B](fa: BadMonad[A])(f: A => BadMonad[B]): BadMonad[B] = f(fa.x)

    override def tailRecM[A, B](a: A)(f: A => BadMonad[Either[A, B]]): BadMonad[B] =
      absurd
  }

  private trait TraverseForBadMonad extends Traverse[BadMonad] {
    override def traverse[G[_]: Applicative, A, B](fa: BadMonad[A])(f: A => G[B]): G[BadMonad[B]] =
      f(fa.x) `map` BadMonad.apply

    override def foldLeft[A, B](fa: BadMonad[A], b: B)(f: (B, A) => B): B = f(b, fa.x)

    override def foldRight[A, B](fa: BadMonad[A], lb: Eval[B])(f: (A, Eval[B]) => Eval[B]): Eval[B] =
      f(fa.x, lb)
  }
}

package badmonad

import badmonad.instances._
import cats.instances.int._
import cats.instances.string._
import cats.instances.tuple._
import cats.laws.discipline.MonadTests
import org.scalacheck.Arbitrary
import org.scalacheck.Arbitrary.arbitrary
import org.specs2.specification.core.Fragments
import org.specs2.{ScalaCheck, Specification}
import org.typelevel.discipline.specs2.Discipline

class BadMonadLawsSpec extends Specification with ScalaCheck with Discipline { def is = s2"""
  Bad monad laws
    $badMonadMonadLaws
  """

  implicit def arbitraryBadMonad[A: Arbitrary]: Arbitrary[BadMonad[A]] = Arbitrary(
    arbitrary[A] `map` BadMonad.apply
  )

  def badMonadMonadLaws: Fragments = checkAll("BadMonad",
    MonadTests[BadMonad].monad[Int, Int, String]
  )
}

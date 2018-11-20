name := "Small bad monad"

version := "0.1"

scalaVersion := "2.12.7"

// General stuff
scalacOptions ++= Seq(
  "-Ypartial-unification",
  "-language:higherKinds",
)
libraryDependencies += "org.typelevel" %% "cats-core" % "1.4.0"
addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.4")

// Testing stuff
libraryDependencies ++= Seq(
  "org.specs2" %% "specs2-core" % "4.3.4" % Test,
  "org.specs2" %% "specs2-scalacheck" % "4.3.4" % Test,
  "org.scalacheck" %% "scalacheck" % "1.14.0" % Test,
  "org.typelevel" %% "cats-laws" % "1.4.0" % Test,
)
scalacOptions in Test += "-Yrangepos"

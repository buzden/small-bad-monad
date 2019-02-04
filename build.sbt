name := "Small bad monad"

version := "0.1"

scalaVersion := "2.12.8"

lazy val catsVersion = "1.6.0"
lazy val specs2Version = "4.4.1"

// General stuff
scalacOptions ++= Seq(
  "-Ypartial-unification",
  "-language:higherKinds",
)
libraryDependencies += "org.typelevel" %% "cats-core" % catsVersion
addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.9")

// Testing stuff
libraryDependencies ++= Seq(
  "org.specs2" %% "specs2-core" % specs2Version % Test,
  "org.specs2" %% "specs2-scalacheck" % specs2Version % Test,
  "org.typelevel" %% "cats-laws" % catsVersion % Test,
)
scalacOptions in Test += "-Yrangepos"

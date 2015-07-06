lazy val root = (project in file(".")).
  settings(
    name := "hello-scala"
    organization := "im.svv"
    version := "1.0"
    scalaVersion := "2.9.2"
    scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")
    libraryDependencies ++= {
      val akkaV = "2.3.9"
      val sprayV = "1.3.3"
      Seq(
        "io.spray"            %%  "spray-can"     % sprayV,
        "io.spray"            %%  "spray-routing" % sprayV,
        "io.spray"            %%  "spray-testkit" % sprayV  % "test",
        "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
        "com.typesafe.akka"   %%  "akka-testkit"  % akkaV   % "test",
        "org.specs2"          %%  "specs2-core"   % "2.3.7" % "test"
      )
    }
  )

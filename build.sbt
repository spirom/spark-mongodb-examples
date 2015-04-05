name := "spark-mongodb-examples"

version := "1.0"

scalaVersion := "2.10.4"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.3.0" % "provided"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "1.3.0" % "provided"

libraryDependencies += "com.github.spirom" %% "spark-mongodb-connector" % "0.5.0"

lazy val demo = taskKey[Unit]("Populates a collection and runs queriess")

fork in (Compile, run) := true

demo := {
  (runMain in Compile).toTask(" DemoSequence").value
}

mainClass in (Compile, run) := Some("DemoSequence")


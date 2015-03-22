name := "spark-mongodb-examples"

version := "1.0"

scalaVersion := "2.10.4"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.3.0"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "1.3.0"

libraryDependencies += "com.github.spirom" %% "spark-mongodb-connector" % "0.5.0"

lazy val demo = taskKey[Unit]("Populates a collection and runs queriess")

demo := {
  (runMain in Compile).toTask(" PopulateTestCollection").value
  (runMain in Compile).toTask(" BasicQuery").value
  (runMain in Compile).toTask(" SQLQuery").value
}
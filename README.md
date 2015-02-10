
# NSMC Client Examples

This project consists of a standalone set of examples showing how to use [NSMC, the Native Spark MongoDB Connector project](https://github.com/spirom/spark-mongodb-connector).

## Prerequisites

You are encouraged to use these examples to develop our own Spark projects, and run them in your own Spark installation. However, you don't actually _need_ a Spark installation to run them -- this sbt-based Intellij Idea Scala project installs as much of Spark you need to run the examples. 

All you need is Scala 2.10, sbt 0.13 and a MongoDB installation in which you have permission to drop and create a collection in some database. You can either connect unauthenticated (if your MongoDB installation allows) or connect using username/password authentication. 

## Configuring, Building and Running

You need to edit the settings at the top of the `DBConfig` object to specify the connection details for your MongoDB server. 

You can build and run the project either through the **IntelliJ** Idea IDE or via the **sbt** command line tool. An sbt target called **demo** is provided for both populating the collection and then querying it through Spark. To run from Idea, run the `main()` methods of first the  `PopulateTestCollection` and then the `BasicQuery` objects. 

## Relationship to NSMC Releases

This project contains a branch for each release of NSMC, For example, to use **NSMC** release tagged **v0.3.0**, look at the branch here called **depends-v0.3.0**.


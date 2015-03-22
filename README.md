
# NSMC Client Examples

This project consists of a standalone set of examples showing how to use [NSMC, the Native Spark MongoDB Connector project](https://github.com/spirom/spark-mongodb-connector).

## Prerequisites

You are encouraged to use these examples to develop our own Spark projects, and run them in your own Spark installation. However, you don't actually _need_ a Spark installation to run them -- this sbt-based Intellij Idea Scala project installs as much of Spark you need to run the examples. 

All you need is Scala 2.10, sbt 0.13 and a MongoDB installation in which you have permission to drop and create a collection in some database. You can either connect unauthenticated (if your MongoDB installation allows) or connect using username/password authentication. 

## Configuring, Building and Running

You need to edit the settings at the top of the `DBConfig` object to specify the connection details for your MongoDB server. 

You can build and run the project either through the **IntelliJ** Idea IDE or via the **sbt** command line tool. 

### To run from sbt

An **sbt** target called **demo** is provided for both populating the collection and then querying it through Spark. It runs the `main()` methods of the objects below in the given order. 

### To run from Idea

* First run the `main()` method of the `PopulateTestCollection` to pupulate the `scratch` collection.
* Demonstrate basic RDD integration by running the `main()` method of the `BasicQuery` object.
* Demonstrate Spark SQL integration (introduced in NSMC 0.4.0) by running the `main()` method of the `SQLQuery` object. There are four queries run, and after each query the result schema is printed, followed by the results:
    * All fields of all documents in the collection.
    * The `custid` field for every document where `discountCode` is not null. 
    * The `custid` and `shippingAddress.zip` of every document, even though some don't have a `shippingAddress`.
    * The count of `custid` for each `shippingAddress.zip`, again even though some don't have a `shippingAddress`.

## Relationship to NSMC Releases and Spark releases

This project contains a branch for each release of NSMC, For example, to use **NSMC** release tagged **v0.4.0**, look at the branch here called **depends-v0.4.0**.

| Branch of this project | NSMC Release | Apache Spark Release | Scala Version | 
-------------------------|--------------|----------------------|---------------|
| depends-v0.5.0 / master| 0.5.0 | 1.3.0 | 2.10 |
| depends-v0.4.1 | 0.4.1 | 1.2.0 | 2.10 |
| depends-v0.4.0 | 0.4.0 | 1.2.0 | 2.10 |
| depends-v0.3.0 | 0.3.0 | 1.1.0 | 2.10 |
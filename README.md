
# NSMC Client Examples

This project consists of a standalone set of examples showing how to use [NSMC, the Native Spark MongoDB Connector project](https://github.com/spirom/spark-mongodb-connector).

## Prerequisites

You are encouraged to use these examples to develop our own Spark projects, and run them in your own Spark installation. 

All you need is Scala 2.10, sbt 0.13 and a MongoDB installation in which you have permission to drop and create a collection in some database. You can either connect unauthenticated (if your MongoDB installation allows) or connect using username/password authentication. 

## Configuring, Building and Running

You need to edit the settings at the top of the `DBConfig` object to specify the connection details for your MongoDB server. 

You can build the project either through the **IntelliJ** Idea IDE or via the **sbt** command line tool, but you will need to use **sbt** to run the `assembly` command so you can submit the example to a Spark cluster. 

## Submitting to a Spark cluster

The examples are not intended to run "locally": you need to submit the generated assembly file to a Spark cluster with network access to your MongoDB instance as follows:

      bin/spark-submit \
          --master <your Spark master URL>  \
          <path to your git projects>/spark-mongodb-examples/target/scala-2.10/spark-mongodb-examples-assembly-1.0.jar 

The above command runs the entire demo sequence. You can also use the `--class` option to specify individual demo components `PopulateTestCollection` (run it first), `BasicQuery` or `SQLQuery`.  

## Relationship to NSMC Releases and Spark releases

This project contains a branch for each release of NSMC, For example, to use **NSMC** release tagged **v0.5.2**, look at the branch here called **depends-v0.5.2**.

| Branch of this project | NSMC Release | Apache Spark Release | Scala Version | 
-------------------------|--------------|----------------------|---------------|
| depends-v0.5.2 / master| 0.5.2 | 1.3.0 | 2.10 |
| depends-v0.5.1 | 0.5.1 | 1.3.0 | 2.10 |
| depends-v0.5.0 | 0.5.0 | 1.3.0 | 2.10 |
| depends-v0.4.1 | 0.4.1 | 1.2.0 | 2.10 |
| depends-v0.4.0 | 0.4.0 | 1.2.0 | 2.10 |
| depends-v0.3.0 | 0.3.0 | 1.1.0 | 2.10 |

## Expected Output 

The following output can be expected, interspersed with Spark logging output, which can be tuned via the file `src/main/resources/log4j.properties'.

### PopulateTestCollection

    DEMO PART 1: POPULATING TEST COLLECTION
    Connecting to MongoDB at localhost:27017 using collection 'scratch' in database 'test'
    not using any authentication

    finished populating collection

### BasicQuery

    DEMO PART 2: BASIC QUERY
    Connecting to MongoDB at localhost:27017 using collection 'scratch' in database 'test'
    not using any authentication

    ****** Obtained 4 records
    ****** custid = Some(1001) #orders = Some(2)
    ****** custid = Some(1002) #orders = Some(1)
    ****** custid = Some(1003) #orders = Some(3)
    ****** custid = Some(1004) #orders = None
    ****** done

### SQLQuery

    DEMO PART 3: SQL QUERIES
    Connecting to MongoDB at localhost:27017 using collection 'scratch' in database 'test'
    not using any authentication

    *** Query 1: Everything
    root
     |-- _id: string (nullable = true)
     |-- billingAddress: struct (nullable = true)
     |    |-- state: string (nullable = true)
     |    |-- zip: string (nullable = true)
     |-- custid: string (nullable = true)
     |-- discountCode: integer (nullable = true)
     |-- orders: array (nullable = true)
     |    |-- element: struct (containsNull = true)
     |    |    |-- itemid: string (nullable = true)
     |    |    |-- orderid: string (nullable = true)
     |    |    |-- quantity: integer (nullable = true)
     |-- shippingAddress: struct (nullable = true)
     |    |-- state: string (nullable = true)
     |    |-- zip: string (nullable = true)

    _id                  billingAddress custid discountCode orders               shippingAddress
    552483b8e4b072bdf... [NV,89150]     1001   null         List([A001,100000... null           
    552483b8e4b072bdf... [CA,92093]     1002   null         List([B012,100000... [CA,92109]     
    552483b8e4b072bdf... [AZ,85014]     1003   1            List([A001,100000... [AZ,85020]     
    552483b8e4b072bdf... null           1004   null         null                 [CA,92109]     

    *** Query 2: Test IS NOT NULL on a field
    root
     |-- custid: string (nullable = true)

    custid
    1003  

    *** Query 3: Field of a structure that's not always present
    root
     |-- custid: string (nullable = true)
     |-- zip: string (nullable = true)

    custid zip  
    1001   null 
    1002   92109
    1003   85020
    1004   92109

    *** Query 4: Group by field of a structure that's not always present
    root
     |-- c0: long (nullable = false)
     |-- zip: string (nullable = true)

    c0 zip  
    2  92109
    1  null 
    1  85020

### At the end

    DEMO COMPLETED

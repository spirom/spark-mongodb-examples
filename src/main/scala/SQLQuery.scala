
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkContext, SparkConf}

object SQLQuery {
  def main (args: Array[String]) {
    DBConfig.printConfig()
    val coreConf =
      new SparkConf()
        .setAppName("MongoReader").setMaster("local[4]")
        .set("nsmc.connection.host", DBConfig.host)
        .set("nsmc.connection.port", DBConfig.port)
    // if a username AND password are defined, use them
    val conf = (DBConfig.userName, DBConfig.password) match {
      case (Some(u), Some(pw)) => coreConf.set("nsmc.user", u).set("nsmc.password", pw)
      case _ => coreConf
    }
    val sc = new SparkContext(conf)

    val sqlContext = new SQLContext(sc)

    try {
      sqlContext.sql(
        s"""
        |CREATE TEMPORARY TABLE dataTable
        |USING nsmc.sql.MongoRelationProvider
        |OPTIONS (db '${DBConfig.testDatabase}', collection '${DBConfig.testCollection}')
      """.stripMargin)

      println("*** Query 1: Everything")
      val data1 =
        sqlContext.sql("SELECT * FROM dataTable")
      data1.schema.printTreeString()
      data1.foreach(println)

      println("*** Query 2: Test IS NOT NULL on a field")
      val data2 =
        sqlContext.sql("SELECT custid FROM dataTable WHERE discountCode IS NOT NULL")
      data2.schema.printTreeString()
      data2.foreach(println)

      println("*** Query 3: Field of a structure that's not always present")
      val data3 =
        sqlContext.sql("SELECT custid, shippingAddress.zip FROM dataTable")
      data3.schema.printTreeString()
      data3.foreach(println)

      println("*** Query 4: Group by field of a structure that's not always present")
      val data4 =
        sqlContext.sql("SELECT COUNT(custid), shippingAddress.zip FROM dataTable GROUP BY shippingAddress.zip")
      data4.schema.printTreeString()
      data4.foreach(println)

    } finally {
      sc.stop()
    }
  }
}
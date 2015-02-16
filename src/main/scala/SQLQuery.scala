
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkContext, SparkConf}
import nsmc.sql.MongoRelationProvider

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

      println("*** Query 1: everything")
      val data =
        sqlContext.sql("SELECT * FROM dataTable")
      data.schema.printTreeString()
      data.foreach(println)

    } finally {
      sc.stop()
    }
  }
}
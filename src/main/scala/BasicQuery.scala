
import com.mongodb.casbah.Imports._
import nsmc._

import org.apache.spark.{SparkContext, SparkConf}

object BasicQuery {
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

    try {

      val data = sc.mongoCollection[DBObject](DBConfig.testDatabase, DBConfig.testCollection)

      data.foreach(dbo => {
        // may be more convenient to wrap the DBObject in a MongoDBObject for easier access
        // (but not strictly necessary)
        val mdbo = new MongoDBObject(dbo)
        println("custid = " + mdbo.getAs[String]("custid") + " #orders = " + dbo.getAs[Seq[MongoDBObject]]("orders").map(_.size))
      })

      println("done")
    } finally {
      sc.stop()
    }
  }
}

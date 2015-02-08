
import nsmc._

import com.mongodb.casbah.commons.MongoDBObject
import org.apache.spark.{SparkContext, SparkConf}

object BasicQuery {
  def main (args: Array[String]) {
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
    val data = sc.mongoCollection[MongoDBObject](DBConfig.testDatabase, DBConfig.testCollection)

    data.collect().foreach(mdbo => {
      println("custid = " + mdbo("custid"))
    })
  }
}

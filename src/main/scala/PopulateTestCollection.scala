import com.mongodb.ServerAddress
import com.mongodb.casbah.Imports._

object PopulateTestCollection {
  def main (args: Array[String]): Unit = {

    val mongoClient =
      (DBConfig.userName, DBConfig.password) match {
        case (Some(u), Some(pw)) => {
          val server = new ServerAddress(DBConfig.host, DBConfig.port.toInt)
          val credentials =
            MongoCredential.createMongoCRCredential(u, DBConfig.testDatabase, pw.toCharArray)
          MongoClient(server, List(credentials))
        }
        case _ => MongoClient(DBConfig.host, DBConfig.port.toInt)
      }

    val db = mongoClient.getDB(DBConfig.testDatabase)

    try {
      DBConfig.printConfig()
      val col = db(DBConfig.testCollection)
      col.drop()

      col += MongoDBObject("custid" -> "1001") ++
        ("billingAddress" -> (MongoDBObject("state" -> "NV") ++ ("zip" -> "89150"))) ++
        ("orders" -> Seq(
          MongoDBObject("orderid" -> "1000001") ++ ("itemid" -> "A001") ++ ("quantity" -> 175) ++ ("returned" -> true),
          MongoDBObject("orderid" -> "1000002") ++ ("itemid" -> "A002") ++ ("quantity" -> 20)
        ))

      col += MongoDBObject("custid" -> "1002") ++
        ("billingAddress" -> (MongoDBObject("state" -> "CA") ++ ("zip" -> "92093"))) ++
        ("shippingAddress" -> (MongoDBObject("state" -> "CA") ++ ("zip" -> "92109"))) ++
        ("orders" -> Seq(
          MongoDBObject("orderid" -> "1000002") ++ ("itemid" -> "B012") ++ ("quantity" -> 200)
        ))

      col += MongoDBObject("custid" -> "1003") ++
        ("billingAddress" -> (MongoDBObject("state" -> "AZ") ++ ("zip" -> "85014"))) ++
        ("shippingAddress" -> (MongoDBObject("state" -> "AZ") ++ ("zip" -> "85020"))) ++
        ("discountCode" -> 1) ++
        ("orders" -> Seq(
          MongoDBObject("orderid" -> "1000003") ++ ("itemid" -> "A001") ++ ("quantity" -> 175),
          MongoDBObject("orderid" -> "1000004") ++ ("itemid" -> "B001") ++ ("quantity" -> 10),
          MongoDBObject("orderid" -> "1000005") ++ ("itemid" -> "A060") ++ ("quantity" -> 12)
        ))

      col += MongoDBObject("custid" -> "1004") ++
        ("shippingAddress" -> (MongoDBObject("state" -> "CA") ++ ("zip" -> "92109")))

      println("finished populating collection")
    } finally {
      mongoClient.close()
    }
  }
}

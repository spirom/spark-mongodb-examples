import com.mongodb.casbah.Imports._

object PopulateTestCollection {
  def main (args: Array[String]): Unit = {
    val mongoClient = MongoClient(DBConfig.host, DBConfig.port.toInt)
    val db = mongoClient.getDB(DBConfig.testDatabase)

    try {
      val col = db(DBConfig.testCollection)
      col.drop()

      col += MongoDBObject("custid" -> "1001") ++ ("state" -> "NV") ++
        ("orders" -> Seq(
          MongoDBObject("orderid" -> "1000001") ++ ("itemid" -> "A001") ++ ("quantity" -> 175) ++ ("returned" -> true),
          MongoDBObject("orderid" -> "1000002") ++ ("itemid" -> "A002") ++ ("quantity" -> 20)
        ))

      col += MongoDBObject("custid" -> "1002") ++ ("state" -> "CA") ++
        ("orders" -> Seq(
          MongoDBObject("orderid" -> "1000002") ++ ("itemid" -> "B012") ++ ("quantity" -> 200)
        ))

      col += MongoDBObject("custid" -> "1003") ++ ("state" -> "AZ") ++
        ("orders" -> Seq(
          MongoDBObject("orderid" -> "1000003") ++ ("itemid" -> "A001") ++ ("quantity" -> 175),
          MongoDBObject("orderid" -> "1000004") ++ ("itemid" -> "B001") ++ ("quantity" -> 10),
          MongoDBObject("orderid" -> "1000005") ++ ("itemid" -> "A060") ++ ("quantity" -> 12)
        ))

    } finally {
      mongoClient.close()
    }
  }
}

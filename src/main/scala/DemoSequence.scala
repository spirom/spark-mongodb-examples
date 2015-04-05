
object DemoSequence {

  def main (args: Array[String]): Unit = {
    try {
      println("DEMO PART 1: POPULATING TEST COLLECTION")
      PopulateTestCollection.main(Array[String]())
      println("DEMO PART 2: BASIC QUERY")
      BasicQuery.main(Array[String]())
      println("DEMO PART 3: SQL QUERIES")
      SQLQuery.main(Array[String]())
      println("DEMO COMPLETED")
    }
  }

}

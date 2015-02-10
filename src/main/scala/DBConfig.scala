
object DBConfig {

  // replace the following with the right values for your MongoDB installation
  val host = "localhost"
  val port = "27017"
  val testDatabase = "test"
  val testCollection = "scratch"

  // replace the following if using authentication
  // val userName: Option[String] = Some("your user name here")
  val userName: Option[String] = None
  // val password: Option[String] = Some("your password here")
  val password: Option[String] = None

  def printConfig() : Unit = {
    println(s"Connecting to MongoDB at $host:$port using collection '$testCollection' in database '$testDatabase'")
    (DBConfig.userName, DBConfig.password) match {
      case (Some(u), Some(pw)) => println(s"using username '$u' and password '$pw'")
      case _ => println("not using any authentication")
    }
  }
}

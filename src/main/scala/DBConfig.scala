
object DBConfig {

  val host = "localhost"
  val port = "27030" // "27017"
  val testDatabase = "test"
  val testCollection = "scratch"

  // replace the following if using authentication
  // val userName: Option[String] = Some("your user name here")
  val userName: Option[String] = None
  // val password: Option[String] = Some("your password here")
  val password: Option[String] = None


}

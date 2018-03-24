package common.main.mail

import javax.mail.Session

class TestMail {
  def test = {
//    new MailConfig().send a Mail (
//      from = ("shantiswarup8@gmail0.com", "Shanti Swarup"),
//      to = Seq("shantiswarup8@gmail.com"),
//      subject = "Import stuff",
//      message = "Dear Boss..."
//    )
    import org.apache.commons.mail._
    try {
      val email: SimpleEmail = new SimpleEmail()
      email.setHostName("smtp.gmail.com")
      email.setSmtpPort(465)
      email.setAuthenticator(new
          DefaultAuthenticator("shantiswarupjs@gmail.com", "Biswamaya@8"))
      email.setSSL(true)
      email.setFrom("shantiswarupjs@gmail.com")
      email.setSubject("Test")
      email.setMsg("This is a test message")
      email.addTo("shantiswarup8@gmail.com")
      email.addTo("shantiswarupjs@gmail.com")
      email.send()
      println("sent")
    } catch {
      case ex: Throwable => println(ex.getMessage)
        println(ex.getCause)
        ex.printStackTrace()
    }
  }

}

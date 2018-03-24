package common.main.mail

import org.apache.commons.mail.{DefaultAuthenticator, SimpleEmail}

import scala.util.Try

object MailConfig {
  private val host = "smtp.gmail.com"
  private val port = 465
  private val username = ""
  private val password = ""
  private val isSSL = true

  private val email = new SimpleEmail()
  email.setHostName(host)
  email.setSmtpPort(port)
  email.setAuthenticator(new DefaultAuthenticator(username, password))
  email.setSSL(isSSL)

  def config: SimpleEmail = {
    Try(email).map{
      res => res
    }.recover{
      case ex: Throwable => throw ex
    }.get
  }
}
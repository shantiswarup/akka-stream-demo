package common.main.mail

object MailUtils {
  def send(mail: Mail): String = {

    val email = MailConfig.config
    mail.to foreach email.addTo
    mail.cc foreach email.addCc
    mail.bcc foreach email.addBcc

    email setSubject mail.subject
    email setMsg mail.message
    email setFrom (mail.from._1, mail.from._2)

    email send()

  }
}

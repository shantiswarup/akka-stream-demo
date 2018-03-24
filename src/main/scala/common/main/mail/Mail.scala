package common.main.mail

case class Mail(from: (String, String), // (email -> name)
                to: Seq[String],
                cc: Seq[String] = Seq.empty,
                bcc: Seq[String] = Seq.empty,
                subject: String,
                message: String) {

}

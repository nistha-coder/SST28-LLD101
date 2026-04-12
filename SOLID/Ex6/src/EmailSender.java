public class EmailSender extends NotificationSender {

    public EmailSender(AuditLog audit) {
        super(audit);
    }

    @Override
    protected void doSend(String subject, String body, String email, String phone) {
        System.out.println("EMAIL -> to=" + email +
                " subject=" + subject +
                " body=" + body);

        audit.add("email sent");
    }
}
public class WhatsAppSender extends NotificationSender {

    public WhatsAppSender(AuditLog audit) {
        super(audit);
    }

    @Override
    protected void doSend(String subject, String body, String email, String phone) {

        // Instead of throwing → graceful handling
        if (phone == null || !phone.startsWith("+")) {
            System.out.println("WA ERROR: phone must start with + and country code");
            audit.add("WA failed");
            return;
        }

        System.out.println("WA -> to=" + phone + " body=" + body);
        audit.add("wa sent");
    }
}
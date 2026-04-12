public abstract class NotificationSender {
    protected final AuditLog audit;

    protected NotificationSender(AuditLog audit) {
        this.audit = audit;
    }

    /**
     * CONTRACT:
     * - n must not be null
     * - Missing fields handled gracefully (no exception)
     * - No sender should tighten preconditions
     * - Failures handled uniformly (no runtime exceptions)
     */
    public final void send(Notification n) {
        if (n == null) return;

        String subject = n.subject == null ? "" : n.subject;
        String body = n.body == null ? "" : n.body;
        String email = n.email;
        String phone = n.phone;

        doSend(subject, body, email, phone);
    }

    protected abstract void doSend(String subject, String body, String email, String phone);
}
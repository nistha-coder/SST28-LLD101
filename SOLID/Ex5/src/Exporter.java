public abstract class Exporter {

    /**
     * CONTRACT:
     * - req must not be null
     * - title/body null treated as ""
     * - MAY throw RuntimeException for format limitations
     * - MUST NOT corrupt data silently
     */
    public final ExportResult export(ExportRequest req) {
        if (req == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        String title = req.title == null ? "" : req.title;
        String body = req.body == null ? "" : req.body;

        return doExport(title, body);
    }

    protected abstract ExportResult doExport(String title, String body);
}
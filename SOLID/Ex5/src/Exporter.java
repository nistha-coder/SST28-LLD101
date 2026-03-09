public abstract class Exporter {
    // implied "contract" but not enforced (smell)
     /**
     * CONTRACT:
     * - req must not be null
     * - exporter must return ExportResult
     * - exporter may signal format limitations via RuntimeException
     */
    public abstract ExportResult export(ExportRequest req);
}
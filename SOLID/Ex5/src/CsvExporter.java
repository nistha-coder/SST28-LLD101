import java.nio.charset.StandardCharsets;

public class CsvExporter extends Exporter {

    @Override
    protected ExportResult doExport(String title, String body) {
        // KEEP lossy logic to preserve output
        String safeBody = body.replace("\n", " ").replace(",", " ");

        String csv = "title,body\n" + title + "," + safeBody + "\n";

        return new ExportResult("text/csv",
                csv.getBytes(StandardCharsets.UTF_8));
    }
}
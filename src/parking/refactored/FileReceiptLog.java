package parking.refactored;

import java.io.*;

public class FileReceiptLog implements ReceiptLog {
    private final String fileName;
    public FileReceiptLog(String fileName) { this.fileName = fileName; }

    public void log(String line) {
        try (FileWriter fw = new FileWriter(fileName, true)) {
            fw.write(line + System.lineSeparator());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}

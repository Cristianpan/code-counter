package mantenimiento.codecounter;

import java.nio.file.Path;

public abstract class Reporter {
    protected String programName;
    protected LineCounter lineCounter;

    public Reporter(Path filePath, LineCounter lineCounter) {
        this.programName = filePath.getFileName().toString();
        this.lineCounter = lineCounter;
    }

    public abstract void generateReport();
}

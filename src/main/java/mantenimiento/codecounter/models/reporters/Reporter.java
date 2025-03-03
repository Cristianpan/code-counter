package mantenimiento.codecounter.models.reporters;
import mantenimiento.codecounter.models.LineCounter;

import java.nio.file.Path;
import java.util.List;

public abstract class Reporter {
    protected String programName;
    protected List<LineCounter> lineCounters;

    public Reporter(Path filePath, List<LineCounter> lineCounters) {
        this.programName = filePath.getFileName().toString();
        this.lineCounters = lineCounters;
    }

    public abstract void generateReport();
}

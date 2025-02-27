package mantenimiento.codecounter.models.reporters;

import mantenimiento.codecounter.models.LineCounter;
import java.nio.file.Path;

public class TerminalReporter extends Reporter {

    public TerminalReporter(Path filePath, LineCounter lineCounter) {
        super(filePath, lineCounter);
    }

    @Override
    public void generateReport() {
        System.out.println("Reporte para el programa: " + this.programName);
        System.out.println(this.lineCounter);
    }
}

package mantenimiento.codecounter;

import java.nio.file.Path;

public class TerminalReporter extends Reporter {

    public TerminalReporter(Path filePath, LineCounter lineCounter) {
        super(filePath, lineCounter);
    }

    @Override
    public void generateReport() {
        System.out.println("Reporte para el programa: " + programName);
        System.out.println(lineCounter);
    }
}

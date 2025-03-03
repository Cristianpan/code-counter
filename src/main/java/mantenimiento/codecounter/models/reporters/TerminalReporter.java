package mantenimiento.codecounter.models.reporters;

import mantenimiento.codecounter.models.LineCounter;
import java.nio.file.Path;
import java.util.List;

public class TerminalReporter extends Reporter {

    public TerminalReporter(Path filePath, List<LineCounter> lineCounter) {
        super(filePath, lineCounter);
    }

    @Override
    public void generateReport() {
        printHeader();
        printBody();
    }

    private void printHeader() {
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Programa: " + this.programName);
        System.out.println("----------------------------------------------------------------------------");
        System.out.printf(" %-40s  %-15s  %-15s \n", "Archivo", "Líneas Físicas", "Líneas Lógicas");
        System.out.println("----------------------------------------------------------------------------");

    }

    private void printBody() {
        for (LineCounter lineCounter : this.lineCounters) {
            System.out.printf(" %-40s  %-15d  %-15d \n", lineCounter.getFileName(),
                    lineCounter.getPhysicalLineAmount(), lineCounter.getLogicalLineAmount());
        }
        System.out.println("----------------------------------------------------------------------------");
    }
}

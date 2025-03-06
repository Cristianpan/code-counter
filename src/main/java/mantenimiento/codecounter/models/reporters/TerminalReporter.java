package mantenimiento.codecounter.models.reporters;

import mantenimiento.codecounter.models.LineCounter;
import java.nio.file.Path;
import java.util.List;

/**
 * Clase que genera un reporte en la terminal
 */
public class TerminalReporter extends Reporter {

    public TerminalReporter(Path filePath, List<LineCounter> lineCounter) {
        super(filePath, lineCounter);
    }

    /**
     * Genera un reporte con la información de los contadores de líneas
     */
    @Override
    public void generateReport() {
        printHeader();
        printBody();
    }

    /**
     * Imprime el encabezado del reporte incluyengo: el nombre del programa y los
     * nombres de las columnas
     */
    private void printHeader() {
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Programa: " + this.programName);
        System.out.println("----------------------------------------------------------------------------");
        System.out.printf(" %-40s  %-15s  %-15s \n", "Archivo", "Líneas Físicas", "Líneas Lógicas");
        System.out.println("----------------------------------------------------------------------------");

    }

    /**
     * Imprime el cuerpo del reporte incluyendo la información de los contadores de
     * líneas por archivo analizado
     */
    private void printBody() {
        for (LineCounter lineCounter : this.lineCounters) {
            System.out.printf(" %-40s  %-15d  %-15d \n", lineCounter.getFileName(),
                    lineCounter.getPhysicalLineAmount(), lineCounter.getLogicalLineAmount());
        }
        System.out.println("----------------------------------------------------------------------------");
    }
}

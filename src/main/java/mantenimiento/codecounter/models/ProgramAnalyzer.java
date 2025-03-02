package mantenimiento.codecounter.models;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import mantenimiento.codecounter.exceptions.FileNotFoundException;
import mantenimiento.codecounter.exceptions.FolderNotFoundException;
import mantenimiento.codecounter.exceptions.InvalidFormatException;
import mantenimiento.codecounter.exceptions.JavaFilesNotFoundException;
import mantenimiento.codecounter.interfaces.FormatValidatorHandler;
import mantenimiento.codecounter.interfaces.LogicalValidatorHandler;
import mantenimiento.codecounter.models.reporters.Reporter;
import mantenimiento.codecounter.models.reporters.TerminalReporter;
import mantenimiento.codecounter.utils.JavaFilesScanner;
import mantenimiento.codecounter.validators.ValidatorManager;

/**
 * Clase encargada de analizar archivos Java dentro de una carpeta, contando
 * líneas de código
 * físicas y lógicas, y generando un reporte con los resultados.
 */
public class ProgramAnalyzer {
    /**
     * Analiza los archivos Java dentro de la carpeta especificada, contando líneas
     * de código y
     * generando un reporte con los resultados.
     *
     * @param folderPath Ruta de la carpeta que contiene los archivos Java.
     */
    public static void analyzeProgram(String folderPath) {
        try {
            List<Path> javaFilePaths = JavaFilesScanner.getJavaFiles(folderPath);
            LineCounter lineCounter = processFiles(javaFilePaths);
            generateReport(folderPath, lineCounter);
        } catch (FolderNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (JavaFilesNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InvalidFormatException e) {
            System.out.println("Error de formato en archivo: " + e.getFileName());
            System.out.println("Razon: " + e.getMessage());
        } catch (Exception e) {
        }
    }

    /**
     * Procesa los archivos Java proporcionados, validando su formato y lógica antes
     * de contar
     * sus líneas de código.
     *
     * @param javaFilePaths Lista de rutas de archivos Java a procesar.
     * @return Un objeto {@link LineCounter} con el conteo de líneas físicas y
     *         lógicas.
     * @throws FileNotFoundException  Si alguno de los archivos no se encuentra.
     * @throws InvalidFormatException Si se encuentra un error de formato en algún
     *                                archivo.
     */
    private static LineCounter processFiles(List<Path> javaFilePaths)
            throws FileNotFoundException, InvalidFormatException {
        LineCounter lineCounter = new LineCounter();
        FormatValidatorHandler formatValidator = ValidatorManager.getFormatValidator();
        LogicalValidatorHandler logicalValidator = ValidatorManager.getLogicalValidator();

        for (Path filePath : javaFilePaths) {
            List<String> fileContent = new JavaFile(filePath)
                    .removeComments()
                    .removeBlankLines()
                    .getContent();

            try {
                countLines(fileContent, formatValidator, logicalValidator, lineCounter);

            } catch (InvalidFormatException e) {
                e.setFileName(filePath.getFileName().toString());
                throw e;
            }
        }
        return lineCounter;
    }

    /**
     * Cuenta las líneas físicas y lógicas de un archivo Java validando su formato y
     * contenido lógico.
     *
     * @param fileContent      Contenido del archivo sin comentarios ni líneas en
     *                         blanco.
     * @param formatValidator  Validador de formato de líneas.
     * @param logicalValidator Validador de lógica de líneas.
     * @param lineCounter      Contador de líneas donde se almacenan los resultados.
     * @throws InvalidFormatException Si alguna línea tiene un formato incorrecto.
     */
    private static void countLines(List<String> fileContent, FormatValidatorHandler formatValidator,
            LogicalValidatorHandler logicalValidator, LineCounter lineCounter) throws InvalidFormatException {

        for (String line : fileContent) {
            if (formatValidator.isValid(line.trim())) {
                lineCounter.incrementPhysicalLineAmount();
                if (logicalValidator.isValid(line)) {
                    lineCounter.incrementLogicalLineAmount();
                }
            }
        }
    }

    /**
     * Genera un reporte con los resultados del análisis de líneas de código.
     *
     * @param folderPath  Ruta de la carpeta analizada.
     * @param lineCounter Contador de líneas de código con los resultados del
     *                    análisis.
     */
    private static void generateReport(String folderPath, LineCounter lineCounter) {
        Reporter reporter = new TerminalReporter(Paths.get(folderPath), lineCounter);
        reporter.generateReport();
    }
}
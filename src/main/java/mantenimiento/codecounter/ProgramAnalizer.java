package mantenimiento.codecounter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import mantenimiento.codecounter.exceptions.FileNotFoundException;
import mantenimiento.codecounter.exceptions.FolderNotFoundException;
import mantenimiento.codecounter.exceptions.InvalidFormatException;
import mantenimiento.codecounter.exceptions.JavaFilesNotFoundException;
import mantenimiento.codecounter.interfaces.FormatValidatorHandler;
import mantenimiento.codecounter.interfaces.LogicalValidatorHandler;
import mantenimiento.codecounter.models.JavaFile;
import mantenimiento.codecounter.models.LineCounter;
import mantenimiento.codecounter.models.reporters.Reporter;
import mantenimiento.codecounter.models.reporters.TerminalReporter;
import mantenimiento.codecounter.utils.JavaFilesScanner;
import mantenimiento.codecounter.validators.ValidatorManager;

public class ProgramAnalizer {
    public static void analiceProgram(String folderPath) {
        try {
            List<Path> javaFilePaths = JavaFilesScanner.getJavaFiles(folderPath);
            LineCounter lineCounter = processFiles(javaFilePaths);
            generateReport(folderPath, lineCounter);
        } catch (FolderNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (JavaFilesNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InvalidFormatException e) {
            System.out.println("Error de formato en archivo: " + folderPath);
            System.out.println("Razon: " + e.getMessage());
        } catch (Exception e) {
        }
    }

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

    private static void countLines(List<String> fileContent, FormatValidatorHandler formatValidator,
            LogicalValidatorHandler logicalValidator, LineCounter lineCounter) throws InvalidFormatException {
        List<String> fileContentCopy = new ArrayList<>(fileContent);

        for (String line : fileContent) {
            if (formatValidator.isValid(line.trim())) {
                lineCounter.incrementPhysicalLineAmount();
                if (!fileContentCopy.isEmpty() && logicalValidator.isValid(fileContentCopy)) {
                    lineCounter.incrementLogicalLineAmount();
                }
            }
        }
    }

    private static void generateReport(String folderPath, LineCounter lineCounter) {
        Reporter reporter = new TerminalReporter(Paths.get(folderPath), lineCounter);
        reporter.generateReport();
    }
}
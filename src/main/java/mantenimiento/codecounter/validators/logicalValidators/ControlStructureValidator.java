package mantenimiento.codecounter.validators.logicalValidators;

//import static mantenimiento.codecounter.constants.JavaRegextConstants.FLOW_CONTROL_REGEX;
//import static mantenimiento.codecounter.constants.JavaRegextConstants.CASE_DECLARATION_REGEX;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.List;

/**
 * Clase que verifica la presencia de estructuras de control 
 * como if, else if, while, do-while, for, for-each y case.
 * 
 */
public class ControlStructureValidator extends LogicalValidator {

    private static final String CONTROL_STRUCTURE_REGEX = "(" +
    "(if|else\\s+if)\\s*\\([^)]*\\)\\s*" + "|" +  // if y else if deben tener paréntesis
    "(while|for)\\s*\\([^)]*\\)\\s*" + "|" +  // while y for deben tener paréntesis
    "do\\s*\\{[^}]*\\}\\s*while\\s*\\([^)]*\\)\\s*" + "|" +  // do-while debe tener paréntesis
    "for\\s*\\([a-zA-Z0-9_]+\\s*:\\s*[^)]+\\)\\s*" + "|" +  // for-each debe tener paréntesis
    "case\\s+[^:]+:\\s*(\\{\\s*)?" +  // case con expresión antes del ':', y opcionalmente seguido de una '{'
    ")";

    /**
     * Verifica si la primera línea de código contiene una estructura de control.
     * @param linesOfCode Lista de líneas de código a analizar.
     * @return true si se encuentra una estructura de control, false en caso contrario.
     */
    @Override
    public boolean isValid(List<String> linesOfCode) {
        if (linesOfCode.isEmpty()) {
            return false;
        }

        // Compilar el regex
        Pattern pattern = Pattern.compile(CONTROL_STRUCTURE_REGEX);

        for (String line : linesOfCode) {
            String cleanedLine = line.trim();
            System.out.println("Evaluando: " + cleanedLine);
            
            // Crear un Matcher para la línea actual
            Matcher matcher = pattern.matcher(cleanedLine);
            
            // Usar find() para buscar coincidencias en cualquier parte de la línea
            if (matcher.find()) {
                return true;
            }
        }

        // Si ninguna línea coincide, pasamos la validación al siguiente en la cadena
        return validateNext(linesOfCode);
    }
}
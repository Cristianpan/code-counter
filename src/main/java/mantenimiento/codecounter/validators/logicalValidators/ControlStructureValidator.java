package mantenimiento.codecounter.validators.logicalValidators;

import static mantenimiento.codecounter.constants.JavaRegextConstants.FLOW_CONTROL_REGEX;

import java.util.List;

/**
 * Clase que verifica la presencia de estructuras de control
 * como if, else if, while, do-while, for, for-each y case.
 * 
 */
public class ControlStructureValidator extends LogicalValidator {

    private static final String CONTROL_STRUCTURE_REGEX = "^(" + FLOW_CONTROL_REGEX
            + " |}\\s*while\\s*)\\([^)]*\\s*\\)?.*";

    /**
     * Verifica si la primera línea de código contiene una estructura de control.
     * 
     * @param linesOfCode Lista de líneas de código a analizar.
     * @return {@code true} si se encuentra una estructura de control, {@code false}
     *         en caso
     *         contrario.
     */
    @Override
    public boolean isValid(List<String> linesOfCode) {

        if (isLogicalControlStructureDeclaration(linesOfCode.getFirst()) && isValidStructure(linesOfCode)) {
            return true;
        }

        return validateNext(linesOfCode);
    }

    /**
     * Verifica si una línea de código es una declaración de estructura de control
     * lógico,
     * excluyendo las declaraciones de "switch".
     *
     * @param lineOfCode Línea de código a evaluar.
     * @return {@code true} si es una estructura de control lógica válida,
     *         {@code false} en caso contrario.
     */
    private boolean isLogicalControlStructureDeclaration(String lineOfCode) {
        return lineOfCode.trim().matches(CONTROL_STRUCTURE_REGEX) && !lineOfCode.trim().contains("switch");
    }

    /**
     * Verifica si una lista de líneas de código contiene una estructura válida,
     * buscando el cierre de una declaración con ")", seguido de cualquier otro
     * contenido.
     *
     * @param linesOfCode Lista de líneas de código a evaluar.
     * @return {@code true} si al menos una línea cumple con el patrón,
     *         {@code false} en caso contrario.
     */
    private boolean isValidStructure(List<String> linesOfCode) {
        int i = 1;
        for (String lineOfCode : linesOfCode) {
            if (lineOfCode.trim().matches("[^)]*\\s*\\).*")) {
                removeLines(linesOfCode, i);
                return true;
            }
            i++;
        }
        return false;
    }
}
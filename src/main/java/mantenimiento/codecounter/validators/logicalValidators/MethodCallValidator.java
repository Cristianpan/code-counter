package mantenimiento.codecounter.validators.logicalValidators;

import static mantenimiento.codecounter.constants.JavaRegextConstants.IDENTIFIER_DECLARATION_REGEX;
import static mantenimiento.codecounter.constants.JavaRegextConstants.PARAMETERS_DECLARATION_REGEX;

import java.util.List;

/**
 * clase que valida si se la sentencia corresponde a una llamada a un método
 * y de forma correcta, con terminacion );
 */
public class MethodCallValidator extends LogicalValidator {
    private static final String OBJECT_METHOD_CALL_REGEX = "^\\s*(" + IDENTIFIER_DECLARATION_REGEX + "\\.)+("
            + IDENTIFIER_DECLARATION_REGEX + "(" + PARAMETERS_DECLARATION_REGEX + "|\\())+.*";

    private static final String METHOD_CALL_REGEX = "^([a-zA-Z_]+("
            + PARAMETERS_DECLARATION_REGEX + "|\\()).*";

    /**
     * valida si existe la llamada a un metodo de forma correcta, es decir, que
     * incluya el cierre ");"
     */
    @Override
    public boolean isValid(List<String> linesOfCode) {
        if (isMethodCall(linesOfCode.getFirst()) && isCompleteMethodCall(linesOfCode)) {
            return true;
        }

        return this.validateNext(linesOfCode);
    }

    /**
     * valida si existe una llamada a un metodo considerando los siguientes
     * ejemplos:
     * ej: object.method();
     * ej: method();
     * 
     * @param lineOfCode sentencia por analizar
     * @return true si coincide con una llamada a un metodo, false en caso contrario
     */
    private boolean isMethodCall(String lineOfCode) {
        return lineOfCode.trim().matches(OBJECT_METHOD_CALL_REGEX) || lineOfCode.trim().matches(METHOD_CALL_REGEX);
    }

    /**
     * valida si la llamada al metodo termina con );, es decir,
     * que este completa para ser considerada como linea logica
     * 
     * @param linesOfCode lineas de codigo por analizar
     * @return true si se encuentra la terminacion );, false en caso contrario
     */
    private boolean isCompleteMethodCall(List<String> linesOfCode) {
        int lastIndex = 1;
        for (String lineOfCode : linesOfCode) {
            if (lineOfCode.matches("([^)]*\\).?)+\\s*;.*")) {
                removeLines(linesOfCode, lastIndex);
                return true;
            }
            lastIndex++;
        }
        return false;
    }
}

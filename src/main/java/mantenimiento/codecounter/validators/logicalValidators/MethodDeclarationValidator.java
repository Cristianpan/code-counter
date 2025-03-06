package mantenimiento.codecounter.validators.logicalValidators;

import static mantenimiento.codecounter.constants.JavaRegextConstants.ACCESS_MODIFIERS_REGEX;
import static mantenimiento.codecounter.constants.JavaRegextConstants.DATATYPE_DECLARATION_REGEX;
import static mantenimiento.codecounter.constants.JavaRegextConstants.FINAL_OR_STATIC_REGEX;
import static mantenimiento.codecounter.constants.JavaRegextConstants.IDENTIFIER_DECLARATION_REGEX;

/**
 * Clase que verifica si existe una declaración de un método para poder
 * contabilizarlo como línea lógica
 */
public class MethodDeclarationValidator extends LogicalValidator {

    private static final String METHOD_DECLARATION = "^(\\s*" + ACCESS_MODIFIERS_REGEX + FINAL_OR_STATIC_REGEX
            + DATATYPE_DECLARATION_REGEX + IDENTIFIER_DECLARATION_REGEX + "\\(.*)";

    /**
     * Determina si una línea de código corresponde a una declaración
     * de un método para considerarla como línea lógica
     * 
     * @param lineOfCode linea de código por analizar
     * @return {@code true} si es declaración de método, {@code false} en caso
     *         contrario
     */
    @Override
    public boolean isValid(String lineOfCode) {
        return isMethodDeclaration(lineOfCode) || this.validateNext(lineOfCode);
    }

    /**
     * Verifica si es una declaración de método 
     * 
     * @param lineOfCode linea de código por analizar
     * @return {@code true} si es declaración de método, {@code false} en caso
     *         contrario
     */
    private boolean isMethodDeclaration(String lineOfCode) {
        return lineOfCode.matches(METHOD_DECLARATION) && !lineOfCode.contains(";");
    }
}

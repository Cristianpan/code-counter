package mantenimiento.codecounter.validators.logicalValidators;

/**
 * Clase que verifica si existe una declaración de una operación ternaria para
 * contabilizarlo como línea lógica
 */
public class TernaryDeclarationValidator extends LogicalValidator {
    private static final String TERNARY_DECLARATION = "(?m)^(\\s*(?:\\w+\\s+\\w+|return)\\s*=?\\s*[^?]+\\?[^:]+:[^;]+;)$";

    /**
     * Determina si una línea de código corresponde a una declaración de una
     * operación ternaria para considerarla como línea lógica
     * 
     * @param linesOfCode
     * @return {@code true} si la corresponde a una declaración de una operación
     *         ternaria {@code false} en caso contrario
     */
    @Override
    public boolean isValid(String lineOfCode) {
        return isTernaryOperationDeclaration(lineOfCode) || this.validateNext(lineOfCode);
    }

    /**
     * Verifica si la sentencia es una declaración de una operación ternaria
     * 
     * @param lineOfCode sentencia de codigo por analizar
     * @return {@code true} si coincide con la declaracion, {@code false} en caso
     *         contrario
     */
    private boolean isTernaryOperationDeclaration(String lineOfCode) {
        return lineOfCode.matches(TERNARY_DECLARATION);
    }
}

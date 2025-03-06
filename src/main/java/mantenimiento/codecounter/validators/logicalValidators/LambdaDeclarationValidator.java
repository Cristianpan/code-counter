package mantenimiento.codecounter.validators.logicalValidators;

/**
 * Clase que verifica la presencia de declaraciones lambda
 */
public class LambdaDeclarationValidator extends LogicalValidator {

    private static final String LAMBDA_DECLARATION = "^(?<![\"']).*(=|return)\\s+\\(?.*\\)?\\s*->\\s*\\{?.*\\}?;?\\s*(?<![\"'])";

    /**
     * Determina si una línea de código corresponde a una declaración
     * de una función lambda para considerarla como línea lógica
     * 
     * @param lineOfCode linea de código por analizar.
     * @return {@code true} si corresponde a la declaración de una función lambda,
     *         {@code false} en caso contrario
     */
    @Override
    public boolean isValid(String lineOfCode) {
        return isLambdaDeclaration(lineOfCode) || this.validateNext(lineOfCode);
    }

    /**
     * Verifica si es una declaración de función lambda
     * 
     * @param lineOfCode linea de código por analizar
     * @return {@code true} si corresponde a la declaración de una función lambda,
     *         {@code false} en caso contrario
     */
    private boolean isLambdaDeclaration(String lineOfCode) {
        return lineOfCode.matches(LAMBDA_DECLARATION);
    }
}

package mantenimiento.codecounter.validators.logicalValidators;

import static mantenimiento.codecounter.constants.JavaRegextConstants.TRY_DECLARATION;

public class TryDeclarationValidator extends LogicalValidator {
    /**
     * Valida si la linea por analizar contiene una declaracion try-with-resources o
     * catch , bien formada.
     * 
     * @param linesOfCode
     * @return {@code true} si la declaracion es correcta
     */
    @Override
    public boolean isValid(String linesOfCode) {
        return isTryDeclartion(linesOfCode) || this.validateNext(linesOfCode);
    }

    /**
     * 
     * verifica si la sentencia es una declaración de try-with-resources
     * o un catch
     * 
     * @param lineOfCode sentencia de codigo por analizar
     * @return {@code true} si coincide con la declaracion
     */
    private boolean isTryDeclartion(String lineOfCode) {
        return lineOfCode.trim().matches(TRY_DECLARATION);
    }
}

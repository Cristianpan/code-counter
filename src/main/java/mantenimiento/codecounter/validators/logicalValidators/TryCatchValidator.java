package mantenimiento.codecounter.validators.logicalValidators;

import java.util.List;

public class TryCatchValidator extends LogicalValidator {

    private static final String TRY_CATCH_REGEX = "^\\s*(try|}\\s*catch)\\s*\\(.*";

    /**
     * Valida si la linea por analizar contiene una declaracion try-with-resources o
     * catch , bien formada.
     * 
     * @param linesOfCode
     * @return {@code true} si la declaracion es correcta
     */
    @Override
    public boolean isValid(List<String> linesOfCode) {
        if (isExceptionEstructure(linesOfCode.getFirst()) && isCompleteSentence(linesOfCode)) {
            return true;
        }

        return this.validateNext(linesOfCode);
    }

    /**
     * 
     * verifica si la sentencia es una declaración de try-with-resources
     * o un catch
     * 
     * @param lineOfCode sentencia de codigo por analizar
     * @return {@code true} si coincide con la declaracion
     */
    private boolean isExceptionEstructure(String lineOfCode) {
        return lineOfCode.trim().matches(TRY_CATCH_REGEX);
    }

    /**
     * verifica si la sentencia del try-with-resources
     * o el catch, esta declarada correctamente, es decir, si cierra con )
     * 
     * @param linesOfCode lines de codigo por analizad
     * @return {@code true} si cierra con )
     */
    private boolean isCompleteSentence(List<String> linesOfCode) {
        int lastIndex = 1; 
        for (String lineOfCode : linesOfCode) {
            if (lineOfCode.trim().matches("[^\\)]*\\)\\s*}?.*")) {
                removeLines(linesOfCode, lastIndex);
                return true;
            }
            lastIndex++; 
        }
        return false;
    }
}

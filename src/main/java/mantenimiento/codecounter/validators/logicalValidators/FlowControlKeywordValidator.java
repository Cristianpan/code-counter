package mantenimiento.codecounter.validators.logicalValidators;

import java.util.Arrays;
import java.util.List;

/**
 * clase que valida si existe una declaración de palabra clave
 * para control de flujo y que termine correctamente en ;
 */
public class FlowControlKeywordValidator extends LogicalValidator {
    public final static String FLOW_CONTROL_KEYWORDS_REGEX = "((break|continue)\\s*;|(return|throw)).*";

    /**
     * valida si existe una palabra clave de control de flujo
     * y que termine correctamente con ;
     */
    @Override
    public boolean isValid(List<String> linesOfCode) {
        if (isFlowControlKeyword(linesOfCode.getFirst()) && endsWithSemiColon(linesOfCode)) {
            return true;
        }

        return this.validateNext(linesOfCode);
    }

    /**
     * valida que la sentencia corresponda a una
     * palabra clave de control de flujo: break, continue, throw
     * 
     * @param lineOfCode sentencia por analizar
     * @return true si corresponde a una palabra clave de control de flujo, false en
     *         caso contrario
     */
    private boolean isFlowControlKeyword(String lineOfCode) {
        return lineOfCode.matches(FLOW_CONTROL_KEYWORDS_REGEX);
    }

    /**
     * valida que la linea termina con ; o si existe el ; después de la linea
     * 
     * @param linesOfCode lineas de codigo por analizar
     * @return true si se encuentra el ;, false en caso contrario
     */
    private boolean endsWithSemiColon(List<String> linesOfCode) {
        for (String lineOfCode : linesOfCode) {
            if (lineOfCode.endsWith(";")) {
                return true;
            }
        }

        return false;
    }
}
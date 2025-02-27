package mantenimiento.codecounter.validators.logicalValidators;

import java.util.List;

/**
 * clase que valida si existe una declaración de palabra clave
 * para control de flujo y que termine correctamente en ;
 */
public class FlowControlKeywordValidator extends LogicalValidator {
    public final static String FLOW_CONTROL_KEYWORDS_REGEX = "^((\\s*break|continue)\\s*;|(return|throw)|case\\s+.+:).*";

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
     * @return {@code true} si corresponde a una palabra clave de control de flujo, {@code false} en
     *         caso contrario
     */
    private boolean isFlowControlKeyword(String lineOfCode) {
        return lineOfCode.trim().matches(FLOW_CONTROL_KEYWORDS_REGEX);
    }

    /**
     * valida que la linea termina con ; o si existe el ; después de la linea
     * 
     * @param linesOfCode lineas de codigo por analizar
     * @return {@code true} si se encuentra el ;, {@code false} en caso contrario
     */
    private boolean endsWithSemiColon(List<String> linesOfCode) {
        int i = 1;
        for (String lineOfCode : linesOfCode) {
            if (lineOfCode.trim().endsWith(";") || lineOfCode.trim().endsWith(":")) {
                removeLines(linesOfCode, i);
                return true;
            }
            i++;
        }

        return false;
    }
}
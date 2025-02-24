package mantenimiento.codecounter.validators.logicalValidators;

import java.util.List;

import static mantenimiento.codecounter.constants.JavaRegextConstants.ACCESS_MODIFIERS_REGEX;
import static mantenimiento.codecounter.constants.JavaRegextConstants.DATATYPE_DECLARATION_REGEX;
import static mantenimiento.codecounter.constants.JavaRegextConstants.FINAL_OR_STATIC_REGEX;
import static mantenimiento.codecounter.constants.JavaRegextConstants.IDENTIFIER_DECLARATION_REGEX;

/**
 * Clase que valida si existe una declaracion con inicializacion
 * incluso si hay un salto de linea, considerando variables locales
 * y atributos
 * 
 * ej: int a = 1;
 * ej: string value = "string 1" + "string 2" +
 * "sring 3";
 */
public class AssignmentValidator extends LogicalValidator {

    private static final String ASSIGMENT_REGEX = "^" + ACCESS_MODIFIERS_REGEX
            + FINAL_OR_STATIC_REGEX + DATATYPE_DECLARATION_REGEX + "?" + IDENTIFIER_DECLARATION_REGEX + "=\\s*[^;]+;?";

    /**
     * Valida si existe una asignacion y si esta termina con ; 
     * para conciderarlo como linea logica, incluso si hay un salto de línea
     */
    @Override
    public boolean isValid(List<String> linesOfCode) {

        if (isAssigment(linesOfCode.get(0)) && endsWithSemiColon(linesOfCode)) {
            return true;
        }

        return this.validateNext(linesOfCode);
    }

    /**
     * valida si existe una asignacion, con o sin terminacion ;
     * ej: type id = value
     * ej: access final|static type id = value
     * ej: id = value
     * 
     * @param lineOfCode sentencia por analizar
     * @return true si coincide con el patron de asignacion, false en caso contrario
     */
    private boolean isAssigment(String lineOfCode) {
        return lineOfCode.matches(ASSIGMENT_REGEX);
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

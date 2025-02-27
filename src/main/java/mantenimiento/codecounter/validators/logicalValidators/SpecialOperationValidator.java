package mantenimiento.codecounter.validators.logicalValidators;

import static mantenimiento.codecounter.constants.JavaRegextConstants.IDENTIFIER_DECLARATION_REGEX;
import static mantenimiento.codecounter.constants.JavaRegextConstants.INCREMENT_OR_DECREMENT_OPERATOR_REGEX;
import static mantenimiento.codecounter.constants.JavaRegextConstants.OPERATORS_REGEX;

import java.util.List;

/**
 * Clase que valida si una línea de código contiene operaciones especiales de
 * asignación, incremento o decremento.
 */

public class SpecialOperationValidator extends LogicalValidator {

    /**
     * Expresión regular para identificar operaciones especiales de incremento o
     * decremento.
     * Detecta expresiones donde un operador de incremento o decremento aparece
     * antes o después de una variable.
     */
    private static final String SPECIAL_INCREMENT_OR_DECREMENT_OPERATION = "^\\s*("
            + INCREMENT_OR_DECREMENT_OPERATOR_REGEX + "\\w+|\\w+" + INCREMENT_OR_DECREMENT_OPERATOR_REGEX
            + ")\\s*;\\s*";

    /**
     * Expresión regular para detectar operaciones especiales de asignación.
     * Se asegura de que la asignación siga un formato específico utilizando
     * identificadores y operadores definidos.
     */
    private static final String SPECIAL_ASSIGNMENT_OPERATION_REGEX = "^\\s*"
            + IDENTIFIER_DECLARATION_REGEX + OPERATORS_REGEX + "=[^;]+;\\s*";

    /**
     * Verifica si la primera línea de código en la lista contiene una operación
     * especial.
     * Si la línea cumple con la condición, se elimina de la lista y se retorna
     * {@code true}.
     * En caso contrario, delega la validación a {@code validateNext}.
     *
     * @param linesOfCode Lista de líneas de código a validar.
     * @return {@code true} si la primera línea es una operación especial y se
     *         elimina; {@code false} en caso contrario.
     */
    @Override
    public boolean isValid(List<String> linesOfCode) {
        if (isSpecialOperator(linesOfCode.getFirst().trim())) {
            removeLines(linesOfCode, 1);
            return true;
        }
        return this.validateNext(linesOfCode);
    }

    /**
     * Determina si una línea de código es una operación especial de asignación,
     * incremento o decremento comparándola con las expresiones regulares definidas.
     *
     * @param lineOfCode Línea de código a evaluar.
     * @return {@code true} si la línea coincide con alguna de las expresiones
     *         regulares; {@code false} en caso contrario.
     */
    private boolean isSpecialOperator(String lineOfCode) {
        return lineOfCode.matches(SPECIAL_ASSIGNMENT_OPERATION_REGEX)
                || lineOfCode.matches(SPECIAL_INCREMENT_OR_DECREMENT_OPERATION);
    }
}

package mantenimiento.codecounter.validators.logicalValidators;

import static mantenimiento.codecounter.constants.JavaRegextConstants.IDENTIFIER_DECLARATION_REGEX;
import static mantenimiento.codecounter.constants.JavaRegextConstants.INCREMENT_OR_DECREMENT_OPERATOR_REGEX;
import static mantenimiento.codecounter.constants.JavaRegextConstants.OPERATORS_REGEX;

import java.util.List;

public class SpecialOperationValidator extends LogicalValidator {

    private static final String SPECIAL_INCREMENT_OR_DECREMENT_OPERATION = "^\\s*("
            + INCREMENT_OR_DECREMENT_OPERATOR_REGEX + "\\w+|\\w+" + INCREMENT_OR_DECREMENT_OPERATOR_REGEX + ")\\s*;\\s*";

    private static final String SPECIAL_ASSIGNMENT_OPERATION_REGEX = "^\\s*" + IDENTIFIER_DECLARATION_REGEX + OPERATORS_REGEX + "=[^;]+;\\s*";

    @Override
    public boolean isValid(List<String> linesOfCode) {
        if (isSpecialOperator(linesOfCode.getFirst().trim())) {
            removeLines(linesOfCode, 1);
            return true;
        }

        return this.validateNext(linesOfCode);
    }

    private boolean isSpecialOperator(String lineOfCode) {
        return lineOfCode.matches(SPECIAL_ASSIGNMENT_OPERATION_REGEX) || lineOfCode.matches(SPECIAL_INCREMENT_OR_DECREMENT_OPERATION);
    }
}

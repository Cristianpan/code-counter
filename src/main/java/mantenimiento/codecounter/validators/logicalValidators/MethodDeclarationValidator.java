package mantenimiento.codecounter.validators.logicalValidators;

import static mantenimiento.codecounter.constants.JavaRegextConstants.ACCESS_MODIFIERS_REGEX;
import static mantenimiento.codecounter.constants.JavaRegextConstants.DATATYPE_DECLARATION_REGEX;
import static mantenimiento.codecounter.constants.JavaRegextConstants.FINAL_OR_STATIC_REGEX;
import static mantenimiento.codecounter.constants.JavaRegextConstants.IDENTIFIER_DECLARATION_REGEX;

public class MethodDeclarationValidator extends LogicalValidator {

    private static final String METHOD_DECLARATION = "^(\\s*" + ACCESS_MODIFIERS_REGEX + FINAL_OR_STATIC_REGEX + DATATYPE_DECLARATION_REGEX + IDENTIFIER_DECLARATION_REGEX + "\\(.*)";

    @Override
    public boolean isValid(String lineOfCode) {
       return isMethodDeclaration(lineOfCode) || this.validateNext(lineOfCode);
    }

    private boolean isMethodDeclaration(String lineOfCode){
        return lineOfCode.matches(METHOD_DECLARATION) && !lineOfCode.contains(";");
    }
}

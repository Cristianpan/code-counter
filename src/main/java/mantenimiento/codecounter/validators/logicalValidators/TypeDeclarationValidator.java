package mantenimiento.codecounter.validators.logicalValidators;

import static mantenimiento.codecounter.constants.JavaRegextConstants.ACCESS_MODIFIERS_REGEX;
import static mantenimiento.codecounter.constants.JavaRegextConstants.TYPE_KEYS;

public class TypeDeclarationValidator extends LogicalValidator {

    private static final String TYPE_DECLARATION = "^(\\s*"  + ACCESS_MODIFIERS_REGEX + ".*\\s*" + TYPE_KEYS + ".*)"; 
    
    @Override
    public boolean isValid(String linesOfCode) {
        return isTypeDeclaration(linesOfCode) || this.validateNext(linesOfCode);
    }

    private boolean isTypeDeclaration(String line) {
        return line.matches(TYPE_DECLARATION);
    }
}

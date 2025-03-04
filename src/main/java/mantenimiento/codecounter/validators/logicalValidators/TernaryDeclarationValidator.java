package mantenimiento.codecounter.validators.logicalValidators;

public class TernaryDeclarationValidator extends LogicalValidator {
    private static final String TERNARY_DECLARATION = "(?<![\"']).*\\s*\\?\\s*.*\\s*:\\s*.*;?\\s*(?<![\"'])";

    public boolean isValid(String linesOfCode) {
        return isTernaryOperationDeclaration(linesOfCode) || this.validateNext(linesOfCode);
    }

    private boolean isTernaryOperationDeclaration(String lineOfCode) {
        return lineOfCode.matches(TERNARY_DECLARATION);
    }
}

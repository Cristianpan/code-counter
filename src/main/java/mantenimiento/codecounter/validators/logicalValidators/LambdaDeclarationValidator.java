package mantenimiento.codecounter.validators.logicalValidators;

public class LambdaDeclarationValidator extends LogicalValidator {

    private static final String LAMBDA_DECLARATION = "^(?<![\"']).*(=|return)\\s+\\(?.*\\)?\\s*->\\s*\\{?.*\\}?;?\\s*(?<![\"'])";
    
    @Override
    public boolean isValid(String linesOfCode) {
        return isLambdaDeclaration(linesOfCode) || this.validateNext(linesOfCode);
    }

    private boolean isLambdaDeclaration(String lineOfCode) {
        return lineOfCode.matches(LAMBDA_DECLARATION);
    }  
}

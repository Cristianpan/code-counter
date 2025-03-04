package mantenimiento.codecounter.validators;

import mantenimiento.codecounter.interfaces.FormatValidatorHandler;
import mantenimiento.codecounter.interfaces.LogicalValidatorHandler;
import mantenimiento.codecounter.validators.formatValidators.ImportValidator;
import mantenimiento.codecounter.validators.formatValidators.SingleAnnotationValidator;
import mantenimiento.codecounter.validators.formatValidators.SingleDeclarationValidator;
import mantenimiento.codecounter.validators.formatValidators.StyleKAndRValidator;
import mantenimiento.codecounter.validators.logicalValidators.FlowControlDeclarationValidator;
import mantenimiento.codecounter.validators.logicalValidators.LambdaDeclarationValidator;
import mantenimiento.codecounter.validators.logicalValidators.MethodDeclarationValidator;
import mantenimiento.codecounter.validators.logicalValidators.TernaryDeclarationValidator;
import mantenimiento.codecounter.validators.logicalValidators.TryDeclarationValidator;
import mantenimiento.codecounter.validators.logicalValidators.TypeDeclarationValidator;

/**
 * Brinda el acceso a los validadores de formato o de líneas lógicas
 */
public class ValidatorManager {
    private static FormatValidatorHandler formatValidator = null;
    private static LogicalValidatorHandler logicalValidator = null;

    /**
     * Genera la secuencia de validaciones de formato
     * 
     * @return Encadenamiento de validadores de format
     */
    public static FormatValidatorHandler getFormatValidator() {

        if (formatValidator != null) {
            return formatValidator;
        }

        FormatValidatorHandler importValidator = new ImportValidator();
        FormatValidatorHandler styleKAndRValidator = new StyleKAndRValidator();
        FormatValidatorHandler singleAnnotationValidator = new SingleAnnotationValidator();
        FormatValidatorHandler singleDeclarationValidator = new SingleDeclarationValidator();

        importValidator.setNextValidator(styleKAndRValidator);
        styleKAndRValidator.setNextValidator(singleAnnotationValidator);
        singleAnnotationValidator.setNextValidator(singleDeclarationValidator);
        formatValidator = importValidator;

        return formatValidator;
    }

    /**
     * Genera la secuencia de validaciones de líneas lógicas
     * 
     * @return Encadenamiento de validadores de líneas lógicas
     */
    public static LogicalValidatorHandler getLogicalValidator() {
        if (logicalValidator != null) {
            return logicalValidator;
        }

        LogicalValidatorHandler tryCatchValidator = new TryDeclarationValidator();
        LogicalValidatorHandler flowControlDeclarationValidator = new FlowControlDeclarationValidator();
        LogicalValidatorHandler typeDeclarationValidator = new TypeDeclarationValidator(); 
        LogicalValidatorHandler methodDeclarationValidator = new MethodDeclarationValidator(); 
        LogicalValidatorHandler lambdaDeclarationValidator = new LambdaDeclarationValidator(); 
        LogicalValidatorHandler ternaryDeclarationValidator = new TernaryDeclarationValidator();
        
        tryCatchValidator.setNextValidator(flowControlDeclarationValidator);
        flowControlDeclarationValidator.setNextValidator(typeDeclarationValidator);
        typeDeclarationValidator.setNextValidator(methodDeclarationValidator);
        typeDeclarationValidator.setNextValidator(lambdaDeclarationValidator);
        lambdaDeclarationValidator.setNextValidator(ternaryDeclarationValidator);
        logicalValidator = tryCatchValidator;

        return logicalValidator;
    }
}
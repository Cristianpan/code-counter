package mantenimiento.codecounter.validators;

import mantenimiento.codecounter.interfaces.FormatValidatorHandler;
import mantenimiento.codecounter.interfaces.LogicalValidatorHandler;
import mantenimiento.codecounter.validators.formatValidators.ImportValidator;
import mantenimiento.codecounter.validators.formatValidators.SingleAnnotationValidator;
import mantenimiento.codecounter.validators.formatValidators.SingleDeclarationValidator;
import mantenimiento.codecounter.validators.formatValidators.StyleKAndRValidator;
import mantenimiento.codecounter.validators.logicalValidators.AssignmentValidator;
import mantenimiento.codecounter.validators.logicalValidators.ControlStructureValidator;
import mantenimiento.codecounter.validators.logicalValidators.FlowControlKeywordValidator;
import mantenimiento.codecounter.validators.logicalValidators.MethodCallValidator;
import mantenimiento.codecounter.validators.logicalValidators.SpecialOperationValidator;
import mantenimiento.codecounter.validators.logicalValidators.TryCatchValidator;

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

        LogicalValidatorHandler assigmentValidator = new AssignmentValidator();
        LogicalValidatorHandler methodCallValidator = new MethodCallValidator();
        LogicalValidatorHandler tryCatchValidator = new TryCatchValidator();
        LogicalValidatorHandler flowControlKeywordValidator = new FlowControlKeywordValidator();
        LogicalValidatorHandler controlStructureValidator = new ControlStructureValidator();
        LogicalValidatorHandler specialOperationValidator = new SpecialOperationValidator();

        assigmentValidator.setNextValidator(tryCatchValidator);
        tryCatchValidator.setNextValidator(controlStructureValidator);
        controlStructureValidator.setNextValidator(methodCallValidator);
        methodCallValidator.setNextValidator(flowControlKeywordValidator);
        flowControlKeywordValidator.setNextValidator(specialOperationValidator);

        logicalValidator = assigmentValidator;

        return logicalValidator;
    }
}
package mantenimiento.codecounter.validators;

import mantenimiento.codecounter.exceptions.InvalidFormatException;
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
import mantenimiento.codecounter.validators.logicalValidators.TryCatchValidator;

public class ValidatorManager {
    private static FormatValidatorHandler formatValidator = null;
    private static LogicalValidatorHandler logicalValidator = null;

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

    public static LogicalValidatorHandler getLogicalValidator() {
        if (logicalValidator != null) {
            return logicalValidator;
        }

        LogicalValidatorHandler assigmentValidator = new AssignmentValidator(); 
        MethodCallValidator methodCallValidator = new MethodCallValidator(); 
        TryCatchValidator tryCatchValidator = new TryCatchValidator(); 
        LogicalValidatorHandler controlStructureValidator = new ControlStructureValidator(); 
        LogicalValidatorHandler flowControlKeywordValidator = new FlowControlKeywordValidator(); 

        assigmentValidator.setNextValidator(methodCallValidator);
        methodCallValidator.setNextValidator(tryCatchValidator);
        tryCatchValidator.setNextValidator(controlStructureValidator);
        controlStructureValidator.setNextValidator(flowControlKeywordValidator);

        logicalValidator = (flowControlKeywordValidator);

        return logicalValidator;
    }
}
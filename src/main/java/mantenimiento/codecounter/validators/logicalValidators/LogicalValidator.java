package mantenimiento.codecounter.validators.logicalValidators;

import java.util.List;

import mantenimiento.codecounter.interfaces.LogicalValidatorHandler;

public abstract class LogicalValidator implements LogicalValidatorHandler {
    private LogicalValidatorHandler nextValidator;

    @Override
    public void setNextValidator(LogicalValidatorHandler nextLogicalValidator) {
        this.nextValidator = nextLogicalValidator;
    }

    protected boolean validateNext(List<String> lineOfFile) {
        if (nextValidator != null) {
            return nextValidator.isValid(lineOfFile);
        }

        return false;
    }
}
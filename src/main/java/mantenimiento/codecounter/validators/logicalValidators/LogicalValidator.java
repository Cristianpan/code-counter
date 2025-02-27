package mantenimiento.codecounter.validators.logicalValidators;

import java.util.List;

import mantenimiento.codecounter.interfaces.LogicalValidatorHandler;

public abstract class LogicalValidator implements LogicalValidatorHandler {
    private LogicalValidatorHandler nextValidator;

    @Override
    public void setNextValidator(LogicalValidatorHandler nextLogicalValidator) {
        this.nextValidator = nextLogicalValidator;
    }

    protected void removeLines(List<String> linesOfFile, int lastIndex) {
        while (lastIndex > 0 && !linesOfFile.isEmpty()) {
            lastIndex--;
            linesOfFile.remove(0);
        }
    }

    protected boolean validateNext(List<String> linesOfFile) {
        if (nextValidator != null) {
            return nextValidator.isValid(linesOfFile);
        }

        removeLines(linesOfFile, 1);

        return false;
    }
}
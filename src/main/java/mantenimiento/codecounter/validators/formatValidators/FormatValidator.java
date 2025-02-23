package mantenimiento.codecounter.validators.formatValidators;

import mantenimiento.codecounter.exceptions.InvalidFormatException;
import mantenimiento.codecounter.interfaces.FormatValidatorHandler;

public abstract class FormatValidator implements FormatValidatorHandler {

    private FormatValidatorHandler nextValidator;

    @Override
    public void setNextValidator(FormatValidatorHandler nextFormatValidator) {
        this.nextValidator = nextFormatValidator;
    }

    protected boolean validateNext(String lineOfFile) throws InvalidFormatException {
        if (nextValidator != null) {
            return nextValidator.isValid(lineOfFile);
        }

        return true;
    }
}
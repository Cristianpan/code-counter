package mantenimiento.codecounter.validators.formatValidators;

import mantenimiento.codecounter.exceptions.InvalidFormatException;
import mantenimiento.codecounter.interfaces.ValidatorHandler;

public abstract class FormatValidator implements ValidatorHandler<InvalidFormatException> {

    private ValidatorHandler<InvalidFormatException> nextValidator;

    @Override
    public void setNextValidator(ValidatorHandler<InvalidFormatException> nextFormatValidator) {
        this.nextValidator = nextFormatValidator;
    }

    protected boolean validateNext(String lineOfFile) throws InvalidFormatException {
        if (nextValidator != null) {
            return nextValidator.isValid(lineOfFile);
        }
        
        return true;
    }
}
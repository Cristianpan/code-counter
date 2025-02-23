package mantenimiento.codecounter.interfaces;

import mantenimiento.codecounter.exceptions.InvalidFormatException;

public interface FormatValidatorHandler {
    public void setNextValidator(FormatValidatorHandler nextFormatValidator);
    public boolean isValid(String lineOfFile) throws InvalidFormatException;
}

package mantenimiento.codecounter.validators.formatValidators;

import mantenimiento.codecounter.exceptions.InvalidFormatException;
import mantenimiento.codecounter.interfaces.FormatValidatorHandler;

/**
 * Proporciona una implementación base para la validación de formato del código.
 * Implementa la interfaz {@link FormatValidatorHandler} y permite encadenar
 * validadores.
 */
public abstract class FormatValidator implements FormatValidatorHandler {

    private FormatValidatorHandler nextValidator;

    /**
     * Establece el siguiente validador en la cadena de validación lógica.
     * 
     * @param nextLogicalValidator Siguiente validador lógico en la cadena.
     */
    @Override
    public void setNextValidator(FormatValidatorHandler nextFormatValidator) {
        this.nextValidator = nextFormatValidator;
    }

    /**
     * Valida la siguiente regla en la cadena de responsabilidad.
     * 
     * @param linesOfFile Lista de líneas de código a validar.
     * @return {@code true} si la validación es exitosa, {@code false} en caso
     *         contrario.
     */
    protected boolean validateNext(String lineOfFile) throws InvalidFormatException {
        if (nextValidator != null) {
            return nextValidator.isValid(lineOfFile);
        }

        return true;
    }
}
package mantenimiento.codecounter.validators.logicalValidators;

import java.util.List;

import mantenimiento.codecounter.interfaces.LogicalValidatorHandler;

/**
 * Proporciona una implementación base para la validación lógica de código.
 * Implementa la interfaz {@link LogicalValidatorHandler} y permite encadenar
 * validadores.
 */
public abstract class LogicalValidator implements LogicalValidatorHandler {
    private LogicalValidatorHandler nextValidator;

    /**
     * Establece el siguiente validador en la cadena de validación lógica.
     * 
     * @param nextLogicalValidator Siguiente validador lógico en la cadena.
     */
    @Override
    public void setNextValidator(LogicalValidatorHandler nextLogicalValidator) {
        this.nextValidator = nextLogicalValidator;
    }

    /**
     * Remueve las líneas que han sido contabilizadas como lineas
     * lógicas
     * 
     * @param linesOfFile lineas de codigo a eliminar
     * @param lastIndex   último indice que ha sido contabilizado como líneas lógica
     */
    protected void removeLines(List<String> linesOfFile, int lastIndex) {
        while (lastIndex > 0 && !linesOfFile.isEmpty()) {
            lastIndex--;
            linesOfFile.remove(0);
        }
    }

    /**
     * Valida la siguiente regla en la cadena de responsabilidad.
     * 
     * @param linesOfFile Lista de líneas de código a validar.
     * @return {@code true} si la validación es exitosa, {@code false} en caso
     *         contrario.
     */
    protected boolean validateNext(List<String> linesOfFile) {
        if (nextValidator != null) {
            return nextValidator.isValid(linesOfFile);
        }

        removeLines(linesOfFile, 1);

        return false;
    }
}
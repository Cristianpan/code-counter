package mantenimiento.codecounter.interfaces;

import java.util.List;

/**
 * Define un manejador para la validación lógica de código fuente.
 * Permite establecer una cadena de responsabilidad entre validadores.
 */
public interface LogicalValidatorHandler {
    /**
     * Establece el siguiente validador en la cadena de validación lógica.
     * 
     * @param nextValidator Siguiente validador lógico en la cadena.
     */
    public void setNextValidator(LogicalValidatorHandler nexValidator);

    /**
     * Verifica si un conjunto de líneas de código cumple con las reglas lógicas
     * esperadas.
     * 
     * @param linesOfCode Lista de líneas de código a validar.
     * @return {@code true} si las líneas cumplen con las reglas lógicas,
     *         {@code false} en caso contrario.
     */
    public boolean isValid(List<String> linesOfCode);
}

package mantenimiento.codecounter.interfaces;

import mantenimiento.codecounter.exceptions.InvalidFormatException;

/**
 * Interfaz que define un manejador para la validación de formato en archivos.
 * Permite establecer una cadena de responsabilidad entre validadores.
 */
public interface FormatValidatorHandler {
    /**
     * Establece el siguiente validador en la cadena de validación.
     * 
     * @param nextFormatValidator Siguiente validador de formato en la cadena.
     */
    public void setNextValidator(FormatValidatorHandler nextFormatValidator);

    /**
     * Verifica si una línea del archivo cumple con el formato esperado.
     * 
     * @param lineOfFile Línea del archivo a validar.
     * @return {@code true} si la línea cumple con el formato, {@code false} en caso
     *         contrario.
     * @throws InvalidFormatException Si el formato es inválido.
     */
    public boolean isValid(String lineOfFile) throws InvalidFormatException;
}

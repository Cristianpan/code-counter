package mantenimiento.codecounter.exceptions;

public class InvalidFormatException extends Exception {

    private final static String ERROR_MESSAGE = "El código no sigue el formato del estándar de codificación. Razón: %s"; 
    
    public InvalidFormatException(String typeError) {
        super(String.format(ERROR_MESSAGE, typeError));
    }
}

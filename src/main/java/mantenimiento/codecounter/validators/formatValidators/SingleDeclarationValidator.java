package mantenimiento.codecounter.validators.formatValidators;

import mantenimiento.codecounter.exceptions.InvalidFormatException;

/**
 * Clase que valida si existe una sola variable declarada por línea
 * ejemplo: int value = 10;
 */
public class SingleDeclarationValidator extends FormatValidator {
    private static final String MULTIPLE_DECLARATIONS_WITH_SEMICOLON_REGEX = "^(.*;\\s*.*;)$";
    private static final String MULTIPLE_DECLARATIONS_WITH_COMMAS_REGEX = ".*,.*,.*";

    public SingleDeclarationValidator() {
        super();
    }

    /**
     * Realiza la validacion del formato verificando si
     * existe una sola declaración por línea
     * @param lineOfFile linea de texto (sentencia de codigo) a validar
     * @return true si la línea cuenta con una sola declaración
     * @throws InvalidFormatException si existen más variables declaradas
     */
    @Override
    public boolean isValid(String lineOfFile) throws InvalidFormatException {
        if (hasMultipleDeclarationsWithSemicolons(lineOfFile) || hasMultipleDeclarationsWithCommas(lineOfFile)) {
            throw new InvalidFormatException("Múltiples declaraciones por línea: " + lineOfFile);
        }

        return this.validateNext(lineOfFile); 
    }

    /**
     * Verifica si la línea contiene múltiples declaraciones separadas por comas.
     *
     * @param lineOfFile La línea del archivo a validar.
     * @return true si hay múltiples declaraciones separadas por comas, false en caso contrario.
     */
    public boolean hasMultipleDeclarationsWithCommas(String lineOfFile) {
        return lineOfFile.matches(MULTIPLE_DECLARATIONS_WITH_COMMAS_REGEX);
    }

    /**
     * Verifica si la línea contiene múltiples declaraciones separadas por punto y coma.
     *
     * @param lineOfFile La línea del archivo a validar.
     * @return true si hay múltiples declaraciones separadas por punto y coma, false en caso contrario.
     */
    public boolean hasMultipleDeclarationsWithSemicolons(String lineOfFile) {
        return lineOfFile.matches(MULTIPLE_DECLARATIONS_WITH_SEMICOLON_REGEX);
    }
}
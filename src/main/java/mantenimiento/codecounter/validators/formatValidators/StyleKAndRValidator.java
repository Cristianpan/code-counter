package mantenimiento.codecounter.validators.formatValidators;

import static mantenimiento.codecounter.constants.ReasonInvalidFormat.INVALID_STYLE_K_AND_R;

import mantenimiento.codecounter.exceptions.InvalidFormatException;

/**
 * Clase que valida si se cumple el formato de uso de llaves
 * K&R
 */
public class StyleKAndRValidator extends FormatValidator {

    private static final String INVALID_OPENING_BRACKED_REGEX = "^\\s*\\{\\s*";
    private static final String VALID_CLOSING_BRACKED_REGEX = "((\\s*\\}\\s*)|(\\s*\\}\\s*(while|else|catch|finally|\\)?;).*))";
    private static final String EMPTY_BRACKED_REGEX = "^\\s*\\{.*\\}\\s*";

    @Override
    public boolean isValid(String lineOfFile) throws InvalidFormatException {
        if (isInValidOpeningBracked(lineOfFile) || isInvalidClosingBracked(lineOfFile) || isEmptyBracked(lineOfFile)) {
            throw new InvalidFormatException(INVALID_STYLE_K_AND_R, lineOfFile);
        }
        return validateNext(lineOfFile);
    }

    /**
     * Verifica si la línea del archivo contiene un corchete de apertura no válido.
     *
     * @param lineOfFile Línea del archivo a evaluar.
     * @return {@code true} si la línea coincide con el patrón de corchete de
     *         apertura no
     *         válido, {@code false} en caso contrario.
     */
    private boolean isInValidOpeningBracked(String lineOfFile) {
        return lineOfFile.matches(INVALID_OPENING_BRACKED_REGEX);
    }

    /**
     * Verifica si la línea del archivo contiene un corchete de cierre no válido.
     *
     * @param lineOfFile Línea del archivo a evaluar.
     * @return {@code true} si la línea coincide con el patrón de corchete de cierre
     *         no válido, {@code false} en caso contrario.
     */
    private boolean isInvalidClosingBracked(String lineOfFile) {

        return (lineOfFile.startsWith("}") || lineOfFile.endsWith("}"))
                && !lineOfFile.matches(VALID_CLOSING_BRACKED_REGEX);
    }

    /**
     * Verifica si la línea del archivo contiene un par de corchetes vacío.
     *
     * @param lineOfFile Línea del archivo a evaluar.
     * @return {@code true} si la línea coincide con el patrón de corchetes vacíos,
     *         {@code false} en
     *         caso contrario.
     */
    private boolean isEmptyBracked(String lineOfFile) {
        return lineOfFile.matches(EMPTY_BRACKED_REGEX);
    }
}
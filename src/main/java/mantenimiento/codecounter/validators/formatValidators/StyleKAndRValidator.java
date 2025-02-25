package mantenimiento.codecounter.validators.formatValidators;

import static mantenimiento.codecounter.constants.JavaRegextConstants.ACCESS_MODIFIERS_REGEX;
import static mantenimiento.codecounter.constants.JavaRegextConstants.BOOLEANS_OPERATORS;
import static mantenimiento.codecounter.constants.JavaRegextConstants.CATCH_DECLARATION_REGEX;
import static mantenimiento.codecounter.constants.JavaRegextConstants.DATATYPE_DECLARATION_REGEX;
import static mantenimiento.codecounter.constants.JavaRegextConstants.FINALLY_DECLARATION_REGEX;
import static mantenimiento.codecounter.constants.JavaRegextConstants.FINAL_OR_STATIC_REGEX;
import static mantenimiento.codecounter.constants.JavaRegextConstants.IDENTIFIER_DECLARATION_REGEX;
import static mantenimiento.codecounter.constants.JavaRegextConstants.PARAMETERS_DECLARATION_REGEX;
import static mantenimiento.codecounter.constants.JavaRegextConstants.STRUCT_DECLARATION_REGEX;
import static mantenimiento.codecounter.constants.JavaRegextConstants.TRY_DECLARATION_REGEX;
import static mantenimiento.codecounter.constants.ReasonInvalidFormat.INVALID_STYLE_K_AND_R;

import mantenimiento.codecounter.exceptions.InvalidFormatException;

/**
 * Clase que valida si se cumple el formato de uso de llaves
 * K&R
 */
public class StyleKAndRValidator extends FormatValidator {

    private static final String TYPE_DECLARION_REGEX = "^" + ACCESS_MODIFIERS_REGEX + "?((abstract|final)\\s*)?"
            + STRUCT_DECLARATION_REGEX + "\\w*\\b.*";

    private static final String METHOD_DECLARATION_REGEX = "^" + ACCESS_MODIFIERS_REGEX + FINAL_OR_STATIC_REGEX
            + DATATYPE_DECLARATION_REGEX + IDENTIFIER_DECLARATION_REGEX + PARAMETERS_DECLARATION_REGEX + ".*";

    private static final String FLOW_CONTROL_DECLARATION_REGEX = "^\\s*(if|for|while|switch|do|}\\s*else(\\s+if)?)\\s*\\(.*\\)";

    private static final String TRY_CATCH_DECLARATION_REGEX = "^(" + TRY_DECLARATION_REGEX + "|"
            + CATCH_DECLARATION_REGEX + "|" + FINALLY_DECLARATION_REGEX + ")";

    private static final String FINAL_DECLARATION_REGEX = "^((" + DATATYPE_DECLARATION_REGEX
            + IDENTIFIER_DECLARATION_REGEX + ",?\\s*)+|(" + BOOLEANS_OPERATORS + ".*)+.*)\\).*";

    @Override
    public boolean isValid(String lineOfFile) throws InvalidFormatException {
        if (isDeclaration(lineOfFile)) {
            return validateDeclaration(lineOfFile);
        }

        if (isOpeningBracket(lineOfFile) || isInvalidClosingBracket(lineOfFile)) {
            throw new InvalidFormatException(INVALID_STYLE_K_AND_R, lineOfFile);
        }

        return validateNext(lineOfFile);
    }

    /**
     * Verifica si una línea de código es una declaración válida (tipo, método,
     * control de flujo, try-catch o final).
     *
     * @param lineOfFile Línea de código a evaluar.
     * @return {@code true} si es una declaración válida, {@code false} en caso
     *         contrario.
     */
    private boolean isDeclaration(String lineOfFile) {
        return isTypeDeclaration(lineOfFile) || isMethodDeclaration(lineOfFile) ||
                isFlowControlDeclaration(lineOfFile) || isTryCatchDeclaration(lineOfFile) ||
                isFinalDeclaration(lineOfFile);
    }

    /**
     * Valida si una declaración sigue el formato de K&R.
     *
     * @param lineOfFile Línea de código a evaluar.
     * @return {@code true} si la declaración es válida.
     * @throws InvalidFormatException Si la declaración no sigue el formato de K&R.
     */
    private boolean validateDeclaration(String lineOfFile) throws InvalidFormatException {
        if (endsWithOpeningBracket(lineOfFile)) {
            return true;
        }
        throw new InvalidFormatException(INVALID_STYLE_K_AND_R, lineOfFile);
    }

    /**
     * Verifica si una línea contiene una llave de cierre pero no es válida según el
     * formato de K&R.
     *
     * @param lineOfFile Línea de código a evaluar.
     * @return {@code true} si la línea contiene una llave de cierre inválida,
     *         {@code false} en caso contrario.
     */
    private boolean isInvalidClosingBracket(String lineOfFile) {
        return isClosingBracket(lineOfFile) && lineOfFile.trim().length() > 1
                && (!lineOfFile.contains("while") && !lineOfFile.contains(";"));
    }

    /**
     * Determina si una línea de código comienza con una llave de apertura '{'.
     *
     * @param lineOfCode Línea de código a evaluar.
     * @return {@code true} si la línea comienza con '{', {@code false} en caso
     *         contrario.
     */
    private boolean isOpeningBracket(String lineOfCode) {
        return lineOfCode.trim().startsWith("{");
    }

    /**
     * Determina si una línea de código contiene una llave de cierre '}' en
     * cualquier parte.
     *
     * @param lineOfCode Línea de código a evaluar.
     * @return {@code true} si la línea contiene '}', {@code false} en caso
     *         contrario.
     */
    private boolean isClosingBracket(String lineOfCode) {
        return lineOfCode.trim().matches(".*}.*");
    }

    /**
     * Verifica si una línea de código termina con una llave de apertura '{'
     * respetando el formato de K&R.
     *
     * @param lineOfCode Línea de código a evaluar.
     * @return {@code true} si la línea finaliza con '{', {@code false} en caso
     *         contrario.
     */
    private boolean endsWithOpeningBracket(String lineOfCode) {
        return lineOfCode.trim().matches("^[^{]*\\{.*");
    }

    /**
     * Determina si una línea de código es una declaración de tipo.
     *
     * @param lineOfCode Línea de código a evaluar.
     * @return {@code true} si la línea coincide con el patrón de declaración de
     *         tipo, {@code false} en caso contrario.
     */
    private boolean isTypeDeclaration(String lineOfCode) {
        return lineOfCode.trim().matches(TYPE_DECLARION_REGEX);
    }

    /**
     * Determina si una línea de código es una declaración de método.
     *
     * @param lineOfCode Línea de código a evaluar.
     * @return {@code true} si la línea coincide con el patrón de declaración de
     *         método, {@code false} en caso contrario.
     */
    private boolean isMethodDeclaration(String lineOfCode) {
        return lineOfCode.trim().matches(METHOD_DECLARATION_REGEX);
    }

    /**
     * Determina si una línea de código es una declaración de control de flujo.
     *
     * @param lineOfCode Línea de código a evaluar.
     * @return {@code true} si la línea coincide con el patrón de declaración de
     *         control de flujo, {@code false} en caso contrario.
     */
    private boolean isFlowControlDeclaration(String lineOfCode) {
        return lineOfCode.trim().matches(FLOW_CONTROL_DECLARATION_REGEX);
    }

    /**
     * Determina si una línea de código es una declaración de un bloque try-catch.
     *
     * @param lineOfCode Línea de código a evaluar.
     * @return {@code true} si la línea coincide con el patrón de declaración de
     *         try-catch, {@code false} en caso contrario.
     */
    private boolean isTryCatchDeclaration(String lineOfCode) {
        return lineOfCode.trim().matches(TRY_CATCH_DECLARATION_REGEX);
    }

    /**
     * 
     * Valida si la linea es el termino de una declaracion de una estructura de
     * control
     * o un método en donde ha habido un salto de línea
     * 
     * @param lineOfCode linea de codigo por analizar
     * @return {@code true} si coincide con el final de la declaracion
     */
    private boolean isFinalDeclaration(String lineOfCode) {
        return lineOfCode.trim().matches(FINAL_DECLARATION_REGEX);
    }
}
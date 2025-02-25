package mantenimiento.codecounter.validators.formatValidators;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import mantenimiento.codecounter.exceptions.InvalidFormatException;

public class StyleKAndRValidatorTest {

    private final StyleKAndRValidator validator = new StyleKAndRValidator();

    @Test
    @DisplayName("Debe de retornar true si hay una declaracion de tipo y termina con {")
    void testValidTypeDeclaration() throws InvalidFormatException {
        String classLine = "public class test {";
        String interfaceLine = "pubic interface formatValidator{";
        String enumLine = "pubic enum enumerable{";
        String typeDeclaration = "public abstract class extends object implements interface {";

        assertTrue(validator.isValid(classLine));
        assertTrue(validator.isValid(interfaceLine));
        assertTrue(validator.isValid(enumLine));
        assertTrue(validator.isValid(typeDeclaration));

    }

    @Test
    @DisplayName("Debe de lanzar la excepcion InvalidFormException si hay una declaracion de tipo y no termina con {")
    void testInvalidTypeDeclaration() throws InvalidFormatException {
        String classLine = "public class test ";
        String interfaceLine = "public interface formatValidator";
        String enumLine = "public enum enumerable";
        String typeDeclaration = "public abstract class extends object implements interface ";

        assertThrows(InvalidFormatException.class, () -> validator.isValid(classLine));
        assertThrows(InvalidFormatException.class, () -> validator.isValid(interfaceLine));
        assertThrows(InvalidFormatException.class, () -> validator.isValid(enumLine));
        assertThrows(InvalidFormatException.class, () -> validator.isValid(typeDeclaration));
    }

    @Test
    @DisplayName("Debe de retornar true si hay una declaracion de método y termina con {")
    void testValidMethodDeclaration() throws InvalidFormatException {
        String methodDeclaration = "public void method(String param1, String param2) {";
        String methodDeclaration2 = "public static final method(String param1, String param2) {";
        assertTrue(validator.isValid(methodDeclaration));
        assertTrue(validator.isValid(methodDeclaration2));
    }

    @Test
    @DisplayName("Debe de lanzar la excepcion InvalidFormException si hay una declaracion de método y no termina con {")
    void testInvalidMethodDeclaration() throws InvalidFormatException {
        String methodDeclaration = "public void method(String param1, String param2) ";
        String methodDeclaration2 = "public static final method(String param1, String param2) ";
        assertThrows(InvalidFormatException.class, () -> validator.isValid(methodDeclaration));
        assertThrows(InvalidFormatException.class, () -> validator.isValid(methodDeclaration2));
    }

    @Test
    @DisplayName("Debe de retornar true si hay una declaracion de estructura de control y termina con {")
    void testValidFlowControlDeclaration() throws InvalidFormatException {
        String ifDeclaration = "if (x > 10) {";
        String elseIfDeclaration = "} else if (x > 10) {";
        String elseDeclaration = "} else {";
        String whileDeclaration = "while () {";
        String switchDeclaration = "switch () {";

        assertTrue(validator.isValid(ifDeclaration));
        assertTrue(validator.isValid(elseIfDeclaration));
        assertTrue(validator.isValid(elseDeclaration));
        assertTrue(validator.isValid(whileDeclaration));
        assertTrue(validator.isValid(switchDeclaration));
    }

    @Test
    @DisplayName("Debe de lanzar la excepcion InvalidFormException si hay una declaracion de estructura de control y no termina con {")
    void testInvalidFlowControlDeclaration() throws InvalidFormatException {
        String ifDeclaration = "if (x > 10) ";
        String elseIfDeclaration = "} else if (x > 10) ";
        String elseDeclaration = "} else ";
        String whileDeclaration = "while () ";
        String switchDeclaration = "switch () ";

        assertThrows(InvalidFormatException.class, () -> validator.isValid(ifDeclaration));
        assertThrows(InvalidFormatException.class, () -> validator.isValid(elseIfDeclaration));
        assertThrows(InvalidFormatException.class, () -> validator.isValid(elseDeclaration));
        assertThrows(InvalidFormatException.class, () -> validator.isValid(whileDeclaration));
        assertThrows(InvalidFormatException.class, () -> validator.isValid(switchDeclaration));
    }

    @Test
    @DisplayName("Debe de retornar true si hay una declaracion de try-catch y termina con {")
    void testValidTryCatchDeclaration() throws InvalidFormatException {
        String tryDeclaration = "try {";
        String tryWithResourcesDeclaration = "try (with resources) {";
        String catchDeclaration = "} catch (Exception e) {";
        String finallyDeclaration = "} finally {";

        assertTrue(validator.isValid(tryDeclaration));
        assertTrue(validator.isValid(tryWithResourcesDeclaration));
        assertTrue(validator.isValid(catchDeclaration));
        assertTrue(validator.isValid(catchDeclaration));
        assertTrue(validator.isValid(finallyDeclaration));

    }

    @Test
    @DisplayName("Debe de lanzar la excepcion InvalidFormException si hay una declaracion try-catch y no termina con {")
    void testInvalidTryCatchDeclaration() throws InvalidFormatException {
        String tryDeclaration = "try";
        String tryWithResourcesDeclaration = "try (with resources) ";
        String catchDeclaration = "} catch (Exception e) ";
        String finallyDeclaration = "} finally ";

        assertThrows(InvalidFormatException.class, () -> validator.isValid(tryWithResourcesDeclaration));
        assertThrows(InvalidFormatException.class, () -> validator.isValid(catchDeclaration));
        assertThrows(InvalidFormatException.class, () -> validator.isValid(catchDeclaration));
        assertThrows(InvalidFormatException.class, () -> validator.isValid(finallyDeclaration));
    }

    @Test
    @DisplayName("Debe de lanzar la excepcion InvalidFormException si las llaves no están correctas")
    void testInvalidBrackedPosition() throws InvalidFormatException {
        String openingBracket = "{";
        String closingBracket = "} if";
        String closingBracketWithWhile = "} while ();";

        assertThrows(InvalidFormatException.class, () -> validator.isValid(openingBracket));
        assertThrows(InvalidFormatException.class, () -> validator.isValid(closingBracket));
        assertTrue(validator.isValid(closingBracketWithWhile));
    }

}

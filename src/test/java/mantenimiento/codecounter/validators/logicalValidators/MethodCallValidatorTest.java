package mantenimiento.codecounter.validators.logicalValidators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MethodCallValidatorTest {

    private final MethodCallValidator validator = new MethodCallValidator();

    @Test
    @DisplayName("Debe de retornar true si la llamada de método termina correctamente, );")
    void isCompleteMethodCall() {
        List<String> linesOfCode = Arrays.asList("object.method(param1, param2);",
                "object.object.method(param1).method(param2);", "method(param1, param2);");

        assertTrue(validator.isValid(linesOfCode));
        assertTrue(validator.isValid(linesOfCode.subList(1, linesOfCode.size())));
        assertTrue(validator.isValid(linesOfCode.subList(2, linesOfCode.size())));
    }
    @Test
    @DisplayName("Debe de retornar false si la llamada de método no termina correctamente, );")
    void isIncompleteMethodCall() {
        List<String> linesOfCode = Arrays.asList("object.method(param1, param2)",
                "object.object.method(param1).method(param2)", "method(param1, param2)");

        assertFalse(validator.isValid(linesOfCode));
        assertFalse(validator.isValid(linesOfCode.subList(1, linesOfCode.size())));
        assertFalse(validator.isValid(linesOfCode.subList(2, linesOfCode.size())));
    }

    @Test
    @DisplayName("Debe de retornar true si la llamada de método, con salto de linea, termina correctamente, );")
    void isCompleteWrappedMethodCall() {
        List<String> linesOfCode = Arrays.asList("object.method(param1", "param2).method(param1);");

        assertTrue(validator.isValid(linesOfCode));
    }
    @Test
    @DisplayName("Debe de retornar false si la llamada de método, con salto de linea, no termina correctamente, );")
    void isIncompleteWrappedMethodCall() {
        List<String> linesOfCode = Arrays.asList("object.method(param1", "param2).method(param1)");

        assertFalse(validator.isValid(linesOfCode));
    }
}

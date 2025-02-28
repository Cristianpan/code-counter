package mantenimiento.codecounter.validators.logicalValidators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FlowControlKeywordValidatorTest {
    private final FlowControlKeywordValidator validator = new FlowControlKeywordValidator();

    @Test
    @DisplayName("Debe de retornar true si detecta palabras de control de flujo con terminación correcta")
    void isCompleteFlowControlKeywordSentence() {
        List<String> linesOfCode = new ArrayList<>(List.of("break;", "continue;", "case 1: case 2:", "throw Exception;", "return valor;",
                "throw new Exception(\"hola\");"));

        assertTrue(validator.isValid(linesOfCode));
        assertTrue(validator.isValid(linesOfCode));
        assertTrue(validator.isValid(linesOfCode));
        assertTrue(validator.isValid(linesOfCode));
        assertTrue(validator.isValid(linesOfCode));
        assertTrue(validator.isValid(linesOfCode));

    }

    @Test
    @DisplayName("Debe de retornar false si detecta palabras de control de flujo con terminación incorrecta")
    void isIncompleteFlowControlKeywordSentence() {
        List<String> linesOfCode = new ArrayList<>(List.of("break", "continue", "case 1", "throw Exception", "return valor",
                "throw new Exception(\"hola\")"));

        assertFalse(validator.isValid(linesOfCode));
        assertFalse(validator.isValid(linesOfCode));
        assertFalse(validator.isValid(linesOfCode));
        assertFalse(validator.isValid(linesOfCode));
        assertFalse(validator.isValid(linesOfCode));
        assertFalse(validator.isValid(linesOfCode));
    }

    @Test
    @DisplayName("Debe de retornar true si detecta return o throw con salto de linea y terminación correcta")
    void isCompleteWrappedFlowControlKeywordSentence() {
        List<String> linesOfCode = new ArrayList<>(List.of("return valor +", "valor;",
                "throw new Exception(\"message\" +" + "\"message\");"));

        assertTrue(validator.isValid(linesOfCode));
        assertTrue(validator.isValid(linesOfCode));
    }
    @Test
    @DisplayName("Debe de retornar false si detecta return o throw con salto de linea y terminación incorrecta")
    void isIncompleteWrappedFlowControlKeywordSentence() {
        List<String> linesOfCode = new ArrayList<>(List.of("return valor +", "valor",
                "throw new Exception(\"message\" +" + "\"message\")"));

        assertFalse(validator.isValid(linesOfCode));
        assertFalse(validator.isValid(linesOfCode));
    }
}

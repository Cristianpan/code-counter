package mantenimiento.codecounter.validators.logicalValidators;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FlowControlKeywordValidatorTest {
    private final FlowControlKeywordValidator validator = new FlowControlKeywordValidator();

    @Test
    @DisplayName("Debe de retornar true si detecta palabras de control de flujo con terminación correcta")
    void isCompleteFlowControlKeywordSentence() {
        List<String> linesOfCode = Arrays.asList("break;", "continue;", "return;", "throw Exception;", "return valor;",
                "throw new Exception(\"hola\");");

        System.out.println(linesOfCode.subList(5, linesOfCode.size()));
        assertTrue(validator.isValid(linesOfCode));
        assertTrue(validator.isValid(linesOfCode.subList(1, linesOfCode.size())));
        assertTrue(validator.isValid(linesOfCode.subList(2, linesOfCode.size())));
        assertTrue(validator.isValid(linesOfCode.subList(3, linesOfCode.size())));
        assertTrue(validator.isValid(linesOfCode.subList(4, linesOfCode.size())));
        assertTrue(validator.isValid(linesOfCode.subList(5, linesOfCode.size())));

    }

    @Test
    @DisplayName("Debe de retornar false si detecta palabras de control de flujo con terminación incorrecta")
    void isIncompleteFlowControlKeywordSentence() {
        List<String> linesOfCode = Arrays.asList("break", "continue", "return", "throw Exception", "return valor",
                "throw new Exception(\"hola\")");

        System.out.println(linesOfCode.subList(5, linesOfCode.size()));
        assertFalse(validator.isValid(linesOfCode));
        assertFalse(validator.isValid(linesOfCode.subList(1, linesOfCode.size())));
        assertFalse(validator.isValid(linesOfCode.subList(2, linesOfCode.size())));
        assertFalse(validator.isValid(linesOfCode.subList(3, linesOfCode.size())));
        assertFalse(validator.isValid(linesOfCode.subList(4, linesOfCode.size())));
        assertFalse(validator.isValid(linesOfCode.subList(5, linesOfCode.size())));
    }

    @Test
    @DisplayName("Debe de retornar true si detecta return o throw con salto de linea y terminación correcta")
    void isCompleteWrappedFlowControlKeywordSentence() {
        List<String> linesOfCode = Arrays.asList("return valor +", "valor;",
                "throw new Exception(\"message\" +" + "\"message\");");

        assertTrue(validator.isValid(linesOfCode));
        assertTrue(validator.isValid(linesOfCode.subList(2, linesOfCode.size())));
    }
    @Test
    @DisplayName("Debe de retornar false si detecta return o throw con salto de linea y terminación incorrecta")
    void isIncompleteWrappedFlowControlKeywordSentence() {
        List<String> linesOfCode = Arrays.asList("return valor +", "valor",
                "throw new Exception(\"message\" +" + "\"message\")");

        assertFalse(validator.isValid(linesOfCode));
        assertFalse(validator.isValid(linesOfCode.subList(2, linesOfCode.size())));
    }
}

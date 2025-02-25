package mantenimiento.codecounter.validators.logicalValidators;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.List;

public class ControlStructureValidatorTest {

    private final LogicalValidator validator = new ControlStructureValidator();

    @Test
    @DisplayName("Debe retornar true al detectar estructuras de control como if, else if, while, do-while, for, for-each y case válidas")
    void testValidControlStructures() {

        //Listas que contienen estructuras válidas en el estándar
        List<String> linesIf = Arrays.asList("if (x > 10) { }");
        List<String> incompleteLinesIf = Arrays.asList("if (x > 10) { ");

        List<String> linesElseIf = Arrays.asList("else if (x == 1) { }");
        List<String> incompleteLinesElseIf = Arrays.asList("else if (x == 1)");

        List<String> linesWhile = Arrays.asList("while (x > 10) { }");
        List<String> linesDoWhile = Arrays.asList("do { } while (x > 10);");
        List<String> linesFor = Arrays.asList("for (int i = 0; i < 10; i++) { }");
        List<String> linesForEach = Arrays.asList("for (int x : arr) { }");
        List<String> linesCase = Arrays.asList("case 1: { }", "case 1:");

        
        assertTrue(validator.isValid(linesIf));
        assertTrue(validator.isValid(incompleteLinesIf));

        assertTrue(validator.isValid(linesElseIf));
        assertTrue(validator.isValid(incompleteLinesElseIf));

        assertTrue(validator.isValid(linesWhile));
        assertTrue(validator.isValid(linesDoWhile));
        assertTrue(validator.isValid(linesFor));
        assertTrue(validator.isValid(linesForEach));
        assertTrue(validator.isValid(linesCase));
    }

    @Test
    @DisplayName("Debe retornar false al no detectar estructuras de control válidas como if, else if, while, do-while, for, for-each y case incorrectas")
    void testInvalidControlStructures() {
        // Listas que contienen estructuras incompletas (sin paréntesis)/sin el formato establecido en el estándar
        List<String> linesIf = Arrays.asList("if x > 10");
        List<String> linesElseIf = Arrays.asList("else if x == 1");
        List<String> linesWhile = Arrays.asList("while x > 10");
        List<String> linesDoWhile = Arrays.asList("do { } while x > 10");
        List<String> linesFor = Arrays.asList("for int i = 0; i < 10; i++");
        List<String> linesForEach = Arrays.asList("for x : arr");
        List<String> linesCase = Arrays.asList("case x");

        assertFalse(validator.isValid(linesIf));
        assertFalse(validator.isValid(linesElseIf));
        assertFalse(validator.isValid(linesWhile));
        assertFalse(validator.isValid(linesDoWhile));
        assertFalse(validator.isValid(linesFor));
        assertFalse(validator.isValid(linesForEach));
        assertFalse(validator.isValid(linesCase));
    }

    //Considerando que en el estándar la sentencia switch no es LOC lógica
    @Test
    @DisplayName("Debe retornar false al detectar una sentencia switch")
    void testSwitchStatement() {
    List<String> linesSwitch = Arrays.asList("switch (x) {");
    assertFalse(validator.isValid(linesSwitch));
}

}

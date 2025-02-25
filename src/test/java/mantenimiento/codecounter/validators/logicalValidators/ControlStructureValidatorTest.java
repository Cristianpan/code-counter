package mantenimiento.codecounter.validators.logicalValidators;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.List;

public class ControlStructureValidatorTest {

    private final LogicalValidator validator = new ControlStructureValidator();

    @Test
    @DisplayName("Debe retornar true al detectar estructuras de control como if, else if, while, for")
    void testValidControlStructures() {
        List<String> linesIf = Arrays.asList("if (x > 10) { ");
        List<String> incompleteLinesIf = Arrays.asList("if (x > 10) { ");

        List<String> linesElseIf = Arrays.asList("} else if (x == 1) {");

        List<String> linesWhile = Arrays.asList("while (x > 10) {");
        List<String> linesDoWhile = Arrays.asList("} while (x > 10);");
        List<String> linesFor = Arrays.asList("for (int i = 0; i < 10; i++) { ");
        List<String> linesForEach = Arrays.asList("for (int x : arr) { ");

        assertTrue(validator.isValid(linesIf));
        assertTrue(validator.isValid(incompleteLinesIf));
        assertTrue(validator.isValid(linesElseIf));
        assertTrue(validator.isValid(linesWhile));
        assertTrue(validator.isValid(linesDoWhile));
        assertTrue(validator.isValid(linesFor));
        assertTrue(validator.isValid(linesForEach));
    }
    @Test
    @DisplayName("Debe retornar true al detectar estructuras de control como if, else if, while, for con saltos de línea")
    void testValidControlStructuresWithWrapped() {
        List<String> linesOfCode = Arrays.asList("if (x > 10 || x > 30", "|| x > 100) {");

        assertTrue(validator.isValid(linesOfCode));
        
    }

    @Test
    @DisplayName("Debe retornar false al no detectar estructuras de control válidas como if, else if, while, for, for-each")
    void testInvalidControlStructures() {
        List<String> linesIf = Arrays.asList("if (x > 10");
        List<String> linesElseIf = Arrays.asList("else if (x == 1");
        List<String> linesWhile = Arrays.asList("while x > 10");
        List<String> linesDoWhile = Arrays.asList("} while x > 10");
        List<String> linesFor = Arrays.asList("for (int i = 0; i < 10; i++");
        List<String> linesForEach = Arrays.asList("for (x : arr");

        assertFalse(validator.isValid(linesIf));
        assertFalse(validator.isValid(linesElseIf));
        assertFalse(validator.isValid(linesWhile));
        assertFalse(validator.isValid(linesDoWhile));
        assertFalse(validator.isValid(linesFor));
        assertFalse(validator.isValid(linesForEach));
    }
    

    // Considerando que en el estándar la sentencia switch no es LOC lógica
    @Test
    @DisplayName("Debe retornar false al detectar una sentencia switch")
    void testSwitchStatement() {
        List<String> linesSwitch = Arrays.asList("switch (x) {");
        assertFalse(validator.isValid(linesSwitch));
    }

}

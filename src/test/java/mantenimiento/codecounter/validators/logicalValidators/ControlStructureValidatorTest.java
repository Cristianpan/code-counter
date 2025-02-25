package mantenimiento.codecounter.validators.logicalValidators;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

public class ControlStructureValidatorTest {

    private final LogicalValidator validator = new ControlStructureValidator();

    @Test
    @DisplayName("Debe retornar true al detectar estructuras de control como if, else if, while, for")
    void testValidControlStructures() {
        List<String> linesIf = new ArrayList<>(List.of("if (x > 10) { "));
        List<String> incompleteLinesIf = new ArrayList<>(List.of("if (x > 10) { "));

        List<String> linesElseIf = new ArrayList<>(List.of("} else if (x == 1) {"));

        List<String> linesWhile = new ArrayList<>(List.of("while (x > 10) {"));
        List<String> linesDoWhile = new ArrayList<>(List.of("} while (x > 10);"));
        List<String> linesFor = new ArrayList<>(List.of("for (int i = 0; i < 10; i++) { "));
        List<String> linesForEach = new ArrayList<>(List.of("for (int x : arr) { "));

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
        List<String> linesOfCode = new ArrayList<>(List.of("if (x > 10 || x > 30", "|| x > 100) {"));

        assertTrue(validator.isValid(linesOfCode));
        
    }

    @Test
    @DisplayName("Debe retornar false al no detectar estructuras de control válidas como if, else if, while, for, for-each")
    void testInvalidControlStructures() {
        List<String> linesIf = new ArrayList<>(List.of("if (x > 10"));
        List<String> linesElseIf = new ArrayList<>(List.of("else if (x == 1"));
        List<String> linesWhile = new ArrayList<>(List.of("while x > 10"));
        List<String> linesDoWhile = new ArrayList<>(List.of("} while x > 10"));
        List<String> linesFor = new ArrayList<>(List.of("for (int i = 0; i < 10; i++"));
        List<String> linesForEach = new ArrayList<>(List.of("for (x : arr"));

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
        List<String> linesSwitch = new ArrayList<>(List.of("switch (x) {"));
        assertFalse(validator.isValid(linesSwitch));
    }

}

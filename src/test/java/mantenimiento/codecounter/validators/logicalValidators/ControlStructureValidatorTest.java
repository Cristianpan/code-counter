package mantenimiento.codecounter.validators.logicalValidators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import mantenimiento.codecounter.interfaces.LogicalValidatorHandler;

public class ControlStructureValidatorTest {

    private final LogicalValidatorHandler validator = new ControlStructureValidator();

    @Test
    @DisplayName("Debe retornar true al detectar estructuras de control como if, for, while, switch")
    void containsControlStructures() {
        List<String> lines = Arrays.asList("if (x > 0) {", "for (int i = 0; i < 10; i++) {", "while (x < 10) {", "switch (x) {");
        
        assertTrue(validator.isValid(lines));
        assertTrue(validator.isValid(lines.subList(1, lines.size())));
        assertTrue(validator.isValid(lines.subList(2, lines.size())));
    }

    @Test
    @DisplayName("Debe retornar false si no detecta estructuras de control")
    void doesNotContainControlStructures() {
        List<String> lines = Arrays.asList("int x = 5;", "String value = \"hello\";", "System.out.println(value);");
        
        assertFalse(validator.isValid(lines));
    }
}


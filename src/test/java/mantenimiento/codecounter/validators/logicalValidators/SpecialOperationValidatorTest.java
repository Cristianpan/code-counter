package mantenimiento.codecounter.validators.logicalValidators;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class SpecialOperationValidatorTest {

    private final SpecialOperationValidator validator = new SpecialOperationValidator(); 

    @Test
    @DisplayName("Debe de retornar true si detecta operaciones de incremento o decremento")
    void testIsValidWithSpecialIncrementOrDecrementOperation() {
        List<String> linesOfCode = new ArrayList<>();
        linesOfCode.add("i++;");
        linesOfCode.add("i--;");
        linesOfCode.add("++i;");
        linesOfCode.add("++i;");
        
        assertTrue(validator.isValid(linesOfCode));
        assertTrue(validator.isValid(linesOfCode));
        assertTrue(validator.isValid(linesOfCode));
        assertTrue(validator.isValid(linesOfCode));
    }

    @Test
    @DisplayName("Debe de retornar true si detecta operaciones especiales de asignación")
    void testIsValidWithSpecialDecrementOperation() {
        List<String> linesOfCode = new ArrayList<>();
        linesOfCode.add("j += 1;");
        linesOfCode.add("j *= 1;");
        linesOfCode.add("j -= 1;");
        linesOfCode.add("j /= 1;");
        linesOfCode.add("j ^= 1;");
        
        assertTrue(validator.isValid(linesOfCode));
    }

    @Test
    @DisplayName("Debe retornar false si no detecta algun tipo de operación especial")
    void testIsValidWithSpecialAssignmentOperation() {
        List<String> linesOfCode = new ArrayList<>();
        linesOfCode.add("x = 5;");
        linesOfCode.add("x = 5 + y + method();");
        
        assertFalse(validator.isValid(linesOfCode));
        assertFalse(validator.isValid(linesOfCode));
    }

}
package mantenimiento.codecounter.validators.logicalValidators;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TernaryOperationDeclaration {

    private final TernaryDeclarationValidator validator = new TernaryDeclarationValidator();

    @Test
    @DisplayName("Debe de retornar true si corresponde a la declaracion de una operacion ternaria")
    public void testIsTernaryOperationDeclaration() {
        List<String> linesOfCode = List.of(
                "int a = 5 > 3 ? 5 : 3;",
                "return 5 > 3 ? 5 : 3;");
        
        for (String lineOfCode : linesOfCode) {
            assertTrue(validator.isValid(lineOfCode));
        }
    }
}

package mantenimiento.codecounter.validators.logicalValidators;

import static org.junit.jupiter.api.Assertions.assertFalse;
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
                "return 5 > 3 ? 5 : 3;",
                "return 5 > 3 ? 5 : 5 > 4 ? 5: 4;");

        for (String lineOfCode : linesOfCode) {
            assertTrue(validator.isValid(lineOfCode));
        }
    }

    @Test
    @DisplayName("Debe de retornar false si no corresponde a la declaracion valida de una operacion ternaria")
    public void testIsNotValidTernaryOperationDeclaration() {
        List<String> linesOfCode = List.of(
                "(edad >= 18) ? \"Mayor\":\"Menor\";");

        for (String lineOfCode : linesOfCode) {
            assertFalse(validator.isValid(lineOfCode));
        }
    }
}

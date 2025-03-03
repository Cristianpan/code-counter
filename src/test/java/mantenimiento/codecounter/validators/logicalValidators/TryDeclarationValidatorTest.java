package mantenimiento.codecounter.validators.logicalValidators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

public class TryDeclarationValidatorTest {

    private final TryDeclarationValidator validator = new TryDeclarationValidator();

    @Test
    @DisplayName("Debe de retornar true al detectar sentencias try")
    void testIsTryDeclaration() {
        List<String> tryDeclarations = List.of(
                "try (Stream<Path> stream = Files.walk(path)) {",
                "try {");

        for (String tryDeclaration : tryDeclarations) {
            assertTrue(validator.isValid(tryDeclaration));
        }
    }

    @Test
    @DisplayName("Debe de retornar false al detectar sentencias que no son try")
    void testIsNotTryDeclaration() {
        List<String> notTryDeclarations = List.of(
                "catch (Exception e) {",
                "finally {",
                "tryDoSomething();");

        for (String notTryDeclartion : notTryDeclarations) {
            assertFalse(validator.isValid(notTryDeclartion));
        }
    }
}
package mantenimiento.codecounter.validators.logicalValidators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TryCatchDeclarationValidatorTest {

    private final TryCatchDeclarationValidator validator = new TryCatchDeclarationValidator();

    @Test
    @DisplayName("Debe de retornar true al detectar un try-with-resources con estructura correcta")
    void isTryWithResourcesAndCompleteStructure() {
        List<String> linesOfCode = new ArrayList<>(List.of("    try (Stream<Path> stream = Files.walk(path)) {"));

        assertTrue(validator.isValid(linesOfCode));
    }

    @Test
    @DisplayName("Debe de retornar true al detectar un try-with-resources con salto de línea y estructura correcta")
    void isWrappedTryWithResourcesAndIncompleteStructure() {
        List<String> linesOfCode = new ArrayList<>(List.of("try (Stream<Path> stream = ", "Files.walk(path)) "));

        assertTrue(validator.isValid(linesOfCode));
    }

    @Test
    @DisplayName("Debe de retornar false si detecta un try-with-resources con estructura incompleta")
    void isTryWithResourcesAndIncompleteStructure() {
        List<String> linesOfCode = new ArrayList<>(List.of("try (Stream<Path> stream = ", "some"));

        assertFalse(validator.isValid(linesOfCode));
    }

    @Test
    @DisplayName("Debe de retornar true al detectar un catch con estructura correcta")
    void isCatchAndCompleteStructure() {
        List<String> linesOfCode = new ArrayList<>(List.of("} catch (Exception e) {"));

        assertTrue(validator.isValid(linesOfCode));
    }

    @Test
    @DisplayName("Debe de retornar false si detectar un catch con estructura incorrecta")
    void isCatchAndIncompleteStructure() {
        List<String> linesOfCode = new ArrayList<>(List.of("} catch (Exception e "));

        assertFalse(validator.isValid(linesOfCode));
    }
}
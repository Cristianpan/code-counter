package mantenimiento.codecounter.validators.logicalValidators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AssignmentValidatorTest {

    private final LogicalValidator validator = new AssignmentValidator();

    @Test
    @DisplayName("Debe de retornar true al detectar una asignacion que termine con ;")
    void isAssignmentWithSemicolon() {
        List<String> lines = new ArrayList<>(
                List.of("Path path = Paths.get(folderPath);", "formatValidator = hola;",
                        "public static final string value = \"value\";"));

        assertTrue(validator.isValid(lines));
        assertTrue(validator.isValid(lines));
        assertTrue(validator.isValid(lines));
    }

    @Test
    @DisplayName("Debe de retornar false en caso de detectar una asignacion que no termine con ;")
    void isAssignmentWithoutSemicolon() {
        List<String> lines = new ArrayList<>(
                List.of("x = 5", "String value = \"value\"", "public string value = value"));

        assertFalse(validator.isValid(lines));
        assertFalse(validator.isValid(lines));
        assertFalse(validator.isValid(lines));
    }

    @Test
    @DisplayName("Debe de retornar true al detectar una asignacion con salto de línea, que termine con ;")
    void isWrappedAssignmentWithSemicolon() {
        List<String> lines = new ArrayList<>(
                List.of("        List<Path> javaFiles = stream.filter(Files::isRegularFile)", ".filter(isJavaFile())",
                        ".toList();"));

        assertTrue(validator.isValid(lines));
    }

    @Test
    @DisplayName("Debe de retornar false en caso de detectar una asignación con salto de línea que no termine con ;")
    void isWrappedAssignmentWithoutSemicolon() {
        List<String> lines = new ArrayList<>(List.of("value = \"value\" + \n", "\"value2\"",
                "public string value = \"value\" + \n", "\"value2\""));

        assertFalse(validator.isValid(lines));
        assertFalse(validator.isValid(lines));
    }

    @Test
    @DisplayName("Debe de retornar false si no detecta una asignacion")
    void isNotAssignment() {
        List<String> lines = new ArrayList<>(List.of("String value;"));

        assertFalse(validator.isValid(lines));
    }
}

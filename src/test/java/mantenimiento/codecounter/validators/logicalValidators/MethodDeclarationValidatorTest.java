package mantenimiento.codecounter.validators.logicalValidators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MethodDeclarationValidatorTest {
    private final LogicalValidator validator = new MethodDeclarationValidator();

    @Test
    @DisplayName("Debe de retornar true si la línea de código es una declaración de método")
    public void testIsMethodDeclaration() {
        List<String> methodDeclarations = List.of(
                "public static void main(String[] args) {",
                "public void doSomething() {",
                "public static List<String> doSomething(String arg1, int arg2) {",
                "List<String> doSomething(String arg1, int arg2) {");

        for (String methodDeclaration : methodDeclarations) {
            assertTrue(validator.isValid(methodDeclaration));
        }
    }

    @Test
    @DisplayName("Debe de retornar false si la línea de código no es una declaración de método")
    public void testIsNotMethodDeclaration() {
        List<String> methodDeclarations = List.of(
                "public static void main(String[] args);",
                "if (true) {",
                "doSomething();",
                "public static final ReasonInvalidFormat INVALID_STYLE_K_AND_R = new ReasonInvalidFormat(",
                "public abstract void doSomething();");

        for (String methodDeclaration : methodDeclarations) {
            assertFalse(validator.isValid(methodDeclaration));
        }
    }
}

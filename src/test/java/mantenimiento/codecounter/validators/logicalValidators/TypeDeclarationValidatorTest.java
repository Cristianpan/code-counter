package mantenimiento.codecounter.validators.logicalValidators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TypeDeclarationValidatorTest {
    private final TypeDeclarationValidator validator = new TypeDeclarationValidator();

    @Test
    @DisplayName("Debe de validar una declaración de tipo correctamente")
    void testIsTypeDeclaration() {
        List<String> typeDeclarations = List.of(
                "public class Test {",
                "public interface Test {",
                "public enum Test {");

        for (String typeDeclaration : typeDeclarations) {
            assertTrue(validator.isValid(typeDeclaration));
        }
    }
    
    @Test
    @DisplayName("Debe de validar una declaración de tipo correctamente con modificadores")
    void testIsTypeDeclarationWithModifiers() {
        List<String> typeDeclarations = List.of(
                "public final static class Test {",
                "public abstract interface Test {",
                "protected enum Test {");

        for (String typeDeclaration : typeDeclarations) {
            assertTrue(validator.isValid(typeDeclaration));
        }
    }

    @Test
    @DisplayName("Debe de validar que no corresponde a una declaración de tipo")
    void testIsNotTypeDeclaration() {
        List<String> notTypeDeclarations = List.of(
                "classTest",
                "publicKey",
                "interfaceTest",
                "enumTest");

        for (String typeDeclaration : notTypeDeclarations) {
            assertFalse(validator.isValid(typeDeclaration));
        }
    }

}

package mantenimiento.codecounter.validators.logicalValidators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeclarationWhitInitializationValidatorTest {

    private final LogicalValidator validator = new DeclarationWhitInitializationValidator();

    @Test
    @DisplayName("Debe de retornar true al detectar una declaracion con inicializacion que termine con ;")
    void isDeclarationWithInitializationWithSemicolon() {
        List<String> lines = Arrays.asList("int x = 5;", "String value = \"value\";",
                "public static final string value = \"value\";");

        assertTrue(validator.isValid(lines));
        assertTrue(validator.isValid(lines.subList(1, lines.size())));
        assertTrue(validator.isValid(lines.subList(2, lines.size())));
    }

    @Test
    @DisplayName("Debe de retornar false en caso de detectar una declaracion con inicializacion que no termine con ;")
    void isDeclarationWithInitializationWithoutSemicolon() {
        List<String> lines = Arrays.asList("int x = 5", "String value = \"value\"", "String value = \"\"");

        assertFalse(validator.isValid(lines));
        assertFalse(validator.isValid(lines.subList(1, lines.size())));
        assertFalse(validator.isValid(lines.subList(2, lines.size())));
    }

    @Test
    @DisplayName("Debe de retornar true al detectar una declaracion con inicializacion y salto de línea, que termine con ;")
    void isWrappedDeclarationWithInitializationWithSemicolon() {
        List<String> lines = Arrays.asList("String value = \"value\" + \n", "\"value2\";",
                "public string value = \"value\" + \n", "\"value2\";");

        assertTrue(validator.isValid(lines));
        assertTrue(validator.isValid(lines.subList(2, lines.size())));
    }
    
    @Test
    @DisplayName("Debe de retornar false en caso de detectar una declaracion con inicializacion y salto de línea que no termine con ;")
    void isWrappedDeclarationWithInitializationWithoutSemicolon() {
        List<String> lines = Arrays.asList("String value = \"value\" + \n", "\"value2\"",
        "public string value = \"value\" + \n", "\"value2\"");
        
        assertFalse(validator.isValid(lines));
        assertFalse(validator.isValid(lines.subList(2, lines.size())));
    }
    
    @Test
    @DisplayName("Debe de retornar false en caso de detectar una declaracion sin inicializacion")
    void isDeclarationWithoutInitialization(){
        List<String> lines = Arrays.asList("String value;");
        
        assertFalse(validator.isValid(lines));
    }
}

package mantenimiento.codecounter.validators.formatValidators;

import mantenimiento.codecounter.exceptions.InvalidFormatException;
import mantenimiento.codecounter.validators.formatValidators.SingleDeclarationValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


class SingleDeclarationValidatorTest {

    @Test
    void testValidSingleDeclaration() throws InvalidFormatException {
        SingleDeclarationValidator validator = new SingleDeclarationValidator();
        assertTrue(validator.isValid("int x = 10;"));
    }

    @Test
    @DisplayName("Debe de lanzar la excepion al detectar incialización de varias variables ")
    void testInvalidMultipleDeclarationsWithSemicolon() {
        SingleDeclarationValidator validator = new SingleDeclarationValidator();
        Exception exception = assertThrows(InvalidFormatException.class, () -> {
            validator.isValid("int value 1 = 10; double value2 = 12.8;");
        });
        assertEquals("El código no sigue el formato del estándar de codificación. Razón: Múltiples declaraciones por línea: int value 1 = 10; double value2 = 12.8;", exception.getMessage());
    }

    @Test
    @DisplayName("Debe de lanzar la excepion al detectar incialización de varias variables ")
    void testInvalidMultipleDeclarationsWithCommas() {
        SingleDeclarationValidator validator = new SingleDeclarationValidator();
        Exception exception = assertThrows(InvalidFormatException.class, () -> {
            validator.isValid("int value1, value2, value3;");
        });
        assertEquals("El código no sigue el formato del estándar de codificación. Razón: Múltiples declaraciones por línea: int value1, value2, value3;", exception.getMessage());
    }

}
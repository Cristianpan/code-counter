package mantenimiento.codecounter.validators.logicalValidators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LambdaDeclarationValidatorTest {
    private final LambdaDeclarationValidator validator = new LambdaDeclarationValidator();
    
    @Test
    @DisplayName("Debe de retornar true si la línea de código es una declaración de lambda")
    void testIsLambdaDeclaration() {
        List<String> linesOfCode = List.of(
            "return (a, b) -> a + b;", 
            "return fileName -> fileName.toString().endsWith(\".java\");",
            "Predicate<Integer> esPar = num -> num % 2 == 0;",
            "BiFunction<Integer, Integer, Integer> multiplicacion = (a, b) -> {"
            );

        for (String lineOfCode : linesOfCode) {
            assertTrue(validator.isValid(lineOfCode));
        }
    }

    @Test
    @DisplayName("Debe de retornar false si la línea de código no es una declaración de lambda")
    void testIsNotLambdaDeclaration(){
        List<String> linesOfCode = List.of(
            "int a = 5;",
            "return a + b;",
            "return fileName.toString().endsWith(\".java\");", 
            "\"return (a, b) -> a + b;\""

        );

        for (String lineOfCode : linesOfCode) {
            assertFalse(validator.isValid(lineOfCode));
        }
    }
}

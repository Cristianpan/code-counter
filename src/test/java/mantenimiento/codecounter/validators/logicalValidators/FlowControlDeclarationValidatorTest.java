package mantenimiento.codecounter.validators.logicalValidators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

public class FlowControlDeclarationValidatorTest {

	private final LogicalValidator validator = new FlowControlDeclarationValidator();

	@Test
	@DisplayName("Debe retornar true al detectar estructuras de control como lineas logicas")
	void testIsValidFlowControolDeclaration() {

		List<String> linesOfCode = List.of(
				"if (x > 10)",
				"while (x > 10)",
				"for (int i = 0; i < 10; i++)",
				"for (int x : arr)",
				"switch(x)");

		for (String lineOfCode : linesOfCode) {
			assertTrue(validator.isValid(lineOfCode));
		}
	}

	@Test
	@DisplayName("Debe retornar false al detectar estructuras de que no son lineas logicas")
	void testIsInvalidFlowControlDeclaration() {
		List<String> linesOfCode = List.of(
				"} else {",
				"do {",
				"case 1:",
				"ifSentence()",
				"} else if (x > 10)");

		for (String lineOfCode : linesOfCode) {
			assertFalse(validator.isValid(lineOfCode));
		}

	}
}

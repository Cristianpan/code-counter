package mantenimiento.codecounter.validators.logicalValidators;

import static mantenimiento.codecounter.constants.JavaRegextConstants.FLOW_CONTROL_KEYS;

/**
 * Clase que verifica la presencia de estructuras de control
 * como if, else if, while, do-while, for, for-each y case.
 */
public class FlowControlDeclarationValidator extends LogicalValidator {


    private static final String FLOW_CONTROL_DECLARATION = "^" + FLOW_CONTROL_KEYS + "\\([^)]*\\s*\\)?;?.*";

    /**
     * Verifica si la primera línea de código contiene una estructura de control.
     * 
     * @param linesOfCode Lista de líneas de código a analizar.
     * @return {@code true} si se encuentra una estructura de control, {@code false}
     *         en caso
     *         contrario.
     */
    @Override
    public boolean isValid(String lineOfCode) {
        return isFlowControlStructureDeclaration(lineOfCode) || validateNext(lineOfCode);
    }

    /**
     * Verifica si una línea de código es una declaración de estructura de control
     * exceptuando: else, else if, case, default.
     *
     * @param lineOfCode Línea de código a evaluar.
     * @return {@code true} si es una estructura de control lógica válida,
     *         {@code false} en caso contrario.
     */
    private boolean isFlowControlStructureDeclaration(String lineOfCode) {
        return lineOfCode.matches(FLOW_CONTROL_DECLARATION);
    }
}
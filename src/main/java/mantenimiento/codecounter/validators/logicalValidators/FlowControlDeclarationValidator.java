package mantenimiento.codecounter.validators.logicalValidators;

import static mantenimiento.codecounter.constants.JavaRegextConstants.FLOW_CONTROL_KEYS;

/**
 * Clase que verifica la presencia de estructuras de control
 * como if, while, do-while, for, for-each y switch.
 */
public class FlowControlDeclarationValidator extends LogicalValidator {

    private static final String FLOW_CONTROL_DECLARATION = "^" + FLOW_CONTROL_KEYS + "\\([^)]*\\s*\\)?;?.*";

    /**
     * Determina si una línea de código si corresponde a una declaración
     * de una estructura de control de flujo para considerarla como línea lógica
     * 
     * @param lineOfCode Lista de líneas de código a analizar.
     * @return {@code true} si se corresponde a la declaración de una estructura de
     *         control, {@code false} en caso contrario.
     */
    @Override
    public boolean isValid(String lineOfCode) {
        return isFlowControlStructureDeclaration(lineOfCode) || validateNext(lineOfCode);
    }

    /**
     * Verifica si una línea de código es una declaración de estructura de contro
     * logiva valida considerando solo declaracion de if, while, do-while, for,
     * for-each, switch
     *
     * @param lineOfCode Línea de código a evaluar.
     * @return {@code true} si es una estructura de control lógica válida,
     *         {@code false} en caso contrario.
     */
    private boolean isFlowControlStructureDeclaration(String lineOfCode) {
        return lineOfCode.matches(FLOW_CONTROL_DECLARATION);
    }
}
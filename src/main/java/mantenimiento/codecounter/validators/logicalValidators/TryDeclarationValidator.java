package mantenimiento.codecounter.validators.logicalValidators;

import static mantenimiento.codecounter.constants.JavaRegextConstants.TRY_DECLARATION;

/**
 * Clase que verifica si existe una declaración de un bloque try para poder
 * contabilizarlo como línea lógica
 */
public class TryDeclarationValidator extends LogicalValidator {
    
    /**
     * Determina si una línea de código corresponde a una declaración de un bloque
     * try para considerarla como línea lógica
     * 
     * @param linesOfCode
     * @return {@code true} si la corresponde a una declaración de un bloque try,
     *         {@code false} en caso contrario
     */
    @Override
    public boolean isValid(String linesOfCode) {
        return isTryDeclartion(linesOfCode) || this.validateNext(linesOfCode);
    }

    /**
     * Verifica si la sentencia es una declaración de un bloque try
     * 
     * @param lineOfCode sentencia de codigo por analizar
     * @return {@code true} si coincide con la declaracion, {@code false} en caso
     *         contrario
     */
    private boolean isTryDeclartion(String lineOfCode) {
        return lineOfCode.trim().matches(TRY_DECLARATION);
    }
}

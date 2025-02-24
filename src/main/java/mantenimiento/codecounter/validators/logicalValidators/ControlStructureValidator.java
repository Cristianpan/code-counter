package mantenimiento.codecounter.validators.logicalValidators;

import mantenimiento.codecounter.interfaces.LogicalValidatorHandler;
import mantenimiento.codecounter.constants.JavaRegextConstants;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 
 * Verifica la presencia de estructuras de control como if, for, while, switch, etc.
 * Implementa la interfaz LogicalValidatorHandler para permitir encadenamiento de validadores.
 */
public class ControlStructureValidator implements LogicalValidatorHandler {
    private LogicalValidatorHandler nextValidator;

    /**
     * Establece el siguiente validador en la cadena de validación.
     * @param nextValidator el siguiente validador a ejecutar si este no encuentra coincidencias
     */
    @Override
    public void setNextValidator(LogicalValidatorHandler nextValidator) {
        this.nextValidator = nextValidator;
    }

    /**
     * Verifica si el código fuente contiene estructuras de control.
     * @param linesOfCode Lista de líneas de código a analizar
     * @return true si se encuentra una estructura de control, false en caso contrario
     */
    @Override
    public boolean isValid(List<String> linesOfCode) {
        Pattern pattern = Pattern.compile(JavaRegextConstants.FLOW_CONTROL_REGEX);
        
        for (String line : linesOfCode) {
            if (pattern.matcher(line).find()) {
                return true;
            }
        }
        
        return nextValidator != null && nextValidator.isValid(linesOfCode);
    }
}


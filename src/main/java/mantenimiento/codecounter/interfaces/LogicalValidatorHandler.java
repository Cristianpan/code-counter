package mantenimiento.codecounter.interfaces;

import java.util.List;

public interface LogicalValidatorHandler {
    public void setNextValidator(LogicalValidatorHandler nexValidator); 
    public boolean isValid(List<String> linesOfCode); 
} 

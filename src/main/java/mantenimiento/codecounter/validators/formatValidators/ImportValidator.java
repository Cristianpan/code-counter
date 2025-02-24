package mantenimiento.codecounter.validators.formatValidators;

import mantenimiento.codecounter.exceptions.InvalidFormatException;

/**
 * Clase que valida si la importacion
 * de un paquete tiene un comodin y lanza una excepcion
 * en caso de haber
 * 
 * ej: import java.util*
 * 
 */
public class ImportValidator extends FormatValidator {

    public ImportValidator() {
        super();
    }

    /**
     * Realiza la validacion del formato verificando si 
     * es un import y si utiliza un comodin
     * 
     * @param lineOfFile linea de texto (sentencia de codigo) a validar
     * @return true si el import es sin comodin
     * @throws InvalidFormatException si el import se realiza con comodin
     */
    @Override
    public boolean isValid(String lineOfFile) throws InvalidFormatException {
        if (isImportDeclaration(lineOfFile)) {
            if (isImportWithWildcard(lineOfFile)) {
                throw new InvalidFormatException("Import con comodín");
            }

            return true;
        }

        return this.validateNext(lineOfFile);
    }

    /**
     * Valida si la sentencia corresponde a una
     * declaracion de import
     * 
     * @param lineOfCode sentencia a validar
     * @return true si es una declaracion de import, false si no corresponde
     */
    private boolean isImportDeclaration(String lineOfCode) {
        String importDeclarationRegex = "^import\\s+.*;$";
        return lineOfCode.matches(importDeclarationRegex);
    }

    /**
     * Valida si la declaracion de import, incluso un import statico, tiene comodín
     * 
     * @param lineOfCode sentencia a validar
     * @return true si el import es con comodin, false si no corresponde
     */
    private boolean isImportWithWildcard(String lineOfCode) {
        String wildcardImportRegex = "^import\\s+(static\\s+)?(\\w+\\.\\s*)+\\*\\s*;";
        return lineOfCode.matches(wildcardImportRegex);
    }
}

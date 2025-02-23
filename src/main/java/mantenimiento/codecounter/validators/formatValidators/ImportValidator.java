package mantenimiento.codecounter.validators.formatValidators;

import mantenimiento.codecounter.exceptions.InvalidFormatException;

public class ImportValidator extends FormatValidator {

    public ImportValidator() {
        super();
    }

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

    private boolean isImportDeclaration(String lineOfCode) {
        String importDeclarationRegex = "^import\\s+.*;$";
        return lineOfCode.matches(importDeclarationRegex);
    }

    private boolean isImportWithWildcard(String lineOfCode) {
        String wildcardImportRegex = "^import\\s+(static\\s+)?(\\w+\\.\\s*)+\\*\\s*;";
        return lineOfCode.matches(wildcardImportRegex);
    }
}

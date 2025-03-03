package mantenimiento.codecounter.constants;

/**
 * Contiene constantes con expresiones regulares utilizadas para
 * analizar estructuras y sintaxis del lenguaje Java.
 */
public class JavaRegextConstants {
    public final static String FLOW_CONTROL_KEYS = "(\\s*(if|for|(\\}\\s*)?while|switch)\\s*)";
    public final static String TRY_DECLARATION = "^(\\s*try\\s*(\\(|\\{).*)";
    public final static String ABSRTACT_KEY = "(abstract\\s+)?";
    public final static String ACCESS_MODIFIERS_REGEX = "((public|private|protected)\\s+)?";
    public final static String DATATYPE_DECLARATION_REGEX = "(\\s*[a-zA-Z0-9]+(<[a-zA-Z0-9]+>)?\\s+)";
    public final static String PARAMETERS_DECLARATION_REGEX = "(\\([^)]*\\)\\s*)";
    public final static String IDENTIFIER_DECLARATION_REGEX = "\\w+\\s*";
    public final static String TYPE_KEYS = "((class|enum|interface)\\s+)";
    public final static String BOOLEANS_OPERATORS = "((?:==|!=|<=|>=|<|>|\\|\\||&&|!)\\s*)";
    public final static String FINAL_OR_STATIC_REGEX = "(?:(?:static\\s+)?(?:final\\s+)?|(?:final\\s+)?(?:static\\s+)?)?";
    public final static String ANNOTATION_REGEX = "^@[A-Za-z_]\\w*(\\(.*\\))?\\s*$";
}

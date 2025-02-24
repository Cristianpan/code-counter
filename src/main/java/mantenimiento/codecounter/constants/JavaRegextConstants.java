package mantenimiento.codecounter.constants;

public class JavaRegextConstants {
    public final static String ACCESS_MODIFIERS_REGEX = "((public|private|protected)\\s+)?";
    public final static String DATATYPE_DECLARATION_REGEX = "([a-zA-Z0-9]+(<[a-zA-Z0-9]+>)?\\s+)";
    public final static String THROWS_DECLARATION_REGEX = "(\\s+(throws\\s+(\\w+\\s*,\\s*)*\\w+)\\s*)";
    public final static String PARAMETERS_DECLARATION_REGEX = "\\([^)]*\\)\\s*";
    public final static String IDENTIFIER_DECLARATION_REGEX = "\\w+\\s*";
    public final static String STRUCT_DECLARATION_REGEX = "((class|enum|interface)\\s+)";
    public final static String FLOW_CONTROL_REGEX = "((if|for|while|switch)\\s*)";
    public final static String CASE_DECLARATION_REGEX = "((case\\s+.*?:\\s*)+)";
    public final static String DEFAULT_DECLARATION_REGEX = "(default\\s*:\\s*)";
    public final static String ELSE_DECLARATION_REGEX = "(}\\s+else\\s+(if)?\\s*)";
    public final static String BOOLEANS_OPERATORS = "((?:==|!=|<=|>=|<|>|\\|\\||&&|!)\\s*)";
    public final static String FINAL_OR_STATIC_REGEX = "(?:(?:static\\s+)?(?:final\\s+)?|(?:final\\s+)?(?:static\\s+)?)?";
}

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
/**
 * Clase para leer un archivo de código fuente Java línea por línea e imprimir su contenido.
 */
public class CodeReader {
    private final File file;

    /**
     * Constructor de la clase JavaFileReader.
     *
     * @param filePath Ruta del archivo Java a leer.
     */
    public CodeReader(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Método para leer el archivo línea por línea e imprimir su contenido en la consola.
     * @throws IOException 
     */
    public void readAndPrintFile() throws IOException {
        if (!file.exists() || !file.isFile()) {
            System.out.println("El archivo especificado no existe o no es válido.");
            return;
        }

        System.out.println("Contenido del archivo " + file.getName() + ":");
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(file.getAbsolutePath()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    /**
     * Método principal para ejecutar la lectura del archivo.
     *
     * @param args Argumentos de la línea de comandos, donde el primer argumento es la ruta del archivo a leer.
     * @throws IOException 
     */
         public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Uso: CodeReader <ruta del archivo>");
            return;
        }
        
        CodeReader codeReader = new CodeReader(args[0]);
        codeReader.readAndPrintFile();
    }
}

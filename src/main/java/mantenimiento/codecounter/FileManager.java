import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * Clase para leer archivos dentro de un directorio de manera recursiva (utilizando pilas)
 * 
 * Referencias:
 * -Google Style Guide for Java: https://google.github.io/styleguide/javaguide.html
 * -Documentación oficial de Java (File API): https://docs.oracle.com/javase/8/docs/api/java/io/File.html
 * -List Files in a Directory in Java: https://www.baeldung.com/java-list-directory-files
 * -Java File Handling Tutorial: https://www.geeksforgeeks.org/file-handling-in-java/
 */

public class FileManager {
    private File rootDirectory;
    private List<String> filePaths;

    /**
     * Constructor de la clase.
     * @param directoryPath Ruta del directorio raíz donde se buscarán los archivos.
     */
    public FileManager(String directoryPath) {
        this.rootDirectory = new File(directoryPath);
        this.filePaths= new ArrayList<>();
    }

    /**
     * Método para iniciar la lectura de archivos en el directorio raíz
     */
    public void readFiles() {
        if (!rootDirectory.exists() || !rootDirectory.isDirectory()) {
            System.out.println("El directorio especificado no existe o no es válido");
            return;
        }
        readFilesIteratively(rootDirectory);

        //Se imprime la lista de archivos .java como prueba de funcionamiento
        System.out.println("\nLista de archivos .java encontrados:");
        if (filePaths.isEmpty()){
            System.out.println("No se encontraron archivos .java");
        } else {
            for (String path : filePaths){
                System.out.println(path);
            }
        }
    }

    /**
     * Método iterativo para leer archivos dentro de una carpeta y sus subcarpetas usando una pila.
     * @param directory Carpeta inicial que se va a explorar.
     */
    private void readFilesIteratively(File directory) {
        Stack<File> stack = new Stack<>();
        stack.push(directory);

        while (!stack.isEmpty()) {
            File currentDir = stack.pop();
            File[] files = currentDir.listFiles();
            if (files == null) {
                continue;
            }
            for (File file : files) {
                if (file.isDirectory()) {
                    stack.push(file);
                } else {
                    if (isJavaFile(file)) {
                        System.out.println("Archivo Java encontrado: " + file.getAbsolutePath());
                        filePaths.add(file.getAbsolutePath());
                        try {
                            CodeReader codeReader = new CodeReader(file.getAbsolutePath());
                            //Se imprime el contenido del archivo para probar el funcionamiento
                            codeReader.readAndPrintFile();
                        } catch (IOException e) {
                            System.err.println("Error al leer el archivo: " + file.getAbsolutePath());
                        }
                    }
                }
            }
        }
    }

    /**
     * Método para analizar si el archivo termina en .java.
     * @param file Archivo a analizar.
     */
    private boolean isJavaFile(File file) {
        return file.getName().endsWith(".java");
    }
}


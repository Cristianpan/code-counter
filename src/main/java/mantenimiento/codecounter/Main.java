package mantenimiento.codecounter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProgramAnalizer.analiceProgram("C:\\Users\\cristianpan\\Desktop\\code-counter\\test");
    }

    private static String requestFolderPath() {
        System.out.println("Ingresa la ruta de la carpeta del proyecto:");
        try (Scanner scanner = new Scanner(System.in)) {
            return scanner.nextLine();
        }
    }
}

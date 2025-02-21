package mantenimiento.codecounter;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        System.out.println("Init application...");
        String directoryPath = "C:\\Users\\pauli\\Downloads\\Mantenimiento 3.0\\ejemplo"; 
        FileReader fileReader = new FileReader(directoryPath);
        fileReader.readFiles();
    }
}
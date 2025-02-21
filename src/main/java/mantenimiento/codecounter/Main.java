package mantenimiento.codecounter;

public class Main {
    public static void main(String[] args) {
        
        //Directorio de ejemplo, cambiar cuando se pruebe
        String directoryPath = "C:\\Users\\pauli\\Downloads\\Mantenimiento 3.0\\ejemplo"; 
        FileManager fileReader = new FileManager(directoryPath);
        fileReader.readFiles();
    }
}

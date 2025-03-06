package mantenimiento.codecounter.models;

/**
 * Clase que representa un contador de lineas fisicas y logicas asociadas
 * a un archivo
 */
public class LineCounter {
    private int physicalLineAmount;
    private int logicalLineAmount;
    private String fileName;

    public LineCounter(String fileName) {
        this.logicalLineAmount = 0;
        this.physicalLineAmount = 0;
        this.fileName = fileName;
    }

    /**
     * Incrementa el contador de lineas fisicas
     */
    public void incrementPhysicalLineAmount() {
        this.physicalLineAmount++;
    }

    /**
     * Incrementa el contador de lineas lógicas
     */
    public void incrementLogicalLineAmount() {
        this.logicalLineAmount++;
    }

    /**
     * @return Devuelve la cantidad de lineas fisicas
     */
    public int getPhysicalLineAmount() {
        return this.physicalLineAmount;
    }

    /**
     * @return Devuelve la cantidad de lineas lógicas
     */
    public int getLogicalLineAmount() {
        return this.logicalLineAmount;
    }

    /**
     * @return Devuelve el nombre del archivo
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * @return Devuelve la cantidad de lineas fisicas y logicas en formato de cadena
     */
    @Override
    public String toString() {
        return "Lineas lógicas: " + this.logicalLineAmount + "\nLíneas físicas: " + this.physicalLineAmount;
    }
}
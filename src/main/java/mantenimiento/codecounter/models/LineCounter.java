package mantenimiento.codecounter.models;

public class LineCounter {
    private int physicalLineAmount;
    private int logicalLineAmount;
    private String fileName;

    public LineCounter(String fileName) {
        this.logicalLineAmount = 0;
        this.physicalLineAmount = 0;
        this.fileName = fileName;
    }

    public void incrementPhysicalLineAmount() {
        this.physicalLineAmount++;
    }

    public void incrementLogicalLineAmount() {
        this.logicalLineAmount++;
    }

    public int getPhysicalLineAmount() {
        return this.physicalLineAmount;
    }

    public int getLogicalLineAmount() {
        return this.logicalLineAmount;
    }

    public String getFileName() {
        return this.fileName;
    }

    @Override 
    public String toString(){
        return "Lineas lógicas: " + this.logicalLineAmount + "\nLíneas físicas: " + this.physicalLineAmount; 
    }
}
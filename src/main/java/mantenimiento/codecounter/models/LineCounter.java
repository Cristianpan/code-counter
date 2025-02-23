package mantenimiento.codecounter.models;

public class LineCounter {
    private int physicalLineAmount;
    private int logicalLineAmount;

    public LineCounter() {
        this.logicalLineAmount = 0;
        this.physicalLineAmount = 0;
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

    @Override 
    public String toString(){
        return "Lineas lógicas: " + this.logicalLineAmount + "\nLíneas físicas: " + this.physicalLineAmount; 
    }
}
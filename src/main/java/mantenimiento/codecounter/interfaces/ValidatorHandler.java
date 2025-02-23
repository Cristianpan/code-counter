package mantenimiento.codecounter.interfaces;

public interface ValidatorHandler <E extends Exception> {
    public void setNextValidator(ValidatorHandler<E> nextFormatValidator);
    public boolean isValid(String lineOfFile) throws E;
}

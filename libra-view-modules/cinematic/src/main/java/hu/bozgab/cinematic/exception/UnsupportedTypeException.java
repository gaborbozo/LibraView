package hu.bozgab.cinematic.exception;

public class UnsupportedTypeException extends RuntimeException {

    public <T> UnsupportedTypeException(Class<T> clazz) {
        super("Unsupported type of " + clazz.getName());
    }

}

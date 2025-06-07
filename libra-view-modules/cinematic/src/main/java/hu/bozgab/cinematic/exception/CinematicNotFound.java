package hu.bozgab.cinematic.exception;

public class CinematicNotFound extends RuntimeException {

    public CinematicNotFound() {
    }

    public CinematicNotFound(String message) {
        super(message);
    }

}

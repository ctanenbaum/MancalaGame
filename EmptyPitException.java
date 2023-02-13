package mancala;

public class EmptyPitException extends Exception {
	public EmptyPitException() {
		super("Error: You cannot pick from an empty hole ");
	}

}

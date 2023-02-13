package mancala;

public class Players {
	private String firstPlayer;
	private String secondPlayer;
	private String currentPlayer;

	public Players() {
		this.firstPlayer = "playerOne";
		this.secondPlayer = "playerTwo";
		currentPlayer = firstPlayer;
	}

	public Players(String first, String second) {
		this.firstPlayer = first;
		this.secondPlayer = second;
		currentPlayer = firstPlayer;
	}

	public void setFirstPlayer(String first) {
		firstPlayer = first;
	}

	public void setSecondPlayer(String second) {
		secondPlayer = second;
	}

	public String getFirstPlayer() {
		return firstPlayer;
	}

	public String getSecondPlayer() {
		return secondPlayer;
	}

	public String getCurrentPlayer() {
		return currentPlayer;
	}

	public String otherPlayer() {
		String otherPlayer = firstPlayer;
		if (currentPlayer == firstPlayer) {
			otherPlayer = secondPlayer;

		}
		return otherPlayer;
	}

	public String nextPlayer() {
		if (currentPlayer == firstPlayer) {
			currentPlayer = secondPlayer;
		} else {
			currentPlayer = firstPlayer;
		}
		return currentPlayer;
	}
}

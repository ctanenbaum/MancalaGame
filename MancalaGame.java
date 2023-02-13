package mancala;

import java.util.Scanner;

public class MancalaGame {
	private String firstPlayer;
	private String secondPlayer;
	private Hole[] arrayHoles;

	public MancalaGame(String playerName1, String playerName2) {
		firstPlayer = playerName1;
		secondPlayer = playerName2;
		arrayHoles = new Hole[14];
	}

	public void setup() {
		// instantiate array with 6 holes of 4, hole of 0, 6 holes of 4, hole of 0
		String tempPlayerName = firstPlayer;
		for (int i = 0; i < 14; i++) {
			if (i % 7 == 6) {
				arrayHoles[i] = new Store(tempPlayerName);
			} else {
				arrayHoles[i] = new Pit(tempPlayerName, i % 7 + 1, i);
			}
			if (i == 6) {
				tempPlayerName = secondPlayer;
			}
		}
	}

	public void printBoard(Players players) {
		if (players.getCurrentPlayer() == players.getFirstPlayer()) { // print board from first players perspective
			System.out.println("Board from " + players.getCurrentPlayer() + "'s perspective\n");
			System.out.println(players.otherPlayer() + "'s Mancala  Pit 6   Pit 5   Pit 4   Pit 3   Pit 2   Pit 1 ");
			StringBuilder str = new StringBuilder();
			for (int i = 0; i < 7; i++) {
				str.append("\t  " + arrayHoles[13 - i].getCount());
			}

			System.out.println(str.toString());
			StringBuilder str2 = new StringBuilder();
			str2.append("\t");
			for (int i = 0; i < 7; i++) {
				str2.append("\t  " + arrayHoles[i].getCount());
			}
			System.out.println(str2.toString());
			System.out.println(
					"\t\tPit 1   Pit 2   Pit 3   Pit 4   Pit 5   Pit 6 " + players.getCurrentPlayer() + "'s Mancala");
			System.out.println();
		} else { // print from second players perspective
			System.out.println("Board from " + players.getCurrentPlayer() + "'s perspective\n");
			System.out.println(players.otherPlayer() + "'s Mancala  Pit 6   Pit 5   Pit 4   Pit 3   Pit 2   Pit 1 ");
			StringBuilder str = new StringBuilder();
			for (int i = 0; i < 7; i++) {
				str.append(" \t  " + arrayHoles[6 - i].getCount());
			}

			System.out.println(str.toString());
			StringBuilder str2 = new StringBuilder();
			str2.append("\t");
			for (int i = 0; i < 7; i++) {
				str2.append("\t  " + arrayHoles[i + 7].getCount());
			}
			System.out.println(str2.toString());
			System.out.println(
					"\t\tPit 1   Pit 2   Pit 3   Pit 4   Pit 5   Pit 6 " + players.getCurrentPlayer() + "'s Mancala");
			System.out.println();
		}

	}

	public void playTurn(Players players) {
		Scanner keyboard = new Scanner(System.in);
		int pitNum;
		Pit chosenPit;
		System.out.println(players.getCurrentPlayer() + ", Which pit would you like to pick from? ");
		pitNum = keyboard.nextInt();
		while (pitNum < 1 || pitNum > 6) { // validate
			System.out.println("This pit number is invalid");
			System.out.println(players.getCurrentPlayer() + ", Which pit would you like to pick from? ");
			pitNum = keyboard.nextInt();
		}
		chosenPit = (Pit) locatePit(players, pitNum); // set pit number as chosen pit
		while (chosenPit.getCount() == 0) { // validate
			System.out.println("This pit is empty, choose another pit.");
			System.out.println(players.getCurrentPlayer() + ", Which pit would you like to pick from? ");
			pitNum = keyboard.nextInt();
			chosenPit = (Pit) locatePit(players, pitNum);
		}
		place(chosenPit, arrayHoles, players); // place marbles from chosen pit in following pits

	}

	public void place(Pit chosenPit, Hole[] arrayHoles, Players players) {
		int num = chosenPit.getCount();
		int index = chosenPit.getPitIndex();
		Boolean restart = false;
		Hole nextHole = null; // set a hole as following hole (could be stores)
		for (int i = 1; i <= num; i++) {
			nextHole = getNextHole(chosenPit, arrayHoles, players.getCurrentPlayer(), i, restart);

			nextHole.add(); // add one to next hole

			if (chosenPit.getPitIndex() + i == 13) { // if array is finished - restart
				chosenPit = (Pit) arrayHoles[0];
				num -= i;
				i = 0;
				restart = true;

			}

		}

		if (nextHole.getCount() == 1 && nextHole.getOwner() == players.getCurrentPlayer()
				&& nextHole.getClass().getName().contains("Pit")) { // if last marble landed in empty pit (not store)
			int marbPitAcross;
			int pitAcross;
			pitAcross = ((Pit) nextHole).getPitAcross(); // cast to pit cuz it has to be a pit
			marbPitAcross = arrayHoles[pitAcross].getCount(); // pull marblles from pit across
			nextHole.setCount(marbPitAcross + 1); // add from pit across to landed on pit
			arrayHoles[pitAcross].setCount(0); // set pit across to 0

		}
		chosenPit = (Pit) arrayHoles[index]; // arrayHoles holds pits and store but chosen pit has to be a pit
		chosenPit.pick();
		if (nextHole.getOwner() == players.getCurrentPlayer() && nextHole.getClass().getName().contains("Store")) {
			if (gameDone(players) == false) {
				printBoard(players);
				System.out.println("Your last marble landed in your store,"); // if last marble landed in current
																				// players store - go again!
				System.out.println("You win a free turn!");
				playTurn(players);
			}

		}

	}

	public Hole getNextHole(Pit chosenPit, Hole[] arrayHoles, String currentPlayer, int i, Boolean restart) {
		if (restart == true) {
			Hole nextHole = arrayHoles[chosenPit.getPitIndex() - 1 + i];
			if (nextHole.getOwner() != currentPlayer) { // skip over other player's hole
				nextHole = arrayHoles[chosenPit.getPitIndex() - 1 + i];
			}
			return nextHole;
		} else {
			Hole nextHole = arrayHoles[chosenPit.getPitIndex() + i];
			if (nextHole.getOwner() != currentPlayer) {
				nextHole = arrayHoles[chosenPit.getPitIndex() + i];
			}
			return nextHole;
		}

	}

	public Hole locatePit(Players players, int pitNum) {
		if (players.getCurrentPlayer() == players.getFirstPlayer()) {
			pitNum = pitNum - 1; // get index from pit number user chose
		} else {
			pitNum = pitNum + 6;
		}
		return arrayHoles[pitNum];

	}

	public boolean gameDone(Players players) { // check if game over
		int total1 = 0;
		int total2 = 0;
		for (int i = 0; i < 6; i++) {
			if (arrayHoles[i].getCount() == 0) { // if either side has all empty pits
				total1++;

			}
			if (arrayHoles[i + 7].getCount() == 0) {
				total2++;
			}

		}
		if (total1 == 6 || total2 == 6) { // find winner
			int totalMarbles1 = 0;
			int totalMarbles2 = 0;
			if (total1 == 6) {
				for (int i = 0; i < 7; i++) { // if first player had all empty
					totalMarbles2 += arrayHoles[i + 7].getCount(); // add rest of marbles to second players store
				}
			} else {
				for (int i = 0; i < 6; i++) { // if second player had all empty
					totalMarbles1 += arrayHoles[i].getCount(); // add rest of marbles to first players store
				}
			}
			if (totalMarbles1 > totalMarbles2) {
				System.out.println(players.getFirstPlayer() + " won!"); // whoever has more marbles wins!
			} else if (totalMarbles1 < totalMarbles2) {
				System.out.println(players.getSecondPlayer() + " won!");
			} else {
				System.out.println("It's a tie!");
			}
			return true;
		}
		return false;
	}

}

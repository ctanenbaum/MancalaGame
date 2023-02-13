package mancala;
import java.util.*;

import beetleGame.BeetleGame;

public class Main {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("What is the first players name? ");
		String firstPlayer = keyboard.next();
		System.out.println("What is the second players name? ");
		String secondPlayer = keyboard.next();
		Players players = new Players(firstPlayer, secondPlayer);
		MancalaGame game = new MancalaGame(firstPlayer, secondPlayer);	
		game.setup();
		do {
		game.printBoard(players);  
		game.playTurn(players);
		game.printBoard(players); 
		players.nextPlayer();
		}
		while(game.gameDone(players) == false);

		

	}

}

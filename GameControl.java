/**
 * Carol Wong
 * Penn ID: 57485844
 * I completed this assignment without help.
 */


package simple21;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

/**
 * This is a simplified version of a common card game, "21". 
 */
public class GameControl {
    
	/**
	 * Human player.
	 */
    HumanPlayer human;
    
    /**
     * Computer player.
     */
    ComputerPlayer player1;
    
    /**
     * Computer player.
     */
    ComputerPlayer player2;
    
    /**
     * Computer player.
     */
    ComputerPlayer player3;
    
    /** 
     * A random number generator to be used for returning random "cards" in a card deck.
     * */
    Random random = new Random();
      
    /**
     * The main method just creates a GameControl object and calls its run method.
     * @param args Not used.
     */
    public static void main(String args[]) {    
        new GameControl().run();
    }
    
    /**
     * Prints a welcome message, then calls methods to perform each of the following actions:
     * - Create the players (one of them a Human)
     * - Deal the initial two cards to each player
     * - Control the play of the game
     * - Print the final results
     */
    public void run() {
    	
        Scanner scanner = new Scanner(System.in);
        
     // print the instructions
     		System.out.println("************************************ S I M P L E  2 1 ************************************");
     		System.out.println("Welcome to Simple 21.");
     		System.out.println("Instructions:");
     		System.out.println("You are playing against 3 other players.");
     		System.out.println("Each of you will try to get as close as possible to 21, without exceeding.");
     		System.out.println("Please enter your name: ");
     		String humansName = scanner.next();
     		createPlayers(humansName);	
     		deal();			
     		controlPlay(scanner);
     		printResults();        
     		scanner.close();	
    }
    
    /**
     * Creates one human player with the given humansName, and three computer players with hard-coded names.
     * @param humansName for human player
     */
    public void createPlayers(String humansName) {
    	// create the players
    	human = new HumanPlayer(humansName);
    	player1 = new ComputerPlayer("Player 1");
    	player2 = new ComputerPlayer("Player 2");
    	player3 = new ComputerPlayer("Player 3");         
    }
    
    /**
     * Deals two "cards" to each player, one hidden, so that only the player who gets it knows what it is, 
     * and one face up, so that everyone can see it. (Actually, what the other players see is the total 
     * of each other player's cards, not the individual cards.)
     */
    public void deal() { 
    	System.out.println();
    	System.out.println(human.name + ":");
    	this.human.takeHiddenCard(nextCard());
    	this.human.takeVisibleCard(nextCard());
    	
    	System.out.println();
    	System.out.println(player1.name + ":");
    	this.player1.takeHiddenCard(nextCard());
    	this.player1.takeVisibleCard(nextCard());
    	
    	System.out.println();
    	System.out.println(player2.name + ":");
    	this.player2.takeHiddenCard(nextCard());
    	this.player2.takeVisibleCard(nextCard());
    	
    	System.out.println();
    	System.out.println(player3.name + ":");
    	this.player3.takeHiddenCard(nextCard());
    	this.player3.takeVisibleCard(nextCard());    	
    }
    
    /**
     * Returns a random "card", represented by an integer between 1 and 10, inclusive. 
     * The odds of returning a 10 are four times as likely as any other value (because in an actual
     * deck of cards, 10, Jack, Queen, and King all count as 10).
     * 
     * Note: The java.util package contains a Random class, which is perfect for generating random numbers.
     * @return a random integer in the range 1 - 10.
     */
    public int nextCard() { 
    	
    	// Generate a random number from 1 to 13 (0 to 12, plus 1 to offset 0). 11 (Jack), 12 (Queen), or 13 (King) will count as only 10.
    	
    	int card = random.nextInt(12) + 1;
    	if (card == 11 || card == 12 || card == 13) {
    		card = 10;
    	}   	
    	return card;
    }

    /**
     * Gives each player in turn a chance to take a card, until all players have passed. Prints a message when 
     * a player passes. Once a player has passed, that player is not given another chance to take a card.
     * @param scanner to use for user input
     */
    
    public void controlPlay(Scanner scanner) { 
    	while (checkAllPlayersHavePassed() == false) {
    		// give human the chance to take card or pass
    		if (human.passed == false) {
    			boolean humanAction = human.offerCard(human, player1, player2, player3, scanner);
        		if (humanAction == true) {
        			System.out.println();
        			System.out.println(human.name + ":");
        			int humanCard = nextCard();
            		human.takeVisibleCard(humanCard);
        		}
        		else {
        			human.passed = true;
        			System.out.println();
        			System.out.println(human.name + " passed.");
        		}	
        	} 
        	
    		// give player 1 the chance to take card or pass
        	if (player1.passed == false) {
        		boolean p1Action = player1.offerCard(human, player1, player2, player3);
        		if (p1Action == true) {
        			System.out.println();
            		System.out.println(player1.name + ":");
            		int p1Card = nextCard();
            		player1.takeVisibleCard(p1Card);
        		} 
        		else {
        			player1.passed = true;
        			System.out.println();
        			System.out.println(player1.name + " passed.");
        		}
        	} 
        	
    		// give player 2 the chance to take card or pass
        	if (player2.passed == false) {
        		boolean p2Action = player2.offerCard(human, player1, player2, player3);
        		if (p2Action == true) {
        			System.out.println();
            		System.out.println(player2.name + ":");
            		int p2Card = nextCard();
            		player2.takeVisibleCard(p2Card);
        		} 
        		else {
        			player2.passed = true;
        			System.out.println();
        			System.out.println(player2.name + " passed.");
        		}
        	} 
        	
    		// give player 3 the chance to take card or pass
        	if (player3.passed == false) {
        		boolean p3Action = player3.offerCard(human, player1, player2, player3);
        		if (p3Action == true) {
        			System.out.println();
            		System.out.println(player3.name + ":");
            		int p3Card = nextCard();
            		player3.takeVisibleCard(p3Card);
        		} 
        		else {
        			player3.passed = true;
        			System.out.println();
        			System.out.println(player3.name + " passed.");
        		}
        	}    	
    	}        
    }
     
    /**
     * Checks if all players have passed.
     * @return true if all players have passed
     */
    public boolean checkAllPlayersHavePassed() {
    	// check if all players passed
    	if (human.passed == true && player1.passed == true && player2.passed == true && player3.passed == true) {
    		return true;
    	} 
    	else {
    		return false;
    	}	  	
    }
    
    /**
     * Prints a summary at the end of the game.
     * Displays how many points each player had, and if applicable, who wins.
     */
    public void printResults() { 
    	// print the score board
       	System.out.println();
    	System.out.println("GAME OVER!");
    	System.out.println("Total points for each player:");
    	System.out.println(human.name + " - " + human.getScore());
    	System.out.println(player1.name + " - " + player1.getScore());
    	System.out.println(player2.name + " - " + player2.getScore());
    	System.out.println(player3.name + " - " + player3.getScore()); 
		printWinner();	    	
    }

    /**
     * Determines who won the game, and prints the results.
     */
    public void printWinner() { 
    	
    	
    	// This sorts the scores from smallest to biggest (indexes 0 to 3).
    	int[] scores={human.getScore(), player1.getScore(), player2.getScore(), player3.getScore()};
    	Arrays.sort(scores);
    	
    	// size1 is the smallest score, size4 is the biggest score.
    	int size1 = scores[0];
    	int size2 = scores[1];
    	int size3 = scores[2];
    	int size4 = scores[3];
    	
    	// If the smallest score out of the 4 scores exceeds 21, the game is tied.
    	if (size1 > 21) {
    		System.out.println("The game is tied! There is no winner.");
    	}
    	
    	// If the second smallest score is more than 21, and the smallest score is less than or equal to 21, 
    	// then check who holds the smallest score. 
    	else if (size2 > 21 && size1 <= 21) {
    		if (size1 == human.getScore()) {
    			System.out.println(human.name + " is the winner at " + size1 + " points!");
    		}
    		if (size1 == player1.getScore()) {
    			System.out.println(player1.name + " is the winner at " + size1 + " points!");
    		}
    		if (size1 == player2.getScore()) {
    			System.out.println(player2.name + " is the winner at " + size1 + " points!");
    		}
    		if (size1 == player3.getScore()) {
    			System.out.println(player3.name + " is the winner at " + size1 + " points!");
    		}				
		}
    	// If the second biggest score is more than 21, and the second smallest score is less than or equal to 21, 
    	// then check who holds the second smallest score. 
    	else if (size3 > 21 && size2 <= 21) {
    		if (size2 == size1) {
    			System.out.println("The game is tied! There is no winner.");
    		}
    		else {
    			if (size2 == human.getScore()) {
        			System.out.println(human.name + " is the winner at " + size2 + " points!");
        		}
        		if (size2 == player1.getScore()) {
        			System.out.println(player1.name + " is the winner at " + size2 + " points!");
        		}
        		if (size2 == player2.getScore()) {
        			System.out.println(player2.name + " is the winner at " + size2 + " points!");
        		}
        		if (size2 == player3.getScore()) {
        			System.out.println(player3.name + " is the winner at " + size2 + " points!");
        		}
    		}
    	}
    	// If the biggest score is more than 21, and the second biggest score is less than or equal to 21, 
    	// then check who holds the second biggest score. 
    	else if (size4 > 21 && size3 <= 21) {
    		if (size3 == size2) {
    			System.out.println("The game is tied! There is no winner.");
    		}
    		else {
    			if (size3 == human.getScore()) {
        			System.out.println(human.name + " is the winner at " + size3 + " points!");
        		}
        		if (size3 == player1.getScore()) {
        			System.out.println(player1.name + " is the winner at " + size3 + " points!");
        		}
        		if (size3 == player2.getScore()) {
        			System.out.println(player2.name + " is the winner at " + size3 + " points!");
        		}
        		if (size3 == player3.getScore()) {
        			System.out.println(player3.name + " is the winner at " + size3 + " points!");
        		}
    		}   		
    	}
    	// Otherwise, just check who has the biggest score that didn't exceed 21, 
    	// weeding out the chances that the two biggest scores are equal.
    	else {
    		if (size4 == size3) {
    			System.out.println("The game is tied! There is no winner.");
    		}
    		else {
    			if (size4 == human.getScore()) {
        			System.out.println(human.name + " is the winner at " + size4 + " points!");
        		}
        		if (size4 == player1.getScore()) {
        			System.out.println(player1.name + " is the winner at " + size4 + " points!");
        		}
        		if (size4 == player2.getScore()) {
        			System.out.println(player2.name + " is the winner at " + size4 + " points!");
        		}
        		if (size4 == player3.getScore()) {
        			System.out.println(player3.name + " is the winner at " + size4 + " pointsz!");
        		}
    		} 
    	}  	
    }    	
}

/**
 * Carol Wong
 * Penn ID: 57485844
 * I completed this assignment without help.
 */

package simple21;

/**
 * Represents a computer player in this simplified version of the "21" card game.
 */
public class ComputerPlayer {

	/** 
	 * The name of the player.
	 */
    String name;
    
    /**
     * The player's one hidden card (a value from 1 - 10).
     */
    private int hiddenCard = 0;
    
    /** 
     * The sum of the player's cards, not counting the hidden card. 
     */
    private int sumOfVisibleCards = 0;
    
    /**
     * Flag indicating if the player has passed (asked for no more cards).
     */
    boolean passed = false;
    
    /**
     * Constructs a computer player with the given name.
     * @param name of the user.
     */
    public ComputerPlayer (String name) {
        this.name = name;
    }
    
    /**
     * Decides whether to take another card. In order to make this decision, this player considers 
     * their own total points (sum of visible cards + hidden card). 
     * This player may also consider other players' sum of visible cards, but not the value 
     * of other players' hidden cards.
     * @param human The other human player
     * @param player1 Another (computer) player
     * @param player2 Another (computer) player
     * @param player3 Another (computer) player
     * @return true if this player wants another card
     */
    public boolean offerCard(HumanPlayer human, ComputerPlayer player1, ComputerPlayer player2, ComputerPlayer player3) { 
    	// This gives the computer players 1, 2, and 3 a game plan to decide whether to pass its turn.

    	int totalPlayer1 = player1.getScore();
    	int visiblePlayer1 = player1.sumOfVisibleCards;
    	int totalPlayer2 = player2.getScore();
    	int visiblePlayer2 = player2.sumOfVisibleCards;
    	int totalPlayer3 = player3.getScore();
    	int visiblePlayer3 = player3.sumOfVisibleCards;
    	int visibleHuman = human.getSumOfVisibleCards(); 
    	

    	
    	// Player 1's game plan
    	if (this.name == player1.name) {
    		// If P1's total cards are lower than 15, P1 draw another card.
    		if (totalPlayer1 < 15) {
    			return true; 
    		}
    		// Otherwise, if human's visible cards is less than 21, and human's visible cards are equal or more than P1's total cards, P1 draw another card.
    		else if (totalPlayer1 <= visibleHuman && visibleHuman < 21) {
    			return true; 
    		} 
    		// Otherwise, if P2's visible cards is less than 21, and P2's visible cards are equal or more than P1's total cards, P1 draw another card.
    		else if (totalPlayer1 <= visiblePlayer2 && visiblePlayer2 < 21) {
    			return true;
    		}
    		// Otherwise, if P3's visible cards is less than 21, and P3's visible cards are equal or more than P1's total cards, P1 draw another card.
    		else if (totalPlayer1 <= visiblePlayer3 && visiblePlayer3 < 21) {
    			return true; 
    		}
    		// Otherwise, P1 do not draw another card, and mark the flag indicating whether the player has passed as true.
    		else {
    			this.passed = true;
    			return false; 
    		}
    	}
    	
    	// Player 2's game plan
    	if (this.name == player2.name) {
    		// If P2's total cards are lower than 16, P2 draws another card.
    		if (totalPlayer2<16) {
    			return true; 
    		}
    		// Otherwise, if human's visible cards is less than 21, and human's visible cards are equal or more than P2's total cards, P2 draws another card.
    		else if (totalPlayer2 <= visibleHuman && visibleHuman < 21) {
    			return true; 
    		}
    		// Otherwise, if P1's visible cards is less than 21, and P1's visible cards are equal or more than P2's total cards, P2 draws another card.
    		else if (totalPlayer2 <= visiblePlayer1 && visiblePlayer1 < 21) {
    			return true; 
        	// Otherwise, if P3's visible cards is less than 21, and P3's visible cards are equal or more than P2's total cards, P2 draws another card.
    		}
    		else if (totalPlayer2 <= visiblePlayer3 && visiblePlayer3 < 21) {
    			return true;
    		}
    		// Otherwise, P2 do not draw another card, and mark the flag indicating whether the player has passed as true.
    		else {
    			this.passed = true;
    			return false; 
    		}    		
    	}    	
   
    	
    	// Player 3's game plan 
    	if (this.name == player3.name) {
    		// If P3's total cards are lower than 17, P3 draw another card.
    		if (totalPlayer1<17) {
    			return true; 
    		}
    		// Otherwise, if human's visible cards is less than 21, and human's visible cards are equal or more than P3's total cards, P3 draws another card.
    		else if (totalPlayer3 <= visibleHuman && visibleHuman < 21) {
    			return true; 
    		}
    		// Otherwise, if P1's visible cards is less than 21, and P1's visible cards are equal or more than P3's total cards, P3 draws another card.
    		else if (totalPlayer3 <= visiblePlayer1 && visiblePlayer1 < 21) {
    			return true; 
    		}
        	// Otherwise, if P2's visible cards is less than 21, and P2's visible cards are equal or more than P3's total cards, P3 draws another card.
    		else if (totalPlayer3 <= visiblePlayer2 && visiblePlayer2 < 21) {
    			return true; 
    		}
    		// Otherwise, P3 do not draw another card, and mark the flag indicating whether the player has passed as true.
    		} else {
    			this.passed = true;
    			return false;
    		}
    	
    	return false;  	
}
    
    /**    
     * Puts the specified card in this player's hand as the hidden card.
     * Prints a message saying that the card is being taken, but does not print the value of the hidden card.
     * @param card being taken
     */
    public void takeHiddenCard(int card) {
    	this.hiddenCard += card;
    	System.out.println("A hidden card has been drawn.");
    	
    }
    
    /**
     * Adds the given card to the sum of the visible cards for this player.
     * Prints a message saying that the card is being taken.
     * @param card being taken
     */
    public void takeVisibleCard(int card) { 
    	this.sumOfVisibleCards += card;
    	System.out.println("A " + card + " card has been drawn.");
    	
    }

    /**
     * Returns the total sum of this player's cards, not counting the hidden card. 
     * @return sumOfVisibleCards
     */
    public int getSumOfVisibleCards() { 
    	return this.sumOfVisibleCards;
    }
    
    /**
     * Return this player's total score (the total of all this player's cards).
     * That is to say, the sum of the visible cards + the hidden card.
     * @return total score 
     */
    public int getScore() { 
    	return this.sumOfVisibleCards + this.hiddenCard;    	
    
    }
}
    

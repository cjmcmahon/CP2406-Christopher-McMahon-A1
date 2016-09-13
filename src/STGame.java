import java.util.ArrayList;
import java.util.Random;
public class STGame
{
    private int numPlayers;
    private int dealerID;
    private int[] playerOrder;
    private STDeck gameDeck;
    private STPlayer[] players;

    STGame(int numPlayers, String deckFileString, int handSize)
    {
        this.numPlayers = numPlayers;
        this.gameDeck = new STDeck(deckFileString);
        this.playerOrder = new int [numPlayers];
        this.players = new STPlayer[numPlayers];
        for(int i = 0; i < numPlayers; i++)
        {
            // Create the player instance
            this.players[i] = new STPlayer(i);
            // Give the player some cards
            this.players[i].setPlayerHand(gameDeck.dealCards(handSize));
        }
        // Assign dealer
        Random rand = new Random();
        this.dealerID = rand.nextInt(this.numPlayers);
        // Build the player order vector to make it easier to cycle through players in the game phase
        for(int i = 0; i < this.numPlayers; i++)
        {
            this.playerOrder[i] = (this.dealerID + i + 1) % this.numPlayers;
        }
    }

    public void testSetup()
    {
        for(STPlayer player : this.players)
        {
            System.out.println(player);
        }
        System.out.println(gameDeck);
        System.out.println("dealerID=" + this.dealerID);
        System.out.print("playerOrder=[");
        for(int i : this.playerOrder)
        {
            System.out.print("" + i + " ");
        }
        System.out.print("]\n");
    }
}

import java.util.ArrayList;
import java.util.Random;
public class STGame
{
    public int numPlayers;
    public int dealerID;
    private int[] playerOrder;
    public STDeck gameDeck;
    public STPlayer[] players;

    STGame(int numPlayers, String deckFileString, int handSize)
    {
        this.numPlayers = numPlayers;
        this.gameDeck = new STDeck(deckFileString);
        this.playerOrder = new int [numPlayers];
        this.players = new STPlayer[numPlayers];
        // Player with id = 0 is always the user
        this.players[0] = new STHumanPlayer(0);
        this.players[0].setPlayerHand(gameDeck.dealCards(handSize));
        // Initialise the AI players
        for(int i = 1; i < numPlayers; i++)
        {
            // Create the player instance
            this.players[i] = new STAIPlayer(i);
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

    public int getDealerID()
    {
        return this.dealerID;
    }

    public int[] getPlayerOrder()
    {
        return this.playerOrder;
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

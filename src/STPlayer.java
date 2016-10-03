import java.util.ArrayList;
public abstract class STPlayer
{
    protected int id;
    protected ArrayList<STCard> playerHand;

    public STPlayer(int id)
    {
        this.id = id;
        this.playerHand = new ArrayList<STCard>();
    }

    public void setPlayerHand(ArrayList<STCard> inCards)
    {
        this.playerHand = inCards;
    }

    public ArrayList<STCard> getPlayerHand()
    {
        return this.playerHand;
    }

    public String toString()
    {
        return "playerID=" + this.id + "\ncards=" + this.playerHand;
    }
}

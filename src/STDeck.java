import java.util.ArrayList;
import java.util.Random;
public class STDeck
{
    private ArrayList<STCard> cards;

    public STDeck(int numInitialCards)
    {
        // TODO: read cards from the provided file or make a different constructor perhaps?
        // IntelliJ suggest explicit declaration of STCard is redundant, considering leaving just in case
        this.cards = new ArrayList<STCard>();
        for(int i = 0; i < numInitialCards; i++)
        {
            cards.add(new STPlayCard(i, "cardName" + i, 1, 2, 1f, CleavageType.NONE, CrustalAbundance.ULTRATRACE, EconomicValue.LOW));
        }
    }

    public ArrayList<STCard> dealCards(int numCardsToDeal)
    {
        ArrayList<STCard> returnCards = new ArrayList<STCard>();
        while(numCardsToDeal > 0 && cards.size() > 0)
        {
            Random rand = new Random();
            int i = rand.nextInt(cards.size());
            returnCards.add(cards.remove(i));
            numCardsToDeal--;
        }
        return returnCards;
    }

    public String toString()
    {
        return "numCardsLeft=" + this.cards.size() + "\ncards=" + this.cards;
    }
}

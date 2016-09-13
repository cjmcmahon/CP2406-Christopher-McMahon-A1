public class STTrumpCard extends STCard
{
    private CardPlayType cardPlayType;

    public STTrumpCard(String cardName, CardPlayType cardPlayType)
    {
        super(cardName);
        this.cardPlayType = cardPlayType;
    }

    public String toString()
    {
        return "cardName" + this.cardName + " trumpCardCat = " + this.cardPlayType;
    }
}

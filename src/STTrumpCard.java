public class STTrumpCard extends STCard
{
    private CardPlayType cardPlayType;

    public STTrumpCard(int id, String cardName, CardPlayType cardPlayType)
    {
        super(id, cardName);
        this.cardPlayType = cardPlayType;
    }

    public String toString()
    {
        return "id = " + this.id + " cardName" + this.cardName + " trumpCardCat = " + this.cardPlayType;
    }
}

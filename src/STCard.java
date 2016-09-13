public abstract class STCard
{
    protected String cardName;

    public STCard(String cardName)
    {
        this.cardName = cardName;
    }

    public abstract String toString();
}
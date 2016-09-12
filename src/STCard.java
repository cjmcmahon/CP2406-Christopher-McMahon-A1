public abstract class STCard
{
    protected int id;
    protected String cardName;

    public STCard(int id, String cardName)
    {
        this.cardName = cardName;
        this.id = id;
    }

    public abstract String toString();
}
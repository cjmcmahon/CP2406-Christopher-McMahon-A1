public class STTrumpCard extends STCard
{
    private CardPlayType cardPlayType;

    public STTrumpCard(String fileName, String imageName, String title, String subtitle)
    {
        super(fileName, imageName, title);
        this.cardPlayType = STTrumpCard.parseCardPlayType(subtitle);
    }

    private static CardPlayType parseCardPlayType(String cardPlayType)
    {
        // Convert to upper case to directly compare to enum types
        cardPlayType = cardPlayType.toUpperCase();
        for(CardPlayType cardPlayTypeTmp : CardPlayType.values())
        {
            if(cardPlayType.equals(cardPlayTypeTmp.toString()))
            {
                return cardPlayTypeTmp;
            }
        }
        // If couldn't get a specific value, return error and NULL value
        System.out.println("Couldn't parse Card Play Type, returning NULL");
        return CardPlayType.NULL;
    }

    public String toString()
    {
        return "title = " + this.title;
    }
}

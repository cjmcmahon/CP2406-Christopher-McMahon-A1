public class STTrumpCard extends STCard
{
    private CardPlayType cardPlayType;

    public STTrumpCard(String fileName, String imageName, String title, String subtitle)
    {
        super(fileName, imageName, title);
        this.cardPlayType = this.parseCardPlayType(subtitle);
    }

    private CardPlayType parseCardPlayType(String cardPlayType)
    {
        // Check for the geologist text, if so, covert to trump
        if(cardPlayType.equals("Change to trumps category of your choice"))
        {
            cardPlayType = "trump";
        }
        // Convert to upper case to directly compare to enum types
        cardPlayType = cardPlayType.toUpperCase();
        // Swap spaces for underscores
        cardPlayType = cardPlayType.replace(" ", "_");
        for(CardPlayType cardPlayTypeTmp : CardPlayType.values())
        {
            if(cardPlayType.equals(cardPlayTypeTmp.toString()))
            {
                return cardPlayTypeTmp;
            }
        }
        // If couldn't get a specific value, return error and NULL value
        System.out.println("Couldn't parse Card Play Type for " + this.title + ", returning NULL");
        return CardPlayType.NULL;
    }

    public String toString()
    {
        return "title = " + this.title + "\n";
    }
}

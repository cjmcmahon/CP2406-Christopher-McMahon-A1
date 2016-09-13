import java.util.ArrayList;
import java.util.Random;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
public class STDeck
{
    private ArrayList<STCard> cards;

    public STDeck(String deckFileString)
    {
        // IntelliJ suggest explicit declaration of STCard is redundant, considering leaving just in case
        this.cards = new ArrayList<STCard>();
        try
        {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(deckFileString));

            // Normalise the document
            document.getDocumentElement().normalize();

            // Note that the dictNodes NodeList includes the root dict node, hence the for loop below starts at 1
            NodeList dictNodes = document.getElementsByTagName("dict");

            for(int i = 1; i < dictNodes.getLength(); i++)
            {
                Node dictNode = dictNodes.item(i);
                // Ensure that this node is indeed an element node
                if(dictNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element dictElement = (Element)dictNode;
                    String cardType = dictElement.getElementsByTagName("key").item(3).getTextContent();
                    // As we know that the value for the type of the card is the item in index 3 of the 'key' tagged items
                    if(cardType.equals("play"))
                    {
                        // TODO: Deal with new play card
                    }
                    else if(cardType.equals("trump"))
                    {
                        // TODO: Deal with new trump card
                    }
                    else
                    {
                        // Ignore
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
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

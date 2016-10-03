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

                    // Extract the details which are common to both play and trump cards
                    String fileName, imageName, title;
                    fileName       = dictElement.getElementsByTagName("string").item(0).getTextContent();
                    imageName      = dictElement.getElementsByTagName("string").item(1).getTextContent();
                    title          = dictElement.getElementsByTagName("string").item(2).getTextContent();

                    String cardType = dictElement.getElementsByTagName("key").item(3).getTextContent();
                    // As we know that the value for the type of the card is the item in index 3 of the 'key' tagged items
                    if(cardType.equals("play"))
                    {
                        // Extract details as strings
                        String chemistry, classification, crystalSystem, hardness;
                        String specificGravity, cleavage, crustalAbundance, economicValue;
                        ArrayList<String> occurrence = new ArrayList<String>();
                        chemistry      = dictElement.getElementsByTagName("string").item(3).getTextContent();
                        classification = dictElement.getElementsByTagName("string").item(4).getTextContent();
                        crystalSystem  = dictElement.getElementsByTagName("string").item(5).getTextContent();
                        // Note, there is at least one occurrence element for each play card
                        occurrence.add(dictElement.getElementsByTagName("string").item(6).getTextContent());
                        int j = 0; // j is to be used as a counter for an offset to deal with varying number of occurrences
                        String tmpString = dictElement.getElementsByTagName("string").item(7).getTextContent();
                        if(tmpString.equals("igneous") || tmpString.equals("metamorphic") || tmpString.equals("sedimentary"))
                        {
                            // There is a second occurrence
                            occurrence.add(tmpString);
                            j++;
                            // Now check if there is a third, but only if there was a second
                            tmpString = dictElement.getElementsByTagName("string").item(8).getTextContent();
                            if(tmpString.equals("igneous") || tmpString.equals("metamorphic") || tmpString.equals("sedimentary"))
                            {
                                // There is a third occurrence
                                occurrence.add(tmpString);
                                j++;
                            }
                        }
                        // Now the value of j will be the offset caused by multiple occurrence strings, the remaining
                        // string items will be handled like there was only one occurrence, but add the offset
                        hardness         = dictElement.getElementsByTagName("string").item(7 + j).getTextContent();
                        specificGravity  = dictElement.getElementsByTagName("string").item(8 + j).getTextContent();
                        cleavage         = dictElement.getElementsByTagName("string").item(9 + j).getTextContent();
                        crustalAbundance = dictElement.getElementsByTagName("string").item(10 + j).getTextContent();
                        economicValue    = dictElement.getElementsByTagName("string").item(11 + j).getTextContent();

                        // Create the object using the retrieved strings, constructor will handle the extraction of details
                        this.cards.add(new STPlayCard(fileName, imageName, title, chemistry, classification,
                                                      crystalSystem, occurrence, hardness, specificGravity,
                                                      cleavage, crustalAbundance, economicValue));
                    }
                    else if(cardType.equals("trump"))
                    {
                        String subtitle = dictElement.getElementsByTagName("string").item(3).getTextContent();
                        this.cards.add(new STTrumpCard(fileName, imageName, title, subtitle));
                    }
                    else
                    {
                        // Ignore - Could include a list for rule cards
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

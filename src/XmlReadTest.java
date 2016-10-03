import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
public class XmlReadTest
{
    public static void main(String[] args) throws Exception
    {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(new File("MstCards_151021.plist"));

        document.getDocumentElement().normalize();
        System.out.println("Root element of the doc is " + document.getDocumentElement().getNodeName());

        NodeList dictNodes = document.getElementsByTagName("dict");
        System.out.println("Number of dictNode nodes = " + dictNodes.getLength());

        for(int i = 1; i < dictNodes.getLength(); i++)
        {
            Node dictNode = dictNodes.item(i);
            if(dictNode.getNodeType() == Node.ELEMENT_NODE)
            {
//                System.out.println(dictNode.getTextContent());
                Element dictElement = (Element)dictNode;
                String cardType = dictElement.getElementsByTagName("key").item(3).getTextContent();
                if(cardType.equals("play"))
                {
                    System.out.println("Deal with play card");
                    String tmp = dictElement.getElementsByTagName("string").item(6).getTextContent();
                    System.out.println(tmp);
                }
                else if(cardType.equals("trump"))
                {
                    System.out.println("Deal with trump card");
                }
                else
                {
                    System.out.println("Non valid card for storage");
                }
            }
        }
    }
}

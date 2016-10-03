/**
 * Created by Chris on 12/09/2016.
 */
import java.util.ArrayList;
public class STPlayCard extends STCard
{
    // Fields are private as no subclass will depend on this information
    private String chemistry;
    private String classification;
    private String crystalSystem;
    private ArrayList<String> occurrence;
    private float hardnessLow;
    private float hardnessHigh;
    private float specificGravityLow;
    private float specificGravityHigh;
    private CleavageType cleavageType;
    private CrustalAbundance crustalAbundance;
    private EconomicValue economicValue;

    public STPlayCard(String fileName, String imageName, String title, String chemistry, String classification,
                      String crystalSystem, ArrayList<String> occurrence, String hardness,
                      String specificGravity, String cleavageType, String crustalAbundance,
                      String economicValue)
    {
        // Accept the raw string inputs from the XML file and handle here
        super(fileName, imageName, title);
        String[] tmpString; // To handle the ranged inputs
        this.chemistry      = chemistry;
        this.classification = classification;
        this.crystalSystem  = crystalSystem;
        this.occurrence     = occurrence;
        // Handle hardness, remove any spaces if present
        hardness = hardness.replace(" ", "");
        tmpString = hardness.split("-");
        if(tmpString.length == 1)
        {
            // Apply hardness to both low and high fields
            this.hardnessLow  = Float.parseFloat(tmpString[0]);
            this.hardnessHigh = this.hardnessLow;
        }
        else
        {
            // Implies two hardness levels
            this.hardnessLow  = Float.parseFloat(tmpString[0]);
            this.hardnessHigh = Float.parseFloat(tmpString[1]);
        }
        // Handle specific gravity, remove any spaces if present
        specificGravity = specificGravity.replace(" ", "");
        tmpString = specificGravity.split("-");
        if(tmpString.length == 1)
        {
            // Apply specific gravity to both low and high fields
            this.specificGravityLow  = Float.parseFloat(tmpString[0]);
            this.specificGravityHigh = this.specificGravityLow;
        }
        else
        {
            // Implies two specific gravity levels
            this.specificGravityLow  = Float.parseFloat(tmpString[0]);
            this.specificGravityHigh = Float.parseFloat(tmpString[1]);
        }
        // Handle cleavage type
        this.cleavageType = this.parseCleavageType(cleavageType);
        // Handle crustal abundance
        this.crustalAbundance = this.parseCrustalAbundance(crustalAbundance);
        // Handle economic value
        this.economicValue = this.parseEconomicValue(economicValue);
    }

    private CleavageType parseCleavageType(String cleavageType)
    {
        // Convert to uppercase to compare to the enumeration type
        cleavageType = cleavageType.toUpperCase();
        // Remove the commas and any slashes
        cleavageType = cleavageType.replace(",", "");
        cleavageType = cleavageType.replace("/", " ");
        // Replace the spaces with underscores, this will now directly relate the enum values
        cleavageType = cleavageType.replace(" ", "_");
        // Rearrange the string from the XML as the XML is terrible
        // Also rearrange only if the first element is numeric
        String tmpString;
        String[] tmpArray = cleavageType.split("_");
        if(tmpArray[0].matches("\\d+"))
        {
            // Swap each element with the neighbour in sets of two
            for(int i = 0; i < tmpArray.length; i++)
            {
                if(i % 2 == 0)
                {
                    // Swap with the element to the right
                    tmpString = tmpArray[i];
                    tmpArray[i] = tmpArray[i + 1];
                    tmpArray[i + 1] = tmpString;
                }
            }
            // Rebuild the original string
            cleavageType = ""; // First empty it
            for(int i = 0; i < tmpArray.length - 1; i++)
            {
                cleavageType = cleavageType + tmpArray[i] + "_";
            }
            cleavageType = cleavageType + tmpArray[tmpArray.length - 1];
        }
        // Testing purposes, display the final string
//        System.out.println("" + this.title + " " + cleavageType);
        for(CleavageType cleavageTypeTmp : CleavageType.values())
        {
            if(cleavageType.equals(cleavageTypeTmp.toString()))
            {
                return cleavageTypeTmp;
            }
        }
        // If no matches, return a none type and message
        System.out.println("Couldn't parse Cleavage Type for " + this.title + ", set to NONE");
        return CleavageType.NONE;
    }

    private CrustalAbundance parseCrustalAbundance(String crystalAbundance)
    {
        // Convert to uppercase to compare to the enumeration type
        crystalAbundance = crystalAbundance.toUpperCase();
        // Replace any spaces with underscores, mostly for the very high case
        crystalAbundance = crystalAbundance.replace(" ", "_");
        for(CrustalAbundance crustalAbundanceTmp : CrustalAbundance.values())
        {
            if(crystalAbundance.equals(crustalAbundanceTmp.toString()))
            {
                return crustalAbundanceTmp;
            }
        }
        // If no matches, return a low type and message
        System.out.println("Couldn't parse Crustal Abundance for " + this.title + ", set to LOW");
        return CrustalAbundance.LOW;
    }

    private EconomicValue parseEconomicValue(String economicValue)
    {
        // Convert to uppercase to compare to the enumeration type
        economicValue = economicValue.toUpperCase();
        // Remove the apostrophe and exclamation mark, also change spaces for underscores
        economicValue = economicValue.replace("'", "");
        economicValue = economicValue.replace("!", "");
        economicValue = economicValue.replace(" ", "_");
        for(EconomicValue economicValueTmp : EconomicValue.values())
        {
            if(economicValue.equals(economicValueTmp.toString()))
            {
                return economicValueTmp;
            }
        }
        // If no matches, return a low type and message
        System.out.println("Couldn't parse Economic Value for " + this.title + ", set to TRIVIAL");
        return EconomicValue.TRIVIAL;
    }

    // Define get methods for each field

    public String getChemistry()
    {
        return this.chemistry;
    }

    public String getClassification()
    {
        return this.classification;
    }

    public String getCrystalSystem()
    {
        return this.crystalSystem;
    }

    public ArrayList<String> getOccurrence()
    {
        return this.occurrence;
    }

    public float getHardnessLow()
    {
        return this.hardnessLow;
    }

    public float getHardnessHigh()
    {
        return this.hardnessHigh;
    }

    public float getSpecificGravityLow()
    {
        return this.specificGravityLow;
    }

    public float getSpecificGravityHigh()
    {
        return this.specificGravityHigh;
    }

    public CleavageType getCleavageType()
    {
        return this.cleavageType;
    }

    public CrustalAbundance getCrustalAbundance()
    {
        return this.crustalAbundance;
    }

    public EconomicValue getEconomicValue()
    {
        return this.economicValue;
    }

    public String toString()
    {
        // Display the relevent core fields plus a few extra for 'educational purposes'
        StringBuilder returnString = new StringBuilder("");
        String tmpString = "Title             : " + this.title + "\n" +
                           "Chemistry         : " + this.chemistry + "\n" +
                           "Classification    : " + this.classification + "\n" +
                           "Crystal System    : " + this.crystalSystem + "\n";
        returnString.append(tmpString);
        returnString.append("Occurrence        : \n");
        for(int i = 0; i < this.occurrence.size(); i++)
        {
            returnString.append("\t\t\t" + this.occurrence.get(i) + "\n");
        }
        if(this.hardnessHigh - this.hardnessLow < 0.1)
        {
            // Hardness is equivalent, display a single value for hardness
            returnString.append("Hardness          : " + this.hardnessHigh + "\n");
        }
        else
        {
            returnString.append("Hardness          : " + this.hardnessLow + " - " + this.hardnessHigh + "\n");
        }
        if(this.specificGravityHigh - this.specificGravityLow < 0.1)
        {
            // Hardness is equivalent, display a single value for hardness
            returnString.append("Specific Gravity  : " + this.specificGravityHigh + "\n");
        }
        else
        {
            returnString.append("Specific Gravity  : " + this.specificGravityLow + " - " + this.specificGravityHigh + "\n");
        }
        returnString.append("Cleavage          : " + this.cleavageType.toString() + "\n");
        returnString.append("Crustal Abundance : " + this.crustalAbundance.toString() + "\n");
        returnString.append("Economic Value    : " + this.economicValue.toString() + "\n\n");
        return returnString.toString();
    }
}

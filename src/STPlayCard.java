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
    private int hardnessLow;
    private int hardnessHigh;
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
        // Handle hardness
        tmpString = hardness.split(" - ");
        if(tmpString.length == 1)
        {
            // Apply hardness to both low and high fields
            this.hardnessLow  = Integer.parseInt(tmpString[0]);
            this.hardnessHigh = Integer.parseInt(tmpString[0]);
        }
        else
        {
            // Implies two hardness levels
            this.hardnessLow  = Integer.parseInt(tmpString[0]);
            this.hardnessHigh = Integer.parseInt(tmpString[1]);
        }
        tmpString = specificGravity.split(" - ");
        if(tmpString.length == 1)
        {
            // Apply hardness to both low and high fields
            this.specificGravityLow  = Float.parseFloat(tmpString[0]);
            this.specificGravityHigh = Float.parseFloat(tmpString[0]);
        }
        else
        {
            // Implies two hardness levels
            this.specificGravityLow  = Float.parseFloat(tmpString[0]);
            this.specificGravityHigh = Float.parseFloat(tmpString[1]);
        }
        // Handle cleavage type
        this.cleavageType = STPlayCard.parseCleavageType(cleavageType);
        // Handle crustal abundance
        this.crustalAbundance = STPlayCard.parseCrustalAbundance(crustalAbundance);
        // Handle economic value
        this.economicValue = STPlayCard.parseEconomicValue(economicValue);
    }

    private static CleavageType parseCleavageType(String cleavageType)
    {
        // Convert to uppercase to compare to the enumeration type
        cleavageType = cleavageType.toUpperCase();
        // Replace the spaces with underscores, this will now directly relate the enum values
        cleavageType = cleavageType.replace(" ", "_");
        for(CleavageType cleavageTypeTmp : CleavageType.values())
        {
            if(cleavageType.equals(cleavageTypeTmp.toString()))
            {
                return cleavageTypeTmp;
            }
        }
        // If no matches, return a none type and message
        System.out.println("Couldn't parse Cleavage Type, set to NONE");
        return CleavageType.NONE;
    }

    private static CrustalAbundance parseCrustalAbundance(String crystalAbundance)
    {
        // Convert to uppercase to compare to the enumeration type
        crystalAbundance = crystalAbundance.toUpperCase();
        for(CrustalAbundance crustalAbundanceTmp : CrustalAbundance.values())
        {
            if(crystalAbundance.equals(crustalAbundanceTmp.toString()))
            {
                return crustalAbundanceTmp;
            }
        }
        // If no matches, return a low type and message
        System.out.println("Couldn't parse Crustal Abundance, set to LOW");
        return CrustalAbundance.LOW;
    }

    private static EconomicValue parseEconomicValue(String economicValue)
    {
        // Convert to uppercase to compare to the enumeration type
        economicValue = economicValue.toUpperCase();
        for(EconomicValue economicValueTmp : EconomicValue.values())
        {
            if(economicValue.equals(economicValueTmp.toString()))
            {
                return economicValueTmp;
            }
        }
        // If no matches, return a low type and message
        System.out.println("Couldn't parse Economic Value, set to LOW");
        return EconomicValue.LOW;
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

    public int getHardnessLow()
    {
        return this.hardnessLow;
    }

    public int getHardnessHigh()
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
        return "title = " + this.title;
    }
}

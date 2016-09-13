/**
 * Created by Chris on 12/09/2016.
 */
public class STPlayCard extends STCard
{
    private int mineralHardnessLow;
    private int mineralHardnessHigh;
    private float specificGravity;
    private CleavageType cleavageType;
    private CrustalAbundance crustalAbundance;
    private EconomicValue economicValue;

    public STPlayCard(String cardName, int mineralHardnessLow, int mineralHardnessHigh, float specificGravity,
                      CleavageType cleavageType, CrustalAbundance crustalAbundance, EconomicValue economicValue)
    {
        super(cardName);
        this.mineralHardnessLow = mineralHardnessLow;
        this.mineralHardnessHigh = mineralHardnessHigh;
        this.specificGravity = specificGravity;
        this.cleavageType = cleavageType;
        this.crustalAbundance = crustalAbundance;
        this.economicValue = economicValue;
    }

    public String toString()
    {
        return "cardName = " + this.cardName + " minHard = " + this.mineralHardnessLow +
                "-" + this.mineralHardnessHigh + " spGrav = " + this.specificGravity + " clType = " +
                this.cleavageType + " crAbun = " + this.crustalAbundance + " ecVal = " + this.economicValue;
    }
}

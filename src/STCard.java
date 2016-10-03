public abstract class STCard
{
    // Fields are protected as the subclasses will rely on being able to freely access this info
    protected String fileName;
    protected String imageName;
    protected String title;

    public STCard(String fileName, String imageName, String title)
    {
        this.fileName  = fileName;
        this.imageName = imageName;
        this.title     = title;
    }

    // Define the get methods
    public String getFileName()
    {
        return this.fileName;
    }

    public String getImageName()
    {
        return this.imageName;
    }

    public String getTitle()
    {
        return this.title;
    }

    public abstract String toString();
}
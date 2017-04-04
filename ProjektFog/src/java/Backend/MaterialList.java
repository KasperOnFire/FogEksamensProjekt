package Backend;

public class MaterialList
{
    private int roofScrews;
    private double roofing;
    private int boards;
    private int roofBoards;
    private int posts;
    private int postScrews;
    private int postBandBoards;
    private double m2;
    private int roofBoardScrews;
    private int roofBoardBrackets;
    
    public void calcCarport(int length, int width)
    {
        calcRoofScrews(length,width);
        calcRoofBoards(length);
        calcPosts(length);
    }
    
    private void calcRoofScrews(int length,int width)
    {
        m2 = (double)(length*width)/100;
        roofScrews = (int)m2*12;
    }
    
    private void calcRoofBoards(int length)
    {
        roofBoards = (length/60)+1;
        roofBoardScrews = roofBoards*18;
        roofBoardBrackets = roofBoards*2;
    }
    
    private void calcPosts(int length)
    {
        posts = 2*(length <= 400 ? 2 : (int) Math.ceil((double) length / 200));
        postScrews = posts*4;
        postBandBoards = 2;
    }
    
    @Override
    public String toString()
    {
        String ret = "";
        ret =    "Roofing area(m2): " + m2 + "\n"
                +"Roof screws: " + roofScrews + "\n"
                +"Roof boards: " + roofBoards + "\n"
                +"Posts: " + posts + "\n"
                +"Post screws: " + postScrews + "\n"
                +"Post bands: " + postBandBoards;
        return ret;
    }
    
}
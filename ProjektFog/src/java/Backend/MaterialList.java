package Backend;

public class MaterialList
{
    private int roofScrews;
    private double roofing;
    private int boards;
    private int roofBoards;
    private int posts;
    private int postScrews;
    private int postBands;
    
    public void calcRoofScrews(int length,int width)
    {
        double m2 = (double)(length*width)/100;
        roofScrews = (int)m2*12;
    }
    
    public void calcRoofBoards(int length)
    {
        roofBoards = (length/60)+1;
    }
    
    public void calcPosts(int length)
    {
        posts = (length <= 400 ? 2 : (int) Math.ceil((double) length / 200));
        postScrews = posts*4;
        postBands = posts;
    }
    
}

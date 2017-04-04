package Backend;

public class MaterialList
{
    private int roofScrews;
    private int roof;
    private int boards;
    private int roofBoards;
    private int posts;
    private int postScrews;
    private int postBandBoards;
    private double m2;
    private int roofBoardScrews;
    private int roofBoardBrackets;
    private int postBolts;
    private int mountingBandScrews;
    private int mountingBands = 2;
    
    
    public void calcCarport(int length, int width)
    {
        
    }
    
    private void roof(int length,int width)
    {
        m2 = (double)(length*width)/100;
        roof = (int)Math.ceil(m2)+1;
    }
    
    private void roofScrews(double m2)
    {
        roofScrews = (int)m2*12;
    }
    
    private void roofBoards(int length)
    {
        roofBoards = (length/60)+1;
    }
    
    private void roofBoardScrews(int roofBoards)
    {
        roofBoardScrews = roofBoards*18;
    }
    
    private void roofBoardBrackets(int roofBoards)
    {
        roofBoardBrackets = roofBoards*2;
    }
    
    private void mountingBandScrews(int roofBoards, int mountingBands)
    {
        mountingBandScrews = roofBoards*mountingBands;
    }
    
    private void posts(int length)
    {
        posts = 2*(length <= 400 ? 2 : (int) Math.ceil((double) length / 200));
    }
    
    private void postScrews(int posts)
    {
        postScrews = posts*4;
    }
    
    private void postBolts(int posts)
    {
        postBolts = posts*2;
    }
    
}
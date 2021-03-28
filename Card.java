public interface Card{
    public static final String Black = "\u001B[30m";
    public static final String Red = "\u001B[31m";
    public static final String Green = "\u001B[32m";
    public static final String Yellow = "\u001B[33m";
    public static final String Blue = "\u001B[34m";
    public static final String White = "\u001B[37m";
    public static final String Reset = "\u001B[0m";
	public String getColor();
    public int getNumber();
    public String getPower();
    public void printCard();
}
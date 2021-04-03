import java.util.*;
public class Player {
    private boolean isGiliran=false;
    private List<Card> playerHand = new ArrayList<Card>();
    private String name;
    private String color;

    public Player(String name, String playerColor){
        this.name=name;
        this.color=playerColor;
        generatePlayerDeck();
    }

    public void generatePlayerDeck(){
        Random rand=new Random();
        int banyakAngka = 10;
        int banyakWarna = 4;
        for (int i=0;i<7;i++){
            int angka = rand.nextInt(banyakAngka);
            int warna = rand.nextInt(banyakWarna);
            String color;

            if (warna==0) color="Red";
            else if (warna==1) color="Green";
            else if (warna==2) color="Blue";
            else color="Yellow";

            int number = angka;
            Card nextCard = new NumCard(color, number);
            playerHand.add(nextCard);
        }
    }
    public Card discard(int cardOrder) {
        Card discardCard = playerHand.get(cardOrder);
        playerHand.remove(discardCard);
        return (discardCard);
    }

    public Card checkHand(int cardOrder){
        return playerHand.get(cardOrder);
    }

    public void setGiliran(boolean giliran){
        isGiliran=giliran;
    }

    public boolean isGiliran() {
        return isGiliran;
    }

    public void draw(Deck deck){
        playerHand.add(deck.getTop());
        deck.shuffle();
    }

    public String getName(){
        return color+name+Card.Reset;
    }

    public int getRemainingCards(){
        return playerHand.size();
    }

    public void showDeck(){
        for (int i=0;i<playerHand.size();i++){
            Card card = playerHand.get(i);
            System.out.print((i+1)+". ");
            card.printCard();
        }
    }

    public List<Card> getPlayerHand(){
        return playerHand;
    }

    public void declareHiji(Deck deck) throws Exception{
        if (getRemainingCards()==1){
            System.out.print(Card.Red+"H ");
            System.out.print(Card.Green+"I ");
            System.out.print(Card.Yellow+"J ");
            System.out.print(Card.Blue+"I ");
            System.out.print(Card.Cyan+"has been declared ");
            System.out.println("by "+getName()+Card.Reset+"!");
        }
        else {
            System.out.print(Card.Red+"Whoops, you still have more than 1 card left! ");
            System.out.println(Card.Cyan+"Only declare HIJI when you have 1 card left."+Card.Reset);
            throw new Exception("IllegalDeclaration");
        }
    }
}

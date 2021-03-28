import java.util.*;
public class Player {
    private boolean isGiliran=false;
    private List<Card> playerHand = new ArrayList<Card>();
    private String name;

    public Player(String name){
        this.name=name;
        Random rand=new Random();
        int banyakAngka = 10;
        int banyakWarna = 4;
        for (int i=0;i<7;i++){
            int angka = rand.nextInt(banyakAngka);
            int warna = rand.nextInt(banyakWarna);
            String color;
            if (warna==0){color="Red";} else if (warna==1){color="Green";} else if (warna==2){color="Blue";} else {color="Yellow";}
            int number = angka;
            Card nextCard = new NumCard(color, number);
            playerHand.add(nextCard);
        }

    }
    public Card Discard(int cardOrder){
        Card discardCard = playerHand.get(cardOrder);
        playerHand.remove(discardCard);
        
        return discardCard;
    }

    public void setGiliran(boolean giliran){
        isGiliran=giliran;
    }

    public boolean isGiliran() {
        return isGiliran;
    }

    public void Draw(Deck deck){
        playerHand.add(deck.getTop());
        deck.getTop().printCard();
        deck.generate();
    }

    public String getName(){
        return name;
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

}

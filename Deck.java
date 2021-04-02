/**
 * Deck mengikuti link berikut
 * https://en.wikipedia.org/wiki/Uno_(card_game)#/media/File:UNO_cards_deck.svg
 */

import java.util.*;

public class Deck {
    private List<Card> deck = new ArrayList<Card>();
    private List<Card> discardDeck = new ArrayList<Card>();
    private final List<String> listColor = new ArrayList<String>();
    private final List<String> listAction = new ArrayList<String>();
    private final List<String> listWild = new ArrayList<String>();
    private Random random;

    public Deck(){
        random = new Random();
        createCards();
        shuffle();
    }

    private void createCards(){
        createListColor();
        createListAction();
        createListWild();
        createNumCards();
        createActionCards();
        createWildCards();
    }

    private void createListColor(){
        listColor.add("Red");
        listColor.add("Yellow");
        listColor.add("Green");
        listColor.add("Blue");
    }

    private void createListAction(){
        listAction.add("Skip");
        listAction.add("Reverse");
        listAction.add("Draw 2");
    }
    
    private void createListWild(){
        listWild.add("Wild Draw 4");
        listWild.add("Wild Color");
    }

    private void createNumCards(){
        for (String color : listColor){
            deck.add(new NumCard(color, 0));

            for (int i = 1; i <= 9; i++){
                deck.add(new NumCard(color, i));
            }
        }
    }

    private void createActionCards(){
        for (String color : listColor){
            for (String power : listAction){
                for (int i=0; i < 2; i++){
                    deck.add(new ActionCard(color, power));
                }
            }
        }
    }

    private void createWildCards(){
        for (String power : listWild){
            for (int i=0; i < 4; i++){
                deck.add(new WildCard("Black", power));
            }
        }
    }

    public void shuffle(){
        for (int i = 0; i < deck.size(); i++) {
            int x = random.nextInt(deck.size());
            int y = random.nextInt(deck.size());
            Card temp = deck.get(x);
            deck.set(x,deck.get(y));
            deck.set(y,temp);
        }
    }

    public boolean isDeckEmpty(){
        return (deck.size() == 0);
    }

    //untuk mengambil 1 kartu dari deck.
    public Card getTop() {
        if (deck.size()==0){
//            throw new Exception();
        }
        return deck.remove(0);
    }

    //kumpulan kartu yang telah dikeluarkan pemain.
    public void discardDeck(Card discardCard){
        discardDeck.add(discardCard);
    }

    public List<Card> getDiscardedDeck(){
        return discardDeck;
    }

    //membuat ulang deck dari discardedDeck saat deck.size()==0.
    public void refillDeck(){
        deck.addAll(discardDeck);
        discardDeck.clear();
        shuffle();
    }
}
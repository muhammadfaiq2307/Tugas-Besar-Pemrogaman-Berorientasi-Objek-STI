public class driver {
    public static void main(String[] args) {
        Card kartu = new NumCard("Blue", 10);
        Deck dek = new Deck();
        Card kartuSekarang = dek.getTop();
        System.out.println(kartuSekarang.getColor());
        System.out.println(kartuSekarang.getNumber());
        dek.generate();
        kartuSekarang = dek.getTop();
        System.out.println(kartuSekarang.getColor());
        System.out.println(kartuSekarang.getNumber());

        Player Faiq = new Player("Faiq");
        Faiq.showDeck();
        System.out.println("====================");
        Player Vincent = new Player("Vincent");
        Vincent.showDeck();
    }
}

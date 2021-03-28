import java.util.Random;
public class Deck {
	private Card top;
	
    public Deck(){
        top = generateAngka();
	}

	public Card getTop(){
		return top;
	}
    
    public Card generateAngka(){
        Random rand=new Random();
        int banyakAngka = 10;
        int banyakWarna = 4;
        int angka = rand.nextInt(banyakAngka);
        int warna = rand.nextInt(banyakWarna);
        String color;
        if (warna==0){color="Red";} else if (warna==1){color="Green";} else if (warna==2){color="Blue";} else {color="Yellow";}
        int number = angka;
        Card generatedCard = new NumCard(color, number);
        return generatedCard;
    }

    public Card generatePower(){
        Random rand=new Random();
        int banyakPower = 3;
        int banyakWarna = 4;
        int pow = rand.nextInt(banyakPower);
        int warna = rand.nextInt(banyakWarna);
        String power;
        String color;
        if (pow==0){power="Skip";} else if (pow==1){power="Reverse";} else {power="PlusTwo";}
        if (warna==0){color="Red";} else if (warna==1){color="Green";} else if (warna==2){color="Blue";} else {color="Yellow";}
        Card generatedCard = new PowCard(color, power);
        return generatedCard;
    }

    public Card generate(){
        Random rand=new Random();
        int jenis = rand.nextInt(2);
        if (jenis==0){return generateAngka();} else {return generatePower();}
    }
    public void newTop(){
        top = generate();
    }
}


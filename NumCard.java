//Kartu Angka

public class NumCard implements Card{
	private String color;
	private int number;
	
	public NumCard(String color, int number){
		this.color = color;
		this.number = number;
	}

	public String getColor(){
		return color;
	}

	public int getNumber(){
		return number;
	}

	public void printCard() {
		switch (color){
			case "Red":
				System.out.print(Red);break;
			case "Blue":
				System.out.print(Blue);break;
			case "Yellow":
				System.out.print(Yellow);break;
			case "Green":
				System.out.print(Green);break;
		}
		System.out.print(number);
		System.out.println(Reset);
	}
}

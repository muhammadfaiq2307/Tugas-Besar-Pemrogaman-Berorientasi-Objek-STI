//Kartu Angka

public class NumCard<Property> implements Card{
	private String color;
	private final Property number;

	public NumCard(String color, Property number){
		this.color = color;
		this.number = number;
	}
	// Temporary
	public String getPower(){
		return "";
	}

	public String getColor(){
		return color;
	}

	@Override
	public <Property> Property getProperty() {
		return (Property) number;
	}

	public void setColor(String color){
		this.color=color;
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

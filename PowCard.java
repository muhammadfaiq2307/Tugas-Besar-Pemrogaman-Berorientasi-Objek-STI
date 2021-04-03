public class PowCard<Property> implements Card{
	private String color;
	private Property power;

	public PowCard(String color, Property power){
		this.color = color;
		this.power = power;
	}

	public String getColor(){
		return color;
	}


	public void setColor(String color){
		this.color=color;
	}

	@Override
	public <Property> Property getProperty() {
		return (Property) power;
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
			case "Black":
				System.out.print(Black);break;
		}
		System.out.print(power);
		System.out.println(Reset);
	}
}
public class PowCard implements Card{
	private String color;
	private String power;

	public PowCard(String color, String power){
		this.color = color;
		this.power = power;
	}

	public String getColor(){
		return color;
	}

	public String getPower(){
		return power;
	}

	public void setColor(String color){
		this.color=color;
	}

	// Temporary
	public int getNumber(){
		return -1;
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
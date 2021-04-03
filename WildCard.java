//Wild Card, kartu tanpa syarat

public class WildCard extends PowCard{
	private String color;
	private String power;
	public WildCard(String color, String power){
		super(color, power);
		this.color=color;
		this.power=power;
	}

	public void setColor(String color){
		this.color=color;
	}

	public String getColor(){
		return color;
	}

	public String getPower(){
		return power;
	}

	@Override
	public void printCard(){
		switch (color) {
			case "Red" : System.out.print(Red);break;
			case "Blue" : System.out.print(Blue);break;
			case "Yellow" : System.out.print(Yellow);break;
			case "Green" : System.out.print(Green);break;
		}
		System.out.print(power);
		System.out.println(Reset);
	}
}
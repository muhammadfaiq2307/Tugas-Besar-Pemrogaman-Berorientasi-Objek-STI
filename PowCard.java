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
		System.out.print(power);
		System.out.println(Reset);
	}
}

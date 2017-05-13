public class Person implements Talkative // installed in default package
{
	private String name; // the Person's name
	private Dinosaur pet; // composition: a Person "has a" Dinosaur (as a pet)
	
	public Person(String name, Dinosaur pet) // Person constructor
	{
		this.name = name; // initialize instance variables
		this.pet = pet;
	}
	
	public String getName() { return name; } // "getter" method
	
	public Dinosaur getPet() { return pet; } // another getter
	
	@Override // this method defines and overrrides Talkative's public abstract Say() method
	public void Say(String message) { System.out.println("Hi, my name is " + name + ", and I say " + message); }
	
	public static void main(String[] args)
	{
		Person fred = new Person("Fred Flintstone", new Dinosaur("Dino", "Bark!"));
		// Fred's pet is the dinosaur Dino
		fred.Say("Yabba Dabba Doo");
		System.out.println(fred.getPet()); // run the Dinosaur's toString() method automatically / implicitly
		fred.getPet().Say(); // run the Dinosaur's Say() method explicitly
		
		// Note: "pet" can't be accessed directly in main because main is static but pet is an instance variable
	}
}
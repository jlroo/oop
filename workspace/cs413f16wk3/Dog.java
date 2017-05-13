public class Dog extends Animal // inheritance
{
	private String breed; // the Dog's breed, a specialization of Animal
	
	public Dog(String name, String breed) // Dog constructor
	{
		super(name); // super: use Animal's constructor to set the Dog's name
		this.breed = breed; // set instance variable breed locally
	}
	
	public static Dog createDog(String name, String breed) // a simple "factory" method
	{
		return new Dog(name, breed);
	}
	
	public String getBreed() { return breed; } // Dog-specific method
	
	@Override // Dog's Say replaces or overrides Animal's version of Say
	public void Say(String message) { System.out.println("Dog " + getName() + " says: " + message); }
	
	public static void main(String[] args)
	{
		Dog wolfhound = new Dog("Rusty", "Wolfhound");
		Animal collie = createDog("Lassie", "Collie"); // a Dog can "act as" an Animal
		collie.Say("the Animal count is now " + Animal.getCount()); // uses Dog's version of Say
		wolfhound.Say("wolfhound's name is " + wolfhound.getName()); // getName automatically inherited
		collie.Say("collie's name is " + collie.getName());
		// this won't compile: collie.Say(collie.getBreed()); // Animal doesn't have a getBreed method
		// but this will: ((Dog) collie).Say(collie.getBreed()); // tell Java collie "is" a Dog object
	}
}
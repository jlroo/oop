public class Dinosaur extends Animal // inheritance
{
	private String sound; // the sound the Dinosaur makes
	
	public Dinosaur(String name, String sound) // Dinosaur constructor
	{
		super(name); // super: use Animal's constructor to set the Dinosaur's name
		this.sound = sound; // set instance variable sound locally
	}
	
	public String getSound() { return sound; } // Dinosaur-specific method
	
	@Override // Dinosaur's Say replaces or overrides Animal's version of Say
	public void Say(String message) { System.out.println("Dinosaur " + getName() + " says: " + message); }
	
	public void Say() { System.out.println("Dinosaur " + getName() + " says: " + sound); }
	// this is an overloaded version of Say (no parameters)
	
	@Override
	public String toString() { return "This Dinosaur's name is " + getName(); }
}
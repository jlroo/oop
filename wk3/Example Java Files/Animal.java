public class Animal implements Talkative // installed in default package
{
	private static int count; // # of Animal objects created - one int count for the class
	private String name; // the Animal's name, an instance variable or field
	
	public Animal() // Animal constructor 1
	{
		this("Unknown"); // calls constructor 2 to set name and count object creations
	}
	
	public Animal(String name) // Animal constructor 2
	{
		this.name = name; // distinguish instance variable name from parameter name
		count++; // count the number of Animal object creations
	}
	
	public String getName() { return name; } // simple "getter" method
	
	public static int getCount() { return count; } // static method to return count of created Animals
	
	@Override // this method defines and overrrides Talkative's public abstract Say() method
	public void Say(String message) { System.out.println("Animal " + name + " says: " + message); }
	
	public static void main(String[] args)
	{
		Animal unknown = new Animal(); // constructor 1
		Animal frog = new Animal("Froggy"); // constructor 2
		unknown.Say("count is now " + getCount()); // call instance method Say() from object "unknown"
		unknown.Say("unknown's name is " + unknown.getName()); // call instance method getName()
		frog.Say("frog's name is " + frog.getName());
	}
}
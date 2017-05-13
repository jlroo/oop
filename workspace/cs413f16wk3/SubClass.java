public class SubClass extends SuperClass // inheritance
{
	private String name;
	
	public SubClass(String name) // SubClass constructor
	{
		this.name = name; // initialize instance variable
	}
	
	// This will not compile! There is an implicit super(); call at the beginning
	// of the SubClass constructor, but there is no SuperClass no-argument constuctor
	
	// Two possible solutions:
	// 1. add a no-argument constructor to SuperClass (called implicitly)
	// 2. better: add a super(name); call at the top of the SubClass constructor
	//    and remove instance variable name and getter getName from SubClass;
	//    the SubClass constructor can now be empty (no statements)
	
	public String getName() { return name; } // "getter" method
}
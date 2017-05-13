import java.util.*; // for Collections

public class TalkativeTest // test class for Talkative objects
{
	public static void main(String[] args)
	{
		List<Talkative> list = new ArrayList<>();
		
		list.add(new Animal()); // all of these implement Talkative
		list.add(new Dog("Rusty", "Wolfhould"));
		Dinosaur dino = new Dinosaur("Dino", "Bark!");
		list.add(dino);
		list.add(new Person("Fred", dino));
		
		for (Talkative t : list) // it's irrelevant what each object t's specific class is
			t.Say("Hi!"); // they must all have a Say() method
	}
}
public class Dog{
    
    private static int age;
    private static int hunger,tired,bored;
    private static int browniePoints;
    private static String name;
    private static String dog1 = "         ,-~~~~-,\n   .-~~~:        :~~~-.\n  !    !          !    !\n {   .^{  O    O  }^.   }\n  ^~^  { .-~~~~-. }  ^~^\n       :!        !:\n      !^._  ()  _.^!\n     !    ^~~~~^    !\n    :                :\n    {                }\n    {     }    {     }\n    {     }    {     }\n    l     l    l     l\n   { { {   }~~{   } } }\n    ^~~~~~^    ^~~~~~^\n";

    public Dog(){
	age=0;
	hunger=100;
	bored=100;
	tired=0;
	name="RUFF";
    }

    public Dog(String name){
	this();
	this.name=name;
    }

    private static void setAge(int n){
	age=n;
    }

    private static void feed(char a){
	if(a=='a'){ // fed an apple
	    hunger-=10;
	}else if(a=='b'){

	}else{

	}
    }

    public String toString(){
	String s="";
	s+=dog1+"\n";
	s+="Name: "+name+"   Age: "+age+"   Hunger: "+hunger+"   Bored: "+bored+"   Tired: "+tired;   
	return s;
    }

    public static void main(String[]args){
	Dog a = new Dog();
	System.out.println(a);
    }

}
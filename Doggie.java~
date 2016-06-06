import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;
import java.io.*;

public class Doggie{

    private static int days,hours;
    private static int hunger,tired,bored;
    private static int browniePoints;
    private static String name;
    private static String dog1 = "         ,-~~~~-,\n   .-~~~:        :~~~-.\n  !    !          !    !\n {   .^{  O    O  }^.   }\n  ^~^  { .-~~~~-. }  ^~^\n       :!        !:\n      !^._  ()  _.^!\n     !    ^~~~~^    !\n    :                :\n    {                }\n    {     }    {     }\n    {     }    {     }\n    l     l    l     l\n   { { {   }~~{   } } }\n    ^~~~~~^    ^~~~~~^\n";
    private static String dog2 = "    ___\n __/o |`.  ..---.   )\n |_,  |_|-'  /   )-^*\n    )        |  ((\n ___Y  ,    .-7 /|\n(_,___/...-- (_/_/\n";

    public Doggie(){
        days=0;
	hours=0;
	hunger=100;
	bored=100;
	tired=0;
	name="";
    }

    private static void intro(){
	System.out.println("\nWELCOME TO DOGGIE DAY CARE.\n");
	System.out.println(dog1);
	wait(1700);
	System.out.println("A very simple task has been bestowed upon you:");
	wait(2500);
	System.out.println("You must raise this fine pup. \n");
	//wait(2000);
	wait(3200);
	System.out.println("Well, first things first...");
	wait(1800);
	System.out.println("What will you name this little lad? (please enter a name)\n");
	nameDog();	
	System.out.println("Aw, look at little "+name+". How adorable!\n");
	wait(1600);
	System.out.println(dog2);
	System.out.println();
	wait(2200);
	System.out.println("Now, I probably don't need to tell you this, but puppies require a lot of care and attention -- kind of like how you do.\n");
	wait(3750);
	System.out.println("You will need to keep "+name+" fed, rested, and entertained for the next week to ensure that you are capable of permanently caring for "+name+".\n");
	wait(3700);
	System.out.println("The day count can be found in the stats bar below "+name+" on the screen.\n");
	wait(3100);
	System.out.println("You will be prompted to perform actions every 3 simulated hours for seven days.\n");
	wait(3300);
	System.out.println("Be cautious, though. If "+name+"gets too bored, it may run away, which is game over for you.");
	wait(3500);
	System.out.println("Similarly, if "+name+" gets too hungry or tired, NAPS (Nikoahla Animal Protective Services) will take "+name+" away, and of course, that too, means game over.");
	wait(4500);
	System.out.println();
    }

    private static void care(){
	while(days<=7){



	}
    }

    private static void nameDog(){
	Scanner in = new Scanner(System.in);
	String n = in.nextLine();
	if(n.length()<=1){
	    System.out.println("That name is too short. Please enter a different name.");
	    nameDog();
	}else if(n.length()>20){
	    System.out.println("That name is too long. Please enter a different name.");
	}else{
	    name = n.toUpperCase();
	    System.out.println();
	}
    }

    private static void setDays(int n){
	days=n;
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
	s+="Name: "+name+"   Days: "+days+" days   Hours: "+hours+" hours   Hunger: "+hunger+"   Bored: "+bored+"   Tired: "+tired;   
	return s;
    }

    private static void wait(int millis){
        try {
            Thread.sleep(millis);
        }
        catch (InterruptedException e) {
        }
    }

    public static void main(String[]args){
	Doggie d = new Doggie();
	intro();
    }

}

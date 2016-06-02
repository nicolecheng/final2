import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;
import java.io.*;

public class Doggie{

    private static int years;
    private static boolean hungry,thirsty;
    private static boolean bored,tired;
    private static String dog,dog2,name;
    
    public Doggie(){
	years=0;
	hungry=true;
	thirsty=true;
	bored=true;
	tired=false;
	dog="\nPRETEND THERE IS A DOG HERE\n";

	name="";	
    }

    private static void intro(){
	System.out.println("\nWELCOME TO DOGGIE DAY CARE.\n");
	wait(2000);
	System.out.println("A very simple task has been bestowed upon you:");
	wait(2200);
	System.out.println("you must raise this fine _____ pup. \n");
	wait(2200);
	System.out.println("Well, first things first...");
	wait(1800);
	System.out.println("What will you name this little lad? (please enter a name)\n");
	nameDog();	
	System.out.println("Aw, look -- it's "+name+". How adorable!\n");
	wait(1500);
	System.out.println(dog);
	System.out.println();
	System.out.println();
	System.out.println();
	System.out.println();
	System.out.println();
	System.out.println();
	System.out.println();
	System.out.println();
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
	    name = n;
	    System.out.println();
	}
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

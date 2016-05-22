import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;
import java.io.*;

public class Tutorial{

    private static boolean testMode;// = true; // debug mode?

    static String name;
  
    public static void main(String [] args){
	//intro
	String s;
	System.out.println("Welcome to That 80s Game. Please enter your name to begin.");
	Scanner enterName = new Scanner(System.in);
	name = enterName.nextLine();
	System.out.println();
	System.out.println("OK, "+name+", let's get into this.");
	System.out.println();
	System.out.println("The rules are simple: you are striving to become to the coolest of the cool.");
	wait(2300);
	System.out.println("What better way to do that than to dominate in every retro game?");
	wait(2500);
	System.out.println("I hope you're ready to begin your adventure... because it's already begun.");
	System.out.println();
	wait(3000);
	System.out.println("You're skateboarding down the street and you're getting thirsty.");
	wait(2500);
	System.out.println("Do you go to the diner, the deli, or to the questionable water fountain at the end of the block?");
	Scanner in = new Scanner(System.in);
	s = in.nextLine();
	getWater(s);
	System.out.println("The End!");
	// output stats?
    }
  
    static boolean[]places = {false, false, false}; // [diner, deli, fountain] keeps track of where the user has gone
    private static void getWater(String s){
	System.out.println();
	System.out.println();
	if (s.toLowerCase().contains("diner")){ //the diner
	    if(!places[0]){
		places[0]=true; // mark that user has gone to the diner
		System.out.println("You walk into the diner and get distracted by the food around you, so you sit down for a stack of flapjacks.");
		wait(2500);
		System.out.println("You totally forget to get water. Nice.");
	    }else{ // has already been here
	        System.out.println("You've already been here. AWKWARD.");
	    }
	    wait(2000);
	    if(!places[2]){//if user has not yet been to water fountain
		System.out.println("Welp, you're back outside and you're still in dire need of a drink -- the deli or the water fountain?");	
	    }else{
		System.out.println("Alright, where to now? (Psst the only place left to go is the deli)");
	    }
	    Scanner in = new Scanner(System.in);
	    String n = in.nextLine();
	    getWater(n);
	}else if (s.toLowerCase().contains("deli")){ //the deli
	    places[1]=true;
	    System.out.println("You enter the deli, and lo and behold: there is a wall of beautifully chilled bottles of water.");
	    wait(3000);
	    System.out.println("You purchase a bottle, quench yourself, and all is good in the world once again.");
	    wait(3000);
	    System.out.println("But what is that you see in the corner?");
	    wait(2500);
	    System.out.println("It's an arcade machine! You walk over and see that it's a Snake game.");
	    wait(3400);
	    System.out.println("The high score is only a 20, currently being held by someone by the alias, Noah Constricter.");
	    wait(3000);
	    System.out.println("Easy peasy, right? You're going to take this fella down. Are you ready? (y/n)");
	    Scanner in = new Scanner(System.in);
	    String n = in.nextLine();
	    if(n.toLowerCase().contains("y")){
		System.out.println();
		wait(500);
		System.out.println("Awesome. Good luck.");
	        wait(1500);
		System.out.println();
	    }else{
		System.out.println();
		wait(500);
		System.out.println("Too bad. You're going to play anyway.");
	        wait(1500);
		System.out.println();
	    }
	    // activate snake
	    Snake.snakeGame();
	}else if(s.toLowerCase().contains("fountain")){ //questionable water fountain
	    if(!places[2]){
		places[2]=true; // Tutorial.places[2] = true;
		System.out.println("You walk over to the water fountain, and see that years of neglect has left it covered in an inch of grime.");
		wait(2000);
		System.out.println("You slowly back away and decide to get water somewhere else.");
	    }else{
		System.out.println("Are you insane? No. Don't go back to that horror show of a water fountain.");
	    }
	    wait(2500);
	    if(!places[0]){
		System.out.println("OK, the diner or the deli?");
	    }else{
		System.out.println("Well, now where do you wanna go? (perhaps it's time to go to the deli)");
	    }
	    Scanner in = new Scanner(System.in);
	    String n = in.nextLine();
	    getWater(n);
	}else{
	    System.out.println("I'm sorry, what was that?");
	    Scanner in = new Scanner(System.in);
	    String n = in.nextLine();
	    getWater(n);
	}
    }

    private static void wait(int millis){
	if(!testMode){ // only wait if not on testMode
	    try {
		Thread.sleep(millis);
	    }
	    catch (InterruptedException e) {
	    }
	}
    }

}

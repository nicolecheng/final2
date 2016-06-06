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
    private static String dog2 = "    ___\n __/o |`.  ..---.   )\n |_,  |_|-'  /   )-^\n    )        |  ((\n ___Y  ,    .-7 /|\n(_,___/...-- (_/_/\n";
    private static String[]mysteryBasket;
    private static String[][]foods;

    public Doggie(){
        days=0;
	hours=0;
	hunger=100;
	bored=100;
	tired=50;
	name="";
	foods=new String[5][2];//foods;
	foods[0][0]="corn";
	foods[0][1]="corndog";
	foods[1][0]="waffles";
	foods[1][1]="woofle";
	foods[2][0]="popsicles";
	foods[2][1]="pupsicle";
	foods[3][0]="poached eggs";
	foods[3][1]="pooched egg";
	foods[4][0]="popcorn";
	foods[4][1]="pupcorn";
	mysteryBasket=new String[5];
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
	System.out.println("You will be prompted to perform actions every three simulated hours for seven simulated days.\n");
	wait(3300);
	System.out.println("Be cautious, though. If "+name+" gets too bored, "+name+" may run away, which is game over for you.\n");
	wait(3500);
	System.out.println("Similarly, if "+name+" gets too hungry or tired, NAPS (Nikoahla Animal Protective Services) will take "+name+" away, and of course, that too, means game over.");
	wait(4500);

	mysteryBasket[0]="You take "+name+" on a walk through the park.";
	mysteryBasket[1]="You start a game of tug-o-war with "+name+", but you accidentally take it a little bit too far...";
	mysteryBasket[2]="You find a time machine somehow... You get to fast forward a day into this tedious trial!";
	mysteryBasket[3]="You put on some of your favorite music. Unfortunately, "+name+" didn't seem to enjoy it at all.";
	mysteryBasket[4]="Hm, "+name+" got into the fridge somehow. "+name+" drinks some mystery liquid.";
	
	begin();
        care();
	
    }

    private static void begin(){
	System.out.println("\nGot it? (y/n)");
	Scanner in = new Scanner(System.in);
	String n = in.nextLine();
	if(n.contains("y")){
	    
	}else{
	    begin();
	}
    }
	    

    private static void care(){
	while(days<=0){//7){

	    if(tired>200){
		System.out.println("\n"+name+" faints from exhaustion. NAPS takes "+name+" away from you.");
		wait(3000);
		System.out.println("You could've done better, and you should've done better.\n");
		wait(1500);
		pupDown();
	    }else if(hunger>200){
		System.out.println("\n"+name+" is starving. NAPS takes "+name+" away from you for this intolerable negligence.");
		wait(3100);
		System.out.println("You should feel ashamed.\n");
		wait(1500);
		pupDown();
	    }else if(bored>200){
		System.out.println("\n"+name+" is so bored that NAPS could sense it. NAPS takes "+name+" away from you.");
		wait(3000);
		System.out.println(name+" needed your love and attention, but you failed to provide...\n");
		wait(1500);
		pupDown();
	    }
	
	    System.out.println(dog1);
	    System.out.println("\nName: "+name+"   Days: "+days+" days   Hours: "+hours+" hours   Hunger: "+hunger+"   Bored: "+bored+"   Tired: "+tired);
	    wait(2000);
	    if(hours%3==0){
		System.out.println();
		instruct();
	    }else{
		System.out.println();
		wait(2000);
		System.out.println(".");
		wait(2000);
		System.out.println(".");
		wait(2000);
		System.out.println(".");
		wait(2000);
		bored+=10;
		tired+=10;
		hunger+=10;
		hours+=1;
	    }
	    if(hours%24==0){
		days++;
	    }
	}
	
	if(days>0){//7){
	    System.out.println();
	    System.out.println();
	    System.out.println("Congratulations! You did it!");
	    wait(2200);
	    System.out.println();
	    System.out.println(name+" is yours. No need to walk on eggshells now. PHEW.\n");
	    wait(2700);
	}
    }

    private static void instruct(){
	System.out.println("What would you like to do?");
	System.out.println();
	System.out.println("1) feed "+name+"\n2) play with "+name+"\n3) tuck "+name+" into bed\n4) mystery option (CAREFUL WITH THIS ONE!)\n");
	Scanner in = new Scanner(System.in);
	String n = in.nextLine();
	if(n.contains("1") || n.contains("feed")){
	    if(feed()){
		bored+=20;
		tired+=20;
		hours+=1;
	    }
	}else if(n.contains("2") || n.contains("play")){
	    if(play()){
		hunger+=30;
		tired+=40;
		hours+=1;
	    }
	}else if(n.contains("3") || n.contains("tuck") || n.contains("sleep")){
	    if(tuck()){
		hunger+=30;
		hours+=1;
	    }
	}else if(n.contains("4") || n.contains("mystery")){
	    if(mystery()){
		hours+=1;
	    }
	}else{
	    System.out.println("Sorry, what was that?\n");
	    wait(1500);
	    instruct();
	}
	

    }

    private static boolean feed(){
	wait(1400);
	System.out.println();
	if(hunger<=0){
	    System.out.println("Chill. "+name+" doesn't get hungry THAT often...");
	    return false;
	}else{
	    System.out.println("What would you like to feed your puppy pal?\n");
	    System.out.println("1) kibble\t2) puppy chow\t3) steak\t4) random (again, careful)");
	    Scanner in = new Scanner(System.in);
	    String n = in.nextLine();
	    System.out.println();
	    if(n.contains("1") || n.contains("kibble")){
		System.out.println(name+" nibbles on the kibble. Delicious.");
		if(hunger>=30){
		    hunger-=30;
		}else{
		    hunger=0;
		}
	    }else if(n.contains("2") || n.contains("chow")){
		System.out.println(name+" chows down on the chow. Yum.");
		if(hunger>=40){
		    hunger-=40;
		}else{
		    hunger=0;
		}
	    }else if(n.contains("3") || n.contains("steak")){
		System.out.println(name+" is overjoyed with this meal, reassuring you that feeding your dog a gourmet steak was not a mis-steak.");
		if(hunger>=50){
		    hunger-=50;
		}else{
		    hunger=0;
		}
	    }else if(n.contains("4") || n.contains("random")){
		int i = (int)(Math.random()*5);
		System.out.println("You decide to feed "+name+" some "+foods[i][0]+".\n");
		wait(2700);
		System.out.println("Oh, look: "+name+" turned into a "+foods[i][1]+".\n");
		wait(2700);
		System.out.println("That's unfortunate.\n");
		if(hunger>=20){
		    hunger-=20;
		}else{
		    hunger=0;
		}
	    }else{
		System.out.println("Sorry, what was that?");
		wait(1000);
		feed();
	    }
	    System.out.println();
	    wait(3500);
	    return true;
	}
    }

    private static boolean play(){
	wait(1800);
	if(bored<=0){
	    System.out.println("Um, "+name+" isn't really in the mood to play.");
	    return false;
	}else{
	    System.out.println("You play some fetch with "+name+". What fun!");
	    if(bored>=30){
		bored-=30;
	    }else{
		bored=0;
	    }
	    return true;
	}
    }

    private static boolean tuck(){
	if(tired<=0){
	    System.out.println("No. "+name+" refuses to go to bed. "+name+" is not tired at all.");
	    return false;
	}else{
	    wait(1800);
	    System.out.println("\n"+name+" took a very nice nap! \n");
	    if(tired>=60){
		tired-=60;
	    }else{
		tired=0;
	    }
	    return true;
	}
    }

    private static boolean mystery(){
	wait(1800);
	int i = (int)(Math.random()*5);
	if(i==0){
	    System.out.println();
	    System.out.println(mysteryBasket[0]);
	    System.out.println();
	    wait(2300);
	    System.out.println(name+" has a great time!");
	    wait(1900);
	    if(bored>=50){
		bored-=50;
	    }else{
		bored=0;
	    }
	}else if(i==1){
	    System.out.println();
	    System.out.println(mysteryBasket[1]);
	    System.out.println();
	    wait(3000);
	    System.out.println(name+" is not happy with you at all. "+name+" begins to bark and cry uncontrollably.\n");
	    wait(3000);
	    System.out.println("NAPS is here to pick "+name+" up. Say good-bye forever... :'(");
	    wait(2800);
	    pupDown();
	}else if(i==2){
	    System.out.println();
	    System.out.println(mysteryBasket[2]);
	    System.out.println();
	    wait(3000);
	    days++;
	    hours+=24;
	    System.out.println(dog1);
	    System.out.println("\nName: "+name+"   Days: "+days+" days   Hours: "+hours+" hours   Hunger: "+hunger+"   Bored: "+bored+"   Tired: "+tired);
      	    System.out.println();
	    wait(2300);
	}else if(i==3){
	    System.out.println();
	    System.out.println(mysteryBasket[3]);
	    System.out.println();
	    wait(3000);
	    System.out.println("Well, nice going. The neighbors have called NAPS for dog abuse.\n");
	    wait(2500);
	    System.out.println("Bid farewell to "+name+".\n");
	    wait(2200);
	    pupDown();
	}else if(i==4){
	    System.out.println();
	    System.out.println(mysteryBasket[4]);
	    System.out.println();
	    wait(3000);
	    System.out.println("Nice! This was a temporary cure-all potion! "+name+" is now in the most amazing state.\n");
	    hunger=0;
	    bored=0;
	    tired=0;
	    System.out.println(dog1);
	    System.out.println("\nName: "+name+"   Days: "+days+" days   Hours: "+hours+" hours   Hunger: "+hunger+"   Bored: "+bored+"   Tired: "+tired);
	    System.out.println();
	    wait(2000);
	}
	return true;
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

    private static void pupDown(){
	System.out.println();
	System.out.println("Looks like you didn't make it through the trial...\n");
	wait(2000);
	System.out.println("Ready to give it another shot? (y)");
	Scanner in = new Scanner(System.in);
	String n = in.nextLine();
	if(n.contains("y")){
	    System.out.println();
	    System.out.println("Alright! Let's do this!\n\n");
	    wait(2500);
	    doggieDayCare();
	}else{
	    pupDown();
	}
    }

    public String toString(){
	String s="";
	s+=dog1+"\n";
	s+="Name: "+name+"   Days: "+days+" days   Hours: "+hours+" hours   Hunger: "+hunger+"   Bored: "+bored+"   Tired: "+tired;   
	return s;
    }

    public static boolean real = true;

    private static void wait(int millis){
	if(real){
	    try {
		Thread.sleep(millis);
	    }
	    catch (InterruptedException e) {
	    }
	}
    }
    
    public static void doggieDayCare(){
	Doggie d = new Doggie();
	intro();
    }

    /*
    public static void main(String[]args){
	Doggie d = new Doggie();
	intro();
    }
    */
    
}

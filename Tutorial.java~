import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;
import java.io.*;

public class Tutorial{

    //private static boolean testMode = false; // with waits or nah?

    static String name, snakeName, dinoName, pacmanName;
    static int snakeScore, dinoScore, pacmanScore, pongScore;
  
    public static void main(String [] args){
	//intro
	String s;
	System.out.println("Welcome to That 80s Game. Please enter your name to begin.");
	Scanner enterName = new Scanner(System.in);
	name = enterName.nextLine();
	System.out.println();
	System.out.println("OK, "+name+", let's get into this.");
	System.out.println();
	wait(1500);
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
	//System.out.println("The End!");
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
	    System.out.println("The high score is only a 7, currently being held by someone by the alias, Noah Constricter.");
	    wait(3000);
	    System.out.println("Easy peasy, right? You're going to take this fella down.");
	    wait(2800);
	    System.out.println("Use the A-W-S-D keys to start and turn, and submit your moves one square prior. Are you ready? (y/n)");
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
	    playSnake();
	}else if(s.toLowerCase().contains("fountain")){ //questionable water fountain
	    if(!places[2]){
		places[2]=true; // Tutorial.places[2] = true;
		System.out.println("You walk over to the water fountain, and see that years of neglect have left it covered in an inch of grime.");
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

    private static void playSnake(){
	snakeScore = Snake.snakeGame();
	if(snakeScore > 7){// || testMode){
	    scene2();
	}else{
	    System.out.println("You didn't beat the high score. Please try again.");
	    System.out.println();
	    wait(2800);
	    System.out.println("Ready to go again? (y)");
	    Scanner in = new Scanner(System.in);
	    String n = in.nextLine();
	    if(n.toLowerCase().contains("y")){
		System.out.println("Rad. Let's begin.");
		wait(3100);
	    }else{
		System.out.println("You have 3200 ms to make yourself ready.");
		wait(3200);
	    }
	    playSnake();
	}
    }

    private static void snakeName(){
	System.out.println("Please enter a nickname to record this high score.");
	Scanner in = new Scanner(System.in);
	snakeName = in.nextLine();
	// output data
	System.out.println();
	System.out.println();
	String spacing = "";
	if(snakeName.length()>25){
	    System.out.println("That nickname has too many characters.");
	    wait(1700);
	    snakeName();
	}else{
	    for(int i = 0; i < 25-snakeName.length(); i++){
		spacing+=" ";
	    }
	    spacing+="   ";
	    System.out.println("1. "+snakeName+spacing+"HI: "+snakeScore);//.substring(2)+spacing+"HI: "+snakeScore);
	    wait(2000);
	    System.out.println("2. Noah Constricter          HI: 7");
	    wait(2000);
	    System.out.println("3. MyAnacondaDo              HI: 6");
	    wait(2000);
	}
    }

    private static void scene2(){
	wait(2000);
	System.out.println();
	System.out.println("Congratulations, you have defeated the Noah Constricter.");
	System.out.println();
	wait(2200);
	snakeName();
	System.out.println("One game down...");
	wait(1000);
	System.out.println(".");
	wait(1000);
	System.out.println(".");
	wait(1000);
	System.out.println(".");
	wait(1200);
	System.out.println("RRRRIIIIIINNNNGGGGGGGGGGG");
	wait(2000);
	System.out.println("RRRRIIIIIINNNNGGGGGGGGGGG");
	wait(2000);
	System.out.println();
	System.out.println("It looks like someone's calling you.");
	wait(1800);
	System.out.println("The number is private...");
	wait(1800);
	System.out.println("Do you pick up? (y/n)");
	Scanner in = new Scanner(System.in);
	String n = in.nextLine();
	if(n.toLowerCase().contains("y")){
	    System.out.println("You pick up the phone and slowly bring the phone to your ear.");
	    wait(2000);
	    System.out.println();
	    System.out.println("A deep scratchy voice begins speaking.");
	    System.out.println();
	    wait(2000);
	    System.out.println("Hello there, "+name+". My name is Mr. Igayim, and I want to help you on your journey.");
	    wait(3800);
	    System.out.println("I want to offer you some advice. Talk less -- smile more. Don't let them know what you're against or what you're for.");
	    wait(4000);
	    System.out.println("OK, but seriously. Go down to the old arcade down by the pier. There are tons of games for you there.");
	    wait(3500);
	    System.out.println("Good luck, young grasshopper.");
	    System.out.println();
	    wait(2000);
	    System.out.println("BEEEEEEEEP");
	    System.out.println();
	    wait(2000);
	    System.out.println("Mr. Igayim hung up on you before you got to respond. Hmph. Rude.");
	}else if(n.toLowerCase().contains("n")){
	    System.out.println("You press the reject button.");
	    wait(2000);
	    System.out.println("The phone immediately short-circuits and you drop it on the floor.");
	    wait(2200);
	    System.out.println("It would've shattered, but luckily, you have been equipped with a Nokia brick.");
	    wait(3000);
	    System.out.println("Instead, a slip of paper falls out with the following words:");
	    System.out.println();
	    wait(2200);
	    System.out.println("Go down to the old arcade by the pier. This is crucial.");
	}else{
	    System.out.println("You hear a large boom, the lights go off, and the ringing stops.");
	    System.out.println();
	    wait(2200);
	    System.out.println("Go down to the pier and enter the old arcade. NOW.");
	}
	System.out.println();
	System.out.println();
	wait(3000);
	System.out.println("Oh gosh. This is weird... and kind of freaky, too.");
	wait(2500);
	System.out.println("Soooooo... It's time to make a decision. Do you go down to the pier? (y/n)");
	n = in.nextLine();
	pier(n);
    }

    private static void pier(String s){
	System.out.println();
	if(s.toLowerCase().contains("y")){
	    System.out.println("You walk down to the pier, and you see a figure in a trench coat.");
	    wait(3200);
	    System.out.println("You approach this strange person and he flashes you a smile.");
	    System.out.println();
	    wait(2900);
	    System.out.println("Why, hello, little "+name+". My name is Pierre.");
	    wait(2800);
	    System.out.println("Pierre on a pier. Pretty clever, aren't I?");
	    wait(2700);
	    System.out.println("OK sorry that was uncalled for.");
	    wait(2600);
	    System.out.println("Anyway, I'm glad you decided to join me. I have the oasis you need to become to very best, like no one ever was.");
	    wait(3700);
	    System.out.println("To get you here was my real test, but to train you is my cause.");
	    wait(3500);
	    System.out.println("Here's the key to my arcade. It's been closed off to the general public for years. Only people I deem worthy are allowed to access this paradise of games.");
	    System.out.println();
	    wait(4400);
	    System.out.println("He tosses you the key for the arcade and walks away. You dare not speak to or approach him.");
	    System.out.println();
	    wait(3800);
	    System.out.println("You go to the arcade, because you are such a brave soul. You unlock it and walk in. The place is lined with tacky neon lights and reeks of pizza and BO. Gross.");
	    wait(5000);
	    System.out.println("Nonetheless, Pierre and Mr. Igayim were right: it had every game imaginable. SCORE.");
	    wait(3700);
	    //}else if(s.toLowerCase().contains("n")){
	}else{
	    System.out.println("You don't go to the pier, but this mention of the arcade has reminded you of your immense love of those bulky machines and their monotonous beeps and tones.");
	    wait(5100);
	    System.out.println("You decide to go down the block to go to your favorite arcade: Flynn's");
	    wait(3700);
	    System.out.println("You walk in, and it's just how you remembered -- perfect.");
	    wait(3200);
	}
	System.out.println();
	System.out.println("You immediately see that DinoRun, your favorite game, is not occupied. You rush over to get your hands on that beauty.");
	wait(3900);
	System.out.println();
	System.out.println("PLEASE ENTER 2 TOKENS TO BEGIN.");
	System.out.println();
	wait(3500);
	System.out.println("Drat. You don't have any tokens, and you really don't feel like getting any.");
	troll();
    }

    private static void troll(){
	System.out.println();
	wait(3900);
	System.out.println("PSSSSSSST. Over here.");
	System.out.println();
	wait(2200);
	System.out.println("You see what appears to be an arcade troll crouching under a gross table.");
	wait(2900);
	System.out.println("You reluctantly approach him.");
	System.out.println();
	wait(2200);
	System.out.println("Hello. I see that you are fresh out of tokens. Luckily for you, I have a proposition for you...");
	wait(3900);
	System.out.println("Answer 2 of these 4 questions correctly, and I will give you 10 beautiful tokens.");
	wait(3000);
	System.out.println();
	questions();
    }

    private static void questions(){
	int qScore = 0;
	//questions
	String[] door0 = {"If I drink, I die. If I eat, I'm fine. What am I?","What gets more wet the more it dries?","What's brown and sticky?","What has a face and two hands but no arms or legs?"};
	String[] door1 = {"Poor people have it, rich people need it, if you eat it you die. What is it?","What goes up and does not come back down?","Brothers and sisters I have none, but this man's father is my father's son. Who is this man?","A man walks into the bar, goes up to the bartender, the bartender scares him, the man says thank you and walks out. What happened?" };
	String[] door2 = {"Where is most of the world's fresh water located?","What is the capital of Canada?","What's the capital of California?","What is the capital of Australia?"};
	String[] door3 = {"What is converted into alcohol during brewing?","In which city was Martin Luther King assasinated?","What day of the week are you most likely to have a heart attack?","What do you call an infant whale?"};
	//answers
	String[] door0ans = {"fire","towel","stick","clock"};
	String[] door1ans = {"nothing","age","my son","hiccup"};
	String[] door2ans = {"antarctica","ottowa","sacramento","canberra"};
	String[] door3ans = {"sugar","memphis","monday","calf"};
	//randomly choose which riddle
	int num0 = (int) (Math.random()*4);
	System.out.println(door0[num0]);
	System.out.println();
	Scanner in = new Scanner(System.in);
	String d0 = in.nextLine();
	System.out.println();
	wait(2000);
	if (d0.toLowerCase().contains(door0ans[num0])){
	    System.out.println("Correct!");
	    qScore++;
	}else{
	    System.out.println("Incorrect! The correct answer was a" + door0ans[num0]);
	}
	System.out.println();
	wait(2000);
	int num1 = (int) (Math.random()*4);
	System.out.println(door1[num1]);
	System.out.println();
	in = new Scanner(System.in);
	String d1 = in.nextLine();
	System.out.println();
	wait(2000);
	if (d1.toLowerCase().contains(door1ans[num1])){
	    System.out.println("Correct!");
	    qScore++;
	}else{
	    System.out.println("Incorrect!  The correct answer was " + door1ans[num1]);
	}
	System.out.println();
	wait(2000);
	int num2 = (int) (Math.random() * 4);
	System.out.println(door2[num2]);
	System.out.println();
	in = new Scanner(System.in);
	String d2 = in.nextLine();
	System.out.println();
	wait(2000);
	if (d2.toLowerCase().contains(door2ans[num2])){
	    System.out.println("Correct!");
	    qScore++;
	}else{
	    System.out.println("Incorrect!  The correct answer was " + door2ans[num2]);
	}
	System.out.println();
	wait(2000);
	int num3 = (int) (Math.random() * 4);
	System.out.println(door3[num3]);
	System.out.println();
	String d3 = in.nextLine();
	System.out.println();
	wait(2000);
	if (d3.toLowerCase().contains(door3ans[num3])){
	    System.out.println("Correct!");
	    qScore++;
	}else{
	    System.out.println("Incorrect!  The correct answer was " + door3ans[num3]);
	}
	System.out.println();
	wait(2600);
	if (qScore >= 2){
	    System.out.println("Congratulations! You won! You got " + qScore + " out of the 4 correct!");
	    System.out.println();
	    wait(3300);
	    System.out.println("You now have tokens to play some arcade games!");
	    System.out.println();
	    wait(3300);	    
	    System.out.println("Finally, you and Dino Run can have some alone time.");
	    wait(3300);
	    System.out.println("Oh, look at that. Tyrannicole has a sorry-looking high score of 10.");
	    wait(3600);
	    System.out.println("You can do so much better.");
	    wait(2800);
	    System.out.println("Ready? (y)");
	    Scanner inn = new Scanner(System.in);
	    String n = inn.nextLine();
	    if(n.toLowerCase().contains("y")){
		System.out.println();
		wait(500);
		System.out.println("Awesome. Good luck.");
		wait(1500);
		System.out.println();
	    }else{
		System.out.println();
		wait(500);
		System.out.println("Meh. Oh well.");
		wait(1500);
		System.out.println();
	    }
	    playDino();
	}else{
	    System.out.println("You lost! Try again.");
	    wait(2500);
	    System.out.println();
	    questions();
	}
    }

    private static void playDino(){
	dinoScore = Platformer.dino();
	if(dinoScore > 10){
	    scene3();
	}else{
	    System.out.println("Oof, sorry, you didn't beat the high score. Please try again.");
	    System.out.println();
	    wait(2700);
	    playDino();
	}
    }

    private static void scene3(){
	System.out.println();
	System.out.println("Nice. You beat Tyrannicole.");
	System.out.println();
	wait(3000);
	dinoName();
	System.out.println();
	wait(1500);
	// cont with scene
	System.out.println("ALRIGGHHHT. Nice. You beat the high!\n");
	wait(2200);
	System.out.println("You look around, and see another one of your absolute favorite games: PACMAN!\n");
	wait(3000);
	System.out.println("You rush over, and this time, you actually have tokens.");
	wait(3000);
	System.out.println("The high score right now is a 100, held by Mr. PaKman"); //************************************************
	wait(2600);
	System.out.println("Yeah, you can beat that.");
	wait(2000);
	pacmanGame();
    }

     private static void dinoName(){
	System.out.println("Please enter a nickname to record this high score.");
	Scanner in = new Scanner(System.in);
	dinoName = in.nextLine();
	System.out.println();
	System.out.println();
	String spacing = "";
	if(dinoName.length()>25){
	    System.out.println("That nickname has too many characters.");
	    wait(1700);
	    dinoName();
	}else{
	    for(int i = 0; i < 25-dinoName.length(); i++){
		spacing+=" ";
	    }
	    spacing+=" ";
	    System.out.println("1. "+dinoName+spacing+"HI: "+dinoScore);
	    wait(2000);
	    System.out.println("2. Tyrannicole               HI: 10");
	    wait(2000);
	    System.out.println("3. TexMex                    HI: 9");
	    wait(2000);
	}
    }

    private static void pacmanName(){
	System.out.println("Please enter a nickname to record this high score.");
	Scanner in = new Scanner(System.in);
	pacmanName = in.nextLine();
	// output data
	System.out.println();
	System.out.println();
	String spacing = "";
	if(pacmanName.length()>25){
	    System.out.println("That nickname has too many characters.");
	    wait(1700);
	    pacmanName();
	}else{
	    for(int i = 0; i < 25-pacmanName.length(); i++){
		spacing+=" ";
	    }
	    spacing+=" ";
	    System.out.println("1. "+pacmanName+spacing+"HI: "+pacmanScore);
	    wait(2000);
	    System.out.println("2. Mr. PaKman                HI: 100"); 
	    wait(2000);
	    System.out.println("3. PackYourBags              HI: 99"); //******CHANGE NAMES******************************************************
	    wait(2000);
	}
    }

    
    private static void pacmanGame(){
	System.out.println("Are you ready? <y>");
	Scanner in = new Scanner(System.in);
	String n = in.nextLine();
	if(n.toLowerCase().contains("y")){
	    System.out.println("Great. Let's begin.");
	    wait(1900);
	    Pacman p = new Pacman();
	    pacmanScore = p.pacman();
	    //insert pacman game here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!****************************************************************
	    if(pacmanScore>100){
		pacmanName();
		scene4();
	    }else{
	    System.out.println("\nShucks, you did not beat the high score. Try again.\n");
	    wait(2000);
	    pacmanGame();
	    }
	}else{
	    pacmanGame();
	}
    }

    private static void scene4(){
	System.out.println();
	System.out.println("You realize how much time you've wasted, so you try to scurry out of the arcade before you're stuck there forever.");
	wait(4000);
	System.out.println("On your way out, you see <INSERT PONG NAME> in front of the entrance.\n"); //****************************************************
	wait(3000);
	System.out.println("Drat. You really hate this guy...\n");
	wait(2200);
	System.out.println("He challenges you to a game of ping pong, and  you know that in order to protect your title, you had to accept the challenge.\n");
	wait(4000);
	pongGame();
    }

     private static void pongGame(){
	System.out.println("Are you ready to play? <y>");
	Scanner in = new Scanner(System.in);
	String n = in.nextLine();
	if(n.toLowerCase().contains("y")){
	    System.out.println("Great. Let's begin.");
	    wait(1900);
	    //insert pong game here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!****************************************************************
	    Pong po = new Pong(0);
	    pongScore = po.pong();
	    if(pongScore==5){
		System.out.println("\nWOOHOO you beat him!");
		wait(1500);
		System.out.println("He walks away in shame, as you are instantly relieved.");
		wait(2000);
		scene5();
	    }else{
		System.out.println("\nShucks, you did not beat him. Rematch time.\n");
		wait(2000);
		pongGame();
	    }
	}else{
	    pongGame();
	}
    }

    private static void scene5(){
	System.out.println();
	System.out.println("You finallly get home, but you see a box with a letter attached to it.");
	wait(2800);
	System.out.println("Being the type of person you are, you open the box before reading the letter.");
	wait(3000);
	System.out.println("In it, you find a puppy... Weird. This isn't the box of Insomnia Cookies that you ordered...");
	wait(3100);
	System.out.println();
	System.out.println("OK, now that you're utterly confused (and quite concerned), you open up the letter and begin reading.\n");
	wait(3400);
	Doggie.doggieDayCare();
	System.out.println();
	closing();
    }

    private static void closing(){
	System.out.println("AH, congratulations, "+name+". You have completed this adventure with flying colors.");
	wait(2800);
	System.out.println("Nikoahla would like to express our kudos.");
	wait(2600);
	System.out.println("\nAnyway, now that you have beaten all of these high scores, become ping-pong champion, and nurtured a puppy...");
	wait(3000);
	System.out.println("We are proud to declare you coolest of the cool!!");
	wait(2600);
	System.out.println("\nYou have completed what you set our to do, which is admirable.");
	wait(2700);
	System.out.println("\nWe hope you enjoyed this adventure as much as we enjoyed crafting it!");
	wait(2800);
	System.out.println("\n'til next time, "+name+"! :)");
	wait(1700);
        System.out.println(" _______  __   __  _______     _______  __    _  ______  ");
	wait(1000);
	System.out.println("|       ||  | |  ||       |   |       ||  |  | ||      | ");
	wait(1000);
	System.out.println("|_     _||  |_|  ||    ___|   |    ___||   |_| ||  _    |");
	wait(1000);
	System.out.println("  |   |  |       ||   |___    |   |___ |       || | |   |");
	wait(1000);
	System.out.println("  |   |  |       ||    ___|   |    ___||  _    || |_|   |");
	wait(1000);
	System.out.println("  |   |  |   _   ||   |___    |   |___ | | |   ||       |");
	wait(1000);
	System.out.println("  |___|  |__| |__||_______|   |_______||_|  |__||______| ");
    }
    
    private static void wait(int millis){
	//if(!testMode && !Snake.started){ // only wait if not on testMode
	    try {
		Thread.sleep(millis);
	    }
	    catch (InterruptedException e) {
	    }
	    //}
    }

}

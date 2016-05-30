import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;
import java.io.*;

public class Snake{

    private static boolean DEBUG = true;
    private static int x,y;
    private static int ox,oy; // xcor, ycor of obstacle
    private static int length;
    private static int rows,cols; 
    private static char[][]board;
    private static MyDeque<Integer>snake;
    private static int dir; // direction: 0 = up, 1 = down, 2 = left, 3 = right
    private static int score,moves;
    static boolean done,started;

    private static void debug(String s){
	if(DEBUG){
	    System.out.println(s);
	}
    }

    private static void debug(int i){
	if(DEBUG){
	    System.out.println(i);
	}
    }

    public Snake(){
	done = false;
	//started = false;
	x = 1;
	y = 1;
	length = 1;
        rows = 15;
	cols = 15;
	snake = new MyDeque<Integer>(); // keeps coords of snake
	snake.addFirst(cols+1); // start at pt (1,1)
	board();
	addObstacle();
	dir = -1;
	score = 0;
	moves = 0;
    }

    private static void board(){ 
	board = new char[rows][cols]; // default board with borders
	for(int row = 0; row < rows; row++){
	    for(int col = 0; col < cols; col++){
		if(row==0 || row==rows-1 || col==0 || col==cols-1){
		    board[row][col] = '#';
		}else{
		    board[row][col] = ' ';
		}
	    }
	}
	board[y][x] = 'S';
    }

    private static void addObstacle(){
	int r,c;
	r = (int)(Math.random()*(rows-2))+1;
	c = (int)(Math.random()*(cols-1))+1;
	if(check(r,c)==0){
	    board[r][c]='!';
	}else{
	    addObstacle();
	}
    }

    public static String toString(boolean a){
	// animations
	//debug(""+rows+" "+cols);
	String s = "Score: "+score+"\n";
	for(int row = 0; row < rows; row++){
	    for(int col = 0; col < cols; col++){
		s += board[row][col]+" ";
	    }
	    s+="\n";
	}
	return s;
    }

    
    public static void run(){
	if(!done){
	    if(dir==0){
		move('u');
	    }else if(dir==1){
		move('d');
	    }else if(dir==2){
		move('l');
	    }else if(dir==3){
		move('r');
	    }
	    check();
	    wait(100);
	    System.out.println("\033[2J");
	    System.out.println(toString(true));
	}
    }

    public static void move(int i){
	started = true;
	if(i==0x77){
	    move('u');
	    dir = 0;
	}else if(i==0x73){
	    move('d');
	    dir = 1;
	}else if(i==0x61){
	    move('l');
	    dir = 2;
	}else if(i==0x64){
	    move('r');
	    dir = 3;
	}
    }

    public static boolean move(char c){ // udlr - up down left right
	int xx = 1;
	int yy = 1;
	if(snake.getLast()!=null){
	    xx = snake.getLast()%cols;
	    yy = snake.getLast()/cols;
	}
	/*	
		if(snake.getLast()!=null && board[x][y]!='!'){
		snake.removeLast();
		}else{
		length++;
		}
	*/
	int d = dir;
	
	if(c=='u'){// && ok(x,y-1)){
	    y--;
	    dir = 0;
	    moves++;
	}else if(c=='d'){// && ok(x,y+1)){
	    y++;
	    dir = 1;
	    moves++;
	}else if(c=='l'){// && ok(x-1,y)){
	    x--;
	    dir = 2;
	    moves++;
	}else if(c=='r'){// && ok(x+1,y)){
	    x++;
	    dir = 3;
	    moves++;
	}/*else{
	   gameOver();
	   return false;
	   }
	 */
	/*
	  String f = ""+(y*cols+x);
	  if(snake.toString().contains(f)){
	  gameOver();
	  return false;
	  }*/
	if(snake.getLast()!=null && check()==3 && ok(y,x)){
	    snake.removeLast();
	    board[yy][xx] = ' ';
	}
	if(!ok(y,x)&&dir>0){
	    System.out.println("NOT OK");
	    gameOver();
	}else{
	    //System.out.println(snake);
	    snake.addFirst(y*cols+x);
	    //check();
	    board[y][x] = 'S';
	    //debug(""+yy+" "+xx);
	}
	if(moves==2){
            wait(10);
	    board[1][2]=' ';
	    board[2][1]=' ';
	}
        return true;
    }
    
    private static boolean ok(int y, int x){
	//debug(""+board[y][x]);
	//debug(""+moves);
	if(board[y][x]=='#'){
	    return false;
	}else if(board[y][x]=='S' && dir >= 0){
	    return false;
	}else{
	    return true;
	}
	//return (board[y][x]!='#' && board[y][x]!='S');
	//return (board[y][x]==' ' || board[y][x]=='!');
    }

    private static int check(){ // checks for hitting the wall / obstacles
	if((board[y][x]=='#')){// || board[y][x]=='S') && moves>0){
	    gameOver();
	    return 2;
	}else if(board[y][x]=='!'){	    // found obstacle thing
	    score++;
	    addObstacle();
	    return 1;
	}else if(board[y][x]=='S'){
	    gameOver();
	    return 3;
	}else if(board[y][x]==' '){
	    return 0;
	}
	return -1;
	// 0 = ' ', 1 = '!', 2 = '#', 3 = 'S'
    }

    private static int check(int y, int x){ // checks for hitting the wall / obstacles
	if((board[y][x]=='#')){// || board[y][x]=='S') && moves>0){
	    gameOver();
	    return 2;
	}else if(board[y][x]=='!'){	    // found obstacle thing
	    score++;
	    addObstacle();
	    return 1;
	}else if(board[y][x]=='S'){
	    gameOver();
	    return 3;
	}else if(board[y][x]==' '){
	    return 0;
	}
	return -1;
	// 0 = ' ', 1 = '!', 2 = '#', 3 = 'S'
    }

    private static void gameOver(){
	System.out.println("x,y"+x+"   "+y);
	System.out.println("coord val"+board[y][x]);
	System.out.println("dir= "+dir);
        System.out.println("\033[2J");
	//board = null;
	System.out.println("GAME OVER.");
	wait(1800);
	System.out.println("Press the up arrow key to continue.");
	wait(2800);
	done=true;
    }



    private static void wait(int millis){
        try {
            Thread.sleep(millis);
        }
        catch (InterruptedException e) {
        }
    }


    private static void setTerminalToCBreak() throws IOException, InterruptedException { //used in main()
	ttyConfig = stty("-g");
	stty("-icanon min 1"); //makes the console go character by character rather than line by line
	stty("-echo"); //disables the terminal displaying the character pressed
    }

    private static String stty(final String args) throws IOException, InterruptedException { //used in setTerminalToCBreak()
	String cmd = "stty " + args + " < /dev/tty";
	
	return exec(new String[] {
		"sh",
		"-c",
		cmd
	    });
    }

    private static String exec(final String[] cmd) throws IOException, InterruptedException { //used in stty()
	ByteArrayOutputStream bout = new ByteArrayOutputStream();

	Process p = Runtime.getRuntime().exec(cmd);
        int c;
        InputStream in = p.getInputStream();

	while ((c = in.read()) != -1) {
            bout.write(c);
        }

        in = p.getErrorStream();

        while ((c = in.read()) != -1) {
            bout.write(c);
        }
	
        p.waitFor();

        String result = new String(bout.toByteArray());
        return result;
    }

    private static String ttyConfig;
    
    public static int snakeGame(){
	//Thanks to Graham King from darkcoding.net for the lesson on making the terminal interactive
        //String ttyConfig;

	Snake m = new Snake();
	try {
	    setTerminalToCBreak();
	    int i = 0;
	    while (true) {
		if (System.in.available() != 0) { //if a button is pressed:
		    int key = System.in.read();
		    //System.out.println(key);
		    if (key == 0x1B) { //if the button pressed is the esc key:
			break; //stop the loop
		    }
		    m.move(key);
		}
		i++;
		m.wait(500);        
		m.run();
	    }
	} catch (IOException e) {
	    System.out.println("IOException");
	} catch (InterruptedException e) {
	    System.out.println("InterruptedException");
	}
	finally {
	    try {
		stty(ttyConfig.trim());
	    } catch (Exception e) {
		System.out.println("Exception restoring tty config");
	    }
	}
	return score;
    }
    
}

//Resources
//https://msdn.microsoft.com/en-us/library/windows/desktop/dd375731(v=vs.85).aspx
//http://www.darkcoding.net/software/non-blocking-console-io-is-not-possible/
//http://stackoverflow.com/questions/9545388/how-can-i-detect-arrow-keys-in-java-console-not-in-gui
//https://docs.oracle.com/javase/tutorial/essential/exceptions/finally.html

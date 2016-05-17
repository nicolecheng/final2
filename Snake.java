import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;
import java.io.*;

public class Snake{

    private boolean DEBUG = true;
    private int x,y;
    private int ox,oy; // xcor, ycor of obstacle
    private int length;
    private int rows,cols; 
    private char[][]board;
    private MyDeque<Integer>snake;
    private int dir; // direction: 0 = up, 1 = down, 2 = left, 3 = right
    private int score;
    private boolean done;

    private void debug(String s){
	if(DEBUG){
	    System.out.println(s);
	}
    }

    public Snake(){
	x = 1;
	y = 1;
	length = 1;
        rows = 12;
	cols = 12;
	snake = new MyDeque<Integer>(); // keeps coords of snake
	snake.addFirst(cols+1); // start at pt (1,1)
	board();
	addObstacle();
	dir = -1;
	score = 0;
    }

    private void board(){ 
	board = new char[rows][cols]; // default 10x10 board with borders
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

    private void addObstacle(){
	int r,c;
	r = (int)(Math.random()*(rows-2))+1;
	c = (int)(Math.random()*(cols-1))+1;
	if(ok(r,c)){
	    board[r][c]='!';
	}else{
	    addObstacle();
	}
    }

    public String toString(){
	// animations
	debug(""+rows+" "+cols);
	String s = "Score: "+score+"\n";
	for(int row = 0; row < rows; row++){
	    for(int col = 0; col < cols; col++){
		s += board[row][col]+" ";
	    }
	    s+="\n";
	}
	return s;
    }

    public void move(int i){
	//debug("HERE");
	if(i==0x77){
	    debug("MOVING UP");
	    move('u');
	    dir = 0;
	}else if(i==0x73){
	    debug("MOVING DOWN");
	    move('d');
	    dir = 1;
	}else if(i==0x61){
	    debug("MOVING LEFT");
	    move('l');
	    dir = 2;
	}else if(i==0x64){
	    debug("MOVING RIGHT");
	    move('r');
	    dir = 3;
	}
    }

    public boolean move(char c){ // udlr - up down left right
	//debug(""+snake.getLast());
	int xx = 1;
	int yy = 1;
	if(snake.getLast()!=null){
	    //debug("AQUI");
	    xx = snake.getLast()%cols;
	    yy = snake.getLast()/cols;
	}
	int d = dir;
	if(c=='u' && ok(x,y-1)){
		y--;
		dir = 0;
	}else if(c=='d' && ok(x,y+1)){
		y++;
		dir = 1;
	}else if(c=='l' && ok(x-1,y)){
		x--;
		dir = 2;
	}else if(c=='r' && ok(x+1,y)){
		x++;
		dir = 3;
	}else{
	    gameOver();
	    return false;
	}
	if(snake.getLast()!=null){
	    snake.removeLast();
	}
	//System.out.println(snake);
	snake.addFirst(y*cols+x);
	check();
	board[y][x] = 'S';
	//debug(""+yy+" "+xx);
	board[yy][xx] = ' ';
        return true;
    }

    private boolean ok(int y, int x){
	return (board[y][x]!='#' && board[y][x]!='S');
    }

    private void check(){ // checks for hitting the wall / obstacles
	if(board[y][x]=='#' || board[y][x]=='S'){
	    gameOver();
	}else if(board[y][x]=='!'){
	    // found obstacle thing
	    score++;
	    addObstacle();
	}
    }

    private void gameOver(){
        System.out.println("\033[2J");
	//board = null;
	System.out.println("GAME OVER. YOU LOST.");
	done=true;
    }

    public void run(){
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
	System.out.println("\033[2J");
	System.out.println(this);
    }


    private void wait(int millis){
        try {
            Thread.sleep(millis);
        }
        catch (InterruptedException e) {
        }
    }


    /*
    //FREE STUFF!!! (creds to mr k)

    public void clearTerminal(){
        System.out.println(CLEAR_SCREEN);
    }

    public String toString(){
        int maxx = maze.length;
        int maxy = maze[0].length;
        String ans = "";
        if(animate){
            ans = "Solving a maze that is " + maxx + " by " + maxy + "\n";
        }
        for(int i = 0; i < maxx * maxy; i++){
            if(i % maxx == 0 && i != 0){
                ans += "\n";
            }
            char c =  maze[i % maxx][i / maxx];
            if(c == '#'){
                ans += color(38,47)+c;
            }else{
                ans += color(33,40)+c;
            }
        }
        return HIDE_CURSOR + go(0,0) + ans + "\n" + SHOW_CURSOR + color(37,40);
    }

    //MORE FREE STUFF!!! *you can ignore all of this*
    //Terminal keycodes to clear the terminal, or hide/show the cursor
    private static final String CLEAR_SCREEN =  "\033[2J";
    private static final String HIDE_CURSOR =  "\033[?25l";
    private static final String SHOW_CURSOR =  "\033[?25h";
    //terminal specific character to move the cursor
    private String go(int x,int y){
        return ("\033[" + x + ";" + y + "H");
    }

    private String color(int foreground,int background){
        return ("\033[0;" + foreground + ";" + background + "m");
    }

    private void wait(int millis){
        try {
            Thread.sleep(millis);
        }
        catch (InterruptedException e) {
        }
    }

    
    //END FREE STUFF
    */

    
    //Thanks to Graham King from darkcoding.net for the lesson on making the terminal interactive
    private static String ttyConfig;

    public static void main(String[] args) {
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
    

}

//Resources
//https://msdn.microsoft.com/en-us/library/windows/desktop/dd375731(v=vs.85).aspx
//http://www.darkcoding.net/software/non-blocking-console-io-is-not-possible/
//http://stackoverflow.com/questions/9545388/how-can-i-detect-arrow-keys-in-java-console-not-in-gui
//https://docs.oracle.com/javase/tutorial/essential/exceptions/finally.html

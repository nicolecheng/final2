import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;
import java.io.*;

public class Snake{

    private int x,y;
    private int length;
    private int maxx,maxy; // bounds
    private char[][]board;
    private int[][]snake;
    private int dir; // direction: 0 = up, 1 = down, 2 = left, 3 = right

    public Snake(){
	x = 1;
	y = 1;
	length = 1;
        maxx = 5;
	maxy = 5;
	snake = new int[10][2]; // keeps coords of snake
	snake[0][0]=x;
	snake[0][1]=y;
	board();
    }

    private void board(){ // rows&cols [1,5], borders [0],[6]
	board = new int[maxx+2][maxy+y]; // default 5x5 board with borders
	for(int row = 0; row <= maxy; row++){
	    for(int col = 0; col <= maxx; col++){
		if(row==0 || row==maxy || col==0 || col==maxx){
		    board[row][col] = '#';
		}else{
		    board[row][col] = ' ';
	    }
	}
    }	
    
    private class Obstacle{
	int x,y;	
    }

    public String toString(){
	// animations
	String s = "";
	for(int row = 0; row < board.length; row++){
	    for(int col = 0; col < board[row].length; col++){
		s += board[row][col]+" ";
	    }
	    s+="\n";
	}
	return s;
    }

    public boolean move(char c){ // udlr - up down left right
	int xx = snake[length-1][0];
	int yy = snake[length-1][1];
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
	    return false;
	}
	snake[
	board[x][y] = 'S';
	board[xx][yy] = ' ';
        reutrn true;
    }

    private boolean ok(int x, int y){
	return (x > 0 && x <= maxx && y > 0 && y <= maxy && board[x][y]!="#");
    }

    public void run(){

    }


    public static void main(String[]args){

	Snake m = new Snake();
	
    }

    private void grow(){
	int[][]hold = new int[snake.length*2][2];
	for(int row = 0; row < snake.length; row++){
	    for(int col = 0; col < snake[row].length; col++){
		hold[row][col] = snake[row][col];
	    }
	}
	snake = hold;
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

    /*
    //Thanks to Graham King from darkcoding.net for the lesson on making the terminal interactive
    private static String ttyConfig;

    public static void main(String[] args) {
	try {
	    setTerminalToCBreak();
	    int i = 0;
	    while (true) {
		if (System.in.available() != 0) { //if a button is pressed:
		    int exitKey = System.in.read();
		    System.out.println(exitKey);
		    if (exitKey == 0x48 || exitKey == 0x61) { //if the button pressed is the up key:
			break; //stop the loop
		    }
		}
		i++;
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
    */

}

//Resources
//https://msdn.microsoft.com/en-us/library/windows/desktop/dd375731(v=vs.85).aspx
//http://www.darkcoding.net/software/non-blocking-console-io-is-not-possible/
//http://stackoverflow.com/questions/9545388/how-can-i-detect-arrow-keys-in-java-console-not-in-gui
//https://docs.oracle.com/javase/tutorial/essential/exceptions/finally.html

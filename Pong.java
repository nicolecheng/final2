import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;

//Resources
//https://msdn.microsoft.com/en-us/library/windows/desktop/dd375731(v=vs.85).aspx
//http://www.darkcoding.net/software/non-blocking-console-io-is-not-possible/
//http://stackoverflow.com/questions/9545388/how-can-i-detect-arrow-keys-in-java-console-not-in-gui
//https://docs.oracle.com/javase/tutorial/essential/exceptions/finally.html

public class Pong {
    private char[][] board;
    private int pos1;
    private int timer;
    private int speed;
    private int pos2;
    private int dir2;
    private int difficulty;
    private int ballX;
    private int ballY;
    private int lastDir;
    private int score1;
    private int score2;
    private boolean DEBUG;
    
    public Pong(int difficulty) {
	DEBUG = false;
	dir2 = 1;
        //initialize the board to by an empty 9 by 25 board
        board = new char[15][75];
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                board[r][c] = ' ';
            }
        }
	//show the border
	for (int c = 0; c < board[0].length; c++) {
	    board[0][c] = '-';
	    board[board.length-1][c] = '-';
	}
        //create the paddles at each end of the board
        pos1 = 6;
        pos2 = 5;
        for (int i = pos1; i < pos1+3; i++) {
            board[i][0] = '#';
        }
        for (int i = pos2; i < pos2+5; i++) {
            board[i][board[0].length-1] = '#';
        }
	score1 = 0;
	score1 = 0;
	//show the ball
        board[board.length/2][board[0].length/2] = '*';
	ballX = board[0].length/2;
	ballY = board.length/2;
	lastDir = 4;
        timer = 0;
	speed = 0;
        this.difficulty = difficulty; //set difficulty
    }
    
    public void reset() {
	if (ballX == 0 || ballX == board[0].length-1) {
	    try {
		if (ballX == 0) {
		    score2++;
		    board[3][board[0].length-7] = 'G';
		    board[3][board[0].length-6] = 'O';
		    board[3][board[0].length-5] = 'A';
		    board[3][board[0].length-4] = 'L';
		    board[3][board[0].length-3] = '!';
		    System.out.println("\033[2J");
		    System.out.println(this);
		    long x = System.currentTimeMillis();
		    Thread.sleep(3000);
		    board[3][board[0].length-7] = ' ';
		    board[3][board[0].length-6] = ' ';
		    board[3][board[0].length-5] = ' ';
		    board[3][board[0].length-4] = ' ';
		    board[3][board[0].length-3] = ' ';
		    for (int i = board.length-7; i < board.length-2; i++) {
			board[3][i] = ' ';
		    }
		    System.out.println("\033[2J");
		    System.out.println(this);
		}
		if (ballX == board[0].length-1) {
		    score1++;
		    board[3][2] = 'G';
		    board[3][3] = 'O';
		    board[3][4] = 'A';
		    board[3][5] = 'L';
		    board[3][6] = '!';
		    System.out.println("\033[2J");
		    System.out.println(this);
		    long x = System.currentTimeMillis();
		    Thread.sleep(3000);
		    board[3][2] = ' ';
		    board[3][3] = ' ';
		    board[3][4] = ' ';
		    board[3][5] = ' ';
		    board[3][6] = ' ';
		    for (int i = 2; i < 7; i++) {
			board[3][i] = ' ';
		    }
		    System.out.println("\033[2J");
		    System.out.println(this);
		}
	    }
	    catch (InterruptedException e) {
		System.out.println("InterruptedException");
	    }
	    for (int i = pos1; i < pos1+3; i++) {
		board[i][0] = ' ';
	    }
	    for (int i = pos2; i < pos2+3; i++) {
		board[i][0] = ' ';
	    }
	    pos1 = 6;
	    pos2 = 4;
	    for (int i = pos1; i < pos1+3; i++) {
		board[i][0] = '#';
	    }
	    for (int i = pos2; i < pos2+5; i++) {
		board[i][board[0].length-1] = '#';
	    }
	    board[board.length/2][board[0].length/2] = '*';
	    ballX = board[0].length/2;
	    ballY = board.length/2;
	    lastDir = 4;
	}
    }

    public void move(int key) {
        if (key == 0x77 && pos1 > 0) {
	    if (pos1 > 1) {
		board[pos1-1][0] = '#';
	    }
	    if (inBounds(pos1+3,board) && pos1+3 != 14) {
		board[pos1+2][0] = ' ';
	    }
            pos1--;
        }
        if (key == 0x73 && pos1 < board.length-4) {
	    if (pos1 != 0) {
		board[pos1][0] = ' ';
	    }
	    if (inBounds(pos1+3,board)) {
		board[pos1+3][0] = '#';
	    }
            pos1++;
        }
    }

    public void botMove() {
	if (pos2 == 1) {
	    dir2 = -1;
	}
	if (pos2 == board.length-7) {
	    dir2 = 1;
	}
	if (dir2 == 1) {
	    pos2--;
	    board[pos2][board[0].length-1] = '#';
	    board[pos2+6][board[0].length-1] = ' ';
	}
	if (dir2 == -1) {
	    pos2++;
	    board[pos2-1][board[0].length-1] = ' ';
	    board[pos2+5][board[0].length-1] = '#';
	}
    }

    public int ballMove(int dir) {
	if (dir == 0) {
	    board[ballY][ballX] = ' ';
	    ballX++;
	    board[ballY][ballX] = '*';
	}
	if (dir == 1) {
	    board[ballY][ballX] = ' ';
	    ballX++;
	    ballY--;
	    board[ballY][ballX] = '*';
	}
	if (dir == 2) {
	    board[ballY][ballX] = ' ';
	    ballY--;
	    board[ballY][ballX] = '*';
	}
	if (dir == 3) {
	    board[ballY][ballX] = ' ';
	    ballX--;
	    ballY--;
	    board[ballY][ballX] = '*';
	}
	if (dir == 4) {
	    board[ballY][ballX] = ' ';
	    ballX--;
	    board[ballY][ballX] = '*';
	}
	if (dir == 5) {
	    board[ballY][ballX] = ' ';
	    ballX--;
	    ballY++;
	    board[ballY][ballX] = '*';
	}
	if (dir == 6) {
	    board[ballY][ballX] = ' ';
	    ballY++;
	    board[ballY][ballX] = '*';
	}
	if (dir == 7) {
	    board[ballY][ballX] = ' ';
	    ballX++;
	    ballY++;
	    board[ballY][ballX] = '*';
	}
	return dir;
    }

    public int getDir(int lastDir) {
	if (ballX == 1) {
	    //hit paddle dead on
	    if (ballY-1 == pos1) {
		return 0;
	    }
	    //hit top of paddle
	    if (ballY == pos1) {
		return 1;
	    }
	    //hit bottom of paddle
	    if (ballY-2 == pos1) {
		return 7;
	    }
	} else if (ballX == board[0].length-2) {
	    if (ballY-2 == pos2) {
		return 3;
	    }
	    //dead on
	    if (ballY-1 == pos2) {
		return 4;
	    }
	    //top
	    if (ballY == pos2) {
		return 4;
	    }
	    //bottom
	    if (ballY+1 == pos2) {
		return 4;
	    }
	    if (ballY+2 == pos2) {
		return 5;
	    }
	}
	//if it hits the ceiling, reflect it in the opposite direction
	else if (ballY <= 1) {
	    if (lastDir == 1) {
		return 7;
	    }
	    if (lastDir == 3) {
		return 5;
	    }
	} else if (ballY >= board.length-2) {
	    if (lastDir == 7) {
		return 1;
	    }
	    if (lastDir == 5) {
		return 3;
	    }
	}
	return lastDir;
    }
    
    public String toString() {
        String ret = "";
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                ret += String.valueOf(board[r][c]);
            }
            ret += "\n";
        }
        return ret;
    }
    
    public boolean play() {
	try {
	    if (DEBUG) {
		System.out.println("lastDir: "+lastDir);
		System.out.println("ballX: "+ballX);
		System.out.println("ballY: "+ballY);
		System.out.println("pos1: "+pos1);
		System.out.println("pos2: "+pos2);
		System.out.println();
	    } else {
		System.out.println("\033[2J");
		System.out.println(this);
	    }
	    long x = System.currentTimeMillis();
	    if (System.in.available() != 0) {
		int key = System.in.read();
		move(key);
		//fixPos();
	    }
	    reset();
	    board[3][3] = Character.forDigit(score1,10);
	    board[3][board[0].length-4] = Character.forDigit(score2,10);
	    lastDir = ballMove(getDir(lastDir));
	    botMove();
	    checkTimer();
	    timer++;
	    return true;
	}
	catch (IOException e) {
	    System.out.println("IOException");
	}
	return false;
    }

    public void checkTimer() {
	if (timer == 10) {
	    speed--;
	    timer = 0;
	}
    }
    
    public void fixPos() {
	int r = 0;
	boolean changed = false;
	while (!changed) {
	    if (board[r][0] == '#') {
		pos1 = r;
		changed = true;
	    }
	    r++;
	}
	r = 0;
	changed = false;
	while (!changed) {
	    if (board[r][board[0].length-1] == '#') {
		pos2 = r;
		changed = true;
	    }
	    r++;
	}
    }

    public static boolean wait(int millis, long currentTime) {
	return System.currentTimeMillis()-currentTime > millis;
    }

    public static boolean inBounds(int pos, char[][] board) {
	return (pos >= 0 && pos < board.length);
    }
    
    //Thanks to Graham King from darkcoding.net for the lesson on making the terminal interactive
    private static String ttyConfig;

    public int pong() {
	try {
	    setTerminalToCBreak();
	    while (score1 < 5 && score2 < 5) {
		if (!play()) {
		    return score1;
		}
		try {
		    Thread.sleep(speed);
		}
		catch (InterruptedException e) {
		}
	    }
	    return score1;
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
	return 0;
    }
    
    public static void main(String[] args) {
	Pong p = new Pong(0);
        System.out.println(p.pong());
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

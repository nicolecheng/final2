import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;

public class Pacman {
    private int score;
    private int lives;
    private int pacX;
    private int pacY;
    private int timer;
    private char[][] board;
    private Ghost red;
    private Ghost blue;
    private Ghost pink;
    private Ghost orange;
    private boolean superPac; //haha

    public Pacman() {
	score = 0;
	lives = 3;
	pacX = 14;
	pacY = 26;
	timer = 0;
	red = new Ghost(14,14);
	blue = new Ghost(12,17);
	pink = new Ghost(14,17);
	orange = new Ghost(16,17);
	superPac = false;
	//board setup
	board = new char[36][28];
	for (int r = 0; r < 36; r++) {
	    for (int c = 0; c < 28; c++) {
		board[r][c] = '*';
	    }
	}
	//clear areas that shouldn't have *s
	for (int r = 0; r < 3; r++) {
	    for (int c = 0; c < 28; c++) {
		board[r][c] = ' ';
	    }
	}
	for (int r = 13; r < 16; r++) {
	    for (int c = 0; c < 5; c++) {
		board[r][c] = ' ';
		board[r][27-c] = ' ';
	    }
	}
	for (int r = 19; r < 22; r++) {
	    for (int c = 0; c < 5; c++) {
		board[r][c] = ' ';
		board[r][27-c] = ' ';
	    }
	}
	for (int r = 34; r < 36; r++) {
	    for (int c = 0; c < 28; c++) {
		board[r][c] = ' ';
	    }
	}
	//score
	board[0][11] = 'S';
	board[0][12] = 'C';
	board[0][13] = 'O';
	board[0][14] = 'R';
	board[0][15] = 'E';
	board[2][14] = Character.forDigit(score/10,10);
	board[2][15] = Character.forDigit(score%10,10);
	//outsides
	for (int c = 0; c < 28; c++) {
	    board[3][c] = '=';
	}
	for (int r = 4; r < 13; r++) {
	    board[r][0] = '=';
	    board[r][27] = '=';
	}
	for (int c = 0; c < 6; c++) {
	    board[12][c] = '=';
	    board[12][27-c] = '=';
	    board[16][c] = '=';
	    board[16][27-c] = '=';
	    board[18][c] = '=';
	    board[18][27-c] = '=';
	    board[22][c] = '=';
	    board[22][27-c] = '=';
	}
	for (int r = 12; r < 17; r++) {
	    board[r][5] = '=';
	    board[r][22] = '=';
	}
	for (int r = 18; r < 23; r++) {
	    board[r][5] = '=';
	    board[r][22] = '=';
	}
	for (int r = 23; r < 34; r++) {
	    board[r][0] = '=';
	    board[r][27] = '=';
	}
	for (int c = 0; c < 28; c++) {
	    board[33][c] = '=';
	}
	//insides
	for (int r = 3; r < 8; r++) {
	    board[r][13] = '=';
	    board[r][14] = '=';
	}
	for (int r = 5; r < 8; r++) {
	    for (int c = 2; c < 26; c++) {
		if (c != 6 && c != 12 && c != 15 && c != 21) {
		    board[r][c] = '=';
		}
	    }
	}
	for (int r = 9; r < 11; r++) {
	    for (int c = 2; c < 26; c++) {
		if (c != 6 && c != 9 && c != 18 && c != 21) {
		    board[r][c] = '=';
		}
	    }
	}
	for (int r = 11; r < 17; r++) {
	    board[r][7] = '=';
	    board[r][8] = '=';
	    if (r < 14) {
		board[r][13] = '=';
		board[r][14] = '=';
	    }
	    board[r][19] = '=';
	    board[r][20] = '=';
	}
	for (int c = 9; c < 12; c++) {
	    board[12][c] = '=';
	    board[13][c] = '=';
	}
	for (int c = 16; c < 19; c++) {
	    board[12][c] = '=';
	    board[13][c] = '=';
	}
	for (int r = 15; r < 20; r++) {
	    board[r][10] = '=';
	    board[r][17] = '=';
	}
	for (int c = 10; c < 18; c++) {
	    board[15][c] = '=';
	    board[19][c] = '=';
	}
	board[15][13] = '-';
	board[15][14] = '-';
	for (int r = 18; r < 23; r++) {
	    board[r][7] = '=';
	    board[r][8] = '=';
	    board[r][19] = '=';
	    board[r][20] = '=';
	}
	for (int c = 10; c < 18; c++) {
	    board[21][c] = '=';
	    board[22][c] = '=';
	}
	board[23][13] = '=';
	board[23][14] = '=';
	for (int c = 2; c < 26; c++) {
	    if (c != 6 && c != 12 && c != 15 && c != 21) {
		board[24][c] = '=';
		board[25][c] = '=';
	    }
	}
	for (int r = 26; r < 29; r++) {
	    board[r][4] = '=';
	    board[r][5] = '=';
	    board[r][22] = '=';
	    board[r][23] = '=';
	}
	for (int c = 1; c < 27; c++) {
	    if (c != 3 && c != 6 && c != 9 && c != 18 && c != 21 && c != 24) {
		board[27][c] = '=';
		board[28][c] = '=';
	    }
	}
	board[29][7] = '=';
	board[29][8] = '=';
	board[29][13] = '=';
	board[29][14] = '=';
	board[29][19] = '=';
	board[29][20] = '=';
	for (int c = 2; c < 26; c++) {
	    if (c != 12 && c != 15) {
		board[30][c] = '=';
		board[31][c] = '=';
	    }
	}
    }

    public void updateScore() {
	//implement
    }
    public void updateLives() {
	//implement
    }

    private String color(int foreground,int background){
	return ("\033[0;" + foreground + ";" + background + "m");
    }

    public String toString() {
        String ret = "";
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c] == '=') {
		    ret += color(37,40)+String.valueOf(board[r][c]) + " ";
		} else {
		    ret += String.valueOf(board[r][c]) + " ";
		}
            }
            ret += "\n";
        }
        return ret;
    }

    private class Ghost {
	private int ghostX;
	private int ghostY;
	private int direction; //house: 0, up: 1, right: 2, down: 3, left: 4
	private boolean freedom;
	private int movementMode; //scatter: 0, chase: 1, frightened: 2

	public Ghost(int ghostX, int ghostY) {
	    this.ghostX = ghostX;
	    this.ghostY = ghostY;
	    direction = 0;
	    freedom = false;
	    movementMode = 0;
	}

	public void setFreedom(boolean freedom) {
	    this.freedom = freedom;
	}
	public void setMovementMode(int movementMode) {
	    this.movementMode = movementMode;
	}
	public void setDirection(int direction) {
	    this.direction = direction;
	}

	public void move() {
	    if (direction == 1) {
		ghostY--;
	    }
	    if (direction == 2) {
		ghostX++;
	    }
	    if (direction == 3) {
		ghostY++;
	    }
	    if (direction == 4) {
		ghostX--;
	    }
	}
    }

    public void play() {
	System.out.println("\033[2J");
	System.out.println(this);
    }
    
    public int pacman() {
	try {
	    long time = System.currentTimeMillis();
	    setTerminalToCBreak();
	    while (System.currentTimeMillis() - time < 5000) {
		play();
		try {
		    Thread.sleep(100);
		}
		catch (InterruptedException e) {
		}
	    }
	    return score;
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
	Pacman p = new Pacman();
	System.out.println(p.pacman());
    }

    private static String ttyConfig;
    
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

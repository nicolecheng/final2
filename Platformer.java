import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;

public class Platformer {
    private char[][] board;
    private Obstacle[] obs;
    private int obsCreated;
    private int level;
    private int jump;
    private int score;
    private int timeSinceLastObs;
    private int timeToReach;
    private int timeSinceLastJump;
    private boolean DEBUG;
    
    public Platformer() {
	DEBUG = false;
	jump = 0;
	timeSinceLastObs = 0;
	timeSinceLastJump = 11;
	timeToReach = (int)(Math.random()*40)+40;
	//init board
	board = new char[15][75];
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                board[r][c] = ' ';
            }
        }
	//init player
	board[board.length-1-jump][2] = '|';
	board[board.length-1-jump][4] = '|';
	board[board.length-2-jump][3] = '-';
	board[board.length-3-jump][3] = '|';
	board[board.length-4-jump][3] = 'v';
	board[board.length-4-jump][2] = '.';
	board[board.length-4-jump][4] = '.';
	//init score
	score = 0;
	board[2][5] = 'S';
	board[2][6] = 'C';
	board[2][7] = 'O';
	board[2][8] = 'R';
	board[2][9] = 'E';
	board[2][10] = ':';
	board[2][12] = Character.forDigit(score/10,10);
	board[2][13] = Character.forDigit(score%10,10);
	
	obs = new Obstacle[10];
	obs[0] = new Obstacle((int)(Math.random()*4)+1,(int)(Math.random()*4)+1);
	obsCreated = 1;
    }

    public void dump() {
	try {
	    for (int i = 0; i < 1000; i++) {
		int key = System.in.read();
	    }
	} catch (IOException e) {
	    System.out.println("IOException line 61");
	}
    }

    public void updateScore() {
	board[2][12] = Character.forDigit(score/10,10);
	board[2][13] = Character.forDigit(score%10,10);
    }

    public void remakeFirstObs() {
	board[board.length-1-jump][2] = '|';
	board[board.length-1-jump][4] = '|';
	board[board.length-2-jump][3] = '-';
	board[board.length-3-jump][3] = '|';
	board[board.length-4-jump][3] = 'v';
	board[board.length-4-jump][2] = '.';
	board[board.length-4-jump][4] = '.';
	Obstacle[] newObs = new Obstacle[10];
	for (int i = 1; i < obs.length; i++) {
	    newObs[i-1] = obs[i];
	}
	newObs[9] = new Obstacle((int)(Math.random()*4)+1,(int)(Math.random()*4)+1);
	obs = newObs;
    }

    public void checkTime() {
	if (timeSinceLastObs >= timeToReach) {
	    if (obsCreated != 10) {
		obs[obsCreated] = new Obstacle((int)(Math.random()*4)+1,(int)(Math.random()*4)+1);
		obsCreated++;
	    }
	    else {
		remakeFirstObs();
	    }
	    timeSinceLastObs = 0;
	    timeToReach = (int)(Math.random()*40)+20;
	}
    }

    public boolean play() {
	System.out.println("\033[2J");
	System.out.println(this);
	for (Obstacle obsX : obs) {
	    if (obsX != null) {
		if (obsX.getBotLeft() > 0) {
		    obsX.moveLeft();
		} else {
		    remakeFirstObs();
		    score++;
		}
	    }
	}
	timeSinceLastJump++;
	if (timeSinceLastJump < 10) {
	    moveUp();
	}
	if (timeSinceLastJump > 10 && timeSinceLastJump < 20) {
	    moveDown();
	}
	timeSinceLastObs++;
	for (int r = 0; r < board.length; r++) {
	    board[r][0] = ' ';
	}
	checkTime();
	updateScore();
	return checkCollision();
    }

    public void moveUp() {
	if (jump < 9) {
	    board[board.length-1-jump][2] = ' ';
	    board[board.length-1-jump][4] = ' ';
	    board[board.length-2-jump][3] = ' ';
	    board[board.length-3-jump][3] = ' ';
	    board[board.length-4-jump][3] = ' ';
	    board[board.length-4-jump][2] = ' ';
	    board[board.length-4-jump][4] = ' ';
	    jump++;
	    board[board.length-1-jump][2] = '|';
	    board[board.length-1-jump][4] = '|';
	    board[board.length-2-jump][3] = '-';
	    board[board.length-3-jump][3] = '|';
	    board[board.length-4-jump][3] = 'v';
	    board[board.length-4-jump][2] = '.';
	    board[board.length-4-jump][4] = '.';
	}
    }

    public void moveDown() {
	if (jump > 0) {
	    board[board.length-1-jump][2] = ' ';
	    board[board.length-1-jump][4] = ' ';
	    board[board.length-2-jump][3] = ' ';
	    board[board.length-3-jump][3] = ' ';
	    board[board.length-4-jump][3] = ' ';
	    board[board.length-4-jump][2] = ' ';
	    board[board.length-4-jump][4] = ' ';
	    jump--;
	    board[board.length-1-jump][2] = '|';
	    board[board.length-1-jump][4] = '|';
	    board[board.length-2-jump][3] = '-';
	    board[board.length-3-jump][3] = '|';
	    board[board.length-4-jump][3] = 'v';
	    board[board.length-4-jump][2] = '.';
	    board[board.length-4-jump][4] = '.';
	}
    }

    public static boolean wait(int millis, long currentTime) {
	return System.currentTimeMillis()-currentTime > millis;
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

    public boolean checkCollision() {
	for (Obstacle obsX : obs) {
	    if (obsX != null) {
		if (jump < obsX.getHeight() && obsX.getBotLeft() >= 2 && obsX.getBotLeft() <= 4) {
		    return true;
		}
	    }
	}
	return false;
    }

    private class Obstacle {
	private int width;
	private int height;
	private int botLeftPos;

	private Obstacle(int width, int height) {
	    this.width = 1; //changed to 1 because it's easier to deal with
	    this.height = height;
	    botLeftPos = board[0].length-width;
	    moveLeft();
	}

	private void moveLeft() {
	    for (int h = 0; h < height; h++) {
		for (int w = 0; w < width; w++) {
		    board[board.length-1-h][botLeftPos+w] = ' ';
		    board[board.length-1-h][botLeftPos+w-1] = '=';
		}
	    }
	    botLeftPos--;
	}

	public int getBotLeft() {
	    return botLeftPos;
	}
	public int getHeight() {
	    return height;
	}
	public int getWidth() {
	    return width;
	}
    }

    public int getScore() {
	return score;
    }
    public int tSLJ() {
	return timeSinceLastJump;
    }
    public void setTSLJ(int TSLJ) {
	timeSinceLastJump = TSLJ;
    }
    
    //Thanks to Graham King from darkcoding.net for the lesson on making the terminal interactive
    private static String ttyConfig;

    public static int dino() {
	try {
	    setTerminalToCBreak();
	    Platformer p = new Platformer();
	    while (true) {
		long x = System.currentTimeMillis();
		if (System.in.available() != 0) {
		    int key = System.in.read();
		    if (key == 0x6B) {
			return 999;
		    }
		}
		if (p.tSLJ() >= 30 && System.in.available() != 0) {
		    int key = System.in.read();
		    if (key == 0x77) {
			p.moveUp();
			p.setTSLJ(0);
		    }
		}
		//while (!wait(100,x)) {}
		Thread.sleep(100);
	        if (p.play()) {
		    //while (!wait(1000,x)) {}
		    Thread.sleep(1000);
		    System.out.println("\033[2J");
		    System.out.println("GAME OVER");
		    return p.getScore();
		}
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
	return 0;
    }

    public static void main(String[] args) {
	System.out.println("\033[2J");
	System.out.println(dino());
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

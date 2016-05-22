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
    private int timeSinceLastObs;
    private int timeToReach;
    private boolean DEBUG;
    
    public Platformer() {
	DEBUG = false;
	jump = 0;
	timeSinceLastObs = 0;
	timeToReach = (int)(Math.random()*20)+5;
	//init board
	board = new char[15][75];
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                board[r][c] = ' ';
            }
        }
	//init player
	board[board.length-1][jump+2] = '|';
	board[board.length-1][jump+4] = '|';
	board[board.length-2][jump+3] = '-';
	board[board.length-3][jump+3] = '|';
	board[board.length-4][jump+3] = 'v';
	board[board.length-4][jump+2] = '.';
	board[board.length-4][jump+4] = '.';
	obs = new Obstacle[5];
	obs[1] = new Obstacle((int)(Math.random()*2)+1,(int)(Math.random()*2)+1);
	obsCreated = 1;
    }

    public void remakeFirstObs() {
	Obstacle[] newObs = new Obstacle[5];
	for (int i = 1; i < obs.length; i++) {
	    newObs[i-1] = obs[i];
	    newObs[5] = new Obstacle((int)(Math.random()*2)+1,(int)(Math.random()*2)+1);
	}
	obs = newObs;
    }

    public void checkTime() {
	if (timeSinceLastObs >= timeToReach) {
	    if (obsCreated != 5) {
		obsCreated++;
		obs[obsCreated] = new Obstacle((int)(Math.random()*2)+1,(int)(Math.random()*2)+1);
	    }
	    else {
		remakeFirstObs();
	    }
	    timeSinceLastObs = 0;
	    timeToReach = (int)(Math.random()*20)+5;
	}
    }

    public void play() {
	System.out.println("\033[2J");
	System.out.println(this);
	for (Obstacle obsX : obs) {
	    obsX.moveLeft();
	}
	if (!System.in.available()) {
	    int key = System.in.read();
	    move(key);
	}
	else {
	    if (jump != 0) {
		board[board.length-1][jump+2] = ' ';
		board[board.length-1][jump+4] = ' ';
		board[board.length-2][jump+3] = ' ';
		board[board.length-3][jump+3] = ' ';
		board[board.length-4][jump+3] = ' ';
		board[board.length-4][jump+2] = ' ';
		board[board.length-4][jump+4] = ' ';
		jump--;
		board[board.length-1][jump+2] = '|';
		board[board.length-1][jump+4] = '|';
		board[board.length-2][jump+3] = '-';
		board[board.length-3][jump+3] = '|';
		board[board.length-4][jump+3] = 'v';
		board[board.length-4][jump+2] = '.';
		board[board.length-4][jump+4] = '.';
	    }
	}
	timeSinceLastObs++;
	checkTime();
    }

    public void move(int key) {
	int time = System.currentTimeMillis();
	if (key == 0x77 && (jump == 0 || jump == 1 || jump == 2 || jump == 3)) {
	    board[board.length-1][jump+2] = ' ';
	    board[board.length-1][jump+4] = ' ';
	    board[board.length-2][jump+3] = ' ';
	    board[board.length-3][jump+3] = ' ';
	    board[board.length-4][jump+3] = ' ';
	    board[board.length-4][jump+2] = ' ';
	    board[board.length-4][jump+4] = ' ';
	    jump++;
	    board[board.length-1][jump+2] = '|';
	    board[board.length-1][jump+4] = '|';
	    board[board.length-2][jump+3] = '-';
	    board[board.length-3][jump+3] = '|';
	    board[board.length-4][jump+3] = 'v';
	    board[board.length-4][jump+2] = '.';
	    board[board.length-4][jump+4] = '.';
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

    private class Obstacle {
	private int width;
	private int height;
	private int botLeftPos;

	private Obstacle(int width, int height) {
	    this.width = width;
	    this.height = height;
	    botLeftPos = board[0].length-width;
	    moveLeft();
	}

	private void moveLeft() {
	    for (int h = 0; h < height; h++) {
		for (int w = 0; w < width; w++) {
		    board[board.length-1+h][botLeftPos+w] = ' ';
		    board[board.length-1+h][botLeftPos+w-1] = '=';
		}
	    }
	    botLeftPos--;
	}
    }
    
    //Thanks to Graham King from darkcoding.net for the lesson on making the terminal interactive
    private static String ttyConfig;

    public static void main(String[] args) {
	try {
	    setTerminalToCBreak();
	    Platformer p = new Platformer();
	    while (true) {
	        if (System.in.available() != 0) {
		    int key = System.in.read();
		    if (key == 0x1B) {
			break;
		    } else {
			p.play();
		    }
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

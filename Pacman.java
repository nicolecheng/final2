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
    
    public int pacman() {
	try {
	    setTerminalToCBreak();
	    while (lives > 0) {
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
}

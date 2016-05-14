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
    private int pos2;
    private int difficulty;
    
    public Pong(int difficulty) {
        //initialize the board to by an empty 9 by 25 board
        board = new char[9][25];
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                board[r][c] = ' ';
            }
        }
        //create the paddles at each end of the board
        pos1 = 3;
        pos2 = 3;
        for (int i = pos1; i < pos1+3; i++) {
            board[i][0] = '#';
        }
        for (int i = pos2; i < pos2+3; i++) {
            board[i][board.length-1] = '#';
        }
        board[board.length/2][board[0].length/2] = '*';
        
        this.difficulty = difficulty; //set difficulty
    }
    
    public void move(int key) {
        if (key == 0x77) {
            board[pos1-1][0] = '#';
            board[pos1+3][0] = ' ';
            pos1++;
        }
        if (key == 0x73) {
            board[pos1][0] = ' ';
            board[pos1+4][0] = '#';
            pos1++;
        }
    }
    
    public String toString() {
        String ret = "";
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board.length; c++) {
                ret += String.valueOf(board[r][c]);
            }
            ret += "\n";
        }
        return ret;
    }
    
    public void play() {
        System.out.println("\033[2J");
        System.out.println(this);
    }

    public void wait(int millis){
	try {
	    Thread.sleep(millis);
	}
	catch (InterruptedException e) {
	}
    }
    
    //Thanks to Graham King from darkcoding.net for the lesson on making the terminal interactive
    private static String ttyConfig;

    public static void main(String[] args) {
    Pong p = new Pong(0);
	try {
	    setTerminalToCBreak();
	    while (true) {
		  if (System.in.available() != 0) { //if a button is pressed:
		      int key = System.in.read();
		      if (key == 0x1B) {
			  break;
		      }
		      p.move(key);
		  }
		  p.wait(500);
		  p.play();
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

import java.util.*;
import java.io.*;

public class Snake{

    int x,y;
    int length;
    int xbound,ybound; // walls    

    public Snake(){
	x = 0;
	y = 0;
	length = 1;
    }

    public class Obstacle{
	int x,y;
	
    }

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



}

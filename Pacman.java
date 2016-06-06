import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;

//color references
//http://stackoverflow.com/questions/1448858/how-to-color-system-out-println-output

public class Pacman {
    private int score;
    private int lives;
    private int pacX;
    private int pacY;
    private int timer;
    private int direction;
    private char[][] board;
    private Ghost[] ghosts;
    private Ghost red;
    private Ghost blue;
    private Ghost pink;
    private Ghost orange;
    private boolean superPac; //haha

    public Pacman() {
	score = 0;
	lives = 3;
	//board setup
	board = new char[36][28];
	setup();
    }

    public boolean play() {
	System.out.println("\033[2J");
	System.out.println(this);
	System.out.println();
	System.out.println("red");
        red.go();
	try {
	    Thread.sleep(10);
	} catch (InterruptedException e) {
	    System.out.println("InterruptedException");
	}
	System.out.println();
	System.out.println("blue");
	blue.go();
	try {
	    Thread.sleep(10);
	} catch (InterruptedException e) {
	    System.out.println("InterruptedException");
	}
	System.out.println();
	System.out.println("pink");
	pink.go();
	try {
	    Thread.sleep(10);
	} catch (InterruptedException e) {
	    System.out.println("InterruptedException");
	}
	System.out.println();
	System.out.println("orange");
	orange.go();
	try {
	    Thread.sleep(10);
	} catch (InterruptedException e) {
	    System.out.println("InterruptedException");
	}
	checkTeleport();
	checkTime();
	updateGhosts();
	try {
	    Thread.sleep(1000);
	} catch (InterruptedException e) {
	    System.out.println("InterruptedException");
	}
	try {
	    if (System.in.available() != 0) {
		int key = System.in.read();
		if (Math.abs(getDir(key)-direction) != 2) {
		    direction = move(getDir(key));
		}
	    }
	    else {
		move(direction);
	    }
	} catch (IOException e) {
	    System.out.println("IOException");
	}
	timer++;
	updateScore();
        updateLives();
        return checkGameOver();
    }

    public void updateScore() {
        board[2][14] = Character.forDigit(score/10,10);
	board[2][15] = Character.forDigit(score%10,10);
    }
    
    public void updateLives() {
        for (Ghost g : ghosts) {
	    if (g.getX() == pacX && g.getY() == pacY) {
		die();
	    }
	}
    }

    public void updateGhosts() {
        board[red.getY()][red.getX()] = 'R';
	board[blue.getY()][blue.getX()] = 'B';
	board[pink.getY()][pink.getX()] = 'P';
	board[orange.getY()][orange.getX()] = 'O';
    }
    
    public void die() {
	try {
	    Thread.sleep(3000);
	}
	catch (InterruptedException e) {
	    System.out.println("InterruptedException");
	}
	System.out.println("\033[2J");
	for (int i = 0; i < 18; i++) {
	    System.out.println();
	}
	System.out.println("YOU DIED");
	for (int i = 19; i < 36; i++) {
	    System.out.println();
	}
	try {
	    Thread.sleep(3000);
	}
	catch (InterruptedException e) {
	    System.out.println("InterruptedException");
	}
	lives--;
	setup();
    }
    
    public void setup() {
	pacX = 13;
	pacY = 26;
	timer = 0;
	direction = 0;
	superPac = false;
	red = new Ghost(14,14);
	blue = new Ghost(12,17);
	pink = new Ghost(14,17);
	orange = new Ghost(16,17);
	ghosts = new Ghost[4];
	ghosts[0] = red;
	ghosts[1] = blue;
	ghosts[2] = pink;
	ghosts[3] = orange;
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
	for (int c = 0; c < 28; c++) {
	    if (c != 6 && c != 21) {
		board[17][c] = ' ';
	    }
	}
	board[12][12] = ' ';
	board[12][15] = ' ';
	board[13][12] = ' ';
	board[13][15] = ' ';
	board[22][9] = ' ';
	board[22][18] = ' ';
	for (int c = 9; c < 19; c++) {
	    board[14][c] = ' ';
	    board[20][c] = ' ';
	}
	for (int r = 15; r < 22; r++) {
	    board[r][9] = ' ';
	    board[r][18] = ' ';
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
	for (int r = 16; r < 19; r++) {
	    for (int c = 11; c < 17; c++) {
		board[r][c] = ' ';
	    }
	}
	//pacman
	board[pacY][pacX] = '<';
	//score
	board[0][11] = 'S';
	board[0][12] = 'C';
	board[0][13] = 'O';
	board[0][14] = 'R';
	board[0][15] = 'E';
	board[2][14] = Character.forDigit(score/10,10);
	board[2][15] = Character.forDigit(score%10,10);
	board[20][11] = 'R';
	board[20][12] = 'E';
	board[20][13] = 'A';
	board[20][14] = 'D';
	board[20][15] = 'Y';
	board[20][16] = '!';
	board[35][0] = 'L';
	board[35][1] = 'I';
	board[35][2] = 'V';
	board[35][3] = 'E';
	board[35][4] = 'S';
	board[35][5] = ':';
	board[35][7] = '<';
	board[35][8] = '3';
	if (lives >= 2) {
	    board[35][10] = '<';
	    board[35][11] = '3';
	}
	else {
	    board[35][10] = ' ';
	    board[35][11] = ' ';
	}
	if (lives == 3) {
	    board[35][13] = '<';
	    board[35][14] = '3';
	}
	else {
	    board[35][13] = ' ';
	    board[35][14] = ' ';
	}
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
	board[15][13] = '=';
	board[15][14] = '=';
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
	board[6][2] = 'o';
	board[6][26] = 'o';
	board[28][2] = 'o';
	board[28][26] = 'o';
    }
    public boolean checkGameOver() { //true if player can continue playing, false otherwise
	if (lives == 0) {
	    gameOver();
	    return false;
	}
	return true;
    }
    public void gameOver() {
        System.out.println("\033[2J");
	for (int i = 0; i < 18; i++) {
	    System.out.println();
	}
	System.out.println("GAME OVER");
	System.out.println("SCORE: "+score);
	for (int i = 20; i < 36; i++) {
	    System.out.println();
	}
    }
    public void checkTeleport() {
	if (pacX == 0 && pacY == 17 && direction == 2) {
	    board[pacY][pacX] = ' ';
	    pacX = 27;
	    board[pacY][pacX] = '<';
	}
	if (pacX == 27 && pacY == 17 && direction == 0) {
	    board[pacY][pacX] = ' ';
	    pacX = 0;
	    board[pacY][pacX] = '<';
	}
	for (Ghost g : ghosts) {
	    if (g.getX() == 0 && g.getY() == 17 && g.getDirection() == 2) {
	        g.setDirection(0);
	    }
	    if (g.getX() == 27 && g.getY() == 17 && g.getDirection() == 0) {
		g.setDirection(2);
	    }
	}
    }
    public void checkTime() {		
	ghosts[0].setFreedom(true);
	if (timer == 5) {
	    board[20][11] = ' ';
	    board[20][12] = ' ';
	    board[20][13] = ' ';
	    board[20][14] = ' ';
	    board[20][15] = ' ';
	    board[20][16] = ' ';
	}
	if (timer == 10) {
	    ghosts[1].setFreedom(true);
	    ghosts[1].setDirection((int)(Math.random()*2)*2);
	    ghosts[1].setYX(14,14);
	}
	if (timer == 20) {
	    ghosts[2].setFreedom(true);
	    ghosts[2].setDirection((int)(Math.random()*2)*2);
	    ghosts[2].setYX(14,14);
	}
	if (timer == 30) {
	    ghosts[3].setFreedom(true);
	    ghosts[3].setDirection((int)(Math.random()*2)*2);
	    ghosts[3].setYX(14,14);
	}
    }
    public int move(int dir) {
	if (dir == 0 && pacX < 27 && board[pacY][pacX+1] != '=') {
	    board[pacY][pacX] = ' ';
	    pacX++;
	    if (board[pacY][pacX] == '*') {
		score++;
	    }
	    board[pacY][pacX] = '<';
	    return 0;
	}
	else if (dir == 1 && pacY > 0 && board[pacY-1][pacX] != '=') {
	    board[pacY][pacX] = ' ';
	    pacY--;
	    if (board[pacY][pacX] == '*') {
		score++;
	    }
	    board[pacY][pacX] = 'v';
	    return 1;
	}
	else if (dir == 2 && pacX > 0 && board[pacY][pacX-1] != '=') {
	    board[pacY][pacX] = ' ';
	    pacX--;
	    if (board[pacY][pacX] == '*') {
		score++;
	    }
	    board[pacY][pacX] = '>';
	    return 2;
	}
	else if (dir == 3 && pacY < 35 && board[pacY+1][pacX] != '=' && board[pacY+1][pacX] != '-') {
	    board[pacY][pacX] = ' ';
	    pacY++;
	    if (board[pacY][pacX] == '*') {
		score++;
	    }
	    board[pacY][pacX] = '^';
	    return 3;
	}
	else {
	    if (direction == 0 && pacX < 27 && board[pacY][pacX+1] != '=') {
		board[pacY][pacX] = ' ';
		pacX++;
		if (board[pacY][pacX] == '*') {
		    score++;
		}
		board[pacY][pacX] = '<';
		return 0;
	    }
	    else if (direction == 1 && pacY > 0 && board[pacY-1][pacX] != '=') {
		board[pacY][pacX] = ' ';
		pacY--;
		if (board[pacY][pacX] == '*') {
		    score++;
		}
		board[pacY][pacX] = 'v';
		return 1;
	    }
	    else if (direction == 2 && pacX > 0 && board[pacY][pacX-1] != '=') {
		board[pacY][pacX] = ' ';
		pacX--;
		if (board[pacY][pacX] == '*') {
		    score++;
		}
		board[pacY][pacX] = '>';
		return 2;
	    }
	    else if (direction == 3 && pacY < 35 && board[pacY+1][pacX] != '=') {
		board[pacY][pacX] = ' ';
		pacY++;
		if (board[pacY][pacX] == '*') {
		    score++;
		}
		board[pacY][pacX] = '^';
		return 3;
	    }
	}
	return direction;
    }
    public int getDir(int key) {
	if (key == 0x64) { //d
	    return 0;
	}
	if (key == 0x77) { //w
	    return 1;
	}
	if (key == 0x61) { //a
	    return 2;
	}
	if (key == 0x73) { //s
	    return 3;
	}
	return 0;
    }

    private String color(int foreground,int background){
	return ("\033[0;" + foreground + ";" + background + "m");
    }

    public String toString() {
        String ret = "";
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c] == '=') {
		    ret += color(30,47)+String.valueOf(board[r][c]) + " " +color(30,47);
		} else if (board[r][c] == '<' || board[r][c] == 'v' || board[r][c] == '>' || board[r][c] == '^') {
		    if (r < 34) {
			ret += color(33,40)+String.valueOf(board[r][c]) + " "+color(30,47);
		    } else {
			ret += color(37,44)+String.valueOf(board[r][c]) + " "+color(30,47);
		    }
		} else {
		    ret += color(37,44)+String.valueOf(board[r][c]) + " "+color(30,47);
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
	private boolean onEdible;
	private int movementMode; //scatter: 0, chase: 1, frightened: 2

	public Ghost(int ghostX, int ghostY) {
	    this.ghostX = ghostX;
	    this.ghostY = ghostY;
	    direction = (int)(Math.random()*2);
	    if (direction == 1) {
		direction = 2;
	    }
	    freedom = false;
	    onEdible = false;
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
	public int getDirection() {
	    return direction;
	}
	
	public int getX() {
	    return ghostX;
	}
	
	public int getY() {
	    return ghostY;
	}

	public void setYX(int y, int x) {
	    board[ghostY][ghostX] = ' ';
	    ghostY = y;
	    ghostX = x;
	}
	
	public int move(int dir) {
	    if (freedom) {
		if (onEdible) {
		    board[ghostY][ghostX] = '*';
		}
		else {
		    board[ghostY][ghostX] = ' ';
		}
		if (dir == 0 && ghostX < 27 && board[ghostY][ghostX+1] != '=') {
		    if (board[ghostY][ghostX+1] == '*') {
			onEdible = true;
		    }
		    else {
			onEdible = false;
		    }
		    ghostX++;
		    return 0;
		}
		if (dir == 1 && ghostY > 0 && board[ghostY-1][ghostX] != '=') {
		    if (board[ghostY-1][ghostX] == '*') {
			onEdible = true;
		    }
		    else {
			onEdible = false;
		    }
		    ghostY--;
		    return 1;
		}
		if (dir == 2 && ghostX > 0 && board[ghostY][ghostX-1] != '=') {
		    if (board[ghostY][ghostX-1] == '*') {
			onEdible = true;
		    }
		    else {
			onEdible = false;
		    }
		    ghostX--;
		    return 2;
		}
		if (dir == 3 && ghostY < 35 && board[ghostY+1][ghostX] != '=' && board[ghostY+1][ghostX] != '-') {
		    if (board[ghostY+1][ghostX] == '*') {
			onEdible = true;
		    }
		    else {
			onEdible = false;
		    }
		    ghostY++;
		    return 3;
		}
	    }
	    return dir;
	}
	
	public void go() {
	    if (atIntersection() == 0) {
	        int holddir = direction;
		while (direction == holddir || Math.abs(holddir-direction) == 2) {
		    direction = (int)(Math.random()*4);
		}
		direction = move(direction);
	    }
	    else if (atIntersection() == 1) {
		if (board[ghostY][ghostX+1] != '=' && board[ghostY-1][ghostX] != '=' && board[ghostY][ghostX-1] != '=') {
		    int holddir = direction;
		    if (holddir == 0) {
			holddir = 2;
		    }
		    else if (holddir == 1) {
			holddir = 3;
		    }
		    else if (holddir == 2) {
			holddir = 0;
		    }
		    else if (holddir == 3) {
			holddir = 1;
		    }
		    direction = (int)(Math.random()*4);
		    while (direction == holddir || direction == 3) {
			direction = (int)(Math.random()*4);
		    }
		    direction = move(direction);
		}
		if (board[ghostY][ghostX+1] != '=' && board[ghostY+1][ghostX] != '=' && board[ghostY][ghostX-1] != '=') {
		    int holddir = direction;
		    if (holddir == 0) {
			holddir = 2;
		    }
		    else if (holddir == 1) {
			holddir = 3;
		    }
		    else if (holddir == 2) {
			holddir = 0;
		    }
		    else if (holddir == 3) {
			holddir = 1;
		    }
		    direction = (int)(Math.random()*4);
		    while (direction == holddir || direction == 1) {
			direction = (int)(Math.random()*4);
		    }
		    direction = move(direction);
		}
		if (board[ghostY][ghostX+1] != '=' && board[ghostY-1][ghostX] != '=' && board[ghostY+1][ghostX] != '=') {
		    int holddir = direction;
		    if (holddir == 0) {
			holddir = 2;
		    }
		    else if (holddir == 1) {
			holddir = 3;
		    }
		    else if (holddir == 2) {
			holddir = 0;
		    }
		    else if (holddir == 3) {
			holddir = 1;
		    }
		    direction = (int)(Math.random()*4);
		    while (direction == holddir || direction == 2) {
			direction = (int)(Math.random()*4);
		    }
		    direction = move(direction);
		}
		if (board[ghostY][ghostX-1] != '=' && board[ghostY-1][ghostX] != '=' && board[ghostY+1][ghostX] != '=') {
		    int holddir = direction;
		    if (holddir == 0) {
			holddir = 2;
		    }
		    else if (holddir == 1) {
			holddir = 3;
		    }
		    else if (holddir == 2) {
			holddir = 0;
		    }
		    else if (holddir == 3) {
			holddir = 1;
		    }
		    direction = (int)(Math.random()*4);
		    while (direction == holddir || direction == 0) {
			direction = (int)(Math.random()*4);
		    }
		    direction = move(direction);
		}
	    }
	    else if (atIntersection() == 2) {
		if (board[ghostY][ghostX-1] != '=' && board[ghostY+1][ghostX] != '=') {
		    if (direction == 0) {
			direction = move(3);
		    }
		    if (direction == 1) {
			direction = move(2);
		    }
		}
		if (board[ghostY+1][ghostX] != '=' && board[ghostY][ghostX+1] != '=') {
		    if (direction == 1) {
			direction = move(0);
		    }
		    if (direction == 2) {
			direction = move(3);
		    }
		}
		if (board[ghostY][ghostX+1] != '=' && board[ghostY-1][ghostX] != '=') {
		    if (direction == 2) {
			direction = move(1);
		    }
		    if (direction == 3) {
			direction = move(0);
		    }
		}
		if (board[ghostY-1][ghostX] != '=' && board[ghostY][ghostX-1] != '=') {
		    if (direction == 3) {
			direction = move(2);
		    }
		    if (direction == 0) {
			direction = move(1);
		    }
		}
	    }
	    else {
		direction = move(direction);
	    }
	    System.out.println("dir:"+direction);
	    System.out.println("x:"+ghostX);
	    System.out.println("y:"+ghostY);
	}
	
	public int atIntersection() { //0 = complete intersection, 1 = 3 choices, 2 = elbow, 3 = straightaway
	    if (board[ghostY][ghostX+1] != '=' && board[ghostY-1][ghostX] != '=' && board[ghostY][ghostX-1] != '=' && board[ghostY+1][ghostX] != '=') {
		return 0;
	    }
	    else if ((board[ghostY][ghostX+1] != '=' && board[ghostY-1][ghostX] != '=' && board[ghostY][ghostX-1] != '=') ||
		     (board[ghostY][ghostX+1] != '=' && board[ghostY+1][ghostX] != '=' && board[ghostY][ghostX-1] != '=') || 
		     (board[ghostY][ghostX+1] != '=' && board[ghostY-1][ghostX] != '=' && board[ghostY+1][ghostX] != '=') || 
		     (board[ghostY][ghostX-1] != '=' && board[ghostY-1][ghostX] != '=' && board[ghostY+1][ghostX] != '=')) {
		return 1;
	    }
	    else if ((board[ghostY][ghostX+1] != '=' && board[ghostY-1][ghostX] != '=') ||
		     (board[ghostY-1][ghostX] != '=' && board[ghostY][ghostX-1] != '=') ||
		     (board[ghostY][ghostX-1] != '=' && board[ghostY+1][ghostX] != '=') ||
		     (board[ghostY+1][ghostX] != '=' && board[ghostY][ghostX+1] != '=')) {
		return 2;
	    }
	    else {
		return 3;
	    }
	}
    }
    
    public int pacman() {
	try {
	    setTerminalToCBreak();
	    while (true) {
		if (!play()) {
		    return score;
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

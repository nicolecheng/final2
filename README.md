<!DOCTYPE html><html>
# final2 <br>
final project semester 2 <br> <br>

Nicole Cheng & Noah Fichter <br>
MKS22X / Period 6 <br> <br>

Team Nikoahla <br> <br>

 That 80s Game <br>
This is a series of terminal-based 80s games. <br>
The user will be able to make an account to keep track of high scores and stats <br>
(or the high scores and the names will be displayed like in arcade games). <br><br>

Development Log <br><ul>
    <li>
      05/11/16: We decided on a project idea and the games that would be included.
    </li>
    <li>
      05/12/16: Start of README.md -- putting together the actual plan.
    </li>
    <li>
      05/14/16: Noah - began Pong, mainly working on interaction with the terminal as well as frequently updating (animation)
    </li>
    <li>
      05/15/16: Noah - actually worked on Pong - player's paddle movement works, need to add in ball movement + physics as well as enemy AI
    </li>
    <li>
      05/15/16: Nicole - got through most of Snake; need to make it a run with interactive controls
    </li>
    <li>
      05/16/16: Nicole - snake working; need to make snake grow create tutorial / game over pages
    </li>
    <li>
      05/16/16: We came up with a rad team name.
    </li>
    <li>
      05/16/16: Noah - still need to work on synchronizing visual with internal
    </li>
    <li>
      05/17/16: Noah - fixed wait function to not actually stop the game, need to place it correctly to sync visual and internal
    </li>
    <li>
      05/20/16: Nicole - started tutorial program's structure and storyline of the game
    </li>
    <li>
      05/21/16: Nicole - wrote beginning segment of the tutorial (up until the character reaches the snake game)
    </li>
    <li>
       05/22/16: Nicole - added a lot more to the tutorial to include 2 games, and I added a trivia segment.
     </li>
     <li>
       05/22/16: Noah - began working on platformer, obstacles not working but player jumping works
     </li>
     <li>
       05/23/16: Noah - worked on platformer, obstacles moving but getting sort of confused in terms of their positions (stopping randomly). To do: make it so that it doesn't queue up input commands on terminal (i.e. the w key to jump) so you can only do it once per frame, aka 10 times per second
     </li>
     <li>
       05/23/16: Nicole - worked on integrating Snake into the tutorial; fixed issue of the wild running terminal; tweaked text inputs in tutorial to be less crash-prone
     </li>
     <li>
       05/23/16: Noah - finished platformer, need to make logistics work (i.e. no more holding jump, obstacles coming at reasonable intervals, etc)
     </li>
     <li>
       05/24/16: Nicole - put everything together to be run from Tutorial.java; couple of bug fixes done; added to tutorial
     </li>
     <li>
     	05/26/16: Noah - fixed infinite jumping, changed score to be every obstacle rather than time, everything working without BUGS but could be optimized
     </li>
     <li>
     	05/26/16: Nicole - added length to snake, but it's triggering gameOver() at wrong times
     </li>
     <li>
     	05/29/16: Noah - finished up lasting errors in Pong (still need to fix certain key errors e.g. w key not registering) no official bugs as of now
     </li>
     <li>
       05/29/16: Nicole - fixed the bug in Snake that didn't let anyone win; found a new bug, which triggers the gameOver() at wrong times.
     </li>
     <li>
     	05/30/16: Noah - began Pacman! setting up board, Ghosts, player
     </li>
     <li>
     	06/01/16: Nicole - did some debugging; began Doggie.java, with its basic outlines and scanners.
     </li>
     <li>
     	06/02/16: Nicole - finally got the ascii dog down (without those tedious "illegal characters"; made the toString() to return the doggie and its stats to the user via the terminal.
     </li>
     <li>
     	06/02/16: Noah - continued Pacman board
     </li>
     <li>
     	06/03/16: Noah - continued Pacman board, began movement basics
     </li>
     <li>
     	06/03/16: Nicole - worked on skeleton of Doggie.java; instructions of Doggie.java done
     </li>
     <li>
     	06/04/16: Noah - added lives and death sequence to Pacman
     </li>
     <li>
     	06/05/16: Noah - made it so all the ghosts move pseudo-randomly in Pacman
     </li>
     <li>
     	06/05/16: Nicole - added interactions with the dog -- eat, sleep and play; Doggie.java done; Tutorial.java almost done (just needs to integrate Pong and Pacman)
     </li>
     <li>
     	06/06/16: Noah - completed Pacman, Pong, and Dino fully!
     </li>
     <li>
     	06/06/16: Nicole - debugged Snake completely i think!
     </li>
     <li>
     	06/07/16: Nicole - Snake, Doggie, Tutorial finally DONE. Tutorial includes all components.
     </li>
 </ul><br><br>

 Project plan/outline including your goals prioritized by importance and chronology <br>
 (things to do + things already done)<br>
 Things to do: <br><ul><li>
	 individual games (for demo)<li>
		 which games are we going to recreate?</li><li>
		 tetris: create class of shapes, user terminal input / controls, score keeping </li><li>
		 pacman: series of boards, class of pacman and other little monster thingies</li><li>
		 nintendogs: def need to keep track of user's dog and whatnot</li><li>
		 pingpong: AI of some sort?</li></li><li>
	 a welding factor for the various components</li><li>
	 powerups and cheat codes</li>
 </ul><br>
 Things already done:<br><ul>
	 <li>
	   Snake
	 </li>
	 <li>
	   Tutorial
	 </li>
	 <li>
	   Pong
	 </li>
	 <li>
	   Platformer
	 </li>
	 <li>
	   Pacman 
	 </li>
	 <li>
	   Doggie
	 </li>
	 <li>
	   Lowered thresholds and special K (ha!) options to skip the tedious task of playing games (how dreadful!)
	 </li>
	 <li>
	   DONE.
	 </li>
</ul><br><br>

Links to the Demo versions, <br> 
what they show about the state of your project, <br> 
Directions on how to compile/run each version. <br>
(e.g. what to do to clone the right version) <br> <br>
<ul>
	<li>
	  Clone final2: https://github.com/nicolecheng/final2.git
	</li>
	<li>
	  Compile and run Tutorial.java.
	</li>
</ul>

</html>

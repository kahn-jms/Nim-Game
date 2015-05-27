/*
  NimAIPlayer.java
  
  This class is provided as a skeleton code for the tasks of 
  Sections 2.3, 2.4 and 2.5 in Project C. Add code (do NOT delete any) to it
  to finish the tasks. 
  
  Coded by: Jin Huang
  Modified by: Jianzhong Qi, 29/04/2015
*/

import java.util.*;

public class NimAIPlayer extends NimPlayer implements Testable {
  // you may further extend a class or implement an interface
  // to accomplish the task in Section 2.3  

  public NimAIPlayer() {
        
  }
  
  // Player returns how many stones to remove
  public int removeStone(Scanner numRev, int remStones, int maxRem)
  {
    for(int i=1; (i<=maxRem&&i<remStones) ; i++)
    {
      if ((remStones - i - 1)%(maxRem + 1) == 0)
      {
        //System.out.println("AI");
        return i;
      }
    }

    // AI couldn't find number which satisfies guaranteed win
    // Stall for time!
    return 1;
  }



  public String advancedMove(boolean[] available, String lastMove) {
    // the implementation of the victory
    // guaranteed strategy designed by you
    String move = "";
    
    return move;
  }
}

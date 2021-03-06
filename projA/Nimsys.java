/* Nimsys.java
 * This class controls the overall Nim game process
 * Author: James Kahn (391990)
 * Created on 27/3/15
 */

import java.util.*;

public class Nimsys
{
  /* repeatedly displays the remaining stones and asks the players to remove 
   * some stones until there are no stones left.
   * Finally, it prints the .Game over. message and the game winner.
   */
  static public void main(String[] args)
  {
    int upperBound = 0;
    int numStones = 0;
    String stoneSym = " *";
    boolean player1Turn = true;
    Scanner kb = new Scanner(System.in); 

    // Print welcome message
    System.out.println("Welcome to Nim");

    // Create player 1
    System.out.println();
    System.out.println("Please enter Player 1's name:");
    NimPlayer player1 = new NimPlayer(kb.next());

    // Create player 2
    System.out.println();
    System.out.println("Please enter Player 2's name:");
    NimPlayer player2 = new NimPlayer(kb.next());

    // Get upper bound of stone removal
    System.out.println();
    System.out.println("Please enter upper bound of stone removal:");
    upperBound = kb.nextInt();

    // Get upper bound of stone removal
    System.out.println();
    System.out.println("Please enter initial number of stones:");
    numStones = kb.nextInt();

   // Start the game
   while (numStones > 0)
  {
    // Print current game status
    System.out.println();
    System.out.print(numStones+" stones left:");
    for (int i=0; i<numStones; i++)
    {
      System.out.print(stoneSym);
    }
    System.out.println();

    // Give correct player their turn
    if (player1Turn)
    {
      numStones -= player1.removeStone(kb);
      player1Turn = false;
    } else
    {
      numStones -= player2.removeStone(kb);
      player1Turn = true;
    }
  }

  // Game has finished, print game over message
  System.out.println();
  System.out.println("Game Over");
  // Print out winner
  if (player1Turn)
  {
    player1.win();
  } else
  {
    player2.win();
  }

  return;
  }
}


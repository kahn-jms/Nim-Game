// James Kahn
// 391990

import java.util.*;

public class Nimsys
{
  static public void main(String[] args)
  {
    int upperBound = 0;
    int numStones = 0;
    String stoneSym = "*";
    boolean player1Turn = true;
    Scanner kb = new Scanner(System.in); 

    // Print welcome message
    System.out.println("Welcome to Nim");

    // Create player 1
    System.out.println("Please enter Player 1's name:");
    NimPlayer player1 = new NimPlayer(kb.next());

    // Create player 2
    System.out.println("Please enter Player 2's name:");
    NimPlayer player2 = new NimPlayer(kb.next());

    // Get upper bound of stone removal
    System.out.println("Please enter upper bound of stone removal:");
    upperBound = kb.nextInt();

    // Get upper bound of stone removal
    System.out.println("Please enter initial number of stones:");
    numStones = kb.nextInt();

   // Start the game
   while (numStones > 0)
  {
    // Print current game status
    stoneSym = new String(new char[numStones]).replace('\0','*');
    System.out.println(numStones+" stones left: "+stoneSym);

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

  System.out.println("Game Over");
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


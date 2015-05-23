/* NimGame.java
 * This class manages game playing process
 * Author: James Kahn (391990)
 * Created on 18/4/15
 */

public class NimGame
{
  // Stone vars
  private int stoneCount, upperBound;

  // Player indexes
  private int player1, player2;

  // NimGame Constructor
  public NimGame() {}


  /* Mutators */

  // Stone Count setter
  public void setStoneCount(int initCount)
  {
    stoneCount = initCount;
    return;
  }

  // Upper bound setter
  public void setUpperBound(int initUpper)
  {
    upperBound = initUpper;
    return;
  }

  // Player 1 setter
  public void setPlayer1(int p1index)
  {
    player1 = p1index;
    return;
  }

  // Player 2 setter
  public void setPlayer2(int p2index)
  {
    player2 = p2index;
    return;
  }


  /* Accessors */

  // Stone Count getter
  public int getStoneCount()
  {
    return stoneCount;
  }

  // Upper bound getter
  public int getUpperBound()
  {
    return upperBound;
  }

  // Player 1 getter
  public int getPlayer1()
  {
    return player1;
  }

  // Player 2 getter
  public int getPlayer2()
  {
    return player2;
  }

  // Print current game status
  public void printStatus()
  {
    System.out.println();
    System.out.print(stoneCount+" stones left:");
    for (int i=0; i<stoneCount; i++)
    {
      System.out.print(" *");
    }
    System.out.println();

    return;
  }

  // Player turn sequence
  public boolean haveTurn(int numRev)
  {
    if ( (numRev<1) || (numRev>upperBound) || (numRev>stoneCount) )
    {
      System.out.println();
      if ( stoneCount < upperBound )
      {
        System.out.println("Invalid move. You must remove between 1 and "+stoneCount+" stones.");
      } else
      {
        System.out.println("Invalid move. You must remove between 1 and "+upperBound+" stones.");
      }

      return false;
    }

    stoneCount -= numRev;
    return true;
  }


}

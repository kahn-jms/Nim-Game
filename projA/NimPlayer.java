/* NimPlayer.java
 * This class controls the overall Nim game process
 * Author: James Kahn (391990)
 * Created on 27/3/15
 */

import java.util.*;

public class NimPlayer
{
  private String name;

  // NimPlayer constructor
  public NimPlayer(String startName)
  {
    name = startName;
  }

  // Return number of stones player wants to remove
  public int removeStone(Scanner numRev)
  {
    // Fetch and return how many stones player wants to remove
    System.out.println(name+"'s turn - remove how many?");
    return numRev.nextInt();
  }

  // Game winning message
  public void win()
  {
    System.out.println(name + " wins!");
    return;
  }
}

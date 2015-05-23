// James Kahn
// 391990

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
    // Fetch how many stones player wants to remove
    System.out.println(name+"'s turn - remove how many?");
    return numRev.nextInt();
  }

  public void win()
  {
    System.out.println(name + " wins!");
    return;
  }
}

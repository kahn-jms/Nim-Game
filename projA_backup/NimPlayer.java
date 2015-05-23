// James Kahn
// 391990

import java.util.*;

public class NimPlayer
{
  String name;

  // NimPlayer constructor
  public NimPlayer(String startName)
  {
    name = startName;
  }

  public int removeStone(Scanner kb)
  {
    // Fetch how many stones player wants to remove
    System.out.println(name+"'s turn - remove how many?");
    int numRev = kb.nextInt();
    return numRev;
  }

  public void win()
  {
    System.out.println(name + " wins!");
    return;
  }
}

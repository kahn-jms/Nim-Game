/* NimPlayer.java
 * This class manages players of the game
 * Author: James Kahn (391990)
 * Created on 18/4/15
 */

import java.util.*;
import java.io.*;

public class NimPlayer implements Serializable
{
  private String userName;

  // Note: Need to be able to edit these
  private String  givenName, familyName;

  // Note: need to be able to reset these
  private int gamesPlayed, gamesWon;

  // NimPlayer constructor
  //static public NimPlayer() {}

  /* Mutators */

  // Username setter
  public void setUserName(String inUname)
  {
    userName = inUname;
    return;
  }

  // Family name setter
  public void setFamName(String inFamName)
  {
    familyName = inFamName;
    return;
  }

  // Given name setter
  public void setGivenName(String inGivenName)
  {
    givenName = inGivenName;
    return;
  }

  // Reset game stats for player
  public void gamesReset()
  {
    gamesPlayed = 0;
    gamesWon = 0;
    return;
  }

  /* Accessors */

  // Username getter
  public String getUserName()
  {
    return userName;
  }

  // Family name getter
  public String getFamName()
  {
    return familyName;
  }

  // Given name getter
  public String getGivenName()
  {
    return givenName;
  }

  // Games played getter
  public int getGamesPlayed()
  {
    return gamesPlayed;
  }

  // Player information getter
  public void printInfo()
  {
    System.out.println
    (
      userName+","+
      givenName+","+
      familyName+","+
      gamesPlayed+" games,"+
      gamesWon+" wins"
    );
    return;
  }

  // Percentage of games won by player
  public int getPercentWon()
  {
    if ( gamesPlayed == 0 )
    {
      return 0;
    }

    // Need to be careful here since round can return 2 types dep. on input
    return Math.round( ((float)gamesWon/gamesPlayed) * 100 );
  }


  // Game winning routine
  public void winGame()
  {
    gamesPlayed += 1;
    gamesWon += 1;
    System.out.println(givenName + " " + familyName + " wins!");
    return;
  }

  // Game losing routine (incase we need an action for losing games later)
  public void loseGame()
  {
    gamesPlayed += 1;
    return;
  }
}

/* Nimsys.java
 * This class controls the overall Nim game process
 * Author: James Kahn (391990)
 * Created on 18/4/15
 */

import java.util.*;

public class Nimsys
{
  /* repeatedly displays the remaining stones and asks the players to remove 
   * some stones until there are no stones left.
   * Finally, it prints the .Game over. message and the game winner.
   */

  // Player list
  static NimPlayer[] playerList = new NimPlayer[100];
  static String[] unameList = new String[100];

  // Scanner object to handle commands (only have one for whole program)
  static Scanner kb = new Scanner(System.in); 

  /* Predefine game commands that can be entered */
  enum Cmds
  {
    ADDPLAYER,
    REMOVEPLAYER,
    EDITPLAYER,
    RESETSTATS,
    DISPLAYPLAYER,
    RANKINGS,
    STARTGAME,
    EXIT,
  }


  static public void main(String[] args)
  {
    String inputLine;
    Cmds inCmd;


    // Print welcome message
    System.out.println("Welcome to Nim");

    // Start the command line
    cmdLoop: while (true)
    {
      System.out.print("\n>");
      // Bit unsure about this as it removes case sensitivity of commands
      // users can enter
      inputLine = kb.nextLine();
      String[] inputSplit = inputLine.split(" ");
      
      // Catch invalid commands (for my ease of use)
      try{
        inCmd = Cmds.valueOf(inputSplit[0].toUpperCase());
      } catch (IllegalArgumentException noCmd)
      {
        System.err.println("Illegal command exception: "+noCmd.getMessage());
        System.out.println(Arrays.asList(Cmds.values()));
        continue;
      }
      

      switch (inCmd)
      {

        // Add new player to list of players
        case ADDPLAYER:
        {
          try
          {
            addPlayer(inputSplit[1]);
          } catch (ArrayIndexOutOfBoundsException noIn)
          {
            System.err.println("Not enough arguments exception: "+noIn.getMessage());
          }
          break;
        }

        // Remove player from list of players
        case REMOVEPLAYER:
        {
          // Check whether user wants to remove one or all players
          if (inputSplit.length == 1)
          {
            // Confirm user wants to remove all players
            System.out.println("Are you sure you want to remove all players? (y/n)");
            String confirmation = kb.nextLine();
            if ( confirmation.equals("y") )
            {
              // Iterate through and remove all players
              for (int i =0; i<unameList.length; i++)
                {
                  if (unameList[i] != null)
                  {
                    removePlayer(unameList[i]);
                  }
                }
             }
          } else
          {
            removePlayer(inputSplit[1]);
          }

          // Re-sort list of players since it's been modified
          Arrays.sort(unameList, Comparator.nullsLast(Comparator.naturalOrder()));
          break;
        }

        // Edit a player's details
        case EDITPLAYER:
        {
          try
          {
            editPlayer(inputSplit[1]);
          } catch (ArrayIndexOutOfBoundsException noIn)
          {
            System.err.println("Not enough arguments exception: "+noIn.getMessage());
          }
          break;
        }

        // Reset a player's gameplay stats
        case RESETSTATS:
        {
          // Check whether user wants to reset stats of one or all players
          if (inputSplit.length == 1)
          {
            // Confirm user wants to reset all players stats
            System.out.println("Are you sure you want to reset all player statistics? (y/n)");
            String confirmation = kb.nextLine();
            if ( confirmation.equals("y") )
            {
              // Iterate through and remove all players
              for (int i =0; i<unameList.length; i++)
                {
                  if (unameList[i] != null)
                  {
                    resetStats(unameList[i]);
                  }
                }
             }
          } else
          {
            resetStats(inputSplit[1]);
          }
          break;
        }

        // Display player information
        case DISPLAYPLAYER:
        {
          // Check whether user wants to display one or all players
          if (inputSplit.length == 1)
          {
            // Iterate through and display all players
            // Since we sort the username list every time it's altered
            // iterating through here will print result in alphabetical order
            for (int i =0; i<unameList.length; i++)
              {
                if (unameList[i] != null )
                {
                  displayPlayer(unameList[i]);
                }
              }
          } else
          {
            displayPlayer(inputSplit[1]);
          }
          break;
        }

        // Print the current leaderboard
        case RANKINGS:
        {
          displayRankings();
          break;
        }
        case STARTGAME:
        {
          try
          {
            playGame(inputSplit[1]);
          } catch (ArrayIndexOutOfBoundsException noIn)
          {
            System.err.println("Not enough arguments exception: "+noIn.getMessage());
          }
          break;
        }
        case EXIT:
        {
          System.out.println();
          break cmdLoop;
        }

        default:
        {
          // Check if anything special needs to be done here
          break;
        }
        
      }

    }




  System.exit(0);
  //return;
  }


  /* Method to add new player to the game */
  static void addPlayer(String newPlayer)
  {

    // Split string on commas
    String[] newNames = newPlayer.split(",");
    //System.out.println("username: " + newNames[0]);
    //System.out.println("famname: " + newNames[1]);
    //System.out.println("firstname: " + newNames[2]);

    // Check player doesn't already exist (can maybe use binary search)
    if ( Arrays.asList(unameList).contains(newNames[0]) )
    {
      System.out.println("The player already exists.");
    } else
    {
      // If not then add to player name list in first empty slot and then sort 
      // the list for when we need to display players
      for (int i=0; i<unameList.length; i++)
      {
        if (unameList[i] == null)
        {
          unameList[i] = newNames[0];
          Arrays.sort(unameList, Comparator.nullsLast(Comparator.naturalOrder()));
          break;
        }
      }

      // And create the actual player instance
      for (int i=0; i<playerList.length; i++)
      {
        if (playerList[i] == null)
        {
          playerList[i] = new NimPlayer();
          // Need to set initial values
          playerList[i].setUserName(newNames[0]);
          playerList[i].setFamName(newNames[1]);
          playerList[i].setGivenName(newNames[2]);
          playerList[i].gamesReset();
          break;
        }
      }
    }

    return;
  }



  /* Method to remove players from player list */
  static void removePlayer(String remPlayer)
  {

    // Check player exists (can maybe use binary search)
    if ( Arrays.asList(unameList).contains(remPlayer) )
    {
      // Delete player instance
      for (int i=0; i<playerList.length; i++)
      {
        if (playerList[i] != null && playerList[i].getUserName().equals(remPlayer))
        {
          // Mark player ref as null, i.e. ready for garbage collection
          playerList[i] = null;
        }
      }

      // Delete player's name from username list and re-sort it
      int remIndex = Arrays.asList(unameList).indexOf(remPlayer);
      unameList[remIndex] = null;
    } else
    {
      System.out.println("The player does not exist.");
    }

    return;
  }


  /* Method to edit player details */
  static void editPlayer(String edPlayer)
  {
    // Split string on commas
    String[] newNames = edPlayer.split(",");

    // Check player exists (can maybe use binary search)
    if ( Arrays.asList(unameList).contains(newNames[0]) )
    {
      // Edit player instance
      for (int i=0; i<playerList.length; i++)
      {
        if (playerList[i] != null && playerList[i].getUserName().equals(newNames[0]))
        {
          playerList[i].setFamName(newNames[1]);
          playerList[i].setGivenName(newNames[2]);
        }
      }
    } else
    {
      System.out.println("The player does not exist.");
    }

    return;
  }


  /* Method to reset player game stats */
  static void resetStats(String resetPlayer)
  {

    // Check player exists (can maybe use binary search)
    if ( Arrays.asList(unameList).contains(resetPlayer) )
    {
      // Reset player instance gameplay stats
      for (int i=0; i<playerList.length; i++)
      {
        if (playerList[i] != null && playerList[i].getUserName().equals(resetPlayer))
        {
          playerList[i].gamesReset();
        }
      }
    } else
    {
      System.out.println("The player does not exist.");
    }

    return;
  }

  
  /* Method to display player information */
  static void displayPlayer(String dispPlayer)
  {

    // Check player exists (can maybe use binary search)
    if ( Arrays.asList(unameList).contains(dispPlayer) )
    {
      // Display player instance gameplay stats
      for (int i=0; i<playerList.length; i++)
      {
        if (playerList[i] != null && playerList[i].getUserName().equals(dispPlayer))
        {
          playerList[i].printInfo();
        }
      }
    } else
    {
      System.out.println("The player does not exist.");
    }

    return;
  }


  /* Method to output current rankings */
  static void displayRankings()
  {
    // Scoreboard will be a list of indexes of players in player list
    // -1 indicates an empty slot on the scoreboard
    int[] scoreBoard = new int[10];
    Arrays.fill(scoreBoard,-1);
    int curPlayerScore = 0;
    String curPlayerUname;
    int curBoardScore = 0;
    String curBoardUname;
    int curPlayerIndex = 0;
    int tempBoardIndex = 0;
    String scoreString;

    // Populate rankings board
    // Check each players scores, place in top ten if they're winning
    for (int i=0; i<playerList.length; i++)
    {
      if (playerList[i] == null)
      {
        continue;
      }

      // Get current players score and index
      curPlayerScore = playerList[i].getPercentWon();
      curPlayerIndex = i;


      // Run through scoreboard to see if player needs to be placed on it
      for (int j=0; j<scoreBoard.length; j++)
      {
        if ( scoreBoard[j] == -1 )
        {
          // Scoreboard slot is empty, place player on scoreboard
          scoreBoard[j] = curPlayerIndex;
          break;
        }

        // ScoreBoard entry is non-empty so get player's score and username
        curBoardScore = playerList[scoreBoard[j]].getPercentWon();
        curBoardUname = playerList[scoreBoard[j]].getUserName();
        curPlayerUname = playerList[curPlayerIndex].getUserName();

        if
        (
          ( curPlayerScore < curBoardScore ) ||
          ( ( curPlayerScore == curBoardScore ) &&
            ( curPlayerUname.compareTo(curBoardUname) > 0) )
        )
        {
          // Current player ranks lower than player on scoreboard or has same 
          // score but has a username that is after the current board player's 
          // alphabetically
          // Skip to next scoreboard entry
          continue;
        }

        // Current player needs to be placed on scoreboard here
        // Change current player to player that was in that place on the 
        // scoreboard, i.e. shift the rest of the players on the board down one 
        // slot
        tempBoardIndex = scoreBoard[j];
        scoreBoard[j] = curPlayerIndex;
        curPlayerIndex = tempBoardIndex;
        curPlayerScore = playerList[curPlayerIndex].getPercentWon();
      }

    }

    // Print scoreboard
    for (int j=0; j<scoreBoard.length; j++)
    {
      // Check if we've reached the end of the scoreboard
      if ( scoreBoard[j] == -1 )
      {
        break;
      }

      curBoardScore = playerList[scoreBoard[j]].getPercentWon();
      scoreString = curBoardScore + "%";

      System.out.printf
      (
        "%-5s| %02d games | " +
        playerList[scoreBoard[j]].getGivenName() +
        " " +
        playerList[scoreBoard[j]].getFamName() +
        "\n",
        scoreString, playerList[scoreBoard[j]].getGamesPlayed()
      );

    }

    return;
  }


  static void playGame(String inOptions)
  {
    // Split option string on commas
    String[] gameOptions = inOptions.split(",");
    
    boolean player1Turn = true;
    NimGame curGame = new NimGame();


    // Check players exist
    if
    ( Arrays.asList(unameList).contains(gameOptions[2]) &&
      Arrays.asList(unameList).contains(gameOptions[3])
    )
    {
      // If players exist initialise game and add them to game
      curGame.setStoneCount(Integer.parseInt(gameOptions[0]));
      curGame.setUpperBound(Integer.parseInt(gameOptions[1]));
      
      for (int i=0; i<playerList.length; i++)
      {
        if (playerList[i] == null)
        {
          continue;
        } else if (playerList[i].getUserName().equals(gameOptions[2]))
        {
          curGame.setPlayer1(i);
        } else if (playerList[i].getUserName().equals(gameOptions[3]))
        {
          curGame.setPlayer2(i);
        }
      }
    } else
    {
      System.out.println("One of the players does not exist.");
      //curGame = null;
      return;
    }


    // Print game info
    System.out.println();
    System.out.println("Initial stone count: "+curGame.getStoneCount());
    System.out.println("Maximum stone removal: "+curGame.getUpperBound());
    System.out.println
    (
      "Player 1: " + 
      playerList[curGame.getPlayer1()].getGivenName() +
      " " +
      playerList[curGame.getPlayer1()].getFamName()
    );
    System.out.println
    (
      "Player 2: " + 
      playerList[curGame.getPlayer2()].getGivenName() +
      " " +
      playerList[curGame.getPlayer2()].getFamName()
    );
    
    // Start the game
    while ( curGame.getStoneCount() > 0)
    {
      // Print stone count
      curGame.printStatus();

      // Give correct player their turn
      // Note that haveTurn returns true if turn was successful
      if (player1Turn)
      {
        System.out.print(playerList[curGame.getPlayer1()].getGivenName());
        System.out.println("'s turn - remove how many?");
        // Note the ! here.
        try
        {
          player1Turn = !curGame.haveTurn(Integer.parseInt(kb.nextLine()));
        } catch (NumberFormatException badNum)
        {
          System.err.println("Bad int exception: "+badNum.getMessage());
        }

      } else
      {
        System.out.print(playerList[curGame.getPlayer2()].getGivenName());
        System.out.println("'s turn - remove how many?");
        
        try
        {
          player1Turn = curGame.haveTurn(Integer.parseInt(kb.nextLine()));
        } catch (NumberFormatException badNum)
        {
          System.err.println("Bad int exception: "+badNum.getMessage());
        }
      }

    }


    // Game has finished, print game over message
    System.out.println();
    System.out.println("Game Over");
    // Print out winner
    if (player1Turn)
    {
      playerList[curGame.getPlayer1()].winGame();
      playerList[curGame.getPlayer2()].loseGame();
    } else
    {
      playerList[curGame.getPlayer2()].winGame();
      playerList[curGame.getPlayer1()].loseGame();
    }

    return;
  }

}

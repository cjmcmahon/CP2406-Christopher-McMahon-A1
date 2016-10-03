import java.util.*;
public class Assignment1
{
    public static void main(String[] args)
    {
//        final int NUM_INIT_CARDS_IN_DECK = 60; // Do we really need to specify this? Could give an XML file with the cards listed
        final String DECK_XML_FILE_STRING = "MstCards_151021.plist";
        final int NUM_INIT_CARDS_IN_HAND = 8;
        MenuItem userMenuChoice;
        int numPlayers;
        System.out.println("Hello, and welcome to the Super Trumps card game!\n");
        for(;;)
        {
            displayMenu();
            userMenuChoice = getUserMenuChoice();
            // Test by printing menu item
//        System.out.println(userMenuChoice);
            // Determine what path to take based on input which may still be invalid
            switch(userMenuChoice)
            {
                case NEW_GAME:
                {
                    // Ask how many players the user wishes to play against
                    System.out.print("Please enter how many players (3 to 5) are going to play this game\n >> ");
                    numPlayers = getValidNumberFromUser(3, 5, "Please enter a valid integer for number of players");
                    STGame game = new STGame(numPlayers, DECK_XML_FILE_STRING, NUM_INIT_CARDS_IN_HAND);
                    // Testing mode: view players hands (just card titles) and player order
//                    game.testSetup();
                    // Tell the user their id number, who the dealer is and the player order
                    System.out.println("You are player 0");
                    System.out.println("The dealer is player " + game.getDealerID());
                    int[] playerOrder = game.getPlayerOrder();
                    String playerOrderString = "The player order is : ";
                    for(int i = 0; i < playerOrder.length - 1; i++)
                    {
                        playerOrderString = playerOrderString + playerOrder[i] + ", ";
                    }
                    playerOrderString = playerOrderString + playerOrder[playerOrder.length - 1];
                    System.out.println(playerOrderString);
                    // Enter main loop
                    gameLoop(game);
                    break;
                }
                case INSTRUCTIONS:
                {
                    displayInstructions();
                    break;
                }
                case EXIT:
                {
                    // Exit the game
                    //return;
                    System.exit(0);
                }
                case INVALID_CHOICE:
                {
                    // TODO: handle invalid menu choice - decide on how to handle
                    break;
                }
            }
        }
    }

    private static void displayInstructions()
    {
        // TODO: Potentially read instructions from file to print to screen or hard code
        System.out.println("Instructions!");
    }

    private static int getValidNumberFromUser(int low, int high, String errorMessage)
    {
        int userInput = 0;
        boolean isInputValid = false;
        String userInputStr;
        Scanner input = new Scanner(System.in);
        // Assume input is invalid even before user types anything, saves a few lines of code here
        while(!isInputValid)
        {
            userInputStr = input.nextLine();
            // If the user input matches a string of digits
            if(userInputStr.matches("\\d+"))
            {
                userInput = Integer.parseInt(userInputStr);
                if(userInput >= low && userInput <= high)
                {
                    // Input is valid number and within the range
                    isInputValid = true;
                }
            }
            else
            {
                // Display error message
                System.out.print(errorMessage + "\n>> ");
            }
        }
        return userInput;
    }

    private static MenuItem getUserMenuChoice()
    {
        // This function doesn't print the menu, but rather is called after the menu has been printed
        // This said, we don't need to print it here.
        int userChoice;
        userChoice = getValidNumberFromUser(1, 3, "That input was invalid, please enter one of the following:\n1 - New Game"
            + "\n2 - View Instructions\n3 - Exit");
        for(MenuItem menuItem : MenuItem.values())
        {
            if(userChoice == menuItem.ordinal())
            {
                return menuItem;
            }
        }
        // This code should only run if no match was found, force invalid
        // Even so, this code shouldn't be reachable, but is here just in case
        return MenuItem.INVALID_CHOICE;
    }

    private static void displayMenu()
    {
        System.out.println("Main Menu\nMake a selection by typing in the appropriate number.");
        System.out.print("1 - Start new game\n2 - View Instructions\n3 - Exit\n>> ");
    }

    private static void gameLoop(STGame game)
    {
        // Define some game logic flags (eg. what the current card type is, is the game running etc...)
        boolean isGameRunning = true; // Set to true initially as we are starting the game, hence it should be running
        CardPlayType currentCardPlayType = CardPlayType.NULL; // Keeps track of the category in play currently
                                                              // Initialise to NULL as nothing has been played
        int lastPlayerID; // Keeps track of the last player to have played a card
        int[] playerOrder = game.getPlayerOrder();
        int currentPlayer = playerOrder[0];
        boolean playerPlayedTurn;
        // Show player their initial cards
        System.out.println("Your initial hand is :");
        ArrayList<STCard> tmpList = game.players[0].getPlayerHand();
        for(int i = 0; i < tmpList.size(); i++)
        {
            System.out.println(tmpList.get(i));
        }
        // Start initial round
        game.players[currentPlayer].startRound();
        while(isGameRunning)
        {
            playerPlayedTurn = game.players[currentPlayer].playTurn();
            if(playerPlayedTurn)
            {
                lastPlayerID = currentPlayer;
            }
            currentPlayer++;
            // Ensure we don't go out of bounds
            currentPlayer = currentPlayer % game.numPlayers;
        }
    }
}

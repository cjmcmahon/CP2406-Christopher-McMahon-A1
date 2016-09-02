/**
 * Created by Chris on 2/09/2016.
 */
import java.util.*;
public class Assignment1
{
    public static void main(String[] args)
    {
        MenuItem userMenuChoice;
        int numPlayers;
        System.out.println("Hello, and welcome to the Super Trumps card game!\n");
        // TODO: Add code from here on into infinite for loop to return to menu after menu choice until user exits
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
                STGame game = new STGame(numPlayers);
                game.newGame();
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
                return;
            }
            case INVALID_CHOICE:
            {
                // TODO: handle invalid menu choice - decide on how to handle
                break;
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
        int userChoice;
        userChoice = getValidNumberFromUser(1, 3, "That input was invalid, please enter one of the following:\n1 - New Game"
            + "\n2 - View Instructions\n3 - Exit");
        for(MenuItem mnuItm : MenuItem.values())
        {
            if(userChoice == mnuItm.ordinal())
            {
                return mnuItm;
            }
        }
        // This code should only run if no match was found, force invalid
        return MenuItem.INVALID_CHOICE;
    }

    private static void displayMenu()
    {
        System.out.println("Main Menu\nMake a selection by typing in the appropriate number.");
        System.out.print("1 - Start new game\n2 - View Instructions\n3 - Exit\n>> ");
    }
}
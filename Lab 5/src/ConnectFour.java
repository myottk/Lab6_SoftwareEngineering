import java.util.*;
/*
3/9/2020
New comment for Lab 6
 */

/*
3/9/2020
Additional comment
 */

public class ConnectFour
{
    static char[][] connBoard;
    public static void main(String args[])
    {
        int boardColumns = 0;
        int boardRows = 0;
        boolean isWinner = false;
        int playCount = 0; //Count chips placed
        String player = "Player 1";
        Scanner userInput = new Scanner(System.in);
        //Initialize board layout
        System.out.print("What would you like the height of the board to be? ");
        boardRows = userInput.nextInt();
        System.out.print("What would you like the length of the board to be? ");
        boardColumns = userInput.nextInt();

        connBoard = new char [boardRows] [boardColumns];

        initializeBoard(connBoard);
        printBoard(connBoard); //Print board and set all spots to '-'

        System.out.println("Player 1: x");
        System.out.println("Player 2: o");

        while(!isWinner) //Loop while no winner
        {
            playCount++;

            if(playCount == (boardRows * boardColumns) + 1) //If there are no slots left, its a draw
            {
                isWinner = true;
                System.out.println("Draw. Nobody wins.");
                break;
            }

            int selectCol = 0;
            char chipType = 'x';
            System.out.print(player + ": Which column would you like to choose? ");
            selectCol = userInput.nextInt();

            if(player.equals("Player 1")) //Set chip types for players
            {
                chipType = 'x';
            }
            else
            {
                chipType = 'o';
            }

            int placedRow = insertChip(connBoard, selectCol, chipType);
            printBoard(connBoard);

            if(checkIfWinner(connBoard, selectCol, placedRow, chipType))
            {
                isWinner = true;
                System.out.println(player + " won the game!");
                break;
            }

            if(player.equals("Player 1")) //Swap players at end of round
            {
                player = player.replace("1", "2");
            }
            else
            {
                player = player.replace("2", "1");
            }
        }
    }

    public static void printBoard(char[][] array) //Print board by looping through it
    {
        for(int i = array.length - 1; i > - 1; i--)
        {
            for(int j = 0; j < array[0].length; j++)
            {
                System.out.print(array[i][j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public static void initializeBoard(char[][] array)
    {
        for(int i = array.length - 1; i > -1; i--)
        {
            for(int j = 0; j < array[0].length; j++)
            {
                array[i][j] = '-';
            }
        }
        connBoard = array;
    }

    public static int insertChip(char[][] array, int col, char chipType)
    {
        for(int i = 0; i < array.length; i++)
        {
            char chip = array[i][col];
            if(chip == '-')
            {
                array[i][col] = chipType;
                connBoard = array;
                return i;
            }
        }

        return -1;
    }

    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType)
    {
        int chipStreak = 0;
        for(int i = 0; i < array.length; i++) //check row
        {
            char chip = array[i][col];
            if(chip == chipType)
            {
                chipStreak ++;
            }
        }

        if(chipStreak == 4)
        {
            return true;
        }

        chipStreak = 0;

        for(int i = 0; i < array[0].length; i++) //check row
        {
            char chip = array[row][i];
            if(chip == chipType)
            {
                chipStreak ++;
            }
        }

        if(chipStreak == 4)
        {
            return true;
        }

        return false;
    }
}
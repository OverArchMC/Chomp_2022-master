import java.awt.*;
import java.util.*;

public class MyPlayer {
    public Chip[][] gameBoard;
    public int[] columns;

    public MyPlayer() {
        columns = new int[10];

        /***
         * This code will run just once, when the game opens.
         * Add your code here.
         */

        //HW 1
        int[] _3x3Possibilities = new int[]{333, 332, 331, 330, 322, 321, 320, 311, 310, 300, 222, 221,
                                            220, 211, 210, 200, 111, 110, 100};

        for(int i = 0; i < _3x3Possibilities.length; i++){
            //System.out.println(_3x3Possibilities[i]);
        }

        //prints every possible board combination within a 3x3 grid
        for(int i = 1; i <= 3; i++){
            for(int j = 0; j <= i; j++){
                for(int k = 0; k <= j; k++){
                    /*if(j<=i && k<= j && k <= i){
                        System.out.println((i+"") + (j+"") + (k+""));
                    }*/
                    System.out.println((i+"") + (j+"") + (k+""));
                }
            }
        }
    }

    public Point move(Chip[][] pBoard) {

        System.out.println("MyPlayer Move");

        gameBoard = pBoard;
        int column = 0;
        int row = 0;

        row = 1;
        column = 1;

        /***
         * This code will run each time the "MyPlayer" button is pressed.
         * Add your code to return the row and the column of the chip you want to take.
         * You'll be returning a data type called Point which consists of two integers.
         */

        Point myMove = new Point(row, column);
        return myMove;
    }

    public ArrayList<int[]> printPossibilitiesOneStepAway(int[] board){
        ArrayList<int[]> possibilities = new ArrayList<>();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i]; j++){
                int[] temp = new int[10];

                for(int k = j; k < temp.length; k++){
                    //Math.min
                    temp[k] = Math.min(board[k], i); //takes care of up and down

                }
            }
        }
        return possibilities;
    }

}

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/*The MyPlayer class has a field called loseStates, which is an ArrayList of
 int[] arrays representing the losing board states.
 This field is initialized by calling the calculateLoseStates method,
 which generates all possible board configurations and checks if they are losing states.*/

public class MyPlayer {
    public Chip[][] gameBoard;
    public int[] columns;

    public ArrayList<int[]> loseStates = calculateLoseStates();

    public MyPlayer() {
        columns = new int[10];
    }

    /*The allPossibleMoves method returns a list of all possible board states
    that can be reached from a given board state. It does this by iterating
    through each column of the board and adding chips to the column in increments of 1,
    creating a new board configuration for each increment. It then checks if the new board configuration
     is valid (i.e., if it is not already in the list of board states) and, if it is, adds it to the list.
     */



    public ArrayList<int[]> allPossibleMoves(Board board) {
        int[] currBoard = board.existingBoardState;

        boolean alreadyCompleted = true;
        ArrayList<int[]> result = new ArrayList<>();


        for (int i = 0; i < currBoard.length; i++) {
            for (int j = 1; j < currBoard[i] + 1; j++) {

                int[] foundStates = currBoard.clone();
                foundStates[i] = j - 1;
                for (int k = i + 1; k < currBoard.length; k++) {
                    if (currBoard[k] > foundStates[i] - 1) {
                        foundStates[k] = j - 1;
                    }
                }

                if (alreadyCompleted == true) {
                    alreadyCompleted = false;
                } else {
                    result.add(foundStates);
                }
            }
        }
        return result;
    }



    /*The bestMove method is used to determine the best move to make given the current board state.
    It first creates a list of all possible board states that can be reached from the current board state,
     and then checks if any of those states are losing states.
     If one of the possible board states is a losing state,
     the method returns the coordinates of the move that was made to get to that state.
     Otherwise, it returns the coordinates (0, 0).
     */

    public int[] bestMove(Board currBoardState) {
        // coordinates
        int x, y;

        //given board
        int[] firstBoard = new int[currBoardState.existingBoardState.length];
        int[] dependent = new int[currBoardState.existingBoardState.length];
        boolean done = false;



        int[] condition = currBoardState.existingBoardState;
        ArrayList<int[]> allPossibleBoards = allPossibleMoves(currBoardState);

        // checks losing
        for(int[] i: allPossibleBoards) {
            for(int[] j: loseStates) {
                if(Arrays.equals(i, j)) {
                    firstBoard = i;
                    done = true;
                }
            }
        }
        if(done == true) {
            for (int i = 0; i < condition.length; i++) {
                dependent[i] = condition[i] - firstBoard[i];
            }
            for(int i = 0; i < condition.length; i++) {
                if(dependent[i] != 0) {
                    x = firstBoard[i];
                    y = i;

                    int[] finalMove = {x, y};
                    return(finalMove);
                }
            }


        } else {
            x = 0;
            y = 0;

            int[] finalMove = {x, y};
            return (finalMove);
        }
        return(new int[]{1, 2, 3});
    }

    public ArrayList<int[]> calculateLoseStates() {
        ArrayList<int[]> losingBoards = new ArrayList<>();
        for(int a = 1; a < 11; a++) {
            for (int b = 0; b < a + 1; b++) {
                for (int c = 0; c < b + 1; c++) {
                    for(int d = 0; d < c + 1; d++) {
                        for (int e = 0; e < d + 1; e++) {
                            for(int f = 0; f < e + 1; f++) {
                                for (int g = 0; g < f + 1; g++) {
                                    for (int h = 0; h < g + 1; h++) {
                                        for (int i = 0; i < h + 1; i++) {
                                            for (int j = 0; j < i + 1; j++) {
                                                boolean isLosing = true;
                                                Board temp = new Board(new int[]{a, b, c, d, h, f, g, h, i, j});
                                                ArrayList<int[]> allTempMoves = allPossibleMoves(temp);

                                                for (int[] possibilities : allTempMoves) {
                                                    for (int[] loseState : losingBoards) {
                                                        if (Arrays.equals(possibilities, loseState)) {
                                                            isLosing = false;
                                                        }
                                                    }

                                                }
                                                if (isLosing == true) {
                                                    losingBoards.add(temp.existingBoardState);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return losingBoards;
    }



    public Board convertChipToBoard(Chip[][] board) {
        ArrayList<Integer> resultingBoard = new ArrayList<>();
        for (int c = 0; c < board[0].length; c++) {
            Integer count = 0;
            for (int r = 0; r < board.length; r++) {
                if (board[r][c].isAlive) {
                    count = count + 1;
                }
            }
            resultingBoard.add(count);
        }

        int[] work = new int[board.length];
        for(int q = 0; q < board.length; q++) {
            work[q] = resultingBoard.get(q);
        }
        return(new Board(work));
    }



    public Point move(Chip[][] pBoard) {
        gameBoard = pBoard;
        Board newBoard = convertChipToBoard(pBoard);
        int[] solution = bestMove(newBoard);


        Point result = new Point(solution[0], solution[1]);
        return result;
    }

}


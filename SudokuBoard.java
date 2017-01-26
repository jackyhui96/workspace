import java.util.ArrayList;
import java.util.Random;

public class SudokuBoard {

    int[][] grid;
    ArrayList<Integer> emptySpaces;

    public static void main(String[] args) {
        SudokuBoard sudoku = new SudokuBoard();
        sudoku.genBoard();

        
        System.out.println(sudoku);
    }

    public SudokuBoard() {
        grid = new int[9][9];
        emptySpaces = new ArrayList<Integer>();
        for(int i = 0; i < 81; i++) {
            emptySpaces.add(new Integer(i));
        }
    }

    public void genBoard() {
        while(!isEmpty()) {
            Random rand = new Random();
            int index = rand.nextInt(emptySpaces.size());
            int number = rand.nextInt(9);

            Integer temp = emptySpaces.get(index);

            int x = emptySpaces.get(index) / 9;
            int y = emptySpaces.get(index) % 9;

            grid[x][y] = number;
            emptySpaces.remove(index);
        }
    }

    public boolean isEmpty() {
        return emptySpaces.isEmpty();
    }

    public ArrayList<Integer> validNumbers(int x, int y) {
        ArrayList<Integer> invalidNumbers = new ArrayList<Integer>();

        for(int i = 0; i < 9; i++) {
            
                Integer colVal = new Integer(grid[x][i]);
                if(!invalidNumbers.contains(colVal)) {
                    invalidNumbers.add(colVal);
                }
            
            
            
                Integer rowVal = new Integer(grid[i][y]);
                if(!invalidNumbers.contains(rowVal)) {
                    invalidNumbers.add(rowVal);
                }
            
        }

        // Check if sub grid is valid
        ArrayList<Integer> gridNumbers = checkSubGrid(x, y);
        for(int i = 0; i < gridNumbers.size(); i++) {
            Integer val = new Integer(gridNumbers.get(i));
            if(!invalidNumbers.contains(val)) {
                invalidNumbers.add(val);
            }
        }

        ArrayList<Integer> validNums = new ArrayList<Integer>();
        for(int i = 1; i <= 9; i++) {
            if(!invalidNumbers.contains(new Integer(i))) {
                validNums.add(i);
            }
        }

        return validNums;
 
    }

    public ArrayList<Integer> checkSubGrid(int x, int y) {
        int gridX = x / 3;
        int gridY = y / 3;

        ArrayList<Integer> gridNumbers = new ArrayList<Integer>();
         
        for(int i = 3*gridX; i < 3*(gridX+1); i++) {
            for(int j = 3*gridY; j < 3*(gridY+1); j++) {
                
                    Integer val = new Integer(grid[i][j]);
                    if(!gridNumbers.contains(val)) {
                        gridNumbers.add(val);
                    }
                
            }
        }
        return gridNumbers;
    }

    public String toString() {
        
        String outputString = "|";

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                
                outputString += grid[i][j] + "|";
                if((j+1) % 3 == 0) {
                    outputString += " |";
                } 
                
            } 
            if((i+1) % 3 == 0) {
                outputString += "\n";
            } 
            
            outputString += "\n|";
        } 

        return outputString;
    }
    


}
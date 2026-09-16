// FightingFire.java
// This program is a game that simulates a wildfire spreading in a forest and the mission is to use water to avoid fire propagation
// Author: Camilo Vergara M.
// Date: 18/10/25

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Scanner;

public class FightingFire {

    public static void generateGrid(GridGame grid){
        List <String> elements = new ArrayList<>();     // initiate a list for the strings elements
        // set of for loops to fill up the element list
        for (int i= 0; i< 43; i++){     // create 43 (55 trees - 12 trees on fire) tree cells
            elements.add("T");      // and add it to the elements list
        }
        for (int i= 0; i< 12; i++){     // create 12 fire cells
            elements.add("F");      // and add it to the elements list
        }
        for (int i= 0; i< 10; i++){     // create 10 rock cells
            elements.add("#");      // and add it to the elements list
        }
        for (int i= 0; i< 5; i++){      // create 5 water cells
            elements.add("W");      // and add it to the elements list
        }
        for (int i= 0; i< 10; i++){     // create 10 empty cells
            elements.add(".");      // and add it to the elements list
        }

        // shuffle the element list
        Collections.shuffle(elements);

        // create the layout
        System.out.println();       // print a space line
        System.out.println("  === Fighting Fire ===");      // print the header
        System.out.println();       // print another space line
        System.out.print("    ");   // print space before the column index
        for (int j=0; j< grid.getCol(); j++){    // go through the column indexes
            System.out.print(j + "  ");     // print those indexes
        }
        System.out.println();   // line before printing the rows
        int counter = 0;      // start a counter for going through the element list
        for (int i=0; i< grid.getRow(); i++){       // go through the rows
            System.out.print(i + "   ");    // print the row's index
            for(int j=0; j< grid.getCol(); j++){        // go through the columns while going through the rows
                String s = elements.get(counter);   // assign an element from the element list to a cell
                grid.setGridCell(s, i, j);     // change the value of the cell
                System.out.print(s + "  ");    // print each cell with some space in between
                counter++;      // add 1 to the counter
            }
            System.out.println();       // print a new line
        }
    }

    public static void main(String[] args){

        boolean game = true;        // for the game loop
        Scanner in = new Scanner(System.in);        // to get users input
        GridGame grid = new GridGame(10, 8);     // to create a grid of 10 rows and 8 columns

        generateGrid(grid);     // create the initial grid

        // handle the game
        while(game){        // while the game is true...
            int row = 0;    // initiate row to zero
            int col = 0;    // initiate column to zero
            boolean notValidRow = true;     // to check for valid input for the row index
            boolean notValidCol = true;     // to check for valid input for the column index

            System.out.println("Enter row:");    // ask for the row position
            while (notValidRow){        // will continue until get a valid index number
                if(!in.hasNextInt()){       // if the input is not an integer, then
                    String wrongRow = in.next();     // stores the input as a string
                    System.err.println(" \"" + wrongRow + "\" " + " is not valid");    // print an error message
                    System.out.println("Enter row again:");     // ask again for the row position
                } else {    // if is an integer, then
                    row = in.nextInt();     // store the value in row
                    if (row < 0 || 9 < row){        // but if is out the valid index
                        System.err.println("\"" + row + "\"" + " is out of range");    // print an error message
                        System.out.println("Enter row again:");     // ask again for the row position
                    } else {        // if the input is a number and within the range
                        notValidRow = false;      // get out of the loop
                    }
                }
            }

            // ask for the position in the column
            System.out.println("Enter column:");     // ask for the column position
            while(notValidCol){     // while is not an integer and within the index range
                if(!in.hasNextInt()){       // if is not an integer
                    String wrongCol = in.next();      // store the value as a string
                    System.err.println("\"" + wrongCol + "\"" + " is not a valid input");     // print an error message
                    System.out.println("Enter column again:");      // ask again for the column position
                } else {        // if is an integer
                    col = in.nextInt();     // store the value in col
                    if (col < 0 || 7 < col){        // if the value is out of range
                        System.err.println("\"" + col + "\"" + " is out of range" );      // print an error message
                        System.out.println("Enter column again:");      // ask again for the column position
                    } else {        // if the input is an integer and within the range
                        notValidCol = false;       // get out of the loop
                    }
                }
            }

            // place a water cell in player's coordinates
            if (grid.getGridCell(row, col).equals("F")){     // if the user's selected cell is on fire, then:
                grid.setFireExtinguished(1);        // add one to the counter of fire extinguished
                grid.setGridCell("W", row, col);     // the cell turns into water, and
                grid.spreadWaterNorth(row, col);     // spread water to the cells above, and
                grid.spreadWaterEast(row, col);      // spread water to the cell to the right, and
                grid.spreadWaterSouth(row, col);     // spread water to the cells below, and
                grid.spreadWaterWest(row, col);      // spread water to the cells to the left
            }

            grid.updateGrid();       // calls the method to update the grid
            System.out.println();       // print a line
            System.out.println("Row to drop water: " + row);        // print the row coordinate
            System.out.println("Column to drop water: " + col);      // print the column coordinate
            System.out.println();       // print a new line
            System.out.println("You extinguished: " + grid.getFireExtinguished() + " fire(s)");      // print the number of fire extinguished
            System.out.println();       // print a new line

            // check to finish the game
            if (grid.getFireCounts() == 0 && grid.getTreeCounts() > 0){     // if no fire and at least one tree left, then
                System.out.println();       // print a new line
                System.out.println("Congrats! You won");        // print a message
                System.out.println("Remaining trees: " + grid.getTreeCounts());     // print the remaining trees
                System.out.println();       // print a new line
                game = false;       // set the game false to finish the game
            } else if (grid.getTreeCounts() == 0){      // if the tree counts is zero, then
                System.out.println();       // print a new line
                System.out.println("Game Over x.x");        // print a message
                System.out.println("Remaining trees: " + grid.getTreeCounts());     // print the remaining trees
                System.out.println();       // print a new line
                game = false;       // set the game false to finish the game
            }
        }
    }
}

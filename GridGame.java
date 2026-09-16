public class GridGame {
    private int row;        // amount of rows
    private int col;        // amount of columns
    private String[][] grid;     // cell string
    private int fireExtinguished = 0;    // amount of water cells
    private int fireCounts;     // amount of fire cells in the grid
    private int treeCounts;     // amount of trees cells in the grid

    // the constructor
    public GridGame(int row, int col) {     // taking as arguments the number of rows and columns
        this.grid = new String[row][col];
        this.row = row;
        this.col = col;
    }

    // getter for the row value
    public int getRow(){
        return this.row;
    }

    // getter for the column value
    public int getCol(){
        return this.col;
    }

    // getter for the grid
    public String getGridCell(int row, int col){
        return grid[row][col];
    }

    // getter for the amount of fire extinguished
    public int getFireExtinguished(){
        return this.fireExtinguished;
    }

    // getter for the fire count
    public int getFireCounts(){
        return this.fireCounts;
    }

    // getter for the tree count
    public int getTreeCounts(){
        return this.treeCounts;
    }

    // setter to modify the character in the cell
    public void setGridCell(String s, int row, int col){
        grid[row][col] = s;
    }

    // setter to count the fire extinguished
    public void setFireExtinguished(int plus){
        fireExtinguished += plus;
    }

    // method to update the grid
    public void updateGrid() {
        this.fireCounts = 0;    // set the fire counter to zero to count each turn
        this.treeCounts = 0;    // set the tree counter to zero each turn
        System.out.print("    ");   // print space for column index
        for(int j=0; j< this.col; j++){    // go through the column indexes
            System.out.print(j + "  ");     // print those indexes
        }
        System.out.println();       // print a new line
        for (int i = 0; i < this.row; i++) {        // for loop to iterate over the rows
            System.out.print(i + "   ");    // print the row's index with a space in between
            for (int j = 0; j < this.col; j++) {        // for loop to iterate over the columns
                spreadFire(i, j);       // call method to spread the fire
                fireCounter(i, j);      // call method to count the fire cells
                treeCounter(i, j);      // call method to count the remaining trees
                System.out.print(grid[i][j] + "  ");        // print the cell
            }
            System.out.println();       // print a new line
        }
    }

    // method to spread water to the cells above
    public void spreadWaterNorth(int row, int col){
        for (int i = row - 1; i >= 0; i--){     // start to check from the row above and move upwards until row index is zero
            if (grid[i][col].equals("F")){      // if the cell is on fire
                grid[i][col] = "W";     // turn it into water
                fireExtinguished++;      // add plus one to the fire extinguished counter
            } else if (grid[i][col].equals("#") || grid[i][col].equals("W")){     // otherwise, if the cell above is a rock or water
                return;     // stop going further
            }
        }
    }

    // method to spread water to the right
    public void spreadWaterEast(int row, int col){
        for (int i= col + 1; i < grid[i].length; i++){      // start in the column and move to the right until the grid's edge
            if (grid[row][i].equals("F")){      // if the cell is on fire
                grid[row][i] = "W";     // then turn it into water
                fireExtinguished++;      // and add one to the fire extinguished counter
            } else if (grid[row][i].equals("#") || grid[row][i].equals("W")){       //otherwise, if the cell is a rock or water
                return;     // stop going further
            }
        }
    }

    // method to spread water to the cells below
    public void spreadWaterSouth(int row, int col){
        for (int i = row + 1; i < grid.length; i++){       // start in the row below, and move downward until the bottom
            if (grid[i][col].equals("F")){      // if the cell is on fire
                grid[i][col] = "W";     // then turn it into water
                fireExtinguished++;      // and add one to the fire extinguished counter
            } else if (grid[i][col].equals("#") || grid[i][col].equals("W")){       //otherwise, if the cell is a rock or water
                return;     // stop going further
            }
        }
    }

    // method to spread water to the left
    public void spreadWaterWest(int row, int col){
        for (int i= col - 1; i >= 0; i--){      // start in the column to the left
            if (grid[row][i].equals("F")){      // if the cell is on fire
                grid[row][i] = "W";     // then turn it into water
                fireExtinguished++;      // and add one to the fire extinguished counter
            } else if (grid[row][i].equals("#") || grid[row][i].equals("W")){       //otherwise, if the cell is a rock or water
                return;     // stop going further
            }
        }
    }

    // method to spread fire and emptied a burning cell
    public void spreadFire(int row, int col){
        if (grid[row][col].equals("T")){      // if the cell is "T"
            double dice = Math.random();      // then store in dice a random number between 0-1
            if (0 < row && grid[row - 1][col].equals("F")){     // and if row is before below the top and if cell above = "F"
                if (dice < 0.75){       // and if dice is under 0.75 (75%)
                    grid[row][col] = "F";       // then, the current cell is now on fire
                }
            } if (col < grid[row].length - 1 && grid[row][col + 1].equals("F")){    // if col is before right edge & if right cell = "F"
                if (dice < 0.75){       // and if dice is under 0.75 (75%)
                    grid[row][col] = "F";       // then, the current cell is now on fire
                }
            } if (row < grid.length - 1 && grid[row + 1][col].equals("F")){     // if row is before the bottom edge & if cell below = "F"
                if (dice < 0.75){       // and if dice is under 0.75 (75%)
                    grid[row][col] = "F";       // then, the current cell is now on fire
                }
            } if (0 < col && grid[row][col - 1].equals("F")){       // if col is before the left edge and left cell is fire
                if (dice < 0.75){       // and if dice is under 0.75 (75%)
                    grid[row][col] = "F";       // then, the current cell is now on fire
                }
            }
        } else if (grid[row][col].equals("F")){     // if the current cell is on fire
            grid[row][col] = ".";       // then, the cell is now empty
        }
    }

    // method to count the fire cells
    public void fireCounter(int row, int col){
        if (grid[row][col].equals("F")){        // if the cell is fire, then
            fireCounts++;       // add one to the counter
        }
    }

    // method to count the tree cells
    public void treeCounter(int row, int col){
        if (grid[row][col].equals("T")){        // if the cell is a tree, then
            treeCounts++;       // add one to the counter
        }
    }
}

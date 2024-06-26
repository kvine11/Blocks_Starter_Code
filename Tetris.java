/**
 * Tetris.java  4/30/2014
 *
 * @author - Jane Doe
 * @author - Period n
 * @author - Id nnnnnnn
 * @author - I received help from ...
 */

// Represents a Tetris game.
public class Tetris implements ArrowListener {
    private BoundedGrid<Block> grid;    // The grid containing the Tetris pieces.
    private BlockDisplay display;        // Displays the grid.
    private Tetrad activeTetrad;        // The active Tetrad (Tetris Piece).

    // Constructs a Tetris Game
    public Tetris() {
        grid = new BoundedGrid<Block>(20, 10);
        display = new BlockDisplay(grid);
        display.setTitle("Tetris");
        display.setArrowListener(this);
        activeTetrad = new Tetrad(grid);
        display.showBlocks();
    }

    // Play the Tetris Game
    public void play() {
        while(true)
        {
            while(activeTetrad.translate(1,0))
            {
                sleep(1);
                display.showBlocks();
            }
            activeTetrad = new Tetrad(grid);
        }




    }


    // Precondition:  0 <= row < number of rows
    // Postcondition: Returns true if every cell in the given row
    //                is occupied; false otherwise.
    private boolean isCompletedRow(int row) {
        for(int i = 0; i < grid.getNumCols(); i++)
        {
            Location cell = new Location(row, i);
            if(grid.get(cell) == null)
            {
                return true;
            }
        }
        return false;
    }

    // Precondition:  0 <= row < number of rows;
    //                The given row is full of blocks.
    // Postcondition: Every block in the given row has been removed, and
    //                every block above row has been moved down one row.
    private void clearRow(int row)
    {
        for(int i = 0; i < grid.getNumCols(); i++)
        {
            Location removeCell = new Location(row, i);
            Block block = grid.get(removeCell);
            block.removeSelfFromGrid();
        }

        for(int i = row - 1; i >= 0; i--)
        {
            for(int j = 0; j < grid.getNumCols(); j++)
            {
                Location oldLoc = new Location(i, j);
                if(grid.get(oldLoc)!= null)
                {
                    Location shift = new Location(i + 1, j);
                    grid.get(oldLoc).moveTo(shift);
                }

            }
        }

    }

    // Postcondition: All completed rows have been cleared.
    private void clearCompletedRows() {
        for(int i = 0; i < grid.getNumRows(); i++)
        {
            if(isCompletedRow(i))
            {
                clearRow(i);
            }
        }
    }

    // Sleeps (suspends the active thread) for duration seconds.
    private void sleep(double duration) {
        final int MILLISECONDS_PER_SECOND = 1000;

        int milliseconds = (int) (duration * MILLISECONDS_PER_SECOND);

        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.err.println("Can't sleep!");
        }
    }


    // Creates and plays the Tetris game.
    public static void main(String[] args) {
        Tetris game = new Tetris();
        game.play();
    }

    @Override
    public void upPressed() {
        activeTetrad.rotate();
        display.showBlocks();
    }

    @Override
    public void downPressed() {
        activeTetrad.translate(1, 0);
        display.showBlocks();
    }

    @Override
    public void leftPressed() {
        activeTetrad.translate(0, -1);
        display.showBlocks();

    }

    @Override
    public void rightPressed() {
        activeTetrad.translate(0, 1);
        display.showBlocks();

    }
}

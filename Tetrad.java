/**
 * Tetrad.java  4/30/2014
 *
 * @author - Jane Doe
 * @author - Period n
 * @author - Id nnnnnnn
 *
 * @author - I received help from ...
 *
 */

import java.awt.Color;

// Represents a Tetris piece.
public class Tetrad
{
    private Block[] blocks;	// The blocks for the piece.

    // Constructs a Tetrad.
    public Tetrad(BoundedGrid<Block> grid)
    {
        int num = (int)(Math.random() * 7) + 0;
        if(num == 0)
        {
            blocks = new Block[4];
            Color color;
            Location [] locs = {new Location(0,3), new Location(0,4), new Location(0,5), new Location(0,6)};
            color = Color.RED;
            for(int i = 0; i < blocks.length; i++)
            {
                blocks[i] = new Block();
                blocks[i].setColor(color);
            }
            addToLocations(grid, locs);
        }
        else if(num == 1)
        {
            blocks = new Block[4];
            Color color;
            Location [] locs = {new Location(0,3), new Location(0,4), new Location(0,5), new Location(1,4)};
            color = Color.GRAY;
            for(int i = 0; i < blocks.length; i++)
            {
                blocks[i] = new Block();
                blocks[i].setColor(color);
            }
            addToLocations(grid, locs);
        }
        else if(num == 2)
        {
            blocks = new Block[4];
            Color color;
            Location [] locs = {new Location(0,3), new Location(0,4), new Location(1,3), new Location(1,4)};
            color = Color.CYAN;
            for(int i = 0; i < blocks.length; i++)
            {
                blocks[i] = new Block();
                blocks[i].setColor(color);
            }
            addToLocations(grid, locs);
        }
        else if(num == 3)
        {
            blocks = new Block[4];
            Color color;
            Location [] locs = {new Location(0,3), new Location(0,4), new Location(0,5), new Location(1,3)};
            color = Color.YELLOW;
            for(int i = 0; i < blocks.length; i++)
            {
                blocks[i] = new Block();
                blocks[i].setColor(color);
            }
            addToLocations(grid, locs);
        }
        else if(num == 4)
        {
            blocks = new Block[4];
            Color color;
            Location [] locs = {new Location(0,3), new Location(0,4), new Location(0,5), new Location(1,5)};
            color = Color.MAGENTA;
            for(int i = 0; i < blocks.length; i++)
            {
                blocks[i] = new Block();
                blocks[i].setColor(color);
            }
            addToLocations(grid, locs);
        }
        else if(num == 5)
        {
            blocks = new Block[4];
            Color color;
            Location [] locs = {new Location(0,4), new Location(0,5), new Location(1,3), new Location(1,4)};
            color = Color.BLUE;
            for(int i = 0; i < blocks.length; i++)
            {
                blocks[i] = new Block();
                blocks[i].setColor(color);
            }
            addToLocations(grid, locs);
        }
        else
        {
            blocks = new Block[4];
            Color color;
            Location [] locs = {new Location(0,3), new Location(0,4), new Location(1,4), new Location(1,5)};
            color = Color.GREEN;
            for(int i = 0; i < blocks.length; i++)
            {
                blocks[i] = new Block();
                blocks[i].setColor(color);
            }
            addToLocations(grid, locs);
        }
    }


    // Postcondition: Attempts to move this tetrad deltaRow rows down and
    //						deltaCol columns to the right, if those positions are
    //						valid and empty.
    //						Returns true if successful and false otherwise.
    public boolean translate(int deltaRow, int deltaCol)
    {
        BoundedGrid<Block> grid = blocks[0].getGrid();
        Location [] oldLoc = removeBlocks();
        Location [] newLoc = new Location[blocks.length];
        for(int i = 0; i < newLoc.length; i++)
        {
            newLoc[i] = new Location(oldLoc[i].getRow() + deltaRow, oldLoc[i].getCol() + deltaCol);
        }
        if(areEmpty(grid, newLoc))
        {
            addToLocations(grid,newLoc);
            return true;
        }
        else
        {
            addToLocations(grid,oldLoc);
            return false;
        }






    }

    // Postcondition: Attempts to rotate this tetrad clockwise by 90 degrees
    //                about its center, if the necessary positions are empty.
    //                Returns true if successful and false otherwise.
    public boolean rotate()
    {
        throw new RuntimeException("INSERT MISSING CODE HERE");
    }


    // Precondition:  The elements of blocks are not in any grid;
    //                locs.length = 4.
    // Postcondition: The elements of blocks have been put in the grid
    //                and their locations match the elements of locs.
    private void addToLocations(BoundedGrid<Block> grid, Location[] locs)
    {
        for(int i = 0; i < locs.length; i++)
        {
            blocks[i].putSelfInGrid(grid, locs[i]);
        }
    }

    // Precondition:  The elements of blocks are in the grid.
    // Postcondition: The elements of blocks have been removed from the grid
    //                and their old locations returned.
    private Location[] removeBlocks()
    {
        Location[] revisedBlocks = new Location[blocks.length];
        for(int i = 0; i < blocks.length; i++)
        {
            revisedBlocks[i] = blocks[i].getLocation();
            blocks[i].removeSelfFromGrid();
        }
        return revisedBlocks;
    }

    // Postcondition: Returns true if each of the elements of locs is valid
    //                and empty in grid; false otherwise.
    private boolean areEmpty(BoundedGrid<Block> grid, Location[] locs)
    {
        for(int i = 0; i < locs.length; i++)
        {
            if(grid.isValid(locs[i]) && grid.get(locs[i]) == null)
            {
                return true;
            }
        }
        return false;
    }
}

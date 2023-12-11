import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Grid extends Tile
{
    
    private Tile[][] gridArray;
    private Rectangle rec1;
    private Rectangle rec2;
    private Rectangle rec3;
    private Rectangle rec4;

    Grid()
    {
        super();
    }

    Grid(Pane pane, Rectangle bounds)
    {
        super(pane, bounds);

        //fill Gridarry with new Tile()
        this.gridArray = new Tile[3][3];
        for(int i = 0; i < 3 ; i++)
        {
            for(int j = 0; j < 3 ; j++)
            {
                this.gridArray[i][j] = new Tile(pane, getInitialTileRec(i, j));
            }
        }
        
        //drawing lines within the bounds
        rec1 = new Rectangle();
        rec2 = new Rectangle();
        rec3 = new Rectangle();
        rec4 = new Rectangle();

        rec1.setFill(Color.BLACK);
        rec2.setFill(Color.BLACK);
        rec3.setFill(Color.BLACK);
        rec4.setFill(Color.BLACK);

        rec1.heightProperty().bind(this.bounds.heightProperty().divide(19.0/17));
        rec2.heightProperty().bind(this.bounds.heightProperty().divide(19.0/17));
        rec3.heightProperty().bind(this.bounds.heightProperty().divide(19.0));
        rec4.heightProperty().bind(this.bounds.heightProperty().divide(19.0));

        rec1.widthProperty().bind(this.bounds.widthProperty().divide(19));
        rec2.widthProperty().bind(this.bounds.widthProperty().divide(19));
        rec3.widthProperty().bind(this.bounds.widthProperty().divide(19.0/17));
        rec4.widthProperty().bind(this.bounds.widthProperty().divide(19.0/17));

        rec1.xProperty().bind(this.bounds.widthProperty().divide(19.0/6).add(this.bounds.xProperty()));
        rec2.xProperty().bind(this.bounds.widthProperty().divide(19.0/12).add(this.bounds.xProperty()));
        rec3.xProperty().bind(this.bounds.widthProperty().divide(19).add(this.bounds.xProperty()));
        rec4.xProperty().bind(this.bounds.widthProperty().divide(19).add(this.bounds.xProperty()));
 
        rec1.yProperty().bind(this.bounds.heightProperty().divide(19).add(this.bounds.yProperty()));
        rec2.yProperty().bind(this.bounds.heightProperty().divide(19).add(this.bounds.yProperty()));
        rec3.yProperty().bind(this.bounds.heightProperty().divide(19.0/6).add(this.bounds.yProperty()));
        rec4.yProperty().bind(this.bounds.heightProperty().divide(19.0/12).add(this.bounds.yProperty()));

        pane.getChildren().add(rec1);
        pane.getChildren().add(rec2);
        pane.getChildren().add(rec3);
        pane.getChildren().add(rec4);
    }

    public Grid addGrid(Pane pane, int x, int y)
    {
        Grid grid = new Grid(pane, getInitialTileRec(x, y));
        gridArray[x][y] = grid;
        return grid;
    }

    public MyX addX(Pane pane, int x, int y)
    {
        MyX myX = new MyX(pane, getInitialTileRec(x, y));
        gridArray[x][y] = myX;
        return myX;
    }

    public MyO addO(Pane pane, int x, int y)
    {
        MyO myO = new MyO(pane, getInitialTileRec(x, y));
        gridArray[x][y] = myO;
        return myO;
    }

    public Tile addTile(Pane pane, int x, int y)
    {
        Tile tile = new Tile(pane, getInitialTileRec(x, y));
        gridArray[x][y] = tile;
        return tile;
    }

    public static void setAsX(Pane pane, Tile grid)
    {
        grid = new MyX(pane, grid.initialRec);
        ((MyX)grid).setFill(App.player1Color);
    }

    public static void setAsO(Pane pane, Tile grid)
    {
        grid = new MyO(pane, grid.initialRec);
        ((MyO)grid).setFill(App.player2Color);
    }

    public static void setAsNewGrid(Pane pane, Tile grid, Rectangle rectangle)
    {
        grid = new Grid(pane, rectangle);
        App.currentX = -1;
        App.currentY = -1;
    }

    public Tile getTile(int x, int y)
    {
        return gridArray[x][y];
    }


    public Boolean isClickedOn(Pane gamePane, double x , double y, int currentX, int currentY)
    {

        //if NOT in range of the grid
        if(!(currentX >= 0 && currentX <= 2 && currentY >= 0 && currentY <= 2))
        {
            return isClickedOn(gamePane, x, y);
        }

        //if NOT clicked on
        if(!gridArray[currentX][currentY].isClickedOn(gamePane, x, y))
        {
            return false;
        }

        //continues assuming it IS in range and it WAS clicked on 
        int winner = App.player;
        if(gridArray[currentX][currentY] instanceof Grid)
        {
            winner = ((Grid)gridArray[currentX][currentY]).checkWin();
        }
        else
        {
            winner = gridArray[currentX][currentY].player;
        }        
       
        //if claimed turn cell into an X or O
        if(winner == 1)
        {
            addX(gamePane, currentX, currentY).setFill(App.player1Color);             
        }
        else if(winner == 2)
        {
            addO(gamePane, currentX, currentY).setFill(App.player2Color);
        }
        

        return true;       
    }

    public Boolean isClickedOn(Pane gamePane, double x , double y)
    {

        //returns false if click is NOT within bounds
        if (!super.isWithinBounds(x, y))
        {
            return false;
        }

        for(int i = 0; i < 3 ; i++)
        {
            for(int j = 0; j < 3 ; j++)
            {
                if(gridArray[i][j].isClickedOn(gamePane, x, y) && gridArray[i][j].player == 0)
                {
                    int winner = App.player;

                    if(gridArray[i][j] instanceof Grid)
                    {
                        winner = ((Grid)gridArray[i][j]).checkWin();
                    }
                    else
                    {
                        //switches player 1 for 2 and for 1
                        App.player = 3 - App.player;
                    }

                    if(winner != 0)
                    {
                        //if claimed turn cell into an X or O
                        if(winner == 1)
                        {
                            addX(gamePane, i, j).setFill(App.player1Color);             
                        }
                        else if(winner == 2)
                        {
                            addO(gamePane, i, j).setFill(App.player2Color);
                        }

                       

                        //sets next avaliable area
                        App.currentX = i;
                        App.currentY = j;
                    }

                    //break both loops
                    i = 3;
                    j = 3; 
                }  
            }
        }    
        return true;
    }

    public Boolean hasGrids(){
        for(Tile[] tileArray : gridArray)
        {
            for(Tile tile : tileArray)
            {
                if (tile instanceof Grid)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public int checkWin(){
        if (checkWinHelper(0,0,0,1,0,2) && gridArray[0][0].player != 0)
            return gridArray[0][0].player;
        if (checkWinHelper(1,0,1,1,1,2) && gridArray[1][0].player != 0)
            return gridArray[1][0].player;
        if (checkWinHelper(2,0,2,1,2,2) && gridArray[2][0].player != 0)
            return gridArray[2][0].player;

        if (checkWinHelper(0,0,1,0,2,0) && gridArray[0][0].player != 0)
            return gridArray[0][0].player;
        if (checkWinHelper(0,1,1,1,2,1) && gridArray[0][1].player != 0)
            return gridArray[0][1].player;
        if (checkWinHelper(0,2,1,2,2,2) && gridArray[0][2].player != 0)
            return gridArray[0][2].player;
       
        if (checkWinHelper(0,0,1,1,2,2) && gridArray[0][0].player != 0)
            return gridArray[0][0].player;
        if (checkWinHelper(0,2,1,1,2,0) && gridArray[0][2].player != 0)
            return gridArray[0][2].player;  

        return 0;
    }

    private Boolean checkWinHelper(int x1, int y1, int x2, int y2, int x3, int y3)
    {
        return gridArray[x1][y1].player == gridArray[x2][y2].player 
            && gridArray[x1][y1].player == gridArray[x3][y3].player;
    }
    
    private Rectangle getInitialTileRec(int x, int y)
    {
        //returns a rec describing where the cell is
        return new Rectangle(
            (x  * initialRec.widthProperty().doubleValue() * 6.0 / 19.0) + (initialRec.widthProperty().doubleValue() / 19.0) + initialRec.xProperty().doubleValue(),
            (y  * initialRec.heightProperty().doubleValue() * 6.0 / 19.0) + (initialRec.heightProperty().doubleValue() / 19.0) + initialRec.yProperty().doubleValue(), 
            (initialRec.widthProperty().doubleValue()  * 4.0 / 19.0) + (initialRec.widthProperty().doubleValue() / 19.0), 
            (initialRec.heightProperty().doubleValue() * 4.0 / 19.0)+ (initialRec.heightProperty().doubleValue() / 19.0)
            );
    }  
}

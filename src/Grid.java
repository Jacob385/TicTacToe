import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Grid extends Tile{
    
    private Tile[][] gridArray;

    Grid(Pane pane, Rectangle bounds){
        super(pane, bounds);

        //fill Gridarry with new Tile()
        this.gridArray = new Tile[3][3];
        for (Tile[] tilearray : gridArray){
            for(Tile tile : tilearray){
                tile = new Tile();
            }
        }  
        
        //drawing lines within the bounds
        Rectangle rec1 = new Rectangle();
        Rectangle rec2 = new Rectangle();
        Rectangle rec3 = new Rectangle();
        Rectangle rec4 = new Rectangle();

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

    public Grid addGrid(Pane pane, int x, int y){
        Grid grid = new Grid(pane, getInitialTileRec(x, y));
        gridArray[x][y] = grid;
        return grid;
    }

    public MyX addX(Pane pane, int x, int y){
        MyX myX = new MyX(pane, getInitialTileRec(x, y));
        gridArray[x][y] = myX;
        return myX;
    }

    public MyO addO(Pane pane, int x, int y){
        MyO myO = new MyO(pane, getInitialTileRec(x, y));
        gridArray[x][y] = myO;
        return myO;
    }

    public Tile getTile(int x, int y){return gridArray[x][y];}
    
    private Rectangle getInitialTileRec(int x, int y){
        return new Rectangle(
            (x  * initialRec.widthProperty().doubleValue() * 6.0 / 19.0) + (initialRec.widthProperty().doubleValue() / 19.0) + initialRec.xProperty().doubleValue(),
            (y  * initialRec.heightProperty().doubleValue() * 6.0 / 19.0) + (initialRec.heightProperty().doubleValue() / 19.0) + initialRec.yProperty().doubleValue(), 
            (initialRec.widthProperty().doubleValue()  * 4.0 / 19.0) + (initialRec.widthProperty().doubleValue() / 19.0), 
            (initialRec.heightProperty().doubleValue() * 4.0 / 19.0)+ (initialRec.heightProperty().doubleValue() / 19.0)
            );
    }  
}

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Tile {
    Rectangle bounds, initialRec;
    private int player;

    Tile(){
        this.player = 0;
    }
    Tile(Pane pane,Rectangle bounds){
        this();
        
        //set up bounds so it scales with window size
        this.bounds = new Rectangle();
        this.bounds.heightProperty().bind(pane.heightProperty().divide(190.0 / bounds.heightProperty().doubleValue()));
        this.bounds.widthProperty().bind(pane.widthProperty().divide(190.0 / bounds.widthProperty().doubleValue()));
        this.bounds.xProperty().bind(pane.widthProperty().divide(190.0 / bounds.xProperty().doubleValue()));
        this.bounds.yProperty().bind(pane.heightProperty().divide(190.0 / bounds.yProperty().doubleValue()));
   
        this.initialRec = bounds;
    }

    public int getPlayer(){return player;}
}

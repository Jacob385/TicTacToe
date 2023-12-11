import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Tile {
    Rectangle bounds, initialRec;
    int player;

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
    public Boolean isClickedOn(Pane gamePane, double x, double y){
        return isWithinBounds(x, y);
    }

    public Boolean isWithinBounds(double x, double y){
        double tempX = this.bounds.xProperty().doubleValue();
        double tempY = this.bounds.yProperty().doubleValue();
        double tempW = this.bounds.widthProperty().doubleValue();
        double tempH = this.bounds.heightProperty().doubleValue();
        
        return tempX <= x && x <= tempX + tempW && tempY <= y && y <= tempY + tempH;
    }

}

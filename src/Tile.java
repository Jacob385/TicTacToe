import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Region {
    public Rectangle bounds, initialRec;
    public int player;

    Tile() {
        this.player = 0;

        this.bounds = new Rectangle();
        this.bounds.heightProperty().bind(this.heightProperty());
        this.bounds.widthProperty().bind(this.widthProperty());
        this.bounds.xProperty().bind(this.widthProperty());
        this.bounds.yProperty().bind(this.heightProperty());

        this.initialRec = this.bounds;
    }

    Tile(Pane pane,Rectangle bounds) {
        this();
        //set up bounds so it scales with window size
        this.bounds = new Rectangle();
        this.bounds.heightProperty().bind(pane.heightProperty().divide(190.0 / bounds.heightProperty().doubleValue()));
        this.bounds.widthProperty().bind(pane.widthProperty().divide(190.0 / bounds.widthProperty().doubleValue()));
        this.bounds.xProperty().bind(pane.widthProperty().divide(190.0 / bounds.xProperty().doubleValue()));
        this.bounds.yProperty().bind(pane.heightProperty().divide(190.0 / bounds.yProperty().doubleValue()));

        this.initialRec = bounds;
    }

    public int getPlayer(){
        return player;
    }
}
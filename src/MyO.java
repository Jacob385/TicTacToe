import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class MyO extends Tile{
    Shape shape;
    
    //scale from 1 to 10
    int THICKNESS = 5;
  
    MyO(Pane pane, Rectangle rec){
        super(pane, rec);

        Ellipse outerEllipse = new Ellipse(
            initialRec.widthProperty().divide(2).doubleValue(),
            initialRec.heightProperty().divide(2).doubleValue(),
            initialRec.widthProperty().divide(2).doubleValue(),
            initialRec.heightProperty().divide(2).doubleValue()
        );
        Ellipse inerEllipse = new Ellipse(
            initialRec.widthProperty().divide(2).doubleValue(),
            initialRec.heightProperty().divide(2).doubleValue(),
            outerEllipse.radiusXProperty().subtract(initialRec.widthProperty().multiply(0.05 * THICKNESS)).doubleValue(),
            outerEllipse.radiusYProperty().subtract(initialRec.heightProperty().multiply(0.05 * THICKNESS)).doubleValue()
        );
        
        this.shape = Shape.subtract(outerEllipse, inerEllipse);

        //seting scale
        shape.scaleXProperty().bind(this.bounds.widthProperty().divide(initialRec.widthProperty()).multiply(17.0/19));
        shape.scaleYProperty().bind(this.bounds.heightProperty().divide(initialRec.heightProperty()).multiply(17.0/19));

        //setting x and y 
        shape.layoutXProperty().bind(this.bounds.widthProperty().divide(19).add(this.bounds.xProperty()).add(shape.scaleXProperty().subtract(1).divide(2).multiply(initialRec.widthProperty())));
        shape.layoutYProperty().bind(this.bounds.heightProperty().divide(19).add(this.bounds.yProperty()).add(shape.scaleYProperty().subtract(1).divide(2).multiply(initialRec.heightProperty())));

        pane.getChildren().add(this.shape);
    }
    
    public void setFill(Paint value){
        shape.setFill(value);
    }

    public Shape getShape(){
        return shape;
    }
}

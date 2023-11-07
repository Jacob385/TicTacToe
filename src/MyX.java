import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class MyX extends Tile{
    Polygon poly;

    //scale from 1 to 10
    int THICKNESS = 5;
  
    MyX(Pane pane, Rectangle rec){
        super(pane, rec);

        double x = 0;
        double y = 0;
        double w = initialRec.widthProperty().doubleValue();
        double h = initialRec.heightProperty().doubleValue();
        double t = initialRec.widthProperty().multiply(0.05 * THICKNESS).doubleValue();

        //Starts top left and goes clockwise
        poly = new Polygon(new double[]{ 
            x, y,
            x + t, y,
            x + (w / 2.0), y + ((h * ( h - (2.0 * t))) / ((h - t) * 2.0)) ,
            
            x + w - t, y,
            x + w, y,
            x + ((w - t) / 2.0) + t, y + (h / 2.0), 
            
            x + w, y + h,
            x + w - t, y + h,
            x + (w / 2.0), y - ((h * ( h - (2.0 * t))) / ((h - t) * 2.0)) + h,

            x + t, y + h,
            x, y + h,
            x + ((w - t) / 2.0) , y + (h / 2.0)
         });
        

        poly.scaleXProperty().bind(this.bounds.widthProperty().divide(initialRec.widthProperty()).multiply(17.0/19));
        poly.scaleYProperty().bind(this.bounds.heightProperty().divide(initialRec.heightProperty()).multiply(17.0/19));

        poly.layoutXProperty().bind(this.bounds.widthProperty().divide(19).add(this.bounds.xProperty()).add(poly.scaleXProperty().subtract(1).divide(2).multiply(initialRec.widthProperty())));
        poly.layoutYProperty().bind(this.bounds.heightProperty().divide(19).add(this.bounds.yProperty()).add(poly.scaleYProperty().subtract(1).divide(2).multiply(initialRec.heightProperty())));

        pane.getChildren().add(poly);
    }
    
    public void setFill(Paint value){
        poly.setFill(value);
    }

    public Shape getShape(){
        return poly;
    }
}

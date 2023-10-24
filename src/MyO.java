import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

public class MyO{
    Ellipse outerEllipse;
    Ellipse inerEllipse;
    int THICKNESS = 5;
  
    MyO(Ellipse ellipse){
        this.outerEllipse = new Ellipse();
        this.outerEllipse.setFill(Color.BLACK);

    }
    MyO(Pane pane, Rectangle rec){
        this.outerEllipse = new Ellipse();
        this.outerEllipse.setFill(Color.BLACK);

        this.outerEllipse.centerXProperty().bind(pane.widthProperty().divide(190.0/(rec.xProperty().doubleValue()+(rec.widthProperty().doubleValue()/2))));
        this.outerEllipse.centerYProperty().bind(pane.heightProperty().divide(190.0/(rec.yProperty().doubleValue()+(rec.heightProperty().doubleValue()/2))));
        this.outerEllipse.radiusXProperty().bind(pane.widthProperty().divide(190.0/(rec.heightProperty().doubleValue()/2)));
        this.outerEllipse.radiusYProperty().bind(pane.heightProperty().divide(190.0/(rec.widthProperty().doubleValue()/2)));

        this.inerEllipse = new Ellipse();
        this.inerEllipse.setFill(Color.WHITE);

        this.inerEllipse.centerXProperty().bind(pane.widthProperty().divide(190.0/(rec.xProperty().doubleValue()+(rec.widthProperty().doubleValue()/2))));
        this.inerEllipse.centerYProperty().bind(pane.heightProperty().divide(190.0/(rec.yProperty().doubleValue()+(rec.heightProperty().doubleValue()/2))));
        this.inerEllipse.radiusXProperty().bind(pane.widthProperty().divide(190.0/(rec.heightProperty().doubleValue()/2-THICKNESS)));
        this.inerEllipse.radiusYProperty().bind(pane.heightProperty().divide(190.0/(rec.widthProperty().doubleValue()/2-THICKNESS)));
    }
    
    void setFill(Paint value){
        outerEllipse.setFill(value);
    }

    public void add(Pane pane){
        pane.getChildren().add(outerEllipse);
        pane.getChildren().add(inerEllipse);
    }
}

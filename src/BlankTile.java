import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class BlankTile extends Tile{
    public Shape shape;
    public Polygon poly;
    int THICKNESS = 5;
/* As children of Tile, we inherit two Rectangles,
bounds and initialRec*/

    BlankTile () {
        super();
        shape = new Rectangle(bounds.getX(), bounds.getY(), bounds.heightProperty().doubleValue(), bounds.widthProperty().doubleValue());
        shape.setFill(Color.GREEN);
    }
    BlankTile(Pane pane, Rectangle rec) {
        super(pane, rec);

        shape = new Rectangle(rec.getX(), rec.getY(), rec.heightProperty().doubleValue(), rec.widthProperty().doubleValue());
        shape.setFill(Color.GRAY);
        //seting scale
        shape.scaleXProperty().bind(this.bounds.widthProperty().divide(initialRec.widthProperty()).multiply(17.0/19));
        shape.scaleYProperty().bind(this.bounds.heightProperty().divide(initialRec.heightProperty()).multiply(17.0/19));

        //setting x and y
        shape.layoutXProperty().bind(this.bounds.widthProperty().divide(19).add(this.bounds.xProperty()).add(shape.scaleXProperty().subtract(1).divide(2).multiply(initialRec.widthProperty())));
        shape.layoutYProperty().bind(this.bounds.heightProperty().divide(19).add(this.bounds.yProperty()).add(shape.scaleYProperty().subtract(1).divide(2).multiply(initialRec.heightProperty())));

        shape.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            toggle(pane);
                });
        pane.getChildren().add(shape);
    }

    public void toggle(Pane pane)
    {
        if(shape.getFill() == Color.GRAY  && player==0) {
            player = 1;
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
            pane.getChildren().remove(shape);
            poly.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                toggle(pane);
            });
            pane.getChildren().add(poly);
            poly.setFill(Color.BLUE);
        } else if (poly.getFill() == Color.BLUE && player == 1) {
            player = 0;

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
            
            shape.setFill(Color.GREEN);
            pane.getChildren().remove(poly);
            shape.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                toggle(pane);
            });
            pane.getChildren().add(shape);
        }
    }
}

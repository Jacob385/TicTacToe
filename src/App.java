import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class App extends Application{
    public void start(Stage myStage){
        Pane pane =new Pane();

        Rectangle rec1 = new Rectangle();
        Rectangle rec2 = new Rectangle();
        Rectangle rec3 = new Rectangle();
        Rectangle rec4 = new Rectangle();

        rec1.setFill(Color.BLACK);
        rec2.setFill(Color.BLACK);
        rec3.setFill(Color.BLACK);
        rec4.setFill(Color.BLACK);

        rec1.heightProperty().bind(pane.heightProperty().divide(19.0/17));
        rec2.heightProperty().bind(pane.heightProperty().divide(19.0/17));
        rec3.heightProperty().bind(pane.heightProperty().divide(19.0));
        rec4.heightProperty().bind(pane.heightProperty().divide(19.0));

        rec1.widthProperty().bind(pane.widthProperty().divide(19));
        rec2.widthProperty().bind(pane.widthProperty().divide(19));
        rec3.widthProperty().bind(pane.widthProperty().divide(19.0/17));
        rec4.widthProperty().bind(pane.widthProperty().divide(19.0/17));

        rec1.xProperty().bind(pane.widthProperty().divide(19.0/6));
        rec2.xProperty().bind(pane.widthProperty().divide(19.0/12));
        rec3.xProperty().bind(pane.widthProperty().divide(19));
        rec4.xProperty().bind(pane.widthProperty().divide(19));
 
        rec1.yProperty().bind(pane.heightProperty().divide(19));
        rec2.yProperty().bind(pane.heightProperty().divide(19));
        rec3.yProperty().bind(pane.heightProperty().divide(19.0/6));
        rec4.yProperty().bind(pane.heightProperty().divide(19.0/12));

        pane.getChildren().add(rec1);
        pane.getChildren().add(rec2);
        pane.getChildren().add(rec3);
        pane.getChildren().add(rec4);

        MyO tac = new MyO(pane, new Rectangle(10, 10, 50, 50));
        tac.setFill(Color.BLUE);
        tac.add(pane);

        Scene scene = new Scene(pane ,190, 190 );
        myStage.setScene(scene);
        myStage.show(); 
    }
    public static void main(String[] args){
        Application.launch();
    }
}

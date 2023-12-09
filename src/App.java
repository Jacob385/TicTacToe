import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class App extends Application{


    //public Tile myTile = new Tile(); 

    public void start(Stage myStage){

        //String currentPlayer = "X";

        Pane pane = new Pane();
  
        Grid grid = new Grid(pane, new Rectangle(0, 0, 190, 190));

        // grid.getTile(row, column); will fetch a Tile that we can alter 
        
        //Some test cases can delete/////////////////////////////////////////////////////////////////////////
        
/*
        pane.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> {
            grid.addX(pane, 0, 0);
            e.consume();
        });


        grid.addGrid(pane, 0, 0);
        grid.addGrid(pane, 1, 1);
        grid.addGrid(pane, 2, 1);
        
        grid.addO(pane, 2, 0).setFill(Color.RED);
        grid.addO(pane, 0, 0).setFill(Color.GREEN);
        grid.addO(pane, 2, 2).setFill(Color.BLUE);

        grid.addX(pane, 0, 2).setFill(Color.RED);
        grid.addX(pane, 1, 2).setFill(Color.GREEN);
        grid.addX(pane, 0, 1).setFill(Color.BLUE);
        
        
        ((Grid)(grid.getTile(1,1))).addGrid(pane,2,2).addO(pane, 0, 0).setFill(Color.BLUE);
        ((Grid)(grid.getTile(1,1))).addGrid(pane,2,2).addX(pane, 2, 0).setFill(Color.RED);
        ((Grid)(grid.getTile(1,1))).addO(pane,1,2).setFill(Color.BLUE); */
        //end of test cases//////////////////////////////////////////////////////////////////////////////////

        Scene scene = new Scene(pane, 500, 500);
        myStage.setScene(scene);
        //scene.setFill(Color.RED);

        /*scene.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            scene.setFill(Color.BLUE);
            //System.out.println("blue");
        });*/

        myStage.setTitle("Ultimate Tic-Tac-Toe");
        myStage.show();
        pane.requestFocus();
    }
    public static void main(String[] args){
        Application.launch();
    }
}
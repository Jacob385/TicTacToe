import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application
{

    public static int player = 1; 
    public static Color player1Color = Color.RED, player2Color = Color.BLUE;
    public static int currentX, currentY;
    Shape currentGirdIndicator = Shape.subtract(new Rectangle(0, 0, 190, 190), new Rectangle(5, 5, 180, 180));

    Pane gamePane;
    Pane optionsPane;
    public void start(Stage myStage)
    {
        BorderPane titlePane = new BorderPane();
        
        gamePane = new Pane();
        BorderPane gameBorderPane = new BorderPane();

        Pane game3dPane = new Pane();

        optionsPane = new Pane();
        
        Scene game3dScene = new Scene(game3dPane, 500, 500);
        Scene optionsScene = new Scene(optionsPane, 500, 500);
        Scene gameScene = new Scene(gamePane, 500, 500);
        Scene TitleScene = new Scene(titlePane, 500, 500);
        myStage.setScene(TitleScene);


        Tile grid = new Grid(gamePane, new Rectangle(0,0,190,190));
        Tile[] grid3d = {new Grid(), new Grid(), new Grid()};

        Boolean[] gameover = {false};
      
        currentGirdIndicator.setFill(Color.GOLD);
        updateIndicatorRec(grid.initialRec);


        //Main Menu button
        Button ReturnToTitleScreenButton = new Button("Main menu");
        gameBorderPane.setBottom(ReturnToTitleScreenButton);
        ReturnToTitleScreenButton.setOnAction(e -> {  
            myStage.setScene(TitleScene);
        });


        //Title screen buttons
        VBox titleVBox = new VBox();
        Button compoundButton = new Button("Compound TicTacToe");
        compoundButton.setOnAction(e -> { 
            gamePane.getChildren().clear();
            Grid.setAsNewGrid(gamePane, (Grid)grid, new Rectangle(0, 0, 190, 190));
            gamePane.getChildren().add(currentGirdIndicator);
            gamePane.getChildren().add(gameBorderPane);
            gameover[0] = false;
            for(int x = 0 ; x < 3 ; x++)
            {
                for(int y = 0 ; y < 3 ; y++)
                {
                    ((Grid)grid).addGrid(gamePane, x, y);
                }
            }     
            updateIndicatorRec(grid.initialRec);
            player = 1;
            myStage.setScene(gameScene);
        });
        Button threeDButton = new Button("3D TicTacToe");
        threeDButton.setOnAction(e -> {
            game3dPane.getChildren().clear();
            grid3d[0] = new Grid(game3dPane, new Rectangle(65, 0, 60, 60));
            grid3d[1] = new Grid(game3dPane, new Rectangle(65, 65, 60, 60));
            grid3d[2] = new Grid(game3dPane, new Rectangle(65, 130, 60, 60));

            game3dPane.getChildren().add(gameBorderPane);
            gameover[0] = false;
            player = 1;
            myStage.setScene(game3dScene);
        });
        Button normalButton = new Button("TicTacToe");
        normalButton.setOnAction(e -> {
            gamePane.getChildren().clear();
            Grid.setAsNewGrid(gamePane, (Grid)grid, new Rectangle(0, 0, 190, 190));
            for(int x = 0 ; x < 3 ; x++)
            {
                for(int y = 0 ; y < 3 ; y++)
                {
                    ((Grid)grid).addTile(gamePane, x, y);
                }
            }  
            gamePane.getChildren().add(gameBorderPane);
            gameover[0] = false;
            player = 1;
            myStage.setScene(gameScene);
        });

        Button optionsButton = new Button("Options");
        optionsButton.setOnAction(e -> {
            myStage.setScene(optionsScene);
            optionsPane.getChildren().add(gameBorderPane);

        });

        titleVBox.getChildren().addAll(compoundButton, threeDButton, normalButton,optionsButton);
        titleVBox.setAlignment(Pos.CENTER);
        titlePane.setCenter(titleVBox);

        //Title screen Text
        VBox textvBox = new VBox();
        Text text =new Text("Compound TicTacToe");
        text.setScaleX(2);
        text.setScaleY(2);
        textvBox.getChildren().add(text);
        textvBox.setAlignment(Pos.CENTER);
        textvBox.setSpacing(25);
        titlePane.setTop(textvBox);
    
        gamePane.setOnMouseClicked(e -> {
            if(gameover[0])
            {
                return;
            }

            ((Grid)grid).isClickedOn(gamePane, e.getX(), e.getY(), currentX, currentY);

            int winner = ((Grid)grid).checkWin();
            if(winner == 1)
            {
                Grid.setAsX(gamePane, grid);
                gameover[0] = true;
            }
            else if(winner == 2)
            {
               Grid.setAsO(gamePane, grid);
               gameover[0] = true;
            }

            if(!(((Grid)grid).getTile(currentX, currentY) instanceof Grid))
            {
                currentX = -1;
                currentY = -1;
                System.out.println("void current: "+App.currentX+" "+App.currentY);
            }
            if(currentX == -1 && currentY == -1)
            {
                updateIndicatorRec(grid.initialRec);
            }
            else
            {
                updateIndicatorRec(((Grid)grid).getTile(currentX, currentY).initialRec);
            }
        });


        game3dPane.setOnMouseClicked(e -> {
            if(gameover[0])
            {
                return;
            }
                System.out.println(gameover[0]);
                for(int x = 0; x < grid3d.length; x++)
                {
                    ((Grid)grid3d[x]).isClickedOn(game3dPane, e.getX(), e.getY());
                    int winner = ((Grid)grid3d[x]).checkWin();
                    if(winner == 1)
                    {
                        grid3d[x] = new MyX(game3dPane, grid3d[x].initialRec);
                        ((MyX)grid3d[x]).setFill(App.player1Color);
                        gameover[0] = true;
                        return;
                    }
                    else if(winner == 2)
                    {
                        grid3d[x] = new MyO(game3dPane, grid3d[x].initialRec);
                        ((MyX)grid3d[x]).setFill(App.player2Color);
                        gameover[0] = true;
                        return;
                    }
                }

                for(int x = 0; x < 3; x++)
                {
                    for(int y = 0; y < 3; y++)
                    {
                        if(((Grid)grid3d[0]).getTile(x, y).player == ((Grid)grid3d[1]).getTile(x, y).player 
                        && ((Grid)grid3d[0]).getTile(x, y).player == ((Grid)grid3d[2]).getTile(x, y).player
                        && ((Grid)grid3d[0]).getTile(x, y).player != 0)
                        {
                            gameover[0] = true;
                            return;
                        }
                    }
                }
                for(int x = 0; x < 3; x++)
                {
                        if(((Grid)grid3d[0]).getTile(x, 0).player == ((Grid)grid3d[1]).getTile(x, 1).player 
                        && ((Grid)grid3d[0]).getTile(x, 0).player == ((Grid)grid3d[2]).getTile(x, 2).player
                        && ((Grid)grid3d[0]).getTile(x, 0).player != 0)
                        {
                            gameover[0] = true;
                            return;
                        }

                        if(((Grid)grid3d[0]).getTile(x, 2).player == ((Grid)grid3d[1]).getTile(x, 1).player 
                        && ((Grid)grid3d[0]).getTile(x, 2).player == ((Grid)grid3d[2]).getTile(x, 0).player
                        && ((Grid)grid3d[0]).getTile(x, 2).player != 0)
                        {
                            gameover[0] = true;
                            return;
                        }

                        if(((Grid)grid3d[0]).getTile(0, x).player == ((Grid)grid3d[1]).getTile(1, x).player 
                        && ((Grid)grid3d[0]).getTile(0, x).player == ((Grid)grid3d[2]).getTile(2, x).player
                        && ((Grid)grid3d[0]).getTile(0, x).player != 0)
                        {
                            gameover[0] = true;
                            return;
                        }

                        if(((Grid)grid3d[0]).getTile(2, x).player == ((Grid)grid3d[1]).getTile(1, x).player 
                        && ((Grid)grid3d[0]).getTile(2, x).player == ((Grid)grid3d[2]).getTile(0, x).player
                        && ((Grid)grid3d[0]).getTile(2, x).player != 0)
                        {
                            gameover[0] = true;
                            return;
                        }
                }
            
            
                if(((Grid)grid3d[0]).getTile(0, 0).player == ((Grid)grid3d[1]).getTile(1, 1).player 
                && ((Grid)grid3d[0]).getTile(0, 0).player == ((Grid)grid3d[2]).getTile(2, 2).player
                && ((Grid)grid3d[0]).getTile(0, 0).player != 0)
                {
                    gameover[0] = true;
                    return;
                }

                if(((Grid)grid3d[0]).getTile(2, 0).player == ((Grid)grid3d[1]).getTile(1, 1).player 
                && ((Grid)grid3d[0]).getTile(2, 0).player == ((Grid)grid3d[2]).getTile(0, 2).player
                && ((Grid)grid3d[0]).getTile(2, 0).player != 0)
                {
                    gameover[0] = true;
                    return;
                }
        });


        optionsPane.getChildren().add(gameBorderPane);

        MyX optionMyX = new MyX(optionsPane, new Rectangle(20, 10, 50, 50));
        MyO optionMyO = new MyO(optionsPane, new Rectangle(120, 10, 50, 50));

        optionMyX.setFill(player1Color);
        optionMyO.setFill(player2Color);

        Tile[] colorOptionRectangles = new Tile[10];
        Color[] colorOptions = {Color.RED, Color.ORANGE , Color.GREEN, Color.BLUE, Color.PURPLE};
        int squaresize = 25;
        for(int x = 0; x < colorOptionRectangles.length; x++)
        {
            colorOptionRectangles[x] = new Tile(optionsPane, 
                new Rectangle(
                    (x < colorOptionRectangles.length / 2 ? 32 : 132),
                    squaresize * (x % (colorOptionRectangles.length / 2)) + 60,
                    squaresize, squaresize
                    ));
            colorOptionRectangles[x].bounds.setFill(colorOptions[x % colorOptions.length]);
            optionsPane.getChildren().add(colorOptionRectangles[x].bounds);
        }


        optionsPane.setOnMouseClicked(e -> { 
            for(int x = 0; x < colorOptionRectangles.length; x++)
            {
                if(colorOptionRectangles[x].isWithinBounds(e.getX(), e.getY()))
                {
                    if(x < colorOptionRectangles.length / 2)
                    {
                        player1Color = colorOptions[x % colorOptions.length];
                        optionMyX.setFill(player1Color);
                    }
                    else
                    {
                        player2Color = colorOptions[x % colorOptions.length];                   
                        optionMyO.setFill(player2Color);
                    }
                }
            }
        });


        
        
        myStage.setTitle("TicTacToe");
        myStage.show();  
    }

    public void updateIndicatorRec(Rectangle bounds){

        Rectangle initialIndicatorRec = new Rectangle(0, 0, 190, 190);
        Rectangle indicatorBounds = new Rectangle();
        indicatorBounds.heightProperty().bind(gamePane.heightProperty().divide(190.0 / bounds.heightProperty().doubleValue()).add(10));
        indicatorBounds.widthProperty().bind(gamePane.widthProperty().divide(190.0 / bounds.widthProperty().doubleValue()).add(10));
        indicatorBounds.xProperty().bind(gamePane.widthProperty().divide(190.0 / bounds.xProperty().doubleValue()).subtract(5));
        indicatorBounds.yProperty().bind(gamePane.heightProperty().divide(190.0 / bounds.yProperty().doubleValue()).subtract(5));

        //seting scale
        currentGirdIndicator.scaleXProperty().bind(indicatorBounds.widthProperty().divide(initialIndicatorRec.widthProperty()).multiply(17.0/19));
        currentGirdIndicator.scaleYProperty().bind(indicatorBounds.heightProperty().divide(initialIndicatorRec.heightProperty()).multiply(17.0/19));

        //setting x and y 
        currentGirdIndicator.layoutXProperty().bind(indicatorBounds.widthProperty().divide(19).add(indicatorBounds.xProperty()).add(currentGirdIndicator.scaleXProperty().subtract(1).divide(2).multiply(initialIndicatorRec.widthProperty())));
        currentGirdIndicator.layoutYProperty().bind(indicatorBounds.heightProperty().divide(19).add(indicatorBounds.yProperty()).add(currentGirdIndicator.scaleYProperty().subtract(1).divide(2).multiply(initialIndicatorRec.heightProperty())));
    }

    public void setScaleableBounds(Shape shape, Rectangle bounds){
        Rectangle scaldedBounds = new Rectangle();
        scaldedBounds.heightProperty().bind(optionsPane.heightProperty().divide(190.0 / bounds.heightProperty().doubleValue()));
        scaldedBounds.widthProperty().bind(optionsPane.widthProperty().divide(190.0 / bounds.widthProperty().doubleValue()));
        scaldedBounds.xProperty().bind(optionsPane.widthProperty().divide(190.0 / bounds.xProperty().doubleValue()));
        scaldedBounds.yProperty().bind(optionsPane.heightProperty().divide(190.0 / bounds.yProperty().doubleValue()));

        //seting scale
        shape.scaleXProperty().bind(scaldedBounds.widthProperty().divide(bounds.widthProperty()));
        shape.scaleYProperty().bind(scaldedBounds.heightProperty().divide(bounds.heightProperty()));

        //setting x and y 
        shape.layoutXProperty().bind(scaldedBounds.widthProperty().divide(19).add(scaldedBounds.xProperty()).add(shape.scaleXProperty().subtract(1).divide(2).multiply(bounds.widthProperty())));
        shape.layoutYProperty().bind(scaldedBounds.heightProperty().divide(19).add(scaldedBounds.yProperty()).add(shape.scaleYProperty().subtract(1).divide(2).multiply(bounds.heightProperty())));
    }

    
    public static void main(String[] args)
    {
        Application.launch();   
    }
}
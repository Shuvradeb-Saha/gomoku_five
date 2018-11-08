package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class GameController {
	@FXML
	private AnchorPane anchorPane;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private GridPane gridPane;

	public static Button[][] squares;

	int size = 10;
	public int[][] board = new int[size][size];
	public static Thread thread;
	private Thread t;
	public static String themeName;
	@FXML
	void initialize() {
		assert gridPane != null : "fx:id=\"gridPane\" was not injected: check your FXML file 'GomokuBoard.fxml'.";
		squares = new Button[size][size];
		
		initializeBoard();
		//System.out.println("game controller init");
		ButtonHandler.board = board;
		
		ButtonHandler buttonhandler = new ButtonHandler();
		if(themeName.equals("Brown Wood")) {
			anchorPane.setStyle("-fx-background-image: url('bc1.jpg');");
			ButtonHandler.img1 = new Image("rp1.png");
			ButtonHandler.img2 = new Image("rp2.png");
			
		}else if(themeName.equals("Bluish Wood")){
			anchorPane.setStyle("-fx-background-image: url('images/bc3.jpg');");
			ButtonHandler.img1 = new Image("p1.png");
			ButtonHandler.img2 = new Image("p2.png");
		}else {
			anchorPane.setStyle("-fx-background-color: lightgray;");
			ButtonHandler.img1 = new Image("images/hp1.png");
			ButtonHandler.img2 = new Image("images/hp2.png");
		}
		
		/*
		 * 
		 * */
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				// System.out.println(i+j);
				squares[i][j] = new Button();
				squares[i][j].setPrefSize(70, 70);
				squares[i][j].setStyle("-fx-background-color: transparent;");
				squares[i][j].setId(j + "" + i + "");
				gridPane.add(squares[i][j], i, j);

				squares[i][j].setOnAction(buttonhandler);

			}

		}

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (MainController.state) {
					// System.out.println("dd");
					if (MainController.computerMove) {
						gridPane.setDisable(true);
					} else {
						gridPane.setDisable(false);
					}
				}
				if(!MainController.state) {
					gridPane.setDisable(true);
					for(int i=0; i<size; i++) {
						for(int j=0; j<size; j++) {
							squares[i][j].setStyle("-fx-opacity: 1;-fx-background-color: transparent;");
						}
					}
					
				}

			}
		});
		t.start();
	}
	
	@FXML
    private Button backBtn;

    @FXML
    private Button exitBtn;

    @FXML
    void backStage(ActionEvent event) throws IOException {
    	MainController.state = true;
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("StartPage.fxml"));
		anchorPane.getChildren().setAll(pane);

    }

    @FXML
    void exitGame(ActionEvent event) {
    	System.exit(0);
    }
	
	
	
	
	
	/*
	 * @FXML
    private Button restartBtn;

    @FXML
    void restartGame(ActionEvent event) {
    	((Stage) anchorPane.getScene().getWindow()).close();
    	Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("GomokuBoard.fxml"));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent arg0) {
				// TODO Auto-generated method stub
				stage.close();
				System.exit(0);
			}
		});
		System.out.println("restart clicked");
		
    }*/

	private void initializeBoard() {

		for (int i = 0; i < size; i++) {
			board[i] = new int[size];
			for (int j = 0; j < size; j++) {
				board[i][j] = 0;
			}
		}

	}
}

package application;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import minimax.GomokuMinimax;
import minimax.Move;

public class MainController {

	private Thread thread;
	public static boolean state = true;
	public static volatile boolean computerMove = false;
	public static boolean withAi;

	@FXML
	private Button startBtn;

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private ComboBox<String> gameMode;

	@FXML
	private ComboBox<String> theme;

	@FXML
	void initialize() {
		assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'StartPage.fxml'.";
		assert startBtn != null : "fx:id=\"startBtn\" was not injected: check your FXML file 'StartPage.fxml'.";
		// System.out.println("main controller init");
		gameMode.getItems().addAll("Two Player", "Computer");

		theme.getItems().addAll("Brown Wood", "Bluish Wood", "White Board");

		thread = new Thread(new Runnable() {

			public void run() {
				try {

					while (state) {
						if (computerMove) {
							// System.out.println("computer move");
							Move aiMove = GomokuMinimax.getAIMove();
							GomokuMinimax.board[aiMove.row][aiMove.col] = 2;
							String id = aiMove.row + "" + aiMove.col;
							// System.out.println(id);
							// ButtonHandler.moveTracker.put(id, "o");

							Platform.runLater(new Runnable() {

								@Override
								public void run() {
									System.out.println("hjkjkj");
									// TODO Auto-generated method stub
									ImageView iv2;
									if (GameController.themeName.equals("Brown Wood")) {

										iv2 = new ImageView(new Image("rp2.png"));
									} else if (GameController.themeName.equals("Bluish Wood")) {
										iv2 = new ImageView(new Image("p2.png"));
									} else {
										iv2 = new ImageView(new Image("/images/hp2.png"));
									}

									GameController.squares[aiMove.col][aiMove.row].setGraphic(iv2);
									GameController.squares[aiMove.col][aiMove.row].setDisable(true);
									GameController.squares[aiMove.col][aiMove.row]
											.setStyle("-fx-opacity: 1;-fx-background-color: transparent;");
									if (ButtonHandler.winChecker(2)) {
										Alert alert = new Alert(AlertType.INFORMATION);
										alert.setTitle("GAME RESULT");
										alert.setHeaderText(null);
										alert.setContentText("Computer Won");

										alert.showAndWait();
										state = false;
									}

								}
							});

							computerMove = false;
						}
						// System.out.println("l");
					}

				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});

		thread.start();

	}

	@FXML
	public void startGame(ActionEvent event) throws IOException {

		// System.out.println(gameMode.getValue());
		if (gameMode.getValue().equals("Two Player")) {
			withAi = false;
		} else {
			withAi = true;
		}
		GameController.themeName = theme.getValue();
		// System.out.println(GameController.themeName);
		state = true;

		AnchorPane pane = FXMLLoader.load(getClass().getResource("GomokuBoard.fxml"));
		anchorPane.getChildren().setAll(pane);

	}

}

package application;

import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	public Parent root;

	public static Stage parentStage;

	@Override
	public void start(Stage primaryStage) {

		try {
			root = FXMLLoader.load(getClass().getResource("Splash.fxml"));// GomokuBoard  Splash StartPage
			Scene sc = new Scene(root);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setTitle("Gomoku");
			primaryStage.setScene(sc);
			primaryStage.setResizable(false);
			primaryStage.show();

			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent arg0) {
					Platform.exit();
					System.exit(0);
				}
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}

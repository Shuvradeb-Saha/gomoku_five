package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SplashController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private StackPane stackPane;

	@FXML
	void initialize() {
		assert stackPane != null : "fx:id=\"stackPane\" was not injected: check your FXML file 'Splash.fxml'.";
		SplashSleep splashSleep = new SplashSleep();
		splashSleep.start();

	}

	class SplashSleep extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(3000);

				Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
						Parent root = null;
						try {
							root = FXMLLoader.load(getClass().getResource("StartPage.fxml"));
							
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
						
						((Stage) stackPane.getScene().getWindow()).hide();

					}
				});

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}

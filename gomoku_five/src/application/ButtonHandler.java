
package application;

import java.util.HashMap;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import minimax.GomokuMinimax;


public class ButtonHandler implements EventHandler {
	private static int counter = 1;
	public static HashMap<String, String> moveTracker = new HashMap<>();
	public static Image img1;
	public static Image img2;
	public static int[][] board;
	// public static GomokuMinimax gomokuMinimax;
	public static GomokuMinimax gomokuMinimax;

	@Override
	public void handle(javafx.event.Event e) {
		// TODO Auto-generated method stub

		Image i1 = img1;// new Image("rp1.png");
		ImageView iv1 = new ImageView(i1);
		Image i2 = img2;// new Image("rp2.png");
		ImageView iv2 = new ImageView(i2);

		Button source = (Button) e.getSource();

		// System.out.println("button = "+source.getId());
		if (MainController.withAi) {
		//	if (!moveTracker.containsKey(source.getId())) {
				//
				source.setGraphic(iv1);
				source.setDisable(true);
				source.setStyle("-fx-opacity: 1;-fx-background-color: transparent;");

				moveTracker.put(source.getId(), "e");

				String[] s = source.getId().split("");
				int i = Integer.parseInt(s[0]);
				int j = Integer.parseInt(s[1]);
				board[i][j] = 1;
				GomokuMinimax.a = i;
				GomokuMinimax.b = j;
				GomokuMinimax.board = board;
				// GameController.threadValue = 2;
				if(winChecker(1)) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("GAME RESULT");
					alert.setHeaderText(null);
					alert.setContentText("You Won");

					alert.showAndWait();
					MainController.state = false;	
					
				}
				
				if(MainController.state) {
					MainController.computerMove = true;
					//System.out.println("come");
				}
					
				

				return;

		//	}
		} else {
			//if (!moveTracker.containsKey(source.getId())) {
				String[] s = source.getId().split("");
				int i = Integer.parseInt(s[0]);
				int j = Integer.parseInt(s[1]);

				if (counter % 2 == 0) {
					board[i][j] = 2;
					moveTracker.put(source.getId(), "e");
					source.setGraphic(iv1);

					if (counter >= 9) {
						if (winChecker(2)) {
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("GAME RESULT");
							alert.setHeaderText(null);
							alert.setContentText("Player 2 Won");

							alert.showAndWait();
							MainController.state = false;
						}
					} //
					//System.out.println(source.getId() + " ec = " + counter);
				} else {
					board[i][j] = 1;
					moveTracker.put(source.getId(), "o");
					source.setGraphic(iv2);

					if (counter >= 9) {
						if (winChecker(1)) {
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("GAME RESULT");
							alert.setHeaderText(null);
							alert.setContentText("Player 1 Won");

							alert.showAndWait();
							MainController.state = false;
						}
					}
				}
				counter++;
				if (counter == 81) {
					System.out.println("Match draw");
				}
		//	}

		}

		return;
	}

	public static boolean winChecker(int checkFor) {
		if (checkHorizonatlly(checkFor) || checkRightDiagonally(checkFor) || checkVertically(checkFor)
				|| checkLeftDiagonally(checkFor)) {
			return true;
		}
		return false;
	}

	private static boolean checkHorizonatlly(int checkFor) {
		int size = board.length;
		boolean flag = false;
		for (int i = 0; i < size; i++) {
			int c = 0;
			for (int j = 0; j < size; j++) {
				if (board[i][j] == checkFor)
					c++;
				else
					c = 0;

				if (c == 5) {
					flag = true;
					break;
				}

			}
		}
		return flag;
	}

	private static boolean checkVertically(int checkFor) {
		int size = board.length;
		boolean flag = false;
		for (int i = 0; i < size; i++) {
			int c = 0;
			for (int j = 0; j < size; j++) {
				if (board[j][i] == checkFor)
					c++;
				else
					c = 0;

				if (c == 5) {
					flag = true;
					break;
				}

			}
		}

		return flag;
	}

	private static boolean checkRightDiagonally(int checkFor) {
		int size = board.length;
		boolean flag = false;
		int c = 0;
		// major diag
		for (int i = 0; i < size; i++) {
			if (board[i][i] == checkFor) {
				c++;
			} else
				c = 0;
			if (c == 5)
				return true;
		}
		for (int i = 1; i <= size - 5; i++) {
			int m = 0;
			c = 0;
			for (int j = i; j < size; j++) {
				if (board[m++][j] == checkFor)
					c++;
				else
					c = 0;
				if (c == 5)
					return true;
			}
		}
		for (int i = 1; i <= size - 5; i++) {
			int m = 0;
			c = 0;
			for (int j = i; j < size; j++) {
				if (board[j][m++] == checkFor)
					c++;
				else
					c = 0;
				if (c == 5)
					return true;
			}

		}

		return flag;

	}

	private static boolean checkLeftDiagonally(int checkFor) {
		int size = board.length;
		int c;
		for (int i = size - 6; i < size; i++) {
			int m = 0;
			c = 0;
			for (int j = i; j >= 0; j--) {
				if (board[m++][j] == checkFor)
					c++;
				else
					c = 0;
				if (c == 5)
					return true;
			}

		}

		for (int i = 1; i <= size - 5; i++) {
			int m = size - 1;
			c = 0;
			for (int j = i; j < size; j++) {
				if (board[j][m--] == checkFor)
					c++;
				else
					c = 0;
				if (c == 5)
					return true;

			}
		}
		return false;

	}

}

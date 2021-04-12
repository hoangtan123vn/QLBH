package Nhacungcap;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main  extends Application {
	@Override
	public void start(Stage stage) throws Exception {
	//	Parent root = FXMLLoader.load(getClass().getResource("/Nhanvien/nhanvien.fxml"));
	//	Parent root = FXMLLoader.load(getClass().getResource("chucnangquanly.fxml"));
		Parent root = FXMLLoader.load(getClass().getResource("nhacungcap.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("nhacungcap.css").toExternalForm());
		stage.getIcons().add(new Image(Main.class.getResourceAsStream("backgroundSGU.png")));
		stage.setResizable(false);
		stage.setScene(scene);
		stage.setTitle("Phan mem quan ly ban hang");
		stage.show();	

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub 
		launch(args);
	}

}

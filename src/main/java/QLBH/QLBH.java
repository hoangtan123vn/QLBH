package QLBH;
import java.sql.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import Nhacungcap.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class QLBH extends Application {
	@Override
	public void start(Stage stage) throws Exception {
	//	Parent root = FXMLLoader.load(getClass().getResource("/Nhanvien/nhanvien.fxml"));
		//Parent root = FXMLLoader.load(getClass().getResource("chucnangquanly.fxml"));
		Parent root = FXMLLoader.load(getClass().getResource("loginform.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("QLBH.css").toExternalForm());
		stage.getIcons().add(new Image(QLBH.class.getResourceAsStream("backgroundSGU.png")));
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Phần mềm quản lý cửa hàng tiện lợi");
		stage.show();	

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub 
		launch(args);
	}
}

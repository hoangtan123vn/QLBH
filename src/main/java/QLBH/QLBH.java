package QLBH;
import java.sql.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class QLBH extends Application {
	private static double xoffset =0; 
	private static double yoffset =0; 
	@Override
	public void start(Stage stage) throws Exception {
	//	Parent root = FXMLLoader.load(getClass().getResource("/Nhanvien/nhanvien.fxml"));
		//Parent root = FXMLLoader.load(getClass().getResource("chucnangquanly.fxml"));
		Parent root = FXMLLoader.load(getClass().getResource("loginform.fxml"));
		Scene scene = new Scene(root);
		scene.setFill(null);
		scene.getStylesheets().add(getClass().getResource("QLBH.css").toExternalForm());
		//scene.getStylesheets().add(getClass().getResource("thongke.css").toExternalForm());
	//	scene.addEventHandler(null, null);
	//	Scene scene = new Scene(root,337,408);
		scene.setOnMousePressed(new EventHandler<MouseEvent>() {
			  @Override public void handle(MouseEvent mouseEvent) {
			    // record a delta distance for the drag and drop operation.
			    xoffset = stage.getX() - mouseEvent.getScreenX();
			    yoffset = stage.getY() - mouseEvent.getScreenY();
			  }
			});
			scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
			  @Override public void handle(MouseEvent mouseEvent) {
			    stage.setX(mouseEvent.getScreenX() + xoffset);
			    stage.setY(mouseEvent.getScreenY() + yoffset);
			  }
			});
			
		stage.getIcons().add(new Image(QLBH.class.getResourceAsStream("backgroundSGU.png")));
		stage.setScene(scene);
		stage.setResizable(false);
		stage.initStyle(StageStyle.TRANSPARENT);
	//	stage.initModality(Modality.APPLICATION_MODAL);
		//stage.resizableProperty().setValue(Boolean.FALSE);
		stage.setTitle("Phần mềm quản lý cửa hàng tiện lợi");
		stage.show();	
		

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub 
		launch(args);
	}
}

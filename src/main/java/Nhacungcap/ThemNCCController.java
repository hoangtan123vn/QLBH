package Nhacungcap;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

import java.util.List;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javax.persistence.criteria.CriteriaQuery;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;
import QLBH.Nhanvien;
import QLBH.Nhacungcap;

public class ThemNCCController extends Application implements Initializable{
/*	public static ThemNCCController instance;

    public ThemNCCController() {
        instance = this;
    }

    public static ThemNCCController getInstance() {
        return instance;
    }
*/	
	@FXML
    private TextField tfncc;

    @FXML
    private TextField tftenncc;

    @FXML
    private TextField tfsdt;

    @FXML
    private TextField tfdiachi;

    @FXML
    private TextField tfemail;
    
    @FXML
    private TextField tfsotienno;

    @FXML
    private Button idsave;

    @FXML
    private Button idclose;

    @FXML
    void close(ActionEvent event) {
    	 Stage stage = (Stage) idclose.getScene().getWindow();
    	 stage.close();
    }

    @FXML
    void save(ActionEvent event) {
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Them Nha cung cap");
		 
    	//Integer mancc = Integer.parseInt(tfncc.getText());
    	String tenncc = tftenncc.getText();
    	String diachi = tfdiachi.getText();
    	Integer sodienthoai = Integer.parseInt(tfsdt.getText());
    	String email = tfemail.getText();
    	Integer sotienno = Integer.parseInt(tfsotienno.getText());
    	
    
    	StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
				.configure("hibernate.cfg.xml")
				.build();
		Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
		Session session = sessionFactory.openSession();
		
    	try {
    		
    		 Nhacungcap nv = new Nhacungcap(tenncc,diachi,sodienthoai,email,sotienno);
    		 session.beginTransaction();
    		 session.save(nv);
    		 session.getTransaction().commit();
    		 //Nhacungcap ncc = new Nhacungcap(tenncc,diachi,sodienthoai,email);
    		 Stage stage = (Stage) idsave.getScene().getWindow();
    		 
        	 stage.close();
        	 alert.setContentText("Them nha cung cap thanh cong !");
        	 alert.showAndWait();    
        	 
        	 
        	nhacungcapController.getInstance().ReloadNHACUNGCAP();
        	
    	}
    	catch (RuntimeException error){
    		 alert.setContentText("Them nha cung cap that bai  !");
    		 alert.showAndWait();
    		session.getTransaction().rollback();
    	}
    }
   
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}
	/*public void showMessageDialog() {
		Alert alert = new Alert(AlertType.INFORMATION);
		 alert.setTitle("Test Connection");
	}*/

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}

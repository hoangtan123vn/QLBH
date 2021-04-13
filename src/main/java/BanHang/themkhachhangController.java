package BanHang;



import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import QLBH.KhachHang;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class themkhachhangController  implements Initializable{

    @FXML
    private TextField numberphone;

    @FXML
    private TextField address;

    @FXML
    private TextField mail;

    @FXML
    private ComboBox <String> sex;

    @FXML
    private TextField name;

    @FXML
    private DatePicker birth;

    @FXML
    private Button them;

    @FXML
    private Button quaylai;

  
    @FXML
    void themkhachhang(ActionEvent event) {
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
    	String hoten = name.getText();
    	String diachi = address.getText();
    	int sdt= Integer.parseInt(numberphone.getText());
    	LocalDate ngaysinh = birth.getValue();
    	String gioitinh = sex.getValue();
    	String email = mail.getText();
    	StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
				.configure("hibernate.cfg.xml")
				.build();
		Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
		Session session = sessionFactory.openSession();
		KhachHang khachhang = new KhachHang(hoten,diachi,sdt,ngaysinh,gioitinh,email);
		
		try {
    		 session.beginTransaction();
    		 session.save(khachhang);
    		 session.getTransaction().commit();		
        	 alert.setContentText("Them khach hang thanh cong !");
        	 alert.showAndWait();    
        	 
        	
    	}
    	catch (RuntimeException error){
    		
    		 alert.setContentText("Them khach hang that bai  !");
    		 alert.showAndWait();
    		session.getTransaction().rollback();
    	
    	}
    }

    @FXML
    void quaylai(ActionEvent event) {
    	Stage stage = (Stage) quaylai.getScene().getWindow();
    	stage.close();
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ObservableList<String> list=FXCollections.observableArrayList("male","Pemale");
		sex.setItems(list);
		
		
	}

}


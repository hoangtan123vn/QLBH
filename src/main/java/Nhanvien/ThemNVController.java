package Nhanvien;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
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
import java.time.LocalDate;
import java.util.ResourceBundle;
import javax.persistence.criteria.CriteriaQuery;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.descriptor.sql.NVarcharTypeDescriptor;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;

import QLBH.HibernateUtils;
import QLBH.Nhanvien;
import QLBH.Taikhoannv;

public class ThemNVController extends Application implements Initializable{
	private FileChooser filechooser;
	private Image image;
	private Blob image1;
	private File file;
	
	//private final Desktop desktop = Desktop.getDesktop();
    @FXML
    private TextField tfhovaten;
    
    @FXML
    private AnchorPane ap;
    
    @FXML
    private ImageView imgview;

    @FXML
    private TextField tfsdt;

    @FXML
    private ComboBox<String> tfcv;

    @FXML
    private TextField tfcmnd;

    @FXML
    private TextField tfdc;

    @FXML
    private Button addImage;

    @FXML
    private Button add;

    @FXML
    private Button huy;
    
    @FXML
    private TextField tfid;
    
    @FXML
    private ComboBox<String> tfgt;
    
    @FXML
    private DatePicker tfngayvaolam;
    
    @FXML
    private DatePicker tfns;
    
    @FXML
    private TextField user;

    @FXML
    private PasswordField xacnhanpass;

    @FXML
    private Label thongbao;
    
    @FXML
    private PasswordField pass;
    @FXML
     void AddImage(ActionEvent event) {
    	 Stage stage = (Stage) ap.getScene().getWindow();
    	 FileChooser fileChooser = new FileChooser();
    	 FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
    	 fileChooser.getExtensionFilters().add(imageFilter);
    	 file = fileChooser.showOpenDialog(stage);
    	 if(file!=null) {
    		image = new Image(file.toURI().toString(),100,150,false,true);
    		imgview.setImage(image);
    	}
    }
    

    @FXML
     void HuyThemNV(ActionEvent event) {
    	//System.exit(0);
    	 Stage stage = (Stage) huy.getScene().getWindow();
    	 stage.close();
    }
    

    @FXML
     void ThemNVButton(ActionEvent event) throws IOException  {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Them nhan vien");
		int t5 ,t6;
		//KIỂM TRA HỌ VÀ TÊN 
		String t1 = tfhovaten.getText();
		if(t1.isEmpty()) {
			alert.setContentText("Bạn chưa nhập Họ và tên nhân viên");
			alert.showAndWait();
			return;
		}
		//KIỂM TRA SỐ ĐIỆN THOẠI
		try {
	//	int id = Integer.parseInt(tfid.getText());
			t5 = Integer.parseInt(tfsdt.getText());
			if(String.valueOf(tfsdt.getText()).length() <10) {
				alert.setContentText("SĐT phải có 10 số ! " + (String.valueOf(tfsdt.getText()).length()));
				alert.showAndWait();
				return;
			}

		}catch (NumberFormatException e) {
			alert.setContentText("SĐT phải là số");
			alert.showAndWait();
			return;
		}
		//KIỂM TRA CHỨNG MINH NHÂN DÂN
		try {
			//	int id = Integer.parseInt(tfid.getText());
			t6 = Integer.parseInt(tfcmnd.getText());
				
		}catch (NumberFormatException e) {
			alert.setContentText("CMND phải là số");
			alert.showAndWait();
			return;
		}
		
		
		
		//int t2 = Integer.parseInt(tfns.getText());
		LocalDate t2 = tfns.getValue();
		String t3 = tfcv.getValue();
		String t4 = tfgt.getValue();
	
		
		String t7 = tfdc.getText();
		LocalDate t8 = tfngayvaolam.getValue();
		String taikhoan = user.getText();
		String matkhau = pass.getText();
		String xacnhanmatkhau = xacnhanpass.getText();
		if (taikhoan.isEmpty()) {
        thongbao.setText("Bạn chưa nhập tài khoản !");
        return;
		}
		else if (matkhau.isEmpty()) {
        thongbao.setText("Bạn chưa nhập mật khẩu !");
        return ;
		}
		else if (!xacnhanmatkhau.equals(matkhau)) {
    	thongbao.setText("Mật khẩu của bạn k đúng  !");
        return ;
		}
    
		Session session = HibernateUtils.getSessionFactory().openSession();
		 FileInputStream fis = new FileInputStream(file);
		 byte[] bFile = new byte[(int) (file.length())];
		 fis.read(bFile);
		 Nhanvien nv = new Nhanvien(t1,t2,t3,t4,t5,t6,t7,bFile,t8);
		Taikhoannv tk = new Taikhoannv(taikhoan,matkhau,nv);
		try {
    		 session.beginTransaction();
    		 session.save(nv);
    		 session.save(tk);
    		 session.getTransaction().commit();
    		 Stage stage = (Stage) ap.getScene().getWindow();
        	 stage.close();
        	
        	 alert.setContentText("Them nhan vien thanh cong !");
        	 
        //	 System.out.println();
        	 alert.showAndWait();    	
    	}
    	catch (RuntimeException error){
    		 System.out.println(error);
    		 alert.setContentText("Them nhan vien that bai  !");
    		 alert.showAndWait();
    		session.getTransaction().rollback();
    	}
		 NhanvienController.getInstance().reloadNHANVIEN();
  //  	
    	 
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
		ObservableList<String> list=FXCollections.observableArrayList("Nhân viên","Quản lý");
		ObservableList<String> list1=FXCollections.observableArrayList("Nam","Nữ");
		tfcv.setItems(list);
		tfgt.setItems(list1);
		//RequiredFielDValidator validator = new RequiredFieldValidator();
		
		
	}

}

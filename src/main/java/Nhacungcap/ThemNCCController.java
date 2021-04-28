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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import QLBH.GiaoDienQLController;
import QLBH.HibernateUtils;
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
    private ImageView exit;
    
    @FXML
    void exit(MouseEvent event) {
    	Stage stage = (Stage) exit.getScene().getWindow();
		stage.close();
		GiaoDienQLController.getInstance().falsedisable();
    }

    @FXML
    void close(ActionEvent event) {
    	 Stage stage = (Stage) idclose.getScene().getWindow();
    	 stage.close();
    	 GiaoDienQLController.getInstance().falsedisable();
    }

    @FXML
    void save(ActionEvent event) {
    	if(KiemTraSDT() & KiemTraTenNCC()& KiemTraEmail()&KiemTraDiaChi()&KiemTraTien()) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Them Nha cung cap");
		 
    	//Integer mancc = Integer.parseInt(tfncc.getText());
    	String tenncc = tftenncc.getText();
    	String diachi = tfdiachi.getText();
    	String sodienthoai = tfsdt.getText();
    	String email = tfemail.getText();
    	Integer sotienno = Integer.parseInt(tfsotienno.getText());
    
    	Session session = HibernateUtils.getSessionFactory().openSession();

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
        	GiaoDienQLController.getInstance().falsedisable();
        	
    	}
    	catch (RuntimeException error){
    		 alert.setContentText("Them nha cung cap that bai  !");
    		 alert.showAndWait();
    		session.getTransaction().rollback();
    	}
    }
    }
    @FXML
    private Label check_sdt;
    
    private boolean KiemTraSDT() {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(tfsdt.getText());
        if(m.find() && m.group().equals(tfsdt.getText())){
            check_sdt.setText(null);
            return true;
        }
        else {
            check_sdt.setText("Vui lòng điền số điện thoại hợp lệ");
            return false;
        }
    }
    
    @FXML
    private Label check_sotienno;
    private boolean KiemTraTien() {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(tfsotienno.getText());
        if(m.find() && m.group().equals(tfsotienno.getText())){
            check_sotienno.setText(null);
            return true;
        }
        else {
            check_sotienno.setText("Vui lòng điền số tiền hợp lệ");
            return false;
        }
    }
    
    @FXML
    private Label check_hoten;
    
    private boolean KiemTraTenNCC() {
		Pattern p = Pattern.compile("^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ ]+$");
		Matcher m = p.matcher(tftenncc.getText());
		if(m.find() && m.group().equals(tftenncc.getText())){
			check_hoten.setText(null);
			return true;
		}
		else {
			check_hoten.setText("Vui lòng điền tên hợp lệ");
			return false;
		}
	}
    
    @FXML
    private Label check_email;
    private boolean KiemTraEmail() {
    	Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    	Matcher m = p.matcher(tfemail.getText());
    	if(m.find() && m.group().equals(tfemail.getText())){
			check_email.setText(null);
			return true;
		}
    	else {
			check_email.setText("Vui lòng điền email hợp lệ");
			return false;
		}
    }
    
    @FXML
    private Label check_diachi;
    private boolean KiemTraDiaChi() {
    	//System.out.println(tfdc.getText());
		if(tfdiachi.getText().isEmpty()){
			check_diachi.setText("Vui lòng điền địa chỉ phù hợp");
			return false;
		}
		check_diachi.setText(null);
		return true;
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

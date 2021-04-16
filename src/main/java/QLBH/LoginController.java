package QLBH;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import org.hibernate.query.Query;
import org.hibernate.type.descriptor.sql.NVarcharTypeDescriptor;

import com.sun.xml.bind.v2.runtime.unmarshaller.Loader;

import Nhanvien.NhanvienController;

import javax.persistence.criteria.CriteriaQuery;

import org.apache.derby.vti.Restriction.AND;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.Window;
import QLBH.GiaoDienQLController;
import QLBH.Taikhoannv;

public class LoginController implements  Initializable{

    @FXML
    private TextField emailIdField;

    @FXML
    private Button submitButton;

    @FXML
	private PasswordField passwordField;
    @FXML
    private Label thongbao;
    
    private Taikhoannv taikhoan;
    

    @FXML
    private ImageView close;
    
    @FXML
    private ImageView minimize;
    
    @FXML
    void close(MouseEvent event) {
    	 Stage stage = (Stage) close.getScene().getWindow();
    	 stage.close();
    }
    @FXML
    void minimize(MouseEvent event) {
    	 Stage stage = (Stage) minimize.getScene().getWindow();
    	 stage.setIconified(true);
    }
     
   @FXML
    void login(ActionEvent event)  {
	   try {
	 Window owner = submitButton.getScene().getWindow();

      System.out.println(emailIdField.getText());
      System.out.println(passwordField.getText());

       if (emailIdField.getText().isEmpty()) {
           thongbao.setText("Bạn chưa nhập tài khoản !");
           return;
       }
       else if (passwordField.getText().isEmpty()) {
           thongbao.setText("Bạn chưa nhập mật khẩu !");
           return ;
       }
      
    Session session = HibernateUtils.getSessionFactory().openSession();
	String username = emailIdField.getText();
	String password =passwordField.getText();
	Taikhoannv taikhoannv = new Taikhoannv (username,password);
		session.getTransaction().begin();
		String hql = "FROM Taikhoannv A WHERE A.username = :username and A.password = :password";
        String hql1 = "SELECT A.username,A.password,B.chucvu FROM Taikhoannv A INNER JOIN A.nhanvien B WHERE A.username = :username and A.password = :password ";
        Query query = session.createQuery(hql);
        query.setParameter("username", username);
        query.setParameter("password", password);
        Query query1 = session.createQuery(hql1);
        query1.setParameter("username", username);
        query1.setParameter("password", password);
        List<Object[]> tk1 = query1.list();
        List<Taikhoannv> checktaikhoan = query.list(); 
        for(Taikhoannv checktk1 : checktaikhoan) {
        	if(checktk1.getusername().contains(taikhoannv.getusername()) && checktk1.getpassword().contains(taikhoannv.getpassword())) {
        		for(Object[] singleRowValues : tk1 ) {
        			String tentaikhoan = (String)singleRowValues[0];
                	String matkhau = (String)singleRowValues[1];
                	String cvString = (String)singleRowValues[2];
                if(tentaikhoan.contains(checktk1.getusername())&& matkhau.contains(checktk1.getpassword()) && cvString.contains("Quản lý")) {
                	String tk = checktk1.getusername();
                	String mk = checktk1.getpassword();
                	taikhoan = session.get(Taikhoannv.class,tk);
                	FXMLLoader loader = new FXMLLoader(getClass().getResource("giaodienquanly.fxml"));
                    Parent tmp = loader.load();
                    Scene scene = new Scene(tmp);
                    Stage stage =(Stage)((Node) event.getSource()).getScene().getWindow();
                    GiaoDienQLController quanly = loader.getController();
                    quanly.LoadData(taikhoan);
                	   stage.hide();
                   	   stage.setScene(scene);
                   	   stage.show();
                	}else if(tentaikhoan.contains(checktk1.getusername())&& matkhau.contains(checktk1.getpassword()) &&cvString.contains("Nhân viên")) {
                		Parent sampleparent = FXMLLoader.load(getClass().getResource("chucnangnhanvien.fxml")) ; 
                    	Scene scene = new Scene(sampleparent);
                    	Stage stage =(Stage)((Node) event.getSource()).getScene().getWindow();
                    	
                    	stage.hide();
                    	stage.setScene(scene);
                    	stage.show();
                	}
        		}
        		Alert alert = new Alert(Alert.AlertType.INFORMATION);
        		alert.setTitle("Đăng nhập");
   		     	alert.setContentText("Đăng nhập thành công");
                alert.showAndWait();
                break;
        }

   }
        thongbao.setText("Đăng nhập thất bại");
        
	}
        
        catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   } 		


   public void initialize(URL url, ResourceBundle rb) {
	   
	      
   }
 
   
}

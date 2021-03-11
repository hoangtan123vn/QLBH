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

import javax.persistence.criteria.CriteriaQuery;

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
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.Window;

public class LoginController implements  Initializable{

    @FXML
    private TextField emailIdField;

    @FXML
    private Button submitButton;

    @FXML
	private PasswordField passwordField;
    @FXML
    private Label thongbao;
     
   @FXML
    void login(ActionEvent event) throws IOException {
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
      
   
  
   	StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
			.configure("hibernate.cfg.xml")
			.build();
	Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
	SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
	Session session = sessionFactory.openSession();
	String username = emailIdField.getText();
	String password =passwordField.getText();
	String chucvu= null;
	//String hql = "SELECT u.username, u.password FROM taikhoannv u WHERE u.username = :username, u.password= :password";	
	//String hql = "SELECT u.username FROM Taikhoannv u WHERE u.username = :username";
	//Taikhoannv taikhoan = new Taikhoannv ( username,password);
		Nhanvien nhanvien = new Nhanvien(username, password);

	//Nhanvien nhanvien = new Nhanvien (chucvu);
	try {
		session.getTransaction().begin();
      
        String hql = "FROM Nhanvien A WHERE A.username = :username and A.password = :password and A.chucvu='QL' ";
        String hql2 ="FROM Nhanvien A WHERE A.username = :username and A.password = :password and A.chucvu='NV' ";
        Query query = session.createQuery(hql);
        Query query2 = session.createQuery(hql2);
        List<Nhanvien> nhanvien1 = session.createQuery(hql).setParameter("username", username).setParameter("password",password).list();
        for(Nhanvien b  : nhanvien1) {
        	 
               if(b.getusername().contains(nhanvien.getusername()) && b.getpassword().contains(nhanvien.getpassword())) {
            	   Stage stage =(Stage)((Node) event.getSource()).getScene().getWindow();
            	   FXMLLoader loader = new FXMLLoader();
            	   loader.setLocation(getClass().getResource("danhsachnhanvien.fxml"));
            	   Parent sampleparent =loader.load();
            	   Scene scene = new Scene(sampleparent);
            	   stage.setScene(scene);    
		}
           break;    
        }
        List<Nhanvien> nhanvien2 = session.createQuery(hql2).setParameter("username", username).setParameter("password",password).list();
        for(Nhanvien b  : nhanvien2) {
       	 
            if(b.getusername().contains(nhanvien.getusername()) && b.getpassword().contains(nhanvien.getpassword())) {
         	   Stage stage =(Stage)((Node) event.getSource()).getScene().getWindow();
         	   FXMLLoader loader = new FXMLLoader();
         	   loader.setLocation(getClass().getResource("welcome.fxml"));
         	   Parent sampleparent =loader.load();
         	   Scene scene = new Scene(sampleparent);
         	   stage.setScene(scene);    
		}
            
     }
        thongbao.setText(" đăng nhập thất bại !!  ");
	}
	catch(HibernateException e) {
		e.printStackTrace();
	}		
   }
   public void initialize(URL url, ResourceBundle rb) {
	   
	      
   }
 
   
}

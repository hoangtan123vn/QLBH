package QLBH;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import org.hibernate.query.Query;
import org.hibernate.type.descriptor.sql.NVarcharTypeDescriptor;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.Window;

public class GiaoDienQLController implements Initializable{

    @FXML
    private Button banhang;

    @FXML
    private Button nhanvien;

    @FXML
    private Button thongke;

    @FXML
    private Button kho;

    @FXML
    private Button kiemtrahang;

    @FXML
    private Button danhmuc;

    @FXML
    private Button nhacungcap;

    @FXML
    private Button khachhang;
    
    @FXML
    private AnchorPane mainpane;
    
    @FXML
    private Label username;
    
    
    
    @FXML
    private ImageView imgnhanvien;

        
    void setNhanvien(Nhanvien nhanvien) {
    	
    }
    
    
    @FXML
    void ChangeKhachHang(ActionEvent event) {

    }

    @FXML
    void ChangeNhacungcap(ActionEvent event) {

    }

    @FXML
    void ChangeNhanvien(ActionEvent event) throws IOException {
 //   	Scene scene = new Scene(login); 
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("/Nhanvien/nhanvien.fxml"));
    	mainpane.getChildren().setAll(pane);
    	if(mainpane.getChildren().setAll(pane)) {
    		//nhanvien.setClickable(false);
    	}
    }
    
   public void LoadData(Taikhoannv taikhoan) {
    	
		username.setText("Xin chào " +taikhoan.getusername());
		username.setUnderline(true);
		//tennhanvien.setText("Xin chao" + taikhoan.getNhanvien().getHovaten());
		byte[] getImageInBytes = taikhoan.getNhanvien().getImage();
		try {
			FileOutputStream fos = new FileOutputStream(new File("photo1.jpg"));
			fos.write(getImageInBytes);
			fos.close();
			Image image = new Image("file:photo1.jpg", imgnhanvien.getFitHeight(), imgnhanvien.getFitHeight(), true, true);
			imgnhanvien.setImage(image);
		} catch (Exception e) {
			e.printStackTrace();
		}
		username.setOnMouseClicked(event ->  {
			
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("profilenhanvien.fxml"));
	            Parent tmp;
				tmp = loader.load();
				Scene scene = new Scene(tmp);
				Stage stage = new Stage();
			//	Stage stage =(Stage)((Node) event.getSource()).getScene().getWindow();
	            ProfilesNhanvienController profiles = loader.getController();
	            profiles.loadData(taikhoan);
	            stage.hide();
            	stage.setScene(scene);
            	stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            
		});
   }
    /*    user = data;
        if (user.getAvatar() == null) {
            avatar.setImage(new javafx.scene.image.Image("/img/avatar.png"));*/
      /*  } else {
            File file = new File("src/main/resources/img/" + M_Image.getImageById(user.getAvatar()).getHashname() + ".png");
            avatar.setImage(new Image(file.toURI().toString()));
        }
        listView(conferences);
	}*/

    @FXML
    void ChangeThongKe(ActionEvent event) {

    }

    @FXML
    void changeBanHang(ActionEvent event) {

    }

    @FXML
    void changeDanhmuc(ActionEvent event) {

    }

    @FXML
    void changeKTH(ActionEvent event) {

    }

    @FXML
    void changekho(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}

}



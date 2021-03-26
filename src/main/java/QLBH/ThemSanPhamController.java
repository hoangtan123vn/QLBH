package QLBH;

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
public class ThemSanPhamController extends Application implements Initializable{
	private FileChooser filechooser;
	private Image image;
	private File file;
	
    @FXML
    private TextField tensanpham;

    @FXML
    private TextField soluong;

    @FXML
    private TextField giatien;


    @FXML
    private ImageView imageSP;

    @FXML
    private Button themanhsp;

    @FXML
    private Button luusp;

    @FXML
    private Button huySP;
    
    @FXML
    private AnchorPane ap;
    
    @FXML
    private ComboBox<String> loaisanpham;
    

    @FXML
    private ComboBox<String> donvi;


    @FXML
    void LuuSP(ActionEvent event) throws IOException {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Them san pham");
    	
    	String tensp = tensanpham.getText();
    	int slsp = Integer.parseInt(soluong.getText());
    	int giatiensp = Integer.parseInt(giatien.getText());
    	String donvisp = donvi.getValue();
    	String loaisp = loaisanpham.getValue();
    	StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
				.configure("hibernate.cfg.xml")
				.build();
		Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
		Session session = sessionFactory.openSession();
		FileInputStream fis = new FileInputStream(file);
		 byte[] bFile = new byte[(int) (file.length())];
		 fis.read(bFile);
		 Sanpham sp= new Sanpham(tensp,loaisp,donvisp,giatiensp,slsp,bFile);
		 try {
    		 session.beginTransaction();
    		 session.save(sp);
    		 session.getTransaction().commit();
    		 alert.setContentText("Thêm sản phẩm thành công !");
    		 alert.showAndWait();   
    		 Stage stage = (Stage) luusp.getScene().getWindow();
        	 stage.close();
        	 chucnangquanly.getInstance().ReloadSANPHAM();
		 }
	    	catch (RuntimeException error){
	    		
	    		 alert.setContentText("Thêm sản phẩm thất bại   !");
	    		 alert.showAndWait();
	    		session.getTransaction().rollback();
	    	}
		 
    }

    @FXML
    void ThemAnhSP(ActionEvent event) {
    	Stage stage = (Stage) ap.getScene().getWindow();
   	 FileChooser fileChooser = new FileChooser();
   	 FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
   	 fileChooser.getExtensionFilters().add(imageFilter);
   	 file = fileChooser.showOpenDialog(stage);
   	 if(file!=null) {
   		image = new Image(file.toURI().toString(),100,150,false,true);
   		imageSP.setImage(image);
   	}
    }

    @FXML
    void huySP(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ObservableList<String> list=FXCollections.observableArrayList("Cái ","Chai ","Bình ","Bịch","Hộp","Ly","Nắm","123");
		ObservableList<String> list1=FXCollections.observableArrayList("nước giải khát","snack","thức ăn nhanh");
		loaisanpham.setItems(list1);
		donvi.setItems(list);
		
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
		
	}

}
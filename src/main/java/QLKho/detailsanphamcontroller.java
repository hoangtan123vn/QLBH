package QLKho;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.persistence.criteria.CriteriaQuery;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.*;

import org.apache.derby.vti.IFastPath;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.descriptor.sql.NVarcharTypeDescriptor;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;

import QLBH.HibernateUtils;
import QLBH.Sanpham;
import QLBH.chucnangquanly;

public class detailsanphamcontroller implements Initializable{
	private FileChooser filechooser;
	private Image image;
	private Blob image1;
	private File file;

    @FXML
    private AnchorPane ap;

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
    private TextField idsp;

    @FXML
    private ComboBox<String> donvi;

    @FXML
    private ComboBox<String> loaisanpham;
    
    
    @FXML 
    private Button reset;
    
    
   //private 
    
    
   
    
    
    public void setSanPham(Sanpham sanpham) {
    	idsp.setText(String.valueOf(sanpham.getMasanpham()));
    	tensanpham.setText(sanpham.getTensanpham());
    	donvi.setValue(sanpham.getDonvi());
    	soluong.setText(String.valueOf(sanpham.getDonvitinh()));
    	giatien.setText(String.valueOf(sanpham.getGiatien()));
    	loaisanpham.setValue(sanpham.getLoaisanpham());
    	imageSP.setImage(getImageFromBytes(sanpham.getImagesp()));
    	
    }
    
    private Image getImageFromBytes(byte[] imgBytes) {
	    try {
	        ByteArrayInputStream inputStream = new ByteArrayInputStream(imgBytes);
	        BufferedImage bufferedImage = ImageIO.read(inputStream);
	        return SwingFXUtils.toFXImage(bufferedImage, null);
	    } catch (IOException e) {
	        e.printStackTrace();
	    } 
	    return null;
	}
    
    @FXML
    void resetSP(ActionEvent event) {
    	
				
    }

    @FXML
    void LuuSP(ActionEvent event) throws IOException{
    	//Sanpham sp = new Sanpham();
		//capnhatsp(setSanPham(sanpham));
    	Alert alert = new Alert(AlertType.INFORMATION);
    	int IDSanPham = Integer.parseInt(idsp.getText());
    	String capnhattensp = tensanpham.getText();
		String capnhatdvsp = donvi.getValue();
		int capnhatslsp = Integer.parseInt(soluong.getText());
		int capnhatgtsp = Integer.parseInt(giatien.getText());
		String capnhatlsp = loaisanpham.getValue();	
		 Session session = HibernateUtils.getSessionFactory().openSession();
				byte[] bFile = null;
				//anh
				
				/*	FileInputStream fis = new FileInputStream(file);
					byte[] bFile = new byte[(int) (file.length())];
					fis.read(bFile);*/
				
				if(bFile == null) {
					BufferedImage bImage = SwingFXUtils.fromFXImage(imageSP.getImage(), null);
					ByteArrayOutputStream s = new ByteArrayOutputStream();
					ImageIO.write(bImage, "png", s);
					 bFile  = s.toByteArray();
				}
				 Sanpham sp = new Sanpham(capnhattensp,capnhatlsp,capnhatdvsp,capnhatgtsp,capnhatslsp,bFile);
				// Sanpham sp = new Sanpham(capnhattensp,capnhatlsp,capnhatdvsp,capnhatgtsp,capnhatslsp);
				sp = session.get(Sanpham.class, IDSanPham);
				try {
					session.beginTransaction();

					if (sp != null ) {
						sp.setTensanpham(capnhattensp);
						sp.setLoaisanpham(capnhatlsp);
						sp.setDonvi(capnhatdvsp);
						sp.setGiatien(capnhatgtsp);
						sp.setDonvitinh(capnhatslsp);
						sp.setImagesp(bFile);
						session.save(sp);
						Stage stage = (Stage) ap.getScene().getWindow();
			        	 stage.close();
					}
					session.getTransaction().commit();
					QLKhoController.getInstance().initialize1();
					alert.setContentText("Cập nhật sản phẩm thành công");
					alert.showAndWait();
				} catch (RuntimeException error) {
					session.getTransaction().rollback();
					alert.setContentText("Cập nhật sản phẩm thất bại");

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
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> list=FXCollections.observableArrayList("Cái ","Chai ","Bình ","Bịch","Hộp","Ly","Nắm","123");
		ObservableList<String> list1=FXCollections.observableArrayList("nước giải khát","snack","thức ăn nhanh");
		loaisanpham.setItems(list1);
		donvi.setItems(list);
		
	}

}
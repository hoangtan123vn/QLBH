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
import javafx.scene.control.TableCell;
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
import javafx.util.Callback;
import javafx.util.StringConverter;
import net.bytebuddy.implementation.bytecode.Addition;
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

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;
import QLBH.Nhanvien;
public class KiemtrahangController implements Initializable{
	 @FXML
	    private TableView tableChitietKiemtra;

	    @FXML
	    private Label madathangkt;

	    @FXML
	    private Label mancckt;

	    @FXML
	    private Label thoigiandatkt;
	   

	    @FXML
	    private TableColumn<Chitietdathang,Sanpham> tenhang;

	    @FXML
	    private TableColumn soluong;

	    @FXML
	    private TableColumn dongia;

	    @FXML
	    private TableColumn choice;

	
	    public ObservableList<Chitietdathang> getChitietdathang(Phieudathang phieudathang) {
	    	String Phieudathang = phieudathang.getMadathang();
	    	ObservableList<Chitietdathang> tableChitietkiemtra = FXCollections.observableArrayList();
	    	 StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
						.configure("hibernate.cfg.xml")
						.build();
				Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
				SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
				Session session = sessionFactory.openSession();
				
				CriteriaBuilder builder = session.getCriteriaBuilder();
				//SELECT C.soluong,SP.tensanpham,SP.giatien FROM chitietdathang C,sanpham SP WHERE C.masanpham = SP.masanpham
				CriteriaQuery<Chitietdathang> query = builder.createQuery(Chitietdathang.class);
				Root<Chitietdathang> root = query.from(Chitietdathang.class); // FROM
				Join<Chitietdathang, Sanpham> SanphamJoin = root.join("sanpham", JoinType.INNER);
				//query.where(builder.equal(SanphamJoin.get("masanpham"), masanpham));
				List<Chitietdathang> ctdh = session.createQuery(query).getResultList();
			 for(Chitietdathang b : ctdh) {
				 tableChitietkiemtra.add(b);
				 System.out.println(b);
			 }
			 return tableChitietkiemtra;
			
			 
	    }
	public void setKiemtrahang (Phieudathang phieudathang) {
		madathangkt.setText(String.valueOf(phieudathang.getMadathang()));
		thoigiandatkt.setText(String.valueOf(phieudathang.getThoigiandat()));
		mancckt.setText(String.valueOf(phieudathang.getNhacungcap().toString()));
		IntitlizeChitietdathang(phieudathang);
	}
	 public void IntitlizeChitietdathang(Phieudathang phieudathang) {
	    	tenhang.setCellFactory(tableChitietKiemtra ->new TableCell<Chitietdathang, Sanpham>(){
	    		@Override
	 		    protected void updateItem(Sanpham item, boolean empty) {
	 		        super.updateItem(item, empty);
	 		        if (empty || item == null) {
	 		            setText(null);
	 		        } else {
	 		            setText(item.getTensanpham());
	 		        }
	 		    }
	    	});
	    	tenhang.setCellValueFactory(new PropertyValueFactory<>("sanpham"));
	    	soluong.setCellValueFactory(new PropertyValueFactory<Chitietdathang,Integer>("soluong"));
	    	dongia.setCellValueFactory(new PropertyValueFactory<>("sanpham"));
	    	dongia.setCellFactory(tbChitietDatHang ->new TableCell<Chitietdathang, Sanpham>(){
	    		@Override
	 		    protected void updateItem(Sanpham item, boolean empty) {
	 		        super.updateItem(item, empty);
	 		        if (empty || item == null) {
	 		            setText(null);
	 		        } else {
	 		            setText(String.valueOf(item.getGiatien()));
	 		        }
	 		    }
	    	});
	    	
	    	tableChitietKiemtra.setItems(getChitietdathang(phieudathang));
	    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}

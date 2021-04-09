package DanhMuc;

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
import QLBH.Chitiethoadon;
import QLBH.Chitietnhaphang;
import QLBH.HibernateUtils;
import QLBH.Hoadon;
import QLBH.Phieunhaphang;
import QLBH.Sanpham;
public class NhaphangDetailController implements Initializable {

	 @FXML
	    private Label lbManhaphang;

	    @FXML
	    private Label lbThoigiannhap;

	    @FXML
	    private Label lbTongtien;

	    @FXML
	    private Label lbMancc;

	    @FXML
	    private Label lbManv;

	    @FXML
	    private Button backNH;

	    @FXML
	    private TableView<Chitietnhaphang> tbChitietNhapHang;

	   

	    @FXML
	    private TableColumn soluong;

	    @FXML
	    private TableColumn dongia;

	    @FXML
	    private TableColumn tongtien;
	    @FXML
	    private TableColumn<Chitietnhaphang, Sanpham> tenhang;


    @FXML
    void backNH(ActionEvent event) throws IOException {
    	Stage stage = (Stage) backNH.getScene().getWindow();
        stage.close();
    }
    
    public void setPhieunhaphang(Phieunhaphang phieunhaphang) {
    	lbManhaphang.setText(String.valueOf(phieunhaphang.getManhaphang()));
    	lbThoigiannhap.setText(String.valueOf(phieunhaphang.getThoigian()));
    	lbMancc.setText((phieunhaphang.getNhacungcap().toString()));
    	lbManv.setText((phieunhaphang.getNhanvien().toString()));
  //  	IntitlizeChitietnhaphang(phieunhaphang);
    	IntitlizeChitietnhaphang(phieunhaphang);
    }
    
   public ObservableList<Chitietnhaphang> getChitietnhaphang(Phieunhaphang phieunhaphang) {
    	String Phieunhaphang = String.valueOf(phieunhaphang.getManhaphang());
    	ObservableList<Chitietnhaphang> tablePhieuNhapHang = FXCollections.observableArrayList();
    	 Session session = HibernateUtils.getSessionFactory().openSession();
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Chitietnhaphang> query = builder.createQuery(Chitietnhaphang.class);
			Root<Chitietnhaphang> root = query.from(Chitietnhaphang.class); // FROM
			Join<Chitietnhaphang, Sanpham> SanphamJoin = root.join("sanpham", JoinType.INNER);
			Join<Chitietnhaphang, Phieunhaphang> NhapHangJoin = root.join("phieunhaphang",JoinType.INNER);
			query.where(builder.equal(NhapHangJoin.get("manhaphang"), Phieunhaphang));
			List<Chitietnhaphang> ctnh = session.createQuery(query).getResultList();
    	
		 for(Chitietnhaphang b : ctnh) {
			 tablePhieuNhapHang.add(b);
			 System.out.println(b);
		 }
		 return tablePhieuNhapHang;	 
    }
   
   public void  IntitlizeChitietnhaphang(Phieunhaphang phieunhaphang ) {
	tenhang.setCellFactory(tbChitietNhapHang-> new TableCell<Chitietnhaphang, Sanpham>(){
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
	soluong.setCellValueFactory(new PropertyValueFactory<Chitietnhaphang,Integer>("soluong"));
	tenhang.setCellValueFactory(new PropertyValueFactory<>("sanpham"));
	dongia.setCellValueFactory(new PropertyValueFactory<>("sanpham"));
	dongia.setCellFactory(tbChitietNhapHang ->new TableCell<Chitietnhaphang, Sanpham>(){
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
	tbChitietNhapHang.setItems(getChitietnhaphang(phieunhaphang));

}
   

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}


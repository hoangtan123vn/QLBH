package QLBH;

import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.*;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;


import java.util.*;
import java.util.Date;
import java.util.List;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javax.persistence.criteria.CriteriaQuery;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;

import javafx.collections.*;

import javafx.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import javafx.scene.control.*;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;
import QLBH.Nhanvien;
import QLBH.Hoadon;
import QLBH.Phieudathang;

public class DanhMucHoaDonController implements Initializable {
	
	
	/*
	 * 
	 * ----------------------FXML Danh SÃ¡ch NhÃ¢n ViÃªn------------------------------
	 * 
	 */
	
	
	@FXML
	private Button idaddNV;

	@FXML
	private TableView<Nhanvien> tableNV;

	@FXML
	private TableColumn id;

	@FXML
	private TableColumn hovaten;

	@FXML
	private TableColumn ngaysinh;

	@FXML
	private TableColumn chucvu;

	@FXML
	private TableColumn sdt;

	@FXML
	private TableColumn cmnd;

	@FXML
	private void ThemNV(ActionEvent event) {
		
	}
	
	
	

	/*
	 * -----------------------------------------------------------------------------
	 */
	
	
	 @FXML
	    private Label lbTitleStore;
	 
	@FXML
    private Tab tabDanhMuc;
	
	@FXML
    private ImageView logo;
	
    @FXML
    private ImageView avata;

    @FXML
    private TabPane tabPaneQLDM;
    
    @FXML
    private TabPane tabPaneid;
	
	/*
	 * 
	 * --------------------------FXML Danh Má»¥c Phiáº¿u HÃ³a Ä�Æ¡n------------------------- 
	 * 
	 */
	
    @FXML
    private Tab sTab_PhieuHoaDon;


    @FXML
    private TableColumn mahoadon;


    @FXML
    private TableColumn    thoigianmua;

    @FXML
    private TableColumn  tonggia;

    @FXML
    private TableColumn  makh;

    @FXML
    private TableColumn  manv;

    @FXML
    private TextField searchPHD;

  

    @FXML
    private Label lbDanhMucPHD;
 
    @FXML
    private ScrollBar verticalPHD;
    
    
    @FXML
    private TableView <Hoadon> tableHoaDon;
    
     ObservableList<Hoadon> list;
    
    
       
    
 
	void searchPHD() {   
    	ObservableList<Hoadon> tbHoaDon = FXCollections.observableArrayList(getHoadon());
    	
        FilteredList<Hoadon> filteredData = new FilteredList<>(tbHoaDon, b -> true);  
        searchPHD.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(hoadon -> {
           if (newValue == null || newValue.isEmpty()) {
            return true;
           }    
           String lowerCaseFilter = newValue.toLowerCase();
           
           if (hoadon.getMahoadon().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
            return true; // Filter matches username
           } 
           else if (hoadon.getThoigianmua().toLowerCase().indexOf(lowerCaseFilter) != -1) {
            return true; // Filter matches password
           }               
                else  
                 return false; // Does not match.
          });
         });  
        
     
		SortedList<Hoadon> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tableHoaDon.comparatorProperty());
		tableHoaDon.setItems(sortedData);  
    	        
    	    }    
   
	
    /*
     * 
     *--------------------- FXML Danh má»¥c phiáº¿u Ä‘áº·t hÃ ng-----------------------------
     * 
     */
    
    
    
    @FXML
    private Tab sTab_PhieuDatHang;

    @FXML
    private Label lbDanhMucPDH;

    @FXML
    private TextField searchPDH;

    @FXML
    private Button btnSearchPDH;

    @FXML
    private TableView<Phieudathang> tablePhieuDatHang;

    @FXML
    private TableColumn madathang;

    @FXML
    private TableColumn mancc;
    
    @FXML
    private TableColumn thoigiandat;

    @FXML
    private TableColumn tongtien;

    @FXML
    private ScrollBar verticalPDH;
    
    @FXML
    void searchPDH(ActionEvent event) {

    }
    
    /*
     * 
     *--------------------- FXML Danh má»¥c phiáº¿u nháº­p hÃ ng-----------------------------
     * 
     */
    @FXML
    private Tab sTab_PhieuNhapHang;

    @FXML
    private Label lbDanhMucPNH;

    @FXML
    private TextField searchPNH;

    @FXML
    private Button btnSearchPNH;

    @FXML
    private TableView<Phieunhaphang> tablePhieuNhapHang;

    @FXML
    private TableColumn manhaphang;
   
    @FXML
    private TableColumn thoigiannhap;

    @FXML
    private ScrollBar verticalPNH;
    
    @FXML
    void searchPNH(ActionEvent event) {

    }
    
    
    /*
     * 
     *--------------------- FXML Danh má»¥c phiáº¿u tráº£ hÃ ng-----------------------------
     * 
     */
  
    @FXML
    private Tab sTab_PhieuTraHang;

    @FXML
    private Button btnSearchPTH;

    @FXML
    private TableView<Phieutrahang> tablePhieuTraHang;

    @FXML
    private TableColumn maphieutra;

    @FXML
    private TableColumn lido;
    
    @FXML
    private TableColumn thoigiantra;

    @FXML
    private TextField searchPTH;

    @FXML
    private Label lbDanhMucPTH;

    @FXML
    private ScrollBar verticalPTH;

    @FXML
    void searchPTH(ActionEvent event) {

    }
    
 

/*
 * ---------------------------Láº¥y dá»¯ liá»‡u tá»« CSDL----------------------------------
 */
    
    
	public void initialize(URL url, ResourceBundle rb) {
		
//------------------------------------NhÃ¢n viÃªn------------------------------------
		// id.setCellValueFactory(new PropertyValueFactory<Nhanvien, Integer>("id"));
		hovaten.setCellValueFactory(new PropertyValueFactory<Nhanvien, String>("hovaten"));
		ngaysinh.setCellValueFactory(new PropertyValueFactory<Nhanvien, Integer>("ngaysinh"));
		chucvu.setCellValueFactory(new PropertyValueFactory<Nhanvien, String>("chucvu"));
		sdt.setCellValueFactory(new PropertyValueFactory<Nhanvien, Integer>("sdt"));
		cmnd.setCellValueFactory(new PropertyValueFactory<Nhanvien, Integer>("cmnd"));
		tableNV.setItems(getNhanvien());
								
//--------------------------------------HÃ³a Ä‘Æ¡n-----------------------------------							

		mahoadon.setCellValueFactory(new PropertyValueFactory<Hoadon, String>("mahoadon"));
		thoigianmua.setCellValueFactory(new PropertyValueFactory<Hoadon, String>("thoigianmua"));
		tonggia.setCellValueFactory(new PropertyValueFactory<Hoadon, Integer>("tonggia"));
		makh.setCellValueFactory(new PropertyValueFactory<Hoadon, Integer>("makh"));
		manv.setCellValueFactory(new PropertyValueFactory<Hoadon, Integer>("manv"));
		tableHoaDon.setItems(getHoadon());
	 	searchPHD();	
		
		
	
//---------------------------------------Phiáº¿u Ä‘áº·t hÃ ng------------------------------	

		madathang.setCellValueFactory(new PropertyValueFactory<Phieudathang, Integer>("madathang"));
		thoigiandat.setCellValueFactory(new PropertyValueFactory<Phieudathang, Integer>("thoigiandat"));
		tongtien.setCellValueFactory(new PropertyValueFactory<Phieudathang, Integer>("tongtien"));
		mancc.setCellValueFactory(new PropertyValueFactory<Phieudathang, Integer>("mancc"));
		manv.setCellValueFactory(new PropertyValueFactory<Phieudathang, Integer>("manv"));
		tablePhieuDatHang.setItems(getPhieudathang());
	

	



		



		/** id.setCellValueFactory(new PropertyValueFactory<Nhanvien, String>("idNV"));
		 * hovaten.setCellValueFactory(new PropertyValueFactory<Nhanvien,
		 * String>("HÃ¡Â»ï¿½ vÃƒÂ  tÃƒÂªn")); ngaysinh.setCellValueFactory(new
		 * PropertyValueFactvory<Nhanvien, Integer>("NgÃƒÂ y sinh"));
		 * chucvu.setCellValueFactory(new PropertyValueFactory<Nhanvien,
		 * String>("ChÃ¡Â»Â©c vÃ¡Â»Â¥")); sdt.setCellValueFactory(new
		 * PropertyValueFactory<Nhanvien, Integer>("SÃ¡Â»â€˜ Ã„â€˜iÃ¡Â»â€¡n thoÃ¡ÂºÂ¡i"));
		 * cmnd.setCellValueFactory(new PropertyValueFactory<Nhanvien,
		 * Integer>("CMND")); tableNV.setItems(getNhanvien());
		 */

}


/*
 * -------------------------------Ä�á»• dá»¯ liá»‡u lÃªn table view-------------------------- 
 */
	
									 /* 
									  * Danh sÃ¡ch nhÃ¢n viÃªn 
									 */
	public ObservableList<Nhanvien> getNhanvien() {
		ObservableList<Nhanvien> TableNV = FXCollections.observableArrayList();
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
				.build();
		Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
		Session session = sessionFactory.openSession();

		CriteriaQuery<Nhanvien> nv = session.getCriteriaBuilder().createQuery(Nhanvien.class);
		nv.from(Nhanvien.class);
		List<Nhanvien> eList = session.createQuery(nv).getResultList();
		// List<Nhanvien> eList = session.createQuery(criteriaQuery).getResultList();
		// List<Nhanvien> eList = session.createQuery(Nhanvien.class).list();
		for (Nhanvien ent : eList) {
			TableNV.add(ent);
		}
		return TableNV;
	}
	
	public ObservableList<Phieudathang> getPhieudathang() {
		ObservableList<Phieudathang> tablePhieuDatHang = FXCollections.observableArrayList();
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
				.build();
		Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
		Session session = sessionFactory.openSession();
		CriteriaQuery<Phieudathang> pdh = session.getCriteriaBuilder().createQuery(Phieudathang.class);
		pdh.from(Phieudathang.class);
		List<Phieudathang> eList = session.createQuery(pdh).getResultList();
		for (Phieudathang ent : eList) {
			tablePhieuDatHang.add(ent);
		}
		return tablePhieuDatHang;
	}
	
							/*
									 * Danh má»¥c phiáº¿u hÃ³a Ä‘Æ¡n
									 */
	public ObservableList <Hoadon> getHoadon() {
		ObservableList <Hoadon> tableHoadon = FXCollections.observableArrayList();
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
				.build();
		Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
		Session session = sessionFactory.openSession();

		CriteriaQuery <Hoadon> hd= session.getCriteriaBuilder().createQuery(Hoadon.class);
		hd.from(Hoadon.class);
		List<Hoadon> eList = session.createQuery(hd).getResultList();
		// List<Nhanvien> eList = session.createQuery(criteriaQuery).getResultList();
		// List<Nhanvien> eList = session.createQuery(Nhanvien.class).list();
		for (Hoadon ent : eList) {
			tableHoadon.add(ent);
		}
		return tableHoadon;		
	}
	
	

}

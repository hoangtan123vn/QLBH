package QLBH;

import java.net.URL;
import org.hibernate.query.Query;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import QLBH.Sanpham;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.control.cell.PropertyValueFactory;


import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class chucnangnhanvienController implements Initializable{
	
	
	 @FXML
	private TextField tongtien;

	@FXML
	private TextField khachtra;

	@FXML
	private TextField tienthua;
    @FXML
    private TableView<Sanpham> TableSP;

    @FXML
    private TableColumn donvi;

    @FXML
    private TableView <Sanpham> hoadon;

    @FXML
    private TableColumn giatien;

    @FXML
    private TableColumn masanpham1;

    @FXML
    private TableColumn loaisanpham;

    @FXML
    private TableColumn loaisanpham1;

    @FXML
    private TableColumn donvi1;

    @FXML
    private TableColumn giatien1;

    @FXML
    private TableColumn donvitnh;

    @FXML
    private TableColumn masanpham;

    @FXML
    private TableColumn tensanpham;

    @FXML
    private TableColumn tensanpham1;

    @FXML
    private TableColumn donvitnh1;
    
    ObservableList<Sanpham> danhmuchoadon = FXCollections.observableArrayList();
    
	 private void setCellValueFromTabletoTexfFieldd()  {
		 TableSP.setOnMouseClicked(event -> {
			 //
			 event();
			// Sanpham sp = TableSP.getItems().get(TableSP.getSelectionModel().getSelectedIndex());
			//tongtien.setText(Integer.toString(sp.getGiatien()));
		
			
		 });
		 hoadon.setOnMouseClicked(event -> {
			 //
			
			 Sanpham sp = hoadon.getItems().get(hoadon.getSelectionModel().getSelectedIndex());
			tongtien.setText(Integer.toString(sp.getGiatien()));
		
			
		 });
	 }

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		setCellValueFromTabletoTexfFieldd();
		masanpham.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("masanpham"));
		tensanpham.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("tensanpham"));
		loaisanpham.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("loaisanpham"));
		donvitnh.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("donvitinh"));
		giatien.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("giatien"));
		donvi.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("donvi"));
		TableSP.setItems(getSanpham());
		
	// 
		masanpham1.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("masanpham"));
		tensanpham1.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("tensanpham"));
		loaisanpham1.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("loaisanpham"));
		donvitnh1.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("donvitinh"));
		giatien1.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("giatien"));
		donvi1.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("donvi"));
		
		//hoadon.setItems(getHoadon());		
		
	}
	public ObservableList<Sanpham> getSanpham() {
		ObservableList<Sanpham> TableSP = FXCollections.observableArrayList();
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
				.build();
		Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
		Session session = sessionFactory.openSession();

		CriteriaQuery<Sanpham> sp = session.getCriteriaBuilder().createQuery(Sanpham.class);
		sp.from(Sanpham.class);
		List<Sanpham> eList = session.createQuery(sp).getResultList();
		for (Sanpham ent : eList) {
			TableSP.add(ent);
		}
		return TableSP;
	}
	//

	//
	private void event() {
		 Sanpham sp = TableSP.getItems().get(TableSP.getSelectionModel().getSelectedIndex());
		 //hoadon.setItems(getHoadon());
		 //	TableSP.getSelectionModel().getSelectedItems();
		 hoadon.getItems().add(sp);
	//hoadon.setItems(getHoadon());
	
}
}

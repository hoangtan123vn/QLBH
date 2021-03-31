package QLBH;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.function.Predicate;


import javax.persistence.criteria.CriteriaQuery;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;
import QLBH.Sanpham;

public class LapPhieuDatHangController implements Initializable{
	
	@FXML
	private Button huy;
	
	@FXML
	private TableColumn donvitinh;

	@FXML
	private TableColumn donvi;

	@FXML
	private TableColumn giatien;
	
	@FXML
	private TableColumn tensanpham;

	@FXML	
	private TableColumn masanpham;
	
	@FXML	
	private TableColumn loaisanpham;
	
	@FXML
	private TableColumn donvitinh1;

	@FXML
	private TableColumn donvi1;

	@FXML
	private TableColumn giatien1;
	
	@FXML
	private TableColumn tensanpham1;

	@FXML	
	private TableColumn masanpham1;
	
	@FXML	
	private TableColumn loaisanpham1;
	
	@FXML
	private TextField tf1;

	@FXML
	private TextField tf2;

	@FXML
	private TextField tf3;

	@FXML
	private TextField tf4;

	@FXML
	private TextField tf5;
	
	@FXML
	private TextField tf6;
	
	@FXML
	private TextField Timkiem;
	
	 @FXML
    private ComboBox<String> cbnhacungcap;
	 
	 @FXML
	 private Button idDatHang;
	
	 
	
	ObservableList<Sanpham> table = FXCollections.observableArrayList(getSanpham());

	@FXML
	private TableView<Sanpham> tableSP;
	
	@FXML
	private TableView<Sanpham> tableSP1;
	
	@FXML
	void CanCel(ActionEvent event) {
		Stage stage = (Stage) huy.getScene().getWindow();
   	 stage.close();
	}
	void Timkiem() {
		ObservableList<Sanpham> TableSP = FXCollections.observableArrayList(getSanpham());

		FilteredList<Sanpham> filteredData = new FilteredList<>(TableSP, b -> true);
		Timkiem.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(sanpham -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (sanpham.getTensanpham().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches username
				} else if (sanpham.getDonvi().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches password
				} else
					return false; // Does not match.
			});
		});
		SortedList<Sanpham> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tableSP.comparatorProperty());
		tableSP.setItems(sortedData);

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
		// List<Nhanvien> eList = session.createQuery(criteriaQuery).getResultList();
		// List<Nhanvien> eList = session.createQuery(Nhanvien.class).list();
		for (Sanpham ent : eList) {
			TableSP.add(ent);
		}
		return TableSP;
	}

	void ReloadSANPHAM() {
		getSanpham();

	}
	 private void setCellValueFromTabletoTexfFieldd()  {
		 tableSP.setOnMouseClicked(event -> {
			 //
			 Sanpham sp = tableSP.getItems().get(tableSP.getSelectionModel().getSelectedIndex());
			 tf1.setText(sp.getTensanpham());
			 tf2.setText(Integer.toString(sp.getMasanpham()));
			 tf3.setText(sp.getDonvi());
			 //tf4.setText(Integer.toString(sp.getGiatien()));
			 //tf5.setText(sp.getDonvitinh()); 
			 tf6.setText(sp.getLoaisanpham());
		 });
	 }
	 public ObservableList<String> getNhaCungCap() {
		 ObservableList<String> tbNCC = FXCollections.observableArrayList();
		 StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
					.configure("hibernate.cfg.xml")
					.build();
		 Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
			SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
			Session session = sessionFactory.openSession();
		 session.getTransaction().begin();
		 String hql = "SELECT P.tenncc FROM Nhacungcap P";
		 Query query = session.createQuery(hql);
		 ArrayList<String> listnhcc = (ArrayList) query.list();
		 for(String t1 : listnhcc) {
			 System.out.println(t1);
			 tbNCC.add(t1);
		 }
		 return tbNCC;				 
	 }	
	 
	 
	 @FXML
	    void DatHang(ActionEvent event) {
		// String nhacungcap = cbnhacungcap.getValue();
		// Sanpham sp = tableSP.getItems().get(tableSP.getSelectionModel().getSelectedIndex());
		 String tensanpham1 = tf1.getText();
		 int masanpham1 = Integer.parseInt(tf2.getText());
		 String donvi1 = tf3.getText();
		 String loaisanpham1 = tf6.getText();
		 int donvitinh1 = Integer.parseInt(tf4.getText());
		 int giatien1 = Integer.parseInt(tf5.getText());
		 
	//	 addItem(masanpham1, tensanpham1, loaisanpham1, donvi1, giatien1, donvitinh1);
		 tableSP1.refresh();
		// Sanpham sp = new Sanpham(tensanpham1,masanpham1,donvi1,donvitinh1,giatien1,loaisanpham1);
		// tableSP1.getItems().add(sp);
	//	 System.out.println(sp);
		 
	    }
/*	 	public void addItem(int masanpham,String tensanpham, String loaisanpham, String donvi,int giatien,int donvitinh) {
		    Sanpham entry = tableSP1.getItems().stream()
		        .filter(item -> item.getTensanpham().equals(tensanpham))
		        .findAny()
		        .orElseGet(()-> {
		             Sanpham newItem = new Sanpham(masanpham,tensanpham, loaisanpham,donvi,0, 0);
		             tableSP1.getItems().add(newItem);
		             return newItem ;
		        });
		    
		    entry.setDonvitinh(entry.getDonvitinh() + donvitinh);
	 	    entry.setGiatien(entry.getGiatien() + giatien);
	 	   // TableSP.refresh();
		   // entry.s
		}*/
	 	
	 	
	 
	 
	 
	 
	 
	 
	 

			
			@Override
			public void initialize(URL url, ResourceBundle rb) {
				setCellValueFromTabletoTexfFieldd();
				tensanpham.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("tensanpham"));
				masanpham.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("masanpham"));
				//loaisanpham.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("loai"));
				donvi.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("donvi"));
				giatien.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("giatien"));
				donvitinh.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("donvitinh"));
			//	comboboxNCC();
				
				tensanpham1.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("tensanpham"));
				masanpham1.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("masanpham"));
				loaisanpham1.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("loaisanpham"));
				donvi1.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("donvi"));
				giatien1.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("giatien"));
				donvitinh1.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("donvitinh"));
				ObservableList<String> list = FXCollections.observableArrayList(getNhaCungCap());
				cbnhacungcap.setItems(list);
				tableSP.setItems(getSanpham());
				Timkiem();
				
			}
	
	}	


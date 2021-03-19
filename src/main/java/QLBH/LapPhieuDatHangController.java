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
	
	
	
	ObservableList<Sanpham> table = FXCollections.observableArrayList(getSanpham());

	@FXML
	private TableView<Sanpham> tableSP;
	
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

			
			@Override
			public void initialize(URL url, ResourceBundle rb) {
				setCellValueFromTabletoTexfFieldd();
				tensanpham.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("tensanpham"));
				masanpham.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("masanpham"));
				loaisanpham.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("loai"));
				donvi.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("donvi"));
				giatien.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("giatien"));
				donvitinh.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("donvitinh"));
				
				tableSP.setItems(getSanpham());
				Timkiem();
				
			}
	
	
	
	}	


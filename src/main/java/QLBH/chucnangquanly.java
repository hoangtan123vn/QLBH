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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

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
import QLBH.Nhanvien;
import QLBH.Hoadon;
import QLBH.Phieudathang;
import QLBH.Phieunhaphang;
import QLBH.Phieutrahang;

public class chucnangquanly extends Application implements Initializable {

	///////////////////////////// AUTHOR : LÊ HOÀNG
	///////////////////////////// TÂN/////////////////////////**************************
	////////////////////////////////// CHỨC NĂNG : QUẢN LÝ NHÂN VIÊN
	///////////////////////////// ///////////*************************
	///////////////////
	public static chucnangquanly instance;

    public chucnangquanly() {
        instance = this;
    }

    public static chucnangquanly getInstance() {
        return instance;
    }
	private Image image;
	@FXML
	private Button idaddNV;

	@FXML
	private AnchorPane anchorpane;

	@FXML
	private TableView<Nhanvien> tableNV;

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
	private TableColumn diachi;

	@FXML
	private Button idreload;

	@FXML
	private TextField hovaten_nv;

	 @FXML
    private DatePicker ns_nv;
	 
	  @FXML
	private DatePicker ngayvaolam;
	 
	 
	@FXML
	private TextField cv_nv;

	@FXML
	private TextField sdt_nv;

	@FXML
	private TextField cmnd_nv;

	@FXML
	private TextField diachi_nv;

	@FXML
	private Button xoa_nv;

	@FXML
	private Button capnhat_nv;

	@FXML
	private TextField id_nv;

	@FXML
	private ImageView imgnhanvien;

	@FXML
	private Button luucapnhat;

	@FXML
	private Button reset;

	@FXML
	private TextField search;

	@FXML
	private TextField gt_nv;

	@FXML
	private ComboBox<String> Listnhanvien;

	ObservableList<Nhanvien> table = FXCollections.observableArrayList(getNhanvien());

	@FXML
	void Reset(ActionEvent event) {
		Nhanvien nv = tableNV.getItems().get(tableNV.getSelectionModel().getSelectedIndex());
		id_nv.setText(Integer.toString(nv.getManv()));
		hovaten_nv.setText(nv.getHovaten());
	//	ns_nv.setText(Integer.toString(nv.getNgaysinh()));
		ns_nv.setValue(nv.getNgaysinh());
		cv_nv.setText(nv.getChucvu());
		gt_nv.setText(nv.getGioitinh());
		sdt_nv.setText(Integer.toString(nv.getSdt()));
		diachi_nv.setText(nv.getDiachi());
		cmnd_nv.setText(Integer.toString(nv.getCmnd()));
		ngayvaolam.setValue(nv.getNgayvaolam());
	}
	

	@FXML
	private void ThemNV(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("themnhanvien.fxml"));
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Them nhan vien");
		stage.show();
	}

	@FXML
	void ListNhanvien(ActionEvent event) throws IOException {
		if (Listnhanvien.getValue() == "Danh sách nhân viên") {
			Parent root = FXMLLoader.load(getClass().getResource("chucnangquanly.fxml"));

			// Scene scene = new Scene(root);
			// Stage stage = new Stage();
			// stage.setScene(scene);
			// stage.show();

		} else if (Listnhanvien.getValue() == "Lịch làm") {
			Parent calamnhanvien = FXMLLoader.load(getClass().getResource("calamnhanvien.fxml"));
			Scene calam_scene = new Scene(calamnhanvien);
			Stage calam_stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
			calam_stage.setScene(calam_scene);
			calam_stage.show();
		}
	}

	/*
	 * public ObservableList<Nhanvien> findAll() { initialize1(); getNhanvien(); //
	 * Wrap the ObservableList in a FilteredList (initially display all data).
	 * 
	 * 
	 * 
	 * }
	 */
		@FXML
	void luucapnhat(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Cap nhat thanh cong ");
		int idnv = (Integer.parseInt(id_nv.getText()));
		String hovatennv = hovaten_nv.getText();
		LocalDate ngaysinhnv = ns_nv.getValue();
		String chucvunv = cv_nv.getText();
		String gioitinhnv = gt_nv.getText();
		LocalDate nvl = ngayvaolam.getValue();
		int sdtnv = (Integer.parseInt(sdt_nv.getText()));
		int cmndnv = (Integer.parseInt(cmnd_nv.getText()));
		String diachinv = diachi_nv.getText();
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
				.build();
		Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Nhanvien nv2 = new Nhanvien(idnv,hovatennv,ngaysinhnv,chucvunv,gioitinhnv,sdtnv,cmndnv,diachinv, nvl);
			nv2 = session.get(Nhanvien.class, idnv);
			if (nv2 != null) {
				// nv2.setid(idnv);
				nv2.setHovaten(hovatennv);
				nv2.setChucvu(chucvunv);
				nv2.setGioitinh(gioitinhnv);
				nv2.setDiachi(diachinv);
				nv2.setNgaysinh(ngaysinhnv);
				nv2.setSdt(sdtnv);
				nv2.setCmnd(cmndnv);
				// person2.setAge(t2);
				/// person2.setAddress(t3);
				session.save(nv2);
				
				
				alert.setContentText("Cap nhat nhan vien thanh cong !");
				alert.showAndWait();
				
				luucapnhat.setVisible(false);
				reset.setVisible(false);
				hovaten_nv.setEditable(false);
				ns_nv.setEditable(false);
				cv_nv.setEditable(false);
				gt_nv.setEditable(false);
				cmnd_nv.setEditable(false);
				sdt_nv.setEditable(false);
				diachi_nv.setEditable(false);
				ngayvaolam.setEditable(false);

			}
			session.getTransaction().commit();
			//tableNV.refresh();
			initializeNHANVIEN();
		} catch (RuntimeException error) {
			session.getTransaction().rollback();
		}

		
	}

	@FXML
	void search() {
		ObservableList<Nhanvien> table = FXCollections.observableArrayList(getNhanvien());
		FilteredList<Nhanvien> filterData = new FilteredList<>(table, p -> true);
		search.textProperty().addListener((observable, oldvalue, newvalue) -> {
			filterData.setPredicate(nv -> {
				if (newvalue == null || newvalue.isEmpty()) {
					return true;
				}
				String typetext = newvalue.toLowerCase();
				if (nv.getHovaten().toLowerCase().indexOf(typetext) != -1) {
					return true;
				}
				if (nv.getChucvu().toLowerCase().indexOf(typetext) != -1) {
					return true;
				}
				return false;

			});
			SortedList<Nhanvien> sortedList = new SortedList<>(filterData);
			sortedList.comparatorProperty().bind(tableNV.comparatorProperty());
			tableNV.setItems(sortedList);
		});
	}

	@FXML
	void CapNhatNhanvien(ActionEvent event) {
		// id_nv.setEditable(true);
		hovaten_nv.setEditable(true);
		ns_nv.setEditable(true);
		cv_nv.setEditable(true);
		gt_nv.setEditable(true);
		cmnd_nv.setEditable(true);
		sdt_nv.setEditable(true);
		diachi_nv.setEditable(true);
		luucapnhat.setVisible(true);
		ngayvaolam.setEditable(true);
		reset.setVisible(true);
	}
//a
	@FXML
	void XoaNhanvien(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Current project is modified");
		alert.setContentText("Save?");
		ButtonType okButton = new ButtonType("Yes");
		ButtonType noButton = new ButtonType("NO");
		alert.getButtonTypes().setAll(okButton, noButton);
		alert.showAndWait().ifPresent(type -> {
			if (type == okButton) {
				int t1 = (Integer.parseInt(id_nv.getText()));
				StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
						.configure("hibernate.cfg.xml").build();
				Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
				SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
				Session session = sessionFactory.openSession();
				Nhanvien nv = new Nhanvien(t1);
				nv = session.get(Nhanvien.class, t1);
				try {
					session.beginTransaction();

					if (nv != null) {
						session.delete(nv);

					}
					session.getTransaction().commit();
				} catch (RuntimeException error) {
					session.getTransaction().rollback();

				}
				initializeNHANVIEN();
				id_nv.setText("");
				hovaten_nv.setText("");
				ns_nv.setValue(null);
				cv_nv.setText("");
				sdt_nv.setText("");
				cmnd_nv.setText("");
				diachi_nv.setText("");
				gt_nv.setText("");
				imgnhanvien.setImage(null);
				ngayvaolam.setValue(null);
			} else if (type == ButtonType.NO) {
				alert.close();
			}
			ObservableList<Nhanvien> table = FXCollections.observableArrayList(getNhanvien());
		});
	}

	public void initializeNHANVIEN() {

		// id.setCellValueFactory(new PropertyValueFactory<Nhanvien, Integer>("id"));
		hovaten.setCellValueFactory(new PropertyValueFactory<Nhanvien, String>("hovaten"));
		ngaysinh.setCellValueFactory(new PropertyValueFactory<Nhanvien, Date>("ngaysinh"));
		chucvu.setCellValueFactory(new PropertyValueFactory<Nhanvien, String>("chucvu"));
		sdt.setCellValueFactory(new PropertyValueFactory<Nhanvien, Integer>("sdt"));
		cmnd.setCellValueFactory(new PropertyValueFactory<Nhanvien, Integer>("cmnd"));
		diachi.setCellValueFactory(new PropertyValueFactory<Nhanvien, Integer>("diachi"));
		// ObservableList<Nhanvien> table = FXCollections.observableArrayList(
		/// getNhanvien()

		// );
		tableNV.setItems(getNhanvien());
		search();
	}

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

//	 public void setNhanviendata(ObservableList<Nhanvien> TableNV) {
	// this.TableNV = TableNV;
	// }
	void reloadNHANVIEN() {
		//
		initializeNHANVIEN();
		getNhanvien();
	}

	@FXML
	void reload(ActionEvent event) {
		//
		initializeNHANVIEN();
		// ObservableList<Nhanvien> table = FXCollections.observableArrayList(
		// getNhanvien()

		// );
//	123212
		getNhanvien();
	}

	private void setCellValueFromTabletoTexfField() {
		tableNV.setOnMouseClicked(event -> {
			//
			Nhanvien nv = tableNV.getItems().get(tableNV.getSelectionModel().getSelectedIndex());
			id_nv.setText(Integer.toString(nv.getManv()));
			hovaten_nv.setText(nv.getHovaten());
		//	ns_nv.setText(Integer.toString(nv.getNgaysinh()));
			ns_nv.setValue(nv.getNgaysinh());
			ngayvaolam.setValue(nv.getNgayvaolam());
			cv_nv.setText(nv.getChucvu());
			gt_nv.setText(nv.getGioitinh());
			sdt_nv.setText(Integer.toString(nv.getSdt()));
			diachi_nv.setText(nv.getDiachi());
			cmnd_nv.setText(Integer.toString(nv.getCmnd()));
			byte[] getImageInBytes = nv.getImage();

			//

			try {
				FileOutputStream fos = new FileOutputStream(new File("photo.jpg"));
				fos.write(getImageInBytes);
				fos.close();
				image = new Image("file:photo.jpg", imgnhanvien.getFitHeight(), imgnhanvien.getFitHeight(), true, true);
				imgnhanvien.setImage(image);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
		});
		//Nhà cung cấp
	/*	Nhacungcap.setOnMouseClicked(event -> {
			//
			Nhacungcap ncc = Nhacungcap.getItems().get(Nhacungcap.getSelectionModel().getSelectedIndex());
			tfncc.setText(Integer.toString(ncc.getMancc()));
			tftenncc.setText(ncc.getTenncc());
			tfsdt.setText(Integer.toString(ncc.getSodienthoai()));
			tfdiachi1.setText(ncc.getDiachi());
			tfemail.setText(ncc.getEmail());
			});
		// */
		

	}

/////////////////////////////AUTHOR :LÊ HOÀNG TÂN /////////////////////////************************** 
//////////////////////////////////CHỨC NĂNG : QL KHÁCH HÀNG  ///////////*************************
///////////////////
	@FXML
	private TextField searchKH;

	@FXML
	private TableColumn idKH;

	@FXML
	private TableColumn hvtKH;

	@FXML
	private TableColumn sdtKH;

	@FXML
	private TableColumn nsKH;

	@FXML
	private TableColumn gtKH;

	@FXML
	private TableColumn diemtichluy;

	@FXML
	private TableColumn emailKH;

	@FXML
	private TableView<KhachHang> tableKH;

	// LỊCH SỬ MUA BÁN HÀNG
	@FXML
	private TableView tableLSMH;

	@FXML
	private TableColumn idKH1;

	@FXML
	private TableColumn tenKHmua;

	@FXML
	private TableColumn maHoaDon;

	@FXML
	private TableColumn tenNVBanHang;

	@FXML
	private TableColumn ngaymua;

	public ObservableList<KhachHang> getKhachHang() {
		ObservableList<KhachHang> TableKH = FXCollections.observableArrayList();
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
				.build();
		Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
		Session session = sessionFactory.openSession();

		CriteriaQuery<KhachHang> kh = session.getCriteriaBuilder().createQuery(KhachHang.class);
		kh.from(KhachHang.class);
		List<KhachHang> eList = session.createQuery(kh).getResultList();
		// List<Nhanvien> eList = session.createQuery(criteriaQuery).getResultList();
		// List<Nhanvien> eList = session.createQuery(Nhanvien.class).list();
		for (KhachHang ent : eList) {
			TableKH.add(ent);
		}
		return TableKH;

	}

	void searchKH() {
		ObservableList<KhachHang> table = FXCollections.observableArrayList(getKhachHang());
		FilteredList<KhachHang> filterData = new FilteredList<>(table, p -> true);
		searchKH.textProperty().addListener((observable, oldvalue, newvalue) -> {
			filterData.setPredicate(kh -> {
				if (newvalue == null || newvalue.isEmpty()) {
					return true;
				}
				String typetext = newvalue.toLowerCase();
				if (kh.getTenkh().toLowerCase().indexOf(typetext) != -1) {
					return true;
				}
				// if(kh.getSodienthoai().toLowerCase().indexOf(typetext) !=-1) {
				// return true;
				// }
				return false;

			});
			SortedList<KhachHang> sortedList = new SortedList<>(filterData);
			sortedList.comparatorProperty().bind(tableKH.comparatorProperty());
			tableKH.setItems(sortedList);
		});
	}

/////////////////////////////AUTHOR :HỒNG THÁI/////////////////////////************************** 
//////////////////////////////////CHỨC NĂNG : QUẢN LÝ KHO ///////////*************************
	///////////////////

	@FXML
	private TableColumn donvitinh;

	@FXML
	private TableColumn donvi;

	@FXML
	private TableColumn giatien;

	@FXML
	private AnchorPane anchorpane1;
	
	@FXML
    private AnchorPane anchorpane2;
	
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
	private TextField Timkiem;

	ObservableList<Sanpham> listM;

	@FXML
	private Button idaddSP;

	@FXML
	private Button iddelSP;

	@FXML
	private Button idupdSP;
	
	
	@FXML
	private Button lichsudathang;
	
	@FXML
	private Button lapphieudahang;
	
	   @FXML
	    private TextField tfloaisanpham;
	   @FXML
	   private Text idsanpham;
	
	ObservableList<Sanpham> table1 = FXCollections.observableArrayList(getSanpham());

	@FXML
	private void LapPhieuDatHang(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("lapphieudathang.fxml"));
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	private void LichSuDatHang(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("lichsudathang.fxml"));
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	private TableView<Sanpham> tableSP;

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

	@FXML
	private void ThemSP(ActionEvent event) throws IOException {
		// ta.setText("");
	//	idsanpham.setVisible(false);
	//	tf2.setVisible(false);
		String t1 = tf1.getText();
	//	int t2 = Integer.parseInt(tf2.getText());
		String t3 = tf3.getText();
		String t5 = tf5.getText();
		int t4 = Integer.parseInt(tf4.getText());
		String t6 = tfloaisanpham.getText();
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
				.build();
		Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
		Session session = sessionFactory.openSession();
		Sanpham sanpham = new Sanpham(t1,t6,t3,t4,t5);
		// person=session.get(Person.class, t1);
		try {
			session.beginTransaction();
			session.save(sanpham);
			session.getTransaction().commit();
			// ta.appendText("Them Thanh Cong ! ! !");
			ReloadSANPHAM();
		} catch (RuntimeException error) {
			session.getTransaction().rollback();
			// ta.appendText("Khong the thuc hien thao tac ! ");
		}

	}
	   @FXML
	    void XoaSP(ActionEvent event) {
		   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			 alert.setTitle("Xoa San Pham");
			 alert.setContentText("Save?");
			 ButtonType okButton = new ButtonType("Yes");
			 ButtonType noButton = new ButtonType("No");
			 alert.getButtonTypes().setAll(okButton, noButton);
			 alert.showAndWait().ifPresent(type -> {
			         if (type == okButton) {
			        	int masanpham = (Integer.parseInt(tf2.getText()));
			         	StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
			     				.configure("hibernate.cfg.xml")
			     				.build();
			     		Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
			     		SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
			     		Session session = sessionFactory.openSession();
			     		Sanpham sp = new Sanpham();
			     		sp=session.get(Sanpham.class, masanpham);
			     		try {
			     			session.beginTransaction();
			     			
			     			if(sp != null) {
			     				session.delete(sp);
			     				ReloadSANPHAM();
			     				
			     			}		
			     			session.getTransaction().commit();	
			     		} catch (RuntimeException error) {
			     			session.getTransaction().rollback();
			     			
			     		}
			     		initialize1();
			     		tf1.setText("");
			     		tf2.setText("");
			     		tf3.setText("");
			     		tf4.setText("");
			     		tf5.setText("");
			     		tfloaisanpham.setText(null);
			     		
			         } else if (type == ButtonType.NO) {
			        	 alert.close();
			         }
			         ObservableList<Sanpham> table = FXCollections.observableArrayList(getSanpham());
			 });
	   }
	   @FXML
		void SuaSP (ActionEvent event) {
		    	tf1.setEditable(true);
		    	tf2.setEditable(true);
		    	tf3.setEditable(true);
		    	tf4.setEditable(true);
		    	tf5.setEditable(true);
		    	tfloaisanpham.setEditable(true);
		    	idluusp.setVisible(true);
	   }
	   @FXML
	    private Button idluusp;
	     
	    @FXML
	    void luusp (ActionEvent actionEvent) {
	    	idsanpham.setVisible(true);
			tf2.setVisible(true);
	    	Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Cap nhat thanh cong ");
			Integer masanpham = Integer.parseInt(tf2.getText());
	    	String tensanpham = tf1.getText();
	    	String donvi = tf3.getText();
	    	String donvitinh = tf5.getText();
	    	Integer giatien = Integer.parseInt(tf4.getText());
	    	String loaisanpham = tfloaisanpham.getText();
	    	StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
					.configure("hibernate.cfg.xml")
					.build();
			Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
			SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
			Session session = sessionFactory.openSession();
			try {
				session.beginTransaction();
				Sanpham sp = new Sanpham(tensanpham,loaisanpham,donvi,giatien,donvitinh);
				sp=session.get(Sanpham.class, masanpham);
				if (sp != null) {
				//	nv2.setid(idnv);
					sp.setTensanpham(tensanpham);
					//sp.setMasanpham(masanpham);
					sp.setDonvi(donvi);
					sp.setDonvitinh(donvitinh);
					sp.setGiatien(giatien);
					sp.setLoaisanpham(loaisanpham);
					 session.save(sp);
					 ReloadSANPHAM();
					 alert.setContentText("Da Cap nhat San pham !");
		        	 alert.showAndWait();
		        	 idluusp.setVisible(false);
		        	 
		         	tf1.setEditable(false);
		        	tf2.setEditable(false);
		        	tf3.setEditable(false);
		        	tf4.setEditable(false);
		        	tf5.setEditable(false);
		        	tfloaisanpham.setEditable(false);
		        	
				}
				session.getTransaction().commit();
			}catch (RuntimeException error) {
				session.getTransaction().rollback();
	    }
			ReloadSANPHAM();
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
		initialize1();
		getSanpham();

	}

	 private void setCellValueFromTabletoTexfFieldd()  {
		 tableSP.setOnMouseClicked(event -> {
			 //
			 Sanpham sp = tableSP.getItems().get(tableSP.getSelectionModel().getSelectedIndex());
			 tf1.setText(sp.getTensanpham());
			 tf2.setText(Integer.toString(sp.getMasanpham()));
			 tf3.setText(sp.getDonvi());
			 tf4.setText(Integer.toString(sp.getGiatien()));
			 tf5.setText(sp.getDonvitinh()); 
			 tfloaisanpham.setText(sp.getLoaisanpham());
		 });
		 
		
	 }
	void initialize1() {
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
/////////////////////////////AUTHOR :HỒNG THÁI/////////////////////////************************** 
//////////////////////////////////CHỨC NĂNG : THỐNG KÊ ///////////*************************
///////////////////

/////////////////////////////AUTHOR :TRƯƠNG NGUYỄN YẾN NHI /////////////////////////************************** 
//////////////////////////////////CHỨC NĂNG : KIỂM TRA NHẬP HÀNG  ///////////*************************
///////////////////

/////////////////////////////AUTHOR :TRƯƠNG NGUYỄN YẾN NHI /////////////////////////************************** 
//////////////////////////////////CHỨC NĂNG : DANH MỤC  ///////////*************************
///////////////////

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
	 * --------------------------FXML Danh Mục Phiếu Hóa
	 * Đơn-------------------------
	 * 
	 */

	@FXML
	private Tab sTab_PhieuHoaDon;

	@FXML
	private TableColumn mahoadon;

	@FXML
	private TableColumn thoigianmua;

	@FXML
	private TableColumn tonggia;

	@FXML
	private TableColumn<Hoadon,KhachHang> makh;

	@FXML
	private TableColumn<Hoadon,Nhanvien> manv1;

	@FXML
	private TableColumn<Phieudathang,Nhanvien> manv;

	@FXML
	private TextField searchPHD;

	@FXML
	private Label lbDanhMucPHD;

	@FXML
	private ScrollBar verticalPHD;

	@FXML
	private TableView<Hoadon> tableHoaDon;

	ObservableList<Hoadon> listPHD;

	// HÓA ĐƠN
	public ObservableList<Hoadon> getHoadon() {
		ObservableList<Hoadon> tableHoaDon = FXCollections.observableArrayList();
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
				.build();
		Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
		Session session = sessionFactory.openSession();

		CriteriaQuery<Hoadon> hd = session.getCriteriaBuilder().createQuery(Hoadon.class);
		hd.from(Hoadon.class);
		List<Hoadon> eList = session.createQuery(hd).getResultList();
		// List<Nhanvien> eList = session.createQuery(criteriaQuery).getResultList();
		// List<Nhanvien> eList = session.createQuery(Nhanvien.class).list();
		for (Hoadon ent : eList) {
			tableHoaDon.add(ent);
		}
		return tableHoaDon;
	}
	
	
	void searchPHD() {
		ObservableList<Hoadon> tbHoaDon = FXCollections.observableArrayList(getHoadon());

		FilteredList<Hoadon> filteredData = new FilteredList<>(tbHoaDon, b -> true);
		searchPHD.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(hoadon -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (hoadon.getMahoadon().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches username
				} else if (hoadon.getThoigianmua().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches password
				} else
					return false; // Does not match.
			});
		});

		SortedList<Hoadon> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tableHoaDon.comparatorProperty());
		tableHoaDon.setItems(sortedData);
	}
	
	@FXML
	private void changeSceneHoadonDetail (ActionEvent event) throws IOException {
		
	//	Parent root = FXMLLoader.load(getClass().getResource("HoadonDetail.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("HoadonDetail.fxml"));
        Parent hoadonViewParent = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(hoadonViewParent);
        Hoadon selected = tableHoaDon.getSelectionModel().getSelectedItem();
        HoadonDetailController DSNVController = loader.getController();
        DSNVController.setHoadon(selected);
        stage.setTitle("Chi tiet hoa don");
        stage.setScene(scene);
        stage.show();
		
       // Scene scene1 = new Scene(hoadonViewParent);
        
		
	}
	
	/*protected void updateItem(T item, boolean empty) {
	    super.updateItem(item, empty);

	    if (empty || item == null) {
	        setText(null);
	      //  setGraphic(null);
	    } else {
	        setText(item.toString());
	    }
	}*/
	
	
	//chi tiết hóa đơn  new stage 
/*	public void changeSceneHoadonDetail(ActionEvent e) throws IOException {
		Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("HoadonDetail.fxml"));
        Parent hoadonViewParent = loader.load();
        Scene scene = new Scene(hoadonViewParent);
        HoadonDetailController DSNVController = loader.getController();
        Hoadon selected = tableHoaDon.getSelectionModel().getSelectedItem();
        DSNVController.setHoadon(selected);
        stage.setScene(scene);
	}*/

	/*
	 * 
	 * --------------------- FXML Danh mục phiếu đặt
	 * hàng-----------------------------
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
	private TableColumn tongtien1;

	@FXML
	private ScrollBar verticalPDH;

	ObservableList<Phieudathang> listPDH;

	@FXML
	void searchPDH() {
		ObservableList<Phieudathang> tbPhieuDatHang = FXCollections.observableArrayList(getPhieudathang());

		FilteredList<Phieudathang> filteredData = new FilteredList<>(tbPhieuDatHang, b -> true);
		searchPDH.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(phieudathang -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (phieudathang.getMadathang().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches username
				} else if (phieudathang.getThoigiandat().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches password
				} else
					return false; // Does not match.
			});
		});

		SortedList<Phieudathang> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tablePhieuDatHang.comparatorProperty());
		tablePhieuDatHang.setItems(sortedData);

	}

	// PHIẾU ĐẶT HÀNG
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
		// List<Nhanvien> eList = session.createQuery(criteriaQuery).getResultList();
		// List<Nhanvien> eList = session.createQuery(Nhanvien.class).list();
		for (Phieudathang ent : eList) {
			tablePhieuDatHang.add(ent);
		}
		return tablePhieuDatHang;
	}
	 @FXML
	    void changeSceneDathangDetail(ActionEvent event) throws IOException  {
		 	FXMLLoader loader = new FXMLLoader(getClass().getResource("DathangDetail.fxml"));
	        Parent dathangViewParent = loader.load();
	        Stage stage1 = new Stage();
	        Scene scene1 = new Scene(dathangViewParent);
	        Phieudathang selected = tablePhieuDatHang.getSelectionModel().getSelectedItem();
	        DathangDetailController DSNVController = loader.getController();
	        DSNVController.setPhieudathang(selected);
	        stage1.setTitle("Chi tiet dat hang");
	        stage1.setScene(scene1);
	        stage1.show();
	    }
	/*
	 * 
	 * --------------------- FXML Danh mục phiếu nhập
	 * hàng-----------------------------
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
	private TableColumn mancc2;

	@FXML
	private TableColumn thoigiannhap;
	
	@FXML
	private TableColumn tongtien;

	@FXML
	private TableColumn manv2;

	@FXML
	private ScrollBar verticalPNH;

	ObservableList<Phieunhaphang> listPNH;

	@FXML
	void searchPNH() {
		ObservableList<Phieunhaphang> tbPhieuNhapHang = FXCollections.observableArrayList(getPhieunhaphang());

		FilteredList<Phieunhaphang> filteredData = new FilteredList<>(tbPhieuNhapHang, b -> true);
		searchPNH.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(phieunhaphang -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (phieunhaphang.getManhaphang().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches username
				} else if (phieunhaphang.getThoigiannhap().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches password
				} else
					return false; // Does not match.
			});
		});

		SortedList<Phieunhaphang> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tablePhieuNhapHang.comparatorProperty());
		tablePhieuNhapHang.setItems(sortedData);

	}

	public ObservableList<Phieunhaphang> getPhieunhaphang() {
		ObservableList<Phieunhaphang> tablePhieuNhapHang = FXCollections.observableArrayList();
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
				.build();
		Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
		Session session = sessionFactory.openSession();

		CriteriaQuery<Phieunhaphang> pnh = session.getCriteriaBuilder().createQuery(Phieunhaphang.class);
		pnh.from(Phieunhaphang.class);
		List<Phieunhaphang> eList = session.createQuery(pnh).getResultList();
		// List<Nhanvien> eList = session.createQuery(criteriaQuery).getResultList();
		// List<Nhanvien> eList = session.createQuery(Nhanvien.class).list();
		for (Phieunhaphang ent : eList) {
			tablePhieuNhapHang.add(ent);
		}
		return tablePhieuNhapHang;
	}

	/*
	 * 
	 * --------------------- FXML Danh mục phiếu trả
	 * hàng-----------------------------
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

	//@FXML
	//private TableColumn lido;

	@FXML
	private TableColumn thoigiantra;

	@FXML
	private TableColumn mancc3;

	@FXML
	private TextField searchPTH;

	@FXML
	private Label lbDanhMucPTH;

	@FXML
	private ScrollBar verticalPTH;

	ObservableList<Phieutrahang> listPTH;

	@FXML
	void searchPTH() {
		ObservableList<Phieutrahang> tbPhieuTraHang = FXCollections.observableArrayList(getPhieutrahang());

		FilteredList<Phieutrahang> filteredData = new FilteredList<>(tbPhieuTraHang, b -> true);
		searchPTH.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(phieutrahang -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (phieutrahang.getMaphieutra().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches username
				} else if (phieutrahang.getThoigiantra().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches password
				} else
					return false; // Does not match.
			});
		});

		SortedList<Phieutrahang> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tablePhieuTraHang.comparatorProperty());
		tablePhieuTraHang.setItems(sortedData);

	}

	@FXML
	private Text txtTitle_store;

	// PHIẾU TRẢ HÀNG
	public ObservableList<Phieutrahang> getPhieutrahang() {
		ObservableList<Phieutrahang> tablePhieuTraHang = FXCollections.observableArrayList();
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
				.build();
		Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
		Session session = sessionFactory.openSession();

		CriteriaQuery<Phieutrahang> pth = session.getCriteriaBuilder().createQuery(Phieutrahang.class);
		pth.from(Phieutrahang.class);
		List<Phieutrahang> eList = session.createQuery(pth).getResultList();
		// List<Nhanvien> eList = session.createQuery(criteriaQuery).getResultList();
		// List<Nhanvien> eList = session.createQuery(Nhanvien.class).list();
		for (Phieutrahang ent : eList) {
			tablePhieuTraHang.add(ent);
		}
		return tablePhieuTraHang;
	}


	// SẢN PHẨM
	public ObservableList<Sanpham> getSanPham1() {
		ObservableList<Sanpham> TableQLSP = FXCollections.observableArrayList();
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
				.build();
		Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
		Session session = sessionFactory.openSession();

		CriteriaQuery<Sanpham> QLSP = session.getCriteriaBuilder().createQuery(Sanpham.class);
		QLSP.from(Sanpham.class);
		List<Sanpham> eList = session.createQuery(QLSP).getResultList();
		// List<Nhanvien> eList = session.createQuery(criteriaQuery).getResultList();
		// List<Nhanvien> eList = session.createQuery(Nhanvien.class).list();
		for (Sanpham ent : eList) {
			TableQLSP.add(ent);
		}
		return TableQLSP;
	}



/////////////////////////////AUTHOR :LÊ QUANG SANG /////////////////////////************************** 
//////////////////////////////////CHỨC NĂNG : NHÀ CUNG CẤP  ///////////*************************
///////////////////
	@FXML
	private Button thanhtoancongno;

	@FXML
	private Button taonhacungcap;

	@FXML
	private TableView<Nocong> tableNhacungcap;

	@FXML
	private TableColumn mancc1;

	@FXML
	private TableColumn tenncc;

	@FXML
	private TableColumn diachi1;

	@FXML
	private TableColumn sotienno;

	@FXML
	private TableColumn email;

	@FXML
	private TableColumn sodienthoai;

	@FXML
	private TableColumn thoigianno;

	@FXML
	private TextField tfncc;

	@FXML
	private TextField tftenncc;

	@FXML
	private TextField tfsdt;

	@FXML
	private TextField tfdiachi1;

	@FXML
	private TextField tfemail;

	@FXML
	private Button idaddncc;

	@FXML
	private Button idreloadncc;

	@FXML
	private Button idupdatencc;

	@FXML
	private ComboBox<String> cbb;
	
	@FXML
	private Button xoanhacc;
	
	 @FXML
	 void XoaNCC(ActionEvent event) {
		 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Current project is modified");
			alert.setContentText("Delete?");
			ButtonType okButton = new ButtonType("Yes");
			ButtonType noButton = new ButtonType("NO");
			alert.getButtonTypes().setAll(okButton, noButton);
			alert.showAndWait().ifPresent(type -> {
				if (type == okButton) {
					int id_ncc = (Integer.parseInt(tfncc.getText()));
					StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
							.configure("hibernate.cfg.xml").build();
					Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
					SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
					Session session = sessionFactory.openSession();
					Nhacungcap ncc = new Nhacungcap(id_ncc);
					//Nhanvien nv = new Nhanvien(t1);
					ncc = session.get(Nhacungcap.class, id_ncc);
					try {
						session.beginTransaction();

						if (ncc != null) {
							session.delete(ncc);

						}
						session.getTransaction().commit();
					} catch (RuntimeException error) {
						session.getTransaction().rollback();

					}
					ReloadNHACUNGCAP();
					tfncc.setText(null);
					tftenncc.setText(null);
					tfsdt.setText(null);
					tfdiachi1.setText(null);
					tfemail.setText(null);
				} else if (type == ButtonType.NO) {
					alert.close();
				}
				ObservableList<Nhanvien> table = FXCollections.observableArrayList(getNhanvien());
			});
	 }

	// NHÀ CUNG CẤP
	public ObservableList<Nocong> getNhacungcap() {
		ObservableList<Nocong> TableNhacungcap = FXCollections.observableArrayList();
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
				.build();
		Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
		Session session = sessionFactory.openSession();
		CriteriaQuery<Nocong> ncc = session.getCriteriaBuilder().createQuery(Nocong.class);
		//SELECT * FROM nocong INNER JOIN nhacungcap
		Root<Nocong> root = ncc.from(Nocong.class);
		Join<Nocong, Nhacungcap> NocongJoin = root.join("nhacungcap", JoinType.INNER);
		//FROM
		//ncc.from(Nhacungcap.class);
		List<Nocong> eList = session.createQuery(ncc).getResultList();
		// List<Nhanvien> eList = session.createQuery(criteriaQuery).getResultList();
		// List<Nhanvien> eList = session.createQuery(Nhanvien.class).list();
		for (Nocong ent : eList) {
			TableNhacungcap.add(ent);
		}
		return TableNhacungcap;
	}

	@FXML
	void addncc(ActionEvent event) {
		//ObservableList<Nocong> Tablencc = FXCollections.observableArrayList(getNhacungcap());
		// ta.setText("");

		Integer mancc = Integer.parseInt(tfncc.getText());
		String tenncc = tftenncc.getText();
		String diachi = tfdiachi1.getText();
		Integer sodienthoai = Integer.parseInt(tfsdt.getText());
		String email = tfemail.getText();

		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
				.build();
		Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
		Session session = sessionFactory.openSession();
		Nhacungcap ncc = new Nhacungcap(mancc, tenncc, diachi, sodienthoai, email);
		// person=session.get(Person.class, t1);
		try {
			session.beginTransaction();
			session.save(ncc);
			session.getTransaction().commit();
			// ta.appendText("Them Thanh Cong ! ! !");
			ReloadNHACUNGCAP();
		} catch (RuntimeException error) {
			session.getTransaction().rollback();
			// ta.appendText("Khong the thuc hien thao tac ! ");
		}
	}

	
	public void ReloadNHACUNGCAP() {
		mancc1.setCellValueFactory(new PropertyValueFactory<>("nhacungcap"));
		mancc1.setCellFactory(table -> new TableCell<Nocong,Nhacungcap>(){
 	    	 @Override
		    protected void updateItem(Nhacungcap item, boolean empty) {
		        super.updateItem(item, empty);
		        if (empty || item == null) {
		            setText(null);
		        } else {
		            setText(Integer.toString(item.getMancc()));
		        }
		    }
 	    });
		tenncc.setCellValueFactory(new PropertyValueFactory<>("nhacungcap"));
		tenncc.setCellFactory(table -> new TableCell<Nocong,Nhacungcap>(){
	    	 @Override
		    protected void updateItem(Nhacungcap item, boolean empty) {
		        super.updateItem(item, empty);
		        if (empty || item == null) {
		            setText(null);
		        } else {
		            setText(item.getTenncc());
		        }
		    }
	    });
		diachi1.setCellValueFactory(new PropertyValueFactory<>("nhacungcap"));
		diachi1.setCellFactory(table -> new TableCell<Nocong,Nhacungcap>(){
	    	 @Override
		    protected void updateItem(Nhacungcap item, boolean empty) {
		        super.updateItem(item, empty);
		        if (empty || item == null) {
		            setText(null);
		        } else {
		            setText(item.getDiachi());
		        }
		    }
	    });
		sodienthoai.setCellValueFactory(new PropertyValueFactory<>("nhacungcap"));
		sodienthoai.setCellFactory(table -> new TableCell<Nocong,Nhacungcap>(){
	    	 @Override
		    protected void updateItem(Nhacungcap item, boolean empty) {
		        super.updateItem(item, empty);
		        if (empty || item == null) {
		            setText(null);
		        } else {
		            setText(Integer.toString(item.getSodienthoai()));
		        }
		    }
	    });
		sotienno.setCellValueFactory(new PropertyValueFactory<Nocong,Nhacungcap>("nhacungcap"));
		// Integer>("sotienno"));
		// thoigianno.setCellValueFactory(new PropertyValueFactory<Nocong,
		// Integer>("thoigianno"));
		email.setCellValueFactory(new PropertyValueFactory<>("nhacungcap"));
		email.setCellFactory(table -> new TableCell<Nocong,Nhacungcap>(){
	    	 @Override
		    protected void updateItem(Nhacungcap item, boolean empty) {
		        super.updateItem(item, empty);
		        if (empty || item == null) {
		            setText(null);
		        } else {
		            setText(item.getEmail());
		        }
		    }
	    });
		//setCellValueFromTabletoTexfFieldNCC();
		tableNhacungcap.setItems(getNhacungcap());
		
		
	}
	
	
	
	@FXML
	void Taonhacungcap(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("taonhacungcap.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root1));
			stage.setTitle("Tạo nhà cung cấp");
			stage.show();
		} catch (Exception e) {
			// TODO: handle exception

		}
	}

	@FXML
	void Thanhtoancongno(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("thanhtoancongno.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root1));
			stage.setTitle("Thanh Toán Công Nợ");
			stage.show();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@FXML
	void Reloadncc(ActionEvent event) {
		ReloadNHACUNGCAP();
	}

	@FXML
	void updatencc(ActionEvent event) {
		tfncc.setEditable(true);
		tftenncc.setEditable(true);
		tfsdt.setEditable(true);
		tfdiachi1.setEditable(true);
		tfemail.setEditable(true);
		idluuncc.setVisible(true);
	}

	@FXML
	private Button idluuncc;

	@FXML
	void luuncc(ActionEvent actionEvent) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Cap nhat thanh cong ");
		Integer mancc = Integer.parseInt(tfncc.getText());
		String tenncc = tftenncc.getText();
		String diachi = tfdiachi1.getText();
		Integer sodienthoai = Integer.parseInt(tfsdt.getText());
		String email = tfemail.getText();
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
				.build();
		Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Nhacungcap ncc1 = new Nhacungcap(mancc, tenncc, diachi, sodienthoai, email);
			ncc1 = session.get(Nhacungcap.class, mancc);
			if (ncc1 != null) {
				// nv2.setid(idnv);
				ncc1.setMancc(mancc);
				ncc1.setTenncc(tenncc);
				ncc1.setDiachi(diachi);
				ncc1.setSodienthoai(sodienthoai);
				ncc1.setEmail(email);

				// person2.setAge(t2);
				/// person2.setAddress(t3);
				session.save(ncc1);
				alert.setContentText("Cap nhat Nha Cung Cap thanh cong !");
				alert.showAndWait();
				idluuncc.setVisible(false);

				tfncc.setEditable(false);
				tftenncc.setEditable(false);
				tfdiachi1.setEditable(false);
				tfsdt.setEditable(false);
				tfemail.setEditable(false);

			}
			session.getTransaction().commit();
		} catch (RuntimeException error) {
			session.getTransaction().rollback();
		}

		ReloadNHACUNGCAP();
	}
	
	/*private void setCellValueFromTabletoTexfFieldNCC() {
		Nhacungcap.setOnMouseClicked(event -> {
			//
			Nhacungcap ncc = Nhacungcap.getItems().get(Nhacungcap.getSelectionModel().getSelectedIndex());
			tfncc.setText(Integer.toString(ncc.getMancc()));
			tftenncc.setText(ncc.getTenncc());
			tfsdt.setText(Integer.toString(ncc.getSodienthoai()));
			tfdiachi1.setText(ncc.getDiachi());
			tfemail.setText(ncc.getEmail());
			//
		});

	}*/
	
	



/////////////////////////////AUTHOR :TỪ CHÍ HUY/////////////////////////************************** 
//////////////////////////////////CHỨC NĂNG : BÁN HÀNG  ///////////*************************
///////////////////
	
	public void initialize(URL url, ResourceBundle rb) {
	

		// QL NHÂN VIÊN //HOÀNG TÂN
		ObservableList<String> list = FXCollections.observableArrayList("Danh sách nhân viên ", "Lịch làm");
		Listnhanvien.setItems(list);
		Listnhanvien.getSelectionModel().select("Danh sách nhân viên");
		initializeNHANVIEN();
		setCellValueFromTabletoTexfField();
		search();

		// QL KHÁCH HÀNG //HOÀNG TÂN
		idKH.setCellValueFactory(new PropertyValueFactory<KhachHang, Integer>("makh"));
		hvtKH.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("tenkh"));
		// loai.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("loai"));
		sdtKH.setCellValueFactory(new PropertyValueFactory<KhachHang, Integer>("sodienthoai"));
		nsKH.setCellValueFactory(new PropertyValueFactory<KhachHang, Integer>("ngaysinh"));
		gtKH.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("gioitinh"));
		diemtichluy.setCellValueFactory(new PropertyValueFactory<KhachHang, Integer>("diemtichluy"));
		emailKH.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("email"));
		tableKH.setItems(getKhachHang());
		searchKH();

		// QL KHO//HỒNG THÁI
		setCellValueFromTabletoTexfFieldd();
		tensanpham.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("tensanpham"));
		masanpham.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("masanpham"));
		// loai.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("loai"));
		donvi.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("donvi"));
		giatien.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("giatien"));
		donvitinh.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("donvitinh"));
		loaisanpham.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("loaisanpham"));
		tableSP.setItems(getSanpham());
		Timkiem();
		// QL danh mục phiếu hóa đơn //Nhi
		mahoadon.setCellValueFactory(new PropertyValueFactory<Hoadon, String>("mahoadon"));
		thoigianmua.setCellValueFactory(new PropertyValueFactory<Hoadon, String>("thoigianmua"));
		tonggia.setCellValueFactory(new PropertyValueFactory<Hoadon, Integer>("tonggia"));
		makh.setCellFactory(tableHoaDon -> new TableCell<Hoadon,KhachHang>() {
		    @Override
		    protected void updateItem(KhachHang item, boolean empty) {
		        super.updateItem(item, empty);
		        if (empty || item == null) {
		            setText(null);
		        } else {
		            setText(Integer.toString(item.getMakh()));
		        }
		    }

		});
		manv1.setCellFactory(tableHoaDon -> new TableCell<Hoadon,Nhanvien>() {
		    @Override
		    protected void updateItem(Nhanvien item, boolean empty) {
		        super.updateItem(item, empty);
		        if (empty || item == null) {
		            setText(null);
		        } else {
		            setText(Integer.toString(item.getManv()));
		        }
		    }

		});
		
		makh.setCellValueFactory(new PropertyValueFactory<>("khachhang"));
		manv1.setCellValueFactory(new PropertyValueFactory<>("nhanvien"));
	/*	makh.setCellFactory(tv -> new TableCell<>() {

		    @Override
		    protected void updateItem(Hoadon item, boolean empty) {
		        super.updateItem(item, empty);
		        if (empty || item == null) {
		            setText(null);
		        } else {
		            setText(item.getKhachhang());
		        }
		    }

		});*/
		
		tableHoaDon.setItems(getHoadon());
		searchPHD();
	//	tableHoaDon.setItems(listPHD);

		// QL danh mục phiếu đặt hàng //Nhi
		madathang.setCellValueFactory(new PropertyValueFactory<Phieudathang, String>("madathang"));
		thoigiandat.setCellValueFactory(new PropertyValueFactory<Phieudathang, String>("thoigiandat"));
		tongtien1.setCellValueFactory(new PropertyValueFactory<Phieudathang, Integer>("tongtien"));
		mancc.setCellValueFactory(new PropertyValueFactory<>("nhacungcap"));
		mancc.setCellFactory(tablePhieuDatHang -> new TableCell<Phieudathang,Nhacungcap>(){
			@Override
 		    protected void updateItem(Nhacungcap item, boolean empty) {
 		        super.updateItem(item, empty);
 		        if (empty || item == null) {
 		            setText(null);
 		        } else {
 		            setText(String.valueOf(item.getMancc()));
 		        }
 		    }
			
		});
		manv.setCellValueFactory(new PropertyValueFactory<>("nhanvien"));
		manv.setCellFactory(tablePhieuDatHang ->new TableCell<Phieudathang,Nhanvien>(){
			@Override
 		    protected void updateItem(Nhanvien item, boolean empty) {
 		        super.updateItem(item, empty);
 		        if (empty || item == null) {
 		            setText(null);
 		        } else {
 		            setText(String.valueOf(item.getManv()));
 		        }
 		    }
		});
		tablePhieuDatHang.setItems(getPhieudathang());
		searchPDH();

		// QL danh mục phiếu nhập hàng // Nhi
		manhaphang.setCellValueFactory(new PropertyValueFactory<Phieunhaphang, String>("manhaphang"));
		thoigiannhap.setCellValueFactory(new PropertyValueFactory<Phieunhaphang, String>("thoigiannhap"));
		tongtien.setCellValueFactory(new PropertyValueFactory<Phieunhaphang, Integer>("tongtien"));
		mancc2.setCellValueFactory(new PropertyValueFactory<Phieunhaphang, Integer>("mancc"));
		manv2.setCellValueFactory(new PropertyValueFactory<Phieunhaphang, Integer>("manv"));
		tablePhieuNhapHang.setItems(getPhieunhaphang());
		searchPNH();

		// QL danh mục phiếu trả hàng //Nhi
		maphieutra.setCellValueFactory(new PropertyValueFactory<Phieutrahang, String>("maphieutra"));
		thoigiantra.setCellValueFactory(new PropertyValueFactory<Phieutrahang, String>("thoigiantra"));
		mancc3.setCellValueFactory(new PropertyValueFactory<Phieutrahang, Integer>("mancc"));
		//manv.setCellValueFactory(new PropertyValueFactory<Phieutrahang, Integer>("manv"));
		tablePhieuTraHang.setItems(getPhieutrahang());
		searchPTH();

		// QL nhà cung cấp //Sang
		mancc1.setCellValueFactory(new PropertyValueFactory<>("nhacungcap"));
		mancc1.setCellFactory(table -> new TableCell<Nocong,Nhacungcap>(){
 	    	 @Override
		    protected void updateItem(Nhacungcap item, boolean empty) {
		        super.updateItem(item, empty);
		        if (empty || item == null) {
		            setText(null);
		        } else {
		            setText(Integer.toString(item.getMancc()));
		        }
		    }
 	    });
		tenncc.setCellValueFactory(new PropertyValueFactory<>("nhacungcap"));
		tenncc.setCellFactory(table -> new TableCell<Nocong,Nhacungcap>(){
	    	 @Override
		    protected void updateItem(Nhacungcap item, boolean empty) {
		        super.updateItem(item, empty);
		        if (empty || item == null) {
		            setText(null);
		        } else {
		            setText(item.getTenncc());
		        }
		    }
	    });
		diachi1.setCellValueFactory(new PropertyValueFactory<>("nhacungcap"));
		diachi1.setCellFactory(table -> new TableCell<Nocong,Nhacungcap>(){
	    	 @Override
		    protected void updateItem(Nhacungcap item, boolean empty) {
		        super.updateItem(item, empty);
		        if (empty || item == null) {
		            setText(null);
		        } else {
		            setText(item.getDiachi());
		        }
		    }
	    });
		sodienthoai.setCellValueFactory(new PropertyValueFactory<>("nhacungcap"));
		sodienthoai.setCellFactory(table -> new TableCell<Nocong,Nhacungcap>(){
	    	 @Override
		    protected void updateItem(Nhacungcap item, boolean empty) {
		        super.updateItem(item, empty);
		        if (empty || item == null) {
		            setText(null);
		        } else {
		            setText(Integer.toString(item.getSodienthoai()));
		        }
		    }
	    });
		sotienno.setCellValueFactory(new PropertyValueFactory<Nocong,Nhacungcap>("nhacungcap"));
		// Integer>("sotienno"));
		// thoigianno.setCellValueFactory(new PropertyValueFactory<Nocong,
		// Integer>("thoigianno"));
		email.setCellValueFactory(new PropertyValueFactory<>("nhacungcap"));
		email.setCellFactory(table -> new TableCell<Nocong,Nhacungcap>(){
	    	 @Override
		    protected void updateItem(Nhacungcap item, boolean empty) {
		        super.updateItem(item, empty);
		        if (empty || item == null) {
		            setText(null);
		        } else {
		            setText(item.getEmail());
		        }
		    }
	    });
		//setCellValueFromTabletoTexfFieldNCC();
		tableNhacungcap.setItems(getNhacungcap());
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}

}

package DanhMuc;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import QLBH.GiaoDienQLController;
import QLBH.HibernateUtils;
import QLBH.Hoadon;
import QLBH.KhachHang;
import QLBH.Nhacungcap;
import QLBH.Nhanvien;
import QLBH.Phieudathang;
import QLBH.Phieunhaphang;
import QLBH.Phieutrahang;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class danhmucController implements Initializable {

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
	 * --------------------------FXML Danh Mục Phiếu Hóa Đơn
	 * //NHI-------------------------
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
	private TableColumn<Hoadon, KhachHang> makh;

	@FXML
	private TableColumn<Hoadon, Nhanvien> manv1;

	@FXML
	private TableColumn<Phieudathang, Nhanvien> manv;

	@FXML
	private TextField searchPHD;

	@FXML
	private Label lbDanhMucPHD;
	@FXML
	private Label thongbaoHD;

	@FXML
	private ScrollBar verticalPHD;

	@FXML
	private TableView<Hoadon> tableHoaDon;

	ObservableList<Hoadon> listPHD;

	// HÓA ĐƠN
	public ObservableList<Hoadon> getHoadon() {
		ObservableList<Hoadon> tableHoaDon = FXCollections.observableArrayList();
	/*	StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
				.build();
		Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
		Session session = sessionFactory.openSession();*/
		Session session = HibernateUtils.getSessionFactory().openSession();
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

				if (String.valueOf(hoadon.getMahoadon()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches username
					/*
					 * } else if (hoadon.getThoigianmua().toLowerCase().indexOf(lowerCaseFilter) !=
					 * -1) { return true; // Filter matches password
					 */
				} else
					return false; // Does not match.
			});
		});

		SortedList<Hoadon> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tableHoaDon.comparatorProperty());
		tableHoaDon.setItems(sortedData);
	}

	@FXML
	private void changeSceneHoadonDetail(ActionEvent event) throws IOException {

		// Parent root = FXMLLoader.load(getClass().getResource("HoadonDetail.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/DanhMuc/HoadonDetail.fxml"));
		Parent hoadonViewParent = loader.load();
		Stage stage = new Stage();
		Scene scene = new Scene(hoadonViewParent);
		scene.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				xoffset = stage.getX() - mouseEvent.getScreenX();
				yoffset = stage.getY() - mouseEvent.getScreenY();
			}
		});
		scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				stage.setX(mouseEvent.getScreenX() + xoffset);
				stage.setY(mouseEvent.getScreenY() + yoffset);
			}
		});
		Hoadon selected = tableHoaDon.getSelectionModel().getSelectedItem();
		HoadonDetailController DSNVController = loader.getController();
	//	DSNVController.setHoadon(selected);
		if(selected == null) {
			 thongbaoHD.setVisible(true);
			 thongbaoHD.setText("Không có phiếu hóa đơn được chọn!!!");
			 System.out.print("Không có phiếu hóa đơn được chọn!!!");
			 return;
		 }
		 else if(selected != null){
			 DSNVController.setHoadon(selected);
			 thongbaoHD.setVisible(false);
			 
		 }
		stage.setTitle("Chi tiet hoa don");
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setScene(scene);
		stage.show();
		GiaoDienQLController.getInstance().truedisable();


	}
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
	private Label thongbaoDH;

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
				if (String.valueOf(phieudathang.getMadathang()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches username

			/*	} else if (phieudathang.getThoigiandat().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches password*/
				} else
					return false; // Does not match.

			});

			SortedList<Phieudathang> sortedData = new SortedList<>(filteredData);
			sortedData.comparatorProperty().bind(tablePhieuDatHang.comparatorProperty());
			tablePhieuDatHang.setItems(sortedData);

		});
	}

	// PHIẾU ĐẶT HÀNG
	public ObservableList<Phieudathang> getPhieudathang() {
		ObservableList<Phieudathang> tablePhieuDatHang = FXCollections.observableArrayList();
		 Session session = HibernateUtils.getSessionFactory().openSession();
		/*	StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
				.build();
		Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
		Session session = sessionFactory.openSession();*/

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
	private static double xoffset = 0;
	private static double yoffset = 0;
	@FXML
	void changeSceneDathangDetail(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/DanhMuc/DathangDetail.fxml"));
		Parent dathangViewParent = loader.load();
		Stage stage1 = new Stage();
		Scene scene1 = new Scene(dathangViewParent);
		scene1.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				xoffset = stage1.getX() - mouseEvent.getScreenX();
				yoffset = stage1.getY() - mouseEvent.getScreenY();
			}
		});
		scene1.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				stage1.setX(mouseEvent.getScreenX() + xoffset);
				stage1.setY(mouseEvent.getScreenY() + yoffset);
			}
		});
		Phieudathang selected = tablePhieuDatHang.getSelectionModel().getSelectedItem();
		DathangDetailController Dathang = loader.getController();
	//	Dathang.setPhieudathang(selected);
		if(selected == null) {
			 thongbaoDH.setVisible(true);
			 thongbaoDH.setText("Không có phiếu đặt hàng được chọn!!!");
			 System.out.print("Không có phiếu đặt hàng được chọn!!!");
			 return;
		 }
		 else if(selected != null){
			 Dathang.setPhieudathang(selected);
			 thongbaoDH.setVisible(false);
			 
		 }
		stage1.setTitle("Chi tiet dat hang");
		stage1.setScene(scene1);
		stage1.initStyle(StageStyle.UNDECORATED);
		stage1.show();
		GiaoDienQLController.getInstance().truedisable();
	}

	/*
	 * 
	 * --------------------- FXML Danh mục phiếu nhập hàng
	 * //NHI-----------------------------
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
	private Label thongbaoNH;

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
			filteredData.setPredicate(phieudathang -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				if (String.valueOf(phieudathang.getManhaphang()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches username

			/*	} else if (phieudathang.getThoigiandat().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches password*/
				} else
					return false; // Does not match.

			});

			SortedList<Phieunhaphang> sortedData = new SortedList<>(filteredData);
			sortedData.comparatorProperty().bind(tablePhieuNhapHang.comparatorProperty());
			tablePhieuNhapHang.setItems(sortedData);

		});
	}
	 

	public ObservableList<Phieunhaphang> getPhieunhaphang() {
		ObservableList<Phieunhaphang> tablePhieuNhapHang = FXCollections.observableArrayList();
		 Session session = HibernateUtils.getSessionFactory().openSession();

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

	@FXML
	void changeSceneNhaphangDetail(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/DanhMuc/NhaphangDetail.fxml"));
		Parent nhaphangViewParent = loader.load();
		Stage stage1 = new Stage();
		Scene scene1 = new Scene(nhaphangViewParent);
		scene1.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				xoffset = stage1.getX() - mouseEvent.getScreenX();
				yoffset = stage1.getY() - mouseEvent.getScreenY();
			}
		});
		scene1.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				stage1.setX(mouseEvent.getScreenX() + xoffset);
				stage1.setY(mouseEvent.getScreenY() + yoffset);
			}
		});
		Phieunhaphang selected = tablePhieuNhapHang.getSelectionModel().getSelectedItem();
		NhaphangDetailController Nhaphang = loader.getController();
//		Nhaphang.setPhieunhaphang(selected);
		if(selected == null) {
			 thongbaoNH.setVisible(true);
			 thongbaoNH.setText("Không có phiếu nhập hàng được chọn!!!");
			 System.out.print("Không có phiếu nhập hàng được chọn!!!");
			 return;
		 }
		 else if(selected != null){
			 Nhaphang.setPhieunhaphang(selected);
			 thongbaoNH.setVisible(false);
			 
		 }
		stage1.setTitle("Chi tiet nhap hang");
		stage1.initStyle(StageStyle.UNDECORATED);
		stage1.setScene(scene1);
		stage1.show();
		GiaoDienQLController.getInstance().truedisable();
	}

	/*
	 * 
	 * --------------------- FXML Danh mục phiếu trả hàng
	 * //NHI-----------------------------
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

	// @FXML
	// private TableColumn lido;

	@FXML
	private TableColumn thoigiantra;

	@FXML
	private TableColumn mancc3;

	@FXML
	private TextField searchPTH;

	@FXML
	private Label lbDanhMucPTH;
	
	@FXML
	private Label thongbaoTH;

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

				if (String.valueOf(phieutrahang.getMaphieutra()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else
					return false; // Does not match.
			});
		});

		SortedList<Phieutrahang> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tablePhieuTraHang.comparatorProperty());
		tablePhieuTraHang.setItems(sortedData);

	}

	@FXML
	void changeSceneTrahangDetail(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/DanhMuc/TrahangDetail.fxml"));
		Parent trahangViewParent = loader.load();
		Stage stage1 = new Stage();
		Scene scene1 = new Scene(trahangViewParent);
		scene1.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				xoffset = stage1.getX() - mouseEvent.getScreenX();
				yoffset = stage1.getY() - mouseEvent.getScreenY();
			}
		});
		scene1.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				stage1.setX(mouseEvent.getScreenX() + xoffset);
				stage1.setY(mouseEvent.getScreenY() + yoffset);
			}
		});
		Phieutrahang selected = tablePhieuTraHang.getSelectionModel().getSelectedItem();
		TrahangDetailController Trahang = loader.getController();
	//	Trahang.setPhieutrahang(selected);
		if(selected == null) {
			 thongbaoTH.setVisible(true);
			 thongbaoTH.setText("Không có phiếu trả hàng được chọn!!!");
			 System.out.print("Không có phiếu trả hàng được chọn!!!");
			 return;
		 }
		 else if(selected != null){
			 Trahang.setPhieutrahang(selected);
			 thongbaoTH.setVisible(false);
			 
		 }
		stage1.setTitle("Chi tiet phieu tra");
		stage1.initStyle(StageStyle.UNDECORATED);
		stage1.setScene(scene1);
		stage1.show();
		GiaoDienQLController.getInstance().truedisable();
	}

	@FXML
	private Text txtTitle_store;

	// PHIẾU TRẢ HÀNG
	public ObservableList<Phieutrahang> getPhieutrahang() {
		ObservableList<Phieutrahang> tablePhieuTraHang = FXCollections.observableArrayList();
		 Session session = HibernateUtils.getSessionFactory().openSession();

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		mahoadon.setCellValueFactory(new PropertyValueFactory<Hoadon, String>("mahoadon"));
		thoigianmua.setCellValueFactory(new PropertyValueFactory<Hoadon, String>("thoigianmua"));
		tonggia.setCellValueFactory(new PropertyValueFactory<Hoadon, Integer>("tonggia"));
		makh.setCellFactory(tableHoaDon -> new TableCell<Hoadon, KhachHang>() {
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
		manv1.setCellFactory(tableHoaDon -> new TableCell<Hoadon, Nhanvien>() {
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
		/*
		 * makh.setCellFactory(tv -> new TableCell<>() {
		 * 
		 * @Override protected void updateItem(Hoadon item, boolean empty) {
		 * super.updateItem(item, empty); if (empty || item == null) { setText(null); }
		 * else { setText(item.getKhachhang()); } }
		 * 
		 * });
		 */

		tableHoaDon.setItems(getHoadon());
		searchPHD();
		// tableHoaDon.setItems(listPHD);

		// QL danh mục phiếu đặt hàng //Nhi
		madathang.setCellValueFactory(new PropertyValueFactory<Phieudathang, String>("madathang"));
		thoigiandat.setCellValueFactory(new PropertyValueFactory<Phieudathang, String>("thoigian"));
		tongtien1.setCellValueFactory(new PropertyValueFactory<Phieudathang, Integer>("tongtien"));
		mancc.setCellValueFactory(new PropertyValueFactory<>("nhacungcap"));
		mancc.setCellFactory(tablePhieuDatHang -> new TableCell<Phieudathang, Nhacungcap>() {
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
		/*
		 * manv.setCellValueFactory(new PropertyValueFactory<>("nhanvien"));
		 * manv.setCellFactory(tablePhieuDatHang ->new
		 * TableCell<Phieudathang,Nhanvien>(){
		 * 
		 * @Override protected void updateItem(Nhanvien item, boolean empty) {
		 * super.updateItem(item, empty); if (empty || item == null) { setText(null); }
		 * else { setText(String.valueOf(item.getManv())); } } });
		 */
		tablePhieuDatHang.setItems(getPhieudathang());
		searchPDH();

		// QL danh mục phiếu nhập hàng // Nhi
		manhaphang.setCellValueFactory(new PropertyValueFactory<Phieunhaphang, Integer>("manhaphang"));
		thoigiannhap.setCellValueFactory(new PropertyValueFactory<Phieunhaphang, String>("thoigian"));
		tongtien.setCellValueFactory(new PropertyValueFactory<Phieunhaphang, Integer>("tongtien"));
		mancc2.setCellValueFactory(new PropertyValueFactory<>("nhacungcap"));
		mancc2.setCellFactory(tablePhieuNhapHang -> new TableCell<Phieunhaphang, Nhacungcap>() {
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
		manv2.setCellValueFactory(new PropertyValueFactory<>("nhanvien"));
		manv2.setCellFactory(tablePhieuNhapHang -> new TableCell<Phieunhaphang, Nhanvien>() {
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

		tablePhieuNhapHang.setItems(getPhieunhaphang());
		maphieutra.setCellValueFactory(new PropertyValueFactory<Phieutrahang, String>("maphieutra"));
		thoigiantra.setCellValueFactory(new PropertyValueFactory<Phieutrahang, String>("thoigian"));
		mancc3.setCellValueFactory(new PropertyValueFactory<>("nhacungcap"));
		mancc3.setCellFactory(tablePhieuTraHang -> new TableCell<Phieutrahang, Nhacungcap>() {
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
		tablePhieuTraHang.setItems(getPhieutrahang());
		searchPTH();
		searchPNH();
	}
}

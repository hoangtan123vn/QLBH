package QLKho;
import QLBH.Sanpham;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.query.Query;
import org.hibernate.type.descriptor.sql.NVarcharTypeDescriptor;

import QLBH.GiaoDienQLController;
import QLBH.HibernateUtils;
import QLBH.Nhanvien;
import QLBH.Phieudathang;
import QLBH.Taikhoannv;


import javax.imageio.ImageIO;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.derby.vti.Restriction.AND;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Callback;



public class QLKhoController implements Initializable {
	
	@FXML
	private TableColumn donvitinh;

	@FXML
	private TableColumn CapNhat;

	@FXML
	private TableColumn imageSp;

	@FXML
	private Button themsanpham;

	@FXML
	private TableColumn<Sanpham, Void> xoasp;
	
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
	private TextField Timkiem;

	ObservableList<Sanpham> listM;

	@FXML
	private Button lapphieudathang;

	@FXML
	private TextField tfloaisanpham;

	@FXML
	private Text idsanpham;
	@FXML
	private TableView<Sanpham> tableSP;
	
	public void falsedisable() {
		anchorpane1.setDisable(false);
	}
	public void truedisable() {
		anchorpane1.setDisable(true);
	}
	

	
	 public static QLKhoController instance;

		public QLKhoController() {
			instance = this;
		}

		public static QLKhoController getInstance() {
			return instance;
		}


	ObservableList<Sanpham> table1 = FXCollections.observableArrayList(getSanpham());
 
    
    @FXML
	void ThemSP(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/QLKho/themsanpham.fxml"));
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setScene(scene);
		stage.show();
		//truedisable();
		GiaoDienQLController.getInstance().truedisable();
		
	}
    @FXML
	void reload(ActionEvent event) {

		initialize1();
	//	getNhanvien();
	}
    private void ButtonXoaSP() {

		Callback<TableColumn<Sanpham, Void>, TableCell<Sanpham, Void>> cellFactory = new Callback<TableColumn<Sanpham, Void>, TableCell<Sanpham, Void>>() {
			@Override
			public TableCell<Sanpham, Void> call(final TableColumn<Sanpham, Void> param) {
				final TableCell<Sanpham, Void> cell = new TableCell<Sanpham, Void>() {

					private final Button btn = new Button("Xóa Sản Phẩm");

					{
						
						///////////////////////////
						btn.setOnAction((ActionEvent event) -> {
							Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
							Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
							alert.setTitle("Xóa Sản Phẩm");
							alert.setContentText("Bạn có chắc xóa sản phẩm này ?");
							ButtonType okButton = new ButtonType("Yes");
							ButtonType noButton = new ButtonType("NO");
							alert.getButtonTypes().setAll(okButton, noButton);
							alert.showAndWait().ifPresent(type -> {
								if (type == okButton) {
									Sanpham sp = getTableView().getItems().get(getIndex());
									StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
											.configure("hibernate.cfg.xml").build();
									Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder()
											.build();
									SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
									Session session = sessionFactory.openSession();
									sp = session.get(Sanpham.class, sp.getMasanpham());
									try {
										session.beginTransaction();
										if (sp != null) {
											session.delete(sp);

										}
										session.getTransaction().commit();
										ReloadSANPHAM();

										alert1.setContentText("Xóa sản phẩm thành công");
										alert1.showAndWait();

									} catch (RuntimeException error) {
										session.getTransaction().rollback();
									}

								} else if (type == ButtonType.NO) {
									alert.close();
								}

							});
						});
					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btn);
						}
					}
				};
				return cell;
			}
		};
		xoasp.setCellFactory(cellFactory);
	}

	private void ButtonChinhSuaAnh() {
		// TableColumn<Sa, Void> colBtn = new TableColumn("Button Column");
		

		Callback<TableColumn<Sanpham, Void>, TableCell<Sanpham, Void>> cellFactory = new Callback<TableColumn<Sanpham, Void>, TableCell<Sanpham, Void>>() {
			@Override
			public TableCell<Sanpham, Void> call(final TableColumn<Sanpham, Void> param) {
				final TableCell<Sanpham, Void> cell = new TableCell<Sanpham, Void>() {

					private final Button btn = new Button("Cập nhật sản phẩm");

					{
						///////////////////////////
						btn.setOnAction((ActionEvent event) -> {
							Parent root;
							try {
								FXMLLoader loader = new FXMLLoader(getClass().getResource("/QLKho/detailsanpham.fxml"));
								Parent CapnhatSP = loader.load();
								Stage stage = new Stage();
								Scene scene = new Scene(CapnhatSP);
								// Hoadon selected = tableHoaDon.getSelectionModel().getSelectedItem();
								Sanpham sp = getTableView().getItems().get(getIndex());
							    QLKho.detailsanphamcontroller CapNhatSP = loader.getController();
								CapNhatSP.setSanPham(sp);
								stage.setTitle("Cập nhật sản phẩm");
								stage.setScene(scene);
								stage.show();
							} catch (IOException e) {

								e.printStackTrace();
								
							}

						});

					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btn);
						}
					}
				};
				return cell;
			}
		};
		CapNhat.setCellFactory(cellFactory);
	}
	
	
	void initialize1() {
		// setCellValueFromTabletoTexfFieldd();
		tensanpham.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("tensanpham"));
		masanpham.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("masanpham"));
		// loai.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("loai"));
		donvi.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("donvi"));
		giatien.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("giatien"));
		donvitinh.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("donvitinh"));
		loaisanpham.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("loaisanpham"));
		ButtonXoaSP();
		ButtonChinhSuaAnh();
		imageSp.setCellValueFactory(new PropertyValueFactory<Sanpham, Byte>("imagesp"));
	//	imageSp.setPrefWidth(50);
//		imageSp.setPrefWidth
		imageSp.setCellFactory(param -> new TableCell<Sanpham, byte[]>() {

			private ImageView imageView = new ImageView();

			@Override
			protected void updateItem(byte[] item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null || empty) {
					setText(null);
					setGraphic(null);
				} else {
					imageView.setImage(getImageFromBytes(item));
					imageView.setFitHeight(65);
					imageView.setFitWidth(65);
					
					BorderPane pane = new BorderPane();
					pane.setCenter(imageView);
					setGraphic(pane);
				}
				this.setItem(item);
			}
		});
		// imageSp.setPrefWidth(70);
		tableSP.setItems(getSanpham());
		Timkiem();

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
	private Button idluusp;

	public ObservableList<Sanpham> getSanpham() {
		ObservableList<Sanpham> TableSP = FXCollections.observableArrayList();
		 Session session = HibernateUtils.getSessionFactory().openSession();

		CriteriaQuery<Sanpham> sp = session.getCriteriaBuilder().createQuery(Sanpham.class);
		sp.from(Sanpham.class);
		List<Sanpham> eList = session.createQuery(sp).getResultList();
		for (Sanpham ent : eList) {
			TableSP.add(ent);
		}
		return TableSP;
	}

	void ReloadSANPHAM() {
		initialize1();
		//tableSP.setItems(getSanpham());

		System.out.println("Thanh cong");
	}
	public void loadData(Taikhoannv taikhoan){
		lapphieudathang.setOnMouseClicked(event ->  {
			 ChangeSceneLapPhieu(taikhoan);
			 System.out.println(taikhoan);
		 });
	}
	public void ChangeSceneLapPhieu(Taikhoannv taikhoan)  {
	try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("lapphieudathang.fxml"));
		Parent kiemtraViewParent = loader.load();
		Stage stage1 = new Stage();
		Scene scene1 = new Scene(kiemtraViewParent);
		//Phieudathang selected = phieudathangkt.getSelectionModel().getSelectedItem();
		LapPhieuDatHangController lapPhieuDatHangController = loader.getController();
		lapPhieuDatHangController.loadData(taikhoan);
		stage1.initStyle(StageStyle.UNDECORATED);
		stage1.setTitle("Lap phieu dat hang");
		stage1.setScene(scene1);
		stage1.show();
		GiaoDienQLController.getInstance().truedisable();
	} catch (Exception e) {
		System.out.println(e);
	}
	}
    
  
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		initialize1();
	}

}

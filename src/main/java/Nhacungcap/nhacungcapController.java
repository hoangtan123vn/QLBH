package Nhacungcap;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import QLBH.Chitiethoadon;
import QLBH.HibernateUtils;
import QLBH.Hoadon;
import QLBH.KhachHang;
import QLBH.Nhacungcap;
import QLBH.Nhanvien;
import QLBH.Sanpham;
import QLBH.chucnangquanly;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
public class nhacungcapController implements Initializable{

    @FXML
    private TableView<Nhacungcap> tableNhacungcap;

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
    private TableColumn deleteNCC;

    @FXML
    private Button idreloadncc;

    @FXML
    private Button idluuncc;

    @FXML
    private Button taonhacungcap;

    @FXML
    private Button thanhtoancongno;

    @FXML
    private TextField timkiem;
    
    @FXML
	private ComboBox<String> cbb;

	private ObservableList<Nhacungcap> nhacungcapdata;
	
	public static nhacungcapController instance;

	public nhacungcapController() {
		instance = this;
	}

	public static nhacungcapController getInstance() {
		return instance;
	}
	
	
	public ObservableList<Nhacungcap> getNhacungcap() {
		ObservableList<Nhacungcap> TableNhacungcap = FXCollections.observableArrayList();
		 Session session = HibernateUtils.getSessionFactory().openSession();

		CriteriaQuery<Nhacungcap> NCC = session.getCriteriaBuilder().createQuery(Nhacungcap.class);
		NCC.from(Nhacungcap.class);
		List<Nhacungcap> eList = session.createQuery(NCC).getResultList();
		// List<Nhanvien> eList = session.createQuery(criteriaQuery).getResultList();
		// List<Nhanvien> eList = session.createQuery(Nhanvien.class).list();
		for (Nhacungcap ent : eList) {
			TableNhacungcap.add(ent);
		}
		return TableNhacungcap;
	}

	public void ReloadNHACUNGCAP() {
		tenncc.setCellValueFactory(new PropertyValueFactory<Nhacungcap, String>("tenncc"));

		diachi1.setCellValueFactory(new PropertyValueFactory<Nhacungcap, String>("diachi"));

		sodienthoai.setCellValueFactory(new PropertyValueFactory<Nhacungcap, Integer>("sodienthoai"));

		sotienno.setCellValueFactory(new PropertyValueFactory<Nhacungcap, Integer>("sotienno"));

		email.setCellValueFactory(new PropertyValueFactory<Nhacungcap, String>("email"));
		tableNhacungcap.setItems(getNhacungcap());
		search();

	}

	@FXML
	void Taonhacungcap(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Nhacungcap/taonhacungcap.fxml"));
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
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/Nhacungcap/thanhtoancongno.fxml"));
			Parent root1 = (Parent) loader.load();
			Stage stage = new Stage();
			ThanhtoanCNcontroller controller = loader.getController();
			Nhacungcap selected = tableNhacungcap.getSelectionModel().getSelectedItem();
			controller.setThanhtoan(selected);
			stage.setScene(new Scene(root1));
			stage.setTitle("thanh toán công nợ");
			stage.show();

			//

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	@FXML
	void Reloadncc(ActionEvent event) {
		ReloadNHACUNGCAP();
	}
	
	void search() {
		ObservableList<Nhacungcap> table = FXCollections.observableArrayList(getNhacungcap());
		FilteredList<Nhacungcap> filterData = new FilteredList<>(table, p -> true);
		timkiem.textProperty().addListener((observable, oldvalue, newvalue) -> {
			filterData.setPredicate(ncc -> {
				if (newvalue == null || newvalue.isEmpty()) {
					return true;
				}
				String typetext = newvalue.toLowerCase();
				if (ncc.getTenncc().toLowerCase().indexOf(typetext) != -1) {
					return true;
				}
				if (ncc.getEmail().toLowerCase().indexOf(typetext) != -1) {
					return true;
				}
				return false;

			});
			SortedList<Nhacungcap> sortedList = new SortedList<>(filterData);
			sortedList.comparatorProperty().bind(tableNhacungcap.comparatorProperty());
			tableNhacungcap.setItems(sortedList);
		});
	}
	
	
	
    
    private void ButtonXoaNCC() {

		Callback<TableColumn<Nhacungcap, Void>, TableCell<Nhacungcap, Void>> cellFactory = new Callback<TableColumn<Nhacungcap, Void>, TableCell<Nhacungcap, Void>>() {
			@Override
			public TableCell<Nhacungcap, Void> call(final TableColumn<Nhacungcap, Void> param) {
				final TableCell<Nhacungcap, Void> cell = new TableCell<Nhacungcap, Void>() {

					private final Button btncc = new Button("Xóa Nhà Cung Cấp");

					{
						///////////////////////////
						btncc.setOnAction((ActionEvent event) -> {
							Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
							Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
							alert.setTitle("Xóa Nhà Cung Cấp");
							alert.setContentText("Bạn có chắc xóa nhà cung cấp này ?");
							ButtonType okButton = new ButtonType("Yes");
							ButtonType noButton = new ButtonType("NO");
							alert.getButtonTypes().setAll(okButton, noButton);
							alert.showAndWait().ifPresent(type -> {
								if (type == okButton) {
									Nhacungcap ncc = getTableView().getItems().get(getIndex());
									StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
											.configure("hibernate.cfg.xml").build();
									Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder()
											.build();
									SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
									Session session = sessionFactory.openSession();
									ncc = session.get(Nhacungcap.class, ncc.getMancc());
									try {
										session.beginTransaction();
										if (ncc != null) {
											session.delete(ncc);

										}
										session.getTransaction().commit();
										ReloadNHACUNGCAP();

										alert1.setContentText("Xóa nhà cung cấp thành công");
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
							setGraphic(btncc);
						}
					}
				};
				return cell;
			}
		};
		deleteNCC.setCellFactory(cellFactory);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		tenncc.setCellValueFactory(new PropertyValueFactory<Nhacungcap, String>("tenncc"));
		tenncc.setCellFactory(TextFieldTableCell.forTableColumn());
		tenncc.setOnEditCommit(new EventHandler<CellEditEvent<Nhacungcap, String>>() {
			@Override
			public void handle(CellEditEvent<Nhacungcap, String> event) {
				StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
						.configure("hibernate.cfg.xml").build();
				Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
				SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				Nhacungcap person = new Nhacungcap();
				person = event.getRowValue();
				person = session.get(Nhacungcap.class, person.getMancc());
				person.setTenncc(event.getNewValue());
				session.save(person);
				session.getTransaction().commit();
				ReloadNHACUNGCAP();
			}
		});

		diachi1.setCellValueFactory(new PropertyValueFactory<Nhacungcap, String>("diachi"));
		diachi1.setCellFactory(TextFieldTableCell.forTableColumn());
		diachi1.setOnEditCommit(new EventHandler<CellEditEvent<Nhacungcap, String>>() {
			@Override
			public void handle(CellEditEvent<Nhacungcap, String> event) {
				StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
						.configure("hibernate.cfg.xml").build();
				Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
				SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				Nhacungcap person = new Nhacungcap();
				person = event.getRowValue();
				person = session.get(Nhacungcap.class, person.getMancc());
				person.setDiachi(event.getNewValue());
				session.save(person);
				session.getTransaction().commit();
				ReloadNHACUNGCAP();
			}
		});

		sodienthoai.setCellValueFactory(new PropertyValueFactory<Nhacungcap, Integer>("sodienthoai"));

		sodienthoai.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		sodienthoai.setOnEditCommit(new EventHandler<CellEditEvent<Nhacungcap, Integer>>() {
			@Override
			public void handle(CellEditEvent<Nhacungcap, Integer> event) {
				StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
						.configure("hibernate.cfg.xml").build();
				Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
				SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				Nhacungcap person = new Nhacungcap();
				person = event.getRowValue();
				person = session.get(Nhacungcap.class, person.getMancc());
				person.setSodienthoai(event.getNewValue());
				session.save(person);
				session.getTransaction().commit();
				ReloadNHACUNGCAP();
			}
		});

		sotienno.setCellValueFactory(new PropertyValueFactory<Nhacungcap, Integer>("sotienno"));

		sotienno.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		sotienno.setOnEditCommit(new EventHandler<CellEditEvent<Nhacungcap, Integer>>() {
			@Override
			public void handle(CellEditEvent<Nhacungcap, Integer> event) {
				StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
						.configure("hibernate.cfg.xml").build();
				Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
				SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				Nhacungcap person = new Nhacungcap();
				person = event.getRowValue();
				person = session.get(Nhacungcap.class, person.getMancc());
				person.setSotienno(event.getNewValue());
				Integer i = (Integer) session.save(person);
				System.out.println("Mã định danh đã tạo:" + i);
				session.getTransaction().commit();
				ReloadNHACUNGCAP();
			}
		});

		email.setCellValueFactory(new PropertyValueFactory<Nhacungcap, String>("email"));

		email.setCellFactory(TextFieldTableCell.forTableColumn());
		email.setOnEditCommit(new EventHandler<CellEditEvent<Nhacungcap, String>>() {
			@Override
			public void handle(CellEditEvent<Nhacungcap, String> event) {
				StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
						.configure("hibernate.cfg.xml").build();
				Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
				SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				Nhacungcap person = new Nhacungcap();
				person = event.getRowValue();
				person = session.get(Nhacungcap.class, person.getMancc());
				person.setEmail(event.getNewValue());
				session.save(person);
				session.getTransaction().commit();
				ReloadNHACUNGCAP();
			}
		});

		// deleteNCC.setCellValueFactory(new PropertyValueFactory<Nhacungcap,
		// Button>("xoa"));
		ButtonXoaNCC();
		tableNhacungcap.setItems(getNhacungcap());
		tableNhacungcap.setEditable(true);
		search();
	}

}

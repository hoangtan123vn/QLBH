package QLKho;

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
import javafx.scene.control.Label;
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
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.criteria.CriteriaQuery;


import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import com.sun.xml.bind.v2.util.QNameMap.Entry;

import BanHang.timkhachhangController;

import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;

import QLBH.Chitietdathang;
import QLBH.Chitiethoadon;
import QLBH.HibernateUtils;
import QLBH.Hoadon;
import QLBH.Nhacungcap;
import QLBH.Nhanvien;
import QLBH.Phieudathang;
import QLBH.Sanpham;
import QLBH.Taikhoannv;
import QLBH.Phieudathang;
public class LapPhieuDatHangController implements Initializable{
	
	@FXML
	private Button huy;
	
	@FXML
	private TableColumn donvitinh;

	@FXML
	private TableColumn donvi;
	
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
	private TableColumn Tongtien;
	
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
    private Label thongbao4;
	
	@FXML
    private Label thongbao5;
	
	@FXML
    private Label thongbao6;
	
	@FXML
    private Label thongbaoo;
	 
	 @FXML
	 private Button idDatHang;
	 
	    @FXML
	    private TextField tongtien;

	    @FXML
	    private Button lapphieu;
	    
	    
	    @FXML
	    private ComboBox<String> cbbthanhtoan;
	 
	
	ObservableList<Sanpham> table = FXCollections.observableArrayList(getSanpham());

	@FXML
	private TableView<Sanpham> tableSP;
	
	@FXML
	private TableView<Chitietdathang> tableSP1;
	
	
	@FXML
    private Text name_ncc;

    @FXML
    private Text id_ncc;
    
    @FXML
	private TableColumn<Chitietdathang, Void> xoasp;
	
	
	 @FXML
	    private Button nhacungcap;
	 
	 @FXML
	    void ChonNCC(ActionEvent event) {
		 ListNhaCungCapController controller2 = new ListNhaCungCapController(this);

	        // Show the new stage/window
	        controller2.showStage();
	    }
	 
	 
    public void setNhaCungCap(Nhacungcap nhacungcap) {
    	id_ncc.setText(String.valueOf(nhacungcap.getMancc()));
    	name_ncc.setText(String.valueOf(nhacungcap.getTenncc()));
    }
    
    
	@FXML
	void CanCel(ActionEvent event) {
		Stage stage = (Stage) huy.getScene().getWindow();
   	   	stage.close();
   	   	QLKhoController.getInstance().falsedisable();
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
			 //tf5.setText(Integer.toString(sp.getGiatien()));
			 //tf5.setText(sp.getDonvitinh()); 
			 tf6.setText(sp.getLoaisanpham());
		 });
	 }
/*	 public ObservableList<String> getNhaCungCap() {
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
	 }	*/
	
	 private void ButtonXoaSP() {
	      

		 Callback<TableColumn<Chitietdathang, Void>, TableCell<Chitietdathang, Void>> cellFactory = new Callback<TableColumn<Chitietdathang, Void>, TableCell<Chitietdathang, Void>>() {
	            @Override
	            public TableCell<Chitietdathang, Void> call(final TableColumn<Chitietdathang, Void> param) {
	                final TableCell<Chitietdathang, Void> cell = new TableCell<Chitietdathang, Void>() {
	                    private final Button btn = new Button("Xóa");

	                    {
	                    	///////////////////////////
	                    btn.setOnAction((ActionEvent event) -> {
	                    	Chitietdathang chitietdathang = getTableView().getItems().get(getIndex());
	                    	 if(tableSP1.getItems().contains(chitietdathang)) {
	                    	 chitietdathang.setSoluong(chitietdathang.getSoluong() - chitietdathang.getSoluong());
	               			 tableSP1.refresh();
	               			 if(chitietdathang.getSoluong()==0) {
	               				 tableSP1.getItems().remove(chitietdathang);
	               				 tableSP1.refresh();
	               			 }
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
	        xoasp.setCellFactory(cellFactory);
	 }
	 
	 @FXML
	    void DatHang(ActionEvent event) {
		 if(KiemTraGia()&KiemTraSL()) {
			 Sanpham sp = tableSP.getItems().get(tableSP.getSelectionModel().getSelectedIndex());
			 String tensanpham = tf1.getText();
			 int masanpham = Integer.parseInt(tf2.getText());
			 String donvi = tf3.getText();
			 String loaisanpham = tf6.getText();
			 int donvitinh = Integer.parseInt(tf4.getText());
			 int giatien = Integer.parseInt(tf5.getText());
			 addItem(donvitinh,sp,0.0);
			 tableSP1.refresh();
			 tf4.setText(null);
			 tf5.setText(null);
	    	}
		
		// String nhacungcap = cbnhacungcap.getValue();
		 
	    }
	 public void addItem(int soluong,Sanpham sanpham,double thanhtien) {
		    Chitietdathang entry = tableSP1.getItems().stream()
		        .filter(item -> item.getSanpham().equals(sanpham))
		        .findAny()
		        .orElseGet(()-> {
		           //  Sanpham newItem = new Sanpham(masanpham,tensanpham, loaisanpham,donvi,0, 0);
		            Chitietdathang newItem = new Chitietdathang(0,sanpham,thanhtien);
		        	tableSP1.getItems().add(newItem);
		            return newItem;
		        });
		    int giatien = Integer.parseInt(tf5.getText());
		    entry.setSoluong(soluong + entry.getSoluong());
		    entry.getSanpham().setGiatien(giatien);
		    entry.setThanhtien(entry.getSanpham().getGiatien()*entry.getSoluong());
		    int sum = 0;
	        for (Chitietdathang chitietdathang : tableSP1.getItems()) {
	            sum = sum + (chitietdathang.getSanpham().getGiatien()*chitietdathang.getSoluong());
	        }
	        tongtien.setText(String.valueOf(sum));
		}
	 

public void loadData(Taikhoannv taikhoan) {
		 int manhanvien = taikhoan.getNhanvien().getManv();
		 lapphieu.setOnMouseClicked(event ->  {
			 if(KiemTraPT()& KiemTraNCC()) {
			 }
			 Alert alert = new Alert(AlertType.INFORMATION);
			 Session session = HibernateUtils.getSessionFactory().openSession();
			int manv = taikhoan.getNhanvien().getManv();
			int tongtienphieudathang = Integer.parseInt(tongtien.getText());
			//String tennhacc = cbnhacungcap.getValue();
			//Nhacungcap ncc = new Nhacungcap(tennhacc);
			Nhanvien nhanvien = new Nhanvien();
			nhanvien = session.get(Nhanvien.class,manv);
			LocalDateTime thoigiandat = LocalDateTime.now();
			int ma_ncc = Integer.parseInt(id_ncc.getText());
			Nhacungcap ncc = new Nhacungcap();
			ncc = session.get(Nhacungcap.class, ma_ncc);
			Phieudathang phieudathang = new Phieudathang(thoigiandat,tongtienphieudathang,ncc,nhanvien);
			if(cbbthanhtoan.getValue() == "Trực tiếp" ) {
				//System.out.println("Thanh cong");
				try {
					session.beginTransaction();
					session.save(phieudathang);
					session.getTransaction().commit();
				//	alert.setContentText("Thêm phieu dat hang thành công !");
		    	//	 alert.showAndWait();  
				} catch (Exception e) {
					// TODO: handle exception
					 alert.setContentText("Lỗi " + e);
					 alert.showAndWait();
			    	 session.getTransaction().rollback();
				}
				for (Chitietdathang chitietdathang : tableSP1.getItems()) {
					Sanpham spp= new Sanpham();
					int masp = chitietdathang.getSanpham().getMasanpham();
					spp= session.get(Sanpham.class, masp);
					int soluong = chitietdathang.getSoluong();
					double thanhtien = chitietdathang.getThanhtien();
					 phieudathang = session.get(Phieudathang.class, phieudathang.getMadathang());
					Chitietdathang chitietdathang1 = new Chitietdathang(phieudathang,spp,soluong,thanhtien);
					 try {
			    		 session.beginTransaction();
			    	//	 System.out.println(phieudathang.getMadathang() + "!23");
			    		 session.save(chitietdathang1);
			    		// session.save(spp);
			    		 session.getTransaction().commit();
			    		 alert.setContentText("Lập phiếu đặt hàng thành công ! ! ! ");
			    		 alert.showAndWait();   
					 }
				    	catch (RuntimeException error){
				    		 System.out.println(error);
				    		 alert.setContentText("Lập phiếu đặt hàng thất bại  !");
				    		 alert.showAndWait();
				    		 session.getTransaction().rollback();
				    	}
					}
			}
			else if(cbbthanhtoan.getValue() == "Tạo công nợ") {
				Nhacungcap nhacungcap = new Nhacungcap();
				nhacungcap= session.get(Nhacungcap.class, ma_ncc);
				try {
					
					session.beginTransaction();
					session.save(phieudathang);
					nhacungcap.setSotienno(nhacungcap.getSotienno()+Integer.parseInt(String.valueOf(tongtienphieudathang)));
					session.save(nhacungcap);
					session.getTransaction().commit();
					alert.setContentText("Cập nhật công nợ thành công  ");
		    		 alert.showAndWait(); 
				//	alert.setContentText("Thêm phieu dat hang thành công !");
		    	//	 alert.showAndWait();  
		    		 
				} catch (Exception e) {
					// TODO: handle exception
					 alert.setContentText("Lỗi " + e);
					 alert.showAndWait();
			    	 session.getTransaction().rollback();
				}
				for (Chitietdathang chitietdathang : tableSP1.getItems()) {
					Sanpham spp= new Sanpham();
					int masp = chitietdathang.getSanpham().getMasanpham();
					spp= session.get(Sanpham.class, masp);
					int soluong = chitietdathang.getSoluong();
					double thanhtien = chitietdathang.getThanhtien();
					 phieudathang = session.get(Phieudathang.class, phieudathang.getMadathang());
					Chitietdathang chitietdathang1 = new Chitietdathang(phieudathang,spp,soluong,thanhtien);
					 try {
			    		 session.beginTransaction();
			    	//	 System.out.println(phieudathang.getMadathang() + "!23");
			    		 session.save(chitietdathang1);
			    		// session.save(spp);
			    		 session.getTransaction().commit();
			    		  
					 }
				    	catch (RuntimeException error){
				    		 System.out.println(error);
				    		 alert.setContentText("Lập phiếu đặt hàng thất bại  !");
				    		 alert.showAndWait();
				    		 session.getTransaction().rollback();
				    	}
					 
					}
				alert.setContentText("Lập phiếu đặt hàng thành công ! ! ! ");
	    		 alert.showAndWait();  
			}
			else {
				 alert.setContentText("bạn phải chọn phương thức thanh toán  !");
				 alert.showAndWait();
			}
			
		 });
	 }
private boolean KiemTraNCC() {
    Pattern p = Pattern.compile("^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ ]+$");
    Matcher m = p.matcher(name_ncc.getText());
	if(m.find() && m.group().equals(name_ncc.getText())){
        thongbaoo.setText(null);
        return true;
    }
    else {
        thongbaoo.setText("Vui lòng chọn nhà cung cấp");
        return false;
    }
}
private boolean KiemTraPT() {
    if( cbbthanhtoan.getValue() == null){
        thongbao6.setText("Chưa chọn phương thức thanh toán");
        return false;
    }
    	thongbao6.setText(null);
    return true;

}

private boolean KiemTraSL() {
    Pattern p = Pattern.compile("[0-9]+");
    Matcher m = p.matcher(tf4.getText());
    if(m.find() && m.group().equals(tf4.getText())){
        thongbao4.setText(null);
        return true;
    }
    else {
        thongbao4.setText("Vui lòng điền số");
        return false;
    }
}
private boolean KiemTraGia() {
    Pattern p = Pattern.compile("[0-9]+");
    Matcher m = p.matcher(tf5.getText());
    if(m.find() && m.group().equals(tf5.getText())){
        thongbao5.setText(null);
        return true;
    }
    else {
        thongbao5.setText("Vui lòng điền số");
        return false;
    }
}
			@Override
			public void initialize(URL url, ResourceBundle rb) {
				ObservableList<String> list = FXCollections.observableArrayList("Trực tiếp", "Tạo công nợ");
				cbbthanhtoan.setItems(list);
				setCellValueFromTabletoTexfFieldd();
				tensanpham.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("tensanpham"));
				masanpham.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("masanpham"));
				//loaisanpham.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("loai"));
				donvi.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("donvi"));
				//giatien.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("giatien"));
				donvitinh.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("donvitinh"));
			//	comboboxNCC();
				
				tensanpham1.setCellValueFactory(new PropertyValueFactory<>("sanpham"));
				tensanpham1.setCellFactory(tableSP1 -> new TableCell<Chitietdathang, Sanpham>() {
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
				
				
				masanpham1.setCellValueFactory(new PropertyValueFactory<>("sanpham"));
				masanpham1.setCellFactory(tableSP1 -> new TableCell<Chitietdathang, Sanpham>() {
					@Override
					protected void updateItem(Sanpham item, boolean empty) {
						super.updateItem(item, empty);
						if (empty || item == null) {
							setText(null);
						} else {
							setText(String.valueOf(item.getMasanpham()));
						}
					}

				});
				loaisanpham1.setCellValueFactory(new PropertyValueFactory<>("sanpham"));
				loaisanpham1.setCellFactory(tableSP1 -> new TableCell<Chitietdathang, Sanpham>() {
					@Override
					protected void updateItem(Sanpham item, boolean empty) {
						super.updateItem(item, empty);
						if (empty || item == null) {
							setText(null);
						} else {
							setText(String.valueOf(item.getLoaisanpham()));
						}
					}

				});
				donvi1.setCellValueFactory(new PropertyValueFactory<>("sanpham"));
				donvi1.setCellFactory(tableSP1 -> new TableCell<Chitietdathang, Sanpham>() {
					@Override
					protected void updateItem(Sanpham item, boolean empty) {
						super.updateItem(item, empty);
						if (empty || item == null) {
							setText(null);
						} else {
							setText(String.valueOf(item.getDonvi()));
						}
					}

				});
				
				giatien1.setCellValueFactory(new PropertyValueFactory<>("sanpham"));
				giatien1.setCellFactory(tableSP1 -> new TableCell<Chitietdathang, Sanpham>() {
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
				donvitinh1.setCellValueFactory(new PropertyValueFactory<Chitietdathang,Integer>("soluong"));
				Tongtien.setCellValueFactory(new PropertyValueFactory<Chitietdathang,Double>("thanhtien"));
				tableSP.setItems(getSanpham());
				Timkiem();
				ButtonXoaSP();
			}
	
	}	


package KiemTraHang;
import javafx.fxml.FXML;
import org.apache.derby.iapi.store.access.SpaceInfo;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import net.bytebuddy.implementation.bytecode.Addition;
import QLBH.Hoadon;
import QLBH.Sanpham;
import QLBH.Taikhoannv;
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
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javax.crypto.MacSpi;
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

import com.sun.javafx.scene.control.SelectedCellsMap;
import com.sun.xml.bind.v2.util.EditDistance;

import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;


import QLBH.Nhanvien;
import QLBH.Phieudathang;
import QLBH.Chitietnhaphang;
import QLBH.Chitietphieutra;
import QLBH.HibernateUtils;
import QLBH.Phieutrahang;
import QLBH.Chitietdathang;
import QLBH.Nhacungcap;
import QLBH.Phieunhaphang;
public class NhapHangvaTraHang implements Initializable{
	 @FXML
	    private TableView<Chitietdathang> tableChitietKiemtra;

	    @FXML
	    private Label madathangkt;

	    @FXML
	    private Label mancckt;

	    @FXML
	    private Label thoigiandatkt;
	   @FXML 
	   private Label tongtien;

	    @FXML
	    private TableColumn<Chitietdathang,Sanpham> tenhang;

	    @FXML
	    private TableColumn soluong;

	    @FXML
	    private TableColumn dongia;
	    
	    @FXML
	    private Button select;

	    @FXML
	    private Button btphieunhap;

	    @FXML
	    private Button btphieutra;
	    
	    @FXML
	    private TextField lydo;
	    
	    @FXML
	    private TableColumn choice;
	    
	    @FXML
	    private TableColumn <Chitietdathang,Sanpham>mahang;
	    
	    @FXML
	    private TableView<Chitietphieutra> tbtrahang;
	    
	    @FXML
	    private TableView<Chitietnhaphang> tbnhaphang;
	    
	    @FXML
	    private TableColumn idtensp2;

	    @FXML
	    private TableColumn idmasp2;

	    @FXML
	    private TableColumn idsoluong2;

	    @FXML
	    private TableColumn iddongia2;
	    @FXML
	    private TableColumn idthanhtien2;

	    @FXML
	    private TableColumn xoasp2;

	    @FXML
	    private TableColumn <Chitietnhaphang,Sanpham>idtensp1;

	    @FXML
	    private TableColumn idmasp1;
	    
	    @FXML
	    private TableColumn idthanhtien1;

	    @FXML
	    private TableColumn xoasp1;

	    @FXML
	    private TableColumn idsoluong1;

	    @FXML
	    private TableColumn iddongia1;
	    @FXML
	    private Button btntra;

	    @FXML
	    private Button btnnhap;
	    
	    @FXML
	    void nhap(ActionEvent event) {
	    	event();
	    }

	    @FXML
	    void tra(ActionEvent event) {
	    	event2();
	    
	    }

	    
	  //  ObservableList<String> CheckBoxList = FXCollections.observableArrayList();    
	    @FXML
	    void selected(ActionEvent event)  {
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

	    public ObservableList<Chitietdathang> getChitietdathang(Phieudathang phieudathang) {
	    	int Phieudathang = phieudathang.getMadathang();
	    	ObservableList<Chitietdathang> tableChitietkiemtra = FXCollections.observableArrayList();
	    	 StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
						.configure("hibernate.cfg.xml")
						.build();
				Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
				SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
				Session session = sessionFactory.openSession();
				
				CriteriaBuilder builder = session.getCriteriaBuilder();
				//SELECT C.soluong,SP.tensanpham,SP.giatien FROM chitietdathang C,sanpham SP WHERE C.masanpham = SP.masanpham
				CriteriaQuery<Chitietdathang> query = builder.createQuery(Chitietdathang.class);
				Root<Chitietdathang> root = query.from(Chitietdathang.class); // FROM
				Join<Chitietdathang, Sanpham> SanphamJoin = root.join("sanpham", JoinType.INNER);
				Join<Chitietdathang, Phieudathang> DatHangJoin = root.join("phieudathang", JoinType.INNER);
				query.where(builder.equal(DatHangJoin.get("madathang"), Phieudathang));
				List<Chitietdathang> ctdh = session.createQuery(query).getResultList();
			 for(Chitietdathang b : ctdh) {
				 tableChitietkiemtra.add(b);
				 System.out.println(b);
			 }
			 return tableChitietkiemtra;
			
			 
	    }
	public void setKiemtrahang (Phieudathang phieudathang) {
		madathangkt.setText(String.valueOf(phieudathang.getMadathang()));
		thoigiandatkt.setText(String.valueOf(phieudathang.getThoigian()));
		mancckt.setText(String.valueOf(phieudathang.getNhacungcap().toString()));
		tongtien.setText(String.valueOf(phieudathang.getTongtien()));
		IntitlizeChitietdathang(phieudathang);
	}
	
	public void loadData(Taikhoannv taikhoan) {
		btphieunhap.setOnMouseClicked(event ->  {
			Alert alert = new Alert(AlertType.INFORMATION);
			Session session = HibernateUtils.getSessionFactory().openSession();
		Nhacungcap nhacungcap = new Nhacungcap();
    	int  mancc= Integer.parseInt(mancckt.getText());
    	nhacungcap = session.get(Nhacungcap.class, mancc);
    	int tongtien =0;
    	for(Chitietnhaphang ct : tbnhaphang.getItems() ) {
    	 tongtien = (int) (tongtien+ct.getThanhtien());
    	}
    	
    	//int tongtienn =Integer.parseInt(tongtien.getText());
    	LocalDateTime dateTime = LocalDateTime.now();
    	Nhanvien nhanvien = new Nhanvien();
    	nhanvien = session.get(Nhanvien.class,taikhoan.getNhanvien().getManv());
    	Phieunhaphang phieunhaphang= new Phieunhaphang(dateTime,tongtien,nhacungcap,nhanvien);
		 try{
    		 session.beginTransaction();
    		 session.save(phieunhaphang);
    		 session.getTransaction().commit();  
    		 alert.setContentText("Thêm phiếu nhập hàng thành công!" );
    		 alert.showAndWait();
		 	}
	    	catch (RuntimeException error){
	    		 alert.setContentText("Lỗi " + error);
	    		 alert.showAndWait();
	    		session.getTransaction().rollback();
	    	}
		
    
    	for (Chitietnhaphang ct : tbnhaphang.getItems()) {
    		int soluongnhap =ct.getSoluong();
    		double thanhtien = ct.getThanhtien();
    		Sanpham sanpham = new Sanpham();
    		sanpham= session.get(Sanpham.class, ct.getSanpham().getMasanpham());
    		Chitietnhaphang chitietnhaphang= new Chitietnhaphang(phieunhaphang,sanpham,soluongnhap,thanhtien);
		//them so luong sp 
    		int soluong = ct.getSanpham().getDonvitinh() + sanpham.getDonvitinh();
    		
			 try {
	    	 session.beginTransaction();
	    	 sanpham.setDonvitinh(soluong);
    		 session.save(chitietnhaphang);
    		 session.save(sanpham);
    		 session.getTransaction().commit();
	         alert.setContentText("Thêm chi tiết nhập hàng và cập nhật số lượng thành công!" );
    		 alert.showAndWait();

			 }
	    	catch (RuntimeException error){
		    		 System.out.println(error);
		    		 alert.setContentText("Thêm  thất bại!");
		    		 alert.showAndWait();
		    		 session.getTransaction().rollback();
		    	} 	    		
    		}	
		});
		
		btphieutra.setOnMouseClicked(event ->  {
			Alert alert = new Alert(AlertType.INFORMATION);
			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
					.configure("hibernate.cfg.xml")
					.build();
			Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
			SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
			Session session = sessionFactory.openSession();
		Nhacungcap nhacungcap = new Nhacungcap();
    	int  mancc= Integer.parseInt(mancckt.getText());
    	nhacungcap = session.get(Nhacungcap.class, mancc);
    	int tongtien =0;
    	for(Chitietphieutra ct : tbtrahang.getItems() ) {
    	 tongtien = (int) (tongtien+ct.getThanhtien());
    	}
    	Nhanvien nhanvien = new Nhanvien();
    	nhanvien = session.get(Nhanvien.class,taikhoan.getNhanvien().getManv());
    	//int tongtienn =Integer.parseInt(tongtien.getText());
    	LocalDateTime dateTime = LocalDateTime.now();
    	Phieutrahang phieutrahang= new Phieutrahang(dateTime,tongtien,nhacungcap,nhanvien);
		 try{
    		 session.beginTransaction();
    		 session.save(phieutrahang);
    		 session.getTransaction().commit();  
    		 alert.setContentText("Thêm phiếu trả hàng thành công!" );
    		 alert.showAndWait();
		 	}
	    	catch (RuntimeException error){
	    		 alert.setContentText("Lỗi " + error);
	    		 alert.showAndWait();
	    		session.getTransaction().rollback();
	    	}
		 
		 for (Chitietphieutra ct : tbtrahang.getItems()) {
			    String lydo1 = lydo.getText();
	    		int soluongnhap =ct.getSoluong();
	    		double thanhtien = ct.getThanhtien();
	    		Sanpham sanpham = new Sanpham();
	    		sanpham= session.get(Sanpham.class, ct.getSanpham().getMasanpham());
	    		
	    		Chitietphieutra chitietphieutra= new Chitietphieutra(phieutrahang,sanpham,soluongnhap,lydo1,thanhtien);
    		//them so luong sp 
	    	//	int soluong = ct.getSanpham().getDonvitinh() + sanpham.getDonvitinh();
	    		
				 try {
		    	 session.beginTransaction();
		    	// sanpham.setDonvitinh(soluong);
	    		 session.save(chitietphieutra);
	    	//	 session.save(sanpham);
	    		 session.getTransaction().commit();
		         alert.setContentText("Thêm chi tiết trả hàng thành công!" );
	    		 alert.showAndWait();

				 }
		    	catch (RuntimeException error){
			    		 System.out.println(error);
			    		 alert.setContentText("Thêm  thất bại   !");
			    		 alert.showAndWait();
			    		 session.getTransaction().rollback();
			    	} 	    		
	    		}	
		});
	}
	private void ButtonXoaSP() {
		Callback<TableColumn<Chitietnhaphang, Void>, TableCell<Chitietnhaphang, Void>> cellFactory = new Callback<TableColumn<Chitietnhaphang, Void>, TableCell<Chitietnhaphang, Void>>() {
            @Override
            public TableCell<Chitietnhaphang, Void> call(final TableColumn<Chitietnhaphang, Void> param) {
                final TableCell<Chitietnhaphang, Void> cell = new TableCell<Chitietnhaphang, Void>() {

                    private final Button btn = new Button("Xóa");

                    {
                    btn.setOnAction((ActionEvent event) -> {
                    	Chitietnhaphang chitietnhaphang = getTableView().getItems().get(getIndex());
                    	 if(tbnhaphang.getItems().contains(chitietnhaphang)) {
                    		tbnhaphang.getItems().remove(chitietnhaphang);
               			 tbnhaphang.refresh();
               			 if(chitietnhaphang.getSoluong()==0) {
               				 tbnhaphang.getItems().remove(chitietnhaphang);
               				 tbnhaphang.refresh();
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
        xoasp1.setCellFactory(cellFactory);
 }
	//
	private void ButtonXoaSP2() {
        Callback<TableColumn<Chitietphieutra, Void>, TableCell<Chitietphieutra, Void>> cellFactory = new Callback<TableColumn<Chitietphieutra, Void>, TableCell<Chitietphieutra, Void>>() {
            @Override
            public TableCell<Chitietphieutra, Void> call(final TableColumn<Chitietphieutra, Void> param) {
                final TableCell<Chitietphieutra, Void> cell = new TableCell<Chitietphieutra, Void>() {

                    private final Button btn = new Button("Xóa");

                    {
                    btn.setOnAction((ActionEvent event) -> {
                    	Chitietphieutra chitietphieutra = getTableView().getItems().get(getIndex());
                    	 if(tbtrahang.getItems().contains(chitietphieutra)) {
                    		tbtrahang.getItems().remove(chitietphieutra);
               			 tbnhaphang.refresh();
               			 if(chitietphieutra.getSoluong()==0) {
               				 tbtrahang.getItems().remove(chitietphieutra);
               				 tbtrahang.refresh();
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
        xoasp2.setCellFactory(cellFactory);
 }
	//
//	private void setCellValue()  {
//        tableChitietKiemtra.setOnMouseClicked(event -> {
//            event();
//            event2();
//        });
//    }
	private void event() {
        Chitietdathang sp = tableChitietKiemtra.getItems().get(tableChitietKiemtra.getSelectionModel().getSelectedIndex());
        
        int masanpham = sp.getSanpham().getMasanpham();
        String tensanpham = sp.getSanpham().getTensanpham();
        String loaisanpham = sp.getSanpham().getLoaisanpham();
        int soluong = sp.getSoluong();
        String donvi = sp.getSanpham().getDonvi();
        int giatien = sp.getSanpham().getGiatien();
         Sanpham sp1 = new Sanpham(masanpham, tensanpham, loaisanpham, donvi, giatien, soluong);
        addItem(soluong,sp1, 0.0);
        tableChitietKiemtra.getItems().remove(sp);
        tableChitietKiemtra.refresh();
   }

   public void addItem(int soluong,Sanpham sanpham,double thanhtien) {
       Chitietnhaphang entry = tbnhaphang.getItems().stream()
           .filter(item -> item.getSanpham().equals(sanpham))
           .findAny()
           .orElseGet(()-> {
               Chitietnhaphang newItem = new Chitietnhaphang(sanpham, soluong, thanhtien);
              tbnhaphang.getItems().add(newItem);
               return newItem ;
           });
       entry.setThanhtien(entry.getSanpham().getGiatien()*entry.getSoluong());
      
   }
   ////
	private void event2() {
        Chitietdathang sp = tableChitietKiemtra.getItems().get(tableChitietKiemtra.getSelectionModel().getSelectedIndex());
        int masanpham = sp.getSanpham().getMasanpham();
        String tensanpham = sp.getSanpham().getTensanpham();
        String loaisanpham = sp.getSanpham().getLoaisanpham();
        int soluong = sp.getSoluong();
        String donvi = sp.getSanpham().getDonvi();
        int giatien = sp.getSanpham().getGiatien();
         Sanpham sp1 = new Sanpham(masanpham, tensanpham, loaisanpham, donvi, giatien, soluong);
        addItem2(soluong,sp1, 0.0);
        tableChitietKiemtra.getItems().remove(sp);
        tableChitietKiemtra.refresh();
   }

   public void addItem2(int soluong,Sanpham sanpham,double thanhtien) {
       Chitietphieutra entry = tbtrahang.getItems().stream()
           .filter(item -> item.getSanpham().equals(sanpham))
           .findAny()
           .orElseGet(()-> {
              
               Chitietphieutra newItem = new Chitietphieutra(sanpham, soluong, thanhtien);
              tbtrahang.getItems().add(newItem);
               return newItem ;
           });
       entry.setThanhtien(entry.getSanpham().getGiatien()*entry.getSoluong());
      
   }
   ////
	public void IntitlizeChitietnhaphang () {
		
		idtensp1.setCellFactory(tbnhaphang ->new TableCell<Chitietnhaphang, Sanpham>(){
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
    	
    	idtensp1.setCellValueFactory(new PropertyValueFactory<>("sanpham"));
    	idmasp1.setCellValueFactory(new PropertyValueFactory<>("sanpham"));
    	idmasp1.setCellFactory(tbnhaphang ->new TableCell<Chitietnhaphang, Sanpham>(){
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
    	idsoluong1.setCellValueFactory(new PropertyValueFactory<Chitietnhaphang,Integer>("soluong"));
    	iddongia1.setCellValueFactory(new PropertyValueFactory<>("sanpham"));
    	iddongia1.setCellFactory(tbnhaphang ->new TableCell<Chitietnhaphang, Sanpham>(){
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
    	idthanhtien1.setCellValueFactory(new PropertyValueFactory<Chitietnhaphang, Double>("thanhtien"));
    	ButtonXoaSP();
    	
    	
	}
public void IntitlizeChitiettrahang () {
		
		idtensp2.setCellFactory(tbnhaphang ->new TableCell<Chitietnhaphang, Sanpham>(){
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
    	
    	idtensp2.setCellValueFactory(new PropertyValueFactory<>("sanpham"));
    	idmasp2.setCellValueFactory(new PropertyValueFactory<>("sanpham"));
    	idmasp2.setCellFactory(tbnhaphang ->new TableCell<Chitietnhaphang, Sanpham>(){
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
    	idsoluong2.setCellValueFactory(new PropertyValueFactory<Chitietnhaphang,Integer>("soluong"));
    	iddongia2.setCellValueFactory(new PropertyValueFactory<>("sanpham"));
    	iddongia2.setCellFactory(tbnhaphang ->new TableCell<Chitietnhaphang, Sanpham>(){
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
    	idthanhtien2.setCellValueFactory(new PropertyValueFactory<Chitietnhaphang, Double>("thanhtien"));
    	ButtonXoaSP2();
    	
    	
	}
	 public void IntitlizeChitietdathang(Phieudathang phieudathang) {
		 //setCellValue();
	    	tenhang.setCellFactory(tableChitietKiemtra ->new TableCell<Chitietdathang, Sanpham>(){
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
	    	
	    	tenhang.setCellValueFactory(new PropertyValueFactory<>("sanpham"));
	    	soluong.setCellValueFactory(new PropertyValueFactory<Chitietdathang,Integer>("soluong"));
	    	dongia.setCellValueFactory(new PropertyValueFactory<>("sanpham"));
	    	dongia.setCellFactory(tbChitietDatHang ->new TableCell<Chitietdathang, Sanpham>(){
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
	    	
	    	
	    	TableColumn<Chitietdathang,Boolean> choice = new TableColumn<Chitietdathang,Boolean>("Membership");
	    	mahang.setCellValueFactory(new PropertyValueFactory<>("sanpham"));
	    	mahang.setCellFactory(tableChitietKiemtra ->new TableCell<Chitietdathang, Sanpham>(){
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
	    	
	    	 
		    	
//	     	choice.setCellValueFactory(new PropertyValueFactory<Chitietdathang,Boolean>("check"));
//	    	choice.setCellFactory(column -> new CheckBoxTableCell());
	    	tableChitietKiemtra.setEditable(true);
	    	tableChitietKiemtra.getColumns().add(choice);
	    	tableChitietKiemtra.setItems(getChitietdathang(phieudathang));	
	    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		IntitlizeChitietnhaphang();
		IntitlizeChitiettrahang ();
		// TODO Auto-generated method stub
		
	}
}
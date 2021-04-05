package BanHang;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import org.hibernate.query.Query;

import com.sun.xml.bind.v2.util.QNameMap.Entry;

import QLBH.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.persistence.criteria.CriteriaQuery;
import javax.swing.event.ChangeListener;

import org.apache.derby.iapi.store.access.SpaceInfo;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import QLBH.Chitiethoadon;
import QLBH.Sanpham;
import javafx.scene.control.cell.PropertyValueFactory;


import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class BanHangController implements Initializable{
	
	
	@FXML
	private Label thongbao;
	 @FXML
	private TextField tongtien;
	 
	 @FXML
	private TextField giamgia;
	 
	 @FXML
	private Label khachhangg;
	 
	 @FXML
	private Label diemtichluy;
	 
	@FXML
	private TextField khachtra;

	@FXML
	private TextField tienthua;
    @FXML
    private TableView<Sanpham> TableSP;


    @FXML
    private TableView <Chitiethoadon> hoadon;

    @FXML
    private TableColumn giatien;

    @FXML
    private TableColumn<Chitiethoadon, Sanpham> masanpham1;

    @FXML
    private TableColumn loaisanpham;

    @FXML
    private TableColumn loaisanpham1;

    @FXML
    private TableColumn donvi1;

    @FXML
    private TableColumn giatien1;

    @FXML
    private TableColumn tensanpham;

    @FXML
    private TableColumn tensanpham1;

    @FXML
    private TableColumn donvitnh1;
        
    @FXML
    private TableColumn thanhtien;
    
    @FXML
    private TableColumn xoaSP;
    
    @FXML
    private TableColumn hinhanhsanpham;
    @FXML
    private Button thanhtoan;
    
    @FXML
    private Button timkhachhang;
    
    @FXML
    private Button chinhsachkhuyenmai;
    
   //
 
    
    ObservableList<Sanpham> danhmuchoadon = FXCollections.observableArrayList();
    
	 private void setCellValueFromTabletoTexfFieldd()  {
		 TableSP.setOnMouseClicked(event -> {
			 //
			 event();
			// Sanpham sp = TableSP.getItems().get(TableSP.getSelectionModel().getSelectedIndex());
		//	 System.out.println(sp.getDonvitinh());
			//tongtien.setText(Integer.toString(sp.getGiatien()));
			 
		
			
		 });
//		 hoadon.setOnMouseClicked(event -> {
//			 //
//			
//			 Sanpham sp = hoadon.getItems().get(hoadon.getSelectionModel().getSelectedIndex());
//			 tongtien.setText(Integer.toString(sp.getGiatien()));
//		
//			
//		 });
	 }
	 
	 public void loadData(Taikhoannv taikhoan) throws IOException{
		 thanhtoan.setOnMouseClicked(event ->  {
			 Alert alert = new Alert(AlertType.INFORMATION);
				StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
						.configure("hibernate.cfg.xml")
						.build();
				Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
				SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
				Session session = sessionFactory.openSession();
				int makh = Integer.parseInt(khachhangg.getText());
				int tongtienmua = Integer.parseInt(tongtien.getText());
				KhachHang khachhang = new KhachHang();
				khachhang = session.get(KhachHang.class, makh);
				Nhanvien nhanvien = new Nhanvien();
				nhanvien = session.get(Nhanvien.class,taikhoan.getNhanvien().getManv());
				//Nhanvien nhanvien = new Nhanvien();
				//nhanvien = session.get(Nhanvien.class, 12);
				Hoadon hoadonn = new Hoadon(null, tongtienmua, nhanvien,khachhang);
				 try {
		    		 session.beginTransaction();
		    		 session.save(hoadonn);
		    	//	 hoadonn = session.get(Hoadon.class, hoadonn.getMahoadon());
		    		 //session.save(chitiethoadon);
		    		// session.save(spp);
		    		 session.getTransaction().commit();
		    		 alert.setContentText("Thêm hoa don thành công !");
		    		 alert.showAndWait();   
				 }
			    	catch (RuntimeException error){
			    		System.out.println(error);
			    		 alert.setContentText("Lỗi " + error);
			    		 alert.showAndWait();
			    		session.getTransaction().rollback();
			    	}
				for (Chitiethoadon chitiethoadon123 : hoadon.getItems()) {
					Sanpham spp= new Sanpham();
					int masp = chitiethoadon123.getSanpham().getMasanpham();
					spp= session.get(Sanpham.class, masp);
					int soluong = chitiethoadon123.getSoluong();
					double thanhtien = chitiethoadon123.getThanhtien();
				//	System.out.println(" masp : " +masp);
					//System.out.println(masp);
					Chitiethoadon  chitiethoadon = new Chitiethoadon(soluong,hoadonn,spp,thanhtien);
					 try {
			    		 session.beginTransaction();
			    		// session.save(hoadonn);
			    		 System.out.println(hoadonn.getMahoadon());
			    		 hoadonn = session.get(Hoadon.class, hoadonn.getMahoadon());
			    		 session.save(chitiethoadon);
			    		// session.save(spp);
			    		 session.getTransaction().commit();
			    		 alert.setContentText("Thêm chi tiet  thành công !");
			    		 alert.showAndWait();   
					 }
				    	catch (RuntimeException error){
				    		 System.out.println(error);
				    		 alert.setContentText("Thêm chi tiet thất bại   !");
				    		 alert.showAndWait();
				    		 session.getTransaction().rollback();
				    	}
					}
				//tich diem + tru san pham
				for (Sanpham spp : TableSP.getItems()) {
		            //  int soluongban = spp.getDonvitinh();
		             // System.out.println(soluongban);
		              
		          for (Chitiethoadon sp : hoadon.getItems()) {
		              //int soluongmua = sp.getSoluong();
		            //  System.out.println(soluongmua);
		              if(spp.getMasanpham() == sp.getSanpham().getMasanpham()) {
		              	System.out.println(spp.getDonvitinh());
		              	System.out.println(sp.getSoluong());
		              	int soluongtrongkho = spp.getDonvitinh();
		              	int soluongban = sp.getSoluong();
		              	int masanphamm = sp.getSanpham().getMasanpham();
		              	int soluongconlai = soluongtrongkho - soluongban;
		 				int tichdiem = Integer.parseInt(tongtien.getText())/10000;
						int tichluy = Integer.parseInt(diemtichluy.getText());
						int makhh = Integer.parseInt(khachhangg.getText());
						System.out.println(tichdiem);
						System.out.println(tichluy);
						System.out.println(makhh);
						int tongg = tichluy +tichdiem ;
						System.out.println(tongg);
		              	System.out.print(masanphamm);
		              	if (soluongban <= soluongtrongkho) {

			                    	try{
			                    		 
			            				session.getTransaction().begin();
			            				String hql = "update KhachHang kh set kh.diemtichluy = :diemtichluy where kh.makh =:makh ";
			            				Query query = session.createQuery(hql);
			            				query.setParameter("diemtichluy", tongg);
			            				query.setParameter("makh",makhh);
			            				System.out.println("11111");
			            				int result = query.executeUpdate();
			            				System.out.print(result);
			            	    		 String hql2 = "update Sanpham sp set sp.donvitinh =:soluongconlai where sp.masanpham =:masanpham ";
			            	    		 Query query1 = session.createQuery(hql2);
			            	    		 query1.setParameter("soluongconlai", soluongconlai);
			            	    	     query1.setParameter("masanpham",masanphamm);
			            	    	     int result1 = query1.executeUpdate();
			            	    	     System.out.print(result);
			            	    	     session.clear();
			            	    	     session.close();
			            	    	     System.out.println("tru san pham thanh cong !");
			            				}
			            		        catch(HibernateException e) {
			            		    		e.printStackTrace();
			            		       
			            		        }
		              	}
		              	else {
		              		 alert.setContentText("het hang roi du me may");
					    		 alert.showAndWait();

		              	}
		              }
		          }

		      }
						
				
			});
		 
		   
	 }
//
	/* private void thanhtien() {
	      

	        Callback<TableColumn<Sanpham, Void>, TableCell<Sanpham, Void>> cellFactory = new Callback<TableColumn<Sanpham, Void>, TableCell<Sanpham, Void>>() {
	            @Override
	            public TableCell<Sanpham, Void> call(final TableColumn<Sanpham, Void> param) {
	                final TableCell<Sanpham, Void> cell = new TableCell<Sanpham, Void>() {
	           		 Sanpham sp = TableSP.getItems().get(TableSP.getSelectionModel().getSelectedIndex());
	           		 String thanhtien;
	           		 
	                  
	                   
	               
	                };
	                return cell;
	            }
	        };
	        xoaSP.setCellFactory(cellFactory);
	 }*/
	 
	 
	 private void ButtonXoaSP() {
	      

	        Callback<TableColumn<Chitiethoadon, Void>, TableCell<Chitiethoadon, Void>> cellFactory = new Callback<TableColumn<Chitiethoadon, Void>, TableCell<Chitiethoadon, Void>>() {
	            @Override
	            public TableCell<Chitiethoadon, Void> call(final TableColumn<Chitiethoadon, Void> param) {
	                final TableCell<Chitiethoadon, Void> cell = new TableCell<Chitiethoadon, Void>() {

	                    private final Button btn = new Button("Xóa Sản Phẩm");

	                    {
	                    	///////////////////////////
	                    btn.setOnAction((ActionEvent event) -> {
	                    	Chitiethoadon chitiethoadon = getTableView().getItems().get(getIndex());
	                    	 if(hoadon.getItems().contains(chitiethoadon)) {
	               			 chitiethoadon.setSoluong(chitiethoadon.getSoluong()-1);
	               			 hoadon.refresh();
	               			 if(chitiethoadon.getSoluong()==0) {
	               				 hoadon.getItems().remove(chitiethoadon);
	               				 hoadon.refresh();
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
	        xoaSP.setCellFactory(cellFactory);
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
	 
	 

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		

		
		setCellValueFromTabletoTexfFieldd();
	//	masanpham.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("masanpham"));
		tensanpham.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("tensanpham"));
		loaisanpham.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("loaisanpham"));
	//	donvitnh.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("donvitinh"));
		giatien.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("giatien"));
	//	donvi.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("donvi"));
		hinhanhsanpham.setCellValueFactory(new PropertyValueFactory<Sanpham, Byte>("imagesp"));
		hinhanhsanpham.setCellFactory(param -> new TableCell<Sanpham, byte[]>() {

	        private ImageView imageView = new ImageView();

	        @Override
	        protected void updateItem(byte[] item, boolean empty) {
	            super.updateItem(item, empty);
	            if (item == null || empty) {
	                setText(null);
	                setGraphic(null);
	            } else {
	                imageView.setImage(getImageFromBytes(item));
	                imageView.setFitHeight(150);
                    imageView.setFitWidth(250);
	                setGraphic(imageView);
	            }
	            this.setItem(item);
	        }
	    });
		TableSP.setItems(getSanpham());
		
	// 
	//	masanpham1.setCellValueFactory(new PropertyValueFactory<Chitiethoadon, Sanpham>("sanpham"));
		masanpham1.setCellValueFactory(new PropertyValueFactory<>("sanpham"));
		masanpham1.setCellFactory(hoadon -> new TableCell<Chitiethoadon, Sanpham>() {
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
		
		tensanpham1.setCellValueFactory(new PropertyValueFactory<>("sanpham"));
		tensanpham1.setCellFactory(hoadon -> new TableCell<Chitiethoadon, Sanpham>() {
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
		
		
		
		loaisanpham1.setCellValueFactory(new PropertyValueFactory<>("sanpham"));
		loaisanpham1.setCellFactory(hoadon -> new TableCell<Chitiethoadon, Sanpham>() {
			@Override
			protected void updateItem(Sanpham item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText(null);
				} else {
					setText(item.getLoaisanpham());
				}
			}

		});
		donvitnh1.setCellValueFactory(new PropertyValueFactory<Chitiethoadon, Integer>("soluong"));

		giatien1.setCellValueFactory(new PropertyValueFactory<>("sanpham"));
		giatien1.setCellFactory(hoadon -> new TableCell<Chitiethoadon, Sanpham>() {
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
		donvi1.setCellValueFactory(new PropertyValueFactory<>("sanpham"));
		donvi1.setCellFactory(hoadon -> new TableCell<Chitiethoadon, Sanpham>() {
			@Override
			protected void updateItem(Sanpham item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText(null);
				} else {
					setText(item.getDonvi());
				}
			}

		});
		ButtonXoaSP();
		
		thanhtien.setCellValueFactory(new PropertyValueFactory<Chitiethoadon, Double>("thanhtien"));
		
//	        
		khachtra.addEventHandler(KeyEvent.KEY_PRESSED, e->{
			if(e.getCode() == KeyCode.ENTER) {
				if( Integer.parseInt(khachtra.getText()) < Integer.parseInt(tongtien.getText())) {
				
                 
                 khachtra.setText("");
                 thongbao.setText("Khong Du Tien, Nhap lai");
                 

                 
                 
			}
				else {
					 float tiengiam = Float.parseFloat(giamgia.getText())/100;
					 float tongtiengiam = Float.parseFloat(tongtien.getText())*(1-tiengiam);
					 float tientrakhach = Float.parseFloat(khachtra.getText())- tongtiengiam;
					// System.out.print(tiengiam);
	                // System.out.print(tongtiengiam);
	                 tienthua.setText(String.valueOf(tientrakhach));
				}
			}
		});
//

		
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
		 
		 /*	 int soluong = sp.getDonvitinh();
		 Chitiethoadon chitiethoadon = new Chitiethoadon(soluong,sp,0.0);
	//	int masanpham = sp.getMasanpham();
		
		// Sanpham sp1 = TableSP.getItems().get(TableSP.getSelectionModel().getSelectedIndex());
		 if(!hoadon.getItems().contains(sp.getTensanpham())) {
			 chitiethoadon.setSoluong(1);
			 hoadon.getItems().add(chitiethoadon);
			// thanhtien.setOnEditCommit(String.valueOf(sp.getDonvitinh() * sp.getGiatien()));
			 hoadon.refresh();
			// Callback cellFactory = null;
			// xoaSP.setCellFactory(cellFactory);
			// tinhtongtien();
		
		 }
		 
		 
		 else if(hoadon.getItems().contains(chitiethoadon)) {
			chitiethoadon.setSoluong(chitiethoadon.getSoluong()+1);
			 hoadon.refresh();
			// tinhtongtien();

		 }*/
		 addItem(0, sp, 0.0);
		 hoadon.refresh();
	}
/*	public void tinhtongtien() {
		Sanpham sp = TableSP.getItems().get(TableSP.getSelectionModel().getSelectedIndex());
		 thanhtien.setCellFactory(new Callback<TableColumn, TableCell>() {
	            @Override
	            public TableCell call(TableColumn p) {
	                return new TableCell() {
	                    @Override
	                    protected void updateItem(Object item, boolean empty) {
	                        super.updateItem(item, empty);
	                        if (this.getTableRow() != null && item != null) {
//	                            System.out.println(this.getTableRow().getItem().toString());
//	                            setText((this.getTableRow().getIndex() + 1) + "");
//	                              System.out.println(this.getTableRow().getIndex());
//	                            System.out.println(getTableView().getItems().get(getTableRow().getIndex() -1));
//	                            System.out.println();
	                           
	                            System.out.println("");
//	                            setText(String.valueOf(Integer.parseInt(getTableRow().getItem().toString()) + Integer.parseInt(getTableView().getItems().get(pre).toString())));
	                            Integer totalValue = new Integer(0);
	                            totalValue = sp.getDonvitinh() * sp.getGiatien();
	                            setText(String.valueOf(totalValue));
	                            int sum = 0;
	                            for (Sanpham thanhtien : hoadon.getItems()) {
	                                sum = sum + (thanhtien.getGiatien()* thanhtien.getDonvitinh());
	                            }
	                            tongtien.setText(String.valueOf(sum));
	                           
	                           //int tientrakhach = Integer.parseInt(khachtra.getText())- Integer.parseInt(tongtien.getText());
	                         //    System.out.print(tientrakhach);
//	                            setText(this.getTableRow().getItem().toString());
	                        } else {
	                            setText("");
	                        }
	                    }
	                };
	            }
	        }); 
	}*/
	public void addItem(int soluong,Sanpham sanpham,double thanhtien) {
	    Chitiethoadon entry = hoadon.getItems().stream()
	        .filter(item -> item.getSanpham().equals(sanpham))
	        .findAny()
	        .orElseGet(()-> {
	           //  Sanpham newItem = new Sanpham(masanpham,tensanpham, loaisanpham,donvi,0, 0);
	            Chitiethoadon newItem = new Chitiethoadon(soluong,sanpham,thanhtien);
	        	hoadon.getItems().add(newItem);
	            return newItem ;
	        });
	    entry.setSoluong(entry.getSoluong()+1);
	//    entry.setDonvitinh(entry.getDonvitinh() + donvitinh);
 	 //   entry.setGiatien(entry.getGiatien() + giatien);
	    entry.setThanhtien(entry.getSanpham().getGiatien()*entry.getSoluong());
	    int sum = 0;
        for (Chitiethoadon chitiethoadon : hoadon.getItems()) {
            sum = sum + (chitiethoadon.getSanpham().getGiatien()*chitiethoadon.getSoluong());
        }
        tongtien.setText(String.valueOf(sum));
 	   // TableSP.refresh();
	   // entry.s
	}
	
	
	
	
	
	
	
	
	//tim kiem 
	@FXML
	private TextField Timkiem;
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
		sortedData.comparatorProperty().bind(((TableView<Sanpham>) TableSP).comparatorProperty());
		((TableView<Sanpham>) TableSP).setItems(sortedData);

	}
	/// thanh toan
	
	// tim khach hang
	@FXML
	void timkhachhang(ActionEvent event){
		timkhachhangController controller2 = new timkhachhangController(this);

        // Show the new stage/window
        controller2.showStage();
	}
	
	
	@FXML
	void chinhsachkhuyenmai(ActionEvent event) {
		
	}
	
	public void setkhachhang(KhachHang khachhang) {
		//khachhangg.setText(khachhang.getMakh().toString());
		//thongbao.setText(khachhang.getMakh().toString());
		diemtichluy.setText(String.valueOf((khachhang.getDiemtichluy())));
		khachhangg.setText(String.valueOf(khachhang.getMakh()));
		if(Integer.parseInt(diemtichluy.getText())> 1000) {
			giamgia.setText("20");
		}
		else if (Integer.parseInt(diemtichluy.getText())< 1000 && Integer.parseInt(diemtichluy.getText())>=500) {
			giamgia.setText("10");
		}
		else if (Integer.parseInt(diemtichluy.getText())< 500 && Integer.parseInt(diemtichluy.getText())> 100) {
			giamgia.setText("5");
		}
		else giamgia.setText("0");
	}
}

package QLBH;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import org.hibernate.query.Query;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.derby.iapi.store.access.SpaceInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import QLBH.Sanpham;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.util.Callback;
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

    
    ObservableList<Sanpham> danhmuchoadon = FXCollections.observableArrayList();
    
	 private void setCellValueFromTabletoTexfFieldd()  {
		 TableSP.setOnMouseClicked(event -> {
			 //
			 event();
			 Sanpham sp = TableSP.getItems().get(TableSP.getSelectionModel().getSelectedIndex());
		//	 System.out.println(sp.getDonvitinh());
			//tongtien.setText(Integer.toString(sp.getGiatien()));
			 
		
			
		 });
		 hoadon.setOnMouseClicked(event -> {
			 //
			
			 Sanpham sp = hoadon.getItems().get(hoadon.getSelectionModel().getSelectedIndex());
			 tongtien.setText(Integer.toString(sp.getGiatien()));
		
			
		 });
	 }
//
	 private void thanhtien() {
	      

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
	                    	 Sanpham sp = getTableView().getItems().get(getIndex());
	                    	 if(hoadon.getItems().contains(sp)) {
	               			 sp.setDonvitinh(sp.getDonvitinh()-1);
	               			 hoadon.refresh();
	               			 if(sp.getDonvitinh()==0) {
	               				 hoadon.getItems().remove(sp);
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
		masanpham1.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("masanpham"));
		tensanpham1.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("tensanpham"));
		loaisanpham1.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("loaisanpham"));
		donvitnh1.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("donvitinh"));
		giatien1.setCellValueFactory(new PropertyValueFactory<Sanpham, Integer>("giatien"));
		donvi1.setCellValueFactory(new PropertyValueFactory<Sanpham, String>("donvi"));
		ButtonXoaSP();
		thanhtien.setCellValueFactory(new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
	            @Override
	            public ObservableValue call(TableColumn.CellDataFeatures p) {
	            	 
	                return new ReadOnlyObjectWrapper(p.getValue());
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
		
		// Sanpham sp1 = TableSP.getItems().get(TableSP.getSelectionModel().getSelectedIndex());
		 if(!hoadon.getItems().contains(sp)) {
			 sp.setDonvitinh(1);
			 hoadon.getItems().add(sp);
			// thanhtien.setOnEditCommit(String.valueOf(sp.getDonvitinh() * sp.getGiatien()));
			 hoadon.refresh();
			// Callback cellFactory = null;
			// xoaSP.setCellFactory(cellFactory);

		
		
		 }
		 
		 
		 else if(hoadon.getItems().contains(sp)) {
			 sp.setDonvitinh(sp.getDonvitinh()+1);
			 hoadon.refresh();
			 
			 thanhtien.setCellFactory(new Callback<TableColumn, TableCell>() {
		            @Override
		            public TableCell call(TableColumn p) {
		                return new TableCell() {
		                    @Override
		                    protected void updateItem(Object item, boolean empty) {
		                        super.updateItem(item, empty);
		                        if (this.getTableRow() != null && item != null) {
//		                            System.out.println(this.getTableRow().getItem().toString());
//		                            setText((this.getTableRow().getIndex() + 1) + "");
//		                              System.out.println(this.getTableRow().getIndex());
//		                            System.out.println(getTableView().getItems().get(getTableRow().getIndex() -1));
//		                            System.out.println();
		                           
		                            System.out.println("");
//		                            setText(String.valueOf(Integer.parseInt(getTableRow().getItem().toString()) + Integer.parseInt(getTableView().getItems().get(pre).toString())));
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
//		                            setText(this.getTableRow().getItem().toString());
		                        } else {
		                            setText("");
		                        }
		                    }
		                };
		            }
		        });
				tienthua.addEventHandler(KeyEvent.KEY_PRESSED, e->{
					if(e.getCode() == KeyCode.ENTER) {
						
						 int tientrakhach = Integer.parseInt(khachtra.getText())- Integer.parseInt(tongtien.getText());
		                 System.out.print(tientrakhach);
					}
				});
			 
		 }
	}
}
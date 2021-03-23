package QLBH;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TrahangDetailController implements Serializable{

    @FXML
    private Label lbMaphieutra;

    @FXML
    private Label lbThoigiantra;

    @FXML
    private Label lbTongtien;

    @FXML
    private Label lbMancc;

    @FXML
    private Label lbManv;

    @FXML
    private Button backPT;

    @FXML
    private TableView tbChitietPhieuTra;

    @FXML
    private TableColumn<Chitietphieutra, Sanpham> tenhang;

    @FXML
    private TableColumn soluong;

    @FXML
    private TableColumn dongia;

    @FXML
    private TableColumn tongtien;

    @FXML
    void backPT(ActionEvent event) throws IOException {
    	Stage stage = (Stage) backPT.getScene().getWindow();
        stage.close();
    }
    
    public void setPhieutrahang(Phieutrahang phieutrahang) {
    	lbMaphieutra.setText(String.valueOf(phieutrahang.getMaphieutra()));
    	lbThoigiantra.setText(String.valueOf(phieutrahang.getThoigiantra()));
    	lbMancc.setText(phieutrahang.getNhacungcap().toString());
    	lbManv.setText(phieutrahang.getNhanvien().toString());
    	IntitlizeChitietphieutra(phieutrahang);
    }
    
    public ObservableList<Chitietphieutra> getChitietphieutra(Phieutrahang phieutrahang) {
    	String Phieutrahang = phieutrahang.getMaphieutra();
    	ObservableList<Chitietphieutra> tablePhieuTraHang = FXCollections.observableArrayList();
    	 StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
					.configure("hibernate.cfg.xml")
					.build();
			Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
			SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
			Session session = sessionFactory.openSession();
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			//SELECT C.soluong,SP.tensanpham,SP.giatien FROM chitietdathang C,sanpham SP WHERE C.masanpham = SP.masanpham
			CriteriaQuery<Chitietphieutra> query = builder.createQuery(Chitietphieutra.class);
			Root<Chitietphieutra> root = query.from(Chitietphieutra.class); // FROM
			Join<Chitietphieutra, Sanpham> SanphamJoin = root.join("sanpham", JoinType.INNER);
			//query.where(builder.equal(SanphamJoin.get("masanpham"), masanpham));
			List<Chitietphieutra> ctpt = session.createQuery(query).getResultList();
		 for(Chitietphieutra b : ctpt) {
			 tablePhieuTraHang.add(b);
			 System.out.println(b);
		 }
		 return tablePhieuTraHang;
		
		 
    }

	private void IntitlizeChitietphieutra(Phieutrahang phieutrahang) {
		tenhang.setCellFactory(tbChitietPhieuTra ->new TableCell<Chitietphieutra, Sanpham>(){
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
    	soluong.setCellValueFactory(new PropertyValueFactory<Chitietphieutra,Integer>("soluong"));
    	dongia.setCellValueFactory(new PropertyValueFactory<>("sanpham"));
    	dongia.setCellFactory(tbChitietPhieuTra ->new TableCell<Chitietphieutra, Sanpham>(){
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
    	tbChitietPhieuTra.setItems(getChitietphieutra(phieutrahang));
	}
}

package QLBH;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HoadonDetailController {
	

	@FXML
    private Label lbMahoadon;

    @FXML
    private Label lbThoigianmua;

    @FXML
    private Label lbTonggia;

    @FXML
    private Label lbMakh;

    @FXML
    private Label lbManv;
    
    public void setHoadon (Hoadon hoadon) {
    	lbMahoadon.setText(hoadon.getMahoadon());
    	lbThoigianmua.setText(hoadon.getThoigianmua());
    	lbTonggia.setText(String.valueOf(hoadon.getMahoadon()));
    	lbMakh.setText(String.valueOf(hoadon.getMakh()));
    	lbManv.setText(String.valueOf(hoadon.getManv()));
    }
    
    @FXML
    void goBack(ActionEvent e) throws IOException {
       Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("danhsachnhanvien.fxml"));
        Parent QLBHhdParent = loader.load();
        Scene scene = new Scene(QLBHhdParent);
        stage.setScene(scene);
  

    	
    }
    
    

}

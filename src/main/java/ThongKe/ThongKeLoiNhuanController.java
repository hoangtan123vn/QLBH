package ThongKe;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.query.Query;

import QLBH.HibernateUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

public class ThongKeLoiNhuanController implements Initializable{

    @FXML
    private LineChart<String, Number> loinhuan;

    @FXML
    private CategoryAxis thang;

    @FXML
    private NumberAxis tienloinhuan;
    
    @FXML
    private ComboBox<String> listthongke;
    
    @FXML
    private AnchorPane mainpane;

    @FXML
    void ListThongKe(ActionEvent event) throws IOException {
    	if (listthongke.getValue() == "Thống kê theo ngày") {
    		AnchorPane pane = FXMLLoader.load(getClass().getResource("/ThongKe/thongkedoanhthutheongay.fxml"));
        	mainpane.getChildren().setAll(pane);
    	}else if(listthongke.getValue() == "Thống kê khác") {
    		AnchorPane pane = FXMLLoader.load(getClass().getResource("/ThongKe/thongkedoanhthu.fxml"));
        	mainpane.getChildren().setAll(pane);
    		
    	}
    }
    
    
    public void ThongKeLoiNhuan() {
    	Session session = HibernateUtils.getSessionFactory().openSession();
    	try {
    		XYChart.Series series = new XYChart.Series();
    		//XYChart.Series series1 = new XYChart.Series();
    		String hql = "SELECT SUM(tonggia),thoigianmua as month FROM Hoadon GROUP BY month(thoigianmua) ORDER BY month(thoigianmua)";
    		String hql1 = "SELECT SUM(tongtien),thoigian as month FROM Phieudathang GROUP BY month(thoigian) ORDER BY month(thoigian)";
    		Query query = session.createQuery(hql);
    		Query query1 = session.createQuery(hql1);
    		 List<Object[]> hd = query.list(); 
    		 List<Object[]> chi = query1.list();
     		 loinhuan.getData().clear();
    		 //series.getData().clear();
    		 
    		 //int doanhthumotthang = 0;
    		 for(Object[] loinhuan : hd) {
    			 for(Object[] dathang : chi) {
    				 long tonggiathu = (long) loinhuan[0];
        			 String monththu = String.valueOf((LocalDateTime) loinhuan[1]);
        			 String dthu = String.valueOf(monththu.substring(5, 7));
        			 
        			 long tonggiachi = (long) dathang[0];
        			 String monthchi = String.valueOf((LocalDateTime) dathang[1]);
        			 String dchi = String.valueOf(monthchi.substring(5, 7));
        			 if(dthu.equals(dchi)) {
        				 long loinhuan2 = tonggiathu-tonggiachi;
        				 series.getData().add(new XYChart.Data(dthu,loinhuan2));
        			 }
        			// doanhthumotthang = doanhthumotthang+hoadon.getTonggia();
        		//	  return doanhthumotthang;
        			// tb_dtthang.getItems().add(hoadon[0]);
        			// series.getData().add(new XYChart.Data(d,tonggia));
        			// linechart.setAnimated(false);
    			 }
    		 }
    		 loinhuan.getData().addAll(series);
    	}catch (Exception e) {
			// TODO: handle exception
    		System.out.println(e);
		}
    }
    		/*	 long tonggia = (long) hoadon[0];
    			 String month = String.valueOf((LocalDateTime) hoadon[1]);
    			// String month =String.valueOf(hoadon.getThoigianmua());
    			 String d = String.valueOf(month.substring(5, 7));
    			// System.out.println(d);
    			// doanhthumotthang = doanhthumotthang+hoadon.getTonggia();
    		//	  return doanhthumotthang;
    			// tb_dtthang.getItems().add(hoadon[0]);
    			 series.getData().add(new XYChart.Data(d,tonggia));
    			 linechart.setAnimated(false);
    			// data.add(new LineChart<Hoadon, Integer>());
    			 
    		 }
    	/*	 linechart.getData().addAll(series);
    		 series.setName("Doanh thu");
    		 linechart.setTitle("Doanh thu và Chi theo tháng");
    		 
    		 for(Object[] dathang : chi) {
    			 long tonggia = (long) dathang[0];
    			 String month = String.valueOf((LocalDateTime) dathang[1]);
    			 String d = String.valueOf(month.substring(5, 7));
    			 series1.getData().add(new XYChart.Data(d,tonggia));
    			 linechart.setAnimated(false);
    		 }
    		 linechart.getData().addAll(series1);
    		 series1.setName("Chi");
    		// linechart.setTitle("Thống kê chi");*/
   

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ObservableList<String> combobox2 = FXCollections.observableArrayList("Thống kê theo ngày","Thống kê khác","Thống kê lợi nhuận");
		listthongke.setItems(combobox2);
		thang.setLabel("Tháng");
		tienloinhuan.setLabel("Tiền lợi nhuận");
		listthongke.getSelectionModel().select("Thống kê lợi nhuận");
		ThongKeLoiNhuan();
	}
    
    

}

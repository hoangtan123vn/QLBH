package QLBH;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.query.Query;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

public class TrangChuController  implements Initializable{

	@FXML
    private LineChart<String,Number> line_chart;

    @FXML
    private CategoryAxis ngay;

    @FXML
    private NumberAxis doanhthu;
    @FXML
    private ComboBox<String> cbb_thang;
    
    @FXML
    private BarChart<String, Number> bar_chart;

    @FXML
    private CategoryAxis Sanpham;

    @FXML
    private NumberAxis Soluongbanra;
    @FXML
    private ComboBox<String> listthongke;
    
    @FXML
    private AnchorPane mainpane;
    
    
    @FXML
    void ThongKeThang(ActionEvent event) {
    	if (cbb_thang.getValue() == "1") {
    		thongketheongay(1);
    	}
    	else if(cbb_thang.getValue() == "2") {
    		thongketheongay(2);
    	}
    	else if(cbb_thang.getValue() == "3") {
    		thongketheongay(3);
    	}
    	else if(cbb_thang.getValue() == "4") {
    		thongketheongay(4);
    	}
    	else if(cbb_thang.getValue() == "5") {
    		thongketheongay(5);
    	}
    	else if(cbb_thang.getValue() == "6") {
    		thongketheongay(6);
    	}
    	else if(cbb_thang.getValue() == "7") {
    		thongketheongay(7);
    	}
    	else if(cbb_thang.getValue() == "8") {
    		thongketheongay(8);
    	}
    	else if(cbb_thang.getValue() == "9") {
    		thongketheongay(9);
    	}
    	else if(cbb_thang.getValue() == "10") {
    		thongketheongay(10);
    	}
    	else if(cbb_thang.getValue() == "11") {
    		thongketheongay(11);
    	}
    	else if(cbb_thang.getValue() == "12") {
    		thongketheongay(12);
    	}
    }
    public void thongketheongay(int thang) {
    	Session session = HibernateUtils.getSessionFactory().openSession();
    	XYChart.Series series = new XYChart.Series();
    	try {
    	
    		String hql = "SELECT SUM(tonggia),thoigianmua as date FROM Hoadon WHERE month(thoigianmua)=:thoigianmua GROUP BY DATE(thoigianmua) ORDER BY DATE(thoigianmua)";
    		Query query = session.createQuery(hql);
    		query.setParameter("thoigianmua", thang);
    		List<Object[]> hd = query.list(); 
    		line_chart.getData().clear();
    		 for(Object[] hoadon : hd) {
    			 ngay.setLabel("Ngày");
    			doanhthu.setLabel("Doanh thu bán được trong một ngày");
    			 long tonggia = (long) hoadon[0];
    			 String date = String.valueOf((LocalDateTime) hoadon[1]);
    			 String d = String.valueOf(date.substring(6, 10));
    			
    			 series.getData().add(new XYChart.Data(d,tonggia));

    		 }
    		 line_chart.setTitle("Thống kê theo ngày của tháng "+thang);
    		 line_chart.getData().addAll(series);
    		 line_chart.setAnimated(false);
    	}catch (Exception e) {
			// TODO: handle exception
    		System.out.println(e);
		}
    		
    		
    }
    
    LocalDateTime t1 = LocalDateTime.now();
    
    
    public void ThongKeSanPham(int ngay,int thang) {
    	Session session = HibernateUtils.getSessionFactory().openSession();
    	try {
    		XYChart.Series series = new XYChart.Series();
    		String hql = "SELECT SP.tensanpham,SUM(CT.soluong) FROM Chitiethoadon CT INNER JOIN CT.sanpham SP INNER JOIN CT.hoadon H WHERE dayofmonth(H.thoigianmua)=:date and month(H.thoigianmua)=:month GROUP BY SP.tensanpham ORDER BY CT.soluong";
    		Query query = session.createQuery(hql);
    		query.setParameter("date", ngay);
    		query.setParameter("month", thang);
    		List<Object[]> hd = query.list(); 
    		bar_chart.getData().clear();
    		 bar_chart.setTitle("Thống kê sản phẩm bán được trong một ngày");
    		  for(Object[] thongke : hd) {
    			  String tensanpham = (String) thongke[0];
    			  long soluong = (long) thongke[1];
    			  series.getData().add(new XYChart.Data(tensanpham,soluong));
    		  }
    		  bar_chart.getData().addAll(series);
    	}catch (Exception e) {
			// TODO: handle exception
    		System.out.println(e);
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ObservableList<String> combobox = FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10","11","12");
		cbb_thang.setItems(combobox);
		//thongketheongay();
		Sanpham.setLabel("Sản phẩm bán ra");
		Soluongbanra.setLabel("Số lượng bán ra ");
		bar_chart.setTitle("Thống kê số lượng sản phẩm bán ra trong một ngày");
		System.out.println(t1);
		String getDatenow = String.valueOf(t1);
		int day = Integer.parseInt(getDatenow.substring(8, 10));
		int thang = Integer.parseInt(getDatenow.substring(5, 7));
		System.out.println(day);
		System.out.println(thang);
		ThongKeSanPham(day, thang);
	}
    
    

}
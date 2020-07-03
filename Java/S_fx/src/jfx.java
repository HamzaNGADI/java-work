import java.awt.Dimension;
import java.io.File;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;



public class jfx extends Application
{
	private Button b;
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("fx lab");
		Group root = new Group();
		Scene sc = new Scene(root, 900, 800, Color.LIGHTGRAY);
		b = new Button();
		b.setText("button fx");
		b.setLayoutX(100);
		b.setLayoutY(60);
		/*b.setOnAction(new EventHandler <ActionEvent>()
				{
					@Override
					public void handle(ActionEvent arg0) {
					  System.out.println("clicked");		
					}
				}
				);*/
		Circle cr = new Circle();
		cr.setLayoutX(90);
		cr.setLayoutY(100);
		cr.setRadius(60);
		cr.setFill(Color.VIOLET);
		cr.setStroke(Color.GRAY);
		cr.setStrokeWidth(3);
		
		Rectangle rc = new Rectangle();
		rc.setLayoutX(150);
		rc.setLayoutY(50);
		rc.setWidth(60);
		rc.setHeight(20);
		rc.setFill(Color.GREEN);
		rc.setStroke(Color.YELLOW);
		rc.setStrokeWidth(5);
		rc.setArcHeight(10);
		rc.setArcWidth(10);
	/*	Synthesizer syn = MidiSystem.getSynthesizer();
		syn.open();
		MidiChannel cana = syn.getChannels()[0];
		cana.programChange(0);
		cana.noteOn(0, 50);  */
		
		ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
		data.add(new PieChart.Data("china",120));
		data.add(new PieChart.Data("usa",130));
		data.add(new PieChart.Data("UK",126));
		PieChart pc = new PieChart();
		pc.setTitle("population");
		pc.setData(data);
//		root.getChildren().add(pc);
		
		 CategoryAxis xAxis = new CategoryAxis();
		 xAxis.setLabel("Country");
		 NumberAxis yAxis = new NumberAxis();
		 yAxis.setLabel("Population (in millions)");
		 
		 BarChart<String,Number> barch = new BarChart<>(xAxis,yAxis);
		 XYChart.Series<String, Number> seriesChina = new XYChart.Series<String, Number>();
		 seriesChina.setName("China");
		 seriesChina.getData().add(new XYChart.Data<String, Number>("1950", 555));
		 seriesChina.getData().add(new XYChart.Data<String, Number>("2000", 1275));
		 seriesChina.getData().add(new XYChart.Data<String, Number>("2010", 1182));
		 seriesChina.getData().add(new XYChart.Data<String, Number>("2050", 1149));
		 XYChart.Series<String, Number> seriesusa = new XYChart.Series<String, Number>();
		 seriesusa.setName("USA");
		 seriesusa.getData().add(new XYChart.Data<String, Number>("1950", 500));
		 seriesusa.getData().add(new XYChart.Data<String, Number>("2002", 1855));
		 seriesusa.getData().add(new XYChart.Data<String, Number>("2015", 1442));
		 seriesusa.getData().add(new XYChart.Data<String, Number>("2050", 1009));
		 XYChart.Series<String, Number> seriesuk = new XYChart.Series<String, Number>();
		 seriesuk.setName("Uk");
		 seriesuk.getData().add(new XYChart.Data<String, Number>("1999", 440));
		 seriesuk.getData().add(new XYChart.Data<String, Number>("2005", 2055));
		 seriesuk.getData().add(new XYChart.Data<String, Number>("2018", 1692));
		 seriesuk.getData().add(new XYChart.Data<String, Number>("2030", 1049));
		 ObservableList<XYChart.Series<String, Number>> datac =FXCollections.<XYChart.Series<String, Number>>observableArrayList();
		 datac.add(seriesChina);
		 datac.add(seriesusa);
		 datac.add(seriesuk);
		 barch.setData(datac);
//		 root.getChildren().add(barch);
		 
		 NumberAxis xais = new NumberAxis();
		 yAxis.setLabel("années");
		 NumberAxis yais = new NumberAxis();
		 yAxis.setLabel("Population (in millions)");
		 LineChart<Number,Number> lnc = new LineChart<>(xais,yais);
		 XYChart.Series<Number, Number> seriesus = new XYChart.Series<Number, Number>();
		 seriesus.setName("USA");
		 seriesus.getData().add(new XYChart.Data<Number, Number>(500, 1040));
		 seriesus.getData().add(new XYChart.Data<Number, Number>(1055, 1002));
		 seriesus.getData().add(new XYChart.Data<Number, Number>(442, 205));
		 seriesus.getData().add(new XYChart.Data<Number, Number>(1019, 1220));
		 XYChart.Series<Number, Number> seriuk = new XYChart.Series<Number, Number>();
		 seriuk.setName("Uk");
		 seriuk.getData().add(new XYChart.Data<Number, Number>(440, 179));
		 seriuk.getData().add(new XYChart.Data<Number, Number>(1055, 905));
		 seriuk.getData().add(new XYChart.Data<Number, Number>(692, 1008));
		 seriuk.getData().add(new XYChart.Data<Number, Number>(340, 1020));	 
		 ObservableList<XYChart.Series<Number,Number>> datch = FXCollections.<XYChart.Series<Number, Number>>observableArrayList();
		 datch.add(seriesus);
		 datch.add(seriuk);
		 lnc.setData(datch);
		 lnc.setStyle("-fx-create-symbols: false;");
		// root.getChildren().add(lnc);
		 
		 
		 
		 TabPane tpan = new TabPane();
		 tpan.setSide(Side.LEFT);
		 tpan.setTabMinWidth((sc.getHeight()-100)/5);
		 tpan.setTabMinHeight(55);
		 tpan.setTabMaxWidth(200); tpan.setTabMaxHeight(150);

		 BorderPane bpan = new BorderPane();
		 
		 Tab tb[] = new Tab[5];
		 HBox hb[] = new HBox[5];
		 String stb[] = {"-fx-background-color: rgba(29,7,150,0.4);","-fx-background-color: rgba(37,10,197,0.4);","-fx-background-color: rgba(50,16,243,0.4);","-fx-background-color: rgba(88,61,265,0.4);","-fx-background-color: rgba(113,89,297,0.4);"};
		 Image im[] = new Image[5];
		 im[0] = new Image(new File("home.png").toURI().toString());
		 im[1] = new Image(new File("tab.png").toURI().toString());
		 im[2] = new Image(new File("graphe.png").toURI().toString());
		 im[3] = new Image(new File("compare.png").toURI().toString());
		 im[4] = new Image(new File("download.png").toURI().toString());
		 for(int i=0;i<5;i++)
		 {
			 tb[i] = new Tab();
			 ImageView iv = new ImageView();
			 iv.setFitHeight(55);
	         iv.setFitWidth(55);
	         iv.setPreserveRatio(true);
	         iv.setImage(im[i]);
			 tb[i].setGraphic(iv);
			 tb[i].setClosable(false);
			 tb[i].setStyle(stb[i]);
			 hb[i] = new HBox();
			 if(i==0) hb[i].getChildren().add(pc);
			 if(i==1) hb[i].getChildren().add(barch);
			 if(i==2) hb[i].getChildren().add(lnc);
			 if(i==3)hb[i].getChildren().add(b);
			 if(i==4)
			 {
					hb[i].getChildren().add(cr);
					hb[i].getChildren().add(rc);
			 }
			 hb[i].setAlignment(Pos.TOP_CENTER);
			 tb[i].setContent(hb[i]);
			 tpan.getTabs().add(tb[i]);
		 }
		 bpan.prefHeightProperty().bind(sc.heightProperty());
		 bpan.prefWidthProperty().bind(sc.widthProperty());
		 bpan.setCenter(tpan);
		 root.getChildren().add(bpan);
	
/*		root.getChildren().add(b);
		root.getChildren().add(cr);
		root.getChildren().add(rc);  */
		
		stage.setScene(sc);
		stage.show();
		
		datafen df = new datafen();
		controller co = new controller(this,df);
	}
	
	public String button_text()
	{
		return b.getText();
	}
	public void button_set(String s)
	{
		b.setText(s);
	}
	public Button button()
	{
		return b;
	}
}


class datafen
{
	String data;
	public datafen()
	{
		System.out.println("data yeaa");
	}
	public void setd(String d)
	{
		data = d;
	}
	public String getd()
	{
		return data;
	}
	public int getsi()
	{
		return data.length();
	}
}

class controller
{
	private jfx fu ;
	private datafen df;
	public controller(jfx f,datafen d)
	{
		fu = f; df = d;
		Button bt = fu.button();
		bt.setOnAction(new listen());
	}
	
	class listen implements EventHandler <ActionEvent>
	{


		@Override
		public void handle(ActionEvent arg0) {
			String st = fu.button_text();
			df.setd(st);
			int sk = df.getsi();
			fu.button_set(df.getd()+" size : "+sk);
			
		}
		
	}
	

}


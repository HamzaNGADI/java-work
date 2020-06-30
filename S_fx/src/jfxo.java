import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class jfxo extends Application
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
		pc.setLayoutX(10); 
		 pc.setLayoutY(10);
		
    	root.getChildren().add(pc);
		
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
		 barch.setLayoutX(400); 
		 barch.setLayoutY(60);
		 root.getChildren().add(barch);
		 
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
		 lnc.setLayoutX(10); 
		 lnc.setLayoutY(400);
		
		 root.getChildren().add(lnc);
		 
		 
		 
		 	
/*		root.getChildren().add(b);
		root.getChildren().add(cr);
		root.getChildren().add(rc);  */
		
		stage.setScene(sc);
		stage.show();
		
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
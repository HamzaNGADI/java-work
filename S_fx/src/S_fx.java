
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;
import javax.swing.JFrame;

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

public class S_fx{

	
	public static void main(String[] args) {
		System.out.println("work it !!");
		
		Application.launch(jfxo.class,args);

//	Application.launch(jfx.class,args);
	
	//fn f = new fn();
		//datafen d = new datafen();
//		fen f = new fen();
//		jfx.launch();
	}

/*	@Override
	public void start(Stage stage) throws Exception {

	}  */

}

class fn extends JFrame
{
	public fn()
	{
		setTitle("fx");
		setSize(900,900);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new FlowLayout()) ;
		
		DefaultPieDataset datp = new DefaultPieDataset();
		datp.setValue("homme", 15);
		datp.setValue("femme", 8);
		
		JFreeChart pc = ChartFactory.createPieChart("humains", datp, true,false,false); 
		ChartPanel cpa = new ChartPanel(pc);
	    cpa.setPreferredSize(new Dimension(350,300));
	    getContentPane().add(cpa);      revalidate(); repaint();
	}
}





package projekt.controllers;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import projekt.JsonReader;
import projekt.Parametry;

public class QuizController extends MenuScreenController {
	
	private StackPaneController stackPaneController;
	
	protected Parametry par = getParamatery();
	
	private JsonReader json = new JsonReader();
	
	String[] tresc_pytania = json.getPytania().getTrescPytania();
	String[][] opcje = 	json.getPytania().getOpcje();
	String[] odpowiedzi = json.getPytania().getOdpowiedzi();
	

    @FXML
    private Label NrPytania;

    @FXML
    private Label TrescPytania;

    @FXML
    private Button ButtonA;

    @FXML
    private Button ButtonB;

    @FXML
    private Button ButtonC;

    @FXML
    private Button ButtonD;

    @FXML
    private Label OdpA;

    @FXML
    private Label OdpB;

    @FXML
    private Label OdpC;

    @FXML
    private Label OdpD;

    @FXML
    private Label TimeLeft;
    
	private String odp;
	private int index;
	private int random;
	protected int ilosc_pytan = tresc_pytania.length;
	private int czas=10;
	
	List<Integer> lista = new ArrayList<Integer>();

    @FXML
    void OnActionButton(ActionEvent e) {
		ButtonA.setDisable(true);
		ButtonB.setDisable(true);
		ButtonC.setDisable(true);
		ButtonD.setDisable(true);
		
		
		if(e.getSource()==ButtonA) {
			odp= "A";
			if(par.getCzyLosowac()==true) {
			if(odp.equals(odpowiedzi[random])) 
				par.addPunkty();
			}else {
			if(odp.equals(odpowiedzi[index])) 
				par.addPunkty();	
			}
		}
		if(e.getSource()==ButtonB) {
			odp= "B";
			if(par.getCzyLosowac()==true) {
			if(odp.equals(odpowiedzi[random])) 
				par.addPunkty();
			}else {
			if(odp.equals(odpowiedzi[index]))
				par.addPunkty();	
			}
		}
		if(e.getSource()==ButtonC) {
			odp= "C";
			if(par.getCzyLosowac()==true) {
				if(odp.equals(odpowiedzi[random]))   
				par.addPunkty();
			}else {
		    if(odp.equals(odpowiedzi[index]))
				par.addPunkty();	
			}
		}
		if(e.getSource()==ButtonD) {
			odp= "D";
			if(par.getCzyLosowac()==true) {
			if(odp.equals(odpowiedzi[random]))  
				par.addPunkty();
			}else {
			if(odp.equals(odpowiedzi[index])) 
				par.addPunkty();	
			}
		}
		if(par.getCzyLosowac()==false)
		DisplayOdp();
		else
		DisplayOdpShuffle();
    	
    }
    
    Timeline timer = new Timeline(
            new KeyFrame(Duration.seconds(1), 
            new EventHandler<ActionEvent>() {

            	@Override
            	public void handle(ActionEvent event) {
            		czas--;
            		TimeLeft.setText(String.valueOf(czas));
            		if(czas<=0) {
            			if(par.getCzyLosowac()==false)
            				DisplayOdp();
            				else
            				DisplayOdpShuffle();
            		}
            	}
            }));
    
	public void nextQuestion() {
		
		if(index>=ilosc_pytan) {
			results();
		}
		else {
			NrPytania.setText("Pytanie "+(index+1)+"/"+ilosc_pytan);
			TrescPytania.setText(tresc_pytania[index]);
			OdpA.setText(opcje[index][0]);
			OdpB.setText(opcje[index][1]);
			OdpC.setText(opcje[index][2]);
			OdpD.setText(opcje[index][3]);
			if(par.getCzyTimer()==true) {
			timer.setCycleCount(Timeline.INDEFINITE);
			timer.play();
			}else {
				TimeLeft.setVisible(false);
			}
		}
	}
	
	
	public void nextQuestionShuffle() {
		
		if(index>=ilosc_pytan) {
			results();
		}
		else {
			random=randomElement(lista);
			NrPytania.setText("Pytanie "+(index+1)+"/"+ilosc_pytan);
			TrescPytania.setText(tresc_pytania[random]);
			OdpA.setText(opcje[random][0]);
			OdpB.setText(opcje[random][1]);
			OdpC.setText(opcje[random][2]);
			OdpD.setText(opcje[random][3]);
			if(par.getCzyTimer()==true) {
			timer.setCycleCount(Timeline.INDEFINITE);
			timer.play();
			}else {
				TimeLeft.setVisible(false);
			}
			lista.removeIf( name -> name.equals(random));
		}
	}
    
public void DisplayOdp() {
	if(par.getCzyTimer()==true) 
		timer.stop();
		
		ButtonA.setDisable(true);
		ButtonB.setDisable(true);
		ButtonC.setDisable(true);
		ButtonD.setDisable(true);
		
		if(!odpowiedzi[index].equals("A"))
			OdpA.setStyle("-fx-text-fill: #F92C00;");
		else
			OdpA.setStyle("-fx-text-fill: #0BA900;");
		if(!odpowiedzi[index].equals("B"))
			OdpB.setStyle("-fx-text-fill: #F92C00;");
		else
			OdpB.setStyle("-fx-text-fill: #0BA900;");			
		if(!odpowiedzi[index].equals("C"))
			OdpC.setStyle("-fx-text-fill: #F92C00;");
		else
			OdpC.setStyle("-fx-text-fill: #0BA900;");			
		if(!odpowiedzi[index].equals("D"))
			OdpD.setStyle("-fx-text-fill: #F92C00;");
		else
			OdpD.setStyle("-fx-text-fill: #0BA900;");	
		
	    Timeline pause = new Timeline(
	            new KeyFrame(Duration.seconds(2), 
	            new EventHandler<ActionEvent>() {

	            	@Override
	            	public void handle(ActionEvent event) {
	            		OdpA.setStyle("-fx-text-fill: #000000;");
	            		OdpB.setStyle("-fx-text-fill: #000000;");
	            		OdpC.setStyle("-fx-text-fill: #000000;");
	            		OdpD.setStyle("-fx-text-fill: #000000;");
	    				
	    				odp = " ";
	    				czas=10;
	    				TimeLeft.setText(String.valueOf(czas));
	    				index++;
	    				ButtonA.setDisable(false);
	    				ButtonB.setDisable(false);
	    				ButtonC.setDisable(false);
	    				ButtonD.setDisable(false);
	    				nextQuestion();
	            	}
	            }));
	    pause.setCycleCount(1);
		pause.play();
}

public void DisplayOdpShuffle() {
	if(par.getCzyTimer()==true) 
		timer.stop();
		
		ButtonA.setDisable(true);
		ButtonB.setDisable(true);
		ButtonC.setDisable(true);
		ButtonD.setDisable(true);
		
		if(!odpowiedzi[random].equals("A"))
			OdpA.setStyle("-fx-text-fill: #F92C00;");
		else
			OdpA.setStyle("-fx-text-fill: #0BA900;");
		if(!odpowiedzi[random].equals("B"))
			OdpB.setStyle("-fx-text-fill: #F92C00;");
		else
			OdpB.setStyle("-fx-text-fill: #0BA900;");			
		if(!odpowiedzi[random].equals("C"))
			OdpC.setStyle("-fx-text-fill: #F92C00;");
		else
			OdpC.setStyle("-fx-text-fill: #0BA900;");			
		if(!odpowiedzi[random].equals("D"))
			OdpD.setStyle("-fx-text-fill: #F92C00;");
		else
			OdpD.setStyle("-fx-text-fill: #0BA900;");			
		
	    Timeline pause = new Timeline(
	            new KeyFrame(Duration.seconds(2), 
	            new EventHandler<ActionEvent>() {

	            	@Override
	            	public void handle(ActionEvent event) {
	            		OdpA.setStyle("-fx-text-fill: #000000;");
	            		OdpB.setStyle("-fx-text-fill: #000000;");
	            		OdpC.setStyle("-fx-text-fill: #000000;");
	            		OdpD.setStyle("-fx-text-fill: #000000;");
	    				
	    				odp = " ";
	    				czas=10;
	    				TimeLeft.setText(String.valueOf(czas));
	    				index++;
	    				ButtonA.setDisable(false);
	    				ButtonB.setDisable(false);
	    				ButtonC.setDisable(false);
	    				ButtonD.setDisable(false);
	    				if(par.getCzyLosowac()==false)
	    				nextQuestion();
	    				else
	    				nextQuestionShuffle();
	            	}
	            }));
	    pause.setCycleCount(1);
		pause.play();
}

public static int randomElement(List<Integer> lista) {
    Random rand = new Random();
    int randomElement = lista.get(rand.nextInt(lista.size()));
	return randomElement;
	}


@FXML
public void initialize() {
	for(int n=0; n<tresc_pytania.length; n++)
		lista.add(n);
	if(par.getCzyLosowac()==false)
	nextQuestion();
	else
	nextQuestionShuffle();
}
	

public void results(){
	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/EndScreen.fxml"));
	Pane pane = null;
	try {
		pane=loader.load();
	} catch (IOException e) {
		e.printStackTrace();
	}
	EndScreenController endScreenController = loader.getController();
	endScreenController.setStackPaneController(stackPaneController);
	stackPaneController.setScreen(pane);
}


public void setStackPaneController(StackPaneController stackPaneController) {
	this.stackPaneController= stackPaneController;
}


}

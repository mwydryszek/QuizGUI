package projekt.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class EndScreenController extends QuizController{

	private StackPaneController stackPaneController;
	
    @FXML
    private Label wynik;

    @FXML
    private Label procenty;

    @FXML
    private Button PowrotButton;
    
    private int proc;

    @FXML
    void powrotMenu() {
    	par.setPunkty(0);
    	stackPaneController.loadMenuScreen();
    }
    
    
    @FXML
    public void initialize() {
    	wynik.setText("Twój wynik to: "+par.getPunkty()+"/"+ilosc_pytan);
    	proc = (int)((par.getPunkty()/(double)ilosc_pytan)*100);
    	procenty.setText(proc+"%");
    }
    
	public void setStackPaneController(StackPaneController stackPaneController) {
		this.stackPaneController= stackPaneController;
	}

}

package it.polito.tdp.indonumero;

import java.awt.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class IndoNumeroController {

	//DEVO GESTIRE SIA LO STATO DEL GIOCO CHE L'INTERFACCIA
	private Model model;
    public void setModel(Model model) {
		this.model = model;
	}

	@FXML
    private Button ButtonNuova;

    @FXML
    private TextField txtCurrent;

    @FXML
    private TextField txtMax;

    @FXML
    private HBox boxGioco;

    @FXML
    private TextField txtTentativo;

    @FXML
    private TextArea txtLog;

    @FXML
    void handleNuova(ActionEvent event) {
    	model.newGame();
    	//se sono inGame devo disabilitare il button di nuovaPArtita.
    
    	ButtonNuova.setDisable(true);
    	boxGioco.setDisable(false); //false se lo abiliti --> sto usando il metodo di disabilazione non di abilitazione
    	txtCurrent.setText(String.format("%d", model.getTentativi()));
    	txtMax.setText(String.format("%d", model.getTMAX()));
    	
    	txtLog.clear();
    	txtTentativo.clear();
    	
    	txtLog.setText(String.format("Indovina un numero tra %d e %d\n", 1,model.getNMAX()));
    	
    	
    }

    @FXML
    void handleProva(ActionEvent event) {
    	
    	String numS = txtTentativo.getText();
    	
    	if(numS.length()==0) {
    		txtLog.appendText("Devo inserire un numero\n");
    		return;
    	}
    	try{
    		int num = Integer.parseInt(numS);
    		
    		//verifica se il num è fuori range
    		/*if(num<1 || num>model.getNMAX()) */
    		//modificando il model in modo da non avere ripetizione di codice ottengo
    		
    		if(!model.valoreValido(num)){
    			txtLog.appendText("Valore fuori dall'intervallo consentito \n");
    			return;}
    		
    		int risultato= model.tentativo(num);
    		txtCurrent.setText(String.format("%d", model.getTentativi()));
    		
    		if(risultato==0)
    			txtLog.appendText("Hai vinto!\n");
    		else if(risultato<0)
    			txtLog.appendText("Troppo basso!\n");
    		else
    			txtLog.appendText("Troppo alto!\n");
    		
    		if(!model.isInGame()) {
    		//la partita è finita
    			//cioè sono finiti i tentativi oppure ho vinto
    			if(risultato!=0) {
    				txtLog.appendText("Hai perso!\n");
    				txtLog.appendText(String.format("Il numero segreto era: %d\n", model.getSegreto()));
    			}
    			boxGioco.setDisable(true);
    			ButtonNuova.setDisable(false);
    		}
    		
    		
    	}catch(NumberFormatException ex) {
    		txtLog.appendText("Il dato inserito non è numerico\n");
    		return;
    	}
    	
    	

    }

}

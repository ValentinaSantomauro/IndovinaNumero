package it.polito.tdp.indonumero;

import java.awt.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class IndoNumeroController {
	
	private int NMAX=100;
	private int TMAX= 7;
	
	private int segreto; //numero da indovinare
	private int tentativi; // tentativi già fatti 
	
	private boolean inGame = false; //variab. che mi dice se c'è una partita in corso
	
	//DEVO GESTIRE SIA LO STATO DEL GIOCO CHE L'INTERFACCIA
	

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
    	
    	this.segreto = (int) (Math.random()*NMAX) + 1;  //rand mi dà un num da 0 a 0.99 * nmax, diventa tra 0 e 99 --> io voglio fino a 100
    	
    	this.tentativi=0;
    	this.inGame=true;
    	//se sono inGame devo disabilitare il button di nuovaPArtita.
    	
    	ButtonNuova.setDisable(true);
    	boxGioco.setDisable(false); //false se lo abiliti --> sto usando il metodo di disabilazione non di abilitazione
    	txtCurrent.setText(String.format("%d", this.tentativi));
    	txtMax.setText(String.format("%d", this.TMAX));
    	
    	txtLog.clear();
    	txtTentativo.clear();
    	
    	txtLog.setText(String.format("Indovina un numero tra %d e %d\n", 1,NMAX));
    	
    	
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
    		if(num == this.segreto) {
    			//ha indovinato
    			txtLog.appendText("Hai vinto!\n");
    			
    			//chiudi la partita
    			//disabilita l'area di gioco e abilita il bottone di nuova partita
    			boxGioco.setDisable(true);
    			ButtonNuova.setDisable(false);
    			this.inGame = false;
    			
    		}else {
    			//tentativo errato
    			this.tentativi++;
    			txtCurrent.setText(String.format("%d", this.tentativi));
    			if(this.tentativi == this.TMAX)
    			{
    				//ha perso
    				txtLog.appendText(String.format("Hai perso! Il numero era : %d\n", this.segreto));
    				//chiudo la partita
    				boxGioco.setDisable(true);
        			ButtonNuova.setDisable(false);
        			this.inGame = false;
    			}else {
    				//sono ancora in gioco
    				
    				if(num<segreto) {
    					//troppo basso
    					txtLog.appendText("Troppo basso\n");
    					
    				}else {
    					//troppo alto
    					txtLog.appendText("Troppo alto\n");
    				}
    			}
    			
    			
    		}
    		
    		
    	}catch(NumberFormatException ex) {
    		txtLog.appendText("Il dato inserito non è numerico\n");
    		return;
    	}
    	
    	

    }

}

package it.polito.tdp.indonumero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class IndoNumeroController {

	private int NMAX = 100;
	private int TMAX =  7;
	private int segreto; //numero da indovinare
	private int tentativi; //tentativi già fatti
	private boolean inGame = false; 
	
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
   
    //in controller devo definire sia l'algoritmo di gioco che l'interfaccia utente
    @FXML
    void handleNuova(ActionEvent event) {
    	
    	//deve decidere qual è il numero segreto
    	this.segreto= (int)(Math.random()*NMAX)+1;
    	this.tentativi =0; 
    	this.inGame= true; //STAI GIOCANDO? SI O NO
    	ButtonNuova.setDisable(true); //
    	boxGioco.setDisable(false); //si lo voglio
    	txtCurrent.setText(String.format("%d", this.tentativi));
    	txtMax.setText(String.format("%d", this.TMAX));
    	txtLog.clear(); //serve per ripulire le caselle di testo dopo ogni partita
    	txtTentativo.clear();
    	
    	txtLog.setText(String.format("Indovina un numero tra %d e %d\n", 1, NMAX));
    	
    	
    }

    @FXML
    void handleProva(ActionEvent event) {
    	
    	// l'utente può aver inserito un valore sbagliato... devo fare dei cntrolli
    	//se ha inserito il valore esatto termino la partita
    	//se è sbagliato dico se è troppo alto o basso
    	//se era l'ultimo tentativo ha perso
    	String numS = txtTentativo.getText();
    	if (numS.length()==0) {
    		txtLog.appendText("Devi inserire un numero\n");
    		return;
    	}
    	try {
    	int num= Integer.parseInt(numS);
    	//puo generare delle eccezioni, le devo gestire
    	//ho inserito un intero
    	if(num==this.segreto) {
    		//ha indovinato
    		txtLog.appendText("Hai vinto!\n");
    		
    		//chiudi la partita, disabilito l'area di gioco e riabilito il botton nuova partita
    		boxGioco.setDisable(true);
    		ButtonNuova.setDisable(false);
    		this.inGame=false; //SE HO VINTO NON STO PIù GIOCANDO -> FALSE
    	}else {
    		//tentativo errato
    		this.tentativi++;
    		txtCurrent.setText(String.format("%d", this.tentativi));
    		 if (this.tentativi==this.TMAX) {
    			 //hai perso
    			 txtLog.appendText(String.format("Hai perso, il numero era: %d \n", this.segreto));
    		 }else {
    			 if(num<segreto) {
    				 //troppobasso
    				 txtLog.appendText("Troppo basso\n");
    			 }else {
    				 //troppo alto
    				 txtLog.appendText("Troppo ato\n");
    			 }
    		 }
    		
    		
    	}
    	}catch (NumberFormatException ex) {
    		txtLog.appendText("il dato inserito non è numerico\n");
    		return;
    	}
    	

    }

}

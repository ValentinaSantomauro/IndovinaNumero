package it.polito.tdp.indonumero;

import java.security.InvalidParameterException;

public class Model {
	
	private int NMAX=100;
	
	private int TMAX= 7;
	
	private int segreto; //numero da indovinare
	private int tentativi; // tentativi già fatti 
	
	

	private boolean inGame = false; //variab. che mi dice se c'è una partita in corso
	
	

	public Model() {
	this.inGame=false;	
	}
	/**
	 * Avvia una partita generando un nuovo numero segreto
	 */
	public void newGame() {
		this.segreto = (int) (Math.random()*NMAX) + 1;  //rand mi dà un num da 0 a 0.99 * nmax, diventa tra 0 e 99 --> io voglio fino a 100
    	
    	this.tentativi=0;
    	this.inGame=true;
    	//se sono inGame devo disabilitare il button di nuovaPArtita.
	}
	
	/**
	 * fai un tentativo per indovinare il numero segreto.
	 * non posso fare tentativi se:
	 * -il tentativo è fuori range
	 * -se è stato esaurito il numero dei tentativi possibili
	 * @param t valore numerico del tentativo
	 * @return 0 se ho indovinato, +1 se è troppo grande, -1 se è troppo piccolo
	 */
	
	public int tentativo(int t) {
		
		if(!inGame) {
			throw new IllegalStateException("Partita non attiva");
		}
		if(!valoreValido(t)) {
			throw new InvalidParameterException("Tentativo fuori range");
		}
		
		this.tentativi++; //se è il tentativo max--partita finita
		
		if(this.tentativi==this.TMAX) {
			this.inGame=false;
		}
		
		if(t==this.segreto) {
			this.inGame=false;
			return 0;} //partita vinta--partita finita
		
		if(t<this.segreto)
			return -1;
		return +1;
	}
	/**
	 * Controlla se il tentativo rispetta le regole formali del gioco, cioè se è nel range [1,NMAX]
	 * @param tentativo
	 * @return true se il tentativo è valido
	 */
	
	public boolean valoreValido(int tentativo) {
		return tentativo>-1 && tentativo<= this.NMAX;
	}
	
	public boolean isInGame() {
		return inGame;
	}
	public int getTentativi() {
		return this.tentativi;
	}
	public int getNMAX() {
		return NMAX;
	}
	public int getTMAX() {
		return TMAX;
	}
	public int getSegreto() {
		return this.segreto;
	}
}

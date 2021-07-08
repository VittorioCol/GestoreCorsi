/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.corsi;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Model;
import it.polito.tdp.corsi.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtPeriodo"
    private TextField txtPeriodo; // Value injected by FXMLLoader

    @FXML // fx:id="txtCorso"
    private TextField txtCorso; // Value injected by FXMLLoader

    @FXML // fx:id="btnCorsiPerPeriodo"
    private Button btnCorsiPerPeriodo; // Value injected by FXMLLoader

    @FXML // fx:id="btnNumeroStudenti"
    private Button btnNumeroStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="btnStudenti"
    private Button btnStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="btnDivisioneStudenti"
    private Button btnDivisioneStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML
    void corsiPerPeriodo(ActionEvent event) {
    	
    	txtRisultato.clear();
    	
    	String periodo=txtPeriodo.getText();
    	Integer p;
    	
    	try {
    		p = Integer.parseInt(periodo);
    	}
    	catch (NumberFormatException ne){
    		txtRisultato.setText("Devi inserire un numero (1 o 2) per il periodo didattico");
    		return;
    	}
    	catch(NullPointerException npe) {
    		txtRisultato.setText("Devi inserire un numero (1 o 2) per il periodo didattico");
    		return;
    	}
    	if(p != 1 && p != 2) {
    		txtRisultato.setText("Devi inserire un numero (1 o 2) per il periodo didattico");
    		return;
    	}
    	List<Corso> corsi = this.model.getCorsiByPeriodo(p);
    	for(Corso c: corsi) {
    		txtRisultato.appendText(c.toString() + "\n");
    	}
    }

    @FXML
    void numeroStudenti(ActionEvent event) {
    	
    	txtRisultato.clear();
    	
    	String periodo=txtPeriodo.getText();
    	int p;
    	try {
    		p = Integer.parseInt(periodo);
    	}
    	catch (NumberFormatException ne){
    		txtRisultato.setText("Devi inserire un numero (1 o 2) per il periodo didattico");
    		return;
    	}
    	catch(NullPointerException npe) {
    		txtRisultato.setText("Devi inserire un numero (1 o 2) per il periodo didattico");
    		return;
    	}
    	if(p != 1 && p != 2) {
    		txtRisultato.setText("Devi inserire un numero (1 o 2) per il periodo didattico");
    		return;
    	}
    	Map<Corso,Integer> corsi=this.model.contaIscrittiCorso(p);
    	for(Corso c: corsi.keySet()) {
    		txtRisultato.appendText(c.toString());
    		Integer n= corsi.get(c);
    		txtRisultato.appendText("\t" + n + "\n");
    	}
    }

    @FXML
    void stampaDivisione(ActionEvent event) {
    	txtRisultato.clear();
    	
    	String corso=txtCorso.getText();
    	if(!model.esisteCorso(corso)) {
    		txtRisultato.appendText("il corso non esiste");
    		return;
    	}
    	Map<String,Integer> studenti= this.model.getStudentiByCorsoGroupedByCDS(corso);
    	
    	if(studenti.size()==0) {
    		txtRisultato.setText("Il corso non ha iscritti");
    		return;
    	}
    	for(String s: studenti.keySet()) {
    		txtRisultato.appendText(s + " " + studenti.get(s)+"\n");
    	}
    }

    @FXML
    void stampaStudenti(ActionEvent event) {
    	txtRisultato.clear();
    	
    	String corso=txtCorso.getText();
    	if(!model.esisteCorso(corso)) {
    		txtRisultato.appendText("il corso non esiste");
    		return;
    	}
    	List<Studente> studenti= this.model.getStudentiByCorso(corso);
    	
    	if(studenti.size()==0) {
    		txtRisultato.setText("Il corso non ha iscritti");
    		return;
    	}
    	for(Studente s: studenti) {
    		txtRisultato.appendText(s.toString()+ "\n");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtPeriodo != null : "fx:id=\"txtPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCorso != null : "fx:id=\"txtCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCorsiPerPeriodo != null : "fx:id=\"btnCorsiPerPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnNumeroStudenti != null : "fx:id=\"btnNumeroStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnStudenti != null : "fx:id=\"btnStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDivisioneStudenti != null : "fx:id=\"btnDivisioneStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
    
    
}
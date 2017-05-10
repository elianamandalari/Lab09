package it.polito.tdp.metrodeparis;


	

	import java.net.URL;
	import java.util.ResourceBundle;

import it.polito.tdp.metrodeparis.model.Fermata;
import it.polito.tdp.metrodeparis.model.Model;
import javafx.event.ActionEvent;
	import javafx.fxml.FXML;
	import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

	public class MetroDeParisController {

	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private ComboBox<Fermata> cmbPartenza;

	    @FXML
	    private ComboBox<Fermata> cmbArrivo;
	    @FXML
	    private TextArea tstResult;
	    
        Model model;
	    @FXML
	    void doCalcolaPercorso(ActionEvent event) {
	    	model.creaGrafo();
	    	
	    	tstResult.appendText("Percorso");
	    	for(Fermata f:model.camminoMinimo(cmbPartenza.getValue(), cmbArrivo.getValue())){
	    	tstResult.appendText("\n"+f+ "\n");
	    	}
	    	
	    	tstResult.appendText("Tempo : "+(model.pesocamminoMinimo(cmbPartenza.getValue(), cmbArrivo.getValue()))/60+"\n");

	    }
	    
	    public void setModel(Model model)
	    {
	    	this.model=model;
	    	cmbPartenza.getItems().addAll(this.model.getFermate());
	    	cmbArrivo.getItems().addAll(this.model.getFermate());
	    }
	    @FXML
	    void initialize() {
	        assert cmbPartenza != null : "fx:id=\"cmbPartenza\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
	        assert cmbArrivo != null : "fx:id=\"cmbArrivo\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
	        
	    }
	    
	    
	}


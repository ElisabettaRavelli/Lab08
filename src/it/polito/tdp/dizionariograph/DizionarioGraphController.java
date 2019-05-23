package it.polito.tdp.dizionariograph;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.dizionariograph.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioGraphController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNumeroLettere;

    @FXML
    private TextField txtParola;

    @FXML
    private Button btnGrafo;

    @FXML
    private Button btnVicini;

    @FXML
    private Button btnGradoMax;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    @FXML
    void doGeneraGrafo(ActionEvent event) {
    	
    	int numeroLettere = Integer.parseInt(txtNumeroLettere.getText());
    	model.createGraph(numeroLettere);
    	txtResult.appendText("Il grafo è stato generato\n");
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtNumeroLettere.clear();
    	txtParola.clear();
    	txtResult.clear();

    }

    @FXML
    void doTrovaGradoMax(ActionEvent event) {
    	int max = model.findMaxDegree();
    	String sMax = model.getVerticeGradoMax();
    	List<String> vicini = model.displayNeighbours(sMax);
    	txtResult.clear();
    	txtResult.appendText("Grado Max: "+ max + "\nVertice Max: "+sMax+"\n");
    	for(String tmp: vicini) {
    		txtResult.appendText(tmp + "\n");
    	}
    }

    @FXML
    void doTrovaVicini(ActionEvent event) {
    	txtResult.clear();
    	String vicino = txtParola.getText();
    	List<String> res = model.displayNeighbours(vicino);
    	for (String tmp : res) {
    		txtResult.appendText(tmp + "\n");
    	}
    }

    @FXML
    void initialize() {
        assert txtNumeroLettere != null : "fx:id=\"txtNumeroLettere\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGrafo != null : "fx:id=\"btnGrafo\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnVicini != null : "fx:id=\"btnVicini\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGradoMax != null : "fx:id=\"btnGradoMax\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model=model;
    }
}

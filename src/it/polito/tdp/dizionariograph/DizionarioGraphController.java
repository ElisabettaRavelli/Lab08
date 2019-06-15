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
    	txtResult.clear();
    	txtParola.clear();
    	
    	try{ 
    		int numeroLettere = Integer.parseInt(txtNumeroLettere.getText());
    		model.createGraph(numeroLettere);
    		txtResult.appendText("Il grafo è stato generato\n");
    		
    		txtNumeroLettere.setDisable(true);
			btnGrafo.setDisable(true);
			txtParola.setDisable(false);
			btnVicini.setDisable(false);
			btnGradoMax.setDisable(false);

    	
    	} catch (NumberFormatException nfe) {
			txtResult.setText("Inserire un numero corretto di lettere!");
		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtNumeroLettere.clear();
    	txtParola.clear();
    	txtResult.clear();
    	
    	txtNumeroLettere.setDisable(false);
		txtParola.setDisable(true);
		btnGrafo.setDisable(false);
		btnVicini.setDisable(true);
		btnGradoMax.setDisable(true);

    }

    @FXML
    void doTrovaGradoMax(ActionEvent event) {
    	try{
    		int max = model.findMaxDegree();
    		String sMax = model.getVerticeGradoMax();
    		List<String> vicini = model.displayNeighbours(sMax);
    		txtResult.clear();
    		txtResult.appendText("Grado Max: "+ max + "\nVertice Max: "+sMax+"\n");
    		txtResult.appendText("Vicini del vertice massimo:\n");
    		for(String tmp: vicini) {
    		txtResult.appendText(tmp + "\n");
    		}
    	}catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
    }

    @FXML
    void doTrovaVicini(ActionEvent event) {
    	try {
    		txtResult.clear();
    		String vicino = txtParola.getText();
    		if(vicino==null || vicino.length()==0) {
    			txtResult.appendText("Inserisci una parola da cercare\n");
    			return;
    		}
    		List<String> res = model.displayNeighbours(vicino);
    		if(res!=null) {
    			for (String tmp : res) {
    				txtResult.appendText(tmp + "\n");
    			}
    		} else {
    			txtResult.appendText("Non è stato trovato nessun risultato");
    		}
    	} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
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
        
        txtParola.setDisable(true);
		btnVicini.setDisable(true);
		btnGradoMax.setDisable(true);

    }
    
    public void setModel(Model model) {
    	this.model=model;
    }
}

package it.polito.tdp.dizionariograph.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionariograph.db.WordDAO;

public class Model {
	
	private Graph<String, DefaultEdge> grafo;
	private String verticeGradoMax;
	
	public String getVerticeGradoMax() {
		return verticeGradoMax;
	}
	
	public Model() {
		grafo = new SimpleGraph<>(DefaultEdge.class);
	}

	public void createGraph(int numeroLettere) {

		//aggiungo i vertici
		WordDAO dao = new WordDAO();
		List<String> allWords = dao.getAllWordsFixedLength(numeroLettere);
		Graphs.addAllVertices(grafo, allWords);
		
		System.out.println(allWords);
		
		//aggiungo gli archi
		for(String fixed : allWords) {
			for(String var : allWords) {
				//se le due parole differiscono solo per una lettera e l'arco non c'è -> lo creo
				if(compare2words(fixed, var, numeroLettere) && grafo.getEdge(fixed, var) == null) {
					grafo.addEdge(fixed, var);
				}
			}
		} 
		System.out.println("Grafo creato: " + grafo.vertexSet().size()+" vertici e "+grafo.edgeSet().size()+" archi");
		
	}

	//Metodo per trovare i vicini di uno specifico vertice
	public List<String> displayNeighbours(String parolaInserita) {
		List<String> resultString = new ArrayList<String>();
		Set<DefaultEdge> result = grafo.edgesOf(parolaInserita);
		for(DefaultEdge tmp : result) {
			resultString.add(tmp.toString());
		}
		return resultString;
	}

	//Metodo per trovare il massimo grado tra tutti i vertici 
	public int findMaxDegree() {
		int gradoMax = 0;
		
		for(String s : grafo.vertexSet()) {
			int grado = grafo.degreeOf(s);
			
			if(grado > gradoMax) {
				gradoMax = grado;
				verticeGradoMax = s;
			}
				
		}
		return gradoMax;
	}
	
	//Metodo che mi permette di capire se due parole differiscono solo per una lettera (true)
	//o se differiscono per più lettere o nessuna (false)
	public boolean compare2words(String w1, String w2, int numeroLettere) {
		
		int count = 0;
		for(int i=0; i<numeroLettere; i++) {
			if(w1.charAt(i)!=w2.charAt(i))
				count ++;
		}
		
		if(count==1)
			return true;
		else
			return false;
	}
}

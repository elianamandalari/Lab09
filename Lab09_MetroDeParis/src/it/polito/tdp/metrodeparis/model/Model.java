package it.polito.tdp.metrodeparis.model;

import java.util.*;

import org.jgrapht.*;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.metrodeparis.dao.MetroDAO;

public class Model {
	private List<Fermata> fermate;
	private WeightedGraph<Fermata, DefaultWeightedEdge> grafo;
	private Map<Integer, Linea> linee;
	public HashMap<Integer, Fermata> mappaFermate = new HashMap<Integer, Fermata>();

	public Collection<Fermata> getFermate() {
		if (this.fermate == null) {
			MetroDAO dao = new MetroDAO();
			this.fermate = dao.getAllFermate(mappaFermate);
		}

		return fermate;
	}

	public void creaGrafo() {
		this.grafo = new WeightedMultigraph<Fermata, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		MetroDAO dao = new MetroDAO();
		this.linee = dao.getLinee();

		Graphs.addAllVertices(grafo, this.getFermate());

		for (FermataPairs fp : dao.listCoppieFermateAdiacenti()) {
			Linea l = linee.get(fp.getIdLinea());
			Fermata f1 = mappaFermate.get(fp.getF1());
			Fermata f2 = mappaFermate.get(fp.getF2());

			double distanza = LatLngTool.distance(f1.getCoords(), f2.getCoords(), LengthUnit.KILOMETER);
			double peso = distanza / l.getVelocita();

			DefaultWeightedEdge e = grafo.addEdge(f1, f2);
			grafo.setEdgeWeight(e, peso);

		}
	}

	public double pesocamminoMinimo(Fermata partenza, Fermata arrivo) {
		

		DijkstraShortestPath<Fermata, DefaultWeightedEdge> d = new DijkstraShortestPath<Fermata, DefaultWeightedEdge>(
				grafo, partenza, arrivo);

		return d.getPathLength() * 3600 + d.getPathEdgeList().size() * 30;
	}

	public List<Fermata >camminoMinimo(Fermata partenza, Fermata arrivo) {
		DijkstraShortestPath<Fermata, DefaultWeightedEdge> d = new DijkstraShortestPath<Fermata, DefaultWeightedEdge>(
				grafo, partenza, arrivo);
		
		
		List<Fermata> fermate=new ArrayList<Fermata>();
		for(DefaultWeightedEdge e:d.getPathEdgeList())
		{
			Fermata f1=grafo.getEdgeSource(e);
		    Fermata f2=grafo.getEdgeTarget(e);
		
		if(!fermate.contains(f1))
			fermate.add(f1);
		else
				fermate.add(f2);
		}
		return fermate;
	}

}

package org.insa.graphs.algorithm.shortestpath;

import java.util.Collections;
import java.util.List;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Node;

public class Label implements Comparable<Label>{

	private Node sommet_courant;
	private boolean marque;
	private double cout;
	private Arc pere;
	
	public Label(Node a, boolean m, double c,  Arc p) {
		this.sommet_courant = a;
		this.marque = m;
		this.cout = c;
		this.pere =p;
	}
	
	public void setPere(Arc p) {
		this.pere = p;
	}
	
	public Arc getPere() {
		return this.pere;
	}
	
	public void marquer() {
		this.marque = true;
	}
	
	public boolean isMarque() {
		return this.marque;
	}
	
	
	public double getCost() {
		return this.cout;
	}
	
	public double getTotalCost() {
		return this.cout;
	}
	
	public void setCost(double cost) {
		this.cout = cost;
	}
	
	public int compareTo(Label other) {
        return Double.compare(getTotalCost(), other.getTotalCost());
    }
	
	public Node getNode() {
		return this.sommet_courant;
	}

}

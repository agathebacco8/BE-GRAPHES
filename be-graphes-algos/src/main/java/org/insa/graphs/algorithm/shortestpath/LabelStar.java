package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;

public class LabelStar extends Label {
	
	
	private double cout_estime;
	
	
	public LabelStar(Node a, boolean m, double c,  Arc p, Node destination) {
		super(a,m,c,p);
		this.cout_estime = a.getPoint().distanceTo(destination.getPoint()) ;
	}
	
	 public double getTotalCost() {
		 return this.getCost() + this.cout_estime;
	 }
	
}

package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.algorithm.AbstractInputData.Mode;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;

public class LabelStar extends Label {
	
	
	private double cout_estime;
	
	
	public LabelStar(Node a, boolean m, double c,  Arc p, Node destination, ShortestPathData data) {
		super(a,m,c,p);
		if (data.getMode() == Mode.LENGTH) {
			this.cout_estime = a.getPoint().distanceTo(destination.getPoint()) ;
		} 
		if (data.getMode() == Mode.TIME) {
			this.cout_estime = ((3.6*a.getPoint().distanceTo(destination.getPoint()))/data.getGraph().getGraphInformation().getMaximumSpeed()) ;
		}
	}
	
	 public double getTotalCost() {
		 return this.getCost() + this.cout_estime;
	 }
	
}

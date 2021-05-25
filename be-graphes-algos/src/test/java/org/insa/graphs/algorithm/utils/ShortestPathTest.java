package org.insa.graphs.algorithm.utils;

import static org.junit.Assert.assertEquals;

import java.io.*;

import org.insa.graphs.algorithm.ArcInspector;
import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.algorithm.shortestpath.*;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.io.*;
import org.junit.Before;
import org.junit.Test;

public class ShortestPathTest {
	
	protected ShortestPathSolution solutionBF[] = new ShortestPathSolution[5];
	protected ShortestPathSolution solutionDijkstra[] = new ShortestPathSolution[5];
	protected ShortestPathSolution solutionAStar[] = new ShortestPathSolution[5];
	
	
    
    @Before
    public void Construct() throws Exception{
		 
		 /*---------------------Lecture de trois cartes-------------------*/
		 
		 final String toulouse = "/home/bacconni/3A/BE-GRAPHES/maps/toulouse.mapgr";
		 final String frenchpolynesia = "/home/bacconni/3A/BE-GRAPHES/maps/french-polynesia.mapgr";
			
		 
		 final GraphReader readerTLS = new BinaryGraphReader(new DataInputStream
				(new BufferedInputStream(new FileInputStream(toulouse))));
			
  		 final GraphReader readerfrenchpolynesia = new BinaryGraphReader(new DataInputStream
				(new BufferedInputStream(new FileInputStream(frenchpolynesia))));
		 
		 Graph graphtoulouse = readerTLS.read();
		 Graph graphfrenchpolynesia = readerfrenchpolynesia.read();
		 
		 
		 
		 /*-------------------------Création des données------------------------*/
		 
		 final ArcInspector allRoads = ArcInspectorFactory.getAllFilters().get(0);
		 final ArcInspector carAndLength = ArcInspectorFactory.getAllFilters().get(1);
		 final ArcInspector carsAndTime = ArcInspectorFactory.getAllFilters().get(2);
		 final ArcInspector pedestrian = ArcInspectorFactory.getAllFilters().get(4);
		 
		 ShortestPathData data[] = new ShortestPathData[5];
		 
		 // Trajet d'un point à lui-même
		 data[0] = new ShortestPathData(graphtoulouse, graphtoulouse.get(10991), graphtoulouse.get(10991), allRoads);
		 
		 // Trajet INSA/bikini en voiture et en temps
		 data[1] = new ShortestPathData(graphtoulouse, graphtoulouse.get(11157), graphtoulouse.get(1068), carsAndTime);
		 
		 // Trajet INSA/bikini en voiture et en distance
		 data[2] = new ShortestPathData(graphtoulouse, graphtoulouse.get(11157), graphtoulouse.get(1068), carAndLength);
		 
		 // Trajet INSA/bikini à pied
		 data[3] = new ShortestPathData(graphtoulouse, graphtoulouse.get(11157), graphtoulouse.get(1068), pedestrian);
		 
		 // Trajet entre deux îles non reliées (trajet non existant)
		 data[4] = new ShortestPathData(graphfrenchpolynesia, graphfrenchpolynesia.get(14071), graphfrenchpolynesia.get(4917), allRoads);

		 
		
		 
		 /*-------------------Execution des trois algorithmes-------------------*/
		 
		  BellmanFordAlgorithm bellman[]= new BellmanFordAlgorithm[5];
		  DijkstraAlgorithm dijkstra[]= new DijkstraAlgorithm[5];
		  AStarAlgorithm astar[]= new AStarAlgorithm[5];
	        
	      for(int i=0; i<5;i++) {
	    	  bellman[i] = new BellmanFordAlgorithm(data[i]);
	    	  dijkstra[i] = new DijkstraAlgorithm(data[i]);
	    	  astar[i] = new AStarAlgorithm(data[i]);
	      }

	      
	      for(int i=0; i<5;i++) {
	    	  solutionBF[i] = bellman[i].run();
	    	  solutionDijkstra[i] = dijkstra[i].run();
	    	  solutionAStar[i] = astar[i].run();
	      }
    }
    
    
    /*-----------------------Comparaison des solutions---------------------*/

	      
	@Test
	public void testDijkstra() throws IOException  {
	    	 
	    	  assertEquals(solutionBF[0].getPath(),solutionDijkstra[0].getPath());
	       
	 }
	
	@Test
	public void testDijkstra1() throws IOException  {
	    	 
	    	  assertEquals(solutionBF[1].getPath().getLength(),solutionDijkstra[1].getPath().getLength(), 0.001);
	       
	 }
	
	@Test
	public void testDijkstra2() throws IOException  {
	    	 
	    	  assertEquals(solutionBF[2].getPath().getMinimumTravelTime(),solutionDijkstra[2].getPath().getMinimumTravelTime(), 0.001);
	       
	 }
	
	@Test
	public void testDijkstra3() throws IOException  {
	    	 
	    	  assertEquals(solutionBF[3].getPath().getLength(),solutionDijkstra[3].getPath().getLength(), 0.001);
	       
	 }
	
	/*@Test
	public void testDijkstra4() throws IOException  {
	    	 
	    	  assertEquals(solutionBF[4].getPath().getLength(),solutionDijkstra[4].getPath().getLength(), 0.001);
	       
	 }*/
	
	
	@Test
	public void testAStar() throws IOException  {
   	 
	    	  assertEquals(solutionBF[0].getPath(),solutionAStar[0].getPath());
	       
	 }
		  
     @Test
	 public void testAStar1() throws IOException  {
		   	 
			 assertEquals(solutionBF[1].getPath().getLength(),solutionAStar[1].getPath().getLength(), 0.001);
			       
	 }
		  
		  
	 @Test
	 public void testAStar2() throws IOException  {
				   	 
			 assertEquals(solutionBF[2].getPath().getMinimumTravelTime(),solutionAStar[2].getPath().getMinimumTravelTime(), 0.001);
					       
     }
		  
	 @Test
	 public void testAStar3() throws IOException  {
				   	 
			 assertEquals(solutionBF[3].getPath().getLength(),solutionAStar[3].getPath().getLength(), 0.001);
					       
	 }
		  
		  
	 /*@Test
	 public void testAStar4() throws IOException  {
				   	 
			 assertEquals(solutionBF[4].getPath().getLength(),solutionAStar[4].getPath().getLength(), 0.001);
					       
     }*/
		  
		  
}



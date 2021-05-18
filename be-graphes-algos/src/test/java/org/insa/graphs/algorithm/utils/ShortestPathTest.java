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
		 
		 final String Toulouse = "/home/bacconni/3A/BE-GRAPHES/maps/toulouse.mapgr";
		 final String belgium = "/home/bacconni/3A/BE-GRAPHES/maps/belgium.mapgr";
		 final String insa = "/home/bacconni/3A/BE-GRAPHES/maps/insa.mapgr";
			
		 
		 final GraphReader readerHG = new BinaryGraphReader(new DataInputStream
				(new BufferedInputStream(new FileInputStream(Toulouse))));
			
  		 final GraphReader readerbelgium = new BinaryGraphReader(new DataInputStream
				(new BufferedInputStream(new FileInputStream(belgium))));
			
		 final GraphReader readerInsa = new BinaryGraphReader(new DataInputStream
				(new BufferedInputStream(new FileInputStream(insa))));
		 
		 Graph graphToulouse = readerHG.read();
		 Graph graphbelgium = readerbelgium.read();
		 Graph graphInsa = readerInsa.read();
		 
		 
		 
		 /*-------------------------Création des données------------------------*/
		 
		 final ArcInspector allRoads = ArcInspectorFactory.getAllFilters().get(0);
		 final ArcInspector carAndLength = ArcInspectorFactory.getAllFilters().get(1);
		 final ArcInspector carsAndTime = ArcInspectorFactory.getAllFilters().get(2);
		 final ArcInspector pedestrian = ArcInspectorFactory.getAllFilters().get(4);
		 
		 ShortestPathData data[] = new ShortestPathData[5];
		 
		 // Trajet d'un point à lui-même
		 data[0] = new ShortestPathData(graphToulouse, graphToulouse.get(10991), graphToulouse.get(10991), allRoads);
		 
		 
		 // Trajet bikini/Canal en voiture et en temps
		 data[1] = new ShortestPathData(graphToulouse, graphToulouse.get(535), graphToulouse.get(535), carsAndTime);
		 
		 // Trajet bikini/Canal en voiture et en distance
		 data[2] = new ShortestPathData(graphToulouse, graphToulouse.get(535), graphToulouse.get(535), carAndLength);
		 
		 // Trajet bikini/Canal à pied
		 data[3] = new ShortestPathData(graphToulouse, graphToulouse.get(535), graphToulouse.get(535), pedestrian);
		 
		 // Trajet entre deux îles non reliées (trajet non existant)
		 data[4] = new ShortestPathData(graphbelgium, graphbelgium.get(417195), graphbelgium.get(116100), allRoads);

		 
		
		 
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
	    	 
	    	  assertEquals(solutionBF[1].getPath(),solutionDijkstra[1].getPath());
	       
	 }
	
	@Test
	public void testDijkstra2() throws IOException  {
	    	 
	    	  assertEquals(solutionBF[2].getPath(),solutionDijkstra[2].getPath());
	       
	 }
	
	@Test
	public void testDijkstra3() throws IOException  {
	    	 
	    	  assertEquals(solutionBF[3].getPath(),solutionDijkstra[3].getPath());
	       
	 }
	
	@Test
	public void testDijkstra4() throws IOException  {
	    	 
	    	  assertEquals(solutionBF[4].getPath(),solutionDijkstra[4].getPath());
	       
	 }
	
	
	@Test
	public void testAStar() throws IOException  {
   	 
	    	  assertEquals(solutionBF[0].getPath(),solutionAStar[0].getPath());
	       
	 }
		  
     @Test
	 public void testAStar1() throws IOException  {
		   	 
			 assertEquals(solutionBF[1].getPath(),solutionAStar[1].getPath());
			       
	 }
		  
		  
	 @Test
	 public void testAStar2() throws IOException  {
				   	 
			 assertEquals(solutionBF[2].getPath(),solutionAStar[2].getPath());
					       
     }
		  
	 @Test
	 public void testAStar3() throws IOException  {
				   	 
			 assertEquals(solutionBF[3].getPath(),solutionAStar[3].getPath());
					       
	 }
		  
		  
	 @Test
	 public void testAStar4() throws IOException  {
				   	 
			 assertEquals(solutionBF[4].getPath(),solutionAStar[4].getPath());
					       
     }
		  
		  
}



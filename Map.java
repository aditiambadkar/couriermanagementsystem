import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.Scanner.*;

public class Map
{
	String city_list []={"Mumbai","Delhi","Bangalore","Pune","Kolkata","Chennai","Jaipur","Bhopal"};
	final static int infinity=9999;
	int m[][]=new int[8][8];
	
	public Map()
	{
		int i,j;
		for(i=0;i<8;i++)
		{
			for(j=0;j<8;j++)
			{
				m[i][j]=0;
			}
		}
		m[0][1]=m[1][0]=50;
		m[0][5]=m[5][0]=300;
		m[1][2]=m[2][1]=200;
		m[2][3]=m[3][2]=100;
		m[2][7]=m[7][2]=150;
		m[3][4]=m[4][3]=50;
		m[4][5]=m[5][4]=100;
		m[4][6]=m[6][4]=250;
		m[6][7]=m[7][6]=100;
		//network
	}
	
	int compute(int sc,int dc)
	{
		int nVertices=8;
		int[]shortestDistances=new int[8];

		boolean[]added=new boolean[nVertices];
		
		for(int vertexIndex=0;vertexIndex<nVertices;vertexIndex++)
		{
			shortestDistances[vertexIndex]=infinity;
			added[vertexIndex]=false;
		}
		
		shortestDistances[sc]=0;

		int[]parents=new int[nVertices];

		parents[sc]=-1;

		for(int i=1;i<nVertices;i++)
		{
			int nearestVertex=-1;
			int shortestDistance=infinity;
			for(int vertexIndex=0;vertexIndex <nVertices; vertexIndex++)
			{
				if(!added[vertexIndex] && shortestDistances[vertexIndex]<shortestDistance) 
				{
					nearestVertex=vertexIndex;
					shortestDistance=shortestDistances[vertexIndex];
				}
			}
			added[nearestVertex] = true;
			
			for(int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) 
			{
				int edgeDistance = m[nearestVertex][vertexIndex];
				if(edgeDistance>0 && ((shortestDistance+edgeDistance)<shortestDistances[vertexIndex])) 
				{
					parents[vertexIndex]=nearestVertex;
					shortestDistances[vertexIndex] = shortestDistance + edgeDistance;
				}
			}
		}
		printSolution(sc,shortestDistances,parents,dc);
		return shortestDistances[dc];
	}

	void printSolution(int startVertex,int[] distances,int[] parents,int dc)
	{
		int nVertices=8;
		System.out.println("City of Origin->Destination\tDistance\t\tPath");
		for(int vertexIndex=0;vertexIndex<nVertices;vertexIndex++) 
		{
			if(vertexIndex!=startVertex && vertexIndex==dc) 
			{
				System.out.print(city_list[startVertex]+" -> ");
				System.out.print(city_list[vertexIndex] + " \t\t\t ");
				System.out.print(distances[vertexIndex] + "\t\t");
				printPath(vertexIndex,parents);
			}
		}
	}

	void printPath(int currentVertex,int[] parents)
	{
		if (currentVertex==-1)
		{
			return;
		}
		printPath(parents[currentVertex], parents);
		System.out.print(city_list[currentVertex]+ " -> ");
	}
}
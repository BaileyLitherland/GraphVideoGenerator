package com.engmig.graphs;

import com.engmig.Vertex;
import org.jcodec.common.DictionaryCompressor;

import javax.vecmath.Vector3d;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class AdjacencyList extends Graph{

    ArrayList<Vertex> vertices = new ArrayList<>();

    ArrayList<ArrayList<Integer>> edges = new ArrayList<>();

    @Override
    public ArrayList<Integer> getNeighbours(Vertex v) {
        int vertexId = vertices.indexOf(v);
        return edges.get(vertexId);
    }

    @Override
    public void addVertex(Vertex v){
        vertices.add(v);
        edges.add(new ArrayList<Integer>());
    }

    // For if we are dealing with vertices index
    @Override
    public void addEdge(int v, int u){
        // Assumes our graphs are undirected
        edges.get(v).add(u);
        edges.get(u).add(v);
    }

    // For if we are dealing with vertices
    @Override
    public void addEdge(Vertex v, Vertex u){
        // Assumes our graphs are undirected
        int vertexVIndex = vertices.indexOf(v);
        int vertexUIndex = vertices.indexOf(u);
        edges.get(vertexVIndex).add(vertexUIndex);
        edges.get(vertexUIndex).add(vertexVIndex);
    }

    @Override
    public int size() {
        return vertices.size();
    }

    @Override
    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    @Override
    public ArrayList<ArrayList<Integer>> getEdges() {
        return edges;
    }

    @Override
    public Vertex getVertex(int x) {
        return vertices.get(x);
    }

    @Override
    public void removeVertex(int x) {
        vertices.remove(x);
        // Now we have to refactor the array to ensure relationships are correctly maintained.
        for (ArrayList<Integer> neighbours: edges){
            int edgeToRemove = neighbours.indexOf(x);
            if (edgeToRemove != -1){
                neighbours.remove(edgeToRemove);
            }
            for (int i = 0; i < neighbours.size(); i++) {
                // Subtract 1 from every index that was greater than the index of the vertices we were removing
                if (neighbours.get(i) > x) {
                    neighbours.set(i, neighbours.get(i) - 1);
                }
            }

        }
        edges.remove(x);
    }

    @Override
    public void removeEdge(int x, int y) {
        int yIndex = edges.get(x).indexOf(y);
        if (yIndex != -1) {
            edges.get(x).remove(yIndex);
        }

        int xIndex = edges.get(y).indexOf(x);
        if (xIndex != -1){
            edges.get(y).remove(xIndex);
        }
    }

    public void makeNVerticesOnCircle(int numV,double h,double k, double r){
        for (int i = 0; i < numV; i++){
            Vertex v = new Vertex();
            addVertex(v);
            double theta = (i * 2 * Math.PI / numV);
            v.setPos(new Vector3d(h+r*Math.cos(theta),k+r*Math.sin(theta),0));
        }
    }

    @Override
    public boolean isAdjacent(int x, int y) {
        if (edges.get(x).contains(y)){
            return true;
        }
        return false;
    }


    public void addRandomEdges(int numE){
        int numV = getNumVertices();
        Random random = new Random();
        for(int i = 0; i < numE; i++ ){
            int v1 = random.nextInt(numV);
            int v2 = random.nextInt(numV);
            while (v1 == v2 || isAdjacent(v1,v2)){
                v1 = random.nextInt(numV);
                v2 = random.nextInt(numV);
            }
            addEdge(v1,v2);
        }
    }

    public void addRandomEdgesOdd(int numE){
        int numV = getNumVertices();
        Random random = new Random();
        for(int i = 0; i < numE; i++ ){
            int v1 = random.nextInt(numV);
            int v2 = random.nextInt(numV);
            if (v1 % 2 == 0){
                v1++;
            }
            if (v2 % 2 == 0){
                v2++;
            }
            while (v1 == v2 || isAdjacent(v1,v2)){
                v1 = random.nextInt(numV);
                v2 = random.nextInt(numV);
                if (v1 % 2 == 0){
                    v1++;
                }
                if (v2 % 2 == 0){
                    v2++;
                }
            }
            addEdge(v1,v2);
        }
    }

    public void addRandomEdgesEven(int numE){
        int numV = getNumVertices();
        Random random = new Random();
        for(int i = 0; i < numE; i++ ){
            int v1 = random.nextInt(numV);
            int v2 = random.nextInt(numV);
            if (v1 % 2 == 1){
                v1--;
            }
            if (v2 % 2 == 1){
                v2--;
            }
            while (v1 == v2 || isAdjacent(v1,v2)){
                v1 = random.nextInt(numV);
                v2 = random.nextInt(numV);
                if (v1 % 2 == 1){
                    v1--;
                }
                if (v2 % 2 == 1){
                    v2--;
                }
            }
            addEdge(v1,v2);
        }
    }


    //  Vertex | Indices of adjacent Vertices
//        0| [2,3]
//        1| [2]
//        2| [0,1,3]
//        3| [0,2]
//        4| []








//    @Override
    public ArrayList<Vertex> getNeighbourss(int x) {
        ArrayList<Vertex> rtnArray = new ArrayList<Vertex>();
        //if (edges.size() > 0) {
            for (Integer vertex : edges.get(x)) {
                rtnArray.add(vertices.get(vertex));
            }
        //}
        return rtnArray;
    }


//    @Override
    public int getVertexIndex(Vertex vertex) {
        return vertices.indexOf(vertex);
    }


//    @Override



//    @Override
    public double getEdgeValue(int x, int y) {
        return 0;
    }


//    @Override
    public int getNumVertices() {
        return vertices.size();
    }


//    public void makeRndGraph(int numV, int numE){
//
//        Random random = new Random();
//        for(int i = 0; i < numV; i++ ){
//            addVertex();
//            Vertex v = getVertex(i);
//            v.setPos(new Vector3d(random.nextInt(3600)+ 120, random.nextInt(2000)+80, 0));
//        }
//        for(int i = 0; i < numE; i++ ){
//            int v1 = random.nextInt(numV);
//            int v2 = random.nextInt(numV);
//            if (v2 == v1){
//                if (v1 != numV -1 ){
//                    v1 =  v1 + 1;
//                }
//                else{
//                    v1 = v1 - 1;
//                }
//            }
//            addEdge(v1,v2,0);
//        }
//    }



}

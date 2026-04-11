package com.engmig.graphs;

import com.engmig.Vertex;

import javax.vecmath.Vector3d;
import java.util.ArrayList;
import java.util.Random;

public class AdjacencyList extends Graph{

    ArrayList<Vertex> vertices = new ArrayList<Vertex>();

    // Edges are a tuple of indices where each index refers to a vertex in the vertices array
    ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();


    @Override
    public boolean isAdjacent(int x, int y) {
        if (edges.get(x).contains(y)){
            return true;
        }
        return false;
    }


    @Override
    public ArrayList<Vertex> getNeighbours(int x) {
        ArrayList<Vertex> rtnArray = new ArrayList<Vertex>();
        //if (edges.size() > 0) {
            for (Integer vertex : edges.get(x)) {
                rtnArray.add(vertices.get(vertex));
            }
        //}
        return rtnArray;
    }

    @Override
    public ArrayList<Vertex> getNeighbours(Vertex x) {
        return getNeighbours(getVertexIndex(x));
    }

    /**
     * @return
     */
    @Override
    public Vertex addVertex() {

        Vertex newVertex = new Vertex();
        vertices.add(newVertex);
        edges.add(new ArrayList<Integer>());
        return newVertex;
    }

    @Override
    public void addVertex(Vertex v) {
        vertices.add(v);
        edges.add(new ArrayList<Integer>());
    }

    @Override
    public void removeVertex(int x) {
        vertices.remove(x);
        // Remove the vertex from the edges list
        for (ArrayList<Integer> edge: edges){
            for (Integer vertex: edge){
                if (vertex > x){
                    vertex = vertex + 1;
                } else if (vertex == x) {
                    edges.remove(edge);
                }
            }
        }
    }

    @Override
    public void addEdge(int x, int y, int w) {
        // TODO Check for double edges
        edges.get(x).add(y);
        edges.get(y).add(x);
    }

    @Override
    public void removeEdge(int x, int y) {
        edges.get(x).remove(y);
        edges.get(y).remove(x);
    }

    @Override
    public Vertex getVertex(int x) {
        return vertices.get(x);
    }


    @Override
    public int getVertexIndex(Vertex vertex) {
        return vertices.indexOf(vertex);
    }


    @Override
    public ArrayList<Vertex> getVertices() {
        return vertices;
    }


    @Override
    public double getEdgeValue(int x, int y) {
        return 0;
    }


    @Override
    public int getNumVertices() {
        return vertices.size();
    }

    @Override
    public ArrayList<ArrayList<Integer>> getEdges() {
        return edges;
    }

    public void makeRndGraph(int numV, int numE){

        Random random = new Random();
        for(int i = 0; i < numV; i++ ){
            addVertex();
            Vertex v = getVertex(i);
            v.setPos(new Vector3d(random.nextInt(3600)+ 120, random.nextInt(2000)+80, 0));
        }
        for(int i = 0; i < numE; i++ ){
            int v1 = random.nextInt(numV);
            int v2 = random.nextInt(numV);
            if (v2 == v1){
                if (v1 != numV -1 ){
                    v1 =  v1 + 1;
                }
                else{
                    v1 = v1 - 1;
                }
            }
            addEdge(v1,v2,0);
        }
    }

    public void makeNVerticesOnCircle(int numV,double h,double k, double r){
        for (int i = 0; i < numV; i++){
            Vertex v = addVertex();
            v.setPos(new Vector3d(h+r*Math.cos(i * 2 * Math.PI/numV),k+r*Math.sin(i * 2* Math.PI/numV),0));
        }
    }

    public void addRandomEdges(int numE){
        int numV = getNumVertices();
        Random random = new Random();
        for(int i = 0; i < numE; i++ ){
            int v1 = random.nextInt(numV);
            int v2 = random.nextInt(numV);
            if (v2 == v1){
                if (v1 != numV -1 ){
                    v1 =  v1 + 1;
                }
                else{
                    v1 = v1 - 1;
                }
            }
            addEdge(v1,v2,0);
        }
    }
}

package com.engmig.graphs;

import com.engmig.Vertex;

import java.util.ArrayList;

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

        for (Integer vertex : edges.get(x)) {
            rtnArray.add(vertices.get(vertex));
        }
        return rtnArray;
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
        return 0;
    }
}

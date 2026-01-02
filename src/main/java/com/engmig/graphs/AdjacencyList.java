package com.engmig.graphs;

import com.engmig.Vertex;

import java.util.ArrayList;

public class AdjacencyList extends Graph{

    ArrayList<Vertex> vertices = new ArrayList<Vertex>();

    ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();

    Vertex nullVertex = new Vertex(true);

    /**
     * @param x the first vertices
     * @param y the second vertices
     * @return Boolean if x is incident to y
     */
    @Override
    public boolean isAdjacent(int x, int y) {
        if (edges.get(x).contains(y)){
            return true;
        }
        return false;
    }

    /**
     * @param x the index of vertex from the vertices array
     * @return
     */
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

    /**
     * @param x
     */
    @Override
    public void removeVertex(int x) {
        vertices.set(x, nullVertex);
        // TODO do this properly
    }

    /**
     * @param x
     * @param y
     * @param w
     */
    @Override
    public void addEdge(int x, int y, int w) {
        // TODO Check for double edges
        edges.get(x).add(y);
        edges.get(y).add(x);
    }

    /**
     * @param x
     * @param y
     */
    @Override
    public void removeEdge(int x, int y) {
        edges.get(x).remove(y);
        edges.get(y).remove(x);
    }

    /**
     * @param x
     * @return
     */
    @Override
    public Vertex getVertex(int x) {
        return vertices.get(x);
    }

    /**
     * @param vertex
     * @return
     */
    @Override
    public int getVertexIndex(Vertex vertex) {
        return vertices.indexOf(vertex);
    }

    /**
     * @return
     */
    @Override
    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    /**
     * @param x
     * @param y
     * @return
     */
    @Override
    public double getEdgeValue(int x, int y) {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public int getNumVertices() {
        return 0;
    }
}

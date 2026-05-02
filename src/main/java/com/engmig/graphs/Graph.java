package com.engmig.graphs;

import com.engmig.Vertex;
import org.jcodec.common.DictionaryCompressor;

import java.util.ArrayList;

public abstract class Graph {

    public abstract ArrayList<Integer> getNeighbours(Vertex v);

    public abstract void addVertex(Vertex v);

    public abstract void addEdge(int v, int u);

    public abstract void addEdge(Vertex v, Vertex u);

    public abstract int size(); // return number of vertices in graph

    public abstract ArrayList<Vertex> getVertices(); // returns list of vertices

    public abstract ArrayList<ArrayList<Integer>> getEdges(); // Returns list of edges




}



//    public abstract boolean isAdjacent(int x, int y); // Returns true is vertices x and y are adjacent
//
//    public abstract ArrayList<Vertex> getNeighbours(int x); // Returns a list of neighbouring vertices to vertex x
//
//    public abstract ArrayList<Vertex> getNeighbours(Vertex x); // Returns a list of neighbouring vertices to vertex x
//
//    public abstract Vertex addVertex(); // Adds a new vertex of degree 0 to the graph
//
//    public abstract void addVertex(Vertex v); // Adds a vertex v to the graph
//
//    public abstract void removeVertex(int x); // Removes vertex at index x from graph and it's edges
//
//    public abstract void addEdge(int x, int y, int w); // Adds edge from vertices x to y with weight w
//
//    public abstract void removeEdge(int x, int y); // Removes edge from vertices x to y if it exists
//
//    public abstract Vertex getVertex(int x); // Gets vertex at index x
//
//    public abstract int getVertexIndex(Vertex vertex); // Gets index of a vertex
//
//    public abstract ArrayList<Vertex> getVertices(); // Gets all vertices
//
//    public abstract double getEdgeValue(int x, int y); // Gets the weight of the edge between vertices x and y
//
//    public abstract int getNumVertices();
//
//    public abstract ArrayList<ArrayList<Integer>> getEdges();
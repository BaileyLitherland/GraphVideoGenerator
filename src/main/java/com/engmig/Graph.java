package com.engmig;

import java.util.ArrayList;

public abstract class Graph {

    // Returns true is vertices x and y are adjacent
    public abstract boolean isAdjacent(int x, int y);

    public abstract ArrayList<Vertex> getNeighbours(int x); // Returns a list of neighbouring vertices to vertex x

    public abstract Vertex addVertex(); // Adds a new vertex of degree 0 to the graph

    public abstract void removeVertex(int x); // Removes vertex x from graph and it's edges

    public abstract void addEdge(int x, int y, int w); // Adds edge from vertices x to y with weight w

    public abstract void removeEdge(int x, int y); // Removes edge from vertices x to y if it exists

    public abstract Vertex getVertex(int x); // Gets vertex x

    public abstract int getVertexIndex(Vertex vertex); //

    public abstract ArrayList<Vertex> getVertices(); // Gets all vertices

    public abstract double getEdgeValue(int x, int y); // Gets the weight of the edge between vertices x and y

    public abstract int getNumVertices();
}
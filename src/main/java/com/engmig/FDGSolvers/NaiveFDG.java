package com.engmig.FDGSolvers;

import com.engmig.Vertex;
import com.engmig.graphs.Graph;

import javax.vecmath.Vector3d;
import java.util.ArrayList;

public class NaiveFDG {
    Graph graph;
    final double SPRING_CONSTANT = .0001;
    double baseLength = 1000;

    public NaiveFDG(Graph graph){
        this.graph = graph;
    }

    public void update(){
        for (Vertex v: graph.getVertices()){
            Vector3d force = new Vector3d();
            ArrayList<Vertex> neighbours = graph.getNeighbours(v);
            for (Vertex u: neighbours)
                force.add(attractiveForce(v,u));
            v.move(force);
        }

    }

    private Vector3d attractiveForce(Vertex v, Vertex u){
        Vector3d forceVector = new Vector3d();
        forceVector.sub(u.getPos(),v.getPos());

        Double length = forceVector.length();
        Double displacement = length-baseLength;

        forceVector.scale(displacement);
        forceVector.scale(SPRING_CONSTANT);

        return forceVector;
    }

}

package com.engmig.FDGSolvers;

import com.engmig.Vertex;
import com.engmig.graphs.Graph;

import javax.vecmath.Vector3d;
import java.util.ArrayList;

public class FruchReinFDG {
    Graph graph;
    final double SPRING_CONSTANT = .0001;
    final double REPULSIVE_FORCE = -.01;

    double targetDistance;

    public FruchReinFDG(Graph graph){
        this.graph = graph;
        targetDistance = Math.sqrt(7200000/graph.getNumVertices());
        System.out.println(targetDistance);

    }



    public void update(){
        for (Vertex v: graph.getVertices()){
            Vector3d force = new Vector3d();
            ArrayList<Vertex> neighbours = graph.getNeighbours(v);
            for (Vertex u: neighbours)
                force.add(attractiveForce(v,u));
            for (Vertex j: graph.getVertices()){
                if (!neighbours.contains(j) && j != v) {
                    force.add(repulsiveForce(v,j));
                }
            }
            v.move(force);
        }

    }

    private Vector3d attractiveForce(Vertex v, Vertex u){
        Vector3d forceVector = new Vector3d();
        forceVector.sub(u.getPos(),v.getPos());
        //System.out.println("U: " + u.getPos() + " V:" + v.getPos());
        Double length = forceVector.length();
        //System.out.println("Length:" + forceVector.length());
        //System.out.println("Force Vector: " + forceVector);
        forceVector.normalize();
        //System.out.println("Normalised Force Vector: " + forceVector);
        //System.out.println("(length*length)/targetDistance: " + (length*length)/targetDistance);
        forceVector.scale((length*length)/targetDistance);
        //System.out.println("final force Vector: " + forceVector);
        //System.out.println("Length:" + (length*length));
        //System.out.println("target:" + targetDistance);

        return forceVector;
    }

    private Vector3d repulsiveForce(Vertex v, Vertex u){
        Vector3d forceVector = new Vector3d();
        forceVector.sub(u.getPos(),v.getPos());

        Double length = forceVector.length();
        System.out.println("Repel");
        forceVector.normalize();
        forceVector.scale((-targetDistance*targetDistance / length));

        return forceVector;
    }
}

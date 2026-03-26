package com.engmig.FDGSolvers;

import com.engmig.Vertex;
import com.engmig.graphs.Graph;

import javax.vecmath.Vector2d;
import javax.vecmath.Vector3d;
import java.util.ArrayList;

public class FruchReinFDG {
    Graph graph;
    final double SPRING_CONSTANT = .0001;
    final double REPULSIVE_FORCE = -.01;

    double t = 300; // TODO: Make it relative to the size of the canvas

    private int iterationCount = 1;
    double targetDistance;

    public FruchReinFDG(Graph graph){
        this.graph = graph;
        targetDistance = Math.sqrt(7400000/(graph.getNumVertices()+10));
    }

    public void update(){
        // Calculate Repulsive Forces
        for (Vertex v: graph.getVertices()){
            Vector3d force = new Vector3d();
            v.setDisp(new Vector3d());
            for (Vertex u: graph.getVertices()){
                if (u != v) {
                    // get the vector vu
                    Vector3d differenceVector = new Vector3d();
                    differenceVector.sub(v.getPos(),u.getPos());

                    // Get the distance between the vectors
                    double length = differenceVector.length();

                    // Get the unit vector of vu
                    Vector3d unitVector = new Vector3d();
                    unitVector.scale(1/length,differenceVector);

                    //(Delta/|Delta|) * fr(|Delta|)
                    unitVector.scale(repulsiveForce(length));

                    v.addDisp(unitVector);
                }
            }
        }
        // Calculate Attractive Forces
        for (int i = 0; i < graph.getNumVertices(); i ++ ){
            // Here edges doesnt store each edge but an
            for(int j: graph.getEdges().get(i)){
                if (j != i) {
                    Vertex v = graph.getVertex(i);
                    Vertex u = graph.getVertex(j);

                    Vector3d differenceVector = new Vector3d();
                    differenceVector.sub(u.getPos(), v.getPos());

                    double length = differenceVector.length();

                    Vector3d unitVector = new Vector3d();
                    unitVector.scale(1/length,differenceVector);

                    unitVector.scale(attractiveForce(length));

                    v.addDisp(unitVector);
                }
            }

        }

        // Limit total displacement
        for (Vertex v: graph.getVertices()){
            Vector3d disp = v.getDisp();
            Double length = disp.length();
            disp.normalize();


            disp.scale(Math.min(length, t));
            //System.out.println(Math.min(length, t));
            v.move(disp);
            v.getPos().x = Math.min(Math.max(v.getPos().x,30), 3800);
            v.getPos().y = Math.min(Math.max(v.getPos().y,30), 2100);

        }
        cool(iterationCount);
        iterationCount += 1;

    }

    private Double attractiveForce(Double length){
        return ((length * length) / targetDistance);
    }

    private Double repulsiveForce(Double length){
        return (targetDistance * targetDistance) / length;
        //return 1.0;
    }

    private void cool(double i){
        //double x = (180/i);
        //t = 1 - Math.pow(1 - x, 3);
        t = 300/((i));
    }




//    // Here edges doesnt store each edge but an
//    Vertex v = graph.getVertex(e.get(0));
//    Vertex u = graph.getVertex(e.get(1));
//
//    Vector3d differenceVector = new Vector3d();
//            differenceVector.sub(v.getPos(), u.getPos());
//
//    double length = differenceVector.length();
//
//    Vector3d unitVector = new Vector3d();
//            unitVector.scale(1/length,differenceVector);
//
//            unitVector.scale(attractiveForce(length));
//
//            v.addDisp(unitVector);
//
//            unitVector.scale(-1);
//            u.addDisp(unitVector);
}

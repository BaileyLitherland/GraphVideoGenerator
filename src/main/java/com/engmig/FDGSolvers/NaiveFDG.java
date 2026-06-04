package com.engmig.FDGSolvers;

import com.almasb.fxgl.physics.box2d.dynamics.contacts.ContactVelocityConstraint;
import com.engmig.Vertex;
import com.engmig.graphs.Graph;
import org.jcodec.common.DictionaryCompressor;

import javax.vecmath.Vector3d;
import java.util.ArrayList;

public class NaiveFDG {

    public NaiveFDG(){

    }

    private final int RESTING_LENGTH = 500;
    private final double REPELLING_FORCE = 1000000;

    public Vector3d attractiveForce(Vertex v, Vertex u){
        // Hooks law, force = -kx where k is the spring constant, and x is the displacement from resting position

        // Get the vector from v to u.
        Vector3d vu = new Vector3d();
        vu.sub(u.getPos(),v.getPos());

        // Get difference in VU length and Resting Length
        double x = vu.length() - RESTING_LENGTH;

        // Calculate x as a displacement Vector
        vu.normalize();
        vu.scale(x);

        // Scale x by -k
        double SPRING_CONSTANT = 0.1;

        vu.scale(-SPRING_CONSTANT);

        return vu;
    }

    public Vector3d repulsiveForce(Vertex v, Vertex u){
        // Coulombs law; force = k * (q1*q2)/r^2
        // Get the vector from v to u.
        Vector3d vu = new Vector3d();
        vu.sub(u.getPos(),v.getPos());
        // Get length of VU squared
        double x = vu.lengthSquared();

        vu.normalize();
        vu.scale(-REPELLING_FORCE/x); // Assumes q1 and q2 is just 1

        return vu;
    }

    public void update(Graph graph){
        for (Vertex v: graph.getVertices()){
            for (Vertex u: graph.getVertices()){
                if (u != v) {
                    if (graph.isAdjacent(v, u)) {
                        v.move(attractiveForce(u, v));
                    } else {
                        v.move(repulsiveForce(v, u));
                    }
                }
            }
        }
    }
}



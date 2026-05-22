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

    private final int RESTING_LENGTH = 750;

    public Vector3d attractiveForce(Vertex v, Vertex u){
        // Hooks law, force = -kx := k is the spring constant, and x is the displacement from resting position

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

    public void update(Graph graph){
        for (Vertex v: graph.getVertices()){
            ArrayList<Integer> neighbours = graph.getNeighbours(v);
            for (int index: neighbours){
                Vertex u = graph.getVertex(index);
                u.move(attractiveForce(v,u));
            }
        }
    }
}



package logic;

import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.optimization.linear.LinearConstraint;
import org.apache.commons.math3.optimization.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optimization.linear.Relationship;
import org.apache.commons.math3.optimization.linear.SimplexSolver;

import java.util.ArrayList;
import java.util.Collection;

/**
* Created by user on 21-Dec-15.
*/
public class LinProg {

    public static void main(String[] args) {
        LinearObjectiveFunction function = new LinearObjectiveFunction(new
                double[] { 2, 2, 1 }, 0);
        Collection<LinearConstraint> constraints = new
                ArrayList<LinearConstraint>();
        constraints.add(new LinearConstraint(new double[] { 1, 1, 0 },
                Relationship.GEQ,  1));
        constraints.add(new LinearConstraint(new double[] { 1, 0, 1 },
                Relationship.GEQ,  1));
        constraints.add(new LinearConstraint(new double[] { 0, 1, 0 },
                Relationship.GEQ,  1));

        SimplexSolver solver = new SimplexSolver();
        PointValuePair solution = solver.optimize(function, constraints,GoalType.MAXIMIZE,true);
        System.out.println("GETPOINT");
        for(double d: solution.getPoint()){
            System.out.println(" " + d);
        }
    }
    public double[] doOptimize(double[] functionArr, double functionConstant, double[][] statement, double statmentConstant, boolean isMinimize){
        LinearObjectiveFunction function = new LinearObjectiveFunction(functionArr, functionConstant);
        Collection<LinearConstraint> constraints = new ArrayList<LinearConstraint>();
//        constraints.
        return null;
    }
}

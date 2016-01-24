package logic;

import com.mkyong.web.controller.HelloController;
import org.apache.commons.math3.optim.MaxIter;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by user on 22-Dec-15.
 */
public class Optim {
    public static void main(String[] args) {
        Optim optim = new Optim();
        optim.matrixReturn(6);
        System.out.println();
        LinearObjectiveFunction f = new LinearObjectiveFunction(new double[]{2, 2, 1,2,1,1},6);
        ArrayList<LinearConstraint> constraints = new ArrayList<LinearConstraint>();
//        optim.
// doOptimize(new double[]{2,2,1},0,3,new double[]{10,20,30},new  double[]{35,40,45},true);
//        constraints.add(new LinearConstraint(new double[]{1, 1, 0}, Relationship.GEQ, 110));
//        constraints.add(new LinearConstraint(new double[]{0, 1, 1}, Relationship.GEQ, 250));
//        constraints.add(new LinearConstraint(new double[]{1, 0, 1}, Relationship.GEQ, 300));
        constraints.add(new LinearConstraint(new double[]{1, 0, 0,0,0,0}, Relationship.GEQ, 90));
        constraints.add(new LinearConstraint(new double[]{0, 1, 0,0,0,0}, Relationship.GEQ, 10));
        constraints.add(new LinearConstraint(new double[]{0, 0, 1,0,0,0}, Relationship.GEQ, 10));
        constraints.add(new LinearConstraint(new double[]{0, 0, 0,1,0,0}, Relationship.GEQ, 10));
        constraints.add(new LinearConstraint(new double[]{0, 0, 0,0,1,0}, Relationship.GEQ, 10));
        constraints.add(new LinearConstraint(new double[]{0, 0, 0,0,0,1}, Relationship.GEQ, 10));

        constraints.add(new LinearConstraint(new double[]{1, 0, 0,0,0,0}, Relationship.LEQ, 100));
        constraints.add(new LinearConstraint(new double[]{0, 1, 0,0,0,0}, Relationship.LEQ, 100));
        constraints.add(new LinearConstraint(new double[]{0, 0, 1,0,0,0}, Relationship.LEQ, 100));
        constraints.add(new LinearConstraint(new double[]{0, 0, 0,1,0,0}, Relationship.LEQ, 100));
        constraints.add(new LinearConstraint(new double[]{0, 0, 0,0,1,0}, Relationship.LEQ, 100));
        constraints.add(new LinearConstraint(new double[]{0, 0, 0,0,0,1}, Relationship.LEQ, 100));
        constraints.add(new LinearConstraint(new double[]{1, 1, 1,1,1,1}, Relationship.GEQ, 200));
        SimplexSolver solver = new SimplexSolver();
        PointValuePair solution = solver.optimize(new MaxIter(100), f, new LinearConstraintSet(constraints),
                GoalType.MINIMIZE, new NonNegativeConstraint(false));
        System.out.println("Result solution");
        for (double d : solution.getPoint()) {
            System.out.println(d + " ");
        }
         System.out.println("Peremennaaya: " + solution.getValue());
    }

    public double[] doOptimize(double[] functionArg, double functionConstant, int n, double[] statementGEQ, double[] statementLEQ, boolean isMinimize) {
        System.out.println("In doOptimize");
        LinearObjectiveFunction function = new LinearObjectiveFunction(functionArg, functionConstant);
        Collection<LinearConstraint> constraints = new ArrayList<LinearConstraint>();
        constraints.add(new LinearConstraint(new double[]{1, 0, 0, 0, 0, 0}, Relationship.GEQ, statementGEQ[0]));
        constraints.add(new LinearConstraint(new double[]{0, 1, 0, 0, 0, 0}, Relationship.GEQ, statementGEQ[1]));
        constraints.add(new LinearConstraint(new double[]{0, 0, 1, 0, 0, 0}, Relationship.GEQ, statementGEQ[2]));
        constraints.add(new LinearConstraint(new double[]{0, 0, 0, 1, 0, 0}, Relationship.GEQ, statementGEQ[3]));
        constraints.add(new LinearConstraint(new double[]{0, 0, 0, 0, 1, 0}, Relationship.GEQ, statementGEQ[4]));
        constraints.add(new LinearConstraint(new double[]{0, 0, 0, 0, 0, 1}, Relationship.GEQ, statementGEQ[5]));


        constraints.add(new LinearConstraint(new double[]{1, 0, 0, 0, 0, 0}, Relationship.LEQ, statementLEQ[0]));
        constraints.add(new LinearConstraint(new double[]{0, 1, 0, 0, 0, 0}, Relationship.LEQ, statementLEQ[1]));
        constraints.add(new LinearConstraint(new double[]{0, 0, 1, 0, 0, 0}, Relationship.LEQ, statementLEQ[2]));
        constraints.add(new LinearConstraint(new double[]{0, 0, 0, 1, 0, 0}, Relationship.LEQ, statementLEQ[3]));
        constraints.add(new LinearConstraint(new double[]{0, 0, 0, 0, 1, 0}, Relationship.LEQ, statementLEQ[4]));
        constraints.add(new LinearConstraint(new double[]{0, 0, 0, 0, 0, 1}, Relationship.LEQ, statementLEQ[5]));
//        constraints.add(new LinearConstraint(new double[]{1, 1, 1,1,1,1}, Relationship.GEQ, statementLEQ[6]));
//        for (int i = 0; i < n; i++) {
//            System.out.println("statementGEQ[i] = " +  statementGEQ[i]);
//            constraints.add(new LinearConstraint(matrixReturn(n)[i], Relationship.GEQ, statementGEQ[i]));
////          constraints.add(new LinearConstraint(arrayReturn(n), Relationship.GEQ, statementGEQ[i]));
////            for (double d : matrixReturn(n)[i]) {
////                System.out.println("from constraint.add GEQ: i=" + i + " d=" + d);
////            }
//        }
//        for (int i = 0; i < n; i++) {
//            System.out.println("statementLEQ[i] = " +  statementLEQ[i]);
//            constraints.add(new LinearConstraint(arrayReturn(n), Relationship.LEQ, statementLEQ[i]));
////            constraints.add(new LinearConstraint(matrixReturn(n)[i], Relationship.LEQ, statementLEQ[i]));
////            for (double d : matrixReturn(n)[i]) {
////             System.out.println("from constraint.add LEQ: i=" + i + " d=" + d);
////             }
//        }
        double sumOfGrand = 0;
        double sumOfLow = 0;
        for(int i =0; i<n; i++){
            sumOfGrand += statementLEQ[i];
            sumOfLow += statementGEQ[i];
        }

        constraints.add(new LinearConstraint(new double[]{1, 1, 1, 1, 1, 1}, Relationship.GEQ, sumOfLow));
        constraints.add(new LinearConstraint(arrayReturn(n), Relationship.LEQ, sumOfGrand* 0.9));
//        constraints.add(new LinearConstraint(arrayReturn(n), Relationship.GEQ, sumOfLow));
        SimplexSolver solver = new SimplexSolver();
        PointValuePair solution = solver.optimize(new MaxIter(100), function, new LinearConstraintSet(constraints),
                isMinimize? GoalType.MINIMIZE: GoalType.MAXIMIZE, new NonNegativeConstraint(false));
        System.out.println();
        System.out.println("Result solution");
        for (double d : solution.getPoint()) {
            System.out.print(d + " ");
        }
        System.out.println("getValue: " + solution.getValue());
        System.out.println("getSecond: " + solution.getSecond());
        HelloController.setCriteria(solution.getValue());
            return solution.getPoint();
    }

    public double[] arrayReturn(int n){
        double[] arr = new double[n];
        for(int i=0; i< arr.length; i++){
            arr[i] = 1;
        }
        return arr;
    }
    public double[][] matrixReturn ( int n){
        double[][] arrarr = new double[n][n];
        for (int i = 0; i < arrarr.length; i++) {

            for (int j = 0; j < arrarr[i].length; j++) {
                arrarr[j][j] = 1;

            }
        }
        return arrarr;
    }
}



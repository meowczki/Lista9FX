package odeOla;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;

public class myFODE implements FirstOrderDifferentialEquations {
    private double mi;
    public myFODE(double mi) {
        this.mi = mi;
    }

    //rownanie 2 rzedu
    public int getDimension() {
        return 2;
    }

    public void computeDerivatives(double t, double[] x, double[] dxdt) throws MaxCountExceededException, DimensionMismatchException {
        //predkosc x[1]
        dxdt[0]=x[1];//x'=v
        //polozenie x[0]
        dxdt[1]=mi*(1-x[0]*x[0])*dxdt[0]-x[0];

    }
}

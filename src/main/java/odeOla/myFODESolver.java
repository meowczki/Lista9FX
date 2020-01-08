package odeOla;
import org.apache.commons.math3.ode.FirstOrderIntegrator;
import org.apache.commons.math3.ode.nonstiff.EulerIntegrator;

import java.util.ArrayList;
import java.util.List;

public class myFODESolver {
    private List<Double>xValues;
    private List<Double>yValues;

    public List<Double> getxValues() {
        return xValues;
    }

    public void setxValues(List<Double> xValues) {
        this.xValues = xValues;
    }

    public List<Double> getyValues() {
        return yValues;
    }

    public void setyValues(List<Double> yValues) {
        this.yValues = yValues;
    }

    public myFODESolver(double mi,double t0,double tk,double x0,double v0, String wykres,int lp) {
        if (mi < 0 ) {
            throw new IllegalArgumentException("nieprawidłowa wartość mi");
        }
        if (lp < 0 ) {
            throw new IllegalArgumentException("nieprawidłowa wartość liczby punktów");
        }
        myFODE myFODE = new myFODE(mi);
        FirstOrderIntegrator foi = new EulerIntegrator(0.001);
        double xStart[] = {v0,x0};
        double xStop[] = {0., 0.};
        List<Double> t = new ArrayList<>();
        List<Double> x = new ArrayList<>();
        List<Double> v = new ArrayList<>();
        t.add(t0);
        v.add(xStart[0]);
        x.add(xStart[1]);
        double tStop=tk;
        for (int i = 0; i < lp-1; i++) {
            t.add(t.get(i) + tStop / lp);
        }
        for (int i = 1; i < lp; i++) {
            foi.integrate(myFODE, t0, xStart, t.get(i), xStop);
            v.add(xStop[0]);
            x.add(xStop[1]);
        }
        switch (wykres) {
            case "x(t)":
                yValues=x;
                xValues=t;
                break;
            case "v(t)":
                yValues=v;
                xValues=t;
                break;case "fazowy":
                yValues=x;
                xValues=v;
                break;

        }


    }




}

package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DiagramData
{
    private String formula;
    private List <Double> pointsY=new ArrayList<>(0);
    private List <Double> pointsX=new ArrayList<>(0);

    private int green=0;
    private int red=0;
    private int blue=0;

    public DiagramData(String formula)
    {
        this.formula = formula;
        Random random=new Random();

        int[] valuesForGenerations=new int[10];
        for(int i=0;i<10;i++)
            valuesForGenerations[i]=25*i;

        green=valuesForGenerations[random.nextInt(10)];
        red=valuesForGenerations[random.nextInt(10)];
        blue=valuesForGenerations[random.nextInt(10)];
    }

    public String getFormula()
    {
        return this.formula;
    }

    public void addPointY(Double number) {
        this.pointsY.add(number);
    }

    public void addPointX(Double number) {
        this.pointsX.add(number);
    }

    public List<Double> getPointsY()
    {
        return this.pointsY;
    }

    public List<Double> getPointsX()
    {
        return this.pointsX;
    }

    public int getGreen(){return this.green;}
    public int getRed(){return this.red;}
    public int getBlue(){return this.blue;}
}

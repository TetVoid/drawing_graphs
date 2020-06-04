package controller;

import model.DiagramData;

import java.util.ArrayList;
import java.util.List;

public class FormulaCounter implements Runnable
{
    private String formula;
    private int iterator=0;
    private Double startValue=0.0;
    private Double xValue=0.0;
    private Double max;
    private Double min;
    private DiagramData grafic;

    public FormulaCounter(String formula, Double startValue)
    {
        this.formula=formula;
        this.startValue=startValue;
        this.xValue=startValue;
    }

    public Double getValueX()
    {
        return xValue;
    }

    public void initCounter(Double min,Double max,DiagramData grafic)
    {
        this.min=min;
        this.max=max;
        this.grafic=grafic;
    }

    @Override
    public synchronized void run() {
        for(int h=0;h<(max-min)*10;h++) {
            String workMap = formula;
            List<Character> actionMap = new ArrayList<>(0);
            List<Double> numberMap = new ArrayList<>(0);

            xValue = startValue + 0.1 * iterator;


            iterator++;
            String numberString = new String();
            for (int i = 0; i < workMap.length(); i++) {

                if ((workMap.charAt(i) == '+') || (workMap.charAt(i) == '-') || (workMap.charAt(i) == '*') || (workMap.charAt(i) == '/')) {
                    actionMap.add(workMap.charAt(i));

                    if (numberString.equals("x"))
                        numberMap.add(xValue);
                    else {
                        Double newNumber = Double.valueOf(numberString);
                        numberMap.add(newNumber);
                    }
                    numberString = "";
                } else {
                    numberString += workMap.charAt(i);
                }
            }

            if (numberString.equals("x"))
                numberMap.add(xValue);
            else {
                Double newNumber = Double.valueOf(numberString);
                numberMap.add(newNumber);
            }


            for (int i = 0; i < actionMap.size(); i++) {
                if ((actionMap.get(i) == '*') || (actionMap.get(i) == '/')) {
                    switch (actionMap.get(i)) {
                        case ('*'):
                            numberMap.set(i, this.multiplication(numberMap.get(i), numberMap.get(i + 1)));
                            numberMap.remove(i + 1);
                            actionMap.remove(i);
                            break;
                        case ('/'):
                            numberMap.set(i, this.div(numberMap.get(i), numberMap.get(i + 1)));
                            numberMap.remove(i + 1);
                            actionMap.remove(i);
                            break;
                    }
                }
            }

            for (int i = 0; i < actionMap.size(); i++) {
                if ((actionMap.get(i) == '+' || actionMap.get(i) == '-')) {
                    switch (actionMap.get(i)) {
                        case ('+'):
                            numberMap.set(i, this.plus(numberMap.get(i), numberMap.get(i + 1)));
                            numberMap.remove(i + 1);
                            actionMap.remove(i);
                            break;

                        case ('-'):
                            numberMap.set(i, this.minus(numberMap.get(i), numberMap.get(i + 1)));
                            numberMap.remove(i + 1);
                            actionMap.remove(i);
                            break;
                    }
                }
            }

            grafic.addPointY(Math.floor(numberMap.get(0) * 1000) / 1000);
            grafic.addPointX(xValue);
            
            notify();
        }
    }

    private Double plus(Double tempAnswer, Double number)
    {
        return tempAnswer+number;
    }

    private Double minus(Double tempAnswer, Double number)
    {
        return tempAnswer-number;
    }

    private Double multiplication(Double tempAnswer, Double number)
    {
        return tempAnswer*number;
    }

    private Double div(Double tempAnswer, Double number)
    {
        return tempAnswer/number;
    }


}

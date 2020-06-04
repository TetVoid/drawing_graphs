package GraficalShell;

import controller.FormulaCounter;
import controller.TableController;
import model.DiagramData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;


public class SetFormulaSelectionListener extends SelectionAdapter
{
    Text formula=null;
    Text minValue=null;
    Text maxValue=null;
    TableController tableController=null;
    GrafDrawingWidget grafDrawingWidget=null;
    SetFormulaSelectionListener(Text formula, Text minValue, Text maxValue, TableController tableController, GrafDrawingWidget grafDrawingWidget)
    {
        this.grafDrawingWidget=grafDrawingWidget;
        this.tableController=tableController;
        this.formula=formula;
        this.maxValue=maxValue;
        this.minValue=minValue;
    }

    public void widgetSelected(SelectionEvent event) {
        Boolean errorFlag = false;
        for (int i = 0; i < maxValue.getText().length(); i++)
            if (!Character.isDigit(maxValue.getText().charAt(i)))
                errorFlag = true;

        for (int i = 0; i < minValue.getText().length(); i++)
            if (!Character.isDigit(minValue.getText().charAt(i)))
                errorFlag = true;

            String strFormula=formula.getText();
        for (int i = 0; i < formula.getText().length(); i++)
            if (!Character.isDigit(strFormula.charAt(i)) && strFormula.charAt(i) != '-' && strFormula.charAt(i) != '+' && strFormula.charAt(i) != '*' && strFormula.charAt(i) != '/'&& strFormula.charAt(i) != 'x')
                errorFlag = true;

        if (errorFlag == false)
        {
            Double max = Double.parseDouble(maxValue.getText());
            Double min = Double.parseDouble(minValue.getText());

            DiagramData grafic = new DiagramData(formula.getText());
            FormulaCounter formulaCounter = new FormulaCounter(formula.getText(), min);


            formulaCounter.initCounter(min,max,grafic);

            new Thread(formulaCounter).start();
            grafDrawingWidget.initGrafic(grafic);

            tableController.initNewGrafic(grafic);
        }
        else
            formula.setText("Ошибка ввода");
    }
}

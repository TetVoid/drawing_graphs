package controller;

import model.DiagramData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class TableController
{
    Table tableView=null;
    int workColumn=0;
    public TableController(Table table)
    {
        this.tableView=table;
    }
    private void tableAddColumn(DiagramData formula)
    {
        TableColumn column = new TableColumn(tableView, SWT.NONE);
        column.setText(formula.getFormula());
        column.setWidth(128);
    }


    public void initNewGrafic(DiagramData diagrama)
    {
        this.tableAddColumn(diagrama);
        TableItem[] arrayOfItems =  tableView.getItems();

        if(arrayOfItems.length<diagrama.getPointsX().size())
        {
            for(int i=0;i<diagrama.getPointsX().size()-arrayOfItems.length;i++)
            {
                TableItem newItem = new TableItem(tableView, SWT.NONE);
            }
             arrayOfItems =  tableView.getItems();
        }

        for(int i=0;i<diagrama.getPointsY().size();i++)
        {
            arrayOfItems[i].setText(workColumn,Double.toString(diagrama.getPointsY().get(i)));
        }
        workColumn++;
    }


}

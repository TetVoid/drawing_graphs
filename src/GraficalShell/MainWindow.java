package GraficalShell;

import controller.TableController;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

public class MainWindow
{
    public static void main(String[] args)
    {
        Shell shell;
        Display display = new Display();
        shell = new Shell(display);
        shell.setLayout(new RowLayout(SWT.HORIZONTAL));

        Composite buttonAndText=new Composite(shell,SWT.NONE);
        Composite labelAndText=new Composite(buttonAndText,SWT.NONE);
        Composite buttonsAndTextColumn1=new Composite(labelAndText,SWT.NONE);
        Composite buttonsAndTextColumn2=new Composite(labelAndText,SWT.NONE);

        buttonAndText.setLayout(new RowLayout(SWT.VERTICAL));
        labelAndText.setLayout(new RowLayout(SWT.HORIZONTAL));
        buttonsAndTextColumn1.setLayout(new RowLayout(SWT.VERTICAL));
        buttonsAndTextColumn2.setLayout(new RowLayout(SWT.VERTICAL));

        Table tableView = new Table(shell, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        tableView.setHeaderVisible(true);
        RowData tableData = new RowData(128,300);
        tableView.setLayoutData(tableData);

        TableController tableController=new TableController(tableView);

        Label formulaLable=new Label(buttonsAndTextColumn1,SWT.NONE);
        Label minValueLabel=new Label(buttonsAndTextColumn1,SWT.NONE);
        Label maxLabel=new Label(buttonsAndTextColumn1,SWT.NONE);

        formulaLable.setText("formula");
        minValueLabel.setText("minValue");
        maxLabel.setText("maxValue");

        Text formula =new Text(buttonsAndTextColumn2,SWT.NONE);
        Text minValue =new Text(buttonsAndTextColumn2,SWT.NONE);
        Text maxValue =new Text(buttonsAndTextColumn2,SWT.NONE);

        RowData data =new RowData(70,20);
        formula.setLayoutData(data);
        minValue.setLayoutData(data);
        maxValue.setLayoutData(data);

        Button setButton = new Button(buttonAndText,SWT.NONE);
        setButton.setText("setFormula");

        GrafDrawingWidget grafDrawingWidget=new GrafDrawingWidget(shell);

        setButton.addSelectionListener(new SetFormulaSelectionListener(formula,minValue,maxValue,tableController,grafDrawingWidget) );

        shell.pack();
        shell.open();
        while (!shell.isDisposed())
        {
            if (!display.readAndDispatch())
                display.sleep();
        }
    }
}

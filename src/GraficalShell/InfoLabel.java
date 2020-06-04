package GraficalShell;

import model.DiagramData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class InfoLabel {

    private Composite infoComposite;
    private Canvas color ;
    private Label labelForFormula;
    private Label labelForScale;
    private Label labelForSingleSegment;

    InfoLabel(DiagramData grafic, Composite composite, int drawingFactor)
    {
        infoComposite=new Composite(composite, SWT.NONE);
        infoComposite.setLayout(new RowLayout(SWT.HORIZONTAL));

        color = new Canvas(infoComposite,SWT.NONE);
        labelForFormula=new Label(infoComposite,SWT.NONE);
        labelForScale=new Label(infoComposite,SWT.NONE);
        labelForScale.setLayoutData(new RowData(100,20));
        labelForSingleSegment=new Label(infoComposite,SWT.NONE);

        color.setLayoutData(new RowData(10,20));
        color.addPaintListener(new PaintListener() {
            public void paintControl(PaintEvent e) {
                Color color1=new Color(e.gc.getDevice(),grafic.getRed(),grafic.getGreen(),grafic.getBlue());
                e.gc.setBackground(color1);
                e.gc.fillRectangle(0,0,10,20);
            }
        });

        labelForFormula.setText(grafic.getFormula()+"|");


        labelForScale.setText("Масштаб 1/"+64/drawingFactor);

        labelForSingleSegment.setText("|Ед. отрезок 75 пикселей");

        int x=composite.getParent().getSize().x;
        int y=composite.getParent().getSize().y+30;
        composite.getParent().setSize(x,y);

        x=composite.getSize().x;
        y=composite.getSize().y+30;

        composite.setLayoutData(new RowData(composite.getSize().x,composite.getSize().y+30));

    }
    public void redraw(int drawingFactor)
    {
        labelForScale.setText("Масштаб 1/"+64/drawingFactor);
    }
}

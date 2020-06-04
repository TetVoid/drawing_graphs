package GraficalShell;

import GraficalShell.InfoLabel;
import model.DiagramData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

public class GrafDrawingWidget  {
    private Shell shell=null;
    private Canvas canvas=null;
    private int drawingFactor=8;
    private DiagramData grafic;

    private Double maxCordsY=0.0;
    private Double maxCordsX=0.0;

    private Composite buttons;
    private Composite canvasSliderButtons;
    private Composite canvasAndSlider;

    private Slider verticalSlider;
    private Slider horizontalSlider;

    private Button decrease;
    private Button increase;

    private int[] horizontalNumbers=new int[5];
    private int[] verticalNumbers=new int[5];

    private int lineSize=3;

    private int mouseCordsX=0;
    private int mouseCordsY=0;

    private boolean mouseFlag=false;
    private boolean ctrlFlag=false;

    private void increaseButtonAction ()
    {

        if(drawingFactor<50) {
            lineSize++;
            drawingFactor *= 2;
        }
        if (maxCordsY * drawingFactor > 300) {
            verticalSlider.setVisible(true);
        }

        if (maxCordsX * drawingFactor > 300) {
            horizontalSlider.setVisible(true);
        }

        verticalSlider.setMaximum((int) (maxCordsY * drawingFactor));
        horizontalSlider.setMaximum((int) (maxCordsX * drawingFactor));
        canvas.redraw();
    }

    private void decreaseButtonAction ()
    {
        if (lineSize != 1)
            lineSize--;
        if (drawingFactor / 2 != 0)
            drawingFactor /= 2;

        if (maxCordsY * drawingFactor < 300) {
            verticalSlider.setVisible(false);
            verticalSlider.setSelection(0);
        }

        if (maxCordsX * drawingFactor < 300) {
            horizontalSlider.setVisible(false);
            horizontalSlider.setSelection(0);
        }
        horizontalSlider.setMaximum((int) (maxCordsX * drawingFactor));
        verticalSlider.setMaximum((int) (maxCordsY * drawingFactor));

        canvas.redraw();
    }

    private void initButtons() {
        decrease.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                decreaseButtonAction();
            }
        });

        increase.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                increaseButtonAction();
            }
        });

        horizontalSlider.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                canvas.redraw();
            }
        });

        verticalSlider.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                canvas.redraw();
            }
        });

        canvas.addMouseListener(new MouseListener() {
            public void mouseDown(MouseEvent e) {
                mouseCordsX = e.x;
                mouseCordsY = e.y;

                mouseFlag = true;

            }

            public void mouseUp(MouseEvent e) {
                mouseFlag = false;
            }

            public void mouseDoubleClick(MouseEvent e) {

            }

        });

        canvas.addMouseMoveListener(new MouseMoveListener() {
            public void mouseMove(MouseEvent b) {
                if (mouseFlag == true) {
                    horizontalSlider.setSelection((mouseCordsX + b.x));
                    verticalSlider.setSelection((mouseCordsY + b.y));
                    canvas.redraw();
                }
            }
        });


        canvas.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {

                if (SWT.HardKeyDown==33)
                    ctrlFlag = true;

            }
            public void keyReleased(KeyEvent e)
            {
                if(SWT.HardKeyUp==34)
                    ctrlFlag=false;
            }
        });

        canvas.addMouseWheelListener(new MouseWheelListener() {
            public void mouseScrolled(MouseEvent b) {

                if (ctrlFlag == true)
                    if (b.count != 3)
                        decreaseButtonAction();
                    else
                        increaseButtonAction();
            }
        });

    }

    private void drawingBorders()
    {
        canvas.addPaintListener(new PaintListener() {
            public void paintControl(PaintEvent e) {
                horizontalNumbers[0]=0;
                horizontalNumbers[1]=8;
                horizontalNumbers[2]=16;
                horizontalNumbers[3]=24;
                horizontalNumbers[4]=32;

                verticalNumbers[0]=0;
                verticalNumbers[1]=8;
                verticalNumbers[2]=16;
                verticalNumbers[3]=24;
                verticalNumbers[4]=32;

                Color color1 = new Color(e.gc.getDevice(), 0, 0, 0);

                e.gc.setForeground(color1);
                e.gc.setLineWidth(3);

                e.gc.drawLine(15,314,314,314);
                e.gc.drawLine(314,314,305,305);
                e.gc.drawLine(16,314,16,15);
                e.gc.drawLine(16,15,25,25);

                e.gc.drawLine(16,25,9,25);
                e.gc.drawLine(16,87,9,87);
                e.gc.drawLine(16,160,9,160);
                e.gc.drawLine(16,237,9,237);
                e.gc.drawLine(16,314,9,314);

                e.gc.drawLine(16,314,16,321);
                e.gc.drawLine(87,314,87,321);
                e.gc.drawLine(160,314,160,321);
                e.gc.drawLine(237,314,237,321);
                e.gc.drawLine(305,314,305,321);

                e.gc.drawText("Y",0,0);
                e.gc.drawText("X",315,300);

                e.gc.drawText(Integer.toString(horizontalNumbers[0]*8/drawingFactor+horizontalSlider.getSelection()/drawingFactor),11,319);
                e.gc.drawText(Integer.toString(horizontalNumbers[1]*8/drawingFactor+horizontalSlider.getSelection()/drawingFactor),82,319);
                e.gc.drawText(Integer.toString(horizontalNumbers[2]*8/drawingFactor+horizontalSlider.getSelection()/drawingFactor),155,319);
                e.gc.drawText(Integer.toString(horizontalNumbers[3]*8/drawingFactor+horizontalSlider.getSelection()/drawingFactor),232,319);
                e.gc.drawText(Integer.toString(horizontalNumbers[4]*8/drawingFactor+horizontalSlider.getSelection()/drawingFactor),300,319);

                e.gc.drawText(Integer.toString(verticalNumbers[0]*8/drawingFactor+verticalSlider.getSelection()/drawingFactor),0,305);
                e.gc.drawText(Integer.toString(verticalNumbers[1]*8/drawingFactor+verticalSlider.getSelection()/drawingFactor),0,240);
                e.gc.drawText(Integer.toString(verticalNumbers[2]*8/drawingFactor+verticalSlider.getSelection()/drawingFactor),0,163);
                e.gc.drawText(Integer.toString(verticalNumbers[3]*8/drawingFactor+verticalSlider.getSelection()/drawingFactor),0,90);
                e.gc.drawText(Integer.toString(verticalNumbers[4]*8/drawingFactor+verticalSlider.getSelection()/drawingFactor),0,28);

            }
        });
    }



    GrafDrawingWidget(Shell shell)
    {

        this.shell=shell;
        canvasSliderButtons=new Composite(shell,SWT.NONE);
        canvasAndSlider=new Composite(canvasSliderButtons,SWT.NONE);

        canvas=new Canvas(canvasAndSlider, SWT.NONE);
        RowData canvasData=new RowData(330,335);
        canvas.setLayoutData(canvasData);

        verticalSlider=new Slider(canvasAndSlider,SWT.VERTICAL);
        horizontalSlider=new Slider(canvasSliderButtons,SWT.HORIZONTAL);

        verticalSlider.setLayoutData(new RowData(20,330));
        horizontalSlider.setLayoutData(new RowData(330,20));

        verticalSlider.setVisible(false);
        horizontalSlider.setVisible(false);

        verticalSlider.setMinimum(0);
        horizontalSlider.setMinimum(0);
        verticalSlider.setMaximum((int)(maxCordsY*drawingFactor));
        horizontalSlider.setMaximum((int)(maxCordsX*drawingFactor));

        buttons = new Composite(canvasSliderButtons,SWT.NONE);

        buttons.setLayout(new RowLayout(SWT.HORIZONTAL));
        canvasAndSlider.setLayout(new RowLayout(SWT.HORIZONTAL));
        canvasSliderButtons.setLayout(new RowLayout(SWT.VERTICAL));

        this.drawingBorders();

        decrease=new Button(buttons,SWT.NONE);
        increase=new Button(buttons,SWT.NONE);

        decrease.setText("-");
        increase.setText("+");

        this.initButtons();


    }


    void initGrafic(DiagramData grafic)
    {
        this.grafic=grafic;
        InfoLabel infoLabel=new InfoLabel(grafic,canvasSliderButtons,drawingFactor);

        canvas.addPaintListener(new PaintListener() {
            public void paintControl(PaintEvent e) {
                Color color1=new Color(e.gc.getDevice(),grafic.getRed(),grafic.getGreen(),grafic.getBlue());
                e.gc.setForeground(color1);
                e.gc.setLineWidth(lineSize);


                for(int i=1;i<grafic.getPointsX().size();i++)
                {
                    int x1=(int)(grafic.getPointsX().get(i-1)*drawingFactor-horizontalSlider.getSelection()+15);
                    int y1=315+verticalSlider.getSelection()-(int)(grafic.getPointsY().get(i-1)*drawingFactor);
                    int x2=(int)(grafic.getPointsX().get(i)*drawingFactor-horizontalSlider.getSelection()+15);
                    int y2=315+verticalSlider.getSelection()-(int)(grafic.getPointsY().get(i)*drawingFactor);
                    if(x1>15&&y1<316&&y2>16)
                        e.gc.drawLine(x1,y1,x2,y2);
                    else {
                        if (y1 > 316&&y2<316) {
                            e.gc.drawLine(x1, 316, x2, y2);
                        }
                        if(y2<16&&y1>16)
                        {
                            e.gc.drawLine(x1, y1, x2, 16);
                        }
                    }

                }

                if(maxCordsY<grafic.getPointsY().get(grafic.getPointsY().size()-1))
                {
                    maxCordsY=grafic.getPointsY().get(grafic.getPointsY().size()-1);

                    if(maxCordsY*drawingFactor>300)
                    {
                        verticalSlider.setVisible(true);
                        verticalSlider.setMaximum((int)(maxCordsY*drawingFactor));
                    }
                }

                if(maxCordsX<grafic.getPointsX().get(grafic.getPointsX().size()-1))
                {
                    maxCordsX=grafic.getPointsX().get(grafic.getPointsX().size()-1);

                    if(maxCordsX*drawingFactor>300)
                    {
                        horizontalSlider.setVisible(true);
                        horizontalSlider.setMaximum((int)(maxCordsX*drawingFactor));
                    }
                }
                infoLabel.redraw(drawingFactor);
            }
        });

        canvas.redraw();
    }

}

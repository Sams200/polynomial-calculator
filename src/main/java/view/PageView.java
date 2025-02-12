package view;

import controller.PageController;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PageView extends JPanel {
    private PageController controller;

    //fonts
    private final Font fontLargeTitle=new Font("Bauhaus 93",Font.PLAIN,55);
    private final Font fontAuthor=new Font("Bauhaus 93",Font.PLAIN,30);
    private final Font fontMath=new Font("Cascadia Code",Font.PLAIN,20);
    ////////////////////////

    //dimensions
    private final Dimension pageDimension=new Dimension(1250,750);
    private final Dimension pageMin=new Dimension(1250,500);
    ///////////////////

    //borders
    private Border borderRaised=BorderFactory.createBevelBorder(BevelBorder.RAISED);
    private Border borderLowered=BorderFactory.createBevelBorder(BevelBorder.LOWERED);
    private Border borderRed=BorderFactory.createLineBorder(Color.RED,5,true);
    private Border borderBlue=BorderFactory.createLineBorder(Color.BLUE,5,true);
    private Border borderGreen=BorderFactory.createLineBorder(Color.GREEN,5,true);
    /////////////////////////////////

    //main page
    private JFrame frame = new JFrame("Polynomial Calculator");
    private JPanel page = new JPanel();
    private JScrollPane pane = new JScrollPane(page, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    private JPanel top = new JPanel();
    private JPanel mid = new JPanel();
    private JPanel bottom = new JPanel();
    //////////////////////////////

    //top
    private JLabel labelProjectTitle=new JLabel("Polynomial Calculator");
    private JLabel labelAuthor=new JLabel("by Suciu Andrei");
    private JPanel panelTopSecond=new JPanel();
    /////////////////////////////

    //mid
    private JPanel panelMidContent=new JPanel();
        private JPanel panelMidFirst=new JPanel();
            private JLabel labelFx=new JLabel("f(x)");
            private JTextField fieldFirstPoly=new JTextField();
        private JPanel panelMidSecond=new JPanel();
            private JButton buttonSwitch=new JButton("↕ Switch");
            private String[] operations={"+","-","∙","÷","df/dx","∫f(x)dx",};
            private JComboBox<String> comboOperations=new JComboBox<String>(operations);
            private JButton buttonCompute=new JButton("Compute →");
            private JTextField resultText=new JTextField();
            private JScrollPane fieldResult=new JScrollPane(resultText,JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    private JPanel panelMidThird=new JPanel();
        private JLabel labelGx=new JLabel("g(x)");
        private JTextField fieldThirdPoly=new JTextField();
    ////////////////////////////

    //bottom
    ///////////////////////////


    public PageView(PageController pageController) {
        //initialize everything
        this.controller = pageController;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(pageDimension);
        frame.setContentPane(pane);

        page.setLayout(new BorderLayout());
        page.add(top, BorderLayout.PAGE_START);
        page.add(mid, BorderLayout.CENTER);
        page.add(bottom, BorderLayout.PAGE_END);

        page.setMinimumSize(pageMin);
        pane.setMinimumSize(pageMin);
        //////////////////////////////////


        //top
        top.setLayout(new BoxLayout(top, BoxLayout.LINE_AXIS));

        panelTopSecond.setLayout(new BoxLayout(panelTopSecond, BoxLayout.PAGE_AXIS));
        labelProjectTitle.setFont(fontLargeTitle);
        labelProjectTitle.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panelTopSecond.add(labelProjectTitle);
        labelAuthor.setFont(fontAuthor);
        labelAuthor.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panelTopSecond.add(labelAuthor);

        top.add(Box.createHorizontalGlue());
        top.add(panelTopSecond);
        top.add(Box.createHorizontalGlue());

        top.setBorder(borderRaised);
        top.addMouseListener(mouseAdapterTop);
        /////////////////////////////

        //mid
        {
            mid.setLayout(new BoxLayout(mid, BoxLayout.PAGE_AXIS));
            mid.add(Box.createVerticalStrut(50));
            mid.add(panelMidContent);
            mid.add(Box.createVerticalGlue());

            //mid add panels
            panelMidContent.setBorder(BorderFactory.createLoweredSoftBevelBorder());
            panelMidContent.setLayout(new BoxLayout(panelMidContent, BoxLayout.PAGE_AXIS));
            panelMidContent.add(panelMidFirst);
            panelMidContent.add(panelMidSecond);
            panelMidContent.add(panelMidThird);

            //set borders at mid
            panelMidFirst.setBorder(borderRaised);
            panelMidSecond.setBorder(borderRaised);
            panelMidThird.setBorder(borderRaised);

            //first
            panelMidFirst.setLayout(new BoxLayout(panelMidFirst, BoxLayout.LINE_AXIS));
            panelMidFirst.add(Box.createHorizontalGlue());
            panelMidFirst.add(labelFx);
            panelMidFirst.add(Box.createHorizontalStrut(15));
            panelMidFirst.add(fieldFirstPoly);
            panelMidFirst.add(Box.createHorizontalStrut(300));
            panelMidFirst.add(Box.createHorizontalGlue());

            fieldFirstPoly.setForeground(Color.GRAY);
            fieldFirstPoly.setColumns(30);
            fieldFirstPoly.setText("First Polynomial");
            fieldFirstPoly.setEditable(true);
            fieldFirstPoly.setMaximumSize(new Dimension(1000, 50));
            fieldFirstPoly.setFont(fontMath);
            fieldFirstPoly.setForeground(Color.GRAY);
            makePlaceholderTxt(fieldFirstPoly,"First Polynomial");
            labelFx.setFont(fontMath.deriveFont(28f));

            //third
            panelMidThird.setLayout(new BoxLayout(panelMidThird, BoxLayout.LINE_AXIS));
            panelMidThird.add(Box.createHorizontalGlue());
            panelMidThird.add(labelGx);
            panelMidThird.add(Box.createHorizontalStrut(15));
            panelMidThird.add(fieldThirdPoly);
            panelMidThird.add(Box.createHorizontalStrut(300));
            panelMidThird.add(Box.createHorizontalGlue());

            fieldThirdPoly.setForeground(Color.GRAY);
            fieldThirdPoly.setColumns(30);
            fieldThirdPoly.setText("Second Polynomial");
            fieldThirdPoly.setEditable(true);
            fieldThirdPoly.setMaximumSize(new Dimension(1000,50));
            fieldThirdPoly.setFont(fontMath);
            makePlaceholderTxt(fieldThirdPoly,"Second Polynomial");
            labelGx.setFont(fontMath.deriveFont(28f));

            //second
            panelMidSecond.setLayout(new BoxLayout(panelMidSecond,BoxLayout.LINE_AXIS));
            panelMidSecond.add(Box.createHorizontalGlue());
            panelMidSecond.add(Box.createHorizontalStrut(150));

            //switch
            panelMidSecond.add(buttonSwitch);
            buttonSwitch.setFont(fontMath);
            panelMidSecond.add(Box.createHorizontalStrut(10));
            buttonSwitch.addActionListener(e -> controller.pressedSwitch());

            //select operation
            panelMidSecond.add(comboOperations);
            comboOperations.setSelectedIndex(0);
            comboOperations.setMaximumSize(new Dimension(200,50));
            comboOperations.setFont(fontMath);
            panelMidSecond.add(Box.createHorizontalStrut(100));
            comboOperations.addActionListener(e -> controller.changedOperation());

            //compute
            panelMidSecond.add(buttonCompute);
            buttonCompute.setFont(fontMath);
            panelMidSecond.add(Box.createHorizontalStrut(10));
            buttonCompute.addActionListener(e -> controller.pressedCompute());

            //result
            panelMidSecond.add(fieldResult);
            fieldResult.setPreferredSize(new Dimension(400,70));
            resultText.setForeground(Color.GRAY);
            resultText.setText("Result");
            resultText.setEditable(false);
            fieldResult.setMaximumSize(new Dimension(1000,50));
            resultText.setFont(fontMath);

            panelMidSecond.add(Box.createHorizontalGlue());
            makePlaceholderTxt(resultText,"Result");

        }
        ////////////////////////////

        //bottom
        ///////////////////////////

    }

    public void setVisibility(boolean isVisible){
        frame.setVisible(isVisible);
    }

    //getters and setters
    public void setFieldFirstPoly(String txt){
        fieldFirstPoly.setText(txt);
    }
    public String getFieldFirstPoly(){
        return fieldFirstPoly.getText();
    }
    public void setFieldThirdPoly(String txt){
        fieldThirdPoly.setText(txt);
    }
    public String getFieldThirdPoly(){
        return fieldThirdPoly.getText();
    }
    public void setFieldResult(String txt){
        resultText.setText(txt);
    }

    public void setFirstForeground(Color color){
        fieldFirstPoly.setForeground(color);
    }
    public void setThirdForeground(Color color){
        fieldThirdPoly.setForeground(color);
    }
    public int getCombo(){
        return comboOperations.getSelectedIndex();
    }
    public void setCombo(int number){
        comboOperations.setSelectedIndex(number);
    }
    ////////////////////////////////////

    //other garbage
    private static void makePlaceholderTxt(JTextField field, String txt){
        FocusListener focusListener=new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(txt)) {
                    field.setText("");
                }
                field.setForeground(Color.WHITE);
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setForeground(Color.GRAY);
                    field.setText(txt);
                }
            }
        };
        field.addFocusListener(focusListener);
    }

    private final MouseAdapter mouseAdapterTop=new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            top.setBorder(borderLowered);
            //super.mouseClicked(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //System.out.println(mealType.getMeal());
            controller.openDocumentation();
            top.setBorder(borderRaised);
            //super.mouseReleased(e);
        }
    };

    /////////////////////////////////////////////
}

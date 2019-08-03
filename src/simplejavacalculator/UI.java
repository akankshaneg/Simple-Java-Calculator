/**
 * @name Simple Java Calculator
 * @package ph.calculator
 * @file UI.java
 * @author SORIA Pierre-Henry
 * @email pierrehs@hotmail.com
 * @link        http://github.com/pH-7
 * @copyright   Copyright Pierre-Henry SORIA, All Rights Reserved.
 * @license     Apache (http://www.apache.org/licenses/LICENSE-2.0)
 * @create      2012-03-30
 *
 * @modifiedby  Achintha Gunasekara
 * @modifiedby  Kydon Chantzaridis
 * @modweb      http://www.achinthagunasekara.com
 * @modemail    contact@achinthagunasekara.com
 * @modemail    kchantza@csd.auth.gr
 */
package simplejavacalculator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.border.CompoundBorder;

public class UI implements ActionListener {

    private final JFrame frame;
    private final JPanel panel;

    private final JTextField text, update;
    private final JButton but[], butDecimal, butAdd, butMinus, butMultiply, butDivide,
            butEqual, butCancel, butSquareRoot, butSquare, butOneDividedBy,
            butCos, butSin, butTan, butxpowerofy, butlog, butrate, butabs,
            butMem, butMemClr, butMemRcl, butBksp, butClear;
    private boolean memClicked, memCleared, bkspClick = false;
    private int height, width;

    private final Calculator calc;

    private final String[] buttonValue = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    public UI() {
        text = new JTextField();
        text.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        text.setHorizontalAlignment(SwingConstants.RIGHT);
        text.setFont(new Font("Arial", Font.PLAIN, 36));
        text.setBackground(new Color(15, 0, 25));
        text.setForeground(Color.white);
        text.setMargin(new Insets(5, 0, 5, 20));
        CompoundBorder border = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(),
                BorderFactory.createEmptyBorder(5, 0, 5, 10));
        text.setBorder(border);

        but = new JButton[10];
        for (int i = 0; i < 10; i++) {
            but[i] = ButtonFactory.getButton(buttonValue[i], ButtonType.PRIMARY);
        }

        butDecimal = ButtonFactory.getButton(".", ButtonType.PRIMARY);
        butAdd = ButtonFactory.getButton("+", ButtonType.OPERATION);
        butMinus = ButtonFactory.getButton("–", ButtonType.OPERATION);
        butMultiply = ButtonFactory.getButton("x", ButtonType.OPERATION);
        butDivide = ButtonFactory.getButton("÷", ButtonType.OPERATION);
        butEqual = ButtonFactory.getButton("=", ButtonType.OPERATION);
        butSquareRoot = ButtonFactory.getButton("√", ButtonType.SECONDARY);
        butSquare = ButtonFactory.getButton("x*x", ButtonType.SECONDARY);
        butOneDividedBy = ButtonFactory.getButton("1/x", ButtonType.SECONDARY);
        butCos = ButtonFactory.getButton("Cos", ButtonType.SECONDARY);
        butSin = ButtonFactory.getButton("Sin", ButtonType.SECONDARY);
        butTan = ButtonFactory.getButton("Tan", ButtonType.SECONDARY);
        butxpowerofy = ButtonFactory.getButton("x^y", ButtonType.SECONDARY);
        butlog = ButtonFactory.getButton("log10", ButtonType.SECONDARY);
        butrate = ButtonFactory.getButton("x%", ButtonType.SECONDARY);
        butabs = ButtonFactory.getButton("abs(x)", ButtonType.SECONDARY);

        butCancel = ButtonFactory.getButton("C", ButtonType.SECONDARY);

        // MEM button
        // This button will store the latest value when clicked. 
        butMem = ButtonFactory.getButton("MEM", ButtonType.SECONDARY);

        // MC button
        // This button will clear the stored memory value.
        butMemClr = ButtonFactory.getButton("MC", ButtonType.SECONDARY);

        // MR button
        // This button will display the stored memory value.
        butMemRcl = ButtonFactory.getButton("MR", ButtonType.SECONDARY);

        // BKSP button
        // This button will backspace the field values.
        butBksp = ButtonFactory.getButton("BKSP", ButtonType.PRIMARY);
        butBksp.setFont(butBksp.getFont().deriveFont(14.0f));

        butClear = ButtonFactory.getButton("CE", ButtonType.SECONDARY);

        // Update field
        // This field will provide updates to the user on what the status of the memory value
        // The field is Read-Only
        update = new JTextField();
        update.setEditable(false);

        calc = new Calculator();

        // Layout
        panel = new JPanel(new GridLayout(5, 6, 1, 1));
        panel.setBackground(new Color(15, 0, 25));
        frame = new JFrame("Open Source Calculator");
        // calculator.png file is located in src\simplejavacalculator
        frame.setIconImage(new ImageIcon(getClass().getResource("calculatorImg.png")).getImage());
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setMinimumSize(new Dimension(360, 350));
        width = frame.getWidth();
        height = frame.getHeight();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(text, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(butClear, BorderLayout.SOUTH);
    }

    public void init() {
        /*
         * New Code
         * 
         * Set default close operation to do nothing, implement a WindowListener / WindowAdapter 
         * and necessary imports in order to prompt the user before leaving the calculator window.
         */
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent w) {
                int confirmation;
                confirmation = JOptionPane.showConfirmDialog(null, "Exit calculator application?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    exit(frame);
                }
            }

            private void exit(JFrame frame) {
                frame.dispose();
            }
        });

        // Row #1
        panel.add(butSquare);
        panel.add(butSquareRoot);
        panel.add(butMem);
        panel.add(butMemClr);
        panel.add(butMemRcl);
        panel.add(butDivide);

        // Row #2
        panel.add(butOneDividedBy);
        panel.add(butlog);

        for (int i = 7; i <= 9; i++) {
            panel.add(but[i]);
            but[i].addActionListener(this);
        }

        panel.add(butMultiply);

        // Row #3
        panel.add(butSin);
        panel.add(butabs);

        for (int i = 4; i <= 6; i++) {
            panel.add(but[i]);
            but[i].addActionListener(this);
        }

        panel.add(butMinus);

        // Row #4
        panel.add(butCos);
        panel.add(butxpowerofy);

        for (int i = 1; i <= 3; i++) {
            panel.add(but[i]);
            but[i].addActionListener(this);
        }

        panel.add(butAdd);

        // Row #5
        panel.add(butTan);
        panel.add(butrate);panel.add(but[0]);
        but[0].addActionListener(this);
        panel.add(butDecimal);
        panel.add(butBksp);
        panel.add(butEqual);

        // ActionListeners
        butDecimal.addActionListener(this);
        butAdd.addActionListener(this);
        butMinus.addActionListener(this);
        butMultiply.addActionListener(this);
        butDivide.addActionListener(this);
        butSquare.addActionListener(this);
        butSquareRoot.addActionListener(this);
        butOneDividedBy.addActionListener(this);
        butCos.addActionListener(this);
        butSin.addActionListener(this);
        butTan.addActionListener(this);
        butxpowerofy.addActionListener(this);
        butlog.addActionListener(this);
        butrate.addActionListener(this);
        butabs.addActionListener(this);
        butEqual.addActionListener(this);
        butCancel.addActionListener(this);

        // New code - Adding new button ActionListeners
        butMem.addActionListener(this);
        butMemClr.addActionListener(this);
        butMemRcl.addActionListener(this);
        butBksp.addActionListener(this);
        butClear.addActionListener(this);

        // when the frame is resized, update the button/field sizes
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {

                // TODO - get the buttons to auto-resize with new layout
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final Object source = e.getSource();

        // implemented the decimal button to work here
        for (int i = 0; i < 10; i++) {
            if (source == but[i] || source == butDecimal) {
                if(source == butDecimal && !text.getText().contains(".")) {
                    text.replaceSelection(".");
                }
                else if(source == but[i]) {
                    text.replaceSelection(buttonValue[i]);
                }
                return;
            }
        }

        if (source == butAdd) {
            writer(calc.calculateBi(Calculator.BiOperatorModes.add, reader()));
        }

        if (source == butMinus) {
            writer(calc.calculateBi(Calculator.BiOperatorModes.minus, reader()));
        }

        if (source == butMultiply) {
            writer(calc.calculateBi(Calculator.BiOperatorModes.multiply,
                    reader()));
        }

        if (source == butDivide) {
            writer(calc
                    .calculateBi(Calculator.BiOperatorModes.divide, reader()));
        }
        if (source == butxpowerofy) {
            writer(calc
                    .calculateBi(Calculator.BiOperatorModes.xpowerofy, reader()));
        }

        if (source == butSquare) {
            writer(calc.calculateMono(Calculator.MonoOperatorModes.square,
                    reader()));
        }

        if (source == butSquareRoot) {
            writer(calc.calculateMono(Calculator.MonoOperatorModes.squareRoot,
                    reader()));
        }

        if (source == butOneDividedBy) {
            writer(calc.calculateMono(
                    Calculator.MonoOperatorModes.oneDividedBy, reader()));
        }

        if (source == butCos) {
            writer(calc.calculateMono(Calculator.MonoOperatorModes.cos,
                    reader()));
        }

        if (source == butSin) {
            writer(calc.calculateMono(Calculator.MonoOperatorModes.sin,
                    reader()));
        }

        if (source == butTan) {
            writer(calc.calculateMono(Calculator.MonoOperatorModes.tan,
                    reader()));
        }
        if (source == butlog) {
            writer(calc.calculateMono(Calculator.MonoOperatorModes.log,
                    reader()));
        }
        if (source == butrate) {
            writer(calc.calculateMono(Calculator.MonoOperatorModes.rate,
                    reader()));
        }
        if (source == butabs) {
            writer(calc.calculateMono(Calculator.MonoOperatorModes.abs, reader()));
        }

        if (source == butEqual) {
            writer(calc.calculateEqual(reader()));
        }

        if (source == butCancel) {
            writer(calc.reset());
        }


        // if the MEM button is pressed, store/display the value
        if (source == butMem) {
            memClicked = true;
            memCleared = false;
            writer(calc.calculateMono(Calculator.MonoOperatorModes.mem, reader()));
        }

        // if the MC button is pressed, clear the stored memory value
        if (source == butMemClr) {
            memCleared = true;
            memClicked = false;
            writer(calc.calculateMono(Calculator.MonoOperatorModes.memClr, reader()));
        }

        // if the MR button is pressed, display the stored memory value
        if (source == butMemRcl) {
            writer(calc.calculateMono(Calculator.MonoOperatorModes.memRcl, reader()));
        }

        // if the BKSP button is pressed, backspace a character in the field
        if (source == butBksp) {
            bkspClick = true;
            writer(calc.calculateMono(Calculator.MonoOperatorModes.bksp, reader()));
        }

        if (source == butClear){
            text.setText("");
        }

        text.selectAll();
    }

    public Double reader() {
        Double num;
        String str;
        str = text.getText();
        
        // new code - remove all whitespace from text field
        for(int i = 0; i < str.length(); i++) {
            if(str.contains(" ")){
                str = str.replaceAll("\\s", "");
            }
        }
        
        // new code - check if the value is "" or empty or just a decimal point (no numbers) and if so, set num = 0.0
        if (str.equals("") || str.isEmpty() || str.equals(".")) {
            num = 0.0;
            return num;
        }
        
        // new code - checks for supported input only
        //            supported input is: [0,1,2,3,4,5,6,7,8,9,/,*,-,+,=, and %] right now and can be updated further
        if (!str.matches("^[0123456789/*-+=^%. ]*$")) {
            num = 0.0;
            return num;
        }

        num = Double.valueOf(str);

        return num;
    }

    public void writer(final Double num) {
        // if BKSP clicked, remove the last character and handle when null
        if (bkspClick == true) {
            String newText = text.getText();
            if (!newText.equals("")) {
                text.setText(newText.substring(0, newText.length() - 1));
            } else {
                text.setText("");
            }

        }  else if (Double.isNaN(num)) {
            text.setText("");
        } else {
            text.setText(Double.toString(num));

            if (memClicked == true) {
                update.setText("Memory Value Stored = " + text.getText());
            }
            if (memCleared == true) {
                update.setText("Memory Value Cleared");
            }
        }
        bkspClick = false;
    }
}

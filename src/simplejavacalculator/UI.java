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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class UI implements ActionListener {

    private final JFrame frame;
    private final JPanel panel;
    private final JTextArea text, update;
    private final JButton but[], butDecimal, butAdd, butMinus, butMultiply, butDivide,
            butEqual, butCancel, butSquareRoot, butSquare, butOneDividedBy,
            butCos, butSin, butTan, butxpowerofy, butlog, butrate, butabs,
            butMem, butMemClr, butMemRcl, butBksp;
    private boolean memClicked, memCleared, bkspClick = false;
    private int height, width;
    private final Calculator calc;

    private final String[] buttonValue = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    public UI() {
        // frame = new JFrame("Calculator PH");             // original code
        frame = new JFrame("Open Source Calculator");       // new code - update the window title

        // frame.setResizable(false);                       // original code
        frame.setResizable(true);                           // new code - set window to be resizable

        // new code - update window icon
        // calculator.png file is located in src\simplejavacalculator 
        frame.setIconImage(new ImageIcon(getClass().getResource("calculatorImg.png")).getImage());

        panel = new JPanel(new FlowLayout());

        text = new JTextArea(2, 25);
        but = new JButton[10];
        for (int i = 0; i < 10; i++) {
            but[i] = new JButton(String.valueOf(i));
        }

        butDecimal = new JButton(".");
        butAdd = new JButton("+");
        butMinus = new JButton("-");
        butMultiply = new JButton("*");
        butDivide = new JButton("/");
        butEqual = new JButton("=");
        butSquareRoot = new JButton("√");
        butSquare = new JButton("x*x");
        butOneDividedBy = new JButton("1/x");
        butCos = new JButton("Cos");
        butSin = new JButton("Sin");
        butTan = new JButton("Tan");
        butxpowerofy = new JButton("x^y");
        butlog = new JButton("log10(x)");
        butrate = new JButton("x%");
        butabs = new JButton("abs(x)");

        butCancel = new JButton("C");

        // MEM button
        // This button will store the latest value when clicked. 
        butMem = new JButton("MEM");

        // MC button
        // This button will clear the stored memory value.
        butMemClr = new JButton("MC");

        // MR button
        // This button will display the stored memory value.
        butMemRcl = new JButton("MR");

        // BKSP button
        // This button will backspace the field values.
        butBksp = new JButton("BKSP");

        // Update field
        // This field will provide updates to the user on what the status of the memory value
        // The field is Read-Only
        update = new JTextArea(1, 25);
        update.setEditable(false);

        calc = new Calculator();
    }

    public void init() {
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(330, 300));
        width = frame.getWidth();
        height = frame.getHeight();
        // frame.setSize(330, 300);                                     // original code
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        // original code

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

        frame.add(panel);
        panel.add(text);

        for (int i = 1; i < 10; i++) {
            panel.add(but[i]);
            but[i].addActionListener(this);
        }
        panel.add(but[0]);
        but[0].addActionListener(this);

        panel.add(butDecimal);
        panel.add(butAdd);
        panel.add(butMinus);
        panel.add(butMultiply);
        panel.add(butDivide);
        panel.add(butSquare);
        panel.add(butSquareRoot);
        panel.add(butOneDividedBy);
        panel.add(butCos);
        panel.add(butSin);
        panel.add(butTan);
        panel.add(butxpowerofy);
        panel.add(butlog);
        panel.add(butrate);
        panel.add(butabs);

        panel.add(butEqual);
        panel.add(butCancel);

        // New code - Adding new buttons to the panel
        panel.add(butMem);
        panel.add(butMemClr);
        panel.add(butMemRcl);
        panel.add(butBksp);
        panel.add(update);

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

        for (int i = 0; i < 10; i++) {
            if (source == but[i]) {
                text.replaceSelection(buttonValue[i]);
                return;
            }
        }
        if (source == butDecimal) {
            // TODO - implement decimal to display on the calculator correctly / store in memory
            writer(calc.calculateMono(Calculator.MonoOperatorModes.decimal, reader()));
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
                    Calculator.MonoOperatorModes.oneDevidedBy, reader()));
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

        text.selectAll();
    }

    public Double reader() {
        Double num;
        String str;
        str = text.getText();

        // new code - check if the value is "" or empty and if so, set num = 0.0
        if (str.equals("") || str.isEmpty()) {
            num = 0.0;
            return num;
        }

        // new code - checks for supported input only
        //            supported input is: [0,1,2,3,4,5,6,7,8,9,/,*,-,+,=, and %] right now and can be updated further
        if (!str.matches("^[0123456789/*-+=^%]*$")) {
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

        } else if (Double.isNaN(num)) {
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

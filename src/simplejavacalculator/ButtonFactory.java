package simplejavacalculator;

import java.awt.*;
import javax.swing.*;

public class ButtonFactory {
    public static JButton getButton(String text, ButtonType buttonType) {
        final JButton button;

        switch (buttonType) {
            case PRIMARY:
                button = create();
                button.setBackground(new Color(105,105,105));
                button.setFont(new Font("Arial", Font.PLAIN, 26));
                button.setText(text);
                button.setBorder(null);
                return button;
            case SECONDARY:
                button = create();
                button.setBackground(Color.darkGray);
                button.setFont(new Font("Arial", Font.PLAIN, 16));
                button.setText(text);
                button.setBorder(null);
                return button;
            case OPERATION:
                button = create();
                button.setBackground(Color.darkGray);
                button.setFont(new Font("Arial", Font.PLAIN, 26));
                button.setText(text);
                button.setBorder(null);
                return button;
            default:
                return create();
        }

    }

    private static JButton create() {
        JButton button = new JButton();
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setForeground(new Color(245,245,245));
        button.setPreferredSize(new Dimension(40, 40));
        button.setBorder(null);

        return button;
    }
}
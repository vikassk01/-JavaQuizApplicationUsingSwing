package quizapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JavaQuizAppUsingSwing extends JFrame implements ActionListener {

    JLabel questionLabel, amountLabel;
    JButton[] options = new JButton[4];
    JButton lifeline;
    int qno = 0, amount = 0;

    String[][] questions = {
            {"Which keyword is used to define a class in Java?", "class", "define", "struct", "object", "1"},
            {"Which method is entry point of Java program?", "start()", "main()", "run()", "init()", "2"},
            {"Which is not a Java data type?", "int", "float", "boolean", "string", "4"},
            {"Which concept allows same method name?", "Inheritance", "Polymorphism", "Encapsulation", "Abstraction", "2"},
            {"Which keyword is used to inherit a class?", "implement", "extends", "inherit", "super", "2"}
    };

    public JavaQuizAppUsingSwing() {
        setTitle("KBC Quiz Game");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(20, 20, 60));

        amountLabel = new JLabel("Amount: ₹0");
        amountLabel.setBounds(500, 20, 200, 30);
        amountLabel.setForeground(Color.YELLOW);
        amountLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(amountLabel);

        questionLabel = new JLabel();
        questionLabel.setBounds(50, 80, 600, 60);
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(questionLabel);

        int y = 180;
        for (int i = 0; i < 4; i++) {
            options[i] = new JButton();
            options[i].setBounds(100, y, 500, 40);
            options[i].setBackground(new Color(0, 102, 204));
            options[i].setForeground(Color.WHITE);
            options[i].setFont(new Font("Arial", Font.BOLD, 14));
            options[i].setFocusPainted(false);
            options[i].addActionListener(this);
            add(options[i]);
            y += 60;
        }
        
        lifeline = new JButton("50-50 Lifeline");
        lifeline.setBounds(250, 420, 200, 30);
        lifeline.setBackground(Color.ORANGE);
        lifeline.setFont(new Font("Arial", Font.BOLD, 12));
        lifeline.addActionListener(this);
        add(lifeline);
        loadQuestion();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    void loadQuestion() {
        if (qno == questions.length) {
            JOptionPane.showMessageDialog(this, "🎉 You won ₹" + amount);
            System.exit(0);
        }

        questionLabel.setText("Q" + (qno + 1) + ": " + questions[qno][0]);

        for (int i = 0; i < 4; i++) {
            options[i].setText(questions[qno][i + 1]);
            options[i].setEnabled(true);
        }
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == lifeline) {
            lifeline.setEnabled(false);
            options[2].setEnabled(false);
            options[3].setEnabled(false);
            return;
        }

        int selected = -1;

        for (int i = 0; i < 4; i++) {
            if (e.getSource() == options[i]) {
                selected = i + 1;
            }
        }
        String correct = questions[qno][5];
        if (String.valueOf(selected).equals(correct)) {
            amount += 1000 * (qno + 1);
            amountLabel.setText("Amount: ₹" + amount);
            JOptionPane.showMessageDialog(this, "✅ Correct!");
            qno++;
            loadQuestion();
        } else {
            JOptionPane.showMessageDialog(this, "❌ Wrong! You won ₹" + amount);
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new JavaQuizAppUsingSwing();
    }
}
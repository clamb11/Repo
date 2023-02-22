import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener{

    JFrame frame;
    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[12];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton;
    JButton sndButton, sqrtButton, negButton, offButton;
    JPanel panel;
    Font myFont = new Font("Courier New", Font.BOLD, 30);

    Font smallMyFont = new Font("Courier New", Font.BOLD, 23);
    double num1 = 0;
    double num2 = 0;
    double result = 0;
    char operator;
    Calculator(){
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450,550);
        frame.setLayout(null);
        textField = new JTextField();
        textField.setBounds(15,5,350, 70);
        textField.setFont(myFont);
        textField.setForeground(Color.white);
        textField.setEditable(false);
        textField.setBackground(Color.PINK);
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("<html>D<br>E<br>L<br>E<br>T<br>E</html>");
        clrButton = new JButton("<html>C<br>L<br>E<br>A<br>R<br> <br>A<br>L<br>L</html>");
        sndButton = new JButton("^x");
        sqrtButton = new JButton("x√");
        negButton = new JButton("(-)");
        offButton = new JButton("off");
        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = sndButton;
        functionButtons[9] = sqrtButton;
        functionButtons[10] = negButton;
        functionButtons[11] = offButton;

        for(int i = 0; i < 12; i++){
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setForeground(Color.WHITE);
            functionButtons[i].setFocusable(false);
            functionButtons[i].setBackground(Color.PINK);
        }
        for(int i = 0; i < 10; i++){
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setForeground(Color.WHITE);
            numberButtons[i].setFocusable(false);
            numberButtons[i].setBackground(Color.pink);
        }
        
        delButton.setFont(smallMyFont);
        delButton.setForeground(Color.white);
        clrButton.setFont(smallMyFont);
        clrButton.setForeground(Color.white);
        delButton.setBounds(370,5,60,250);
        clrButton.setBounds(370,260,60,250);
        negButton.setFont(smallMyFont);
        offButton.setFont(smallMyFont);
        panel = new JPanel();
        panel.setBounds(15, 80, 350, 430);
        panel.setLayout(new GridLayout(5,4, 10, 10));
        panel.add(sndButton);
        panel.add(sqrtButton);
        panel.add(negButton);
        panel.add(divButton);
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(numberButtons[0]);
        panel.add(decButton);
        panel.add(equButton);
        panel.add(offButton);

        frame.add(panel);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textField);
        frame.setVisible(true);

    }

    public static void main(String[] args){
        Calculator calc = new Calculator();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < 10; i++){
            if(e.getSource() == numberButtons[i]){
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }
        if(e.getSource() == decButton) {
            textField.setText(textField.getText().concat("."));
        }
        if(e.getSource() == sndButton){
            num1 = Double.parseDouble(textField.getText());
            operator = '^';
            textField.setText("");
        }
        if(e.getSource() == sqrtButton){
            num1 = Double.parseDouble(textField.getText());
            operator = '√';
            textField.setText("");

        }
        if(e.getSource() == negButton){
            double temp = Double.parseDouble(textField.getText());
            temp*= -1;
            textField.setText(String.valueOf(temp));
        }
        if(e.getSource() == offButton){
            System.exit(0);

        }
        if(e.getSource() == addButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        }
        if(e.getSource() == subButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }
        if(e.getSource() == mulButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
        }
        if(e.getSource() == divButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }
        if(e.getSource() == clrButton) {
            textField.setText("");
        }
        if(e.getSource() == delButton){
            String toBeRemoved = textField.getText();
            textField.setText("");
            for(int i = 0; i < toBeRemoved.length() -1; i++){
                textField.setText(textField.getText() + toBeRemoved.charAt(i));
            }
        }
        if(e.getSource() == equButton) {

            switch (operator) {
                case '+':
                    num2 = Double.parseDouble(textField.getText());
                    result = num1 + num2;
                    break;
                case '-':
                    num2 = Double.parseDouble(textField.getText());
                    result = num1 - num2;
                    break;
                case '*':
                    num2 = Double.parseDouble(textField.getText());
                    result = num1 * num2;
                    break;
                case '/':
                    num2 = Double.parseDouble(textField.getText());
                    result = num1 / num2;
                    break;
                case '^':
                    num2 = Double.parseDouble(textField.getText());
                    result = Math.pow(num1, num2);
                    break;
                case '√':
                    num2 = Double.parseDouble(textField.getText());
                    result = Math.pow(num2, 1/num1);


            }
            textField.setText(String.valueOf(result));
            num1 = result;
        }



            }
        }




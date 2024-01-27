package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.io.IOException;
import javax.imageio.ImageIO;

public class mini extends JFrame implements ActionListener {
    String pin;
    JButton button;
    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JPanel contentPane;

    mini(String pin){
        this.pin = pin;
        setTitle("Transaction History");
        setSize(400,600);
        setLocation(20,20);
        setLayout(null);

        try {
            // Load background image
            Image backgroundImage = ImageIO.read(getClass().getResource("/icons/pink.jpg"));
            contentPane = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    // Draw the background image
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            };
        } catch (IOException e) {
            e.printStackTrace();
            contentPane = new JPanel();
            contentPane.setBackground(new Color(255, 255, 255)); // Fallback background color
        }


        setContentPane(contentPane);
        contentPane.setLayout(null);

        label1 = new JLabel();
        label1.setBounds(20,140,400,200);
        contentPane.add(label1);

        label2 = new JLabel("Pragati Nagrgoje");
        label2.setFont(new Font("System", Font.BOLD,19));
        label2.setBounds(150,20,200,20);
        contentPane.add(label2);

        label3 = new JLabel();
        label3.setBounds(20,80,300,20);
        contentPane.add(label3);

        label4 = new JLabel();
        label4.setBounds(20,400,300,20);
        contentPane.add(label4);

        try{
            Con c = new Con();
            ResultSet resultSet = c.statement.executeQuery("select * from login where pin = '"+pin+"'");
            while (resultSet.next()){
                label3.setText("Card Number:  "+ resultSet.getString("card_number").substring(0,4) + "XXXXXXXX"+ resultSet.getString("card_number").substring(12));
            }
        } catch (Exception e ){
            e.printStackTrace();
        }

        try{
            int balance =0;
            Con c = new Con();
            ResultSet resultSet = c.statement.executeQuery("select * from bank where pin = '"+pin+"'");
            while (resultSet.next()){

                label1.setText(label1.getText() + "<html>"+resultSet.getString("date")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+resultSet.getString("type")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+resultSet.getString("amount")+ "<br><br><html>");

                if (resultSet.getString("type").equals("Deposit")){
                    balance += Integer.parseInt(resultSet.getString("amount"));
                }else {
                    balance -= Integer.parseInt(resultSet.getString("amount"));
                }
            }
            label4.setText("Your Total Balance is Rs "+balance);
        } catch (Exception e){
            e.printStackTrace();
        }

        button = new JButton("Exit");
        button.setBounds(20,500,100,25);
        button.addActionListener(this);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        contentPane.add(button);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
    }

    public static void main(String[] args) {
        new mini("");
    }
}

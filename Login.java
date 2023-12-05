
// package bank;
// import bank.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

public class Login extends JFrame implements ActionListener {
    public JButton clear, btbL, signUp;
    JTextField txtCard;
    JPasswordField txtPass;

    public Login() {
        ImageIcon i = new ImageIcon(ClassLoader.getSystemResource("icons/img1.png"));
        Image im = i.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(im);
        JLabel lblic = new JLabel(i3, JLabel.LEFT);
        lblic.setBounds(60, 60, 60, 60);
        JLabel lblh, lblcard, lblpass;
        lblh = new JLabel("Welcome to ATM");
        lblh.setBounds(170, 70, 400, 60);
        lblh.setFont(new Font("Time new Roman", Font.BOLD, 30));

        lblcard = new JLabel("Card Number");
        lblcard.setBounds(90, 240, 150, 30);
        lblcard.setFont(new Font("Time new Roman", Font.BOLD, 20));

        lblpass = new JLabel("Password");
        lblpass.setBounds(90, 320, 150, 30);
        lblpass.setFont(new Font("Time new Roman", Font.BOLD, 20));

        txtCard = new JTextField();
        txtCard.setBounds(250, 240, 200, 30);
        txtCard.setFont(new Font("Time new Roman", Font.BOLD, 20));
        txtPass = new JPasswordField();
        txtPass.setBounds(250, 320, 200, 30);
        txtPass.setFont(new Font("Time new Roman", Font.BOLD, 20));

        // JButton clear, btbL, signUp;
        clear = new JButton("Clear");
        clear.setBounds(140, 400, 120, 30);
        clear.setFont(new Font("Time new Roman", Font.BOLD, 20));
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(this);

        btbL = new JButton("login");
        btbL.setBounds(300, 400, 120, 30);
        btbL.setFont(new Font("Time new Roman", Font.BOLD, 20));
        btbL.setBackground(Color.BLACK);
        btbL.setForeground(Color.WHITE);
        btbL.addActionListener(this);

        signUp = new JButton("Sign Up");
        signUp.setBounds(140, 480, 280, 30);
        signUp.setFont(new Font("Time new Roman", Font.BOLD, 20));
        signUp.setBackground(Color.BLACK);
        signUp.setForeground(Color.WHITE);
        signUp.addActionListener(this);

        add(lblh);
        add(lblic);
        add(lblcard);
        add(lblpass);
        add(txtCard);
        add(txtPass);
        add(clear);
        add(btbL);
        add(signUp);

        setTitle("ATM");
        setLayout(null);
        setSize(600, 800);
        setLocation(350, 200);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent a) {
        if (a.getSource() == clear) {
            txtCard.setText("");
            txtPass.setText("");
        } else if (a.getSource() == signUp) {
            setVisible(false);
            new Sign1().setVisible(true);
        } else if (a.getSource() == btbL) {
            String cardNum = txtCard.getText();
            String pinNum = txtPass.getText();
            try {
                String querry = "select * from login where cardno = '" + cardNum + "' and pin ='" + pinNum + "' ";
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost/bankmanagement", "root",
                        "Anant@2004");
                PreparedStatement smt = c.prepareStatement(querry);
                ResultSet rs = smt.executeQuery();
                if (rs.next()) {
                    setVisible(false);
                    new ATM(pinNum).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Card or Pin");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        new Login();
    }
}
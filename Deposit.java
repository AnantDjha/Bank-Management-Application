import javax.swing.*;
import javax.swing.border.*;
// import java.util.*;
import java.awt.*;
import java.time.*;
import java.awt.event.*;
import java.sql.*;

public class Deposit extends JFrame {
    JButton back, dp;
    JTextField amount;
    String pinNum;

    Deposit(String pinNum) {
        this.pinNum = pinNum;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(700, 940, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        JLabel lbl = new JLabel(i3);
        lbl.setBounds(0, 0, 700, 900);
        add(lbl);
        back = new JButton("Back");
        back.setBounds(195, 520, 138, 30);
        back.setFont(new Font("System", Font.BOLD, 14));
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                setVisible(false);
                new Login().setVisible(true);
            }
        });
        lbl.add(back);

        JLabel l = new JLabel("Enter Your Amount");
        l.setBounds(180, 300, 200, 30);
        l.setFont(new Font("System", Font.BOLD, 16));
        l.setForeground(Color.WHITE);
        lbl.add(l);

        amount = new JTextField();
        amount.setBackground(Color.BLACK);
        amount.setForeground(Color.WHITE);
        amount.setFont(new Font("System", Font.BOLD, 20));
        amount.setBounds(180, 350, 160, 30);
        Border bd = BorderFactory.createLineBorder(Color.BLACK, 2);
        amount.setHorizontalAlignment(JTextField.CENTER);
        amount.setBorder(bd);
        lbl.add(amount);

        dp = new JButton("Deposit");
        dp.setBounds(195, 470, 138, 30);
        dp.setFont(new Font("System", Font.BOLD, 14));
        dp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                String amt = amount.getText();
                String type = "Deposit";
                LocalDate dt = LocalDate.now();
                try {
                    String str = "insert into transaction values('" + pinNum + "','" + dt + "','" + type + "','" + amt
                            + "')";
                    Connection c = DriverManager.getConnection("jdbc:mysql://localhost/bankmanagement", "root",
                            "Anant@2004");
                    PreparedStatement smt = c.prepareStatement(str);
                    smt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "The Deposit is succesfull");
                    setVisible(false);
                    new ATM(pinNum).setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        lbl.add(dp);

        setLayout(null);
        setLocation(600, 50);
        setSize(700, 900);
        setUndecorated(true);
        setVisible(true);
    }

    public static void main(String args[]) {
        new Deposit("");
    }

}

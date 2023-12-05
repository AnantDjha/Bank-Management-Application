import javax.swing.*;
import javax.swing.border.*;
// import java.util.*;
import java.awt.*;
import java.time.*;
import java.awt.event.*;
import java.sql.*;

public class Withdraw extends JFrame {
    JButton back, dp;
    JTextField amount;
    String pinNum;
    int qwer;

    Withdraw(String pinNum) {
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
                new ATM(pinNum).setVisible(true);

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

        dp = new JButton("Withdraw");
        dp.setBounds(195, 470, 138, 30);
        dp.setFont(new Font("System", Font.BOLD, 14));
        dp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                String amt = amount.getText();
                String type = "Withdraw";
                LocalDate dt = LocalDate.now();
                try {
                    String a1 = "Withdraw";
                    String b1 = "Deposit";
                    String q1 = "select amount from transaction where pin = '" + pinNum + "' and type = '" + a1 + "'";
                    String q2 = "select amount from transaction where pin = '" + pinNum + "' and type = '" + b1 + "'";

                    Connection c = DriverManager.getConnection("jdbc:mysql://localhost/bankmanagement", "root",
                            "Anant@2004");
                    PreparedStatement smt1 = c.prepareStatement(q1);
                    PreparedStatement smt2 = c.prepareStatement(q2);

                    ResultSet r1 = smt1.executeQuery();
                    ResultSet r2 = smt2.executeQuery();
                    int with = 0;
                    int dep = 0;
                    while (r1.next()) {
                        with = with + Integer.parseInt(r1.getString("amount"));
                    }
                    while (r2.next()) {
                        dep = dep + Integer.parseInt(r2.getString("amount"));
                    }
                    qwer = dep - with;

                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (Integer.parseInt(amt) <= qwer) {
                    try {
                        String str = "insert into transaction values('" + pinNum + "','" + dt + "','" + type + "','"
                                + amt
                                + "')";
                        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/bankmanagement", "root",
                                "Anant@2004");
                        PreparedStatement smt = c.prepareStatement(str);
                        smt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "The Withdraw is succesfull");
                        setVisible(false);
                        new ATM(pinNum).setVisible(true);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Not Enough Balanace");
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
        new Withdraw("");
    }

}

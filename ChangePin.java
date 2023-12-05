import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ChangePin extends JFrame {
    String pinNum;

    ChangePin(String pinNum) {
        this.pinNum = pinNum;
        JTextField prev, curr;
        JButton back, change;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(700, 940, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        JLabel lbl = new JLabel(i3);
        lbl.setBounds(0, 0, 700, 900);
        add(lbl);

        JLabel a = new JLabel("Current PIN");
        JLabel b = new JLabel("New PIN");

        a.setBounds(180, 320, 160, 30);
        a.setFont(new Font("System", Font.BOLD, 20));
        a.setForeground(Color.WHITE);
        b.setForeground(Color.WHITE);

        lbl.add(a);
        b.setBounds(180, 420, 160, 30);
        b.setFont(new Font("System", Font.BOLD, 20));
        lbl.add(b);

        prev = new JTextField();
        prev.setBackground(Color.WHITE);
        prev.setForeground(Color.BLACK);
        prev.setFont(new Font("System", Font.BOLD, 20));
        prev.setBounds(180, 350, 160, 30);
        Border bd = BorderFactory.createLineBorder(Color.BLACK, 2);
        prev.setHorizontalAlignment(JTextField.CENTER);
        prev.setBorder(bd);
        lbl.add(prev);
        curr = new JTextField();
        curr.setBackground(Color.WHITE);
        curr.setForeground(Color.BLACK);
        curr.setFont(new Font("System", Font.BOLD, 20));
        curr.setBounds(180, 450, 160, 30);
        curr.setHorizontalAlignment(JTextField.CENTER);
        prev.setBorder(bd);
        lbl.add(curr);

        back = new JButton("Back");
        back.setBounds(175, 500, 80, 30);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                new ATM(pinNum);
            }
        });
        lbl.add(back);

        change = new JButton("Change");
        change.setBounds(265, 500, 80, 30);
        change.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                // String prevPin = prev.getText();
                // System.out.println(prevPin);
                String currPin = curr.getText();
                String qr1 = "update transaction set pin = '" + currPin + "' where pin = '" + pinNum + "'";
                String qr2 = "update sign3 set pin = '" + currPin + "' where pin = '" + pinNum + "'";
                String qr3 = "update login set pin = '" + currPin + "' where pin = '" + pinNum + "'";

                // if (!prevPin.equals(pinNum)) {
                // JOptionPane.showMessageDialog(null, "Current pin is not Valid");
                // return;
                // } else {
                try {
                    Connection c = DriverManager.getConnection("jdbc:mysql://localhost/bankmanagement", "root",
                            "Anant@2004");
                    PreparedStatement smt1 = c.prepareStatement(qr1);
                    PreparedStatement smt2 = c.prepareStatement(qr2);
                    PreparedStatement smt3 = c.prepareStatement(qr3);
                    smt1.executeUpdate();
                    smt2.executeUpdate();
                    smt3.executeUpdate();
                    JOptionPane.showMessageDialog(lbl, "Do you realy want to change the pin");
                    setVisible(false);
                    new ATM(curr.getText()).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // }

            }
        });
        lbl.add(change);

        setLayout(null);
        setSize(700, 940);
        setLocation(500, 10);
        setVisible(true);

    }

    public static void main(String args[]) {
        new ChangePin("");
    }
}

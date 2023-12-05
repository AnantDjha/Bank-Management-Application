import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class Balance extends JFrame {
    JButton check, back;
    String pinNum;
    String str;

    Balance(String pinNum) {
        this.pinNum = pinNum;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(700, 940, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        JLabel lbl = new JLabel(i3);
        lbl.setBounds(0, 0, 700, 940);
        add(lbl);

        JLabel l = new JLabel("Account Balance");
        l.setBounds(180, 330, 160, 30);
        l.setFont(new Font("System", Font.BOLD, 20));
        l.setForeground(Color.WHITE);
        lbl.add(l);
        JLabel ll = new JLabel("*******");
        ll.setBounds(180, 370, 160, 30);
        ll.setFont(new Font("System", Font.BOLD, 16));
        ll.setHorizontalAlignment(JLabel.CENTER);
        ll.setForeground(Color.WHITE);

        lbl.add(ll);

        back = new JButton("Back");
        back.setBounds(210, 500, 100, 30);
        back.setFont(new Font("System", Font.BOLD, 16));
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                new ATM(pinNum);
            }
        });
        lbl.add(back);

        check = new JButton("Check");
        check.setBounds(210, 450, 100, 30);
        check.setFont(new Font("System", Font.BOLD, 16));
        lbl.add(check);
        check.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    String a = "Withdraw";
                    String b = "Deposit";
                    String q1 = "select amount from transaction where pin = '" + pinNum + "' and type = '" + a + "'";
                    String q2 = "select amount from transaction where pin = '" + pinNum + "' and type = '" + b + "'";

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
                    str = "" + (dep - with);
                    if (ll.getText() == "*******") {
                        ll.setText("Rs: " + str);
                        check.setText("Hide");
                        return;
                    }
                    if (ll.getText() != "*******") {
                        ll.setText("*******");
                        check.setText("Check");

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        setLocation(400, 10);
        setSize(700, 940);
        setLayout(null);
        setVisible(true);
    }

    public static void main(String args[]) {
        new Balance("");
    }
}

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Ministatement extends JFrame {
    Ministatement(String pinNum) {
        JLabel card = new JLabel("card");
        card.setBounds(50, 50, 300, 30);
        add(card);

        JLabel mainn = new JLabel();

        add(mainn);

        try {
            String temp = "";
            String q1 = "select * from login where pin = '" + pinNum + "'";
            String q2 = "select * from transaction where pin = '" + pinNum + "'";
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost/bankmanagement", "root", "Anant@2004");
            PreparedStatement smt1 = c.prepareStatement(q1);
            PreparedStatement smt2 = c.prepareStatement(q2);
            ResultSet rs1 = smt1.executeQuery();
            ResultSet rs2 = smt2.executeQuery();
            while (rs1.next()) {
                temp = rs1.getString("cardno");
            }
            card.setText("Card Number:  " + temp.substring(0, 4) + "xxxxxxxx" + temp.substring(11));
            while (rs2.next()) {
                mainn.setText(
                        mainn.getText() + "<html>" + rs2.getString("DateAndTime")
                                + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                                + rs2.getString("type")
                                + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + "Rs " + rs2.getString("amount")
                                + "<br/>");
            }
            mainn.setBounds(50, 100, 400, 200);

        } catch (Exception e) {
            e.printStackTrace();
        }
        setSize(500, 600);
        setLayout(null);
        setVisible(true);
    }

    public static void main(String args[]) {
        new Ministatement("pinNum");
    }

}
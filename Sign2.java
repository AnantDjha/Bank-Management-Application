
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

public class Sign2 extends JFrame {
    public JLabel lbl, religion, catagory, income, qualify, occu, pan, adhar, senior, Exsist;
    public JComboBox<String> treligion, tcategory, tincome, tqualify;
    public JTextField toccu, tpan, tadhar;
    public JRadioButton cyes, cno, eyes, eno;
    JButton next;
    String formno;

    Sign2(String form) {
        this.formno = form;
        lbl = new JLabel("Page 2:Additional Information");
        lbl.setFont(new Font("Time New Roman", Font.BOLD, 22));
        lbl.setBounds(130, 30, 300, 30);
        add(lbl);

        religion = new JLabel("Religion:");
        religion.setFont(new Font("Time New Roman", Font.BOLD, 20));
        religion.setBounds(60, 90, 150, 30);
        add(religion);
        String relegionArr[] = { "", "Hindu", "islamic", "Christian" };
        treligion = new JComboBox<String>(relegionArr);
        treligion.setBounds(250, 90, 250, 30);
        treligion.setBackground(Color.WHITE);
        treligion.setFont(new Font("Time New Roman", Font.BOLD, 20));

        add(treligion);

        catagory = new JLabel("Catagory:");
        catagory.setFont(new Font("Time New Roman", Font.BOLD, 20));
        catagory.setBounds(60, 150, 150, 30);
        add(catagory);
        String categoryArr[] = { "", "General", "OBC", "Other" };
        tcategory = new JComboBox<String>(categoryArr);
        tcategory.setBounds(250, 150, 250, 30);
        tcategory.setBackground(Color.WHITE);
        tcategory.setFont(new Font("Time New Roman", Font.BOLD, 20));

        add(tcategory);

        income = new JLabel("Income:");
        income.setFont(new Font("Time New Roman", Font.BOLD, 20));
        income.setBounds(60, 210, 150, 30);
        add(income);

        String incomeArr[] = { "", "0-250000", "250000-500000", "500000-1000000", "1000000 & Above" };
        tincome = new JComboBox<String>(incomeArr);
        tincome.setBounds(250, 210, 250, 30);
        tincome.setBackground(Color.WHITE);
        tincome.setFont(new Font("Time New Roman", Font.BOLD, 20));

        add(tincome);

        qualify = new JLabel("Qualification:");
        qualify.setFont(new Font("Time New Roman", Font.BOLD, 20));
        qualify.setBounds(60, 270, 150, 30);
        add(qualify);

        String qualifyArr[] = { "", "10th", "12th", "Graduation", "Post Graduation" };
        tqualify = new JComboBox<String>(qualifyArr);
        tqualify.setBounds(250, 270, 250, 30);
        tqualify.setBackground(Color.WHITE);
        tqualify.setFont(new Font("Time New Roman", Font.BOLD, 20));

        add(tqualify);

        occu = new JLabel("Occupation:");
        occu.setFont(new Font("Time New Roman", Font.BOLD, 20));
        occu.setBounds(60, 330, 150, 30);
        add(occu);

        toccu = new JTextField();
        toccu.setBounds(250, 330, 250, 30);
        toccu.setBackground(Color.WHITE);
        toccu.setFont(new Font("Time New Roman", Font.BOLD, 20));

        add(toccu);

        pan = new JLabel("PAN:");
        pan.setFont(new Font("Time New Roman", Font.BOLD, 20));
        pan.setBounds(60, 390, 150, 30);
        add(pan);

        tpan = new JTextField();
        tpan.setBounds(250, 390, 250, 30);
        tpan.setBackground(Color.WHITE);
        tpan.setFont(new Font("Time New Roman", Font.BOLD, 20));

        add(tpan);

        adhar = new JLabel("Aadhar No:");
        adhar.setFont(new Font("Time New Roman", Font.BOLD, 20));
        adhar.setBounds(60, 450, 150, 30);
        add(adhar);

        tadhar = new JTextField();
        tadhar.setBounds(250, 450, 250, 30);
        tadhar.setBackground(Color.WHITE);
        tadhar.setFont(new Font("Time New Roman", Font.BOLD, 20));

        add(tadhar);

        ButtonGroup c = new ButtonGroup();

        ButtonGroup e = new ButtonGroup();

        senior = new JLabel("Senior Citizen?");
        senior.setFont(new Font("Time New Roman", Font.BOLD, 20));
        senior.setBounds(60, 510, 150, 30);
        add(senior);
        cyes = new JRadioButton("Yes");
        cyes.setBounds(250, 510, 100, 30);
        cyes.setFont(new Font("Time New Roman", Font.BOLD, 20));
        c.add(cyes);
        add(cyes);

        cno = new JRadioButton("No");
        cno.setBounds(350, 510, 100, 30);
        cno.setFont(new Font("Time New Roman", Font.BOLD, 20));
        c.add(cno);
        add(cno);

        Exsist = new JLabel("Existing Account");
        Exsist.setFont(new Font("Time New Roman", Font.BOLD, 20));
        Exsist.setBounds(60, 570, 170, 30);
        add(Exsist);

        eyes = new JRadioButton("Yes");
        eyes.setBounds(250, 570, 100, 30);
        eyes.setFont(new Font("Time New Roman", Font.BOLD, 20));
        e.add(eyes);
        add(eyes);

        eno = new JRadioButton("No");
        eno.setBounds(350, 570, 100, 30);
        eno.setFont(new Font("Time New Roman", Font.BOLD, 20));
        e.add(eno);
        add(eno);

        next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setBounds(440, 620, 60, 30);
        add(next);
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String streligion, stcatagory, stincome, stqualify, stoccu, stpan, stadhar;
                streligion = (String) treligion.getSelectedItem();
                stcatagory = (String) tcategory.getSelectedItem();
                stincome = (String) tincome.getSelectedItem();
                stqualify = (String) tqualify.getSelectedItem();
                stoccu = toccu.getText();
                stpan = tpan.getText();
                stadhar = tadhar.getText();

                String stSenior = null;
                if (cyes.isSelected()) {
                    stSenior = "yes";
                } else if (cno.isSelected()) {
                    stSenior = "No";
                }
                String stExist = null;
                if (eyes.isSelected()) {
                    stExist = "yes";
                } else if (eno.isSelected()) {
                    stExist = "No";
                }

                try {

                    String str = "insert into signup2 values('" + streligion + "','" + stcatagory + "','" + stincome
                            + "','" + stqualify
                            + "','" + stoccu + "','" + stpan + "','" + stadhar + "','" + stSenior + "','" + stExist
                            + "','" + formno + "')";

                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/bankmanagement", "root",
                            "Anant@2004");
                    PreparedStatement smt = conn.prepareStatement(str);
                    smt.executeUpdate();
                    setVisible(false);
                    new Sign3(formno).setVisible(true);
                } catch (Exception ev) {
                    ev.printStackTrace();
                }
            }
        });

        setLayout(null);
        setSize(600, 730);
        setVisible(true);
        setLocation(600, 250);
    }

    public static void main(String args[]) {
        new Sign2("");
    }
}

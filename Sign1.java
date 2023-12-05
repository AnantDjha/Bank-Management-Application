
import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
// import com.toedter.calendar.JDateChooser;
import java.sql.*;

public class Sign1 extends JFrame {
    JLabel lblCode, lbl, name, fname, email, phone, addr, city, state, pin, gender;
    JTextField tname, tfname, temail, tphone, tcity, tstate, tpin;
    JTextArea taddr;
    JRadioButton male, female;
    JButton next;
    Random r = new Random();
    int a = r.nextInt(1000, 10000);

    Sign1() {
        r = new Random();
        int a = r.nextInt(1000, 10000);

        lblCode = new JLabel("Appliction NO:" + a);
        lblCode.setBounds(450, 10, 150, 30);
        lblCode.setFont(new Font("Time New Roman", Font.BOLD, 12));
        add(lblCode);

        lbl = new JLabel("Page 1: Pesonal Details");
        lbl.setBounds(150, 30, 300, 30);
        lbl.setFont(new Font("Time New Roman", Font.BOLD, 22));
        add(lbl);

        name = new JLabel("Name:");
        name.setBounds(80, 90, 150, 30);
        name.setFont(new Font("Time New Roman", Font.BOLD, 16));
        add(name);

        tname = new JTextField();
        tname.setBounds(250, 90, 250, 30);
        add(tname);
        tname.setFont(new Font("raleway", Font.BOLD, 16));

        fname = new JLabel("Father's Name:");
        fname.setBounds(80, 140, 150, 30);
        fname.setFont(new Font("Time New Roman", Font.BOLD, 16));
        add(fname);
        tfname = new JTextField();
        tfname.setBounds(250, 140, 250, 30);
        tfname.setFont(new Font("raleway", Font.BOLD, 16));
        add(tfname);

        email = new JLabel("Email:");
        email.setBounds(80, 190, 150, 20);
        email.setFont(new Font("Time New Roman", Font.BOLD, 16));
        add(email);
        temail = new JTextField();
        temail.setBounds(250, 190, 250, 30);
        temail.setFont(new Font("raleway", Font.BOLD, 16));
        add(temail);

        phone = new JLabel("Mobile No:");
        phone.setBounds(80, 240, 150, 20);
        phone.setFont(new Font("Time New Roman", Font.BOLD, 16));
        add(phone);
        tphone = new JTextField();
        tphone.setBounds(250, 240, 250, 30);
        tphone.setFont(new Font("raleway", Font.BOLD, 16));
        add(tphone);

        addr = new JLabel("Address:");
        addr.setBounds(80, 290, 150, 20);
        addr.setFont(new Font("Time New Roman", Font.BOLD, 16));
        add(addr);
        taddr = new JTextArea(3, 20);
        taddr.setBounds(250, 290, 250, 60);
        taddr.setFont(new Font("raleway", Font.BOLD, 16));
        add(taddr);

        city = new JLabel("City:");
        city.setBounds(80, 360, 150, 20);
        city.setFont(new Font("Time New Roman", Font.BOLD, 16));
        add(city);
        tcity = new JTextField();
        tcity.setBounds(250, 360, 250, 30);
        tcity.setFont(new Font("raleway", Font.BOLD, 16));
        add(tcity);

        state = new JLabel("State:");
        state.setBounds(80, 410, 150, 20);
        state.setFont(new Font("Time New Roman", Font.BOLD, 16));
        add(state);
        tstate = new JTextField();
        tstate.setBounds(250, 410, 250, 30);
        tstate.setFont(new Font("raleway", Font.BOLD, 16));
        add(tstate);

        pin = new JLabel("Pin Code:");
        pin.setBounds(80, 460, 150, 20);
        pin.setFont(new Font("Time New Roman", Font.BOLD, 16));
        add(pin);
        tpin = new JTextField();
        tpin.setBounds(250, 460, 250, 30);
        tpin.setFont(new Font("raleway", Font.BOLD, 16));
        add(tpin);

        gender = new JLabel("Gender:");
        gender.setBounds(80, 510, 150, 20);
        gender.setFont(new Font("Time New Roman", Font.BOLD, 16));
        add(gender);
        male = new JRadioButton("Male");
        female = new JRadioButton("Female");
        ButtonGroup bg = new ButtonGroup();
        male.setBounds(250, 510, 100, 30);
        female.setBounds(350, 510, 100, 30);
        bg.add(male);
        bg.add(female);
        add(male);
        add(female);

        next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setBounds(440, 560, 60, 30);
        add(next);
        next.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String formno, sname, sfname, semail, sphone, saddr, scity, sstate, spin, sgender;
                formno = "" + a;
                sname = tname.getText();
                sfname = tfname.getText();
                semail = temail.getText();
                sphone = tphone.getText();
                saddr = taddr.getText();
                scity = tcity.getText();
                sstate = tstate.getText();
                spin = tpin.getText();

                sgender = null;
                if (male.isSelected()) {
                    sgender = "male";
                } else if (female.isSelected()) {
                    sgender = "Female";
                }
                String str = "insert into signup values('" + formno + "','" + sname + "','" + sfname + "','" + semail
                        + "','" + sphone + "','" + saddr + "','" + scity + "','" + sstate + "','" + sgender + "','"
                        + spin
                        + "')";
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bankmanagement", "root",
                            "Anant@2004");
                    PreparedStatement s = con.prepareStatement(str);
                    s.executeUpdate();
                    setVisible(false);
                    new Sign2(formno).setVisible(true);
                } catch (Exception em) {
                    System.out.println(em);
                    ;
                }

            }
        });

        setSize(600, 650);
        setLayout(null);
        setVisible(true);

    }

    public static void main(String args[]) {
        new Sign1();
    }
}

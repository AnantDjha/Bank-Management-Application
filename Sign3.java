import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

public class Sign3 extends JFrame implements ActionListener {

    JRadioButton saving, current, fd, rd;
    JCheckBox atm, netBank, mBank, eStat, sms, cheque, last;
    JButton Sumbit, Cancel;
    String formno;

    Sign3(String fn) {
        formno = fn;
        JLabel lbl = new JLabel("Page 3: Accounts Details");
        lbl.setFont(new Font("Time New Roman", Font.BOLD, 22));
        lbl.setBounds(130, 30, 300, 30);
        add(lbl);

        JLabel account = new JLabel("Account Type");
        account.setBounds(60, 90, 150, 30);
        account.setFont(new Font("raleway", Font.BOLD, 18));
        add(account);

        saving = new JRadioButton("Saving Account");
        saving.setBounds(80, 140, 160, 30);
        saving.setFont(new Font("raleway", Font.BOLD, 15));
        add(saving);
        current = new JRadioButton("Current Account");
        current.setBounds(260, 140, 160, 30);
        current.setFont(new Font("raleway", Font.BOLD, 15));
        add(current);
        fd = new JRadioButton("Fixed Deposite");
        fd.setBounds(80, 190, 160, 30);
        fd.setFont(new Font("raleway", Font.BOLD, 15));
        add(fd);
        rd = new JRadioButton("Reccuring Deposite");
        rd.setBounds(260, 190, 160, 30);
        rd.setFont(new Font("raleway", Font.BOLD, 15));
        add(rd);

        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(saving);
        bg1.add(current);
        bg1.add(fd);
        bg1.add(rd);

        JLabel cardNo = new JLabel("Card Number:");
        cardNo.setBounds(60, 250, 150, 30);
        cardNo.setFont(new Font("raleway", Font.BOLD, 18));
        add(cardNo);

        JLabel card = new JLabel("XXXX-XXXX-XXXX-3463");
        card.setBounds(250, 250, 250, 30);
        card.setFont(new Font("raleway", Font.BOLD, 18));
        add(card);

        JLabel punNo = new JLabel("PIN:");
        punNo.setBounds(60, 300, 150, 30);
        punNo.setFont(new Font("raleway", Font.BOLD, 18));
        add(punNo);

        JLabel pun = new JLabel("XXXX");
        pun.setBounds(250, 300, 250, 30);
        pun.setFont(new Font("raleway", Font.BOLD, 18));
        add(pun);

        JLabel service = new JLabel("Service Required");
        service.setBounds(60, 350, 200, 30);
        service.setFont(new Font("raleway", Font.BOLD, 18));
        add(service);

        atm = new JCheckBox("ATM Card");
        atm.setBounds(80, 390, 160, 30);
        atm.setFont(new Font("raleway", Font.BOLD, 15));
        add(atm);

        netBank = new JCheckBox("Internet Banking");
        netBank.setBounds(260, 390, 160, 30);
        netBank.setFont(new Font("raleway", Font.BOLD, 15));
        add(netBank);

        mBank = new JCheckBox("Mobile Banking");
        mBank.setBounds(80, 440, 160, 30);
        mBank.setFont(new Font("raleway", Font.BOLD, 15));
        add(mBank);

        eStat = new JCheckBox("E-Statement");
        eStat.setBounds(260, 440, 160, 30);
        eStat.setFont(new Font("raleway", Font.BOLD, 15));
        add(eStat);

        cheque = new JCheckBox("Cheque Book");
        cheque.setBounds(80, 490, 160, 30);
        cheque.setFont(new Font("raleway", Font.BOLD, 15));
        add(cheque);

        sms = new JCheckBox("Email and SMS Alert");
        sms.setBounds(260, 490, 170, 30);
        sms.setFont(new Font("raleway", Font.BOLD, 15));
        add(sms);

        last = new JCheckBox("I herebr declare that the above mentioned details are correct");
        last.setBounds(80, 570, 500, 30);
        last.setFont(new Font("raleway", Font.BOLD, 12));
        add(last);

        Sumbit = new JButton("Submit");
        Sumbit.setBounds(300, 620, 100, 30);
        Sumbit.setBackground(Color.GRAY);
        Sumbit.setForeground(Color.BLACK);
        Sumbit.setEnabled(false);
        Sumbit.addActionListener(this);
        add(Sumbit);

        Cancel = new JButton("Cancel");
        Cancel.setBounds(180, 620, 100, 30);
        Cancel.setBackground(Color.BLACK);
        Cancel.setForeground(Color.WHITE);
        Cancel.addActionListener(this);
        add(Cancel);

        last.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (last.isSelected()) {
                    Sumbit.setBackground(Color.BLACK);
                    Sumbit.setForeground(Color.WHITE);
                    Sumbit.setEnabled(true);
                }
                if (!last.isSelected()) {
                    Sumbit.setBackground(Color.GRAY);
                    Sumbit.setForeground(Color.BLACK);
                    Sumbit.setEnabled(false);
                }
            }
        });

        setSize(600, 700);
        setLocation(600, 250);
        setLayout(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == Sumbit) {
            String accType = null;
            if (saving.isSelected()) {
                accType = "Saving Account";
            } else if (fd.isSelected()) {
                accType = "Fixed Deposit Acoount";
            } else if (rd.isSelected()) {
                accType = "Reccuring Deposit Account";
            } else if (current.isSelected()) {
                accType = "Current Account";
            }
            String serviceStr = "";
            if (atm.isSelected()) {
                serviceStr = serviceStr + "ATM Card";
            }
            if (netBank.isSelected()) {
                serviceStr = serviceStr + ",Internet Banking";
            }
            if (sms.isSelected()) {
                serviceStr = serviceStr + ",Email and SMS Alert";
            }
            if (eStat.isSelected()) {
                serviceStr = serviceStr + ",E-Statement";
            }
            if (mBank.isSelected()) {
                serviceStr = serviceStr + ",Mobile Banking";
            }
            if (cheque.isSelected()) {
                serviceStr = serviceStr + ",Cheque Book";
            }
            Random r = new Random();
            long cardNum = r.nextLong(1000000000000000L, 10000000000000000L);
            int pinNum = r.nextInt(1000, 10000);
            // System.out.println(cardNum);
            try {
                String str = "insert into sign3 values('" + formno + "','" + accType + "','" + serviceStr
                        + "','" + cardNum
                        + "','" + pinNum + "')";
                String str2 = "insert into login values('" + formno + "','" + cardNum + "','" + pinNum + "')";

                Connection c = DriverManager.getConnection("jdbc:mysql://localhost/bankmanagement", "root",
                        "Anant@2004");
                PreparedStatement smt = c.prepareStatement(str);
                PreparedStatement smt1 = c.prepareStatement(str2);
                smt.executeUpdate();
                smt1.executeUpdate();
                JOptionPane.showMessageDialog(this, "Card No :" + cardNum + "\nPIN:" + pinNum);
                setVisible(false);
                new Deposit("" + pinNum).setVisible(true);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (ae.getSource() == Cancel) {

        }
    }

    public static void main(String args[]) {
        new Sign3("");
    }
}

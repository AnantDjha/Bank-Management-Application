import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ATM extends JFrame {

    JButton deposite, withdraw, fast, stat, pinChange, balance, exit;
    String p;

    ATM(String p) {
        this.p = p;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(700, 940, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        JLabel lbl = new JLabel(i3);
        lbl.setBounds(0, 0, 700, 900);
        add(lbl);

        JLabel lbl1 = new JLabel("Please select your transaction");
        lbl1.setBounds(180, 300, 200, 30);
        lbl1.setFont(new Font("System", Font.BOLD, 12));
        lbl1.setForeground(Color.WHITE);
        lbl.add(lbl1);

        deposite = new JButton("Deposit");
        deposite.setBounds(120, 400, 140, 30);
        deposite.setFont(new Font("System", Font.BOLD, 14));
        deposite.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                new Deposit(p).setVisible(true);
                ;
            }
        });
        lbl.add(deposite);

        fast = new JButton("Fast Cash");
        fast.setBounds(120, 440, 140, 30);
        fast.setFont(new Font("System", Font.BOLD, 14));
        lbl.add(fast);

        pinChange = new JButton("Change Pin");
        pinChange.setBounds(120, 480, 140, 30);
        pinChange.setFont(new Font("System", Font.BOLD, 14));
        pinChange.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                setVisible(false);
                new ChangePin(p).setVisible(true);
            }
        });
        lbl.add(pinChange);

        withdraw = new JButton("Cash Withdrawl");
        withdraw.setBounds(265, 400, 138, 30);
        withdraw.setFont(new Font("System", Font.BOLD, 13));
        withdraw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                setVisible(false);
                new Withdraw(p).setVisible(true);
            }
        });
        lbl.add(withdraw);

        stat = new JButton("Mini Statement");
        stat.setBounds(265, 440, 138, 30);
        stat.setFont(new Font("System", Font.BOLD, 13));
        lbl.add(stat);

        balance = new JButton("Balance");
        balance.setBounds(265, 480, 138, 30);
        balance.setFont(new Font("System", Font.BOLD, 14));
        balance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                new Balance(p);
            }
        });
        lbl.add(balance);

        exit = new JButton("Exit");
        exit.setBounds(195, 520, 138, 30);
        exit.setFont(new Font("System", Font.BOLD, 14));
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                System.exit(0);
            }
        });
        lbl.add(exit);

        setLayout(null);
        setLocation(600, 50);
        setSize(700, 900);
        setUndecorated(true);
        setVisible(true);

    }

    public static void main(String args[]) {
        new ATM("");
    }
}

package electricity.billing.system;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;

public class DepositDetails extends JFrame implements ActionListener {

    Choice meternumber , cmonth;
    JTable table;
    JButton search,print;
    DepositDetails() {
        super("Depost Details");

        setSize(700,700);
        setLocation(400,100);


        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel lblmeternumber = new JLabel("Search by Meter Number");
        lblmeternumber.setBounds(20,20,150,20);
        add(lblmeternumber);


        meternumber = new Choice();
        meternumber.setBounds(180,20,150,20);
        add(meternumber);

        try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("select * from customer");
                while(rs.next()) {
                    meternumber.add(rs.getString("meter_no"));
                }
        }catch(Exception e){
            e.printStackTrace();
        }

        JLabel lblmonth = new JLabel("Search by Month");
        lblmonth.setBounds(400,20,100,20);
        add(lblmonth);


        cmonth = new Choice();
        cmonth.setBounds(520,20,150,20);
        cmonth.add("January");
        cmonth.add("Feburary");
        cmonth.add("March");
        cmonth.add("April");
        cmonth.add("May");
        cmonth.add("June");
        cmonth.add("July");
        cmonth.add("August");
        cmonth.add("September");
        cmonth.add("October");
        cmonth.add("November");
        cmonth.add("December");
        add(cmonth);


        table = new JTable();

        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from bill");

            table.setModel(DbUtils.resultSetToTableModel(rs));

        }catch(Exception e) {
            e.printStackTrace();
        }


        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0,100,700,600);
        add(sp);



        search = new JButton("Search");
        search.setBounds(20,70,80,20);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBounds(120,70,80,20);
        print.addActionListener(this);
        add(print);



        setVisible(true);

    }

    public static void main(String[] args) {

        new DepositDetails();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            String query = "select * from bill where meter_no = '"+meternumber.getSelectedItem()+"' and month = '"+cmonth.getSelectedItem()+"'";


            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            }catch(Exception ae) {

            }
        } else {
            try {
                    table.print();
            }catch(Exception ae) {
                ae.printStackTrace();
            }
        }
    }
}

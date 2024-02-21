import com.mysql.cj.xdevapi.Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminPanel extends JFrame implements ActionListener {
    public static JLabel welcomeMessage;
    public static JTextField tf;
    public static JButton btn;
    public static JTable j;

    AdminPanel() {
        setSize(900, 700);
        setLayout(null);
        setVisible(true);
        setLocationRelativeTo(null);

        welcomeMessage = new JLabel("Admin Panel");
        welcomeMessage.setBounds(100, 20, 300, 50);
        welcomeMessage.setFont(new Font("Arial", Font.BOLD, 35));
        add(welcomeMessage);

        tf = new JTextField();
        tf.setBounds(540, 40, 200, 20);
        add(tf);

        btn = new JButton("Done");
        btn.setBounds(750, 40, 100, 20);
        btn.addActionListener(this);
        add(btn);
        try {
            Connection con = DB.connect();
            String query = "select form_no, name, father_name, gender, dob, email from signup";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery(query);
            List<String[]> dataList = new ArrayList<>();

            while(rs.next()) {
                String formN = rs.getString(1);
                String name = rs.getString(2);
                String father_name = rs.getString(3);
                String gender = rs.getString(4);
                String dob = rs.getString(5);
                String email = rs.getString(6);

                dataList.add(new String[]{formN, name, father_name, gender, dob, email});

            }
            String[][] data = dataList.toArray(new String[0][]);

            // Column Names
            String[] columnNames = {"Form Number", "Name", "Father Name", "Gender", "DOB", "Email"};

            // Initializing the JTable
            JTable j = new JTable(data, columnNames);
            j.setBounds(10, 10, 200, 150); // Set bounds for JTable

            // Create JScrollPane with JTable
            JScrollPane sp = new JScrollPane(j);
            sp.setBounds(50, 80, 800, 500); // Set bounds for JScrollPane

            // Set the preferred size of the JViewport inside JScrollPane
            sp.getViewport().setPreferredSize(new Dimension(800, 500));

            add(sp); // Add JScrollPane to frame

            setLocationRelativeTo(null);
        }catch(Exception err) {
            err.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btn) {
            String formN = tf.getText();
            new UserDetails(formN);
        }
    }

    public static void main(String[] args) {
        new AdminPanel();
    }
}

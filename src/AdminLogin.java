import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminLogin extends JFrame implements ActionListener {
    public static JLabel adminIdLabel, adminPasswordLabel, welcomeMessage;
    public static JButton signUpButton, loginButton, clearButton;
    public static JTextField adminIdField;
    public static JPasswordField adminPasswordField;
    AdminLogin() {
        welcomeMessage = new JLabel("Admin Login");
        welcomeMessage.setBounds(200, 20, 300, 50);
        welcomeMessage.setFont(new Font("Arial", Font.BOLD, 35));
        add(welcomeMessage);

        adminIdLabel = new JLabel("Admin ID");
        adminIdLabel.setBounds(100, 115, 200, 20);
        adminIdLabel.setFont(new Font("Arial", Font.BOLD, 28));
        add(adminIdLabel);

        adminIdField = new JTextField();
        adminIdField.setBounds(300, 110, 300, 28);
        add(adminIdField);

        adminPasswordLabel = new JLabel("Password");
        adminPasswordLabel.setBounds(100, 175, 200, 20);
        adminPasswordLabel.setFont(new Font("Arial", Font.BOLD, 28));
        add(adminPasswordLabel);

        adminPasswordField = new JPasswordField();
        adminPasswordField.setBounds(300, 170, 300, 28);
        add(adminPasswordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(100, 240, 290, 35);
        loginButton.setFont(new Font("Arial", Font.BOLD, 15));
        loginButton.setBackground(Color.DARK_GRAY);
        loginButton.setForeground(Color.white);
        loginButton.addActionListener(this);
        add(loginButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(410, 240, 190, 35);
        clearButton.setFont(new Font("Arial", Font.BOLD, 15));
        clearButton.setBackground(Color.DARK_GRAY);
        clearButton.setForeground(Color.white);
        clearButton.addActionListener(this);
        add(clearButton);

        setSize(700, 500);
        setLayout(null);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String adminID = adminIdField.getText();
            String adminPassword = adminPasswordField.getText();


            //validation
           if (adminID.equalsIgnoreCase("") || adminPassword.equalsIgnoreCase("")) {
               JOptionPane.showMessageDialog(null, "Id or password wrong");
           }else {
               dispose();
               try {
                   Connection con =  DB.connect();
                   String q  = "select * from admin where id = '"+adminID+"' and password = '"+adminPassword+"'";
                   PreparedStatement preparedStatement = con.prepareStatement(q);
                   ResultSet rs = preparedStatement.executeQuery(q);

                   while (rs.next()) {
                       String adID = rs.getString(1);
                       String adPass = rs.getString(2);

                       if(adID.equalsIgnoreCase(adminID) && adPass.equalsIgnoreCase(adminPassword)) {
                           new AdminPanel();
                       }else {
                           JOptionPane.showMessageDialog(null, "Either id or password no wrong");
                       }
                   }
               }catch(Exception err) {
                   err.printStackTrace();
               }
           }
        }
        if (e.getSource() == clearButton) {
            adminIdField.setText("");
            adminPasswordField.setText("");
        }
        if (e.getSource() == signUpButton) {
            dispose();
            new SignupForm();
        }
    }

    public static void main(String[] args) {
        new AdminLogin();
    }
}

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDetails extends JFrame {
    String formNumber;
    public static JLabel accountDetails, accountDetailsValue, cardNDetails, cardNValue, amountDetails, amountValue;
    UserDetails(String formNumber) {
        this.formNumber = formNumber;
        setSize(500, 300);
        setLayout(null);
        setVisible(true);
        setLocationRelativeTo(null);

        try {
            Connection con = DB.connect();
            String query = "select account_type, card_no, amount from signup3";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery(query);


            while(rs.next()) {
                String accountType = rs.getString(1);
                String cardN = rs.getString(2);
                String amount = rs.getString(3);

                accountDetails = new JLabel("Account Type: ");
                accountDetails.setBounds(40, 20, 200, 25);
                accountDetails.setFont(new Font("Raleway", Font.BOLD, 22));
                add(accountDetails);

                accountDetailsValue = new JLabel();
                accountDetailsValue.setBounds(250, 20, 200, 25);
                accountDetailsValue.setFont(new Font("Raleway", Font.BOLD, 18));
                accountDetailsValue.setText(accountType);
                add(accountDetailsValue);

                cardNDetails = new JLabel("Card Number: ");
                cardNDetails.setBounds(40, 60, 200, 25);
                cardNDetails.setFont(new Font("Raleway", Font.BOLD, 22));
                add(cardNDetails);

                cardNValue = new JLabel();
                cardNValue.setBounds(250, 60, 200, 25);
                cardNValue.setFont(new Font("Raleway", Font.BOLD, 18));
                cardNValue.setText(cardN);
                add(cardNValue);

                amountDetails = new JLabel("Amount: ");
                amountDetails.setBounds(40, 100, 200, 25);
                amountDetails.setFont(new Font("Raleway", Font.BOLD, 22));
                add(amountDetails);

                amountValue = new JLabel();
                amountValue.setBounds(250, 100, 200, 25);
                amountValue.setFont(new Font("Raleway", Font.BOLD, 18));
                amountValue.setText(amount);
                add(amountValue);
            }
        }catch(Exception err) {
            err.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new UserDetails("");
    }
}

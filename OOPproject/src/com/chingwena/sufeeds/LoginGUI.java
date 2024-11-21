//Mukundi Witness Chingwena, ICS, 190004, 9/11/2024
package com.chingwena.sufeeds;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginGUI extends JFrame {
    private JLabel lblTitle, lblUsername, lblPassword, lblCopyright;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnSignUp;

    public LoginGUI() {
        setTitle("SU Feeds - Login");
        setSize(450, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        lblTitle = new JLabel("Welcome to SU Feeds", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setForeground(new Color(0xAD1427));

        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        panelForm.setBackground(new Color(0x16C4CA));
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelForm.add(lblUsername, gbc);

        txtUsername = new JTextField(15);
        txtUsername.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelForm.add(txtUsername, gbc);

        lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelForm.add(lblPassword, gbc);

        txtPassword = new JPasswordField(15);
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelForm.add(txtPassword, gbc);

        lblCopyright = new JLabel("©MUKUNDI WITNESS CHINGWENA - ICS 1A", JLabel.CENTER);
        lblCopyright.setFont(new Font("Arial", Font.PLAIN, 12));
        add(lblCopyright, BorderLayout.SOUTH);

        btnLogin = new JButton("Login");
        btnLogin.setBackground(new Color(0x9DA11E));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelForm.add(btnLogin, gbc);

        btnSignUp = new JButton("Sign Up");
        btnSignUp.setBackground(new Color(0x173ACC));
        btnSignUp.setForeground(Color.BLACK);
        btnSignUp.setFocusPainted(false);
        btnSignUp.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelForm.add(btnSignUp, gbc);

        add(lblTitle, BorderLayout.NORTH);
        add(panelForm, BorderLayout.CENTER);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentId = authenticateUser(txtUsername.getText(), new String(txtPassword.getPassword()));
                if (studentId != null) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    new ClassesGUI(studentId).setVisible(true); //pass studentid
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Credentials");
                }
            }
        });

        btnSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SignUpGUI().setVisible(true);
            }
        });
    }

    private String authenticateUser(String username, String password) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT student_id FROM tbl_students WHERE student_id = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("student_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
//It is the robotic hymn of Doom


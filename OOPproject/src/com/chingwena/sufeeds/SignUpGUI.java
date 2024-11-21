//Mukundi Witness Chingwena, ICS, 190004, 9/11/2024
package com.chingwena.sufeeds;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpGUI extends JFrame {
    private JLabel lblName, lblUsername, lblPassword, lblSchool, lblCourse, lblTitle, lblCopyright;
    private JTextField txtName, txtUsername;
    private JPasswordField txtPassword;
    private JComboBox<String> cmbSchool, cmbCourse;
    private JButton btnRegister;

    public SignUpGUI() {
        setTitle("SU Feeds - Sign Up");
        setSize(500, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        lblTitle = new JLabel("Register for SU Feeds", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(new Color(0xAD1427));
        add(lblTitle, BorderLayout.NORTH);

        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        panelForm.setBackground(new Color(0x16C4CA));
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        lblName = new JLabel("Student Name:");
        lblName.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelForm.add(lblName, gbc);

        txtName = new JTextField(15);
        txtName.setFont(new Font("Arial", Font.PLAIN, 15));
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelForm.add(txtName, gbc);

        lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelForm.add(lblUsername, gbc);

        txtUsername = new JTextField(15);
        txtUsername.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelForm.add(txtUsername, gbc);

        lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelForm.add(lblPassword, gbc);

        txtPassword = new JPasswordField(15);
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelForm.add(txtPassword, gbc);

        lblSchool = new JLabel("School:");
        lblSchool.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelForm.add(lblSchool, gbc);

        cmbSchool = new JComboBox<>(new String[]{
                "Strathmore Institute of Management and Technology (SI)",
                "Strathmore Institute of Mathematical Sciences (SIMS)",
                "School of Tourism and Hospitality (STH)",
                "School of Humanities and Social Sciences (SHSS)",
                "Strathmore Law School (SLS)",
                "Strathmore Business School (SBS)",
                "School of Computing and Engineering Sciences (SCES)",
                "Strathmore lnstitute of Management and Technology(SI)"
        });
        cmbSchool.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 3;
        panelForm.add(cmbSchool, gbc);

        lblCourse = new JLabel("Course:");
        lblCourse.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 4;
        panelForm.add(lblCourse, gbc);

        cmbCourse = new JComboBox<>(new String[]{
                "Bachelors in Informatics and Computer Science (BISC)",
                "Bachelor of Science in Tourism Management (BTM)",
                "Bachelor of Business Science in Financial Engineering (BBSFENG)",
                "Bachelor of Laws (LLB)",
                "Bachelor of Arts in International Studies",
                "Bachelor of Commerce (BCOM)",
                "Diploma in Business Information Technology (DBIT)",
                "Diploma in Business Management (DBM)",
                "Bachelor of Arts in Development Studies and Philosophy",
                "Bachelor of Arts in Communication"
        });
        cmbCourse.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 4;
        panelForm.add(cmbCourse, gbc);

        lblCopyright = new JLabel("©MUKUNDI WITNESS CHINGWENA - ICS 1A", JLabel.CENTER);
        lblCopyright.setFont(new Font("Arial", Font.BOLD, 12));
        add(lblCopyright, BorderLayout.SOUTH);

        btnRegister = new JButton("Register");
        btnRegister.setBackground(new Color(0x9DA11E));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegister.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelForm.add(btnRegister, gbc);

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser(txtName.getText(), txtUsername.getText(),
                        new String(txtPassword.getPassword()),
                        cmbSchool.getSelectedItem().toString(),
                        cmbCourse.getSelectedItem().toString());
            }
        });

        add(panelForm, BorderLayout.CENTER);

        getContentPane().setBackground(new Color(0x173ACC));
    }

    private void registerUser(String studentName, String username, String password, String school, String course) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO tbl_students (student_id, student_name, password, school, course) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, studentName);
            stmt.setString(3, password);
            stmt.setString(4, school);
            stmt.setString(5, course);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Sign-up successful!");
            dispose();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error during registration.");
        }
    }


}
//It is the robotic hymn of Doom


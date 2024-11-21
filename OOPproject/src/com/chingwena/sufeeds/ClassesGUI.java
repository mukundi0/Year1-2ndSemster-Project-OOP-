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
import java.sql.Statement;

public class ClassesGUI extends JFrame {
    private JLabel lblTitle, lblClassName, lblSemester,lblYear, lblCopyright;
    private JTextField txtClassName, txtSemester, txtYear;
    private JButton btnAddClass, btnViewFeedback;
    private String studentId;

    public ClassesGUI(String studentId) {
        this.studentId = studentId;

        setTitle("Manage Classes");
        setSize(450, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        lblTitle = new JLabel("Manage Your Classes", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setForeground(new Color(0xAD1427));

        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBackground(Color.WHITE);
        panelForm.setBackground(new Color(0x16C4CA));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        lblClassName = new JLabel("Class Name:");
        lblClassName.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelForm.add(lblClassName, gbc);

        txtClassName = new JTextField(15);
        txtClassName.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelForm.add(txtClassName, gbc);

        lblSemester = new JLabel("Semester:");
        lblSemester.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelForm.add(lblSemester, gbc);

        txtSemester = new JTextField(15);
        txtSemester.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelForm.add(txtSemester, gbc);

        lblYear = new JLabel("Year: ");
        lblYear.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelForm.add(lblYear, gbc);

        txtYear = new JTextField(15);
        txtYear.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelForm.add(txtYear, gbc);

        lblCopyright = new JLabel("©MUKUNDI WITNESS CHINGWENA - ICS 1A", JLabel.CENTER);
        lblCopyright.setFont(new Font("Arial", Font.PLAIN, 12));
        add(lblCopyright, BorderLayout.SOUTH);


        btnAddClass = new JButton("Add Class");
        btnAddClass.setBackground(new Color(0x9DA11E));
        btnAddClass.setForeground(Color.WHITE);
        btnAddClass.setFocusPainted(false);
        btnAddClass.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelForm.add(btnAddClass, gbc);

        btnViewFeedback = new JButton("View Feedback");
        btnViewFeedback.setBackground(new Color(0x173ACC));
        btnViewFeedback.setForeground(Color.BLACK);
        btnViewFeedback.setFocusPainted(false);
        btnViewFeedback.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 3;
        panelForm.add(btnViewFeedback, gbc);

        add(lblTitle, BorderLayout.NORTH);
        add(panelForm, BorderLayout.CENTER);

        btnAddClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addClass();
            }
        });

        btnViewFeedback.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FeedbackViewerGUI(studentId).setVisible(true);
            }
        });
    }

    private void addClass() {
        String className = txtClassName.getText();
        String semester = txtSemester.getText();
        String year = txtYear.getText();

        if (className.isEmpty() || semester.isEmpty() || year.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Missing Information", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO tbl_classes (student_id, class_name, semester, year) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, studentId);
            stmt.setString(2, className);
            stmt.setString(3, semester);
            stmt.setString(4, year);

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int classId = rs.getInt(1);
                JOptionPane.showMessageDialog(this, "Class added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                // Pass classId to TopicsGUI for adding topics to the class
                new TopicsGUI(studentId, classId).setVisible(true);
                dispose();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding class.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
//It is the robotic hymn of doom



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

public class TopicsGUI extends JFrame {
    private JLabel lblTitle, lblTopicName, lblWeek, lblDescription, lblCopyright;
    private JTextField txtTopicName, txtWeek;
    private JTextArea txtTopicDescription;
    private JButton btnAddTopic;
    private int classId;
    private String studentId;

    public TopicsGUI(String studentId, int classId) {
        this.studentId = studentId;
        this.classId = classId;

        setTitle("Add Topics for Class");
        setSize(450, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        lblTitle = new JLabel("Add a New Topic", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setForeground(new Color(0xAD1427));

        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        panelForm.setBackground(new Color(0x16C4CA));
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        lblTopicName = new JLabel("Topic Name:");
        lblTopicName.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelForm.add(lblTopicName, gbc);

        txtTopicName = new JTextField(15);
        txtTopicName.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelForm.add(txtTopicName, gbc);

        lblWeek = new JLabel("Week:");
        lblWeek.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelForm.add(lblWeek, gbc);

        txtWeek = new JTextField(15);
        txtWeek.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelForm.add(txtWeek, gbc);

        lblDescription = new JLabel("Description:");
        lblDescription.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelForm.add(lblDescription, gbc);

        txtTopicDescription = new JTextArea(3, 15);
        txtTopicDescription.setFont(new Font("Arial", Font.PLAIN, 14));
        txtTopicDescription.setWrapStyleWord(true);
        txtTopicDescription.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(txtTopicDescription);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelForm.add(scrollPane, gbc);

        lblCopyright = new JLabel("©MUKUNDI WITNESS CHINGWENA - ICS 1A", JLabel.CENTER);
        lblCopyright.setFont(new Font("Arial", Font.PLAIN, 12));
        add(lblCopyright, BorderLayout.SOUTH);

        btnAddTopic = new JButton("Add Topic");
        btnAddTopic.setBackground(new Color(0x9DA11E));
        btnAddTopic.setForeground(Color.WHITE);
        btnAddTopic.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panelForm.add(btnAddTopic, gbc);

        add(lblTitle, BorderLayout.NORTH);
        add(panelForm, BorderLayout.CENTER);

        btnAddTopic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTopic();
            }
        });
    }

    private void addTopic() {
        String topicName = txtTopicName.getText();
        String week = txtWeek.getText();
        String description = txtTopicDescription.getText();

        if (topicName.isEmpty() || week.isEmpty() || description.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields", "Missing Information", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO tbl_topics (class_id, topic_name, week, description) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, classId);
            stmt.setString(2, topicName);
            stmt.setString(3, week);
            stmt.setString(4, description);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int topicId = rs.getInt(1);
                JOptionPane.showMessageDialog(this, "Topic added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                new FeedbackGUI(studentId, topicId).setVisible(true);
                dispose();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding topic.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
//It is the robotic hymn of doom

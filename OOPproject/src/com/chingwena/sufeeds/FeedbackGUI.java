//Mukundi Witness Chingwena, ICS, 190004, 9/11/2024
package com.chingwena.sufeeds;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FeedbackGUI extends JFrame {
    private JLabel lblCopyright;
    private JTextArea txtFeedbackContent;
    private JButton btnSubmitFeedback;
    private String studentId;
    private int topicId;

    public FeedbackGUI(String studentId, int topicId) {
        this.studentId = studentId;
        this.topicId = topicId;

        setTitle("Submit Feedback");
        setSize(450, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        txtFeedbackContent = new JTextArea();
        txtFeedbackContent.setWrapStyleWord(true);
        txtFeedbackContent.setLineWrap(true);
        txtFeedbackContent.setFont(new Font("Arial", Font.PLAIN, 14));
        txtFeedbackContent.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0xCCCCCC), 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        JScrollPane scrollPane = new JScrollPane(txtFeedbackContent);

        lblCopyright = new JLabel("©MUKUNDI WITNESS CHINGWENA - ICS 1A", JLabel.CENTER);
        lblCopyright.setFont(new Font("Arial", Font.PLAIN, 12));
        add(lblCopyright, BorderLayout.NORTH);

        btnSubmitFeedback = new JButton("Submit Feedback");
        btnSubmitFeedback.setFont(new Font("Arial", Font.BOLD, 14));
        btnSubmitFeedback.setForeground(Color.WHITE);
        btnSubmitFeedback.setBackground(new Color(0xAD1427));
        btnSubmitFeedback.setFocusPainted(false);


        add(scrollPane, BorderLayout.CENTER);
        add(btnSubmitFeedback, BorderLayout.SOUTH);


        btnSubmitFeedback.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitFeedback();
            }
        });
    }

    private void submitFeedback() {
        String feedbackContent = txtFeedbackContent.getText();

        if (feedbackContent.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter feedback.", "Missing Information", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO tbl_feedback (student_id, topic_id, feedback_content) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, studentId);
            stmt.setInt(2, topicId);
            stmt.setString(3, feedbackContent);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Feedback submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            new FeedbackViewerGUI(studentId).setVisible(true);
            dispose();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error submitting feedback.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
//It is the robotic hymn of doom

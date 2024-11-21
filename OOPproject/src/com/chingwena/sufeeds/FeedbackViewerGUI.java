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

public class FeedbackViewerGUI extends JFrame {
    private JLabel lblCopyright;
    private JPanel feedbackPanel;
    private String studentId;
    private JButton logoutButton;

    public FeedbackViewerGUI(String studentId) {
        this.studentId = studentId;

        setTitle("View All Feedback");
        setSize(600, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        feedbackPanel = new JPanel();
        feedbackPanel.setLayout(new BoxLayout(feedbackPanel, BoxLayout.Y_AXIS));
        feedbackPanel.setBackground(new Color(0x16C4CA));



        lblCopyright = new JLabel("©MUKUNDI WITNESS CHINGWENA - ICS 1A", JLabel.CENTER);
        lblCopyright.setFont(new Font("Arial", Font.PLAIN, 12));
        add(lblCopyright, BorderLayout.SOUTH);


        logoutButton = new JButton("Logout");
        logoutButton.setBackground(new Color(0xBCC12E));
        logoutButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        logoutButton.setFont(new Font("Arial", Font.PLAIN, 14));
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginGUI();
            }
        });

        add(logoutButton, BorderLayout.NORTH);
        add(new JScrollPane(feedbackPanel), BorderLayout.CENTER);

        loadFeedback();
    }

    private void loadFeedback() {
        feedbackPanel.removeAll();

        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT f.feedback_id, f.feedback_content, c.class_name, t.topic_name, s.student_id, " +
                    "s.student_name, c.year ,s.school, s.course " +
                    "FROM tbl_feedback f " +
                    "JOIN tbl_topics t ON f.topic_id = t.topic_id " +
                    "JOIN tbl_classes c ON t.class_id = c.class_id " +
                    "JOIN tbl_students s ON f.student_id = s.student_id";

            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int feedbackId = rs.getInt("feedback_id");
                String feedbackContent = rs.getString("feedback_content");
                String className = rs.getString("class_name");
                String topicName = rs.getString("topic_name");
                String feedbackStudentId = rs.getString("student_id");
                String studentName = rs.getString("student_name");
                String year = rs.getString("year");
                String school = rs.getString("school");
                String course = rs.getString("course");

                JPanel feedbackEntry = new JPanel(new BorderLayout());
                feedbackEntry.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(0xCCCCCC), 1),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)
                ));
                feedbackEntry.setBackground(Color.WHITE);

                JTextArea feedbackDetails = new JTextArea();
                feedbackDetails.setText(
                        "Class: " + className + "\n" +
                                "Topic: " + topicName + "\n" +
                                "User: " + feedbackStudentId + " - " + studentName + "\n" +
                                "Year: " + year + "\n" +
                                "School: " + school + "\n" +
                                "Course: " + course + "\n" +
                                "Feedback: " + feedbackContent

                );
                feedbackDetails.setEditable(false);
                feedbackDetails.setWrapStyleWord(true);
                feedbackDetails.setLineWrap(true);
                feedbackDetails.setFont(new Font("Arial", Font.PLAIN, 13));
                feedbackDetails.setForeground(new Color(0x333333));

                feedbackEntry.add(feedbackDetails, BorderLayout.CENTER);

                if (feedbackStudentId.equals(studentId)) {
                    JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                    JButton btnEdit = new JButton("Edit");
                    JButton btnDelete = new JButton("Delete");

                    btnEdit.setBackground(new Color(0x9DA11E));
                    btnEdit.setForeground(Color.WHITE);
                    btnDelete.setBackground(new Color(0xAD1427));
                    btnDelete.setForeground(Color.WHITE);

                    btnEdit.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            editFeedback(feedbackId, feedbackContent);
                        }
                    });

                    btnDelete.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            deleteFeedback(feedbackId);
                        }
                    });

                    btnPanel.add(btnEdit);
                    btnPanel.add(btnDelete);
                    feedbackEntry.add(btnPanel, BorderLayout.SOUTH);
                }

                feedbackPanel.add(feedbackEntry);
                feedbackPanel.add(Box.createVerticalStrut(10)); // Add space between feedback entries
            }

            feedbackPanel.revalidate();
            feedbackPanel.repaint();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading feedback.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editFeedback(int feedbackId, String currentContent) {
        String newFeedbackContent = JOptionPane.showInputDialog(this, "Edit your feedback:", currentContent);

        if (newFeedbackContent != null && !newFeedbackContent.trim().isEmpty()) {
            try (Connection conn = DBConnection.getConnection()) {
                String updateQuery = "UPDATE tbl_feedback SET feedback_content = ? WHERE feedback_id = ?";
                PreparedStatement stmt = conn.prepareStatement(updateQuery);
                stmt.setString(1, newFeedbackContent);
                stmt.setInt(2, feedbackId);
                stmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "Feedback updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadFeedback();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating feedback.", "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deleteFeedback(int feedbackId) {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this feedback?", "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = DBConnection.getConnection()) {
                String deleteQuery = "DELETE FROM tbl_feedback WHERE feedback_id = ?";
                PreparedStatement stmt = conn.prepareStatement(deleteQuery); 
                stmt.setInt(1, feedbackId);
                stmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "Feedback deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadFeedback();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error deleting feedback.", "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
//It is the robotic hymn of doom

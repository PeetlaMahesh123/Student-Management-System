package com.Student;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.*;

public class UpdateStudent extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtId, txtName, txtDept, txtMarks;

    // 🔹 SINGLE CONNECTION (CLASS LEVEL)
    private Connection con;

    public UpdateStudent() {

        setTitle("Update Student");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // 🔹 CREATE CONNECTION ONCE
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/collegemanagement",
                    "root", ""
            );
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database connection failed");
            return;
        }

        JLabel title = new JLabel("Update Student");
        title.setForeground(Color.BLUE);
        title.setFont(new Font("Times New Roman", Font.BOLD, 16));
        title.setBounds(150, 15, 200, 20);
        contentPane.add(title);

        JLabel lblId = new JLabel("Enter ID:");
        lblId.setBounds(40, 55, 100, 20);
        contentPane.add(lblId);

        txtId = new JTextField();
        txtId.setBounds(140, 55, 120, 20);
        contentPane.add(txtId);

        JLabel lblName = new JLabel("New Name:");
        lblName.setBounds(40, 90, 100, 20);
        contentPane.add(lblName);

        txtName = new JTextField();
        txtName.setBounds(140, 90, 120, 20);
        contentPane.add(txtName);

        JLabel lblDept = new JLabel("New Department:");
        lblDept.setBounds(40, 125, 120, 20);
        contentPane.add(lblDept);

        txtDept = new JTextField();
        txtDept.setBounds(140, 125, 120, 20);
        contentPane.add(txtDept);

        JLabel lblMarks = new JLabel("New Marks:");
        lblMarks.setBounds(40, 160, 100, 20);
        contentPane.add(lblMarks);

        txtMarks = new JTextField();
        txtMarks.setBounds(140, 160, 120, 20);
        contentPane.add(txtMarks);

        JButton btnName = new JButton("Update Name");
        btnName.setBounds(290, 90, 130, 20);
        contentPane.add(btnName);

        JButton btnDept = new JButton("Update Dept");
        btnDept.setBounds(290, 125, 130, 20);
        contentPane.add(btnDept);

        JButton btnMarks = new JButton("Update Marks");
        btnMarks.setBounds(290, 160, 130, 20);
        contentPane.add(btnMarks);

        JButton btnBack = new JButton("< Back");
        btnBack.setBounds(330, 230, 90, 20);
        contentPane.add(btnBack);

        // update name
        btnName.addActionListener(e -> {
            if (txtId.getText().isEmpty() || txtName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter ID and Name");
                return;
            }

            try (PreparedStatement ps =
                         con.prepareStatement(
                                 "UPDATE studentinfo SET name = ? WHERE id = ?")) {

                ps.setString(1, txtName.getText());
                ps.setInt(2, Integer.parseInt(txtId.getText().trim()));

                int rows = ps.executeUpdate();
                JOptionPane.showMessageDialog(
                        this,
                        rows > 0 ? "Name updated successfully!" : "Student not found"
                );

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // update department
        btnDept.addActionListener(e -> {
            if (txtId.getText().isEmpty() || txtDept.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter ID and Department");
                return;
            }

            try (PreparedStatement ps =
                         con.prepareStatement(
                                 "UPDATE studentinfo SET dept = ? WHERE id = ?")) {

                ps.setString(1, txtDept.getText());
                ps.setInt(2, Integer.parseInt(txtId.getText().trim()));

                int rows = ps.executeUpdate();
                JOptionPane.showMessageDialog(
                        this,
                        rows > 0 ? "Department updated successfully!" : "Student not found"
                );

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        // update marks
        btnMarks.addActionListener(e -> {
            if (txtId.getText().isEmpty() || txtMarks.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter ID and Marks");
                return;
            }
            try (PreparedStatement ps =
                         con.prepareStatement(
                                 "UPDATE studentinfo SET marks = ? WHERE id = ?")) {

                ps.setInt(1, Integer.parseInt(txtMarks.getText().trim()));
                ps.setInt(2, Integer.parseInt(txtId.getText().trim()));

                int rows = ps.executeUpdate();
                JOptionPane.showMessageDialog(
                        this,
                        rows > 0 ? "Marks updated successfully!" : "Student not found"
                );

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });
        btnBack.addActionListener(e -> {
            try {
                if (con != null) con.close(); // 🔹 close once
            } catch (SQLException ignored) {}
            new Main();
            dispose();
        });

        setVisible(true);
    }
}

package com.Student;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.*;

public class DeleteStudent extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtId;

    public DeleteStudent() {

        setTitle("Delete Student");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel title = new JLabel("Delete Student");
        title.setFont(new Font("Times New Roman", Font.BOLD, 16));
        title.setBounds(160, 30, 150, 20);
        contentPane.add(title);

        JLabel lblId = new JLabel("Enter ID:");
        lblId.setFont(new Font("Times New Roman", Font.BOLD, 13));
        lblId.setBounds(90, 90, 80, 20);
        contentPane.add(lblId);

        txtId = new JTextField();
        txtId.setBounds(180, 90, 120, 20);
        contentPane.add(txtId);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(150, 140, 120, 25);
        btnDelete.setBackground(new Color(128, 0, 0));
        btnDelete.setForeground(Color.WHITE);
        contentPane.add(btnDelete);

        JButton btnBack = new JButton("< Back");
        btnBack.setBounds(330, 230, 90, 20);
        contentPane.add(btnBack);

        // 🔹 DELETE STUDENT (NO METHOD)
        btnDelete.addActionListener(e -> {

            if (txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter student ID",
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this student?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }
            Connection con = null;
            PreparedStatement ps = null;
            try {
                int id = Integer.parseInt(txtId.getText().trim());

              con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/collegemanagement",
                        "root", ""
                );
               ps = con.prepareStatement("DELETE FROM studentinfo WHERE id = ?");
                ps.setInt(1, id);
                int rows = ps.executeUpdate();
                JOptionPane.showMessageDialog(
                        this,
                        rows > 0 ? "Student deleted successfully!"
                                 : "Student not found"
                );
                
                txtId.setText("");
            } catch (Exception ex) {
                ex.printStackTrace();
            }finally {
            	try {
            	if(con != null) con.close();
            	if(ps != null) ps.close();
            	} catch(Exception e1) {
            		e1.printStackTrace();
            	}
            }
        });

        btnBack.addActionListener(e -> {
            new Main();
            dispose();
        });

        setVisible(true);
    }
}

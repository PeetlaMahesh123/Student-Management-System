package com.Student;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.sql.*;

public class Fetch extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField eid;
    private JTextArea result;

    public Fetch() {
        setTitle("Fetch Student");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel title = new JLabel("Fetch Student");
        title.setForeground(Color.BLUE);
        title.setFont(new Font("Times New Roman", Font.BOLD, 16));
        title.setBounds(165, 20, 150, 20);
        contentPane.add(title);

        JLabel lblId = new JLabel("Enter ID:");
        lblId.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblId.setBounds(60, 50, 80, 20);
        contentPane.add(lblId);

        eid = new JTextField();
        eid.setBounds(150, 50, 120, 20);
        contentPane.add(eid);

        JButton btnFetch = new JButton("Fetch");
        btnFetch.setBounds(290, 50, 80, 20);
        contentPane.add(btnFetch);

        result = new JTextArea();
        result.setBounds(20, 100, 400, 120);
        result.setEditable(false);
        contentPane.add(result);

        JButton btnBack = new JButton("< Back");
        btnBack.setBounds(330, 230, 90, 20);
        contentPane.add(btnBack);

        // 🔹 FETCH BUTTON LOGIC (NO METHOD)
        btnFetch.addActionListener(e -> {

            result.setText("");

            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/collegemanagement";
            String user = "root";
            String pwd = "";
            String sql = "SELECT * FROM studentinfo WHERE id = ?";

            try {
                Class.forName(driver);
                Connection con = DriverManager.getConnection(url, user, pwd);
                PreparedStatement ps = con.prepareStatement(sql);

                int id = Integer.parseInt(eid.getText().trim());
                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    result.setText(
                            "Student ID : " + rs.getInt("id") + "\n" +
                            "Name       : " + rs.getString("name") + "\n" +
                            "Department : " + rs.getString("dept") + "\n" +
                            "Marks      : " + rs.getInt("marks")
                    );
                } else {
                    result.setText("Student not found");
                }

                rs.close();
                ps.close();
                con.close();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        null,
                        "Error: " + ex.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        btnBack.addActionListener(e -> {
            new Main();
            dispose();
        });

        setVisible(true);
    }
}

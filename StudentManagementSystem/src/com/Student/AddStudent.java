package com.Student;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.*;

public class AddStudent extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField id, name, dept, marks;

    public AddStudent() {
        setTitle("Add Student");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel title = new JLabel("Add Student");
        title.setForeground(Color.BLUE);
        title.setFont(new Font("Times New Roman", Font.BOLD, 16));
        title.setBounds(170, 20, 150, 20);
        contentPane.add(title);

        JLabel lblId = new JLabel("Enter ID:");
        lblId.setBounds(50, 70, 100, 20);
        contentPane.add(lblId);

        JLabel lblName = new JLabel("Enter Name:");
        lblName.setBounds(50, 100, 100, 20);
        contentPane.add(lblName);

        JLabel lblDept = new JLabel("Enter Dept:");
        lblDept.setBounds(50, 130, 100, 20);
        contentPane.add(lblDept);

        JLabel lblMarks = new JLabel("Enter Marks:");
        lblMarks.setBounds(50, 160, 100, 20);
        contentPane.add(lblMarks);

        id = new JTextField();
        id.setBounds(180, 70, 150, 20);
        contentPane.add(id);

        name = new JTextField();
        name.setBounds(180, 100, 150, 20);
        contentPane.add(name);

        dept = new JTextField();
        dept.setBounds(180, 130, 150, 20);
        contentPane.add(dept);

        marks = new JTextField();
        marks.setBounds(180, 160, 150, 20);
        contentPane.add(marks);

        JButton btnAdd = new JButton("Add Student");
        btnAdd.setBounds(150, 200, 140, 25);
        contentPane.add(btnAdd);

        JButton btnBack = new JButton("< Back");
        btnBack.setBounds(330, 230, 90, 20);
        contentPane.add(btnBack);

        // 🔹 ADD STUDENT LOGIC (NO METHOD)
        btnAdd.addActionListener(e -> {

            if (id.getText().isEmpty() || name.getText().isEmpty()
                    || dept.getText().isEmpty() || marks.getText().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "All fields are required",
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            Connection con = null;
            PreparedStatement ps = null;
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/collegemanagement";
            String user = "root";
            String pwd = "";

            try {
                Class.forName(driver);
                con = DriverManager.getConnection(url, user, pwd);
                ps = con.prepareStatement("INSERT INTO studentinfo VALUES (?, ?, ?, ?)");
                int sid = Integer.parseInt(id.getText().trim());
                int smarks = Integer.parseInt(marks.getText().trim());
                ps.setInt(1, sid);
                ps.setString(2, name.getText());
                ps.setString(3, dept.getText());
                ps.setInt(4, smarks);
                int rows = ps.executeUpdate();
                JOptionPane.showMessageDialog(
                        this,
                        rows > 0 ? "Student added successfully!" : "Insert failed"
                );
                // clear fields
                id.setText("");
                name.setText("");
                dept.setText("");
                marks.setText("");
            } catch (Exception e1) {
               e1.printStackTrace();
            } finally {
            	try {
            	if(con != null) con.close();
            	if(ps != null) ps.close();}
            	catch(Exception e1) {
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

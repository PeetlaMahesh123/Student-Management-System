package com.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class FetchAllStudent extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private DefaultTableModel model;

    public FetchAllStudent() {

        setTitle("Fetch All Students");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 350);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JPanel contentPane = new JPanel();
        contentPane.setBounds(0, 0, 600, 350);
        contentPane.setBackground(new Color(128, 255, 255));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel title = new JLabel("FETCH ALL STUDENTS", SwingConstants.CENTER);
        title.setFont(new Font("Times New Roman", Font.BOLD, 18));
        title.setForeground(Color.BLUE);
        title.setBounds(75, 10, 430, 30);
        contentPane.add(title);

        // 🔹 TABLE MODEL
        model = new DefaultTableModel(
                new String[]{"ID", "Name", "Department", "Marks"}, 0
        );

        table = new JTable(model);
        table.setRowHeight(26);
        table.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        table.getTableHeader().setFont(
                new Font("Times New Roman", Font.BOLD, 14)
        );

        // 🔹 ROW COLORING
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column
                );

                if (!isSelected) {
                    c.setBackground(
                            row % 2 == 0
                                    ? new Color(220, 235, 255)
                                    : new Color(220, 255, 220)
                    );
                    c.setForeground(Color.BLACK);
                } else {
                    c.setBackground(new Color(100, 150, 255));
                    c.setForeground(Color.WHITE);
                }
                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 50, 560, 220);
        contentPane.add(scrollPane);

        JButton btnBack = new JButton("< Back");
        btnBack.setFont(new Font("Times New Roman", Font.BOLD, 13));
        btnBack.setBounds(486, 278, 90, 25);
        contentPane.add(btnBack);

        btnBack.addActionListener(e -> {
            new Main().setVisible(true);
            dispose();
        });

        // 🔹 FETCH ALL DATA (NO METHOD)
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/collegemanagement",
                    "root", ""
            );

            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM studentinfo");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("dept"),
                        rs.getInt("marks")
                });
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error: " + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FetchAllStudent::new);
    }
}

package com.Student;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Main() {

        getContentPane().setBackground(new Color(128, 255, 255));
        setTitle("Student Management System");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel title = new JLabel("Student Management System");
        title.setFont(new Font("Times New Roman", Font.BOLD, 16));
        title.setForeground(Color.BLUE);
        title.setBounds(110, 20, 300, 30);
        getContentPane().add(title);

        JButton btnFetch = new JButton("Fetch Student");
        btnFetch.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
        btnFetch.setBackground(new Color(255, 255, 255));
        JButton btnAdd = new JButton("Add Student");
        btnAdd.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
        btnAdd.setBackground(new Color(255, 255, 255));
        JButton btnDelete = new JButton("Delete Student");
        btnDelete.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
        btnDelete.setBackground(new Color(255, 255, 255));
        JButton btnUpdate = new JButton("Update Student");
        btnUpdate.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
        btnUpdate.setBackground(new Color(255, 255, 255));
        JButton btnFetchAll = new JButton("Fetch All Students");
        btnFetchAll.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
        btnFetchAll.setBackground(new Color(255, 255, 255));

        btnFetch.setBounds(40, 80, 150, 25);
        
        btnFetch.addActionListener(e -> { 
        	new Fetch(); 
        	dispose(); 
        	});
        
        
        btnAdd.setBounds(40, 120, 150, 25);
        
        btnAdd.addActionListener(e -> { 
        	new AddStudent(); 
        	dispose(); 
        	});
        
        btnDelete.setBounds(250, 80, 150, 25);
        
        btnDelete.addActionListener(e -> { 
        	new DeleteStudent();
        	dispose();
        });
        
        btnUpdate.setBounds(250, 120, 150, 25);
        
        btnUpdate.addActionListener(e -> { 
        	new UpdateStudent();
        	dispose(); 
        });   
        
        btnFetchAll.setBounds(150, 170, 150, 25); 
        
        btnFetchAll.addActionListener(e -> {
        	new FetchAllStudent();   
        	dispose();       
        });
        
        getContentPane().add(btnFetch);
        getContentPane().add(btnAdd);
        getContentPane().add(btnDelete);
        getContentPane().add(btnUpdate);
        getContentPane().add(btnFetchAll);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}

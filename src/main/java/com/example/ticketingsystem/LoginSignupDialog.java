package com.example.ticketingsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginSignupDialog {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Show the login/signup dialog when the program starts
            showLoginDialog();
        });
    }

    public static void showLoginDialog() {
        JFrame frame = new JFrame("Login/Signup");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null); // Center the window on the screen

        // Create a main panel with a background color
        JPanel panel = new JPanel();
        panel.setBackground(new Color(245, 245, 245)); // Light grey background
        frame.add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Title label with some styling
        JLabel titleLabel = new JLabel("Welcome to Ticketing System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(new Color(56, 119, 197)); // Blue color

        // Username and password fields with padding
        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(245, 245, 245));
        formPanel.setLayout(new GridLayout(2, 2, 10, 10));

        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JTextField usernameField = new JTextField(20);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

        formPanel.add(userLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);

        // Buttons with styling
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 245));

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(56, 119, 197)); // Blue color
        loginButton.setForeground(Color.BLUE);
        loginButton.setPreferredSize(new Dimension(120, 40));
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        JButton signupButton = new JButton("Sign Up");
        signupButton.setFont(new Font("Arial", Font.BOLD, 14));
        signupButton.setBackground(new Color(32, 155, 66)); // Green color
        signupButton.setForeground(Color.BLUE);
        signupButton.setPreferredSize(new Dimension(120, 40));
        signupButton.setFocusPainted(false);
        signupButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        buttonPanel.add(loginButton);
        buttonPanel.add(signupButton);

        // Add everything to the main panel
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(formPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(buttonPanel);

        // Add action listeners for the buttons
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Dummy login validation
                if ("admin".equals(username) && "password".equals(password)) {
                    JOptionPane.showMessageDialog(frame, "Login successful!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Dummy signup logic (e.g., print to console)
                System.out.println("Signing up new user:");
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);
                JOptionPane.showMessageDialog(frame, "Sign up successful!");
            }
        });

        frame.setVisible(true);
    }
}

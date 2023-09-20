/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package abdillah130923;

/**
 *
 * @author abdil
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class BannerApp extends JFrame {
    private JLabel topLabel;
    private JLabel bottomLabel;
    private String topText;
    private String bottomText;

    public BannerApp() {
        topLabel = new JLabel();
        bottomLabel = new JLabel();

        topLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        topLabel.setHorizontalAlignment(JLabel.LEFT);

        bottomLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        bottomLabel.setHorizontalAlignment(JLabel.RIGHT);

        add(topLabel, BorderLayout.NORTH);
        add(bottomLabel, BorderLayout.SOUTH);

        topText = "Abdillah Hendra Rahmatullah";
        bottomText = "Abdillah Hendra Rahmatullah";

        // Buat thread untuk animasi teks pertama (kiri ke kanan)
        Thread topThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    moveTopText();
                    try {
                        Thread.sleep(120); // Mengatur kecepatan animasi
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // Buat thread untuk animasi teks kedua (kanan ke kiri)
        Thread bottomThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    moveBottomText();
                    try {
                        Thread.sleep(120); // Mengatur kecepatan animasi
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        topThread.start();
        bottomThread.start();

        setTitle("Dual Banner App");
        setSize(600, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void moveTopText() {
        topText = topText.substring(1) + topText.charAt(0);
        topLabel.setText(topText);
    }

    private void moveBottomText() {
        bottomText = bottomText.charAt(bottomText.length() - 1) + bottomText.substring(0, bottomText.length() - 1);
        bottomLabel.setText(bottomText);
    }

    public static void main(String[] args) {
        new BannerApp();
    }
}

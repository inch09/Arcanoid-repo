import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        int boardWidth = 800;
        int boardHeight = 800;
        JFrame startWindow = new JFrame("Меню");
        JButton button = new JButton("Играть");
        startWindow.setLocation(500, 50);
        button.setBounds(300, 300, 200, 100);
        button.setVisible(true);
        startWindow.add(button);
        startWindow.setSize(boardWidth, boardHeight);
        startWindow.setLayout(null);
        startWindow.setVisible(true);
        startWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startWindow.dispose();
                JFrame frame = new JFrame("Arcanoid");
                frame.setVisible(true);
                frame.setLocation(500, 50);
                frame.setSize(boardWidth, boardHeight);
                frame.setResizable(false);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Arcanoid arcanoid = new Arcanoid(boardWidth, boardHeight);
                frame.add(arcanoid);
                frame.pack();
                arcanoid.requestFocus();

            }
        }
        );


    }


}
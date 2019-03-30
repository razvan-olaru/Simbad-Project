package main.java.softdesign;

import javax.swing.JInternalFrame;
import simbad.gui.Simbad;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/*
The Utility class allows the user to interact with the system by letting him
choose whether the mission should be aborted or not. A stop button was created
in the same frame as the system.
*/
public final class Utility {

    // Constructor
    private Utility() {}

    // Creates the stop button
    public static void addStopButton(Simbad frame) {
        JInternalFrame jframe = new JInternalFrame("Mission", true, false, false, false);
        jframe.setBounds(650, 500, 120, 75);

        Container container = jframe.getContentPane();
        JButton stopButton = new JButton("Stop Mission");
        Font smallFont = new Font("Arial", Font.PLAIN, 11);
        stopButton.setFont(smallFont);
        ActionListener stopActionListener = e -> {
            CentralStation.getInstance().stopMission();
            JOptionPane.showMessageDialog(null, "Mission Stopped!","Message", JOptionPane.INFORMATION_MESSAGE);
        };
        stopButton.addActionListener(stopActionListener);
        container.add(stopButton);
        frame.getDesktopPane().add(jframe);
        jframe.setVisible(true);
    }
}
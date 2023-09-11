/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.imagestoautocad.imagecoordinatestoautocad;

import java.awt.Dimension;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;

/**
 *
 * @author szaboa
 */
public class Main_ImageCoordinatesToAutoCAD {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("File Selector");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 180);
        frame.setLocationRelativeTo(null);

        JButton openButton = new JButton("Select Folder");
        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectFolderAndGenerateFile();
            }
        });
        
        JEditorPane editorPane = new JEditorPane();
        editorPane.setContentType("text/html"); // Set content type to HTML
        editorPane.setEditable(false); // Make it read-only
        editorPane.setPreferredSize(new Dimension(300, 100));
        editorPane.setText("<html>Válaszz egy mappát!<br/>A program \"imageConvert.scr\" néven elhelyez egy fájlt a megnyitott mappában.<br/>AutoCAD-be a .src fájl behívható a _SCRIPT paranccsal.</html>");

        JPanel panel = new JPanel();
        panel.add(openButton);
        panel.add(editorPane);
        frame.add(panel);

        frame.setVisible(true);
    }

    private static void selectFolderAndGenerateFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = fileChooser.getSelectedFile();
            boolean success = generateScrFile(selectedFolder);

            if (success) {
                JOptionPane.showMessageDialog(null, "File generated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                // Close the application after successful file generation
                System.exit(0);
            } else {
                JOptionPane.showMessageDialog(null, "Error generating file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static boolean generateScrFile(File folder) {
        ReadImages r = new ReadImages();
        WriteImages w = new WriteImages();

        // Read GPS data from images in the specified folder
        r.readImagesFromFolder(folder.getAbsolutePath());

        // Get the list of extracted GPS information
        List<GpsInfo> gpsInfoList = r.getGpsInfoList();

        String fileName = "imageConvert.scr";

        // Write GPS data to the "example.scr" file
        w.writeGPSDataToFile(new File(folder, fileName).getAbsolutePath(), gpsInfoList);
        return true;
    }
}
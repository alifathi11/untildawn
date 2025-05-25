package com.untildawn.Models;

import com.badlogic.gdx.files.FileHandle;

import java.awt.*;

import com.badlogic.gdx.files.FileHandle;

import javax.swing.*;
import java.io.File;

public class FileChooser {
    public interface FileChooserListener {
        void fileChosen(FileHandle file);
    }

    private FileChooserListener listener;

    public void chooseFile() {
        SwingUtilities.invokeLater(() -> {
            JFileChooser chooser = new JFileChooser();
            int returnVal = chooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File selected = chooser.getSelectedFile();
                if (selected != null) {
                    listener.fileChosen(new FileHandle(selected));
                }
            }
        });
    }

    public void setListener(FileChooserListener listener) {
        this.listener = listener;
    }
}

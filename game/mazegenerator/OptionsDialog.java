package game.mazegenerator;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class OptionsDialog extends JDialog { 
    private static final long serialVersionUID = 1L; private int rows = 0;

    private int cols = 0;
    private int type = 0;
    private boolean canceled = true;

    private JTextField rowsField = new JTextField(3);
    private JTextField colsField = new JTextField(3);
    private JRadioButton mazeButton = new JRadioButton("Maze");
    private JRadioButton antiMazeButton = new JRadioButton("Anti-Maze");

    public OptionsDialog(int rows, int cols, int type) { 
        this.rows = rows;
        this.cols = cols;
        this.type = type;
        
        setTitle("Maze Generator Options"); 
        
        // main panel
        JPanel mainPanel = new JPanel();
        // laying out the dialog components in a column
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        add(mainPanel, BorderLayout.CENTER);
        JLabel rowsLabel = new JLabel("Rows:"); 
        mainPanel.add(rowsLabel);
        rowsField.setText("" + rows);
        mainPanel.add(rowsField);
        JLabel colsLabel = new JLabel("Columns:"); 
        mainPanel.add(colsLabel);
        colsField.setText("" + cols);
        mainPanel.add(colsField);
        // radio buttons to choose maze or anti-maze
        JLabel typeLabel = new JLabel("Maze Type:"); 
        mainPanel.add(typeLabel);
        mainPanel.add(mazeButton);
        mainPanel.add(antiMazeButton);
        // placing the radio buttons in a button group
        ButtonGroup buttonGroup = new ButtonGroup(); 
        buttonGroup.add(mazeButton); 
        buttonGroup.add(antiMazeButton);
        // setting the initially selected radio button
        buttonGroup.add(antiMazeButton);
        if(type==MazeGenerator.TYPE_MAZE) {
            mazeButton.setSelected(true);
        }
        else {
            antiMazeButton.setSelected(true); 
        }
       
           // button panel
        // add code to create an OK button here
        JPanel buttonPanel = new JPanel(); 
        add(buttonPanel, BorderLayout.PAGE_END);
        JButton okButton = new JButton("OK"); 
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                close();
            } 
        });
        buttonPanel.add(okButton);
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                cancel();
            } 
        });
        buttonPanel.add(cancelButton);
        // setting okButton as the default button
        getRootPane().setDefaultButton(okButton); 

    }

    // isCanceled() method
    public boolean isCanceled() {
        return canceled;
    }

    // make the window invisible when user cancels the dialog 
    private void cancel() {
        setVisible(false);
    }

    // close() method 
    private void close() {
        try { 
            String rowsString = rowsField.getText(); 
            String colsString = colsField.getText(); 
            int newRows = Integer.parseInt(rowsString); 
            int newCols = Integer.parseInt(colsString); 
            if (newRows>1 && newCols>1) {
                rows = newRows;
                cols = newCols;
                if (mazeButton.isSelected()) {
                    type = MazeGenerator.TYPE_MAZE;
                }
                else {
                    type = MazeGenerator.TYPE_ANTIMAZE;
                }
                setVisible(false);
                canceled = false;
            } 
            else {
                String message = "There must be more than one rows and more than one columns.";
                JOptionPane.showMessageDialog(this, message); 
            }
        }
        catch (NumberFormatException e) {
                String message = "Rows and columns must be numbers.";
                JOptionPane.showMessageDialog(this, message); 
        }  
    }
    // getting rows, columns, and type from the dialog
    public int getRows() {
        return rows;
    }
    public int getColumns() {
        return cols;
    }
    public int getMazeType() {
        return type;
    }

    
}    
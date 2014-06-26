/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package commonwealth.gui;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import commonwealth.Main;



public class CommonwealthGUI extends javax.swing.JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5148636010798525188L;

	/**
     * Creates new form MainFrame
     */
    public CommonwealthGUI() {
        initComponents();
    }
                 
    private void initComponents(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.getContentPane();
        
        outputTextField = new JTextField();
        titleLabel = new JLabel();
        checkButton = new JButton();
        jScrollPane1_INPUT = new JScrollPane();
        jScrollPanel_OUTPUT = new JScrollPane();
        inputTextField = new JTextArea();
     
        introLabel = new JLabel();
        jLabel1 = new JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Commonwealth");
        setBackground(new java.awt.Color(251, 223, 105));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setSize(new java.awt.Dimension(600, 320));
        setResizable(false);
        setLocation(dim.width/2 - getSize().width/2, dim.height/2 - getSize().height/2);
        //setIconImage(icon);

        outputTextField.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        
        outputTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outputTextFieldActionPerformed(evt);
            }
        });
        outputTextField.setEditable(false);

        titleLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Welcome to Commonwealth");
        titleLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        titleLabel.setPreferredSize(new java.awt.Dimension(280, 23));

        checkButton.setBackground(java.awt.Color.red);
        checkButton.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        checkButton.setText("Check");
        checkButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    checkButtonActionPerformed(evt);
                } catch (FileNotFoundException e) {
                    
                    e.printStackTrace();
                } catch (IndexOutOfBoundsException e){
                    e.printStackTrace();
                }
                
            }
        });
        checkButton.setEnabled(false);
       
        inputTextField.setFont(new java.awt.Font("Comic Sans MS", 0, 14));
        inputTextField.setColumns(20);
        inputTextField.setLineWrap(true);
        inputTextField.setRows(5);
        inputTextField.setPreferredSize(new java.awt.Dimension(200, 100));
        jScrollPane1_INPUT.setViewportView(inputTextField);
        inputTextField.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "check");
        inputTextField.getActionMap().put("check", new AbstractAction() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 2073586348665522911L;

			public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    checkButtonActionPerformed(evt);
                } catch (FileNotFoundException e) {
                    
                    print("File not found :(");
                } catch (IndexOutOfBoundsException e){
                    print("Oops, something went wrong. Try Again!");
                }
                
            }
        });
        inputTextField.setEnabled(false);
                
        introLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        introLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        introLabel.setText("Please Type In Sentences Below:");
        introLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        introLabel.setPreferredSize(new java.awt.Dimension(280, 18));

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel1.setText("Output:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(introLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addComponent(checkButton, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(258, 258, 258)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(outputTextField)
                            .addComponent(jScrollPane1_INPUT, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(introLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(checkButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(outputTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }
                           
    private void checkButtonActionPerformed(ActionEvent evt) throws FileNotFoundException, IndexOutOfBoundsException {                                             
    	
    	String userInput = inputTextField.getText();
    	
    	//Main.start(userInput);
    	
    	print(Main.start(userInput));
    }                                           

    private void outputTextFieldActionPerformed(ActionEvent evt) {                                                
      
    }                      
    
    //private static void configureAll(){
    //    Identifier.initialize();
    //    Tokenizer.initialize();
    //}
    
    
    private void initialize(){
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CommonwealthGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CommonwealthGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CommonwealthGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CommonwealthGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public void run() throws FileNotFoundException{
        /* Set the Windows look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Windows (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
        this.initialize();
        final CommonwealthGUI GUI = new CommonwealthGUI();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GUI.setVisible(true);
            }
        });
        
        GUI.inputTextField.setText("Configuring...");
        //configureAll();
        GUI.inputTextField.setText("");
        GUI.inputTextField.setEnabled(true);
        GUI.checkButton.setEnabled(true);
        GUI.outputTextField.setEnabled(true);
    }
    
    public void print(String s){
        outputTextField.setText(s);
    }
    
    public static void show(String s){
        JOptionPane.showMessageDialog(null, s, "Info", JOptionPane.ERROR_MESSAGE);
    }
    
    // Variables declaration - do not modify
    private JLabel introLabel;
    private JLabel jLabel1;
    private JScrollPane jScrollPane1_INPUT;
    private JScrollPane jScrollPanel_OUTPUT;
    private JTextField outputTextField;
    private JButton checkButton;
    public JTextArea inputTextField;
    private JLabel titleLabel;
    // End of variables declaration                  
}

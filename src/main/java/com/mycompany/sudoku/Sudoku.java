/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.sudoku;
import javax.swing.*;      // Imports core GUI components like JButton, JFrame, and JPanel
import java.awt.*;         // Imports layout managers and colors
import java.awt.event.*;   // Imports event listeners for button clicks
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;

public final class Sudoku extends javax.swing.JFrame {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Sudoku.class.getName());
    javax.swing.JButton[][] board=new javax.swing.JButton[9][9]; //stores all the buttons
    boolean [][]predefinedButtons=new boolean[9][9]; //keeps a track of pre-defined buttons
    char[][] values= new char[9][9];    //updates curr values
    char[][] sol=new char[9][9];    //actual solution
    private boolean globalFlag=true;
    private boolean checkFlag=true;
    private String select="";
    
    JFrame f1=new JFrame("Loading Page");
    JLabel t1=new JLabel("new game is loading...");
    JProgressBar bar;
     JPanel panel=new JPanel();

    public Sudoku() {
        
        initComponents();
        this.setSize(735, 900);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        AssignTurn(b1);
        mapping();
        
    for(int i=0; i<9; i++){
        for(int j=0; j<9; j++){
            predefinedButtons[i][j] = !board[i][j].getText().equals("");
    }
    }
    values=helper(board);
    sol=helper(board);
        
    if(solve(sol,0,0)){System.out.println("Solution found!");}
    else{System.out.println("solution does not exist!");
    }    
    }     
    
    public void Loading_page(){
        f1.setSize(306,126);
        f1.setBackground(new Color(245, 245, 220));
        f1.setForeground(Color.BLACK);
        f1.setLocationRelativeTo(null);
        f1.setResizable(false);
        f1.setUndecorated(true);
        f1.setLayout(new BorderLayout());
        
        
        panel.setBounds(0,0,300,120);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, true));
        panel.setLayout(null);
        panel.setBackground(new Color(245, 245, 220));
        
        
        t1.setText("new game loading...");
        t1.setFont(new Font("Arial", Font.BOLD, 18));
        t1.setBounds(60,20, 250, 30); 
        
        bar=new JProgressBar(SwingConstants.HORIZONTAL,0,100);
        bar.setBounds(75, 75, 150, 30);
        bar.setStringPainted(true);
        bar.setForeground(Color.BLUE);
        bar.setValue(0);
        bar.setAlignmentX(Component.CENTER_ALIGNMENT);
       
        

        panel.add(t1);
        panel.add(bar);
        f1.add(panel, BorderLayout.CENTER);
        f1.setVisible(true);
        
        }
        
    public void AssignTurn(javax.swing.JButton btn){
        b1.setBackground(java.awt.Color.black);
        b2.setBackground(java.awt.Color.black);
        b6.setBackground(java.awt.Color.black);
        b3.setBackground(java.awt.Color.black);
        b4.setBackground(java.awt.Color.black);
        b5.setBackground(java.awt.Color.black);
        b7.setBackground(java.awt.Color.black);
        b8.setBackground(java.awt.Color.black);
        b9.setBackground(java.awt.Color.black);
        
        btn.setBackground(java.awt.Color.PINK);
        select=btn.getText();
    }
    
    
    private void SeeSolution(){
        int x = this.getX();  
        int y = this.getY();
        if(globalFlag==true){
            globalFlag=false;
            solution.setText("HIDE SOLUTION");

        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
            if(predefinedButtons[i][j]==false){
                board[i][j].setText(String.valueOf(sol[i][j]));
                board[i][j].setForeground(Color.BLUE);
                board[i][j].setBackground(Color.LIGHT_GRAY);
           }
        }
    }
        
    this.setSize(735, 900);    
    this.setLocation(x, y);
    }
    else{
    globalFlag=true;
    solution.setText("SEE SOLUTION");
    Reset();
    }      }
    
    
    
    public char[][] helper(JButton[][] board){
        char [][] val=new char[9][9];
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(board[i][j].getText().equals("")){
                val[i][j]='.';
                }
                else{
                val[i][j]=(board[i][j].getText()).charAt(0);
                }
            }
        }        
        
    return val;
    }
    
    public boolean solve(char [][] board, int row, int col){
    if(row==9){return true;}
    
    int nextRow=row, nextCol=col+1;
    if(nextCol==9){
    nextRow=row+1;
    nextCol=0;
    }
    
    if(board[row][col]!='.'){
        return(solve(board, nextRow, nextCol));
    }
    
    for(char i='1'; i<='9'; i++){
    if(isSafe(board, row, col, i)){
        board[row][col]=i;
        if(solve(board, nextRow, nextCol)){return true;}
        board[row][col]='.';
    }
    
    }
    
    return false;
    }
    
    private boolean isSafe(char[][] board, int row, int col, char dig){
        for(int i=0; i<9; i++){
        if(board[row][i]==dig){return false;}
        }
        
        for(int i=0; i<9; i++){
        if(board[i][col]==dig){return false;}
        }
        
        int sr=(row/3)*3;
        int sc=(col/3)*3;
        
        for(int i=sr; i<=sr+2; i++){
            for(int j=sc; j<=sc+2; j++){
                if(board[i][j]==dig){
                    return false;}
            }
        }
    return true;
        }
    
    
    private void check(){
        
        if(checkFlag){
        checkFlag=false;
        check.setText("RETURN");
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(!predefinedButtons[i][j] && !board[i][j].getText().equals("")){
                    if(!board[i][j].getText().equals(String.valueOf(sol[i][j]))
                        ){
                        board[i][j].setBackground(Color.RED);
                    }
                    else{board[i][j].setBackground(Color.GREEN);
                    }
                }
                
        }
    }
        }
        
        else{
            checkFlag=true;
            check.setText("CHECK MOVES");
            for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(!predefinedButtons[i][j] && !board[i][j].getText().equals("")){
                board[i][j].setForeground(Color.BLACK);
                board[i][j].setBackground(Color.ORANGE);
                }
            }
        }
        
    }
    }
    
    private void mapping(){
    // Row 0
    board[0][0] = B1;  board[0][1] = B2;  board[0][2] = B3;  
    board[0][3] = B4;  board[0][4] = B5;  board[0][5] = B6;  
    board[0][6] = B7;  board[0][7] = B8;  board[0][8] = B9;
    
    // Row 1
    board[1][0] = B10; board[1][1] = B11; board[1][2] = B12;     
    board[1][3] = B13; board[1][4] = B14; board[1][5] = B15; 
    board[1][6] = B16; board[1][7] = B17; board[1][8] = B18;
    
    // Row 2
    board[2][0] = B19; board[2][1] = B20; board[2][2] = B21;
    board[2][3] = B22; board[2][4] = B23; board[2][5] = B24;
    board[2][6] = B25; board[2][7] = B26; board[2][8] = B27;
    
    // Row 3
    board[3][0] = B28; board[3][1] = B29; board[3][2] = B30;
    board[3][3] = b31; board[3][4] = B32; board[3][5] = B33;
    board[3][6] = B34; board[3][7] = B35; board[3][8] = B36;
    
    // Row 4
    board[4][0] = B37; board[4][1] = B38; board[4][2] = B39;
    board[4][3] = B40; board[4][4] = B41; board[4][5] = B42;
    board[4][6] = B43; board[4][7] = B44; board[4][8] = B45;
    
    // Row 5
    board[5][0] = B46; board[5][1] = B47; board[5][2] = B48;
    board[5][3] = B49; board[5][4] = B50; board[5][5] = B51;
    board[5][6] = B52; board[5][7] = B53; board[5][8] = B54;
    
    // Row 6
    board[6][0] = B55; board[6][1] = B56; board[6][2] = B57;
    board[6][3] = B58; board[6][4] = B59; board[6][5] = B60;
    board[6][6] = B61; board[6][7] = B62; board[6][8] = B63;
    
    // Row 7
    board[7][0] = B64; board[7][1] = B65; board[7][2] = B66;
    board[7][3] = B67; board[7][4] = B68; board[7][5] = B69;
    board[7][6] = B70; board[7][7] = B71; board[7][8] = B72;
    
    // Row 8
    board[8][0] = B73; board[8][1] = B74; board[8][2] = B75;
    board[8][3] = B76; board[8][4] = B77; board[8][5] = B78;
    board[8][6] = B79; board[8][7] = B80; board[8][8] = B81;
    
    
    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
            if (board[i][j] != null) {
                board[i][j].putClientProperty("row", i);
                board[i][j].putClientProperty("col", j);
            }
        }
    }
}
    
    public void handleBoardClick(java.awt.event.ActionEvent evt){
    javax.swing.JButton clicked=(javax.swing.JButton) evt.getSource();
    
    int row=(int)clicked.getClientProperty("row");
    int col=(int)clicked.getClientProperty("col");
    if(predefinedButtons[row][col]){
        javax.swing.JOptionPane.showMessageDialog(this, "This place is already allocated.", "WARNING!", javax.swing.JOptionPane.INFORMATION_MESSAGE);}
    
    else{
    clicked.setText(select);
    values[row][col]=select.charAt(0);
   
    clicked.setBackground(java.awt.Color.ORANGE);
    clicked.setForeground(java.awt.Color.BLUE);
    }
    }
    
    public void Reset(){
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(predefinedButtons[i][j]==false){
                board[i][j].setText("");
                board[i][j].setForeground(Color.BLACK);
                board[i][j].setBackground(Color.LIGHT_GRAY);                }
            }
        }
         AssignTurn(b1);    
        
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    
   public void fetchApi() {
    Reset();
    Loading_page();  // ✅ function call
    
    SwingWorker<Void, Integer> worker = new SwingWorker<>() {
        
        @Override
        protected Void doInBackground() throws Exception {
            publish(10);
            Thread.sleep(500);
            
            String api = "https://sudoku-api.vercel.app/api/dosuku?query={newboard(limit:200){grids{value,solution,difficulty},results,message}}";
            URL url = new URL(api);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            
            publish(30);
            Thread.sleep(500);
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            
            publish(60);
            Thread.sleep(500);
            
            JsonObject root = JsonParser.parseString(response.toString()).getAsJsonObject();
            JsonArray grid = root
                .getAsJsonObject("newboard")
                .getAsJsonArray("grids")
                .get(0).getAsJsonObject()
                .getAsJsonArray("value");
            
            publish(80);
            Thread.sleep(200);
            java.awt.EventQueue.invokeLater(() -> {
                for (int i = 0; i < 9; i++) {
                    var row = grid.get(i).getAsJsonArray();
                    for (int j = 0; j < 9; j++) {
                        int val = row.get(j).getAsInt();
                        if (val != 0) {
                            board[i][j].setText(String.valueOf(val));
                            values[i][j] = (char)(val + '0');
                            predefinedButtons[i][j] = true;
                            board[i][j].setBackground(new Color(197, 235, 186));
                        } else {
                            board[i][j].setText("");
                            board[i][j].setBackground(Color.LIGHT_GRAY);
                            predefinedButtons[i][j] = false;
                            values[i][j] = '.';
                        }
                    }
                }
                sol = helper(board);
                if (solve(sol, 0, 0)) System.out.println("Solution found!");
                else System.out.println("Solution does not exist!");
            });
            
            publish(100);
            Thread.sleep(1000);
            return null;
        }
        
        @Override
        protected void process(java.util.List<Integer> chunks) {
            bar.setValue(chunks.get(chunks.size() - 1)); // ✅ directly access
        }
        
        @Override
        protected void done() {
            f1.dispose(); // ✅ directly access
        }
    };
    
    worker.execute();
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel105 = new javax.swing.JPanel();
        jPanel110 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jButton1 = new javax.swing.JButton();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        jPopupMenu4 = new javax.swing.JPopupMenu();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel1 = new javax.swing.JPanel();
        B28 = new javax.swing.JButton();
        B37 = new javax.swing.JButton();
        B30 = new javax.swing.JButton();
        B29 = new javax.swing.JButton();
        B38 = new javax.swing.JButton();
        B39 = new javax.swing.JButton();
        B47 = new javax.swing.JButton();
        B48 = new javax.swing.JButton();
        B46 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        B55 = new javax.swing.JButton();
        B64 = new javax.swing.JButton();
        B57 = new javax.swing.JButton();
        B56 = new javax.swing.JButton();
        B65 = new javax.swing.JButton();
        B66 = new javax.swing.JButton();
        B74 = new javax.swing.JButton();
        B75 = new javax.swing.JButton();
        B73 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        B31 = new javax.swing.JButton();
        B40 = new javax.swing.JButton();
        B33 = new javax.swing.JButton();
        B32 = new javax.swing.JButton();
        B41 = new javax.swing.JButton();
        B42 = new javax.swing.JButton();
        B50 = new javax.swing.JButton();
        B51 = new javax.swing.JButton();
        B49 = new javax.swing.JButton();
        b31 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        B1 = new javax.swing.JButton();
        B10 = new javax.swing.JButton();
        B3 = new javax.swing.JButton();
        B2 = new javax.swing.JButton();
        B11 = new javax.swing.JButton();
        B12 = new javax.swing.JButton();
        B20 = new javax.swing.JButton();
        B21 = new javax.swing.JButton();
        B19 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        B4 = new javax.swing.JButton();
        B13 = new javax.swing.JButton();
        B6 = new javax.swing.JButton();
        B5 = new javax.swing.JButton();
        B14 = new javax.swing.JButton();
        B15 = new javax.swing.JButton();
        B23 = new javax.swing.JButton();
        B24 = new javax.swing.JButton();
        B22 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        B7 = new javax.swing.JButton();
        B16 = new javax.swing.JButton();
        B9 = new javax.swing.JButton();
        B8 = new javax.swing.JButton();
        B17 = new javax.swing.JButton();
        B18 = new javax.swing.JButton();
        B26 = new javax.swing.JButton();
        B27 = new javax.swing.JButton();
        B25 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        B34 = new javax.swing.JButton();
        B43 = new javax.swing.JButton();
        B36 = new javax.swing.JButton();
        B35 = new javax.swing.JButton();
        B44 = new javax.swing.JButton();
        B45 = new javax.swing.JButton();
        B53 = new javax.swing.JButton();
        B54 = new javax.swing.JButton();
        B52 = new javax.swing.JButton();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jPanel12 = new javax.swing.JPanel();
        B58 = new javax.swing.JButton();
        B67 = new javax.swing.JButton();
        B60 = new javax.swing.JButton();
        B59 = new javax.swing.JButton();
        B68 = new javax.swing.JButton();
        B69 = new javax.swing.JButton();
        B77 = new javax.swing.JButton();
        B78 = new javax.swing.JButton();
        B76 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        B61 = new javax.swing.JButton();
        B70 = new javax.swing.JButton();
        B63 = new javax.swing.JButton();
        B62 = new javax.swing.JButton();
        B71 = new javax.swing.JButton();
        B72 = new javax.swing.JButton();
        B80 = new javax.swing.JButton();
        B81 = new javax.swing.JButton();
        B79 = new javax.swing.JButton();
        b1 = new javax.swing.JButton();
        b2 = new javax.swing.JButton();
        b9 = new javax.swing.JButton();
        b8 = new javax.swing.JButton();
        b3 = new javax.swing.JButton();
        b4 = new javax.swing.JButton();
        b5 = new javax.swing.JButton();
        b6 = new javax.swing.JButton();
        b7 = new javax.swing.JButton();
        reset = new javax.swing.JButton();
        exit = new javax.swing.JButton();
        solution = new javax.swing.JButton();
        check = new javax.swing.JButton();
        NEW = new javax.swing.JButton();

        jPanel105.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel105Layout = new javax.swing.GroupLayout(jPanel105);
        jPanel105.setLayout(jPanel105Layout);
        jPanel105Layout.setHorizontalGroup(
            jPanel105Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );
        jPanel105Layout.setVerticalGroup(
            jPanel105Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );

        jPanel110.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel110Layout = new javax.swing.GroupLayout(jPanel110);
        jPanel110.setLayout(jPanel110Layout);
        jPanel110Layout.setHorizontalGroup(
            jPanel110Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );
        jPanel110Layout.setVerticalGroup(
            jPanel110Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jButton1.setText("jButton1");

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(204, 205, 186));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(700, 900));
        setSize(new java.awt.Dimension(700, 900));

        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(250, 250));

        B28.setBackground(new java.awt.Color(197, 235, 186));
        B28.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B28.setText("8");
        B28.addActionListener(this::B28ActionPerformed);

        B37.setBackground(new java.awt.Color(197, 235, 186));
        B37.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B37.setText("4");
        B37.addActionListener(this::B37ActionPerformed);

        B30.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B30.addActionListener(this::B30ActionPerformed);

        B29.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B29.addActionListener(this::B29ActionPerformed);

        B38.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B38.addActionListener(this::B38ActionPerformed);

        B39.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B39.addActionListener(this::B39ActionPerformed);

        B47.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B47.addActionListener(this::B47ActionPerformed);

        B48.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B48.addActionListener(this::B48ActionPerformed);

        B46.setBackground(new java.awt.Color(197, 235, 186));
        B46.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B46.setText("7");
        B46.addActionListener(this::B46ActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(B46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B37, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B28, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(B38, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(B29, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(B47, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(B30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B39, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B48, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(B28, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                    .addComponent(B29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(B38, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(B39, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(B47, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                            .addComponent(B48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(B37, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B46, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        jPanel2.setPreferredSize(new java.awt.Dimension(250, 250));

        B55.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B55.addActionListener(this::B55ActionPerformed);

        B64.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B64.addActionListener(this::B64ActionPerformed);

        B57.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B57.addActionListener(this::B57ActionPerformed);

        B56.setBackground(new java.awt.Color(197, 235, 186));
        B56.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B56.setText("6");
        B56.addActionListener(this::B56ActionPerformed);

        B65.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B65.addActionListener(this::B65ActionPerformed);

        B66.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B66.addActionListener(this::B66ActionPerformed);

        B74.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B74.addActionListener(this::B74ActionPerformed);

        B75.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B75.addActionListener(this::B75ActionPerformed);

        B73.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B73.addActionListener(this::B73ActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(B64, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B55, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B73, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(B65, javax.swing.GroupLayout.PREFERRED_SIZE, 67, Short.MAX_VALUE)
                            .addComponent(B74, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(B75, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(B66, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(B56, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(B57, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(B55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B56, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                    .addComponent(B57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(B66, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                    .addComponent(B64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(B73, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                    .addComponent(B75, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        jPanel6.setMaximumSize(new java.awt.Dimension(65, 65));
        jPanel6.setMinimumSize(new java.awt.Dimension(65, 65));
        jPanel6.setPreferredSize(new java.awt.Dimension(250, 250));
        jPanel6.setRequestFocusEnabled(false);

        B31.addActionListener(this::B31ActionPerformed);

        B40.setBackground(new java.awt.Color(197, 235, 186));
        B40.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B40.setText("8");
        B40.addActionListener(this::B40ActionPerformed);

        B33.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B33.addActionListener(this::B33ActionPerformed);

        B32.setBackground(new java.awt.Color(197, 235, 186));
        B32.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B32.setText("6");
        B32.addActionListener(this::B32ActionPerformed);

        B41.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B41.addActionListener(this::B41ActionPerformed);

        B42.setBackground(new java.awt.Color(197, 235, 186));
        B42.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B42.setText("3");
        B42.addActionListener(this::B42ActionPerformed);

        B50.setBackground(new java.awt.Color(197, 235, 186));
        B50.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B50.setText("2");
        B50.addActionListener(this::B50ActionPerformed);

        B51.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B51.addActionListener(this::B51ActionPerformed);

        B49.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B49.addActionListener(this::B49ActionPerformed);

        b31.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        b31.addActionListener(this::b31ActionPerformed);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(B49, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B50, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(B51, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(B41, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addComponent(B31, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b31, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(B32, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(B40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(75, 75, 75)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(B42, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B33, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(B50, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(B31, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(B33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(B32, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(B40, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(B41, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(B42, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(B49, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(B51, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        jPanel7.setPreferredSize(new java.awt.Dimension(200, 200));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        B1.setBackground(new java.awt.Color(197, 235, 186));
        B1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B1.setText("5");
        B1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        B1.setMaximumSize(new java.awt.Dimension(65, 65));
        B1.setMinimumSize(new java.awt.Dimension(65, 65));
        B1.setPreferredSize(new java.awt.Dimension(65, 65));
        B1.addActionListener(this::B1ActionPerformed);
        jPanel7.add(B1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 65, 65));

        B10.setBackground(new java.awt.Color(197, 235, 186));
        B10.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B10.setText("6");
        B10.setMargin(new java.awt.Insets(0, 0, 0, 0));
        B10.setPreferredSize(new java.awt.Dimension(65, 65));
        B10.addActionListener(this::B10ActionPerformed);
        jPanel7.add(B10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        B3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B3.setPreferredSize(new java.awt.Dimension(65, 65));
        B3.addActionListener(this::B3ActionPerformed);
        jPanel7.add(B3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, -1, -1));

        B2.setBackground(new java.awt.Color(197, 235, 186));
        B2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B2.setText("3");
        B2.setAutoscrolls(true);
        B2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        B2.setMaximumSize(new java.awt.Dimension(65, 65));
        B2.setMinimumSize(new java.awt.Dimension(65, 65));
        B2.setPreferredSize(new java.awt.Dimension(65, 65));
        B2.addActionListener(this::B2ActionPerformed);
        jPanel7.add(B2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 65, 65));

        B11.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B11.setPreferredSize(new java.awt.Dimension(65, 65));
        B11.addActionListener(this::B11ActionPerformed);
        jPanel7.add(B11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, -1, -1));

        B12.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B12.setPreferredSize(new java.awt.Dimension(65, 65));
        B12.addActionListener(this::B12ActionPerformed);
        jPanel7.add(B12, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, -1, -1));

        B20.setBackground(new java.awt.Color(197, 235, 186));
        B20.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B20.setText("9");
        B20.addActionListener(this::B20ActionPerformed);
        jPanel7.add(B20, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 65, 65));

        B21.setBackground(new java.awt.Color(197, 235, 186));
        B21.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B21.setText("8");
        B21.addActionListener(this::B21ActionPerformed);
        jPanel7.add(B21, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 65, 65));

        B19.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B19.addActionListener(this::B19ActionPerformed);
        jPanel7.add(B19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 65, 65));

        jPanel9.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        B4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        B4.setMaximumSize(new java.awt.Dimension(65, 65));
        B4.setMinimumSize(new java.awt.Dimension(65, 65));
        B4.setPreferredSize(new java.awt.Dimension(65, 65));
        B4.addActionListener(this::B4ActionPerformed);

        B13.setBackground(new java.awt.Color(197, 235, 186));
        B13.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B13.setText("1");
        B13.addActionListener(this::B13ActionPerformed);

        B6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B6.setPreferredSize(new java.awt.Dimension(65, 65));
        B6.addActionListener(this::B6ActionPerformed);

        B5.setBackground(new java.awt.Color(197, 235, 186));
        B5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B5.setText("7");
        B5.setPreferredSize(new java.awt.Dimension(65, 65));
        B5.addActionListener(this::B5ActionPerformed);

        B14.setBackground(new java.awt.Color(197, 235, 186));
        B14.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B14.setText("9");
        B14.setPreferredSize(new java.awt.Dimension(65, 65));
        B14.addActionListener(this::B14ActionPerformed);

        B15.setBackground(new java.awt.Color(197, 235, 186));
        B15.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B15.setText("5");
        B15.setMaximumSize(new java.awt.Dimension(65, 65));
        B15.setMinimumSize(new java.awt.Dimension(65, 65));
        B15.setPreferredSize(new java.awt.Dimension(65, 65));
        B15.addActionListener(this::B15ActionPerformed);

        B23.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B23.addActionListener(this::B23ActionPerformed);

        B24.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B24.addActionListener(this::B24ActionPerformed);

        B22.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B22.addActionListener(this::B22ActionPerformed);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(B4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B22, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(B14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B23, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(B15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B24, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(B5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(B13, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(B14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(B15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(B23, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                    .addComponent(B24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        jPanel10.setPreferredSize(new java.awt.Dimension(250, 250));

        B7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B7.setPreferredSize(new java.awt.Dimension(65, 65));
        B7.addActionListener(this::B7ActionPerformed);

        B16.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B16.addActionListener(this::B16ActionPerformed);

        B9.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B9.addActionListener(this::B9ActionPerformed);

        B8.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B8.addActionListener(this::B8ActionPerformed);

        B17.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B17.addActionListener(this::B17ActionPerformed);

        B18.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B18.addActionListener(this::B18ActionPerformed);

        B26.setBackground(new java.awt.Color(197, 235, 186));
        B26.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B26.setText("6");
        B26.addActionListener(this::B26ActionPerformed);

        B27.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B27.addActionListener(this::B27ActionPerformed);

        B25.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B25.addActionListener(this::B25ActionPerformed);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(B16, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(B25, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(B7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(B8, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(B26, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(B17, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(B18, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B9, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B27, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(B7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(B16, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(B18, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(B17, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(B27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(B26, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(B25, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        jPanel11.setPreferredSize(new java.awt.Dimension(250, 250));

        B34.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B34.addActionListener(this::B34ActionPerformed);

        B43.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B43.addActionListener(this::B43ActionPerformed);

        B36.setBackground(new java.awt.Color(197, 235, 186));
        B36.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B36.setText("3");
        B36.addActionListener(this::B36ActionPerformed);

        B35.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B35.addActionListener(this::B35ActionPerformed);

        B44.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B44.addActionListener(this::B44ActionPerformed);

        B45.setBackground(new java.awt.Color(197, 235, 186));
        B45.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B45.setText("1");
        B45.addActionListener(this::B45ActionPerformed);

        B53.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B53.addActionListener(this::B53ActionPerformed);

        B54.setBackground(new java.awt.Color(197, 235, 186));
        B54.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B54.setText("6");
        B54.addActionListener(this::B54ActionPerformed);

        B52.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B52.addActionListener(this::B52ActionPerformed);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(B34, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B43, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(B52, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(B44, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(B53, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B35, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(B45, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(B36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(B34, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                            .addComponent(B35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(B36, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(B43, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B44, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B45, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(B53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B54, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        jPanel12.setPreferredSize(new java.awt.Dimension(250, 250));

        B58.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B58.addActionListener(this::B58ActionPerformed);

        B67.setBackground(new java.awt.Color(197, 235, 186));
        B67.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B67.setText("4");
        B67.addActionListener(this::B67ActionPerformed);

        B60.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B60.addActionListener(this::B60ActionPerformed);

        B59.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B59.addActionListener(this::B59ActionPerformed);

        B68.setBackground(new java.awt.Color(197, 235, 186));
        B68.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B68.setText("1");
        B68.addActionListener(this::B68ActionPerformed);

        B69.setBackground(new java.awt.Color(197, 235, 186));
        B69.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B69.setText("9");
        B69.addActionListener(this::B69ActionPerformed);

        B77.setBackground(new java.awt.Color(197, 235, 186));
        B77.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B77.setText("8");
        B77.addActionListener(this::B77ActionPerformed);

        B78.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B78.addActionListener(this::B78ActionPerformed);

        B76.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B76.addActionListener(this::B76ActionPerformed);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(B76, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(B77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B68, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B59, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(B60, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B78, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(9, 9, 9))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(B59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B58, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(B69, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(B67, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(B68, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(B77, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                    .addComponent(B76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        jPanel13.setPreferredSize(new java.awt.Dimension(250, 250));

        B61.setBackground(new java.awt.Color(197, 235, 186));
        B61.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B61.setText("2");
        B61.addActionListener(this::B61ActionPerformed);

        B70.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B70.addActionListener(this::B70ActionPerformed);

        B63.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B63.addActionListener(this::B63ActionPerformed);

        B62.setBackground(new java.awt.Color(197, 235, 186));
        B62.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B62.setText("8");
        B62.addActionListener(this::B62ActionPerformed);

        B71.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B71.addActionListener(this::B71ActionPerformed);

        B72.setBackground(new java.awt.Color(197, 235, 186));
        B72.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B72.setText("5");
        B72.addActionListener(this::B72ActionPerformed);

        B80.setBackground(new java.awt.Color(197, 235, 186));
        B80.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B80.setText("7");
        B80.addActionListener(this::B80ActionPerformed);

        B81.setBackground(new java.awt.Color(197, 235, 186));
        B81.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B81.setText("9");
        B81.addActionListener(this::B81ActionPerformed);

        B79.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        B79.addActionListener(this::B79ActionPerformed);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(B61, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                    .addComponent(B70, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(B79, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(B62, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                    .addComponent(B71, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(B80, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(B81, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                    .addComponent(B63, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(B72, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(B63, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B62, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                    .addComponent(B61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(B70, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B79, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(B72, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B81, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(B71, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B80, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        b1.setBackground(new java.awt.Color(0, 0, 0));
        b1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        b1.setForeground(new java.awt.Color(255, 255, 255));
        b1.setText("1");
        b1.addActionListener(this::b1ActionPerformed);

        b2.setBackground(new java.awt.Color(0, 0, 0));
        b2.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        b2.setForeground(new java.awt.Color(255, 255, 255));
        b2.setText("2");
        b2.addActionListener(this::b2ActionPerformed);

        b9.setBackground(new java.awt.Color(0, 0, 0));
        b9.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        b9.setForeground(new java.awt.Color(255, 255, 255));
        b9.setText("9");
        b9.addActionListener(this::b9ActionPerformed);

        b8.setBackground(new java.awt.Color(0, 0, 0));
        b8.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        b8.setForeground(new java.awt.Color(255, 255, 255));
        b8.setText("8");
        b8.addActionListener(this::b8ActionPerformed);

        b3.setBackground(new java.awt.Color(0, 0, 0));
        b3.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        b3.setForeground(new java.awt.Color(255, 255, 255));
        b3.setText("3");
        b3.addActionListener(this::b3ActionPerformed);

        b4.setBackground(new java.awt.Color(0, 0, 0));
        b4.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        b4.setForeground(new java.awt.Color(255, 255, 255));
        b4.setText("4");
        b4.addActionListener(this::b4ActionPerformed);

        b5.setBackground(new java.awt.Color(0, 0, 0));
        b5.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        b5.setForeground(new java.awt.Color(255, 255, 255));
        b5.setText("5");
        b5.addActionListener(this::b5ActionPerformed);

        b6.setBackground(new java.awt.Color(0, 0, 0));
        b6.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        b6.setForeground(new java.awt.Color(255, 255, 255));
        b6.setText("6");
        b6.addActionListener(this::b6ActionPerformed);

        b7.setBackground(new java.awt.Color(0, 0, 0));
        b7.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        b7.setForeground(new java.awt.Color(255, 255, 255));
        b7.setText("7");
        b7.addActionListener(this::b7ActionPerformed);

        reset.setBackground(new java.awt.Color(255, 204, 204));
        reset.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        reset.setText("RESET");
        reset.addActionListener(this::resetActionPerformed);

        exit.setBackground(new java.awt.Color(204, 204, 255));
        exit.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        exit.setText("EXIT");
        exit.addActionListener(this::exitActionPerformed);

        solution.setBackground(new java.awt.Color(51, 204, 255));
        solution.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        solution.setText("SEE SOLUTION");
        solution.addActionListener(this::solutionActionPerformed);

        check.setBackground(new java.awt.Color(153, 204, 255));
        check.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        check.setText("CHECK MOVE");
        check.addActionListener(this::checkActionPerformed);

        NEW.setBackground(new java.awt.Color(237, 221, 189));
        NEW.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        NEW.setText("NEW GAME");
        NEW.addActionListener(this::NEWActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(b3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b5, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b7, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b8, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b9, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(NEW, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(solution)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(check, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(b9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(b3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(b5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(b6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(b7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(b8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(reset)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NEW, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(solution, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(check)
                            .addComponent(exit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void B28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B28ActionPerformed
           handleBoardClick(evt);
        
    }//GEN-LAST:event_B28ActionPerformed

    private void B37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B37ActionPerformed
         handleBoardClick(evt);
    }//GEN-LAST:event_B37ActionPerformed

    private void B30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B30ActionPerformed
         handleBoardClick(evt);
    }//GEN-LAST:event_B30ActionPerformed

    private void B29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B29ActionPerformed
         handleBoardClick(evt);
    }//GEN-LAST:event_B29ActionPerformed

    private void B38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B38ActionPerformed
         handleBoardClick(evt);
    }//GEN-LAST:event_B38ActionPerformed

    private void B39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B39ActionPerformed
         handleBoardClick(evt);
    }//GEN-LAST:event_B39ActionPerformed

    private void B47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B47ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B47ActionPerformed

    private void B48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B48ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B48ActionPerformed

    private void B46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B46ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B46ActionPerformed

    private void B31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B31ActionPerformed
        // TODO add your handling code here:
         //handleBoardClick(evt);
    }//GEN-LAST:event_B31ActionPerformed

    private void B40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B40ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B40ActionPerformed

    private void B33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B33ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B33ActionPerformed

    private void B32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B32ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B32ActionPerformed

    private void B41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B41ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B41ActionPerformed

    private void B42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B42ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B42ActionPerformed

    private void B50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B50ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B50ActionPerformed

    private void B51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B51ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B51ActionPerformed

    private void B49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B49ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B49ActionPerformed

    private void B1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B1ActionPerformed
        handleBoardClick(evt);
    }//GEN-LAST:event_B1ActionPerformed

    private void B10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B10ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B10ActionPerformed

    private void B3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B3ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B3ActionPerformed

    private void B2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B2ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B2ActionPerformed

    private void B11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B11ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B11ActionPerformed

    private void B12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B12ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B12ActionPerformed

    private void B20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B20ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B20ActionPerformed

    private void B21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B21ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B21ActionPerformed

    private void B19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B19ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B19ActionPerformed

    private void B4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B4ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B4ActionPerformed

    private void B13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B13ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B13ActionPerformed

    private void B6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B6ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B6ActionPerformed

    private void B5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B5ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B5ActionPerformed

    private void B14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B14ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B14ActionPerformed

    private void B15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B15ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B15ActionPerformed

    private void B23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B23ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B23ActionPerformed

    private void B24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B24ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B24ActionPerformed

    private void B22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B22ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B22ActionPerformed

    private void B7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B7ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B7ActionPerformed

    private void B16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B16ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B16ActionPerformed

    private void B9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B9ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B9ActionPerformed

    private void B8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B8ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B8ActionPerformed

    private void B17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B17ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B17ActionPerformed

    private void B18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B18ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B18ActionPerformed

    private void B26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B26ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B26ActionPerformed

    private void B27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B27ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B27ActionPerformed

    private void B25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B25ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B25ActionPerformed

    private void B58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B58ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B58ActionPerformed

    private void B67ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B67ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B67ActionPerformed

    private void B60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B60ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B60ActionPerformed

    private void B59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B59ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B59ActionPerformed

    private void B68ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B68ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B68ActionPerformed

    private void B69ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B69ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B69ActionPerformed

    private void B77ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B77ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B77ActionPerformed

    private void B78ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B78ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B78ActionPerformed

    private void B76ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B76ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B76ActionPerformed

    private void B55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B55ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B55ActionPerformed

    private void B64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B64ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B64ActionPerformed

    private void B57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B57ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B57ActionPerformed

    private void B56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B56ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B56ActionPerformed

    private void B65ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B65ActionPerformed
        // TODO add your handling code here: 
        handleBoardClick(evt);
        
    }//GEN-LAST:event_B65ActionPerformed

    private void B66ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B66ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B66ActionPerformed

    private void B74ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B74ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B74ActionPerformed

    private void B75ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B75ActionPerformed
        // TODO add your handling code here:
        
         handleBoardClick(evt);
    }//GEN-LAST:event_B75ActionPerformed

    private void B73ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B73ActionPerformed
        // TODO add your handling code here:
         handleBoardClick(evt);
    }//GEN-LAST:event_B73ActionPerformed

    
    
    
    
    //option buttons
    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        // TODO add your handling code here:
        AssignTurn(b1);
        select="1";      
    }//GEN-LAST:event_b1ActionPerformed

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
        // TODO add your handling code here:
        AssignTurn(b2);
        select="2";
    }//GEN-LAST:event_b2ActionPerformed

    private void b9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b9ActionPerformed
        // TODO add your handling code here:
        AssignTurn(b9);
        select="9";
    }//GEN-LAST:event_b9ActionPerformed

    private void b8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b8ActionPerformed
        // TODO add your handling code here:
        AssignTurn(b8);
        select="8";
    }//GEN-LAST:event_b8ActionPerformed

    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b3ActionPerformed
        // TODO add your handling code here:
        AssignTurn(b3);
        select="3";
    }//GEN-LAST:event_b3ActionPerformed

    private void b4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b4ActionPerformed
        // TODO add your handling code here:
        AssignTurn(b4);
        select="4";
    }//GEN-LAST:event_b4ActionPerformed

    private void b5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b5ActionPerformed
        // TODO add your handling code here:
        AssignTurn(b5);
        select="5";
    }//GEN-LAST:event_b5ActionPerformed

    private void b6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b6ActionPerformed
        // TODO add your handling code here:
        AssignTurn(b6);
        select="6";
    }//GEN-LAST:event_b6ActionPerformed

    private void b7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b7ActionPerformed
        // TODO add your handling code here:
        AssignTurn(b7);
        select="7";
    }//GEN-LAST:event_b7ActionPerformed

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        // TODO add your handling code here:
        JFrame frame= new JFrame("RESET!");
        frame.add(f1);
        if(JOptionPane.showConfirmDialog(frame, "Do you really wish to reset?", "RESET",javax.swing.JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
        Reset();} 
       
    }//GEN-LAST:event_resetActionPerformed

    private void solutionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solutionActionPerformed
        // TODO add your handling code here:
        SeeSolution();
    }//GEN-LAST:event_solutionActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        // TODO add your handling code here:
        JFrame frame=new JFrame("EXIT!");
        if(JOptionPane.showConfirmDialog(frame, "Do you wish to exit?", "EXIT", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
        System.exit(0);
        }
        
    }//GEN-LAST:event_exitActionPerformed

    private void checkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkActionPerformed
        // TODO add your handling code here:
        check();        
    }//GEN-LAST:event_checkActionPerformed

    private void b31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b31ActionPerformed
        // TODO add your handling code here:
        handleBoardClick(evt);
    }//GEN-LAST:event_b31ActionPerformed

    private void B79ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B79ActionPerformed
        // TODO add your handling code here:
        handleBoardClick(evt);
    }//GEN-LAST:event_B79ActionPerformed

    private void B81ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B81ActionPerformed
        // TODO add your handling code here:
        handleBoardClick(evt);
    }//GEN-LAST:event_B81ActionPerformed

    private void B80ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B80ActionPerformed
        // TODO add your handling code here:
        handleBoardClick(evt);
    }//GEN-LAST:event_B80ActionPerformed

    private void B72ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B72ActionPerformed
        // TODO add your handling code here:
        handleBoardClick(evt);
    }//GEN-LAST:event_B72ActionPerformed

    private void B71ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B71ActionPerformed
        // TODO add your handling code here:
        handleBoardClick(evt);
    }//GEN-LAST:event_B71ActionPerformed

    private void B62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B62ActionPerformed
        // TODO add your handling code here:
        handleBoardClick(evt);
    }//GEN-LAST:event_B62ActionPerformed

    private void B63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B63ActionPerformed
        // TODO add your handling code here:
        handleBoardClick(evt);
    }//GEN-LAST:event_B63ActionPerformed

    private void B70ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B70ActionPerformed
        // TODO add your handling code here:
        handleBoardClick(evt);
    }//GEN-LAST:event_B70ActionPerformed

    private void B61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B61ActionPerformed
        // TODO add your handling code here:
        handleBoardClick(evt);
    }//GEN-LAST:event_B61ActionPerformed

    private void NEWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NEWActionPerformed
        // TODO add your handling code here:
        fetchApi();
    }//GEN-LAST:event_NEWActionPerformed

    private void B52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B52ActionPerformed
        // TODO add your handling code here:
        handleBoardClick(evt);
    }//GEN-LAST:event_B52ActionPerformed

    private void B54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B54ActionPerformed
        // TODO add your handling code here:
        handleBoardClick(evt);
    }//GEN-LAST:event_B54ActionPerformed

    private void B53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B53ActionPerformed
        // TODO add your handling code here:
        handleBoardClick(evt);
    }//GEN-LAST:event_B53ActionPerformed

    private void B45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B45ActionPerformed
        // TODO add your handling code here:
        handleBoardClick(evt);
    }//GEN-LAST:event_B45ActionPerformed

    private void B44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B44ActionPerformed
        // TODO add your handling code here:
        handleBoardClick(evt);
    }//GEN-LAST:event_B44ActionPerformed

    private void B35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B35ActionPerformed
        // TODO add your handling code here:
        handleBoardClick(evt);
    }//GEN-LAST:event_B35ActionPerformed

    private void B36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B36ActionPerformed
        // TODO add your handling code here:
        handleBoardClick(evt);
    }//GEN-LAST:event_B36ActionPerformed

    private void B43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B43ActionPerformed
        // TODO add your handling code here:
        handleBoardClick(evt);
    }//GEN-LAST:event_B43ActionPerformed

    private void B34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B34ActionPerformed
        // TODO add your handling code here:
        handleBoardClick(evt);
    }//GEN-LAST:event_B34ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Sudoku().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B1;
    private javax.swing.JButton B10;
    private javax.swing.JButton B11;
    private javax.swing.JButton B12;
    private javax.swing.JButton B13;
    private javax.swing.JButton B14;
    private javax.swing.JButton B15;
    private javax.swing.JButton B16;
    private javax.swing.JButton B17;
    private javax.swing.JButton B18;
    private javax.swing.JButton B19;
    private javax.swing.JButton B2;
    private javax.swing.JButton B20;
    private javax.swing.JButton B21;
    private javax.swing.JButton B22;
    private javax.swing.JButton B23;
    private javax.swing.JButton B24;
    private javax.swing.JButton B25;
    private javax.swing.JButton B26;
    private javax.swing.JButton B27;
    private javax.swing.JButton B28;
    private javax.swing.JButton B29;
    private javax.swing.JButton B3;
    private javax.swing.JButton B30;
    private javax.swing.JButton B31;
    private javax.swing.JButton B32;
    private javax.swing.JButton B33;
    private javax.swing.JButton B34;
    private javax.swing.JButton B35;
    private javax.swing.JButton B36;
    private javax.swing.JButton B37;
    private javax.swing.JButton B38;
    private javax.swing.JButton B39;
    private javax.swing.JButton B4;
    private javax.swing.JButton B40;
    private javax.swing.JButton B41;
    private javax.swing.JButton B42;
    private javax.swing.JButton B43;
    private javax.swing.JButton B44;
    private javax.swing.JButton B45;
    private javax.swing.JButton B46;
    private javax.swing.JButton B47;
    private javax.swing.JButton B48;
    private javax.swing.JButton B49;
    private javax.swing.JButton B5;
    private javax.swing.JButton B50;
    private javax.swing.JButton B51;
    private javax.swing.JButton B52;
    private javax.swing.JButton B53;
    private javax.swing.JButton B54;
    private javax.swing.JButton B55;
    private javax.swing.JButton B56;
    private javax.swing.JButton B57;
    private javax.swing.JButton B58;
    private javax.swing.JButton B59;
    private javax.swing.JButton B6;
    private javax.swing.JButton B60;
    private javax.swing.JButton B61;
    private javax.swing.JButton B62;
    private javax.swing.JButton B63;
    private javax.swing.JButton B64;
    private javax.swing.JButton B65;
    private javax.swing.JButton B66;
    private javax.swing.JButton B67;
    private javax.swing.JButton B68;
    private javax.swing.JButton B69;
    private javax.swing.JButton B7;
    private javax.swing.JButton B70;
    private javax.swing.JButton B71;
    private javax.swing.JButton B72;
    private javax.swing.JButton B73;
    private javax.swing.JButton B74;
    private javax.swing.JButton B75;
    private javax.swing.JButton B76;
    private javax.swing.JButton B77;
    private javax.swing.JButton B78;
    private javax.swing.JButton B79;
    private javax.swing.JButton B8;
    private javax.swing.JButton B80;
    private javax.swing.JButton B81;
    private javax.swing.JButton B9;
    private javax.swing.JButton NEW;
    private javax.swing.JButton b1;
    private javax.swing.JButton b2;
    private javax.swing.JButton b3;
    private javax.swing.JButton b31;
    private javax.swing.JButton b4;
    private javax.swing.JButton b5;
    private javax.swing.JButton b6;
    private javax.swing.JButton b7;
    private javax.swing.JButton b8;
    private javax.swing.JButton b9;
    private javax.swing.JButton check;
    private javax.swing.JButton exit;
    private javax.swing.JButton jButton1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel105;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel110;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    private javax.swing.JPopupMenu jPopupMenu4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton reset;
    private javax.swing.JButton solution;
    // End of variables declaration//GEN-END:variables

}

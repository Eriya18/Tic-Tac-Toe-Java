import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class TicTacToe{
    int boardWidth=600;
    int boardHeight=650;

    JFrame frame=new JFrame("Tic-Tac-Toe");
    JLabel textLabel= new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JPanel bottomPanel =new JPanel();
    JButton restartButton =new JButton("Restart");

    JButton[][] board=new JButton[3][3];
    String PlayerX= "X";
    String Player0= "O";
    String currentPlayer= PlayerX;
    boolean gameOver= false;
    int turns=0;
    int scoreX= 0;
    int scoreO=0;
    JLabel scoreLabel=new JLabel("X: 0 | O: 0");

    TicTacToe(){
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(new Color(20, 20, 50));
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial",Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3,3 ));
        boardPanel.setBackground(new Color(30, 30, 60));
        frame.add(boardPanel);

        for(int r=0; r<3; r++){
            for(int c=0; c<3; c++){
                JButton tile=new JButton();
                board[r][c]=tile;
                boardPanel.add(tile);

                tile.setBackground(new Color(200, 230, 240));
                tile.setForeground(new Color(10, 40, 70));
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
                tile.setBorder(BorderFactory.createLineBorder(new Color(0, 180, 180), 3));
               // tile.setText(currentPlayer);
                
                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        if (gameOver) return;
                            JButton tile =(JButton)e.getSource();
                            if (tile.getText()=="") {
                                tile.setText(currentPlayer);
                                turns++;
                                checkWinner();
                                if(!gameOver){
                                    currentPlayer=currentPlayer==PlayerX ? Player0: PlayerX;
                                    textLabel.setText(currentPlayer+ "'s turn.");
                                    textLabel.setForeground(currentPlayer.equals(PlayerX)? Color.cyan : new Color(0,200, 150));
                                }
                             }
                        }
                }); 
            }
        }
        bottomPanel.setLayout(new GridLayout(2, 1));
        restartButton.setFont(new Font("Arial", Font.BOLD, 30));
        restartButton.setBackground(new Color(0, 160, 160));
        restartButton.setForeground(Color.white);
        restartButton.setBorder(BorderFactory.createLineBorder(new Color(0, 180, 180), 3));
        restartButton.addActionListener(e -> resetBoard());

        scoreLabel.setFont(new Font("Arial", Font.BOLD, 30));
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setForeground(Color.white);
        scoreLabel.setBackground(new Color(20, 20, 50));
        scoreLabel.setOpaque(true);

        bottomPanel.add(scoreLabel);
        bottomPanel.add(restartButton);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    void checkWinner(){
        for(int r=0; r<3; r++){
            if(board[r][0].getText()=="") continue;

            if(board[r][0].getText()==board[r][1].getText()&&
                board[r][1].getText()==board[r][2].getText()){
                    setWinnerTiles(board[r][0], board[r][1], board[r][2]);
                    gameOver= true;
                    return;
                }
            }

            for(int c=0; c<3; c++){
                if(board[0][c].getText()=="") continue;

                if(board[0][c].getText()==board[1][c].getText()&&
                    board[1][c].getText()==board[2][c].getText()){
                    setWinnerTiles(board[0][c], board[1][c], board[2][c]);
                    gameOver= true;
                    return;    
                    }
                }

                if(board[0][0].getText()==board[1][1].getText()&&
                    board[1][1].getText()==board[2][2].getText()&&
                    board[0][0].getText() !=""){
                        setWinnerTiles(board[0][0], board[1][1], board[2][2]);
                        gameOver=true;
                        return;
                    }
                    if(board[0][2].getText()==board[1][1].getText()&&
                         board[1][1].getText()==board[2][0].getText()&&
                         board[0][2].getText() !=""){
                            setWinnerTiles(board[0][2], board[1][1], board[2][0]);
                            // setWinner(board[1][1]);
                            // setWinner(board[2][0]);
                            gameOver=true;
                            return;
                        }
                            
                            if(turns==9){
                                for(int r=0; r<3; r++){
                                    for(int c=0; c<3; c++){
                                        setTie(board[r][c]);
                                    }
                                }
                                gameOver=true;
                            }
                    }
void setWinnerTiles(JButton tile1, JButton tile2,JButton tile3){
        Color winColor= new Color(0, 254, 180);
        Color setColor =new Color(0, 200, 200);
        tile1.setForeground(winColor);
        tile1.setBackground(setColor);
        tile2.setForeground(winColor);
        tile2.setBackground(setColor);
        tile3.setForeground(winColor);
        tile3.setBackground(setColor);
        textLabel.setText(currentPlayer + " is the winner");
        gameOver=true;
        if (currentPlayer.equals(PlayerX)) {
            scoreX++;
        }else{
            scoreO++;
        }
        updateScore();
    }

void setTie(JButton tile){
        tile.setForeground(new Color(0, 200, 200));
        tile.setBackground(new Color(0, 100, 100));
        textLabel.setText("Tie!");
        textLabel.setForeground(new Color(0, 200, 200));
    }
void updateScore(){
    scoreLabel.setText("X: "+ scoreX+ "| O: "+scoreO);
    }

void resetBoard(){
    for(int r=0; r<3; r++){
        for(int c=0; c<3; c++){
            board[r][c].setText("");
            board[r][c].setForeground(new Color(10, 40, 70));
            board[r][c].setBackground(new Color(200, 230, 240));
        }
    }
        gameOver=false;
        turns=0;
        currentPlayer=PlayerX;
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setForeground(Color.white);
    }
}

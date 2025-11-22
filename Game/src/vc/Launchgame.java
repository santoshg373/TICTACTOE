package vc;
import java.util.Random;
import java.util.Scanner;

class tictactoe {
   static char[][] board;

    public tictactoe() {
        board = new char[3][3];
        initboard();
    }

    void initboard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }
    }

   static void dispboard() {
        System.out.println("______________");
        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("______________");
        }
    }

   static void placemarker(int row, int col, char mark) {
        try {
            board[row][col] = mark;
        } catch (Exception e) {
            System.out.println("Invalid exception");
        }
    }

    static boolean colwin() {
        for (int j = 0; j < 2; j++) {
            if (board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true;
            }
        }
        return false;
    }

    static boolean rowwin() {
        for (int i = 0; i < 2; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }
        return false;
    }

   static boolean diagwin() {
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2] || board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }
        return false;
    }
    static  boolean draw(){
        for(int i=0;i<=2;i++){
            for(int j=0;j<=2;j++){
                if(board[i][j]==' '){
                    return false;
                }
            }
        }
        return  true;
    }
}
abstract class player{
    String name;
    char mark;
    abstract void makemove();
    boolean isvalidmove(int row ,int col){
        if(row>=0&&row<=2&&col>=0&&col<=2){
            if(tictactoe.board[row][col]==' '){
                return true;
            }
        }
        return false;
    }
}
    class humanplayer extends player{

        public  humanplayer(String name,char mark){
            this.name=name;
            this.mark=mark;
        }
        int row;
        int col;
        void makemove(){
            Scanner sc=new Scanner(System.in);
            do{
            System.out.println("Enter the row and col");
            row= sc.nextInt();
            col= sc.nextInt();
            }while(!isvalidmove( row,col));
            tictactoe.placemarker(row,col,mark);

        }

    }
class AI extends player{

    public  AI(String name,char mark){
        this.name=name;
        this.mark=mark;
    }
    int row;
    int col;
    void makemove(){
        Scanner sc=new Scanner(System.in);
        int row;
        int col;
        do{
            System.out.println("Enter the row and col");
            Random r=new Random();
            row= r.nextInt(3);
            col= r.nextInt(3);
        }while(!isvalidmove( row,col));
        tictactoe.placemarker(row,col,mark);

    }
}


class Launchgame {

	public  static  void main(String[] args){
	      tictactoe t=new tictactoe();
	     humanplayer p1=new humanplayer("Santu",'x');
	     AI p2=new AI("AI",'o');
	     player cp;
	     cp=p1;
	     while(true){
	         System.out.println(cp.name+" turn");
	         cp.makemove();
	         tictactoe.dispboard();
	         if(tictactoe.colwin()||tictactoe.rowwin()||tictactoe.diagwin()){
	             System.out.println(cp.name+" has won");
	             break;
	         } else if (tictactoe.draw()) {
	             System.out.println("match is draw");
	             break;

	         } else{
	             if(cp==p1){
	                 cp=p2;
	             }
	             else{
	                 cp=p1;
	             }
	         }
	     }
	  }
}


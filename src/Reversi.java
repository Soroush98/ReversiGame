import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import sun.management.GarbageCollectionNotifInfoCompositeData;

import java.lang.management.GarbageCollectorMXBean;
import java.util.ArrayList;
import java.util.Scanner;

public class Reversi {
    private int board[][]=new int [8][8];
    Reversi()
    {

        board[3][3]=1;
        board[3][4]=2;
        board[4][3]=2;
        board[4][4]=1;
    }
    public  void paint()
    {
        System.out.print("    ");
        for (int j=0;j<8;j++) {
            System.out.print(j+ "  ");
        }
        System.out.println();
        for (int i=0;i<8;i++)
        {
            System.out.print(i+" :");
            for (int j=0;j<8;j++) {

                if (board[i][j]==1)
                    System.out.print("|o|");
                    else if (board[i][j]==2)
                    System.out.print("|*|");
                    else
                System.out.print("| |");

            }
            System.out.println();
        }

    }
public void move(int i,int j,int x,int y,int player){
        board[i][j]=player;
    int coplayer=0;
    if (player==1)
    {
        coplayer=2;
    }
    else  coplayer=1;
    int x1=x;
    int y1=y;
    while(board[i+x][j+y]!=player)
    {
        board[i+x][j+y]=player;
        if (x1==-1)
            x--;
        else if (x1==1)
            x++;
        if (y1==1)
            y++;
        else if (y1==-1)
            y--;
    }

}
public ArrayList<int []>checkmove(int a, int b, int player)
{
ArrayList <int[]> moves=new ArrayList<int []>(10);
        if (a < 0 || a >= 8 || b < 0 || b > 8)
            throw new IllegalArgumentException("Please Enter Between 0-7");
        if (board[a][b]==1 || board[a][b]==2)
            throw new IllegalArgumentException("Can't place it at this room");
        int x=a;
        int y=b;
            int coplayer=0;
            if (player==1)
            {
                coplayer=2;
            }
            else  coplayer=1;
            int f=0;
            for (int i=-1;i<=1;i++) {
                for (int j=-1;j<=1;j++) {
                    x=a;
                    y=b;
                    if (x+i>=0 && x+i<=7 && y+j>=0 && y+j<=7) {
                        if (board[x + i][y + j] == coplayer) {
                            f = 0;
                            while (board[x + i][y + j] != player  ) {
                                if (board[x + i][y + j] == 0 ) {
                                    f = 1;
                                    break;
                                }
                                if (i == -1)
                                    x--;
                                if (i == 1)
                                    x++;
                                if (j == 1)
                                    y++;
                                if (j == -1)
                                    y--;
                                if ( x+i==8 || x+i==-1 || y+j==8 || y+j==-1)
                                {
                                    f=1;
                                    break;
                                }
                            }
                            if (f == 0) {
                                int [] mv=new int [2];
                                mv[0]=i;
                                mv[1]=j;
                                moves.add(mv);
                            }
                        }
                    }
                }
            }


return moves;
}
public void printwinner()
{
    int white=0;
    int black=0;
    for (int i=0;i<8;i++)
        for(int j=0;j<8;j++) {
        if (board[i][j]==1)
            white++;
        else if (board[i][j]==2)
            black++;
        }
        if (black>white)
            System.out.println("Black is Winner");
    else if (white>black)
            System.out.println("White is Winner");
    else
            System.out.println("Equals");
    System.out.println("Black Point: "+black+"\n"+"White Point: "+white);

}
public int checkfinish(int player)
{
    ArrayList <int[]> k=new ArrayList<int[]>(10);
    for (int i=0;i<8;i++) {
        for (int j = 0; j < 8; j++) {
            if (board[i][j] == 0) {
                k = checkmove(i, j, player);
                if (k.size()!=0)
                    return 1;
            }
        }
    }
        return 0;
}
    public static void main(String[] args)
    {
        Reversi i=new Reversi();
        int play=1;
        ArrayList <int[]> move;
        while(true) {
            move=new ArrayList<int []>(10);
            i.paint();
            if(i.checkfinish(play%2+1)==0)
                break;
            if (play%2==0)
                System.out.println("White's Turn");
            else if (play%2==1)
                System.out.println("Black's Turn");
            System.out.println("Enter your numbers");
            Scanner sc = new Scanner(System.in);
            int x = sc.nextInt();
            int y = sc.nextInt();
            try {
                move= i.checkmove(x,y,play%2+1);
                if (move.size()==0) {
                    System.out.println("Move Not available Try again");
                    continue;
                }
                else {
                    for (int o=0;o<move.size();o++)
                        System.out.println(move.get(o)[0]+" "+move.get(o)[1]);
                    for (int o=0;o<move.size();o++)
    i.move(x,y,move.get(o)[0],move.get(o)[1],play%2+1);


                }
                i.paint();

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }
            play++;
        }
       i.printwinner();
    }
}

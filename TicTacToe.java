import java.util.Random;


public class TicTacToe {

    //attributes
    private String table[][] = new String [3][3];

    //metods
    //contructor

    TicTacToe(){
        tableReset();
    }

    //reset the table
    public void tableReset(){
        int i, j;
        for(i = 0;i<3; i++){
            for(j = 0; j<3;j++){
                table[i][j] = "";
            }
        }
    }

    //takes the player's input and if its possible it updates the board with his input
    public int playerInput(int row, int col){
        //some variables
        String player = "X";
        if(numRange(row, col) && positonAvailable(row, col)){
            table[row][col] = player;
            if(checkWin(player)){
                return 2;
            }else{
                return 1;
            }
        }else{

            return 0;
        }

    }

    //check if the player's input coordinatese are between 0 and 2
    private boolean numRange(int row, int col){
        if(row >= 0 && row <= 2 && col>=0 && col <= 2){
            return true;
        }else{
            return false;
        }
    }

    private boolean positonAvailable (int row, int col ){
        if(table[row][col].equals("")){
            return true;
        }else{
            return false;
        }
    }


    public String toString(){
        String drawTable = "";
        drawTable = drawTable+ 1+ "  "+ 2+ "  "+ 3+ "\n"+ table[0][0]+ " | " + table[0][1] + " | " + table[0][2]+ "  A"+ "\n--------\n" +table[1][0] + " | " +  table[1][1] + " | " + table[1][2] +"  B" +"\n--------\n" + table[2][0] + " | " +  table[2][1] + " | " +  table[2][2] + "  C";                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                //Eric Cristelli
        return drawTable;
    }




    //check if anyone has win
    private boolean checkWin(String player){
        if( table[0][0].equals(player) && table[0][1].equals(player) && table[0][2].equals(player) ||
                table[1][0].equals(player) && table[1][1].equals(player) && table[1][2].equals(player) ||
                table[2][0].equals(player) && table[2][1].equals(player) && table[2][2].equals(player) ||
                table[0][0].equals(player) && table[1][0].equals(player) && table[2][0].equals(player) ||
                table[0][1].equals(player) && table[1][1].equals(player) && table[2][1].equals(player) ||
                table[0][2].equals(player) && table[1][2].equals(player) && table[2][2].equals(player) ||
                table[0][0].equals(player) && table[1][1].equals(player) && table[2][2].equals(player) ||
                table[2][0].equals(player) && table[1][1].equals(player) && table[0][2].equals(player)){
            return true;
        }else{
            return false;
        }
    }



    public int computerMove(){
        Random random = new Random();
        int num ;
        if(!fullTable()){
            if(freeMiddle()){ // if the middle is empity will place there the o
                table[1][1] = "o";
            } else if (trick() != 0) {
                if(trick() == 1){
                    num = random.nextInt(2);
                    switch (num) {
                        case 0 -> table[0][2] = "o";
                        case 1 -> table[2][0] = "o";
                    }
                }else{
                    num = random.nextInt(2);
                    switch (num) {
                        case 0 -> table[0][2] = "o";
                        case 1 -> table[2][2] = "o";
                    }
                }

            } else if (firstMoveCenter()) { //if the player has firstly moved in the middle, it will place randomly in a corner
                num = random.nextInt(4);
                switch (num) {
                    case 0 -> table[0][0] = "o";
                    case 1 -> table[0][2] = "o";
                    case 2 -> table[2][0] = "o";
                    case 3 -> table[2][2] = "o";
                }
            }else if(winPossibility("o")){//if the computer can win it will win
                int i = 0;
                int row;
                int col;
                boolean possibility = false;


                do{
                    row = random.nextInt(3);
                    col = random.nextInt(3);
                    if(positonAvailable(row, col)){
                        table[row][col] = "o";

                        if(checkWin("o")){
                            possibility = true;
                        }
                        table[row][col] = "";
                    }
                    i++;
                }while(!possibility && i< 50);
                table[row][col] = "o";
                if(checkWin("o")) {
                    return 2; //if the computer has win
                }
            }else{

                int i = 0;
                int row;
                int col;
                boolean possibility = false;


                do{
                    row = random.nextInt(3);
                    col = random.nextInt(3);
                    if(positonAvailable(row, col)){
                        table[row][col] = "X";

                        if(checkWin("X")){
                            possibility = true;
                        }
                        table[row][col] = "";
                    }
                    i++;
                }while(!possibility && i< 50);
                table[row][col] = "o";

                if(checkWin("o")){
                    return 2; //if the computer has win
                }else{
                    return 1; // the move was succesfull
                }

            }
        }else{
            return 4; //the table is full
        }

        if(checkWin("o")){
            return 2; //if the computer has win
        }else{
            return 1; // the move was succesfull
        }


    }



    // given the player, it checks if the player could do tris (it has arleady two alligned symbols)
    private boolean winPossibility(String player){
        Random random = new Random();
        int i = 0;
        int row;
        int col;
        boolean possibility = false;


        do{
            Boolean insert = false;
            row = random.nextInt(3);
            col = random.nextInt(3);
            if(positonAvailable(row, col)){
                table[row][col] = player;
                insert = true;
                if(checkWin(player)){
                    possibility = true;
                }
                table[row][col] = "";
            }

            i++;

        }while(!possibility && i< 50);

        return possibility;
    }


    //check if the middle is empity
    private boolean freeMiddle(){
        if(table[1][1].equals("")){
            return true;
        }else{
            return false;
        }
    }


    //check if the player has firstly moved in center
    private boolean firstMoveCenter(){
        if(table[0][0].equals("")&& table[0][1].equals("")&& table[0][2].equals("")&&table[1][0].equals("")&& table[1][1].equals("X")&&table[1][2].equals("")&&table[2][0].equals("")&&table[2][1].equals("")&& table[2][2].equals("")){
            return true;
        }else{
            return false;
        }
    }

    //check a particular case
    private int trick(){
        if(table[0][0].equals("X")&& table[1][1].equals("o")&& table[2][2].equals("X") &&  table[0][1].equals("")&& table[0][2].equals("")&&table[1][0].equals("")&& table[1][2].equals("")&&table[2][0].equals("")&&table[2][1].equals("")){
            return 1;
        }else if (table[2][0].equals("X")&& table[1][1].equals("o")&& table[0][2].equals("X") && table[0][0].equals("")&& table[0][1].equals("")&& table[1][0].equals("")&& table[1][2].equals("")&& table[2][1].equals("")&& table[2][2].equals("")){
            return 2;
        }else{
            return 0;
        }
    }

    private boolean fullTable(){

        boolean full = true;
        int i, j;
        for(i = 0;i<3; i++){
            for(j = 0; j<3;j++){
                if(table[i][j].equals("")){
                    full = false;
                }

            }
        }
        return full;
    }

}
//Eric Cristelli
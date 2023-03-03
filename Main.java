import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner input;
        input = new Scanner(System.in);

        //some variables
        int row = 0;
        int col = 0;
        String rowLetter;
        int i = 0;
        int points = 0;
        int tmp = 10, tmp2, choice;
        boolean exit = false;

        //initialization the object
        TicTacToe game1 = new TicTacToe();


        while(!exit){
            System.out.println("Insert 1 to start a game");
            System.out.println("Insert 0 to exit");
            System.out.print("Insert your choice: ");
            choice = input. nextInt();



            System.out.println("\n\n\n\n\n\n\n\n\n\n\n");



            switch (choice){
                case 0:
                    exit = true;
                    System.out.println("Godbye");
                    break;

                case 1:

                    //print start table
                    game1.tableReset();
                    System.out.println("GAME START");
                    System.out.println(game1.toString());
                    while(tmp != 2 && tmp != 4){
                        System.out.println("");
                        System.out.print("Insert the row: ");
                        input = new Scanner(System.in);
                        rowLetter = input.nextLine();
                        switch(rowLetter){
                            case "a":
                                row = 0;
                                break;

                            case "A":
                                row = 0;
                                break;

                            case "b":
                                row = 1;
                                break;

                            case "B":
                                row = 1;
                                break;

                            case "c":
                                row = 2;
                                break;

                            case "C":
                                row = 2;
                                break;

                        }
                        System.out.print("Insert the column: ");
                        input = new Scanner(System.in);
                        col = input.nextInt();
                        col--;
                        System.out.println("\n");
                        tmp = game1.playerInput(row, col);

                        switch(tmp){
                            case 2:
                                System.out.println("You Win ;)");
                                points++;
                                System.out.println("Your points :"+ points);
                                break;

                            case 0:
                                System.out.println ("Your move was not inserted in the table");
                                break;

                            case 1:
                                tmp = game1.computerMove();
                                System.out.println("Your turn");
                                System.out.println(game1.toString());
                                switch (tmp){
                                    case 4:
                                        System.out.print("\n");
                                        System.out.println ("The table is full");
                                        System.out.println("Tie");
                                        break;

                                    case 2:
                                        System.out.print("\n");
                                        System.out.println("The computer has Win ;(");
                                        System.out.println("Your points : "+ points);
                                        break;

                                }

                        }
                    }
            }
            tmp = 10; //reset tmp
            System.out.print("\n\n\n");

        }
    }
}
//Eric Cristelli
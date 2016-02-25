import java.util.Scanner;

public class Main {

    public static void main(String [] args){
        int boardSize = 5;
        Scanner reader = new Scanner(System.in);
        Player whitePlayer;
        Player blackPlayer;
        String inputString;
        //initialize game
        do {
            System.out.println("Is the white player a human? (y/n)");
            inputString = reader.next();
        } while(!isValidInput(inputString));

        if(inputString.equals("yes") || inputString.equals("y") || inputString.equals("Y")){
            whitePlayer = new HumanPlayer(boardSize, false);
        } else {
            whitePlayer = new RobotPlayer(boardSize, false);
        }
        
        do {
            System.out.println("Is the black player a human? (y/n)");
            inputString = reader.next();
        } while(!isValidInput(inputString));
        if(inputString.equals("yes") || inputString.equals("y") || inputString.equals("Y")){
            blackPlayer = new HumanPlayer(boardSize, true);
        } else {
            blackPlayer = new RobotPlayer(boardSize, true);
        }
        
        BoardState currentState = new BoardState(boardSize, whitePlayer, blackPlayer);
        
        //run the game loop
        Boolean gameComplete = false;
        while(!gameComplete){
            Boolean whiteCanMove = currentState.playerCanMove(whitePlayer);
            if(whiteCanMove){
                System.out.println("\n\n\nWHITE'S TURN\n----------------");
                currentState = whitePlayer.runTurn(currentState);
            }
            Boolean blackCanMove = currentState.playerCanMove(blackPlayer);
            if(blackCanMove){
                System.out.println("\n\n\nBLACK'S TURN\n----------------");
                currentState = blackPlayer.runTurn(currentState);
            }
            gameComplete = !(whiteCanMove || blackCanMove);
        }
        //print out winner information
        System.out.println("\n\n\n----------------");
        currentState.renderState();
        System.out.println("\n Game Complete");
        Player winner = currentState.findWinner(blackPlayer, whitePlayer);
        if(winner == null){
            System.out.println("Tie Game");
        } else {
            System.out.println(winner.toString() + " Wins!");
        }
    }
    
    private static Boolean isValidInput(String input){
        return (input.equals("yes") || input.equals("y") || input.equals("Y") || 
                input.equals("no") || input.equals("n") || input.equals("N")); 
    }

}

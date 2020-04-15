
package com.mycompany.tictac2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TheTicTac {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
    
    public static void main(String[] args){
        
        char[][] gameBoard = {{'|', ' ', '|', ' ', '|', ' ', '|'},
            {'|', '-', '+', '-', '+', '-', '|'},
            {'|', ' ', '|', ' ', '|', ' ', '|'},
            {'|', '-', '+', '-', '+', '-', '|'},
            {'|', ' ', '|', ' ', '|', ' ', '|'}};
                
        printGameBoard(gameBoard);
        
        while(true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter your placement (1-9): ");
            int pos = scan.nextInt();
            while(playerPositions.contains(pos) || cpuPositions.contains(pos)){
                System.out.println("This position is taken!");
                pos = scan.nextInt();
            }

            placePiece(gameBoard, pos, "player");
            
            String result = checkWinner();
            if (result.length()>0){
                System.out.println(result);
            }

            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)){
                cpuPos = rand.nextInt(9) + 1;
            }
            placePiece(gameBoard, cpuPos, "cpu");

            printGameBoard(gameBoard);
            
            result = checkWinner();
            if(result.length()>0){
                System.out.println(result);
            }
        }
    }
    
    public static void printGameBoard(char[][] gameBoard){
        for(char[] row : gameBoard){
            for(char c : row){
                System.out.print(c);
            }
            System.out.println();
        }
    }
    
    public static void placePiece(char[][] gameBoard, int pos, String user){
        
        char symbol = ' ';
        
        if(user.equals("player")){
            symbol = 'X';
            playerPositions.add(pos);
        } else if(user.equals("cpu")){
            symbol = 'O';
            cpuPositions.add(pos);
        }
        
        switch(pos){
            case 1:
                gameBoard[0][1] = symbol;
                break;
            case 2:
                gameBoard[0][3] = symbol;
                break;
            case 3:
                gameBoard[0][5] = symbol;
                break;
            case 4:
                gameBoard[2][1] = symbol;
                break;
            case 5:
                gameBoard[2][3] = symbol;
                break;
            case 6:
                gameBoard[2][5] = symbol;
                break;
            case 7:
                gameBoard[4][1] = symbol;
                break;
            case 8:
                gameBoard[4][3] = symbol;
                break;
            case 9:
                gameBoard[4][5] = symbol;
                break;
            default:
                break;
        }
    }
    
    public static String checkWinner(){
        
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List di1 = Arrays.asList(1, 5, 9);
        List di2 = Arrays.asList(7, 5, 3);
        
        List<List> winning = new ArrayList<>();
        winning.add(di2);
        winning.add(di1);
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        
        for(List l : winning){
            if(playerPositions.containsAll(l)){
                return "Congratulations, you won!";
            } else if (cpuPositions.containsAll(l)){
                return "Oh dear, better luck next time :(";
            } else if (playerPositions.size()+ cpuPositions.size() >= 9){
                return "CAT!";
            }
        }
        return "";
    }
}

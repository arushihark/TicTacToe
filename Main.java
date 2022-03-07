import java.util.*;
import java.io.*;

public class Main {
  static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
  static ArrayList<Integer> compPositions = new ArrayList<Integer>();

  public static void main(String[] args) {
    //create gameboard
    char[][] gameBoard = {{' ', '|', ' ', '|', ' '}, 
                          {'-', '+', '-', '+', '-'},
                          {' ', '|', ' ', '|', ' '},
                          {'-', '+', '-', '+', '-'},
                          {' ', '|', ' ', '|', ' '}};

    Scanner sc = new Scanner(System.in);
    while (true) {
      System.out.print("Enter your placement (1-9): ");
      int playerPos = sc.nextInt();
      while (playerPositions.contains(playerPos)|| compPositions.contains(playerPos)) {
        System.out.print("Sorry that position is already taken. Enter an empty position: ");
        playerPos = sc.nextInt();
      }
      //System.out.println(playerPos);
      placePiece(gameBoard, playerPos, "player");
      Random rand = new Random();

      int computerPos = rand.nextInt(10);
      while (playerPositions.contains(computerPos)|| compPositions.contains(computerPos)) {
        computerPos = rand.nextInt(10);
      }
      placePiece(gameBoard, computerPos, "computer");

      printGameBoard(gameBoard);
      System.out.println(checkWinner());      
    }
  }

  static void printGameBoard(char[][] gameBoard) {
    //print gameboard
    for (char[] row : gameBoard) {
      for (char c : row)
        System.out.print(c  + " ");
      System.out.println();
    }
  }

  static void placePiece(char[][] gameBoard, int pos, String user) {
    char symbol = ' ';

    if (user.equals("player")) {
      symbol = 'X';
      playerPositions.add(pos);
    } else if(user.equals("computer")) {
      symbol = 'O';
      compPositions.add(pos);
    }

    switch(pos) {
      case 1: 
        gameBoard[0][0] = symbol;
        break;
      case 2: 
        gameBoard[0][2] = symbol;
        break;
      case 3: 
        gameBoard[0][4] = symbol;
        break;
      case 4: 
        gameBoard[2][0] = symbol;
        break;
      case 5: 
        gameBoard[2][2] = symbol;
        break;
      case 6: 
        gameBoard[2][4] = symbol;
        break;
      case 7: 
        gameBoard[4][0] = symbol;
        break;
      case 8: 
        gameBoard[4][2] = symbol;
        break;
      case 9: 
        gameBoard[4][4] = symbol;
        break;
      default:
        break;
    }
  }

  static String checkWinner() {
    ArrayList<Integer> topRow = new ArrayList<Integer>();
    topRow.add(1); topRow.add(2); topRow.add(3);

    ArrayList<Integer> midRow = new ArrayList<Integer>();
    midRow.add(4); midRow.add(5); midRow.add(6);

    ArrayList<Integer> botRow = new ArrayList<Integer>();
    botRow.add(7); botRow.add(8); botRow.add(9);

    ArrayList<Integer> leftCol = new ArrayList<Integer>();
    leftCol.add(1); leftCol.add(4); leftCol.add(7);
    
    ArrayList<Integer> midCol = new ArrayList<Integer>();
    midCol.add(2); midCol.add(5); midCol.add(8);

    ArrayList<Integer> rightCol = new ArrayList<Integer>();
    rightCol.add(3); rightCol.add(6); rightCol.add(9);

    ArrayList<Integer> diagDown = new ArrayList<Integer>();
    diagDown.add(1); diagDown.add(5); diagDown.add(9);

    ArrayList<Integer> diagUp = new ArrayList<Integer>();
    diagUp.add(7); diagUp.add(5); diagUp.add(3);

    ArrayList<ArrayList<Integer>> winningPos = new ArrayList<ArrayList<Integer>>();
    winningPos.add(topRow);
    winningPos.add(midRow);
    winningPos.add(botRow);
    winningPos.add(leftCol);
    winningPos.add(midCol);
    winningPos.add(rightCol);
    winningPos.add(diagDown);
    winningPos.add(diagUp);

    for (ArrayList al : winningPos) {
      if(playerPositions.containsAll(al))
        return "Congratulations you won!";
      else if (compPositions.containsAll(al))
        return "Sorry you lost :/";
      else if (playerPositions.size() + compPositions.size() == 9)
        return "Tie game!";
    }
    return "";
    }
}

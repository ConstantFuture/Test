//Name: Savik Kinger
//Block: H
//Lab: 5 - Black Box Challenge
//Date Started: November 13, 2018
//Date Completed: November 21, 2018
import java.util.Random;
import java.util.Scanner;

public class APCS_lab5 {

	static Scanner input = new Scanner(System.in);

	static Random randy = new Random();

	public static void main(String[] args) {
		//Main Game Board
		char [][] game = new char [10] [10];
		setUpBoard(game);

		//For Guess Method
		char [][] tempgame = new char [10] [10];
		for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++)
			{
				tempgame[i][j] = '.';
			}
		}
		System.out.println("Welcome to the Black Box");
		int shotCounter = 0;
		int guessCounter = 0;
		int entered;
		int temp;
		boolean [] playon = new boolean [1]; // for ending Guess method
		playon[0] = true;
		do {
			System.out.println("");
			System.out.println("Choose:");
			System.out.println("      (1)  Shoot a Laser");
			System.out.println("      (2)  Guess at a mirror location:");
			System.out.println("      (0)  Quit the game:");
			System.out.println("");
			System.out.println("Enter Choice: ");
			entered = input.nextInt();
			switch (entered) {
			case 1:
				System.out.println("What number would you like to enter in at?");
				temp = input.nextInt();
				System.out.println("You will exit at: ");
				if (temp < 10)
					System.out.println("You exit at "+ laser(game,'n', 9, temp));
				else if (temp < 20)
					System.out.println("You exit at "+ laser(game,'e', 19-temp, 0));
				else if (temp < 30)
					System.out.println("You exit at "+ laser(game, 's', 0, temp%10));
				else if (temp < 40)
					System.out.println("You exit at "+ laser(game,'w', temp%10, 9));
				shotCounter++;
				break;
			case 2:
				if (playon[0] == true)
					guess(game, tempgame, playon);
				else if(playon[0] == false)  System.out.println("You have guessed them All! Congratulations!");
				guessCounter++;
				break;
			case 3:
				System.out.println("The answer to the board is: ");
				displayGame(game);
				break;
			default:
				System.out.println("The End.");
				break;
			}
			System.out.println("");
			System.out.println("Shot Statistics:");
			System.out.println("The number of shots you have done is: " + shotCounter);
			System.out.println("The number of guesses you have made is: " + guessCounter);
			System.out.println("");
		} while (entered == 1 || entered == 2 || entered == 3);

		System.out.println("Thank you and Goodbye!");
	}
	public static void setUpBoard (char [][] game){
		for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++)
			{
				game[i][j] = '.';
			}
		}
		boolean a = true;
		int e,f,g;
		for(int i = 0; i < 10; i++){
			g = randy.nextInt(i+2);
			e = randy.nextInt(9);
			f = randy.nextInt(9);
			do {
				if (game[e][f] != '/' && game[e][f] != '\\')
					a = true;
				else {
					e = randy.nextInt(9);
					f = randy.nextInt(9);
					a = false;
				}
			} while (a != true);
			if (g%2 == 0)
				game[e][f] = '/';
			else
				game[e][f] = '\\';
		}
	}
	public static void displayGame (char [][] game){
		System.out.println("   20212223242526272829");
		for (int i = 0; i < 10; i++){
			System.out.print((-i+19) + " ");
			for (int j = 0; j < 10; j++)
			{
				System.out.print(game[i][j] + " ");
			}
			System.out.println(i+30);
		}
		System.out.println("   0 1 2 3 4 5 6 7 8 9 ");
	}
	public static int laser (char [][]game, char direction, int row, int column) {
		if (game[row][column] == '\\') {
			if (direction == 'n')
				direction = 'w';
			else if (direction == 's')
				direction = 'e';
			else if (direction == 'w')
				direction = 'n';
			else if (direction == 'e')
				direction = 's';
		}
		else if (game[row][column] == '/') {
			if (direction == 'n')
				direction = 'e';
			else if (direction == 's')
				direction = 'w';
			else if (direction == 'e')
				direction = 'n';
			else if (direction == 'w')
				direction = 's';
		}
		if (direction == 's' && row == 9)
			return column;
		else if (direction == 'n' && row == 0)
			return column + 20;
		else if (direction == 'e' && column == 9)
			return row + 30;
		else if (direction == 'w' && column == 0)
			return 19-row;

		if (direction == 'n')
			row = row - 1;
		else if (direction == 's')
			row = row + 1;
		else if (direction == 'e')
			column = column + 1;
		else if (direction == 'w')
			column = column - 1;

		return laser(game, direction, row, column);
	}
	public static void guess (char [][]game, char [][] tempgame, boolean [] playon){

		System.out.println("Please use the left y axis and bottom x axis for you guessing coordinates (the smaller numbers)");
		System.out.println();
		System.out.println("Which row do you think he mirror is in ?");
		int enter1 = input.nextInt();
		enter1 = 19-enter1;
		System.out.println("Which column do you think he mirror is in ?");
		int enter2 = input.nextInt();
		if (game[enter1][enter2] == '/' || game[enter1][enter2] == '\\') {
			char temp = game[enter1][enter2];
			tempgame[enter1][enter2] = temp;
			System.out.println("You found a Mirror!");
			displayGame(tempgame);
		}
		else System.out.println("You missed!");

		int total = 0;
		for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++)
			{
				if (tempgame[i][j] == '/' || tempgame[i][j] == '\\') total++;
			}
		}
		if (total == 10) playon[0] = false;
		else playon[0] = true;
	}
}

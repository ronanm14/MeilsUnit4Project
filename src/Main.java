import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int balance = 1000;
        int lastBal = balance;
        int gCount = 0;
        int diff = 0;

        System.out.println("Welcome to Las Vegas!");
        System.out.println("You start out in the casino with 1000 dollars.");
        System.out.println("You need to earn 2500 dollars to catch your flight back home.");
        System.out.println("If you lose all your money, it's kind of over for you.");
        System.out.println("By the way, soft aces are aces worth 11 instead of 1.");
        System.out.println("Good luck! You'll need it...\n");

        game g = new game();
        while (balance > 0 && balance < 2500) {
            g.newGame(balance);
            balance = g.setBal();
            gCount++;
            diff = balance - lastBal;
            if (diff > 0) {
                System.out.println("You won " + diff + " dollars!\n");
            } else if (diff < 0) {
                System.out.println("You lost " + Math.abs(diff) + " dollars...\n");
            } else {
                System.out.println("You didn't win or lose any money.\n");
            }
            System.out.println("Your balance is now " + balance + " dollars.\n");
            lastBal = balance;

        }

        if (balance >= 2500) {
            System.out.println("Congrats! You escaped Las Vegas!");
            System.out.println("You played " + gCount + " games of blackjack and won a total of " + (balance - 1000) + " dollars.");
            System.out.print("Remember, 99% of gamblers quit right before they hit big!");
        } else {
            System.out.println("Unfortunately, you ran out of money and starved on the boardwalk.");
            System.out.println("You played " + gCount + " games of blackjack and lost a total of " + Math.abs(balance - 1000) + " dollars.");
            System.out.print("Better luck next time...");
        }
    }
}
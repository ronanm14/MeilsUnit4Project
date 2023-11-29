import java.util.Scanner;

public class game {
    private int bal;
    public game () {}

    public void newGame (int ba) {
        Scanner s = new Scanner(System.in);
        boolean pBlackjack = false;
        boolean gameOver = false;
        boolean endTurn = false;
        int balance = ba;
        int bet = 0;
        int payout = 0;
        blackjack b;
        String choice = "";

        System.out.print("How much would you like to bet? ");
        bet = s.nextInt();
        s.nextLine();
        if (bet > balance || bet <= 0) {
            System.out.println("Invalid bet amount! Set your bet to 250. ");
            balance -= 250;
            payout = 500;
            b = new blackjack();
        } else {
            balance -= bet;
            payout = bet * 2;
            b = new blackjack(bet);
        }
        pBlackjack = b.setBlackjack();
        if (!pBlackjack) {
            System.out.println();
            b.info(true);

            System.out.print("What would you like to do? Hit (h), stand (s), double down (d), or fold (f)? ");
            choice = s.nextLine();
            if (choice.equals("f")) {
                payout /= 4;
            } else {
                if (choice.equals("d")) {
                    balance -= bet;
                    payout *= 2;
                    bet *= 2;
                }
                b.newTurn(choice);
                gameOver = b.setGameOver();
                endTurn = b.setEndTurn();
                System.out.println();

                while (!endTurn && !gameOver) {
                    b.info(false);
                    System.out.print("What would you like to do? Hit (h), stand (s), or double down (d)? ");
                    choice = s.nextLine();
                    b.newTurn(choice);
                    if (choice.equals("d")) {
                        balance -= bet;
                        payout *= 2;
                        bet *= 2;
                    }
                    gameOver = b.setGameOver();
                    endTurn = b.setEndTurn();
                    System.out.println();
                }
                if (!gameOver) {
                    b.runDealer();
                    b.info(true);
                } else {
                    b.info(false);
                }

                if (b.calcResults().equals("d")) {
                    payout = 0;
                } else if (b.calcResults().equals("t")) {
                    payout /= 2;
                }
            }
        } else {
            System.out.println("\nYou got a blackjack!");
            if (b.blackjackCheck()) {
                System.out.println("Nice blackjack! That pays 3 to 2 odds!");
                payout *= 1.5;
            } else {
                System.out.println("The dealer also got a blackjack. Unlucky...");
                payout /= 2;
            }
        }
        bal = balance + payout;
    }

    public int setBal () {
        return bal;
    }
}
public class blackjack {
    private String deck = "1111222233334444555566667777888899990000JJJJQQQQKKKK";
    private boolean pBlackjack = false;
    private boolean dGameOver = false;
    private boolean pGameOver = false;
    private boolean endTurn = false;
    private int dCount = 0;
    private int dAces = 0;
    private int pCount = 0;
    private int pAces = 0;
    private int betNum;

    public blackjack() {
        betNum = 250;
        pDrawCard();
        dDrawCard();
        pDrawCard();
        if (pCount == 21) {
            pBlackjack = true;
        }
    }

    public blackjack(int bet) {
        betNum = bet;
        pDrawCard();
        dDrawCard();
        pDrawCard();
        if (pCount == 21) {
            pBlackjack = true;
        }
    }

    public void newTurn (String choice) {
        if (choice.equals("h")) {
            pDrawCard();
        } else if (choice.equals("d")) {
            pDrawCard();
            endTurn = true;
        } else if (choice.equals("s")) {
            endTurn = true;
        } else {
            System.out.println("Invalid choice. Try again.");
        }
    }

    public int drawCard() {
        int random = (int) (Math.random() * deck.length() + 1);
        String card = deck.substring(random - 1, random);
        int value = 0;
        if (!card.equals("J") && !card.equals("Q") && !card.equals("K")) {
            value = Integer.parseInt(card);
        }
        if (Integer.toString(value).equals("0")) {
            value = 10;
        }
        return value;
    }

    public void pDrawCard() {
        int card = drawCard();
        if (card == 1) {
            pAces++;
            card += 10;
        }
        pCount += card;
        if (pCount > 21) {
            if (pAces > 0) {
                pAces--;
                pCount -= 10;
            } else {
                pGameOver = true;
            }
        }
    }

    public void dDrawCard() {
        int card = drawCard();
        if (card == 1) {
            dAces++;
            card += 10;
        }
        dCount += card;
        if (dCount > 21) {
            if (dAces > 0) {
                dAces--;
                dCount -= 10;
            } else {
                dGameOver = true;
            }
        }
    }

    public void runDealer () {
        while (dCount < 17 || (dCount <= 17 && dAces != 0)) {
            dDrawCard();
        }
        //dealer stands or hits on 17 with an ace worth 11? hits
        //always stands on 18 or higher
    }

    public String calcResults () {
        if (pGameOver) {
            return "d";
        } else if (dGameOver) {
            return "p";
        } else if (dCount > pCount) {
            return "d";
        } else if (pCount > dCount) {
            return "p";
        } else {
            return "t";
        }
    }

    public boolean blackjackCheck () {
        dDrawCard();
        if (dCount != 21) {
            return true;
        }
        return false;
    }


    public void info(boolean dInfo) {
        System.out.println("Your cards are worth " + pCount + " with " + pAces + " soft aces.");
        if (dInfo) {
            System.out.println("The dealer's cards are worth " + dCount + " with " + dAces + " soft aces.");
        }
    }

    public boolean setEndTurn() {
        return endTurn;
    }

    public boolean setGameOver() {
        return pGameOver;
    }

    public boolean setBlackjack() {
        return pBlackjack;
    }
}
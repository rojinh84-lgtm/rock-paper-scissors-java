import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        System.out.println("hello welcome to our game");
        System.out.println("for how many matches you want to play? please enter a number");

        int numberOfRounds = 0;
        boolean validNumber = false;
        while (!validNumber) {
            try {
                String input = userInput.nextLine();
                numberOfRounds = Integer.parseInt(input);
                validNumber = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number!");
            }
        }

        Play play = new Play();
        PcPlay pc = new PcPlay();

        for (int i = 0; i < numberOfRounds; i++) {
            Move userMove = null;

            System.out.println("enter your move use emoji");
            System.out.println("Choose: " + Move.ROCK.getEmoji() + " " + Move.PAPER.getEmoji() + " " + Move.SCISSORS.getEmoji());

            while (userMove == null) {
                String input = userInput.nextLine();
                userMove = Move.fromEmoji(input);
                if (userMove == null) {
                    System.out.println("Invalid input");
                }
            }

            Move systemMove = pc.setPosition();
            play.game(userMove, systemMove);
        }

        System.out.println("Your score: " + play.userWin);
        System.out.println("Computer score: " + play.systemWin);

        if (play.userWin > play.systemWin) {
            System.out.println("You won!");
        } else if (play.systemWin > play.userWin) {
            System.out.println("Computer won!");
        } else {
            System.out.println("Draw!");
        }

        userInput.close();
    }
}

enum Move {
    ROCK("🗿"), PAPER("📄"), SCISSORS("✂️");

    private final String emoji;

    Move(String emoji) {
        this.emoji = emoji;
    }

    public String getEmoji() {
        return emoji;
    }

    public static Move fromEmoji(String emoji) {
        for (Move m : values()) {
            if (m.emoji.equals(emoji)) {
                return m;
            }
        }
        return null;
    }
}

class Play {
    int userWin = 0;
    int systemWin = 0;

    public void game(Move userMove, Move systemMove) {
        if (userMove == systemMove) {
            System.out.println("Your move is similar to system's");
            return;
        }

        boolean userWins =
            (userMove == Move.ROCK && systemMove == Move.SCISSORS) ||
            (userMove == Move.SCISSORS && systemMove == Move.PAPER) ||
            (userMove == Move.PAPER && systemMove == Move.ROCK);

        if (userWins) {
            userWin++;
            System.out.println("You win this round!");
        } else {
            systemWin++;
            System.out.println("Computer wins this round!");
        }
    }
}

class PcPlay {
    private final Random random = new Random();

    public Move setPosition() {
        Move[] moves = Move.values();
        return moves[random.nextInt(moves.length)];
    }
}
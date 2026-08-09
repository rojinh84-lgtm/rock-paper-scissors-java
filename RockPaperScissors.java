import java.util.Random;
import java.util.Scanner;
public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        System.out.println("hello welcome to our game");
        System.out.println("for how many match you want to play? please enter a number");
        boolean time = false;
        int number = 0;
        while (!time) {
            try {
                String input = userInput.nextLine();
                number = Integer.parseInt(input);
                time = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number!");
            }
        }
        
        
        Play play = new Play();

        for (int i = 0; i < number; i++) {
            boolean checkInput = false;
            System.out.println("enter your move use emoji");
            System.out.println("Choose: 🗿 📄 ✂️");
            String UserMove = "";
            while (!checkInput) {
                try {
                    UserMove = userInput.nextLine();

                    if (!UserMove.equals("✂️") &&
                        !UserMove.equals("📄") &&
                        !UserMove.equals("🗿")) {

                        throw new Exception("Invalid input");
                    }

                    checkInput = true;

                } catch (Exception e) {
                    System.out.println("Invalid input");
                }
            }

            PcPlay system = new PcPlay();

            String systemMove = system.setPosition();

            play.game(UserMove, systemMove);

        }
        
        System.out.println("Your score: " + play.user_win);
        System.out.println("Computer score: " + play.system_win);

        if (play.user_win > play.system_win) {
            System.out.println("You won!");
        } else if (play.system_win > play.user_win) {
            System.out.println("Computer won!");
        } else {
            System.out.println("Draw!");
        }

    }
}
class Play{
    int user_win = 0;  
    int system_win = 0;  
    public void game(String user_move, String system_move){
        if ((user_move.equals("✂️") && system_move.equals("📄")) || (user_move.equals("🗿") && system_move.equals("✂️")) || (user_move.equals("📄") && system_move.equals("🗿"))){
            user_win++;
             System.out.println("You win this round!");
        }
        else if (user_move.equals(system_move)){
            System.out.println("your move is similar with system");
        }
        else{
            system_win++;
            System.out.println("Computer wins this round!");
        }
        
    }
    
    
}
class PcPlay{
    Random random = new Random();
    //int num = random.nextInt(3);
    public String setPosition(){
        int num = random.nextInt(3);
        if(num == 0){
            return "🗿";
        }
        else if(num == 1){
            return "✂️";
        }
        return "📄";
    }
        

}

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApplication {
    private static final int QUIZ_DURATION_SECONDS = 20;
    private static int score = 0;
    private static Timer timer;
    private static boolean quizRunning = false;

    public static void main(String[] args) {
        startQuiz();
    }

    private static void startQuiz() {
        quizRunning = true;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            private int timeRemaining = QUIZ_DURATION_SECONDS;

            @Override
            public void run() {
                if (timeRemaining > 0) {
                    System.out.println("Time remaining: " + timeRemaining + " seconds");
                    timeRemaining--;
                } else {
                    endQuiz();
                }
            }
        }, 0, 1000);

        System.out.println("Welcome to the Quiz!");
        System.out.println("Answer the following questions:");

        // Create and ask questions
        Question q1 = new Question("What is the capital of Japan?", 
                                    "A) Tokyo", 
                                    "B) Beijing", 
                                    "C) Seoul", 
                                    "D) Bangkok", 
                                    "A");

        askQuestion(q1);

        Question q2 = new Question("Which planet is known as the Red Planet?", 
                                    "A) Venus", 
                                    "B) Mars", 
                                    "C) Jupiter", 
                                    "D) Saturn", 
                                    "B");

        askQuestion(q2);

        Question q3 = new Question("Who is known as the father of modern physics?", 
                                    "A) Isaac Newton", 
                                    "B) Albert Einstein", 
                                    "C) Galileo Galilei", 
                                    "D) Max Planck", 
                                    "B");

        askQuestion(q3);

        // Wait for the timer to finish
        try {
            Thread.sleep(QUIZ_DURATION_SECONDS * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        endQuiz();
    }

    private static void askQuestion(Question question) {
        System.out.println(question.getQuestion());
        System.out.println(question.getOptionA());
        System.out.println(question.getOptionB());
        System.out.println(question.getOptionC());
        System.out.println(question.getOptionD());

        Scanner scanner = new Scanner(System.in);
        System.out.print("Your answer: ");
        String userAnswer = scanner.nextLine().toUpperCase();

        if (userAnswer.equals(question.getCorrectAnswer())) {
            System.out.println("Correct!\n");
            score++;
        } else {
            System.out.println("Incorrect. The correct answer is " + question.getCorrectAnswer() + "\n");
        }
    }

    private static void endQuiz() {
        quizRunning = false;
        timer.cancel();
        System.out.println("Quiz ended! Your score is: " + score + " out of the total questions.");
        System.exit(0);
    }
}

class Question {
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;

    public Question(String question, String optionA, String optionB, String optionC, String optionD, String correctAnswer) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
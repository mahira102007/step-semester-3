
import java.util.*;

abstract class Question {
    private final String text;
    private final int points;

    Question(String text, int points) {
        if (text == null || text.trim().isEmpty() || points < 0)
            throw new IllegalArgumentException("Invalid question");
        this.text = text;
        this.points = points;
    }

    public String getText() { return text; }
    public int getPoints() { return points; }
    public abstract boolean isCorrect(String answer);
}

class MultipleChoiceQuestion extends Question {
    private final String correct;

    MultipleChoiceQuestion(String text, int points, String correct) {
        super(text, points);
        this.correct = correct;
    }

    public boolean isCorrect(String answer) {
        return answer != null
            && correct.equalsIgnoreCase(answer.trim());
    }
}

class TrueFalseQuestion extends Question {
    private final boolean correct;

    TrueFalseQuestion(String text, int points, boolean correct) {
        super(text, points);
        this.correct = correct;
    }

    public boolean isCorrect(String answer) {
        return answer != null
            && Boolean.toString(correct).equalsIgnoreCase(answer.trim());
    }
}

class Examination {
    private final String title;
    private final List<Question> questions = new ArrayList<>();

    Examination(String title) { this.title = title; }

    public void addQuestion(Question q) { questions.add(q); }
    public String getTitle() { return title; }
    public List<Question> getQuestions() {
        return Collections.unmodifiableList(questions);
    }
}

class Attempt {
    private final String student;
    private final Examination exam;
    private final Map<Question, String> answers = new LinkedHashMap<>();
    private boolean submitted = false;

    Attempt(String student, Examination exam) {
        this.student = student;
        this.exam = exam;
    }

    public void answer(Question q, String answer) {
        if (submitted)
            throw new IllegalStateException(
                "Cannot change answers for a submitted examination."
            );

        if (!exam.getQuestions().contains(q))
            throw new IllegalArgumentException(
                "Question not in examination"
            );

        answers.put(q, answer);
        System.out.println("Answer recorded for " + q.getText() + ".");
    }

    public void submit() {
        if (submitted)
            throw new IllegalStateException("Exam already submitted");

        submitted = true;
        System.out.println(exam.getTitle()
                + " submitted by " + student + ".");

        int score = 0;
        int total = 0;

        for (Question q : exam.getQuestions()) {
            total += q.getPoints();
            boolean correct = q.isCorrect(answers.get(q));
            int awarded = correct ? q.getPoints() : 0;
            score += awarded;

            System.out.println(q.getText() + ": "
                    + (correct ? "Correct" : "Incorrect")
                    + " (" + awarded + " points)");
        }

        System.out.println("Total score: " + score + "/" + total);
    }
}

public class week8p3 {
    public static void main(String[] args) {
        Examination exam = new Examination("Exam A");

        Question q1 =
            new MultipleChoiceQuestion("Question 1", 5, "C");
        Question q2 =
            new TrueFalseQuestion("Question 2", 5, false);

        exam.addQuestion(q1);
        exam.addQuestion(q2);

        Attempt a = new Attempt("Student 1", exam);
        System.out.println("Exam A started by Student 1.");

        a.answer(q1, "C");
        a.answer(q2, "True");
        a.submit();

        try {
            a.answer(q1, "A");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}

import java.util.Random;

public class Student {

    private final int studentId;
    private final String name;
    private final Random random;
    private final SubmissionStats stats;

    public Student(int studentId, String name, SubmissionStats stats) {
        this.studentId = studentId;
        this.name = name;
        this.stats = stats;
        this.random = new Random();
    }

    public void submitExam() {
        System.out.println(name + " Submission is processing.....");
        try {
            int simulateTime = random.nextInt(100);
            int randomNumber = random.nextInt(100);
            Thread.sleep(simulateTime);
            if (randomNumber < 5) {
                stats.recordFailure();
                System.out.println(name + " Submission Failed");
            } else {
                stats.recordSuccess();
                System.out.println(name + " Submission Successful");
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

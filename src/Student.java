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
        int attempts = 0;
        boolean success = false;
        System.out.println(name + " Submission is processing.....");
        while (attempts < 2 && !success) {
            try {
                attempts++;
                Thread.sleep(random.nextInt(100));
                if (random.nextInt(100) < 5) {
                    throw new RuntimeException(name + "Temporary failure");
                }
                stats.recordSuccess();
                System.out.println(name + " Submission Successful");
                success = true;

            } catch (Exception e) {
                if (attempts == 2) {
                    stats.recordFailure();
                    System.out.println(name + " Submission Failed");
                }
            }
        }
    }
}

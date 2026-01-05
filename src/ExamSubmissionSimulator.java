public class ExamSubmissionSimulator {
    public static void main(String[] args) throws InterruptedException {

        int students = 500;
        int poolSize = Runtime.getRuntime().availableProcessors() * 2;

        NewSubmissionSystem submissionSystem = new NewSubmissionSystem(students, poolSize);

        submissionSystem.processSubmission();
        submissionSystem.shutdown();
    }
}
public class ExamSubmissionSimulator {
    public static void main(String[] args) throws InterruptedException {

        int students = 100000;
        int poolSize = Runtime.getRuntime().availableProcessors() * 4;

        NewSubmissionSystem submissionSystem = new NewSubmissionSystem(students, poolSize);

        submissionSystem.processSubmission();
        submissionSystem.shutdown();
    }
}
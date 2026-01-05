import java.util.concurrent.*;

public class NewSubmissionSystem {

    private final int numOfStudents;
    private final int poolSize;
    private final SubmissionStats stats;
    private ExecutorService executor;

    public NewSubmissionSystem(int numOfStudents, int poolSize) {
        this.numOfStudents = numOfStudents;
        this.poolSize = poolSize;
        this.stats = new SubmissionStats();
    }

    public void processSubmission() {
        executor = Executors.newFixedThreadPool(poolSize);
        CountDownLatch latch = new CountDownLatch(numOfStudents);
        stats.startTimer();

        for (int i = 0; i < numOfStudents; i++) {
            int id = i + 1;

            executor.execute(() -> {
                Student student =
                        new Student(id, "Student-" + id, stats);
                try {
                    student.submitExam();
                } finally {
                    latch.countDown();
                }
            });
        }

        try {
            System.out.println("Waiting for all submissions to finish...");
            latch.await();
            stats.endTimer();
            stats.printResults();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void shutdown() throws InterruptedException {
        System.out.println("Shutting down...");
        executor.shutdown();
        if(!executor.awaitTermination(30, TimeUnit.SECONDS)){
            executor.shutdownNow();
        }
        System.out.println("Submission System shut down");
    }
}
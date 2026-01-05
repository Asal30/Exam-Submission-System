import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class SubmissionStats {

    private final AtomicInteger successful;
    private final AtomicInteger failed;
    private final AtomicLong startTime;
    private final AtomicLong endTime;

    public SubmissionStats() {
        this.successful = new AtomicInteger(0);
        this.failed = new AtomicInteger(0);
        this.startTime = new AtomicLong(0);
        this.endTime = new AtomicLong(0);
    }
    public void startTimer() {
        startTime.set(System.currentTimeMillis());
    }

    public void endTimer() {
        endTime.set(System.currentTimeMillis());
    }

    public void recordSuccess() {
        successful.incrementAndGet();
    }

    public void recordFailure() {
        failed.incrementAndGet();
    }

    public long getTotalTime(){
        return endTime.get() - startTime.get();
    }

    public void printResults() {

        int total = successful.get() + failed.get();
        double successRate = (total == 0) ? 0 : (successful.get() * 100.0) / total;

        System.out.println("\n--- Submission Results ---");
        System.out.println("Total Time : " + getTotalTime() + " ms");
        System.out.println("Total Students : " + total);
        System.out.println("Successful Submissions : " + successful.get());
        System.out.println("Failed Submissions : " + failed.get());
        System.out.println("Success Rate : " + successRate + "%");
    }
}
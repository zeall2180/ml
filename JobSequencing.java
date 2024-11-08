import java.util.Arrays;

class Job {
    String jobId;
    int profit;
    int deadline;

    public Job(String jobId, int profit, int deadline) {
        this.jobId = jobId;
        this.profit = profit;
        this.deadline = deadline;
    }
}

public class JobSequencing {
    
    // Function to perform job sequencing to maximize profit
    public static int jobSequencing(Job[] jobs, int n) {
        // Sort jobs in decreasing order of profit
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        // Find the maximum deadline
        int maxDeadline = 0;
        for (Job job : jobs) {
            maxDeadline = Math.max(maxDeadline, job.deadline);
        }

        // Create an array to store the result of scheduled jobs
        String[] result = new String[maxDeadline];
        boolean[] slot = new boolean[maxDeadline];  // To keep track of available slots

        int totalProfit = 0;

        // Iterate through all jobs
        for (Job job : jobs) {
            // Find a slot from job.deadline - 1 to 0
            for (int j = Math.min(maxDeadline, job.deadline) - 1; j >= 0; j--) {
                if (!slot[j]) {  // If the slot is available
                    result[j] = job.jobId;  // Schedule the job
                    slot[j] = true;         // Mark the slot as filled
                    totalProfit += job.profit;
                    break;
                }
            }
        }

        // Print the job sequence and total profit
        System.out.println("Scheduled Jobs: ");
        for (String jobId : result) {
            if (jobId != null) {
                System.out.print(jobId + " ");
            }
        }
        System.out.println();
        return totalProfit;
    }

    public static void main(String[] args) {
        Job[] jobs = {
            new Job("A", 100, 2),
            new Job("B", 19, 1),
            new Job("C", 27, 2),
            new Job("D", 25, 1),
            new Job("E", 15, 3)
        };

        int n = jobs.length;
        int maxProfit = jobSequencing(jobs, n);
        System.out.println("Maximum Profit: " + maxProfit);
    }
}

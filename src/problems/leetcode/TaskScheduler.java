package problems.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/task-scheduler/
 */
public class TaskScheduler {

    public static int leastInterval(char[] tasks, int n) {
        int[] taskCounts = new int['Z' - 'A' + 1];
        for (char task : tasks) {
            taskCounts[task - 'A']++;
        }

        // our heap is a max-heap which means poll() will give us
        // the task with max count
        PriorityQueue<Integer> queue = new PriorityQueue<>('Z' - 'A' + 1, Collections.reverseOrder());
        for (int f : taskCounts) {
            if (f > 0) {
                queue.add(f);
            }
        }

        int totalTime = 0;
        while (!queue.isEmpty()) {
            int i = 0;
            List<Integer> remainingTasks = new ArrayList<>();

            // try to get n tasks from the queue
            while (i <= n) {
                if (!queue.isEmpty()) {
                    if (queue.peek() > 1)
                    {
                        // this task still has more work. it will be added back to the queue
                        remainingTasks.add(queue.poll() - 1);
                    }
                    else
                    {
                        // this task is done.
                        queue.poll();
                    }
                }

                totalTime++;
                if (queue.isEmpty() && remainingTasks.size() == 0) {
                    break;
                }

                i++;
            }

            queue.addAll(remainingTasks);
        }

        return totalTime;
    }

    /**
     * Main idea:
     * <p>
     * - If we find the number the value of the maximum frequency and we find
     * the total number of tasks that don't have the maximum frequency, we can
     * notice that if (total # of tasks with a frequency less than max / n) >= (maximum frequency)
     * we can simply return the total tasks.
     * - In the other case we can derive an equation that provides us with
     * the total number of extra idle we need + total tasks
     * (max-1) x (n+1) + (# of letters that have the max freq).
     * - This is because we know we at least need to have (max-1) separate
     * boxes to process the task with the max frequency, and for each of those
     * boxes it requires at least (n+1) tasks whether it be an actual task or
     * idle.
     * - The (# of letters that have the max freq) will be added as well
     * because the last box (see below) doesn't require to have an idle or
     * tasks in between to finish the last task.
     * <p>
     * Example:
     * A: 6
     * B: 1
     * C: 2
     * D: 5
     * E: 4
     * n = 2
     * <p>
     * Max freq: 6
     * number tasks with freq less than max: 12
     * [A D E] [A D E] [A D E] [A D E] [A D C B] [A C]
     * <p>
     * Notice that you can break it up such a way that we don't need to idle to
     * complete all the tasks.
     * But if n = 3
     * We must have idles in order to complete all the tasks
     * [A D E C] [A D E B] [A D E C] [A D E IDLE] [A D IDLE IDLE] [A] = (6-1)*(3+1) + 1
     */
    public static int leastIntervalON(char[] tasks, int n) {
        if (n == 0) {
            return tasks.length;
        }

        int[] taskCounts = new int['Z' - 'A' + 1];
        int maxFreq = 0, totalTaskCount = tasks.length;
        for (char c : tasks) {
            taskCounts[c - 'A']++;
            maxFreq = Math.max(maxFreq, taskCounts[c - 'A']);
        }

        int maxFreqTaskCount = 0;
        for (int i : taskCounts) {
            if (i == maxFreq) {
                maxFreqTaskCount++;
            }
        }

        if ((totalTaskCount - (maxFreqTaskCount * maxFreq)) / n >= maxFreq) {
            return totalTaskCount;
        }

        return (maxFreq - 1) * (n + 1) + maxFreqTaskCount;
    }

    public static void main(String[] args) {
        char[] tasks = new char[]{'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int n = 2;

        System.out.println(leastIntervalON(tasks, n));
        System.out.println(leastInterval(tasks, n));
    }

    /*
     * Input: tasks = ["A","A","A","B","B","B"], n = 2
     * Output: 8
     * Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
     *
     *
     * input: A, A, A, B, B
     *
     * n = 1
     * schedule: A, B, A, B, A
     *
     * n = 2
     * schedule: A, B, idle, A, B, idle, A
     *
     *
     * input: A, A, A, B, B, C
     *
     * n = 2
     * schedule: A, B, C, A, B, idle, A
     *
     * tasks:
     * A -> {last sch: -1, rem: 3}
     * B -> {last sch: -1, rem: 2}
     * C -> {last sch: -1, rem: 1}
     *
     * schedule: A
     *
     * A -> {last sch: 0, rem: 2}
     * B -> {last sch: -1, rem: 2}
     * C -> {last sch: -1, rem: 1}
     *
     * schedule: A, B
     *
     * A -> {last sch: 0, rem: 2}
     * B -> {last sch: 1, rem: 1}
     * C -> {last sch: -1, rem: 1}
     *
     * schedule: A, B, C
     *
     * A -> {last sch: 0, rem: 2}
     * B -> {last sch: 1, rem: 1}
     * C -> {last sch: 2, rem: 0}
     *
     * schedule: A, B, C, A
     *
     * A -> {last sch: 3, rem: 1}
     * B -> {last sch: 1, rem: 1}
     * C -> {last sch: 2, rem: 0}
     *
     * schedule: A, B, C, A, B
     *
     * A -> {last sch: 3, rem: 1}
     * B -> {last sch: 4, rem: 0}
     * C -> {last sch: 2, rem: 0}
     *
     * schedule: A, B, C, A, B, idle,
     */


}

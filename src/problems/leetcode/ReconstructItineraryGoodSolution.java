package problems.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class ReconstructItineraryGoodSolution {

    private HashMap<String, PriorityQueue<String>> map = new HashMap<>();
    private List<String> itinerary = new LinkedList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            map.putIfAbsent(ticket.get(0), new PriorityQueue<>());
            // add the "destination" to pq for dfs search, in lexical order by default
            map.get(ticket.get(0)).offer(ticket.get(1));
        }
        findItinerary("JFK");
        Collections.reverse(itinerary);
        return itinerary;
    }

    public void findItinerary(String departure) {
        // If destination exists. When arriving the last departure, add it to res directly
        while (map.containsKey(departure) && !map.get(departure).isEmpty()) {
            // get the next destination, departure and iterate
            findItinerary(map.get(departure).poll());
        }
        itinerary.add(departure);
    }

    public static void main(String[] args) {
        List<List<String>> tickets2 = Arrays.asList(Arrays.asList("JFK", "SFO"), Arrays.asList("JFK", "ATL"), Arrays.asList("SFO", "ATL"), Arrays.asList("ATL", "JFK"), Arrays.asList("ATL", "SFO"));

        List<List<String>> tickets3 = Arrays.asList(Arrays.asList("JFK", "KUL"), Arrays.asList("JFK", "NRT"), Arrays.asList("NRT", "JFK"));

        List<String> itinerary = new ReconstructItineraryGoodSolution().findItinerary(tickets2);
        System.out.println(itinerary);
    }

}

package problems.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/reconstruct-itinerary/
 */
public class ReconstructItinerary {

    public static void main(String[] args) {
        List<List<String>> tickets = Arrays.asList(Arrays.asList("MUC", "LHR"),
                Arrays.asList("JFK", "MUC"), Arrays.asList("SFO", "SJC"), Arrays.asList("LHR", "SFO"));

        List<List<String>> tickets2 = Arrays.asList(Arrays.asList("JFK", "SFO"), Arrays.asList("JFK", "ATL"), Arrays.asList("SFO", "ATL"), Arrays.asList("ATL", "JFK"), Arrays.asList("ATL", "SFO"));

        List<List<String>> tickets3 = Arrays.asList(Arrays.asList("JFK", "KUL"), Arrays.asList("JFK", "NRT"), Arrays.asList("NRT", "JFK"));

        List<String> itinerary = findItinerary(tickets3);
        System.out.println(itinerary);
    }

    public static List<String> findItinerary(List<List<String>> tickets) {
        Map<String, List<String>> ticketsMap = new HashMap<>();
        for (List<String> ticket : tickets) {
            ticketsMap.computeIfAbsent(ticket.get(0), from -> new ArrayList<>()).add(ticket.get(1));
        }

        for (List<String> destinations : ticketsMap.values()) {
            Collections.sort(destinations);
        }


        List<String> itinerary = new ArrayList<>();
        findItinerary(ticketsMap, "JFK", itinerary, tickets.size());

        return itinerary;
    }

    private static boolean findItinerary(Map<String, List<String>> ticketsMap, String from, List<String> itinerary, int ticketCount) {
        itinerary.add(from);
        if (itinerary.size() == ticketCount + 1) {
            return true;
        }

        List<String> destinations = ticketsMap.get(from);
        if (destinations == null) {
            itinerary.remove(itinerary.size() - 1);
            return false;
        }

        for (int i = 0; i < destinations.size(); i++) {
            String destination = destinations.remove(i);
            if (findItinerary(ticketsMap, destination, itinerary, ticketCount)) {
                return true;
            }
            destinations.add(i, destination);
        }
        itinerary.remove(itinerary.size() - 1);
        return false;
    }

}

// Java Electoral College Project 11/25/2020

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ElectoralCollege {
    public static ArrayList<State> states = new ArrayList<>();

    // Simulations
    public static void main(String args[]) {
        readFile("electoral_data.csv");
        State[] highestVCP = highVoteCountParty(states);
        System.out.println("Highest Electoral Vote Count for Republicans: " + highestVCP[0].getStateName() + "\nHighest Electoral Vote Count for Democrats: " + highestVCP[1].getStateName() + "\nHighest Electoral Vote Count for Swing States: " + highestVCP[2].getStateName());
        System.out.println("Total Electoral Votes: " + totalVotes(states));
        System.out.println("Party with most Electoral Votes: " + topParty(states));

        // Decided Swing States iterated 100+ times to find out who won per time
        for (int i = 0; i <= 111; i++) {
            ArrayList<State> decidedStates = decideSwing(); // loop in method
            System.out.println("New Party with most Electoral Votes: " + topParty(decidedStates));
        }

    }

    // Reads file line by line, data from each line used to create new State object
    public static void readFile(String fileName) {
        try {
            File fn = new File(fileName);
            Scanner scan = new Scanner(fn);
            scan.nextLine();

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                State newState = new State();

                String[] lineArray = line.split(",");
                newState.setStateName(lineArray[0]);
                newState.setVoteCount(Integer.parseInt(lineArray[1]));
                newState.setParty(lineArray[2]);

                states.add(newState);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Highest Electoral Vote Counts per Party {R, B, P}
    public static State[] highVoteCountParty(ArrayList<State> stateArrayList) {
        State[] topResults = new State[3];
        topResults[0] = new State(); // State R
        topResults[1] = new State(); // State B
        topResults[2] = new State(); // State P

        for (State s : stateArrayList) {
            switch (s.getParty()) {
                case "R":
                    if (s.getVoteCount() > topResults[0].getVoteCount()) {
                        topResults[0] = s;
                    }
                    break;

                case "B":
                    if (s.getVoteCount() > topResults[1].getVoteCount()) {
                        topResults[1] = s;
                    }
                    break;

                case "P":
                    if (s.getVoteCount() > topResults[2].getVoteCount()) {
                        topResults[2] = s;
                    }
                    break;
            }
        }
        return topResults;
    }

    // Total amount of votes
    public static int totalVotes(ArrayList<State> stateArrayList) {
        int total = 0;
        for (State t : stateArrayList) {
            total += t.getVoteCount();
        }
        return total;
    }

    // Highest between Republican or Democrat
    public static String topParty(ArrayList<State> stateArrayList) {
        int r = 0; // Republican
        int d = 0; // Democrat
        for (State p : stateArrayList) {
            switch (p.getParty()) {
                case "R":
                    r += p.getVoteCount();
                    break;
                case "B":
                    d += p.getVoteCount();
                    break;
            }
        }

        String tParty = "";
        if (r > d) {
            tParty = "Republican";
        } else {
            tParty = "Democrat";
        }
        return tParty;
    }

    // Assigns Swing States to Republican or Democrat Randomly
    public static ArrayList<State> decideSwing() {
        ArrayList<State> copyOfStates = new ArrayList<>();
        for(State copy : states){
            State temp = new State();
            temp.setStateName(copy.getStateName());
            temp.setVoteCount(copy.getVoteCount());
            temp.setParty(copy.getParty());
            copyOfStates.add(temp);
        }

        for (State s : copyOfStates) {
            if (s.getParty().equals("P")) {
                int random = new Random().nextInt(2);
                if (random == 0) {
                    s.setParty("R");
                } else {
                    s.setParty("B");
                }
            }
        }
        return copyOfStates;
    }
}
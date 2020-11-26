public class State {

    private String sn;
    private int vc;
    private String p;

    public void setStateName(String stateName) {
        sn = stateName;
    }

    public String getStateName() {
        return sn;
    }

    public void setVoteCount(int voteCount) {
        vc = voteCount;
    }

    public int getVoteCount() {
        return vc;
    }

    public void setParty(String party) {
        p = party;
    }

    public String getParty() {
        return p;
    }
}

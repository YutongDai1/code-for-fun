package constants;

public enum Constants {
    /**
     * MAX_CLIENT_SIZE
     */
    MAX_CLIENT_SIZE(10),
    /**
     * TCP_SERVER_PORT
     */
    TCP_SERVER_PORT(9999);

    private int index;

    /**
     * index
     * @param index index
     */
    Constants(int index) {
        this.index = index;
    }

    /**
     * index
     * @return index
     */
    public int getIndex() {
        return index;
    }

}

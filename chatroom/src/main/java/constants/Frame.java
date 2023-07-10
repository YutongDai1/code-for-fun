package constants;

public enum Frame {

    /**
     * Connect message:
     * int Message Identifier: CONNECT_MESSAGE
     */
    CONNECT_MESSAGE(19),
    /**
     * Connect response:
     * int Message Identifier: CONNECT_RESPONSE
     */
    CONNECT_RESPONSE(20),
    /**
     * Disconnect message:
     * int Message Identifier: DISCONNECT_MESSAGE
     */
    DISCONNECT_MESSAGE(21),
    /**
     * Query users:
     * int Message Identifier: QUERY_CONNECTED_USERS
     */
    QUERY_CONNECTED_USERS(22),
    /**
     * Query users response:
     * int Message Identifier: QUERY_USERS_RESPONSE
     */
    QUERY_USERS_RESPONSE(23),
    /**
     * Broadcast Message:
     * int Message Identifier: BROADCAST_MESSAGE
     */
    BROADCAST_MESSAGE(24),
    /**
     * Direct Message:
     * int Message Identifier: DIRECT_MESSAGE
     */
    DIRECT_MESSAGE(25),
    /**
     * Failed Message:
     * int Message Identifier: FAILED_MESSAGE
     */
    FAILED_MESSAGE(26),
    /**
     * Send Insult:
     * int Message Identifier: SEND_INSULT
     */
    SEND_INSULT(27),
    /**
     * Disconnect response:
     * int Message Identifier: DISCONNECT_RESPONSE
     */
    DISCONNECT_RESPONSE(28),
    /**
     * MESSAGE_RECEIVE:
     * int Message Identifier: MESSAGE_RECEIVE
     */
    MESSAGE_RECEIVE(29),
    /**
     *  MESSAGE_SEND_RESPONSE:
     *  int Message Identifier: MESSAGE_SEND_RESPONSE
     */
    MESSAGE_SEND_RESPONSE(30);

    private final int id;

    Frame(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

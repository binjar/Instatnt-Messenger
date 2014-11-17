import java.io.*;

/**
 * This class defines the different type of messages that will be exchanged between the
 * Clients and the Server.
 */

public class ChatMessage implements Serializable {

    // The different types of message sent by the Client
    // ONLINE to receive the list of the users connected
    // MESSAGE an ordinary message
    // LOGOUT to disconnect from the Server

    static final int ONLINE = 0, MESSAGE = 1, LOGOUT = 2;

    private int type;
    private String message, rcvr;
    private String[] onln;
    

    /**
     * @param type
     * @param message
     * @param rcvr
     * initialize message & receiver.
     */
    ChatMessage(int type, String message, String rcvr) {
        this.type = type;
        this.message = message;
        this.rcvr = rcvr;
    }

    /**
     * @param type
     * @param onln 
     * initialize list of connected user.
     */
    ChatMessage(int type, String[] onln) {
        this.type = type;
        this.onln = onln;
    }

    /**
     * for log out
     * @param type
     */
    public ChatMessage(int type) {
        this.type = type;
    }
    
    
    /**
     * @return type of response
     */
    int getType() { return type; }

    /**
     * @return message
     */
    String getMessage() { return message; }
        
    /**
     * @return receiver of the message
     */
    String getRcvr() { return rcvr; }
    
    /**
     * @return Online user list.
     */
    String[] getOnlineList(){ return onln; }
}
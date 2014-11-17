import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import java.io.*;

public class Client {
    private ObjectInputStream sInput;
    private ObjectOutputStream sOutput;
    private Socket socket;

    private ClientGUI cg;

    private String server, username;
    private int port;
    public boolean rqst_onln = false;
    
    Client(String server, int port, String username, ClientGUI cg) {
        this.server = server;
        this.port = port;
        this.username = username;
        this.cg = cg;
    }
    
    public boolean start() {
        try {
            socket = new Socket(server, port);
        } catch(IOException e) {
            display("Cannot connect to server: " + e);
            return false;
        }

        String welcome_msg = "You are now connected with " + socket.getInetAddress() + ":" + socket.getPort();
        display(welcome_msg);

        try {
            sInput  = new ObjectInputStream(socket.getInputStream());
            sOutput = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            display("Creating new I/O Stream failed : " + e);
            return false;
        }
        
        new ListenFromServer().start();
        
        try {
            sOutput.writeObject(username);
        }
        catch (IOException e) {
            display("Login Failed : " + e);
            disconnect();
            return false;
        }
        
        // if worked
        return true;
    }

    private void display(String msg) {
        cg.append(msg + "\n");
    }
	
    void sendMessage(ChatMessage msg) {
        try {
            sOutput.writeObject(msg);
        } catch(IOException e) {
            display("Writing to server failed : " + e);
        }
    }

    void disconnect() {
        try { 
            if(sInput != null) { sInput.close(); }
        } catch(IOException e) {
            display("Cannot close input stream. " + e.getMessage());
        }
        
        try {
            if(sOutput != null) sOutput.close();
        } catch(IOException e) {
            display("Cannot close Output stream. " + e.getMessage());
        }
        
        try {
            if(socket != null) socket.close();
        } catch(IOException e) {
            display("Cannot close socket. " + e.getMessage());
        }

        if(cg != null)
            cg.connectionFailed();
    }
    
        
    /*A class that waits for the message from the server and append them to the JTextArea*/
    class ListenFromServer extends Thread {
        private ChatMessage cm;

        @Override
        public void run() {
            while(true) {
                try {
                    cm = (ChatMessage) sInput.readObject();
                } catch (IOException e) {
                    display(username + " Error: reading Streams: " + e);
                    disconnect();
                    break;				
                } catch(ClassNotFoundException e2) {
                    break;
                }
                
                switch(cm.getType()){
                    case ChatMessage.MESSAGE:
                        cg.append(cm.getMessage());
                        break;
                      
                    case ChatMessage.ONLINE:
                        cg.showOnln(cm.getOnlineList());
                        break;
                        
                    case ChatMessage.LOGOUT:
                        disconnect();
                }
            }
        }
    }
}
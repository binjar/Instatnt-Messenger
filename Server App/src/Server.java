import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Server {
    private static int uniqueId;
    private ArrayList<ClientThread> al;
    
    private ServerGUI sg;
    private SimpleDateFormat sdf;

    private int port;

    private boolean keepGoing;


    public Server(int port, ServerGUI sg) {
        this.sg = sg;
        this.port = port;
        sdf = new SimpleDateFormat("HH:mm:ss");
        al = new ArrayList<ClientThread>();
        
    }

    public void start() {
        keepGoing = true;
        
        try {
            ServerSocket serverSocket = new ServerSocket(port);

            // infinite loop to wait for connections
            while(keepGoing) {
                display("Waiting for Clients on port " + port + ".\n");

                // accept connection
                Socket socket = serverSocket.accept();
                
                // if asked to stop
                if(!keepGoing)
                    break;
                
                // make a thread of it
                ClientThread t = new ClientThread(socket);
                al.add(t);  // save it in the ArrayList
                t.start();  //start the thread
            }
            
            // When asked to stop
            try {
                serverSocket.close();
                for(int i = 0; i < al.size(); ++i) {
                    ClientThread tc = al.get(i);
                    try {
                        tc.sInput.close();
                        tc.sOutput.close();
                        tc.socket.close();
                    }
                    catch(IOException e) {
                        display("Error: " + e);
                    }
                }
            }
            catch(Exception e) {
                display("Closing connections error: " + e);
            }
        }
        // something is wrong
        catch (IOException e) {
            display("Error on new ServerSocket: " + e);
        }
    }

    //To stop the Server
    protected void stop() {
        keepGoing = false;
        
        try {
            new Socket("localhost", port);
        }
        catch(Exception e) {
            display("Error: " + e);
        }
    }
    
    
    //Display an event (not a message)
    private void display(String msg) {
        sg.appendEvent(now() + msg + "\n");
    }
    
    public String now(){
        return sdf.format(new Date()) + " - ";
    }
    
    /*
     *  to broadcast a message to all Clients
     */
    private synchronized void broadcast(String rcvr, String sndr, String message) {
        String msg = now() + sndr + ": " + message;
        int track = 0;
        for(int i=0; i<al.size(); i++) {
            ClientThread ct = al.get(i);
            if(rcvr.equals("")){
                if(ct.username.equalsIgnoreCase(sndr)){
                    ct.sendMessage(new ChatMessage(ChatMessage.MESSAGE, now() + "You: " + message, ""));
                    ct.sendMessage(new ChatMessage(ChatMessage.MESSAGE, now() + "Server response: " + message, ""));
                    track = 2;
                }
            } else if(rcvr.equalsIgnoreCase("all")){
                if(!ct.username.equals(sndr)){
                    ct.sendMessage(new ChatMessage(ChatMessage.MESSAGE, msg, ""));
                    track = 1;
                }
            } else if(rcvr.equalsIgnoreCase(ct.username)) {
                ct.sendMessage(new ChatMessage(ChatMessage.MESSAGE, msg, ""));
                track = 1;
            }
        }
        
        if(track == 1){
            for(int i=0; i<al.size(); i++) {
                ClientThread ct = al.get(i);
                if(sndr.equalsIgnoreCase(ct.username)){
                    ct.sendMessage(new ChatMessage(ChatMessage.MESSAGE, now() + "You: " + message, ""));
                } 
            }
        } else if(track == 0) {
            for(int i=0; i<al.size(); i++) {
                ClientThread ct = al.get(i);
                if(sndr.equalsIgnoreCase(ct.username)){
                    ct.sendMessage(new ChatMessage(ChatMessage.MESSAGE, now() + "Message sending failed.", ""));
                } 
            }
        }
    }

    // for a client who log out
    synchronized void remove(int id) {
        for(int i = 0; i < al.size(); ++i) {
            ClientThread ct = al.get(i);
            
            if(ct.id == id) {
                al.remove(i);
                return;
            }
        }
    }

    /**
     * This thread will run for each client
     */
    class ClientThread extends Thread {
        Socket socket;
        ObjectInputStream sInput;
        ObjectOutputStream sOutput;
        
        int id; // Unique id (easier for deconnection)
        String username;    // the Username of the Client
        ChatMessage cm; // the only type of message it will receive
        String date;    // connected date/time

        // Thread Constructor
        ClientThread(Socket socket) {
            id = ++uniqueId;
            this.socket = socket;
            
            // Creating both Data Stream
            try {
                sOutput = new ObjectOutputStream(socket.getOutputStream());
                sInput  = new ObjectInputStream(socket.getInputStream());
                
                username = (String) sInput.readObject();
                display(username + " just connected.");
            } catch (IOException | ClassNotFoundException e) {
                display("Error: " + e);
            }
        }

        
       
        @Override
        public void run() {
            // to loop until Stop
            boolean keepGoing = true;
            while(keepGoing) {
                try {
                    cm = (ChatMessage) sInput.readObject();
                } catch (IOException e) {
                    keepGoing = false;
                    display("Error: " + e);
                    break;				
                } catch(ClassNotFoundException e2) {
                    display("Error: " + e2);
                    break;
                }
                
                String message = cm.getMessage();
                String rcvr = cm.getRcvr();
                
                switch(cm.getType()) {
                    case ChatMessage.MESSAGE:
                        broadcast(rcvr, username, message);
                        break;
                      
                    case ChatMessage.ONLINE:
                        String onln = "";
                        for(int i=0; i<al.size(); i++){
                            ClientThread ct = al.get(i);
                            onln += ct.username + ";";
                        }
                        String[] onln_lst = onln.split(";");
                        
                        sendMessage(new ChatMessage(ChatMessage.ONLINE, onln_lst));
                        break;
                        
                    case ChatMessage.LOGOUT:
                        keepGoing = false;
                        break;
                }
            }
            remove(id);
            close();
        }

        
        private void close() {
            try {
                if(sOutput != null) sOutput.close();
            } catch(IOException e) {
                display("Error: " + e);
            }
            
            try {
                if(sInput != null) sInput.close();
            } catch(Exception e) {
                display("Error: " + e);
            }
            try {
                if(socket != null) socket.close();
            } catch (Exception e) {
                display("Error: " + e);
            }
        }

        
        void sendMessage(ChatMessage msg) {
            try {
                sOutput.writeObject(msg);
            }
            catch(IOException e) {
                display("Writing to server failed : " + e);
            }
        }
    }
}
package sdf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        
        System.out.println("Starting server on port 3000"); 
        // Create a server socket and listen to a specific port (once)
        ServerSocket server =  new ServerSocket(3000); 

        // convert into a while loop > to make it run continually
        while (true) {
            // Wait for connection
            System.out.println("Waiting for incoming connection");
            Socket conn = server.accept();
            
            System.out.println("Got a connection");

            // do something
            // get the input stream, read the data from the client 
            InputStream is = conn.getInputStream(); 
            ObjectInputStream ois = new ObjectInputStream(is); 

            // Reader r = new InputStreamReader(is); 
            //     // decorator to help to read
            // BufferedReader br = new BufferedReader(r); 

            String input = ois.readUTF(); 

            // String input = br.readLine(); 
            System.out.printf(">>> from client: %s\n", input);  

            //change it to uppercase 
            input = input.toUpperCase(); 
            System.out.printf(">>> from client: %s\n", input);  

            // write the results back to the client 
            OutputStream os = conn.getOutputStream(); 
            ObjectOutputStream oos = new ObjectOutputStream(os); 
            oos.writeUTF(input); 
            oos.flush(); 

            // Close the socket connection, all streams will be closed 
            conn.close(); 
        }        

    }
}
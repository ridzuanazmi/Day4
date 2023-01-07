package sdf;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
public class Client {
    public static void main(String[] args) throws IOException {
        
        // Connect to the server listening on port 3000
        // 127.0.0.1 or localhost means my local computer 
        // otherwise have to find out what is the IP address of the server
        Socket conn = new Socket("127.0.0.1" , 3000); 

        System.out.println("Connected to server on localhost:3000");

        Console cons = System.console(); 
        String line = cons.readLine("What would you like to uppercase today?"); 

        // Do something 
        OutputStream os = conn.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os); 

        oos.writeUTF(line);
        oos.flush(); 

        // Writer w = new OutputStreamWriter(os); 
        
        // w.write(line);
        // w.flush(); // need to flush, otherwise will print null from client 
        // w.close(); 

        // Get input from the server 
        InputStream is = conn.getInputStream(); 
        // Reader rr = new InputStreamReader(iss); 
        // BufferedReader brr = new BufferedReader(rr); 
        
        ObjectInputStream ois = new ObjectInputStream(is); 
        String result = ois.readUTF();  

        System.out.printf(">>> from server: %s\n", result);  

        // close the connection 
        conn.close(); 

    }
    
}

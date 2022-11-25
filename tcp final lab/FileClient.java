import java.io.BufferedOutputStream;
import java.io.FileOutputStream; 
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;

public class FileClient {
public static void main(String[] args) throws Exception{
    //socket initialization
    Socket socket = new Socket(InetAddress.getByName("localhost"), 2333);
    byte[] contents = new byte[10000];

    FileOutputStream fos = new FileOutputStream("sendd");
    try (BufferedOutputStream bos = new BufferedOutputStream(fos)) {
        //read stream which has been sent from server using input stream
        InputStream is = socket.getInputStream(); 
        int bytesRead = 0; 
        while((bytesRead=is.read(contents))!=-1)
        bos.write(contents, 0, bytesRead); 
        bos.flush();
    }
    socket.close();
    System.out.println("File saved successfully!"); }
}

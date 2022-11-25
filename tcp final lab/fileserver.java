import java.io.*;
import java.net.*;

public class fileserver 
{
    public static void main(String[] args) throws Exception
    {
        //creating a new socket in the server 
        ServerSocket ssock = new ServerSocket(2333);
        //accepting the connection
        Socket socket = ssock.accept();

        InetAddress IA = InetAddress.getByName("localhost");

        //server taking file as input 
        File file = new File("sendd.txt");
        FileInputStream fis = new FileInputStream(file);
        try (BufferedInputStream bis = new BufferedInputStream(fis)) {
            OutputStream os = socket.getOutputStream();

            //file contents are converted into the array- contents_of_file
            byte[] contents_of_file;
            long fileLength = file.length();


            long current = 0;
            long start = System.nanoTime();

            while(current!=fileLength){
                int size = 10000;
                if(fileLength - current >= size)
                {current += size; }
                else{
                size = (int)(fileLength - current); 
                current = fileLength;
                }
                contents_of_file = new byte[size];
                //reading the contents of the file
                bis.read(contents_of_file, 0, size); 
                os.write(contents_of_file);
                } 
                os.flush();
        }

        socket.close();
        ssock.close();
        System.out.println("File sent succesfully!");
    }
}

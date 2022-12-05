package FunctionPKG;

import java.util.*;
import java.io.*;

public class ShowDataApp {

    public static void showData() throws IOException {

         
        try (FileReader fileReader = new FileReader("database.txt");
            BufferedReader bufferRead = new BufferedReader(fileReader);) {
                
            String data = bufferRead.readLine();
            int nomer = 0;

            System.out.println("\n-------------------------------------------------------------------------------------------------");
            System.out.println("| No |\tPenulis                |\tJudul Buku             | Tahun |\tPenerbit");
            System.out.println("-------------------------------------------------------------------------------------------------");
            while(data != null) {
                nomer++;
                StringTokenizer sToken = new StringTokenizer(data, ",");
    
                System.out.printf("| %2d ", nomer);
                System.out.printf("|\t%-22s ", sToken.nextToken());
                System.out.printf("|\t%-22s ", sToken.nextToken());
                System.out.printf("| %-5s ", sToken.nextToken());
                System.out.printf("|\t%s ", sToken.nextToken());
                System.out.println();
                System.out.println("-------------------------------------------------------------------------------------------------");
    
                data = bufferRead.readLine();
            }
            bufferRead.close();
        } catch (Exception e) {
            System.err.println("\n\nData Not Found !! [messege] : " + e);
            System.err.println("Silkan tambah data");
            AddDataApp.addData();
            return;
        }
        
    }
}

package FunctionPKG;

import java.util.*;
import java.io.*;
import utilSystem.*;


public class DeleteDataApp {
    
    public static void delData() throws IOException {

        // DB ORI for main saved database
        File dbOri = new File("database.txt");
        FileReader inputDbOri = new FileReader(dbOri);
        BufferedReader bufferDbOri = new BufferedReader(inputDbOri);

        // Temp DB for second database temporary
        File dbTemp = new File("tempDB.txt");
        FileWriter inputDbTemp = new FileWriter(dbTemp);
        BufferedWriter bufferDbTemp = new BufferedWriter(inputDbTemp);

        Scanner inputDel = new Scanner(System.in);
        int number;
        int numberCount = 0;
        String data;
        boolean isMatch;

        //Show Data
        System.out.println("List Data");
        ShowDataApp.showData();

        // Pilih Data
        System.out.print("Masukan nomer data yang ingin dihapus : ");
        number = inputDel.nextInt();

        // Match Data
        data = bufferDbOri.readLine();
        while (data != null) {

            numberCount++;
            isMatch = false;
            StringTokenizer st = new StringTokenizer(data, ",");

            if (number == numberCount) {
                // Confrim Data
                System.out.println("\n-------------------------------------------------------------------------------------------------");
                System.out.println("-----------------------------------------KONFRIMASI DATA-----------------------------------------");
                System.out.println("-------------------------------------------------------------------------------------------------");
                System.out.println("Nama Penulis    : " + st.nextToken());
                System.out.println("Judul Buku      : " + st.nextToken());
                System.out.println("Tahun Terbit    : " + st.nextToken());
                System.out.println("Penerbit        : " + st.nextToken());

                isMatch = SystemMethod.isYesNo("Apakah ingin menghapus data ini ? ");
            }
            // Tulis Data di dbTemp
            if (isMatch) {
                SystemMethod.clearScreen();
                System.out.println("\n-------------------------------------------------------------------------------------------------");
                System.out.println("-----------------------------------------DATA TERHAPUS-------------------------------------------");
                System.out.println("-------------------------------------------------------------------------------------------------\n");
            } else {
                bufferDbTemp.write(data);
                bufferDbTemp.newLine();
            }
            data = bufferDbOri.readLine();
        }
        bufferDbTemp.flush();

        bufferDbOri.close();
        bufferDbTemp.close();
        
        dbOri.delete();
        dbTemp.renameTo(dbOri);
    }
}

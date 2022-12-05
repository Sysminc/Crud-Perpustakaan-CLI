package FunctionPKG;

import utilSystem.*;
import java.util.*;
import java.io.*;
import java.time.Year;

public class AddDataApp {
    
    public static void addData() throws IOException {
        Scanner inputData = new Scanner(System.in);

        FileWriter fileWriter = new FileWriter("database.txt", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        try {
            
            String nama, judul, tahun, penerbit;
            boolean isData = true; 
            boolean isAdd;

            while (isData != false) {
                System.out.print("\nMasukan nama penulis : ");
                nama = inputData.nextLine();
                System.out.print("Masukan judul buku : ");
                judul = inputData.nextLine();
                System.out.print("Masukan tahun penerbit : ");
                tahun = inputData.nextLine();
                while (tahunIsValid(tahun) != true ) {
                    System.out.print("Masukan tahun penerbit : ");
                    tahun = inputData.nextLine();
                }
                System.out.print("Masukan nama penerbit : ");
                penerbit = inputData.nextLine();

                String[] newData = {nama,judul,tahun,penerbit};

                System.out.println("\n-------------------------------------------------------------------------------------------------");
                System.out.println("-------------------------------------MENCARI DATA YANG TERSEDIA----------------------------------");
                System.out.println("-------------------------------------------------------------------------------------------------");
                SearchDataApp.findData(newData);

                // Confrim Data
                System.out.println("\n-------------------------------------------------------------------------------------------------");
                System.out.println("-----------------------------------------KONFRIMASI DATA-----------------------------------------");
                System.out.println("-------------------------------------------------------------------------------------------------");
                System.out.println("Nama Penulis    : " + nama);
                System.out.println("Judul Buku      : " + judul);
                System.out.println("Tahun Terbit    : " + tahun);
                System.out.println("Penerbit        : " + penerbit);

                isAdd = SystemMethod.isYesNo("Apakah ingin menambahkan data ini ? ");

                if (isAdd) {
                    // Data dibaca oleh 
                    SystemMethod.clearScreen();
                    bufferedWriter.write(nama + "," + judul + "," + tahun + "," + penerbit);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    System.out.println("\n-------------------------------------------------------------------------------------------------");
                    System.out.println("-----------------------------------------DATA DITAMBAHKAN----------------------------------------");
                    System.out.println("-------------------------------------------------------------------------------------------------\n");
                    ShowDataApp.showData();
                }
                isData = SystemMethod.isYesNo("Input lagi ?");
            }
            bufferedWriter.close();
            
        } catch (IOException e ) {
            System.out.println("Data Not Found !! [messege] : " + e);
        }
    }

    public static boolean tahunIsValid(String tahun) {
        try {
            Year.parse(tahun);
            return true;    
        } catch (Exception e) {
            System.err.println("\nTahun tidak valid input kembali");
            return false;
        }
    }
}

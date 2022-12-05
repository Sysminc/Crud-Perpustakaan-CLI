package FunctionPKG;

import java.util.*;

import utilSystem.SystemMethod;

import java.io.*;

public class SearchDataApp {
    
    public static void serachData() {

        Scanner inputSearch = new Scanner(System.in);
        String data;
        String[] keywords;

        System.out.print("\nInput data buku yang ingin dicari : ");
        data = inputSearch.nextLine();
        System.out.println("Mencari Data : " + data);
        keywords = data.split("\\s+");
        try {
            findData(keywords);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected static void findData(String[] keywords) throws IOException  {

         
        try (FileReader dataFile = new FileReader("database.txt");
            BufferedReader dFile = new BufferedReader(dataFile);) {

            String data = dFile.readLine();
            int nomer = 0;
            boolean isFind = false;
            
            System.out.println("\n-------------------------------------------------------------------------------------------------");
            System.out.println("| No |\tPenulis                |\tJudul Buku             | Tahun |\tPenerbit\t");
            System.out.println("-------------------------------------------------------------------------------------------------");
            while(data != null) {

                // Cek Keywords 
                
                isFind = true;
                for (String keyword : keywords) {
                    isFind = isFind && data.toLowerCase().contains(keyword.toLowerCase());
                }

                // Show Data
                
                if (isFind) {
                    SystemMethod.clearScreen();
                    nomer++;
                    StringTokenizer sToken = new StringTokenizer(data, ",");

                    System.out.printf("| %2d ", nomer);
                    System.out.printf("|\t%-22s ", sToken.nextToken());
                    System.out.printf("|\t%-22s ", sToken.nextToken());
                    System.out.printf("| %-5s ", sToken.nextToken());
                    System.out.printf("|\t%s ", sToken.nextToken());
                    System.out.println();
                    System.out.println("-------------------------------------------------------------------------------------------------");
                }
                data = dFile.readLine();
            }

            // This is auth the boolean when true will loop and when false will excute next program
            
        } catch (Exception e) {
            System.err.println("\nData Not Found !! [messege] : " + e);
            System.err.println("Tambah data terlebih dahulu");
            AddDataApp.addData();
            return;
        }
    }
}

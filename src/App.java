import java.util.*;

import utilSystem.*;
import FunctionPKG.*;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner inTerminal = new Scanner(System.in);

        String inputMenu;
        boolean isTrue = true;

        while (isTrue) {
            SystemMethod.clearScreen();
            System.out.println("\nMenu Perpustakaan Sysminc");
            System.out.println("1.\tLihat data buku");
            System.out.println("2.\tUbah data buku");
            System.out.println("3.\tHapus data buku");
            System.out.println("4.\tTambah data buku");
            System.out.println("5.\tCari data buku");
            System.out.println("6.\tExit");
            System.out.print("\nMasukan pilihan : ");
            inputMenu = inTerminal.next();

            switch (inputMenu) {
                case "1":
                    // Lihat Buku
                    System.out.println("\n~~~~~~~~~");
                    System.out.println("DATA BUKU");
                    System.out.println("~~~~~~~~~");
                    ShowDataApp.showData();
                    break;

                case "2":
                    System.out.println("\n~~~~~~~~~~~~~~");
                    System.out.println("UBAH DATA BUKU");
                    System.out.println("~~~~~~~~~~~~~~");
                    UpdateDataApp.updateData();
                    ShowDataApp.showData();
                    break;

                case "3":
                    System.out.println("\n~~~~~~~~~~~~~~~");
                    System.out.println("HAPUS DATA BUKU");
                    System.out.println("~~~~~~~~~~~~~~~");
                    DeleteDataApp.delData();
                    ShowDataApp.showData();
                    break;

                case "4":
                    SystemMethod.clearScreen();
                    System.out.println("\n~~~~~~~~~~~~~~~~");
                    System.out.println("TAMBAH DATA BUKU");
                    System.out.println("~~~~~~~~~~~~~~~~");
                    AddDataApp.addData();
                    break;

                case "5":
                    System.out.println("\n~~~~~~~~~~~~~~");
                    System.out.println("CARI DATA BUKU");
                    System.out.println("~~~~~~~~~~~~~~");
                    SearchDataApp.serachData();

                    boolean isLoop = SystemMethod.isYesNo("Apakah ingin mencari lagi ? ");
                    while (isLoop) {
                        SearchDataApp.serachData();
                        isLoop = SystemMethod.isYesNo("Apakah ingin mencari lagi ? ");
                    }
                    break;

                case "6":
                    System.out.println("\n---------------------");
                    System.out.println("---PROGRAM SELESAI---");
                    System.out.println("---------------------");
                    System.exit(0);

                default:
                    System.err.println(inputMenu + " Angka tidak ditemukan. Input kembali angkai [1-5]");
                    break;
            }
            isTrue = SystemMethod.isYesNo("Apakah ingin balik ke menu");
        }
        inTerminal.close();
    }
}
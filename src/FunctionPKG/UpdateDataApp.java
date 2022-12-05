package FunctionPKG;

import java.util.*;
import java.io.*;

import utilSystem.*;

public class UpdateDataApp {

    public static void updateData() throws IOException {
        // DB ORI for main saved database
        File dbOri = new File("database.txt");
        FileReader inputDbOri = new FileReader(dbOri);
        BufferedReader bufferDbOri = new BufferedReader(inputDbOri);

        // Temp DB for second database temporary
        File dbTemp = new File("tempDB.txt");
        FileWriter inputDbTemp = new FileWriter(dbTemp);
        BufferedWriter bufferDbTemp = new BufferedWriter(inputDbTemp);

        // filed data variable
        Scanner inputUpdate = new Scanner(System.in);
        int number;
        int numberCount = 0;
        String data, dataOri, nama, judul, tahun, penerbit;
        boolean isUpdate = false;
        boolean isDoneUpdate = false;

        // Menampilkan data-data pada user
        System.out.println("List Data");
        ShowDataApp.showData();

        // Pilih data pada database yang akan diubah
        System.out.print("Masukan nomer data yang ingin diupdate : ");
        number = inputUpdate.nextInt();

        // read data dan mulai match data
        data = bufferDbOri.readLine();
        while (data != null) {

            numberCount++;
            StringTokenizer st = new StringTokenizer(data, ",");

            if (number == numberCount) {

                // Konfirmasi data yang ingin diubah
                System.out.println(
                        "\n-------------------------------------------------------------------------------------------------");
                System.out.println(
                        "-----------------------------------------KONFRIMASI DATA-----------------------------------------");
                System.out.println(
                        "-------------------------------------------------------------------------------------------------");
                System.out.println("Nama Penulis    : " + st.nextToken());
                System.out.println("Judul Buku      : " + st.nextToken());
                System.out.println("Tahun Terbit    : " + st.nextToken());
                System.out.println("Penerbit        : " + st.nextToken());

                String[] dataPanel = { "nama", "judul", "tahun", "penerbit" };
                String[] tempData = new String[4];

                // Token Refensi
                st = new StringTokenizer(data, ",");

                // Tulis data yang ingin diubah
                for (int i = 0; i < dataPanel.length; i++) {
                    isUpdate = SystemMethod.isYesNo("Apakah anda ingin merubah " + dataPanel[i] + " data ini ? ");
                    dataOri = st.nextToken();

                    if (isUpdate) {
                        inputUpdate = new Scanner(System.in);
                        System.out.print("Masukan " + dataPanel[i] + " yang diubah : ");
                        tempData[i] = inputUpdate.nextLine();

                    } else {
                        tempData[i] = dataOri;
                    }
                }

                // Validasi data tahun
                while (AddDataApp.tahunIsValid(tempData[2]) != true) {
                    System.out.print("Masukan tahun penerbit : ");
                    tempData[2] = inputUpdate.nextLine();
                }

                // Tampilan data bawah sudah diubah
                st = new StringTokenizer(data, ",");

                System.out.println("\n-------------------------------------------------------------------------------------------------");
                System.out.println("-------------------------------------MENCARI DATA YANG TERSEDIA----------------------------------");
                System.out.println("-------------------------------------------------------------------------------------------------");
                SearchDataApp.findData(tempData);

                System.out.println("Nama Penulis    : " + st.nextToken() + "--> " + tempData[0]);
                System.out.println("Judul Buku      : " + st.nextToken() + "--> " + tempData[1]);
                System.out.println("Tahun Terbit    : " + st.nextToken() + "--> " + tempData[2]);
                System.out.println("Penerbit        : " + st.nextToken() + "--> " + tempData[3]);

                // Konfirmasi data unutk disimpan
                isDoneUpdate = SystemMethod.isYesNo("Apakah data ini ingin disimpan ? ");
                if (isDoneUpdate) {
                    SystemMethod.clearScreen();
                    nama = tempData[0];
                    judul = tempData[1];
                    tahun = tempData[2];
                    penerbit = tempData[3];

                    // Data ditambahkan ke database
                    bufferDbTemp.write(nama + "," + judul + "," + tahun + "," + penerbit);
                    System.out.println(
                            "\n-------------------------------------------------------------------------------------------------");
                    System.out.println(
                            "-----------------------------------------DATA UPDATE---------------------------------------------");
                    System.out.println(
                            "-------------------------------------------------------------------------------------------------\n");

                } else {
                    // Copy data yang sudah ada
                    bufferDbTemp.write(data);
                }

            } else {
                // Copy data yang sudah ada
                bufferDbTemp.write(data);
            }
            // Membuat baris baru didata
            bufferDbTemp.newLine();

            // Mealnjutkan baca database
            data = bufferDbOri.readLine();
        }

        // Memasukan data dan menghapus database temp
        bufferDbTemp.flush();

        

        bufferDbOri.close();
        bufferDbTemp.close();

        dbOri.delete();
        dbTemp.renameTo(dbOri);
    }

}

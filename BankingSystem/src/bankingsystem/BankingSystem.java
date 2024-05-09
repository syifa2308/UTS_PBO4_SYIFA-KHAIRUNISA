
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bankingsystem;

import java.util.ArrayList;
import java.util.Scanner;

public class BankingSystem {
    private ArrayList<BankAccount> accounts;

    public BankingSystem() {
        accounts = new ArrayList<>();
    }

    public void showMenu() {
        System.out.println("=== Menu ===");
        System.out.println("1. Registrasi Akun Baru");
        System.out.println("2. Mengirim Uang");
        System.out.println("3. Menyimpan Uang");
        System.out.println("4. Mengecek Informasi Rekening Pribadi");
        System.out.println("5. Keluar");
        System.out.println("============");
    }

    public void registerAccount(String nama, String alamat, String nomor_telepon, int saldo) {
        BankAccount newAccount = new BankAccount(nama, alamat, nomor_telepon, saldo);
        accounts.add(newAccount);
        System.out.println("Akun berhasil didaftarkan dengan nomor akun: " + newAccount.getNomorAkun());
    }

    public void transferMoney(String nomorAkunPengirim, String nomorAkunPenerima, int jumlahUang) {
        BankAccount pengirim = findAccountByNumber(nomorAkunPengirim);
        BankAccount penerima = findAccountByNumber(nomorAkunPenerima);

        if (pengirim != null && penerima != null) {
            pengirim.transfer(jumlahUang);
            penerima.topUp(jumlahUang);
        } else {
            System.out.println("Nomor akun pengirim atau penerima tidak valid.");
        }
    }

    public void depositMoney(String nomorAkun, int jumlahUang) {
        BankAccount account = findAccountByNumber(nomorAkun);
        if (account != null) {
            account.topUp(jumlahUang);
        } else {
            System.out.println("Nomor akun tidak valid.");
        }
    }

    public void checkAccountInfo(String nomorAkun) {
        BankAccount account = findAccountByNumber(nomorAkun);
        if (account != null) {
            account.showDescription();
        } else {
            System.out.println("Nomor akun tidak valid.");
        }
    }

    private BankAccount findAccountByNumber(String nomorAkun) {
        for (BankAccount account : accounts) {
            if (account.getNomorAkun().equals(nomorAkun)) {
                return account;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankingSystem bankingSystem = new BankingSystem();

        boolean exit = false;
        while (!exit) {
            bankingSystem.showMenu();
            System.out.print("Pilih menu (1-5): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Masukkan nama: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan alamat: ");
                    String alamat = scanner.nextLine();
                    System.out.print("Masukkan nomor telepon: ");
                    String nomorTelepon = scanner.nextLine();
                    System.out.print("Masukkan saldo awal: ");
                    int saldo = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    bankingSystem.registerAccount(nama, alamat, nomorTelepon, saldo);
                    break;
                case 2:
                    System.out.print("Masukkan nomor akun pengirim: ");
                    String pengirim = scanner.nextLine();
                    System.out.print("Masukkan nomor akun penerima: ");
                    String penerima = scanner.nextLine();
                    System.out.print("Masukkan jumlah uang yang akan ditransfer: ");
                    int jumlahUang = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    bankingSystem.transferMoney(pengirim, penerima, jumlahUang);
                    break;
                case 3:
                    System.out.print("Masukkan nomor akun: ");
                    String nomorAkun = scanner.nextLine();
                    System.out.print("Masukkan jumlah uang yang akan disimpan: ");
                    int depositAmount = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    bankingSystem.depositMoney(nomorAkun, depositAmount);
                    break;
                case 4:
                    System.out.print("Masukkan nomor akun: ");
                    String akun = scanner.nextLine();
                    bankingSystem.checkAccountInfo(akun);
                    break;
                case 5:
                    exit = true;
                    System.out.println("Terima kasih!");
                    break;
                default:
                    System.out.println("Menu tidak valid. Silakan pilih menu 1-5.");
            }
        }

        scanner.close();
    }
}

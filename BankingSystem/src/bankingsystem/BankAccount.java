/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankingsystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BankAccount {
    private String nama;
    private String alamat;
    private String nomor_telepon;
    private String nomor_akun;
    private int saldo;
    private LocalDateTime tanggal_registrasi;

    public BankAccount(String nama, String alamat, String nomor_telepon, int saldo) {
        this.nama = nama;
        this.alamat = alamat;
        this.nomor_telepon = nomor_telepon;
        this.saldo = saldo;
        this.nomor_akun = generateNomorAkun();
        this.tanggal_registrasi = LocalDateTime.now();
    }

    public void topUp(int jumlahTopUp) {
        saldo += jumlahTopUp;
        System.out.println("Saldo berhasil ditambahkan. Saldo sekarang: " + saldo);
    }

    public void transfer(int jumlahTransfer) {
        if (saldo >= jumlahTransfer) {
            saldo -= jumlahTransfer;
            System.out.println("Transfer berhasil. Saldo sekarang: " + saldo);
        } else {
            System.out.println("Saldo tidak mencukupi untuk transfer ini.");
        }
    }

    private String generateNomorAkun() {
        StringBuilder nomorAkun = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            nomorAkun.append((int) (Math.random() * 10));
        }
        return nomorAkun.toString();
    }

    public void showDescription() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println("Nama: " + nama);
        System.out.println("Alamat: " + alamat);
        System.out.println("Nomor Telepon: " + nomor_telepon);
        System.out.println("Nomor Akun: " + nomor_akun);
        System.out.println("Saldo: " + saldo);
        System.out.println("Tanggal Registrasi: " + tanggal_registrasi.format(formatter));
    }

    public String getNomorAkun() {
        return nomor_akun;
    }

    public int getSaldo() {
        return saldo;
    }
}

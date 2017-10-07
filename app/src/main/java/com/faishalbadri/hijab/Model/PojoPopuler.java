package com.faishalbadri.hijab.Model;

import java.util.List;

/**
 * Created by faishal on 9/14/17.
 */

public class PojoPopuler {

    /**
     * isi : [{"id_isi":"1","id_admin":"1","id_kategori":"1","isi_judul":"Anak Kita Hilang di Warung","admin_nama":"admin","kategori_nama":"sd121","isi_keterangan":"exemple aja","isi_tgl_upload":"2017-09-12 10:07:19","isi_kunjungan":"2","isi_gambar":"example.png"},{"id_isi":"38","id_admin":"1","id_kategori":"2","isi_judul":"jam terbaru hilang ditengah jalan","admin_nama":"admin","kategori_nama":"shvdy1iu]\\","isi_keterangan":"\nasdw123<\/p>\r\n","isi_tgl_upload":"2017-09-12 10:08:17","isi_kunjungan":"1","isi_gambar":"example.png"},{"id_isi":"2","id_admin":"1","id_kategori":"2","isi_judul":"laskdlq","admin_nama":"admin","kategori_nama":"shvdy1iu]\\","isi_keterangan":"exemple aja","isi_tgl_upload":"2017-09-11 14:02:17","isi_kunjungan":"0","isi_gambar":"example.png"},{"id_isi":"3","id_admin":"1","id_kategori":"3","isi_judul":"asdnqnw","admin_nama":"admin","kategori_nama":"sd1uwp","isi_keterangan":"exemple aja","isi_tgl_upload":"2017-09-11 14:02:17","isi_kunjungan":"0","isi_gambar":"example.png"},{"id_isi":"4","id_admin":"1","id_kategori":"4","isi_judul":"Exampleqwhbevoyq","admin_nama":"admin","kategori_nama":"asd1ew12","isi_keterangan":"exemple aja","isi_tgl_upload":"2017-09-11 14:02:17","isi_kunjungan":"0","isi_gambar":"example.png"},{"id_isi":"5","id_admin":"1","id_kategori":"5","isi_judul":"sdbqow","admin_nama":"admin","kategori_nama":"po12e","isi_keterangan":"exemple aja","isi_tgl_upload":"2017-09-11 14:02:17","isi_kunjungan":"0","isi_gambar":"example.png"},{"id_isi":"6","id_admin":"1","id_kategori":"6","isi_judul":"sadquwghoqlw","admin_nama":"admin","kategori_nama":"iougbiblk","isi_keterangan":"exemple aja","isi_tgl_upload":"2017-09-11 14:02:17","isi_kunjungan":"0","isi_gambar":"example.png"},{"id_isi":"7","id_admin":"1","id_kategori":"7","isi_judul":"q1yw97edha","admin_nama":"admin","kategori_nama":"lbh ,2","isi_keterangan":"exemple aja","isi_tgl_upload":"2017-09-11 14:02:17","isi_kunjungan":"0","isi_gambar":"example.png"},{"id_isi":"8","id_admin":"1","id_kategori":"8","isi_judul":"1892jkansdk","admin_nama":"admin","kategori_nama":"vbjvjvjh","isi_keterangan":"exemple aja","isi_tgl_upload":"2017-09-11 14:02:17","isi_kunjungan":"0","isi_gambar":"example.png"},{"id_isi":"9","id_admin":"1","id_kategori":"9","isi_judul":"sadbg187","admin_nama":"admin","kategori_nama":"bmnb","isi_keterangan":"exemple aja","isi_tgl_upload":"2017-09-11 14:02:17","isi_kunjungan":"0","isi_gambar":"example.png"}]
     * status : 1
     * msg : Data Semua Isi Populer
     */

    private String status;
    private String msg;
    private List<IsiBean> isi;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<IsiBean> getIsi() {
        return isi;
    }

    public void setIsi(List<IsiBean> isi) {
        this.isi = isi;
    }

    public static class IsiBean {
        /**
         * id_isi : 1
         * id_admin : 1
         * id_kategori : 1
         * isi_judul : Anak Kita Hilang di Warung
         * admin_nama : admin
         * kategori_nama : sd121
         * isi_keterangan : exemple aja
         * isi_tgl_upload : 2017-09-12 10:07:19
         * isi_kunjungan : 2
         * isi_gambar : example.png
         */

        private String id_isi;
        private String id_admin;
        private String id_kategori;
        private String isi_judul;
        private String admin_nama;
        private String kategori_nama;
        private String isi_keterangan;
        private String isi_tgl_upload;
        private Double isi_kunjungan;
        private String isi_gambar;

        public String getId_isi() {
            return id_isi;
        }

        public void setId_isi(String id_isi) {
            this.id_isi = id_isi;
        }

        public String getId_admin() {
            return id_admin;
        }

        public void setId_admin(String id_admin) {
            this.id_admin = id_admin;
        }

        public String getId_kategori() {
            return id_kategori;
        }

        public void setId_kategori(String id_kategori) {
            this.id_kategori = id_kategori;
        }

        public String getIsi_judul() {
            return isi_judul;
        }

        public void setIsi_judul(String isi_judul) {
            this.isi_judul = isi_judul;
        }

        public String getAdmin_nama() {
            return admin_nama;
        }

        public void setAdmin_nama(String admin_nama) {
            this.admin_nama = admin_nama;
        }

        public String getKategori_nama() {
            return kategori_nama;
        }

        public void setKategori_nama(String kategori_nama) {
            this.kategori_nama = kategori_nama;
        }

        public String getIsi_keterangan() {
            return isi_keterangan;
        }

        public void setIsi_keterangan(String isi_keterangan) {
            this.isi_keterangan = isi_keterangan;
        }

        public String getIsi_tgl_upload() {
            return isi_tgl_upload;
        }

        public void setIsi_tgl_upload(String isi_tgl_upload) {
            this.isi_tgl_upload = isi_tgl_upload;
        }

        public Double getIsi_kunjungan() {
            return isi_kunjungan;
        }

        public void setIsi_kunjungan(Double isi_kunjungan) {
            this.isi_kunjungan = isi_kunjungan;
        }

        public String getIsi_gambar() {
            return isi_gambar;
        }

        public void setIsi_gambar(String isi_gambar) {
            this.isi_gambar = isi_gambar;
        }
    }
}

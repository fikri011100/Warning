package com.faishalbadri.hijab.Model;

import java.util.List;

/**
 * Created by faishal on 9/14/17.
 */

public class PojoKategori {

    /**
     * kategori : [{"id_kategori":"12","kategori_nama":"234fdg","kategori_gambar":"example.png"},{"id_kategori":"14","kategori_nama":"4352tfdsf","kategori_gambar":"example.png"},{"id_kategori":"4","kategori_nama":"asd1ew12","kategori_gambar":"example.png"},{"id_kategori":"16","kategori_nama":"asdasdasd","kategori_gambar":"example.png"},{"id_kategori":"17","kategori_nama":"asdbaskhvdhkjvqkjhvwjhq","kategori_gambar":"example.png"},{"id_kategori":"9","kategori_nama":"bmnb","kategori_gambar":"example.png"},{"id_kategori":"10","kategori_nama":"dfg","kategori_gambar":"example.png"},{"id_kategori":"11","kategori_nama":"dfgwe32","kategori_gambar":"example.png"},{"id_kategori":"13","kategori_nama":"fghre2","kategori_gambar":"example.png"},{"id_kategori":"18","kategori_nama":"fikri","kategori_gambar":"example.png"},{"id_kategori":"6","kategori_nama":"iougbiblk","kategori_gambar":"example.png"},{"id_kategori":"7","kategori_nama":"lbh ,2","kategori_gambar":"example.png"},{"id_kategori":"5","kategori_nama":"po12e","kategori_gambar":"example.png"},{"id_kategori":"1","kategori_nama":"sd121","kategori_gambar":"example.png"},{"id_kategori":"3","kategori_nama":"sd1uwp","kategori_gambar":"example.png"},{"id_kategori":"15","kategori_nama":"sfdfwer2","kategori_gambar":"example.png"},{"id_kategori":"2","kategori_nama":"shvdy1iu]\\","kategori_gambar":"example.png"},{"id_kategori":"8","kategori_nama":"vbjvjvjh","kategori_gambar":"example.png"}]
     * status : 1
     * msg : Data Semua Kategori
     */

    private String status;
    private String msg;
    private List<KategoriBean> kategori;

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

    public List<KategoriBean> getKategori() {
        return kategori;
    }

    public void setKategori(List<KategoriBean> kategori) {
        this.kategori = kategori;
    }

    public static class KategoriBean {
        /**
         * id_kategori : 12
         * kategori_nama : 234fdg
         * kategori_gambar : example.png
         */

        private String id_kategori;
        private String kategori_nama;
        private String kategori_gambar;

        public String getId_kategori() {
            return id_kategori;
        }

        public void setId_kategori(String id_kategori) {
            this.id_kategori = id_kategori;
        }

        public String getKategori_nama() {
            return kategori_nama;
        }

        public void setKategori_nama(String kategori_nama) {
            this.kategori_nama = kategori_nama;
        }

        public String getKategori_gambar() {
            return kategori_gambar;
        }

        public void setKategori_gambar(String kategori_gambar) {
            this.kategori_gambar = kategori_gambar;
        }
    }
}

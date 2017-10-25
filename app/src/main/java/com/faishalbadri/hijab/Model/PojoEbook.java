package com.faishalbadri.hijab.Model;

import java.util.List;

/**
 * Created by fikriimaduddin on 10/24/17.
 */

public class PojoEbook {

  /**
   * ebook : [{"id_ebook":"1","judul_ebook":"helloo","gambar_ebook":"example.png","link":"http://santriprogrammer.com"},{"id_ebook":"2","judul_ebook":"halloow","gambar_ebook":"example.png","link":"http://santriprogrammer.com"}]
   * status : 1
   * msg : Data Semua Ebook
   */

  private String status;
  private String msg;
  private List<EbookBean> ebook;

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

  public List<EbookBean> getEbook() {
    return ebook;
  }

  public void setEbook(List<EbookBean> ebook) {
    this.ebook = ebook;
  }

  public static class EbookBean {

    /**
     * id_ebook : 1
     * judul_ebook : helloo
     * gambar_ebook : example.png
     * link : http://santriprogrammer.com
     */

    private String id_ebook;
    private String judul_ebook;
    private String gambar_ebook;
    private String link;

    public String getId_ebook() {
      return id_ebook;
    }

    public void setId_ebook(String id_ebook) {
      this.id_ebook = id_ebook;
    }

    public String getJudul_ebook() {
      return judul_ebook;
    }

    public void setJudul_ebook(String judul_ebook) {
      this.judul_ebook = judul_ebook;
    }

    public String getGambar_ebook() {
      return gambar_ebook;
    }

    public void setGambar_ebook(String gambar_ebook) {
      this.gambar_ebook = gambar_ebook;
    }

    public String getLink() {
      return link;
    }

    public void setLink(String link) {
      this.link = link;
    }
  }
}

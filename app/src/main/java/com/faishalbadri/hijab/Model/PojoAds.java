package com.faishalbadri.hijab.Model;

import java.util.List;

/**
 * Created by fikriimaduddin on 10/26/17.
 */

public class PojoAds {

  /**
   * ads : [{"id_ads":"1","judul_ads":"hello","gambar_ads":"example.png","link_ads":"https://www.youtube.com/watch?v=xuSxRuk_07Y"},{"id_ads":"2","judul_ads":"hello","gambar_ads":"example.png","link_ads":"https://www.youtube.com/watch?v=xuSxRuk_07Y"}]
   * status : 1
   * msg : Data Semua Ads
   */

  private String status;
  private String msg;
  private List<AdsBean> ads;

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

  public List<AdsBean> getAds() {
    return ads;
  }

  public void setAds(List<AdsBean> ads) {
    this.ads = ads;
  }

  public static class AdsBean {

    /**
     * id_ads : 1
     * judul_ads : hello
     * gambar_ads : example.png
     * link_ads : https://www.youtube.com/watch?v=xuSxRuk_07Y
     */

    private String id_ads;
    private String judul_ads;
    private String gambar_ads;
    private String link_ads;

    public String getId_ads() {
      return id_ads;
    }

    public void setId_ads(String id_ads) {
      this.id_ads = id_ads;
    }

    public String getJudul_ads() {
      return judul_ads;
    }

    public void setJudul_ads(String judul_ads) {
      this.judul_ads = judul_ads;
    }

    public String getGambar_ads() {
      return gambar_ads;
    }

    public void setGambar_ads(String gambar_ads) {
      this.gambar_ads = gambar_ads;
    }

    public String getLink_ads() {
      return link_ads;
    }

    public void setLink_ads(String link_ads) {
      this.link_ads = link_ads;
    }
  }
}

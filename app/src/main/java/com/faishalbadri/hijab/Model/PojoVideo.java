package com.faishalbadri.hijab.Model;

import java.util.List;

/**
 * Created by fikriimaduddin on 9/28/17.
 */

public class PojoVideo {

  /**
   * video : [{"id":"1","judul_video":"Hampir Di Cium HARUKA!!","video":"ehcyDLnTWuQ","duration":"3:46"},{"id":"2","judul_video":"REFRESHER MONKEY KING DOTA 2 PATCH 7.06 NEW META PRO GAMEPLAY","video":"MLbvrYpLOLw","duration":"10:02"},{"id":"3","judul_video":"6 RAPIERS ON PHANTOM ASSASSIN IN-GAME DOTA 2","video":"VNTH1CCVtJA","duration":"45:08"},{"id":"4","judul_video":"Wendy Bete Karena Dituduh Mengambil Snack - Ini Talk Show 23 April 2016 (3/6)\n","video":"L5NT-m5GGPg","duration":"15:23"}]
   * status : 1
   * msg : Data Semua Video
   */

  private String status;
  private String msg;
  private List<VideoBean> video;

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

  public List<VideoBean> getVideo() {
    return video;
  }

  public void setVideo(List<VideoBean> video) {
    this.video = video;
  }

  public static class VideoBean {

    /**
     * id : 1
     * judul_video : Hampir Di Cium HARUKA!!
     * video : ehcyDLnTWuQ
     * duration : 3:46
     */

    private String id;
    private String judul_video;
    private String video;
    private String duration;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getJudul_video() {
      return judul_video;
    }

    public void setJudul_video(String judul_video) {
      this.judul_video = judul_video;
    }

    public String getVideo() {
      return video;
    }

    public void setVideo(String video) {
      this.video = video;
    }

    public String getDuration() {
      return duration;
    }

    public void setDuration(String duration) {
      this.duration = duration;
    }
  }
}

package com.faishalbadri.hijab.Model;

import java.util.List;

/**
 * Created by fikriimaduddin on 10/23/17.
 */

public class PojoUser {

  /**
   * user : [{"id_user":"1","username":"aaaa","email":"aaaa","password":"74b87337454200d4d33f80c4663dc5e5","img_user":"download.jpg"}]
   * status : 1
   * msg : Data Semua User
   */

  private String status;
  private String msg;
  private List<UserBean> user;

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

  public List<UserBean> getUser() {
    return user;
  }

  public void setUser(List<UserBean> user) {
    this.user = user;
  }

  public static class UserBean {

    /**
     * id_user : 1
     * username : aaaa
     * email : aaaa
     * password : 74b87337454200d4d33f80c4663dc5e5
     * img_user : download.jpg
     */

    private String id_user;
    private String username;
    private String email;
    private String password;
    private String img_user;

    public String getId_user() {
      return id_user;
    }

    public void setId_user(String id_user) {
      this.id_user = id_user;
    }

    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }

    public String getImg_user() {
      return img_user;
    }

    public void setImg_user(String img_user) {
      this.img_user = img_user;
    }
  }
}

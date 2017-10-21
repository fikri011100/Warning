package com.faishalbadri.hijab.Model;

import java.util.List;

/**
 * Created by fikriimaduddin on 10/21/17.
 */

public class PojoSession {

  /**
   * session : [{"id_session":"1","id_user":"1","id_voting":"1","session_status":"1"}]
   * status : 1
   * msg : Data Semua Session
   */

  private String status;
  private String msg;
  private List<SessionBean> session;

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

  public List<SessionBean> getSession() {
    return session;
  }

  public void setSession(List<SessionBean> session) {
    this.session = session;
  }

  public static class SessionBean {

    /**
     * id_session : 1
     * id_user : 1
     * id_voting : 1
     * session_status : 1
     */

    private String id_session;
    private String id_user;
    private String id_voting;
    private String session_status;

    public String getId_session() {
      return id_session;
    }

    public void setId_session(String id_session) {
      this.id_session = id_session;
    }

    public String getId_user() {
      return id_user;
    }

    public void setId_user(String id_user) {
      this.id_user = id_user;
    }

    public String getId_voting() {
      return id_voting;
    }

    public void setId_voting(String id_voting) {
      this.id_voting = id_voting;
    }

    public String getSession_status() {
      return session_status;
    }

    public void setSession_status(String session_status) {
      this.session_status = session_status;
    }
  }
}

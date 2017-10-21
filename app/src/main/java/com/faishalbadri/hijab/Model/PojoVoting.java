package com.faishalbadri.hijab.Model;

import java.util.List;

/**
 * Created by fikriimaduddin on 10/21/17.
 */

public class PojoVoting {

  /**
   * voting : [{"id_voting":"1","voting_nickname":"Faishal","voting_img":"example.png","voting_like":"0"}]
   * status : 1
   * msg : Data Semua Voting
   */

  private String status;
  private String msg;
  private List<VotingBean> voting;

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

  public List<VotingBean> getVoting() {
    return voting;
  }

  public void setVoting(List<VotingBean> voting) {
    this.voting = voting;
  }

  public static class VotingBean {

    /**
     * id_voting : 1
     * voting_nickname : Faishal
     * voting_img : example.png
     * voting_like : 0
     */

    private String id_voting;
    private String voting_nickname;
    private String voting_img;
    private Double voting_like;

    public String getId_voting() {
      return id_voting;
    }

    public void setId_voting(String id_voting) {
      this.id_voting = id_voting;
    }

    public String getVoting_nickname() {
      return voting_nickname;
    }

    public void setVoting_nickname(String voting_nickname) {
      this.voting_nickname = voting_nickname;
    }

    public String getVoting_img() {
      return voting_img;
    }

    public void setVoting_img(String voting_img) {
      this.voting_img = voting_img;
    }

    public Double getVoting_like() {
      return voting_like;
    }

    public void setVoting_like(Double voting_like) {
      this.voting_like = voting_like;
    }
  }
}

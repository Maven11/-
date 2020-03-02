package com.hwua.pojo;

public class Message {
    private Long id;
    private Long sendid;
    private String title;
    private String msgcontent;
    private Integer state;
    private Integer receiveid;
    private String msg_create_date;
    private User sendUser;// 短消息和发送者是1对1的关系

    public Message() {
    }

    public Message(Long sendid, String title, String msgcontent, Integer state, Integer receiveid, String msg_create_date) {
        this.sendid = sendid;
        this.title = title;
        this.msgcontent = msgcontent;
        this.state = state;
        this.receiveid = receiveid;
        this.msg_create_date = msg_create_date;
    }

    public User getSendUser() {
        return sendUser;
    }

    public void setSendUser(User sendUser) {
        this.sendUser = sendUser;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSendid() {
        return sendid;
    }

    public void setSendid(Long sendid) {
        this.sendid = sendid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsgcontent() {
        return msgcontent;
    }

    public void setMsgcontent(String msgcontent) {
        this.msgcontent = msgcontent;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getReceiveid() {
        return receiveid;
    }

    public void setReceiveid(Integer receiveid) {
        this.receiveid = receiveid;
    }

    public String getMsg_create_date() {
        return msg_create_date;
    }

    public void setMsg_create_date(String msg_create_date) {
        this.msg_create_date = msg_create_date;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", sendid=" + sendid +
                ", title='" + title + '\'' +
                ", msgcontent='" + msgcontent + '\'' +
                ", state=" + state +
                ", receiveid=" + receiveid +
                ", msgCreateDate='" + msg_create_date + '\'' +
                '}';
    }
}

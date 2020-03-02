package com.hwua.service;

import com.hwua.pojo.Message;

import java.util.List;

public interface IMessageService {
    /**
     * 获取登录用户收到的所有短消息
     * 
     * @param loginid
     * @return
     */
    public List<Message> queryMessageByLoginUser(Long loginid, int start, int pageSize) throws Exception;// 查询登录用户收到的所有短消息
    public List<Message> queryMessageByLoginId(Long loginid) throws Exception;
    public Message queryMessageById(String id) throws Exception;; //根据id来查询一条短消息
    
    public int sendMessage(Message msg) throws Exception;;//发送短消息
    
    public int deleteMsgById(int id) throws Exception;;// 删除一条短消息
    
    public Long queryMsgCount(int loginid) throws Exception;; //查询登录用户收到的短消息个数
}

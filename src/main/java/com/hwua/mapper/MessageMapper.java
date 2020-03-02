package com.hwua.mapper;

import com.hwua.pojo.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageMapper {
    /**
     * 获取登录用户收到的所有短消息
     *
     * @param loginid
     * @return
     */
    public List<Message> queryMessageByLoginUser(@Param("loginid") Long loginid, @Param("start") int start, @Param("pageSize") int pageSize) throws Exception;// 查询登录用户收到的所有短消息
    public List<Message> queryMessageByLoginId(@Param("loginid") Long loginid) throws Exception;// 查询登录用户收到的所有短消息

    public Message queryMessageById(String id) throws Exception; //根据id来查询一条短消息

    public Integer saveMessage(Message msg)throws Exception;//发送短消息

    public Integer deleteMsgById(int id)throws Exception;// 删除一条短消息

    public Long queryMsgCount(int loginid)throws Exception; //查询登录用户收到的短消息个数

    public Integer updateMessage(Message msg) throws Exception;//更新短消息
}

package com.hwua.service.impl;

import com.hwua.mapper.MessageMapper;
import com.hwua.pojo.Message;
import com.hwua.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("msgService")
@Transactional
public class MessageServiceImpl implements IMessageService {
    @Autowired
    private MessageMapper msgMapper;

    @Override
    public List<Message> queryMessageByLoginUser(Long loginid, int start, int pageSize) throws Exception {
        return msgMapper.queryMessageByLoginUser(loginid, start, pageSize);
    }

    @Override
    public List<Message> queryMessageByLoginId(Long loginid) throws Exception {
        return msgMapper.queryMessageByLoginId(loginid);
    }

    @Override
    public Message queryMessageById(String id) throws Exception {
        Message msg = msgMapper.queryMessageById(id);
        msg.setState(0);
        msgMapper.updateMessage(msg);
        return msg;
    }

    @Override
    public int sendMessage(Message msg) throws Exception {
        return msgMapper.saveMessage(msg);
    }

    @Override
    public int deleteMsgById(int id) throws Exception {
        return msgMapper.deleteMsgById(id);
    }

    @Override
    public Long queryMsgCount(int loginid) throws Exception {
        return msgMapper.queryMsgCount(loginid);
    }
}

package org.goshop.users.service;

import org.goshop.users.i.MessageService;
import org.goshop.users.mapper.read.ReadGsMessageMapper;
import org.goshop.users.pojo.GsMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 05/01/2018.
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {

    @Autowired
    ReadGsMessageMapper readGsMessageMapper;

    @Override
    public List<GsMessage> findByCondition(Map condition) {
        return readGsMessageMapper.selectByCondition(condition);
    }

    @Override
    public GsMessage findOne(Long id) {
        return readGsMessageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<GsMessage> findBySelfRelated(Map condition) {
        return readGsMessageMapper.selectBySelfRelated(condition);
    }
}

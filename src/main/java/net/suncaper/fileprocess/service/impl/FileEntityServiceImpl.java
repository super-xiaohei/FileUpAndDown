package net.suncaper.fileprocess.service.impl;

import net.suncaper.fileprocess.entity.FileEntity;
import net.suncaper.fileprocess.entity.FileEntityExample;
import net.suncaper.fileprocess.mapper.FileEntityMapper;
import net.suncaper.fileprocess.service.FileEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zyq
 * @date 2021/1/8 - 11:27
 */
@Service
public class FileEntityServiceImpl implements FileEntityService {
    @Autowired
    private FileEntityMapper fileEntityMapper;
    @Override
    public void upload(List<FileEntity> fileEntityList) {
        fileEntityList.stream().forEach(x-> fileEntityMapper.insertSelective(x));
    }

    @Override
    public List<FileEntity> selectAll() {
        List<FileEntity> fileEntityList = fileEntityMapper.selectByExample(null);
        return fileEntityList;
    }

    @Override
    public FileEntity selectById(int id) {
        FileEntityExample fileEntityExample = new FileEntityExample();
        fileEntityExample.createCriteria().andIdEqualTo(id);
        List<FileEntity> fileEntityList = fileEntityMapper.selectByExample(fileEntityExample);
        return fileEntityList.size() > 0? fileEntityList.get(0):null;
    }
}

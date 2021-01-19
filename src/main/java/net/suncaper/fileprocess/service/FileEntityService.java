package net.suncaper.fileprocess.service;

import net.suncaper.fileprocess.entity.FileEntity;

import java.util.List;

/**
 * @author zyq
 * @date 2021/1/8 - 11:26
 */
public interface FileEntityService {
    void upload(List<FileEntity> fileEntityList);

    List<FileEntity> selectAll();

    FileEntity selectById(int id);
}

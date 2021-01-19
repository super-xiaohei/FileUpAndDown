package net.suncaper.fileprocess.mapper;

import java.util.List;
import net.suncaper.fileprocess.entity.FileEntity;
import net.suncaper.fileprocess.entity.FileEntityExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


public interface FileEntityMapper {
    long countByExample(FileEntityExample example);

    int deleteByExample(FileEntityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FileEntity record);

    int insertSelective(FileEntity record);

    List<FileEntity> selectByExample(FileEntityExample example);

    FileEntity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FileEntity record, @Param("example") FileEntityExample example);

    int updateByExample(@Param("record") FileEntity record, @Param("example") FileEntityExample example);

    int updateByPrimaryKeySelective(FileEntity record);

    int updateByPrimaryKey(FileEntity record);
}
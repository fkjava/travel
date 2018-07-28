package org.fkjava.travel.commons.file.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.fkjava.travel.commons.file.dao.FileInfoDao;
import org.fkjava.travel.commons.file.domain.FileInfo;
import org.fkjava.travel.commons.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

// 必须要把当前包增加到业务逻辑层的扫描中
@Service
@Transactional
public class FileServiceImpl implements FileService {

    @Autowired
    private FileInfoDao dao;
    // 把application.properties文件里面的属性输入进来
    @Value("${travel.file.dir}")
    private String fileDir;

    @Override
    public List<FileInfo> find() {
        return dao.findAll();
    }

    @Override
    public String save(MultipartFile file) {
        // 1.保存文件到磁盘
        // 1.1.定义文件存储的目标文件夹
        File dir = new File(fileDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // 1.2.生成随机文件名，因为不同的用户可能上传同名的文件
        String newFileName = UUID.randomUUID().toString();
        File dest = new File(dir, newFileName);
        // System.out.println("目标文件路径：" + dest.getAbsolutePath());
        // System.out.println("路径：" + dest.getPath());
        try {
            // 把文件的内容，传输到目标文件位置
            Files.copy(file.getInputStream(), dest.toPath());
        } catch (IllegalStateException | IOException e) {
            throw new RuntimeException(e);
        }

        // 2.保存文件信息到数据库
        FileInfo info = new FileInfo();
        info.setContentType(file.getContentType());// 文件的内容类型
        info.setFileLength(file.getSize());// 文件大小
        info.setName(file.getOriginalFilename());// 原始文件名
        info.setPath(newFileName);// 文件实际存储的位置

        try {
            FileInfo i = dao.save(info);
            return i.getId();
        } catch (Exception e) {
            // 出现异常删除上传的文件
            dest.delete();
            throw new RuntimeException(e);
        }
    }

    @Override
    public FileInfo getById(String id) {
        return this.dao.getOne(id);
    }

    @Override
    public void delete(String id) {
        // 1.根据id找到FileInfo
        FileInfo info = this.getById(id);
        // 2.根据路径把文件删除
        File file = new File(info.getPath());
        file.delete();
        // 3.删除数据库里面的FileInfo
        this.dao.delete(info);
    }

    @Override
    public InputStream getFileContent(FileInfo info) throws FileNotFoundException {
        File file = new File(this.fileDir, info.getPath());
        FileInputStream in = new FileInputStream(file);
        return in;
    }
}

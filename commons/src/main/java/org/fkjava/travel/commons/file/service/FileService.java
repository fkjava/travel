package org.fkjava.travel.commons.file.service;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.fkjava.travel.commons.file.domain.FileInfo;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    List<FileInfo> find();

    String save(MultipartFile file);

    FileInfo getById(String id);

    void delete(String id);

    public InputStream getFileContent(FileInfo info) throws FileNotFoundException;

}

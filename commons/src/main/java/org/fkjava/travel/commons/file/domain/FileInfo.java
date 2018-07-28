package org.fkjava.travel.commons.file.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.fkjava.travel.commons.domain.UUIDEntity;

@Entity
@Table(name = "COMMONS_FILE_INFO")
public class FileInfo extends UUIDEntity {

    // 文件名
    private String name;
    // 文件类型
    private String contentType;
    // 文件大小
    private long fileLength;
    // 文件的实际存储位置
    @Column(name = "`PATH`")
    private String path;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getFileLength() {
        return fileLength;
    }

    public void setFileLength(long fileLength) {
        this.fileLength = fileLength;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

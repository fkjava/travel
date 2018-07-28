package org.fkjava.travel.commons.file.dao;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.fkjava.travel.commons.file.domain.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// 一定要把包加入到 applicationContext-jpa.xml里面，使用<jpa:repositories>元素进行扫描。
@Repository
// <FileInfo, String> : 第一个参数表示实体类型，第二个参数表示主键的数据类型
public interface FileInfoDao extends JpaRepository<FileInfo, String> {

    // 每次保存都新建一个事务，在调用这个方法以后就提交事务
    // 如果提交事务失败则删除文件
    @Transactional(value = TxType.REQUIRES_NEW)
    <S extends FileInfo> S save(S entity);
}

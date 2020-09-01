package com.uz.Repository;

import com.uz.Entity.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadRepository extends JpaRepository<FileUpload, Integer > {
    FileUpload findByFileName(String fileName);

    FileUpload findByHashId(String hashId);
}

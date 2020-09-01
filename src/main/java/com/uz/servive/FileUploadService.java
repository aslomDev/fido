package com.uz.servive;

import com.uz.Entity.FileUpload;
import com.uz.Repository.FileUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service
public class FileUploadService {

    @Value("${upload.folder}")
    private String uploadFolder;

    @Autowired
    private final FileUploadRepository uploadRepository;

    public FileUploadService(FileUploadRepository uploadRepository) {
        this.uploadRepository = uploadRepository;
    }


    @PostMapping
    public String upload(MultipartHttpServletRequest request){

        MultipartFile file;

        FileUpload fileUpload = new FileUpload();
        return null;

    }


}

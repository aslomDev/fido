package com.uz.servive;

import com.uz.Entity.Document;
import com.uz.Entity.FileUpload;
import com.uz.Repository.DocumentRepository;
import com.uz.Repository.FileUploadRepository;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private final DocumentRepository documentRepository;

    @Autowired
    private final FileUploadRepository fileUploadRepository;


    private final Hashids hashId;


    @Value("${upload.folder}")
    private String uploadFolder;

    public DocumentService(DocumentRepository documentRepository, FileUploadRepository fileUploadRepository) {
        this.documentRepository = documentRepository;
        this.fileUploadRepository = fileUploadRepository;
        this.hashId = new Hashids(getClass().getName(), 6);
    }


    public Document addDocument(Document document, MultipartFile file) throws ParseException {

        document.setRegDate(new Date());
//

        try {
            FileUpload upload = new FileUpload();
            upload.setFileName(file.getOriginalFilename());
            upload.setFileContent(file.getContentType());
            upload.setFileSize(file.getSize());


            upload.setDowbUri(uploadFolder + "/" + file.getOriginalFilename());
            fileUploadRepository.save(upload);
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadFolder + "/" + file.getOriginalFilename());
            Files.write(path, bytes);
            upload.setHashId(hashId.encode(upload.getId()));
            fileUploadRepository.save(upload);

            document.setFileUpload(upload);
            documentRepository.save(document);


        } catch (IOException e) {
            e.printStackTrace();
        }

            return documentRepository.save(document);

    }

    public Document editDocument(Document document) throws ParseException {

        document.setRegDate(new Date());
//
//        try {
////            FileUpload upload = fileUploadRepository.findById(id).get();
////            upload.setFileName(file.getOriginalFilename());
////            upload.setFileContent(file.getContentType());
////            upload.setFileSize(file.getSize());
////
////
////            upload.setDowbUri(uploadFolder + "/" + file.getOriginalFilename());
////            fileUploadRepository.save(upload);
////            byte[] bytes = file.getBytes();
////            Path path = Paths.get(uploadFolder + "/" + file.getOriginalFilename());
////            Files.write(path, bytes);
////            upload.setHashId(hashId.encode(upload.getId()));
////            fileUploadRepository.save(upload);
////
////            document.setFileUpload(upload);
////            documentRepository.save(document);
////
////
////        } catch (IOException e) {
////            e.printStackTrace();
////        }

        return documentRepository.save(document);

    }

    public List<Document> listDocument(){
        List<Document> documents = documentRepository.findAll();
        return documents;
    }

    public Document delete(Integer id){
        Document document = documentRepository.findById(id).get();
        documentRepository.delete(document);

        return document;
    }


    public Document editCourier(Integer id) {

        Document document = documentRepository.getOne(id);

        document.setRegDate(new Date());

        documentRepository.save(document);

        return document;
    }

    private boolean isSupportedType(String type){
        return type.equals("image/jpg")||type.equals("image/jpeg")||type.equals("image/png")||type.equals("office/pdf")||type.equals("office/docx")||type.equals("video/mp4")||type.equals("video/mp3");
    }



}

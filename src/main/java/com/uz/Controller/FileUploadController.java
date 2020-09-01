package com.uz.Controller;

import com.uz.Entity.FileUpload;
import com.uz.Repository.FileUploadRepository;
import com.uz.servive.MapValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/file")
public class FileUploadController {
//
//    @Autowired
//    private final FileUploadRepository fileUploadRepository;
//
//    public FileUploadController(FileUploadRepository fileUploadRepository) {
//        this.fileUploadRepository = fileUploadRepository;
//    }
//
//    @PostMapping("/upload")
//    public String uploadFiles(@RequestParam("files") MultipartFile[] files, Model model){
//        List<String> fileNames = new ArrayList<>();
//
//        try {
//            List<FileUpload> storedFile = new ArrayList<>();
//
//            for (MultipartFile file : files){
//                FileUpload fileUpload = fileUploadRepository.findByFileName(file.getOriginalFilename());
//
//                if (fileUpload != null){
//                    fileUpload.setFileByte(file.getBytes());
//                }else {
//                    fileUpload = new FileUpload(file.getOriginalFilename(), file.getContentType(), file.getSize(), file.getBytes());
//                }
//
//                fileNames.add(file.getOriginalFilename());
//                storedFile.add(fileUpload);
//            }
//            fileUploadRepository.saveAll(storedFile);
//            model.addAttribute("message","файл ок");
//            model.addAttribute("files", fileNames);
//        } catch (IOException e) {
//            model.addAttribute("message","ошибка");
//            model.addAttribute("files", fileNames);
//        }
//        return "file";
//
//    }
//
//    @GetMapping("/files")
//    public String getFile(Model model){
//
//        return "file";
//    }


}

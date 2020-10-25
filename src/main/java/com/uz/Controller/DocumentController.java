package com.uz.Controller;

import com.uz.Entity.Courier;
import com.uz.Entity.Document;
import com.uz.Entity.FileUpload;
import com.uz.Entity.Korres;
import com.uz.Repository.CourierRepository;
import com.uz.Repository.DocumentRepository;
import com.uz.Repository.FileUploadRepository;
import com.uz.Repository.KorresRepository;
import com.uz.servive.DocumentService;
import com.uz.servive.MapValidatorService;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileUrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class DocumentController {

    @Autowired
    private final DocumentService documentService;

    @Autowired
    private final DocumentRepository documentRepository;

    @Autowired
    private final CourierRepository courierRepository;

    @Autowired
    private final FileUploadRepository fileUploadRepository;

    @Autowired
    private final KorresRepository korresRepository;

    private final Hashids hashId;

    @Value("${upload.folder}")
    private String uploadFolder;

    public DocumentController(DocumentService documentService, DocumentRepository documentRepository, CourierRepository courierRepository, FileUploadRepository fileUploadRepository, KorresRepository korresRepository) {
        this.documentService = documentService;
        this.documentRepository = documentRepository;
        this.courierRepository = courierRepository;
        this.fileUploadRepository = fileUploadRepository;
        this.korresRepository = korresRepository;
        this.hashId = new Hashids(getClass().getName(), 6);
    }

    @GetMapping
    public String getDocument(Model model){


        List<Courier> courierList = courierRepository.findAll();
        List<Document> documentList = documentRepository.findAll();
        List<Korres> korresList = korresRepository.findAll();
        model.addAttribute("documents", documentList);
        model.addAttribute("couriers", courierList);
        model.addAttribute("couriers", korresList);
        return "document";
    }

    @GetMapping("/add")
    public String addForm(Document document, Model model){

//
        List<Courier> courierList = courierRepository.findAll();
        List<Korres> korresList = korresRepository.findAll();
        model.addAttribute("couriers", courierList);
        model.addAttribute("korress", korresList);


        return "addDocument";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute Document document, BindingResult result, Model model, MultipartFile file, RedirectAttributes redirectAttributes) throws ParseException {

        List<Courier> courierList = courierRepository.findAll();
        List<Korres> korresList = korresRepository.findAll();
        model.addAttribute("couriers", courierList);
        model.addAttribute("korress", korresList);


        if (result.hasErrors()){
            Map<String, String> errorHashMap = MapValidatorService.getErrors(result);
            model.mergeAttributes(errorHashMap);
            model.addAttribute("errors", document);
            System.out.println(errorHashMap);
            return "addDocument";
        }

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "пожалуйста выберите документ файла");
            return "redirect:/add";
        }else if (!isSupportedType(file.getContentType())){
            redirectAttributes.addFlashAttribute("issuport", "Допустимый формат загружаемых файлов: PDF, Doc, Docx");
            return "redirect:/add";
        }else if (1048576  < file.getSize()){
            redirectAttributes.addFlashAttribute("hajm", "Допустимый размер файла 1мб");
            return "redirect:/add";
        }



        model.addAttribute("document", documentService.addDocument(document, file));

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteDocument(@PathVariable Integer id){
        Document document = documentService.delete(id);
        return "redirect:/";

    }

    @GetMapping("/edit/{id}")
    public String getOne(@PathVariable Integer id, Model model){
        List<Courier> courierList = courierRepository.findAll();
        List<Korres> korresList = korresRepository.findAll();
        Optional<FileUpload> fileUploadList = fileUploadRepository.findById(id);
        Document document = documentService.editCourier(id);
        model.addAttribute("oneDocument", document);
        model.addAttribute("couriers", courierList);
        model.addAttribute("korress", korresList);
        model.addAttribute("fileList", fileUploadList);
        return "editDocument";
    }

    @PostMapping("/update")
        public String updateDocument( @Valid @ModelAttribute Document document, BindingResult result, Model model, MultipartFile file, RedirectAttributes redirectAttributes) throws ParseException {

        List<Courier> courierList = courierRepository.findAll();
        List<Korres> korresList = korresRepository.findAll();
        model.addAttribute("couriers", courierList);
        model.addAttribute("korress", korresList);




        if (result.hasErrors()){
            Map<String, String> errorHashMap = MapValidatorService.getErrors(result);
            model.mergeAttributes(errorHashMap);
            model.addAttribute("errors", document);
            System.out.println(errorHashMap);
            return "editDocument";
        }

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "пожалуйста выберите документ файла");
            return "redirect:/update";
        }else if (!isSupportedType(file.getContentType())){
            redirectAttributes.addFlashAttribute("issuport", "Допустимый формат загружаемых файлов: PDF, Doc, Docx");
            return "redirect:/update";
        }else if (1048576  < file.getSize()){
            redirectAttributes.addFlashAttribute("hajm", "Допустимый размер файла 1мб");
            return "redirect:/update";
        }

                try {
            FileUpload upload = fileUploadRepository.findById(document.getId()).get();
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




        model.addAttribute("document", documentService.editDocument(document));

        return "redirect:/";
    }

    @GetMapping("/files/{hashId}")
    public ResponseEntity downloadFile(@PathVariable String hashId) throws MalformedURLException {
        FileUpload file =  fileUploadRepository.findByHashId(hashId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + URLEncoder.encode(file.getFileName()) + "\"")
                .contentType(MediaType.parseMediaType(file.getFileContent()))
                .contentLength(file.getFileSize())
                .body(new FileUrlResource(String.format("%s/%s", uploadFolder, file.getFileName())));
    }

    @GetMapping("/files//veiw/{hashId}")
    public ResponseEntity viewdFile(@PathVariable String hashId) throws MalformedURLException {
        FileUpload file =  fileUploadRepository.findByHashId(hashId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + URLEncoder.encode(file.getFileName()) + "\"")
                .contentType(MediaType.parseMediaType(file.getFileContent()))
                .contentLength(file.getFileSize())
                .body(new FileUrlResource(String.format("%s/%s", uploadFolder, file.getFileName())));
    }



    private boolean isSupportedType(String type){
        return type.equals("application/pdf")||type.equals("application/x-pdf")||type.equals(" application/x-bzpdf")||type.equals("application/x-gzpdf")||type.equals("application/msword")||type.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
    }


}

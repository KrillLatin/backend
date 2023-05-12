package univ.tuit.krill_lotin.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import univ.tuit.krill_lotin.dto.ResponseText;
import univ.tuit.krill_lotin.helper.APIResponse;
import univ.tuit.krill_lotin.helper.ResponseBuilder;
import univ.tuit.krill_lotin.service.TranslatorService;
import univ.tuit.krill_lotin.utils.FileDownloadUtil;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import univ.tuit.krill_lotin.utils.FileUploadUtil;

import java.io.*;

@RestController
@RequestMapping("/api/v1")
public class LatinKrillApi {

    @Autowired
    TranslatorService translatorService;

    ResponseText rm = new ResponseText();


    @RequestMapping(value = "/test",
            method = RequestMethod.GET)
    String test() {
        return "It is working";
    }


    @RequestMapping(
            value = "/latin_krill/{id}",
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8", MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<APIResponse> translate(@PathVariable Integer id, @RequestParam String str) {

        if (id.equals(1)) rm = translatorService.transliterateToCyrillic(str);
        else rm = translatorService.transliterateToLatin(str);

        return ResponseBuilder.buildOK(rm, null, HttpStatus.OK);
    }

    @PostMapping("/readFile/{id}")
    public ResponseEntity<?> upDownLoadFile(
            @PathVariable Integer id,
            @RequestParam("file") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        //save file
        var fileUploadUtil = new FileUploadUtil(translatorService);
        String fileCode = fileUploadUtil.readTxtFile(fileName, multipartFile, id);

        var downloadUtil = new FileDownloadUtil();

        return uploadFile(downloadUtil, fileCode);

    }

    @PostMapping("/readWordFile/{id}")
    public ResponseEntity<?> wordFile(@PathVariable Integer id, @RequestParam("file") MultipartFile multipartFile) throws IOException {

        FileUploadUtil fileUploadUtil = new FileUploadUtil(translatorService);
        FileDownloadUtil fileDownloadUtil = new FileDownloadUtil();
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        String savedFileName = fileUploadUtil.updateDocument(fileName, id, multipartFile);

        return uploadFile(fileDownloadUtil, savedFileName);
    }

    private ResponseEntity<?> uploadFile(FileDownloadUtil fileDownloadUtil, String fileName) {
        Resource resource;
        try {
            resource = fileDownloadUtil.getFileAsResource(fileName);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }

        if (resource == null) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }
        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }
}

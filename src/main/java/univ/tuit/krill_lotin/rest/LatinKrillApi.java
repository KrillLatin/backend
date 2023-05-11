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
        if (id.equals(1))
            rm = translatorService.transliterateToCyrillic(str);
        else rm = translatorService.transliterateToLatin(str);
        return ResponseBuilder.buildOK(rm, null, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/readFile/{id}",
            method = RequestMethod.POST)
    public ResponseEntity<?> upDownLoadFile(
            @PathVariable Integer id,
            @RequestParam("file") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        //save file
        var fileUploadUtil = new FileUploadUtil(translatorService);
        String fileCode = fileUploadUtil.saveFile(fileName, multipartFile, id);

        Resource resource;
        FileDownloadUtil downloadUtil = new FileDownloadUtil();

        try {
            //get file
            resource = downloadUtil.getFileAsResource(fileCode);
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

   /* @RequestMapping(
            value = "/readFile/{id}/{fileName}",
            method = RequestMethod.GET)
    ResponseEntity<?> readFromFile(@PathVariable Integer id, @PathVariable String fileName) {
        FileDownloadUtil downloadUtil = new FileDownloadUtil();

        Resource resource = null;
        try {
            resource = downloadUtil.getFileAsResource(fileName);
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

      */
    /*

      BufferedReader br = new BufferedReader(new FileReader(file));
        String fileName = file.getName();
        String fileFormat = file.getAbsolutePath();
        String st;

        while ((st = br.readLine()) != null) {
            if (id.equals(1))
                rm = translatorService.transliterateToCyrillic(st);
            else rm = translatorService.transliterateToLatin(st);
        }

        FileWriter myWriter = new FileWriter("src/main/resources/temp"+fileName);
        myWriter.write(rm.getMessage());
        myWriter.close();
        File f = new File("src/main/resources/temp"+fileName);*//*
        //   return ResponseBuilder.buildOK(f, null, HttpStatus.OK);
    }*/

   /* @PostMapping("/uploadFile/{id}")
    public ResponseEntity<FileUploadResponse> uploadFile(
            @PathVariable Integer id,
            @RequestParam("file") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//        long size = multipartFile.getSize();

        String ext = Files.getFileExtension(fileName);
        int extSize = Files.getFileExtension(fileName).length() + 1;
        String filePureName = fileName.substring(0, fileName.length() - extSize);

        String savedFileName;
        if (id.equals(1)) {
            savedFileName = filePureName + "-ToKrill." + ext;
        } else {
            savedFileName = filePureName + "-ToLatin." + ext;
        }

        File file = null;
      */
    /*  WordExtractor extractor = null;
        try
        {
        file = new File("src/main/resources/temp/"+savedFileName);
        FileInputStream fis = new FileInputStream(file.getAbsolutePath());
        HWPFDocument document = new HWPFDocument(fis);
        extractor = new WordExtractor(document);
        String[] fileData = extractor.getParagraphText();
        for (int i = 0; i < fileData.length; i++)
        {
            if (fileData[i] != null)
                System.out.println(fileData[i]);
        }
    }
        catch (Exception exep)
    {
        exep.printStackTrace();
    }*/
    /*


        FileInputStream fis = new FileInputStream("src/main/resources/temp/" + savedFileName);
        StringBuilder sb = new StringBuilder();

        *//*XWPFDocument docx = new XWPFDocument(fis);
        List<XWPFParagraph> paragraphList = docx.getParagraphs();
        StringBuilder sb = new StringBuilder();

        for (XWPFParagraph paragraph:paragraphList){

            sb.append(paragraph);
        }
        translatorService.transliterateToCyrillic(String.valueOf(sb));
*//*
        String filecode = FileUploadUtil.saveFile(savedFileName, multipartFile);


        file = new File(filecode);

        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/temp/" + file));

        String st;
        // Condition holds true till
        // there is character in a string
        ResponseText responseText = new ResponseText();

        while ((st = br.readLine()) != null)

            if (id.equals(1)) {
                responseText = translatorService.transliterateToCyrillic(st);
                sb.append(responseText.getMessage());

            } else {
                responseText = translatorService.transliterateToLatin(st);
                sb.append(responseText.getMessage());

            }

        File newFile = new File("src/main/resources/temp/" + file);
        FileWriter fw = new FileWriter(newFile);
        fw.write(String.valueOf(sb));
        fw.close();


        FileUploadResponse response = new FileUploadResponse();


        response.setFileName(savedFileName);
//        response.setSize(size);
//        response.setDownloadUri("src/main/resources/temp/" + filecode);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }*/

  /*  private byte[] download(URL url) throws IOException {
        URLConnection uc = url.openConnection();
        int len = uc.getContentLength();
        InputStream is = new BufferedInputStream(uc.getInputStream());
        try {
            byte[] data = new byte[len];
            int offset = 0;
            while (offset < len) {
                int read = is.read(data, offset, data.length - offset);
                if (read < 0) {
                    break;
                }
                offset += read;
            }
            if (offset < len) {
                throw new IOException(
                        String.format("Read %d bytes; expected %d", offset, len));
            }
            return data;
        } finally {
            is.close();
        }
    }*/


}

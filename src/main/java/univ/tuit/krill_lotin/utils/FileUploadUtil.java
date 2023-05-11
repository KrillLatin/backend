package univ.tuit.krill_lotin.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;
import univ.tuit.krill_lotin.dto.ResponseText;
import univ.tuit.krill_lotin.service.TranslatorService;

public class FileUploadUtil {

    public FileUploadUtil(TranslatorService translatorService) {
        this.translatorService = translatorService;
    }

    private final TranslatorService translatorService;

    private String PATH = "src/main/resources/temp/";

    public String saveFile(String fileName, MultipartFile multipartFile, Integer id)
            throws IOException {
        Path uploadPath = Paths.get(PATH);

        ResponseText rm;
        StringBuilder sb = new StringBuilder();


        String savedFileName;
        if (id.equals(1))
            savedFileName = String.valueOf(sb.append("(Krill)").append(fileName));
        else
            savedFileName = String.valueOf(sb.append("(Latin)").append(fileName));


        if (!Files.exists(uploadPath))
            Files.createDirectories(uploadPath);


        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(savedFileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save file: " + savedFileName, ioe);
        }

        File file = new File(PATH + savedFileName);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        sb = new StringBuilder();

        while ((st = br.readLine()) != null) {

            if (id.equals(1)) {
                rm = translatorService.transliterateToCyrillic(st);
                sb.append(rm.getMessage());
                sb.append("\n");
            } else {
                rm = translatorService.transliterateToLatin(st);
                sb.append(rm.getMessage());
                sb.append("\n");
            }
        }
        FileWriter myWriter = new FileWriter(PATH + savedFileName);
        myWriter.write(sb.toString());
        myWriter.close();

        return savedFileName;
    }
}

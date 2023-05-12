package univ.tuit.krill_lotin.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.web.multipart.MultipartFile;
import univ.tuit.krill_lotin.dto.ResponseText;
import univ.tuit.krill_lotin.service.TranslatorService;

public class FileUploadUtil {

    StringBuilder sb;
    ResponseText rm;

    public FileUploadUtil(TranslatorService translatorService) {
        this.translatorService = translatorService;
    }

    private final TranslatorService translatorService;

    private String PATH = "src/main/resources/temp/";

    public String readTxtFile(String fileName, MultipartFile multipartFile, Integer id)
            throws IOException {
        String savedFileName = saveFile(fileName, multipartFile);

        File file = new File(PATH + savedFileName);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        sb = new StringBuilder();
        rm = new ResponseText();
        while ((st = br.readLine()) != null) {
            if (id.equals(1)) {
                rm = translatorService.transliterateToCyrillic(st);
            } else {
                rm = translatorService.transliterateToLatin(st);
            }
            sb.append(rm.getMessage());
            sb.append("\n");
        }
        FileWriter myWriter = new FileWriter(PATH + savedFileName);
        myWriter.write(sb.toString());
        myWriter.close();

        return savedFileName;
    }

    public String updateDocument(String input, Integer id, MultipartFile multipartFile) throws IOException {

        String originalFileName = saveFile(input, multipartFile);
        String savedFileName = null;
        try {
            FileInputStream fis = new FileInputStream(PATH + originalFileName);
            XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
            XWPFDocument newDoc = new XWPFDocument();

            List<XWPFParagraph> paragraphList = xdoc.getParagraphs();


            for (XWPFParagraph xwpfParagraph : paragraphList) {
                XWPFParagraph paragraph = newDoc.createParagraph();
                paragraph.setAlignment(xwpfParagraph.getAlignment());
                paragraph.setStyle(xwpfParagraph.getStyle());
                for (XWPFRun rn : xwpfParagraph.getRuns()) {
                    XWPFRun run = paragraph.createRun();

                    paragraph.addRun(rn);
                    paragraph.addRun(rn);
                    run.setFontSize(rn.getFontSize());
                    run.setTextPosition(rn.getTextPosition());
                    run.setFontFamily(rn.getFontFamily());

                    if (id.equals(1)) {
                        run.setText(translatorService.transliterateToCyrillic(rn.text()).getMessage());
                    } else {
                        run.setText(translatorService.transliterateToLatin(rn.text()).getMessage());
                    }
                }
            }
            savedFileName = createNewWordFile(newDoc, id, input);

            /* System.out.println("********************************************************************");
                System.out.println(xwpfParagraph.getText());
                System.out.println(xwpfParagraph.getAlignment());
                System.out.println(xwpfParagraph.getRuns().size());
                System.out.println(xwpfParagraph.getStyle());

                // Returns numbering format for this paragraph, eg bullet or lowerLetter.
                System.out.println(xwpfParagraph.getNumFmt());
                System.out.println(xwpfParagraph.getAlignment());

                System.out.println(xwpfParagraph.isWordWrapped());

                System.out.println("********************************************************************");*//*
            }*/
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return savedFileName;
    }

    private String saveFile(String fileName, MultipartFile multipartFile) throws IOException {

        Path uploadPath = Paths.get(PATH);

        if (!Files.exists(uploadPath))
            Files.createDirectories(uploadPath);

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save file: " + fileName, ioe);
        }
        return fileName;
    }

    private String createNewWordFile(XWPFDocument document, Integer id, String fileName) throws IOException {
        sb = new StringBuilder();
        String savedFileName;
        if (id.equals(1))
            savedFileName = String.valueOf(sb.append("(Krill)").append(fileName));
        else
            savedFileName = String.valueOf(sb.append("(Latin)").append(fileName));

        FileOutputStream out = new FileOutputStream(PATH + savedFileName);
        document.write(out);
        out.close();
        document.close();
        return savedFileName;
    }
}

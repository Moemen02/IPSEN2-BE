package nl.Groep13.OrderHandler.utils;

import com.aspose.cells.SaveFormat;
import nl.Groep13.OrderHandler.service.V2.LabelDataServiceV2;

import org.apache.commons.io.FileUtils;
import org.apache.fontbox.util.BoundingBox;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LabelCreation {
    LabelDataServiceV2 labelDataServiceV2;

    public LabelCreation(LabelDataServiceV2 labelDataServiceV2) {
        this.labelDataServiceV2 = labelDataServiceV2;
    }

    private String templateName;
    private Integer[] retourLabelRows = {7, 8, 10, 11, 12, 15, 21, 21, 24};
    private Integer[] retourLabelColumns = {3, 3, 3, 3, 3, 3, 3, 6, 3};
    private Integer[] storageLabelRows = {8, 11, 12, 15, 21, 21, 24};
    private Integer[] storageLabelColumns = {3, 3, 3, 3, 3, 6, 3};

    public HashMap<String, String> createLabel(Long Id) throws Exception {
        HashMap<String, String> label = labelDataServiceV2.getLabelData(Id);

        if (label == null) return null;

        String[] retourLabelData = {label.get("vervoerder"), label.get("klant"), label.get("factuurklant"),
                label.get("straat"), label.get("postcode"), label.get("orderCode"), label.get("description"),
                label.get("kleur"), label.get("metrage")};

        String[] storageLabelData = {label.get("klant"), "NULL", label.get("afdeling"),
                label.get("orderCode"), label.get("description"), label.get("kleur"), label.get("metrage")};



        if (label.get("retour").contains("true")) {
            templateName = "retourLabel";
            copyTemplate(templateName);
            makeExcelLabel(retourLabelRows,retourLabelColumns,retourLabelData, templateName);
        }

        if (label.get("retour").contains("false")) {
            templateName = "magazijnLabel";
            copyTemplate(templateName);
            makeExcelLabel(storageLabelRows, storageLabelColumns, storageLabelData, templateName);

        }
        return label;
    }

    private void copyTemplate(String templateName) throws IOException {
        File templateSource = new File("src/main/resources/Labels/" + templateName + ".xlsx");
        File copyTo = new File("src/main/resources/Labels/newLabel/" + templateName + ".xlsx");
        FileUtils.copyFile(templateSource, copyTo);
    }

    private void makeExcelLabel(Integer[] labelRows , Integer[] labelColumns, String[] labelData, String templateName) throws Exception {
        try {
            File labelPath = new File("src/main/resources/Labels/newLabel/" + templateName + ".xlsx");
            FileInputStream file = new FileInputStream(labelPath);
            Workbook wb = WorkbookFactory.create(file);
            Sheet sheet = wb.getSheetAt(0);

            for (int i = 0; i < labelData.length; i++) {
                Row row = sheet.getRow(labelRows[i]);
                Cell cell = row.createCell(labelColumns[i]);
                cell.setCellValue(labelData[i]);
            }

            file.close();

            FileOutputStream outputStream = new FileOutputStream(labelPath);
            wb.write(outputStream);
            wb.close();

            outputStream.close();
            toPdf(templateName);


        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }


    private void toPdf(String templateName){
        try {
            String path = "src/main/resources/Labels/newLabel/" + templateName + ".xlsx";
            com.aspose.cells.Workbook toPdf = new com.aspose.cells.Workbook(path);
            toPdf.save("src/main/resources/Labels/newLabel/" + templateName, SaveFormat.PDF);
            toPng(templateName);
        }
        catch (ExceptionInInitializerError | Exception e){

        }
    }

    private void toPng(String templateName) throws IOException {
        try {
            String source = "src/main/resources/Labels/newLabel/" + templateName;
            String destination = "src/main/resources/images/";
            File sourceFile = new File(source);

            PDDocument document = PDDocument.load(source);
            List<PDPage> list = document.getDocumentCatalog().getAllPages();
            String fileName = sourceFile.getName().replace(".pdf", "");

            int pageNumber = 1;
            for (PDPage page : list) {
                page.setCropBox(new PDRectangle(new BoundingBox(100,200,500,700)));
                BufferedImage image = page.convertToImage();

                File outputfile = new File(destination + fileName + ".png");
//                File file = new ClassPathResource("images/" + fileName + ".png").getFile();

                pageNumber++;
                ImageIO.write(image, "png", outputfile);

            }
            document.close();
//            placeImage("src/main/resources/images/" + templateName + ".png");

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

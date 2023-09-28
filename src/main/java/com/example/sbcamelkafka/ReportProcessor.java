package com.example.sbcamelkafka;

import com.sun.istack.ByteArrayDataSource;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.attachment.AttachmentMessage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.activation.DataHandler;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ReportProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        ArrayList<Meeting> ms = exchange.getIn().getBody(ArrayList.class);
        byte[] buf = {};

        Workbook workbook = new XSSFWorkbook();
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);

        Sheet sheet = workbook.createSheet("Meetings");
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 4000);
        Row header = sheet.createRow(0);


        for (int i = 0; i < ms.size(); i++) {

            Row row = sheet.createRow(i);
            Cell cell = row.createCell(0);
            cell.setCellValue(ms.get(i).getVisitor_id());
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue(ms.get(i).getHost_employee());
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellValue(ms.get(i).getEmployee_email());
            cell.setCellStyle(style);

            cell = row.createCell(4);
            cell.setCellValue(ms.get(i).getStart_time());
            cell.setCellStyle(style);

            cell = row.createCell(5);
            cell.setCellValue(ms.get(i).getEnd_time());
            cell.setCellStyle(style);

        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (outputStream) {
            workbook.write(outputStream);
        }

        AttachmentMessage attMsg = exchange.getIn(AttachmentMessage.class);
        attMsg.addAttachment("meeting-invite.xslx",
                new DataHandler(new ByteArrayDataSource(outputStream.toByteArray(),
                        "text/plain")));
    }

}
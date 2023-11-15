package com.seneau.agentservice.services.utils;

import com.seneau.agentservice.data.model.Agent;
import com.seneau.agentservice.data.model.Contrat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class UploadService {

    public Date formatDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.parse(date);
    }

    public List<Agent> getAgentsFromExcelFile(MultipartFile file, Integer numberOfSheet) throws IOException, ParseException {
        List<Agent> agents = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        if (numberOfSheet == null || numberOfSheet < 0) {
            numberOfSheet = workbook.getNumberOfSheets();
        }
        for (int i = 0; i < numberOfSheet; i++) {
            Sheet sheet = workbook.getSheetAt(i);

            DataFormatter dataFormatter = new DataFormatter();
            Iterator<Row> rowIterator = sheet.rowIterator();
            int j = 0;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (j != 0) {
                    Agent agent = new Agent();
                    agent.setMatricule(Integer.parseInt(dataFormatter.formatCellValue(row.getCell(0))));
                    agent.setFullName(dataFormatter.formatCellValue(row.getCell(1)) + " " + dataFormatter.formatCellValue(row.getCell(2)));
                    agent.setSituationMatrimoniale(dataFormatter.formatCellValue(row.getCell(3)));
                    agent.setDateNaissance(formatDate(dataFormatter.formatCellValue(row.getCell(4))));
                    Contrat contrat = new Contrat();
                    contrat.setDateDebut(formatDate(dataFormatter.formatCellValue(row.getCell(5))));
                    contrat.setDateRetraite(formatDate(dataFormatter.formatCellValue(row.getCell(6))));
                    agents.add(agent);
                }
                j++;
            }
        }
        return agents;
    }
}

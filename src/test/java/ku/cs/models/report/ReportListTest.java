package ku.cs.models.report;

import ku.cs.service.ProcessData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReportListTest {

    @Test
    void getReportLists() {
        ProcessData processData = new ProcessData<>();
        List<Report> test = processData.getReportList().getReportLists();

        for(Report temp : test){

            System.out.println(temp.getTitle());
        }
        System.out.println(test);

    }
}
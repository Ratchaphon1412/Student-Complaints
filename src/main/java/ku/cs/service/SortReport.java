package ku.cs.service;

import ku.cs.models.report.Report;
import ku.cs.models.report.ReportList;

import java.util.Comparator;
import java.util.List;

public class SortReport {

    private static List<Report> reportSort;



    private static ReportList reportLists = new ReportList();

    public static void sortCollection(String choice){

        switch (choice) {
            case "New Post" -> {
                Comparator newPost = (o1, o2) -> {
                    Report p1 = (Report) o1;
                    Report p2 = (Report) o2;
                    return p2.getDate().compareTo(p1.getDate());
                };
                reportSort.sort(newPost);
            }
            case "Old Post" -> {
                Comparator oldPost = (o1, o2) -> {
                    Report p1 = (Report) o1;
                    Report p2 = (Report) o2;
                    return p1.getDate().compareTo(p2.getDate());
                };
                reportSort.sort(oldPost);
            }
            case "Most Like" -> {
                Comparator mostLike = (o1, o2) -> {
                    Report p1 = (Report) o1;
                    Report p2 = (Report) o2;
                    return p2.getCountLike() - p1.getCountLike();
                };
                reportSort.sort(mostLike);
            }
            case "Least Like" -> {
                Comparator leastLike = (o1, o2) -> {
                    Report p1 = (Report) o1;
                    Report p2 = (Report) o2;
                    return p1.getCountLike() - p2.getCountLike();
                };
                reportSort.sort(leastLike);
            }
        }

    }


    public static void sortCategory(String choice){
        //set temp
        if(!choice.equals("All")) {
            reportLists.setReportSetterSort(reportSort);
            ReportList reportpare = reportLists.sortReport(report -> report.getCategory().getNameCategory().equals(choice));
            reportSort = reportpare.getReportSort();
        }

    }

    public static  void sortProcess(String choice){
        //set temp


        if(!choice.equals("All")){
            reportLists.setReportSetterSort(reportSort);
            ReportList reportpare = reportLists.sortReport(report -> report.getReportStage().equals(choice));
            reportSort = reportpare.getReportSort();
        }


    }

    public static void sortVote (String less ,String most){
        if(!most.equals("") && !less.equals("")) {
            int lessInt = Integer.parseInt(less);
            int mostInt = Integer.parseInt(most);
            reportLists.setReportSetterSort(reportSort);
            ReportList reportpare = reportLists.sortReport(report -> report.getCountLike() >= lessInt && report.getCountLike() <= mostInt);
            reportSort = reportpare.getReportSort();
        }else if(!most.equals("")){
            int mostInt = Integer.parseInt(most);
            reportLists.setReportSetterSort(reportSort);
            ReportList reportpare = reportLists.sortReport(report -> report.getCountLike() > mostInt);
            reportSort = reportpare.getReportSort();
        }

    }


    public static List<Report> getReportSort() {
        return reportSort;
    }

    public static void setReportSort(List<Report> reportSort) {
        SortReport.reportSort = reportSort;
    }


}

package ku.cs.models.report;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

public class Category {
    private String nameCategory;
    private String data;
    private String pattern;

    private LinkedHashMap<String, List<LinkedHashMap<String,String>>> mapPatternValue;

    public Category(String nameCategory,String data,String pattern) {
        this.mapPatternValue = new LinkedHashMap<>();
        this.nameCategory = nameCategory;
        this.data = data;
        this.pattern = pattern;
        mapPattern();
    }



    private void mapPattern(){
        String[] patternArray = pattern.split("\\|");
        String[] dataArray = data.split("\\|");
        List<LinkedHashMap<String,String>> tempText =  new ArrayList<>();
        List<LinkedHashMap<String,String>> tempImage = new ArrayList<>();

        for(int i = 0; i < patternArray.length;i++ ){
            switch (patternArray[i]){
                case "text":{
                    LinkedHashMap<String,String> dataText = new LinkedHashMap<>();
                    String[] subdata = dataArray[i].split(":");
                    dataText.put(subdata[0],subdata[1]);
                    tempText.add(dataText);
                }
                case "image":{
                    LinkedHashMap<String,String> dataImage = new LinkedHashMap<>();
                    String[] subdata = dataArray[i].split(":");
                    dataImage.put(subdata[0],subdata[1]);
                    tempImage.add(dataImage);
                }
            }

        }
        mapPatternValue.put("text",tempText);
        mapPatternValue.put("image",tempImage);





    }

    public String getNameCategory() {
        return nameCategory;
    }

    public LinkedHashMap<String, List<LinkedHashMap<String, String>>> getMapPatternValue() {
        return mapPatternValue;
    }
}

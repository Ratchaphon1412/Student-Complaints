package ku.cs.models.report;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

public class Category {
    private String nameCategory;
    private String dataText;

    private String dataImage;
    private String text;
    private String image;

    private LinkedHashMap<String,LinkedHashMap<String,String>> mapDataPattern;


    public Category(String nameCategory,String dataText,String dataImage,String text,String image) {
        this.mapDataPattern = new LinkedHashMap<>();
        this.nameCategory = nameCategory;
        this.dataText = dataText;
        this.dataImage = dataImage;
        this.text = text;
        this.image = image;
        mapPattern();
    }



    private void mapPattern(){
        String[] dataTextArray = dataText.split("\\|");
        String[] dataImageArray = dataImage.split("\\|");
        String[] patternText = text.split("\\|");
        String[] patternImage = image.split("\\|");
        LinkedHashMap<String,String> generateText = new LinkedHashMap<>();
        // บรรยายความรู้สึก key น้ำท้วมแล้วไอสาสสสสสเอ้ย value
        for(int i = 0; i < patternText.length ; i++){
          generateText.put(patternText[i],dataTextArray[i]);
        }
        LinkedHashMap<String,String> generateImage = new LinkedHashMap<>();
        //รูปภาพเพิ่มเดิมของสถานที key  gugGG_2022-09-26_1664207021492.jpg value
        for(int i = 0;i < patternImage.length; i++){
            generateImage.put(patternImage[i],dataImageArray[i]);
        }

        mapDataPattern.put("text",generateText);
        mapDataPattern.put("image",generateImage);
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public LinkedHashMap<String, LinkedHashMap<String, String>> getMapDataPattern() {
        return mapDataPattern;
    }
}

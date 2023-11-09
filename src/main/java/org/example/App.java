package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world! W:37.79  H:28.35
 * ROW_HEIGHT = 200; COL_WIDTH = 800;  HEIGHT:13  ,WIDTH:26
 * ROW_HEIGHT = 200; COL_WIDTH = 1600; HEIGHT:14  ,WIDTH:51
 * ROW_HEIGHT = 400; COL_WIDTH = 1600; HEIGHT:27  ,WIDTH:51
 * ROW_HEIGHT = 4000; COL_WIDTH = 16000; HEIGHT:267  ,WIDTH:501
 * ROW_HEIGHT = 12800; COL_WIDTH = 25600; HEIGHT:547  ,WIDTH:801
 * h:14.28, 14.81 17.98 14.88
 * w:30.7,  31.3  31.93 31.6
 * 44 16
 * 46 18
 */
public class App {  //1:3 完美
    /**
     * 84 22 3.8
     * 73 19
     */
    static int RATIO = 3;   //W/H
    static int SM = 1;
    static short ROW_HEIGHT = (short)14.175* 10;
    //static int COL_WIDTH = (short)37.79* 40;

    public static void main(String[] args) throws IOException {

        BufferedImage image = ImageIO.read(new File("D:\\test\\4.png"));
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        // 获取图像的像素数据
        int width = image.getWidth();
        int height = image.getHeight();
        Map<String,CellStyle> cellStyleMap = new HashMap<>();
        for (int y = 0; y*SM < height; y++) {
            for(int count = 0;count<RATIO;count++){
                Row row = sheet.createRow(y*RATIO+count);
                row.setHeightInPoints((float) 18.25);   //行高
                for (int x = 0; x*SM < width; x++) {
                    java.awt.Color color = new Color(image.getRGB(x*SM, y*SM));
                    String key = color.getRed()+"_"+color.getGreen()+"_"+color.getBlue();
                    CellStyle cellStyle;
                    if(cellStyleMap.containsKey(key)){
                        cellStyle = cellStyleMap.get(key);
                    }else{
                        cellStyle = workbook.createCellStyle();
                        XSSFColor xssfColor = new XSSFColor();
                        xssfColor.setRGB(new byte[]{(byte)color.getRed(),(byte)color.getGreen(),(byte)color.getBlue()});
                        cellStyle.setFillForegroundColor(xssfColor);
                        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                        cellStyleMap.put(key,cellStyle);
                    }
                    Cell cell = row.createCell(x);

                    cell.setCellStyle(cellStyle);
                }
            }
        }

        /*for (int x = 0; x < width; x++){
            sheet.setColumnWidth(x,COL_WIDTH);  //列宽 5712/31.7
        }*/

        System.out.println("ColumnWidth: "+sheet.getColumnWidth(0));    //     180 *31.7 = 5712
        System.out.println("RowHeight: "+sheet.getRow(0).getHeight());  //point*20  27   400*14.81 = 27

        // 将工作簿写入文件
        try  {
            FileOutputStream outputStream = new FileOutputStream("D:\\test\\4.xlsx");
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        workbook.close();
    }
}

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CSV文件操作工具类
 */
public class CsvUtil {

    private static final String dir = System.getProperty("user.dir") + "/test";

    public static void main(String[] args){
        try {
            CsvWriterAll();
            CsvReaderAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void CsvReaderAll() throws IOException {
        File csv = new File(dir + "/writerTest.csv");
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(csv), "GBK"), ',');
        String[] header = reader.readNext();
        for(String s : header) {
            System.out.print(s + " ");
        }
        System.out.println();
        List<String[]> list = reader.readAll();
        for(int i=0; i<list.size(); i++) {
            for(int j=0; j < list.get(i).length; j++) {
                System.out.print(list.get(i)[j] + " ");
            }
            System.out.println();
        }
    }


    public static void CsvWriterAll() throws IOException {
        String path = dir + "/writerTest.csv";
        File csv = new File(path);
        if (!csv.exists()) csv.createNewFile();

        List<String[]> list = new ArrayList<>();

        for (int i = 0; i < 22; i++) {

            String[] ss = {String.valueOf("张小"+i),String.valueOf(i%2==1? "男":"女"),String.valueOf(i),String.valueOf("北总布胡同 "+i+"号院")};
            list.add(ss);
        }

        CSVWriter writer = new CSVWriter(new OutputStreamWriter(new FileOutputStream(csv),"GBK"),CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER);
        String[] arr = {"姓名","性别","年龄","住址"};
        writer.writeNext(arr);
        writer.writeAll(list);
        writer.flush();
        writer.close();
    }


}

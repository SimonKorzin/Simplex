package logic;

import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {



    private Double[] matrixX = new Double[500];
//    public  List<String> list = new ArrayList<String>();


    public static  void main(String[] args) {
//        double[][] matrixMain = getTableByRows("test", "test1", "test3");
        Main main = new Main();
//        double[][] matrixMain = main.getTableByRows("test", "test1");
//        for(double[] arr: matrixMain) {
//            System.out.println();
//            for(double d: arr){
//                System.out.print(d + " ");
//            }
//        }
//        String[] strings1 = {"age", "stab.glu", "chol", "weight"};
//        System.out.println("for(double d: getTableByRows(\"chol\",\"stanin\")[0]){");
//        for(double d: main.getTableByRows("chol", "stanin")[0]){
//            System.out.println(d + " ");


//        main.getArrayByRows("s.blood");
//        createOLS("ratio","age", "stab.glu", "chol", "weight");
//        main.createOLS("ratio", "age", "stab.glu", "chol", "weight");

        ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>(0);
        lists.add(new ArrayList<Integer>(0));
        lists.add(new ArrayList<Integer>(0));
        ArrayList<Integer> l = lists.get(0);
        l.add(0);
        l.add(1);
        l.add(2);
        l.add(3);
        lists.set(0, l);
        l = lists.get(1);
        l.add(3);
        l.add(2);
        l.add(1);
        l.add(0);
        lists.set(1, l);
        for (Integer i : lists.get(0)){
            System.out.println("list.get(0): " + i);
        }
        for (Integer i : lists.get(1)){
            System.out.println("list.get(1): " + i);
        }
//        main.createOLS("test3", "test", "test1");
//        importing();


    }

    public  double[] getArrayByRows(String string){
        double [][] temp = getTableByRows(string);
        double returnArr[] = new double[temp.length];
        System.out.println(temp.length + " temp.length\n returnArr: ");
        for(int i=0; i<temp.length; i++){
            returnArr[i] = temp[i][0];
            System.out.print(returnArr[i]);
        }
        return returnArr;
    }
    public List<String> getNameOfColumn(String str){
        try{
            Resource resource = new ClassPathResource(str);
            System.out.println("filename " + resource.getFilename());
            FileInputStream fileInputStream = new FileInputStream(resource.getFile());
//            File file = new File("regressiondata/databaseMain.xls");
//            FileInputStream fileInputStream = new FileInputStream("regressiondata/databaseMain.xls");
//            FileInputStream fileInputStream = new FileInputStream("WEB-INF/regressiondata/databaseMain.xls");

//            InputStream resourceInputStream = resource.getInputStream();
//            System.out.println("toString " + resourceInputStream.toString());


            POIFSFileSystem fs = new POIFSFileSystem(fileInputStream);
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);

            HSSFRow row;
            HSSFRow innerRow;
            HSSFCell cell;
            HSSFCell innerCell;

            int rows; // No of rows
            rows = sheet.getPhysicalNumberOfRows();

            int cols = 0; // No of columns
            int tmp = 0;

            // This trick ensures that we get the data properly even if it doesn't start from first few rows
            for(int i = 0; i < 10 || i < rows; i++) {
                row = sheet.getRow(i);
                if(row != null) {
                    tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                    if(tmp > cols) cols = tmp;
                }
            }
            rows = 29;

            int i= -1;
            row = sheet.getRow(0);
            List<String> list1 = new ArrayList<String>(0);
            for(int c = 0; c < cols; c++) {
                System.out.println("c= " + c);
//                        System.out.println("row= " + row.);
                cell = row.getCell((short) c);
                list1.add(cell.getStringCellValue());
                System.out.println("list value in getNameMethods " + list1.get(c));
            }
            return list1;
        }catch(IOException e) {
            e.printStackTrace();
            return null;
    }

    }

    public  double[][] getTableByRows(String... strings){
        System.out.println("In getTablesByRows");
        try{
//            File file = new File("regressiondata/databaseMain.xls");
//
//            FileInputStream fileInputStream = new FileInputStream("WEB-INF/regressiondata/databaseMain.xls");
//            Resource resource = new ClassPathResource("regressiondata/databaseMain.xls");
            //FOR SPRING ----------------------------------------------------------
                     Resource resource = new ClassPathResource("regressiondata/databaseMain.xls");
                     System.out.println("filename " + resource.getFilename());
                     FileInputStream fileInputStream = new FileInputStream(resource.getFile());
            //---------------------------------------------------------------------
//            FileInputStream fileInputStream = new FileInputStream("regressiondata/databaseMain.xls");
//            InputStream resourceInputStream = resource.getInputStream();
//            System.out.println("toString " + resourceInputStream.toString());
            //REQUIRED=============================================================

            //======================================================================
            POIFSFileSystem fs = new POIFSFileSystem(fileInputStream);
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);

            HSSFRow row;
            HSSFRow innerRow;
            HSSFCell cell;
            HSSFCell innerCell;

            int rows; // No of rows
            rows = sheet.getPhysicalNumberOfRows();

            int cols = 0; // No of columns
            int tmp = 0;

            // This trick ensures that we get the data properly even if it doesn't start from first few rows
            for(int i = 0; i < 10 || i < rows; i++) {
                row = sheet.getRow(i);
                if(row != null) {
                    tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                    if(tmp > cols) cols = tmp;
                }
            }
            rows = 29;//29 for databaseMain.xls
            double[][] matrix = new double[rows-1][strings.length];
                int i= -1;
                row = sheet.getRow(0);

                    for(int c = 0; c < cols; c++) {
                        System.out.println("c= " + c);
//                        System.out.println("row= " + row.);
                        cell = row.getCell((short)c);

                            for(String str: strings){
                                System.out.println("str = " + str + ", cell = " + cell.toString());
                                if (str.equals(cell.getRichStringCellValue().getString())){
                                    i++;
                                    System.out.println("Outer i=" + i);
                                    System.out.println("I find input string: " + str + " " + cell.getRichStringCellValue().getString());
                                    System.out.println("rows " + rows);
                                    for(int rr = 1; rr < rows; rr++) {
                                        System.out.println("Inner rr = " + rr);
                                        innerRow = sheet.getRow(rr);
                                        innerCell = innerRow.getCell((short) c);
//                                        try {
//                                            System.out.println("cell.toString()" + cell.toString());
//                                            System.out.println("parseDouble" + Double.parseDouble(cell.toString()));
//                                        }catch(NumberFormatException e){
//                                            System.out.println("NumberFormatException catch!");}
                                        matrix[rr-1][i] = Double.parseDouble(innerCell.toString());
//                                        System.out.println("matrix[r][i]" + matrix[rr][i]);
                                    }

                                    }
                                }
                            //                            System.out.println(cell.getRichStringCellValue().getString());
//                            switch (cell.getRichStringCellValue().getString()) {
//                                case "test":
//                                    System.out.println("FROM STRINGS mE find test at" + cell.getRichStringCellValue().getString() + " " + cell.getRowIndex() + ":" + c);
//                                case "test1":
//                                    System.out.println("FROM STRINGS mE I find test1 at" + cell.getRichStringCellValue().getString() + " " + cell.getRowIndex() + ":" + c);
//                                case "test2":
//                                    System.out.println("FROM STRINGS mE I find test2 at" + cell.getRichStringCellValue().getString() + " " + cell.getRowIndex() + ":" + c);
//                            }
                            System.out.println("row= " + 0 + "; column= " + c);
                        }
            System.out.println("MATRIX");
            for(double[] arr: matrix) {
                System.out.println();
                for (double d : arr) {
                    System.out.print(d + " ");
                }
            }
            return matrix;
        }catch(IOException ioe) {
            ioe.printStackTrace();
            return null;
        }

    }

    public  OLSMultipleLinearRegression createOLS(String str, String... strs){
        OLSMultipleLinearRegression ols = new OLSMultipleLinearRegression();
        ols.newSampleData(getArrayByRows(str), getTableByRows(strs));
        List<String> coofs = new ArrayList<String>();
        coofs.add(new String("" + ols.calculateRSquared()));
        System.out.println("calculateRSquared() "+ coofs.get(0));
        coofs.add(new String("" +ols.calculateTotalSumOfSquares()));
        System.out.println("calculateTotalSumOfSquares() " + coofs.get(1));
        coofs.add(new String("" +ols.calculateAdjustedRSquared()));
        System.out.println("ols.calculateAdjustedRSquared() " + coofs.get(2));
        coofs.add(new String("" +ols.estimateRegressandVariance()));
        System.out.println("ols.estimateRegressandVariance() " + coofs.get(3));


        System.out.println("calculateHat() " + ols.calculateHat());

        System.out.println("ols.estimateRegressionParameters());");
        for(double d:  ols.estimateRegressionParameters()) {
            System.out.println(" " + d);
        }

        return  ols;
    }















    public  double[][] getAlterTableByRows(){
        System.out.println("IN GETALTERTABLEROWS");
        double[][] matrixBefore =  getTableByRows("age","stab.glu", "chol", "weight");
        double[][] matrixAfter = new double[matrixBefore[0].length][matrixBefore.length];
        for(int i=0; i<matrixBefore.length; i++){
            System.out.println();
            for (int j=0; j<matrixBefore[i].length; j++){
                matrixAfter[j][i] = matrixBefore[i][j];
                System.out.println(" " + matrixAfter[j][i]);
            }
        }
        return matrixAfter;
    }
//    public  void importing(){
////        System.out.println(System.getProperty("path.separator" + " " + File.separator));
//        try{
//            FileInputStream fileInputStream = new FileInputStream("regressiondata/source.xls");
//            POIFSFileSystem fs = new POIFSFileSystem(fileInputStream);
//            HSSFWorkbook wb = new HSSFWorkbook(fs);
//            HSSFSheet sheet = wb.getSheetAt(0);
//            HSSFRow row;
//            HSSFCell cell;
//            int rows; // No of rows
//            rows = sheet.getPhysicalNumberOfRows();
//
//            int cols = 0; // No of columns
//            int tmp = 0;
//
//            // This trick ensures that we get the data properly even if it doesn't start from first few rows
//            for(int i = 0; i < 10 || i < rows; i++) {
//                row = sheet.getRow(i);
//                if(row != null) {
//                    tmp = sheet.getRow(i).getPhysicalNumberOfCells();
//                    System.out.println(" i:" + i + " tmp" + tmp);
//                    if(tmp > cols) cols = tmp;
//                }
//            }
//
//            for(int r = 0; r < rows; r++) {
//                row = sheet.getRow(r);
//                if(row != null) {
//                    for(int c = 0; c < cols; c++) {
//                        cell = row.getCell((short)c);
//
//                        if(r == 0) {
////                            System.out.println(cell.getRichStringCellValue().getString());
//                            switch (cell.getRichStringCellValue().getString()) {
//                                case "test":
//                                    System.out.println("I find test at" + cell.getRichStringCellValue().getString() + " " + cell.getRowIndex() + ":" + c);
//                                case "test1":
//                                    System.out.println("I find test1 at" + cell.getRichStringCellValue().getString() + " " + cell.getRowIndex() + ":" + c);
//                                case "test2":
//                                    System.out.println("I find test2 at" + cell.getRichStringCellValue().getString() + " " + cell.getRowIndex() + ":" + c);
//                            }}else {
////
////                        }
//                            System.out.println("cellType :" + cell.getCellType());
//                            switch (cell.getCellType()) {
//                                case HSSFCell.CELL_TYPE_STRING:
//                                    System.out.println(cell.getRichStringCellValue().getString());
//                                    break;
//                                case HSSFCell.CELL_TYPE_BOOLEAN:
//                                    System.out.println(cell.getBooleanCellValue());
//                                    break;
//                                case HSSFCell.CELL_TYPE_NUMERIC:
//                                    System.out.println(cell.getNumericCellValue());
//                                default:
//                                    System.out.println();
//                            }
//
//                            System.out.println("Another shit " + r + ":" + c);
//                        }
//                    }
//                }
//            }
//        } catch(IOException ioe) {
//            ioe.printStackTrace();
//        }
//    }
}

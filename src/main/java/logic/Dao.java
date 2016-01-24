package logic;

import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 15-Dec-15.
 */
public class Dao {
    public static double[] getArrayByRows(String string){
        double [][] temp = getTableByRows(string);
        double returnArr[] = new double[temp.length];
        System.out.println(temp.length + " temp.length\n returnArr: ");
        for(int i=0; i<temp.length; i++){
            returnArr[i] = temp[i][0];
            System.out.print(returnArr[i]);
        }
        return returnArr;
    }
    public static double[][] getTableByRows(String... strings){
        System.out.println("In getTablesByRows");
        try{
//            FileInputStream fileInputStream = new FileInputStream("regressiondata/source.xls");
            FileInputStream fileInputStream = new FileInputStream("regressiondata/databaseMain.xls");
//            FileInputStream fileInputStream = new FileInputStream("regressiondata/data.xls");

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
            double[][] matrix = new double[rows][strings.length];
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
                            matrix[rr][i] = Double.parseDouble(innerCell.toString());
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

    public static List<String> createOLS(String str, String... strs){
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

        return  coofs;
    }


}

package com.nju.datautil;


import java.io.*;

/**
 * create by stephen on 2018/7/6
 */
public class IOUtil {
    /**
     * 本地文件是否存在
     */
    public static boolean isFileExisted(String path) {
        File file = new File(path);
        return file.isFile() && file.exists();
    }

    /**
     * 存入文件
     */
    public static void saveArrayToFile(double[][] similarityMatrix, String fileName) {
        try (FileWriter writer = new FileWriter(fileName, false)) {
            for (int i = 0; i < similarityMatrix.length; ++i) {
                for (int j = 0; j < similarityMatrix[0].length; ++j) {
                    if (j == 0) {
                        writer.write(similarityMatrix[i][j] + "");
                    } else {
                        writer.write("," + similarityMatrix[i][j]);
                    }
                }
                writer.write("\n");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double[][] readArrayFromFile(String fileName) {
        double[][] matrix = new double[119][119];
        int row = 0;
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName)))) {

            String text;
            String[] temp;
            while ((text = bufferedReader.readLine()) != null) {
                temp = text.split(",");
                for (int j = 0; j < 119; ++j) {
                    matrix[row][j] = Double.valueOf(temp[j]);
                }
                row++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return matrix;
    }
}

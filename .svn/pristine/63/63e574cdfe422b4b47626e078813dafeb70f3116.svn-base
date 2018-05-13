package com.java.util;

import java.io.BufferedInputStream;  
import java.io.BufferedOutputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.util.Enumeration;  
  
import org.apache.tools.zip.ZipEntry;  
import org.apache.tools.zip.ZipFile;  
import org.apache.tools.zip.ZipOutputStream;  
  
public class ZipUtil {  
  
    private static final int BUFFEREDSIZE = 1024;  

    public void zip(String zipFileName, String filePath, boolean isDelete) throws Exception {  
        zip(zipFileName, new File(filePath), isDelete);  
    }  
    public void zip(String zipFileName, File inputFile, boolean isDelete) throws Exception {  
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
        out.setEncoding("gbk");
        if (!inputFile.exists()) {  
            throw new FileNotFoundException("在指定路径未找到需要压缩的文件！");  
        }  
        zip(out, inputFile, "", isDelete);  
        out.close();  
    }  
  
    /** 
     * 递归压缩方法 
     *  
     * @param out 
     *            压缩包输出流 
     * @param f 
     *            需要压缩的文件 
     * @param base 
     *            压缩的路径 
     * @param isDelete 
     *            是否删除源文件 
     * @throws Exception 
     */  
    private void zip(ZipOutputStream out, File inputFile, String base, boolean isDelete) throws Exception {  
        if (inputFile.isDirectory()) { // 如果是目录  
            File[] inputFiles = inputFile.listFiles();  
            out.putNextEntry(new ZipEntry(base + "/"));  
            base = base.length() == 0 ? "" : base + "/";  
            for (int i = 0; i < inputFiles.length; i++) {  
                zip(out, inputFiles[i],base + inputFiles[i].getName(), isDelete);  
            }  

        } else { // 如果是文件  
            if (base.length() > 0) {  
                out.putNextEntry(new ZipEntry(base));  
            } else {  
                out.putNextEntry(new ZipEntry(inputFile.getName()));  
            }  
            FileInputStream in = new FileInputStream(inputFile);  
            try {  
                int len;  
                byte[] buff = new byte[BUFFEREDSIZE];  
                while ((len = in.read(buff)) != -1) {  
                    out.write(buff, 0, len);  
                }  
            } catch (IOException e) {  
                throw e;  
            } finally {  
                in.close();  
            }  
        }  
        if (isDelete) {  
            inputFile.delete();  
        }  
    }  
}  

package pan.util;

import java.io.File;
import java.text.DecimalFormat;

/**
 * @名称 有关文件操作的工具类，封装了一些文件操作的方法
 * @作者 zhaoxuyang
 * @版本 v1.0
 * @日期 2019-01-01
 */
public class FileUtil {

    public static void main(String[] args){
//        
//        //获取一个文件下的所有文件字节数测试
//        long length = FileUtil.getSizeFromFolder("D:/soft");
//        System.out.println(length);
//        String size = FileUtil.formatFileSize(length);
//        System.out.println(size);
// 
        
        //格式化目录或文件类别的测试
        System.out.print(formatType(new File("D:/soft")));
        
        
    }
    
    /**
     * @param newDirPath
     * @return 
     * @描述 联级创建单个文件夹
     */
    public static boolean mkdirs(String newDirPath) {
        File f = new File(newDirPath);
        if (f.exists()) {
            System.out.println("文件夹已存在" + f.getAbsolutePath());
            return false;
        } else {
            return f.mkdirs();
        }
    }
    
    /**
     * 格式化文件类型，结果依配置文件的设置而定
     * @param file
     * @return 
     */
    public static String formatType(File file) {
        if(file.isFile()){
            return PathUtil.getValueByKey("file.type.file");//“文件”
        } else {
            return PathUtil.getValueByKey("file.type.directory");//“目录”
        }
    }
    
    
    /**
     * @param length
     * @return
     * @描述 将文件长度转换成人性化的文件大小字符串
     */
    public static String formatFileSize(long length) {
        if (length < 0) {
            return "";
        } else if (length < 1024) {
            return decimalFormat.format(length) + "B";
        } else if (length < 1024 * 1024) {
            return decimalFormat.format(length / 1024.0) + "KB";
        } else if (length < 1024 * 1024 * 1024) {
            return decimalFormat.format(length / 1024.0 / 1024) + "MB";
        } else {
            return decimalFormat.format(length / 1024.0 / 1024 / 1024) + "GB";
        }
    }
    private static final DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    
    /**
     * 获取一个文件或目录中所有文件的字节数
     * @param folderPath
     * @return 
     */
    public static long getFileLength(final String folderPath){
        long size = 0;
        File file = new File(folderPath);
        //System.out.println(file.getAbsolutePath());
        if(file.isFile()){
            size += file.length();
        } else {
            File []files = file.listFiles();
            for (File f:files){
                size += getFileLength(f.getAbsolutePath());
            }
        }
        return size;
    }
    


    /**
     * @param filePath 文件(夹)路径
     * @描述 删除文件，如果是文件夹，则递归删除所有的
     */
    public static void deleteFile(final String filePath) {

        File file = new File(filePath);
        if (file.isFile()) {
            file.delete();
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                deleteFile(f.getAbsolutePath());
            }
            file.delete();
        }
    }
    
    /**
     * @param path
     * @param fileName
     * @描述 检查path后面是否有/或者\
     * @return path+name
     */
    public static String pathCheck(final String path, final String fileName) {

        if (path.endsWith("/") || path.endsWith("\\")) {
            return path + fileName;
        } else {
            return path + "/" + fileName;
        }
    }
}

package club.nwsuaf.service;

import club.nwsuaf.model.FileVO;
import java.io.File;
import java.util.List;

/**
 * @名称 逻辑服务 - 文件
 * @作者 赵栩旸
 */

public interface FileService {
    public List<FileVO> listFiles();
    /**
     * 将java.io.File 转换成FileVO
     * @param file
     * @return FileVO
     */
    public FileVO formatFileVO(File file);
    
    /**
     * 获取保存文件夹
     * @return 
     */
    public String getUploadFolder();
    
    /**
     * 删除文件
     * @param fileName
     * @return 
     */
    public boolean deleteFile(String fileName);
    
    /**
     * 重命名
     * @param name
     * @param newName
     * @return 
     */
    public boolean rename(String name,String newName);
}
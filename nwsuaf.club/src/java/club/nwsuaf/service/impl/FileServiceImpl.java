package club.nwsuaf.service.impl;

import club.nwsuaf.model.FileVO;
import club.nwsuaf.service.FileService;
import club.nwsuaf.util.DatetimeUtil;
import club.nwsuaf.util.FileUtil;
import club.nwsuaf.util.PathUtil;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @名称 逻辑服务实现类 - 文件
 * @作者 赵栩旸
 */
@Service
public class FileServiceImpl implements FileService {

    @Override
    public List<FileVO> listFiles() {
        List<FileVO> result = new LinkedList<>();

        String uploadFolderPath = getUploadFolder();
        File uploadFolder = new File(uploadFolderPath);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }
        File[] files = uploadFolder.listFiles();
        for (File file : files) {
            result.add(formatFileVO(file));
        }
        return result;

    }

    @Override
    public String getUploadFolder() {
        PathUtil pathUtil = new PathUtil();
        String webRoot = pathUtil.getWebRootPath();
        String folderName = PathUtil.getValueByKey("file.upload.folder");
        System.err.println(webRoot);
        System.err.println(String.format("%sWEB-INF/%s", webRoot, folderName));
        return String.format("%sWEB-INF/%s", webRoot, folderName);
    }

    /**
     * @描述 将普通File转换成自定义格式的File数据类型，是为了方便存储到List中
     */
    @Override
    public FileVO formatFileVO(File file) {
        FileVO fileVO = new FileVO();
        String name = file.getName();//名称直接取
        String lastTime = DatetimeUtil.formatDateTime(file.lastModified());//格式化最后修改时间
        String type = FileUtil.formatType(file);
        String size = FileUtil.formatFileSize(FileUtil.getFileLength(file.getAbsolutePath()));
        String parent = file.getParent();
        fileVO.setName(name);
        fileVO.setLastTime(lastTime);
        fileVO.setSize(size);
        fileVO.setType(type);
        fileVO.setParent(parent);
        return fileVO;
    }

    @Override
    public boolean deleteFile(String fileName) {
        String folder = getUploadFolder();
        File deleteFile = new File(folder, fileName);
        return deleteFile.delete();
    }

    @Override
    public boolean rename(String name, String newName) {
        String path = getUploadFolder();
        String oldPath = FileUtil.pathCheck(path, name);
        File oldFile = new File(oldPath);
        String newPath = oldFile.getParent() + File.separator + newName;
        System.err.println("newPath:" + newPath);
        File newFile = new File(newPath);
        return oldFile.renameTo(newFile);
    }

}

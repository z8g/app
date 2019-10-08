
package pan.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pan.model.FileVO;
import pan.util.DateUtil;
import pan.util.FileUtil;
import pan.util.PathUtil;

/**
 * @名称 文件操作服务类，为控制类提供文件操作
 * @作者 zhaoxuyang
 * @版本 v1.0
 * @日期 2019-01-01
 */
public class FileService {

    private static final PathUtil pathUtil = new PathUtil();

    public static void main(String[] args) {

//获取指定目录下的所有信息
//        List<FileVO> fileVOList = getFileVOList("D:/soft",true);
//        fileVOList.stream().forEach((fileVO) -> {
//            System.out.println(fileVO);
//        });
        
        
//获取用户主目录       
//        String userRoot = getUserRoot();
//        System.out.println(userRoot);
//        //F:/code/20181222/pan/build/web/WEB-INF/user
//        
    
        
//        //创建文件夹的测试
//        String []paths = {
//            "E:/path1","E:/path2/"
//        };
//        String []folders = {
//            "folder1","/folder2","/folder3/","folder4/"
//        };
//        for(String path:paths){
//            for(String folder:folders){
//                System.out.println(newFolder(path,folder));
//            }
//        }
/*全部创建成功
配置文件加载成功
E:/path1/folder1
true
E:/path1//folder2
true
E:/path1//folder3/
true
E:/path1/folder4/
true
E:/path2/folder1
true
E:/path2//folder2
true
E:/path2//folder3/
true
E:/path2/folder4/
true
        */
        
         //创建文件的测试，
//        String []paths = {
//            "E:/"
//        };
//        String []files = {
//            "file1","/file2","/file3/","file4/"
//        };
//        for(String path:paths){
//            for(String file:files){
//                System.out.println(newFile(path,file));
//            }
//        }
//配置文件加载成功
//E:/file1
//true
//E://file2
//true
//E://file3/
//true
//E:/file4/
//true   
        
        
//用户注册测试，根据ID在用户主目录下创建一个文件夹
//userRegister(1);

//返回上级目录测试
//        System.out.println(upper(null,"2"));
//        newFolder("F:/code/20181222/pan/build/web/WEB-INF/user/2/12/1221","assa");
//        System.out.println(upper("F:/code/20181222/pan/build/web/WEB-INF/user/2/12/1221","2"));

    }

    /**
     * @描述 用户单击文件夹或者文件时要么进入文件夹，要么执行文件下载
     * @param path
     * @param childName
     * @param request
     * @param response
     * @return path
     */
    public static String child(final String path, final String childName, HttpServletRequest request, HttpServletResponse response) {
        if (path == null || childName == null) {
            return path;
        }
        String str = FileUtil.pathCheck(path, childName);
        File childFile = new File(str);
        if (childFile.isDirectory()) {
            return childFile.getAbsolutePath();
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("filepath", childFile.getAbsolutePath());
            try {
                request.setAttribute("callback", "visit.html?tid="+session.getAttribute("tid"));
                request.getRequestDispatcher("WEB-INF/jsp/download.jsp").forward(request, response);
            } catch (IOException | ServletException ex) {
                
            }
        }
        return path;
    }
    /**
     * 取相对路径
     * @param pathUrl
     * @return 
     */
    public static String getPathUri(String pathUrl) {
        File pathFile = new File(pathUrl);
        File userRootFolder = new File(getUserRoot());
        String strShort = userRootFolder.getAbsolutePath();
        String strLong = pathFile.getAbsolutePath();
        return strLong.substring(strShort.length());
    }
    /**
     * 相对路径转绝对路径
     * @param pathUrl
     * @return 
     */
    public static String getAbsolutePath(String pathUrl) {
        File pathFile = new File(getUserRoot()+pathUrl);
        return pathFile.getAbsolutePath();
    }
     
    /**
     * @描述 检测当前目录是否属于当前用户
     * @param tid
     * @param userId
     * @return
     */
    public static boolean userSelfCheck(final String tid, final String userId) {
        if (tid == null || userId == null || "".equals(userId) || "".equals(tid)) {
            return false;//别想通过穿空参数来偷看用户的私密文件夹
        }
        return tid.equals(userId);
    }
    
    /**
     * @param userId
     * @描述 用户注册激活后再文件系统的操作
     */
    public static void userRegister(Integer userId) {
        final String userRoot = getUserRoot();
        newFolder(userRoot,userId + "");
    }
    
   /**
     * @param path 传入的当前目录
     * @param tid 所处目录的用户的id
     * @return 被改变的当前目录
     * @描述 处理上级目录
     */
    public static String upper(final String path, final String tid) {
        String currPath = getUserRoot() + "/" + tid;
        File currFile = new File(currPath);
        if (path == null) {//如果path不存在，则新定义path为根路径
            return currPath;
        }
        File pathFile = new File(path);
        if (!pathFile.exists()) {//如果传入参数所对应的文件不存在
            return currPath;
        }
        String strLong = pathFile.getAbsolutePath();
        String strShort = currFile.getAbsolutePath();
        if (strLong.contains(strShort)) {//如果包含（大于等于）
            if (!pathFile.equals(currFile)) {//没有达到根目录currPath
                return pathFile.getParent();
            }//否则是恰好达到根目录
        }//否则是错误类型，可能是用户自己POST的数据
        return strShort;//直接返回用户根目录currPath

    }
    /**
     * 检查参数列表是否为空，如果有一个为空返回true
     * 直到所有参数都不为空时，返回false
     * @param args
     * @return 
     */
    private static boolean checkNull(Object...args){
        for(Object arg:args){
            if(arg == null){
                return true;
            }
        }
        return false;
    }
    
    /**
     * 新建文件,需要该文件所在文件夹已经存在
     *
     * @param path
     * @param fileName
     * @return
     */
    public static boolean newFile(final String path, final String fileName) {
        if(checkNull(path,fileName)){
            return false;
        }
        try {
            String filePath = FileUtil.pathCheck(path, fileName);
            System.out.println(filePath);
            return new File(filePath).createNewFile();
        } catch (IOException ex) {
            return false;
        }
    }
    
    /**
     * 新建文件夹
     * @param path
     * @param folderName
     * @return 
     */
    public static boolean newFolder(final String path, final String folderName) {
        if(checkNull(path,folderName)){
            return false;
        }
        String folderPath = FileUtil.pathCheck(path, folderName);
        System.out.println(folderPath);
        return FileUtil.mkdirs(folderPath);
    }
    
    
    
    /**
     * 文件重命名
     * @param path 原文件夹路径
     * @param oldName 原文件名
     * @param newName 原路径下的新名称
     * @return
     * @描述 考虑到安全性，根据原路径和新名称进行重命名，int flag表示区别于上一种方法的标志，也就是说，用户只能在当前文件夹下重命名
     */
    public static boolean rename(final String path, final String oldName, final String newName) {
       if(checkNull(path,oldName,newName)){
            return false;
        }
        String oldPath = FileUtil.pathCheck(path, oldName);
        File oldFile = new File(oldPath);
        String newPath = oldFile.getParent() + File.separator + newName;
        File newFile = new File(newPath);
        return oldFile.renameTo(newFile);
    }

    /**
     * 移动文件或文件夹
     * @param oldPath 原路径
     * @param newPath 新路径
     * @param id 当前登录的用户id
     * @return
     * @描述 移动文件如果新文件路径不在用户空间中，则拒绝移动
     */
    public static boolean move(final String oldPath, final String newPath, final String id) {
        if(checkNull(oldPath,newPath,id)){
            return false;
        }
        File oldFile = new File(oldPath);
        File newFile = new File(newPath);
        final String userRoot = getUserRoot();//所有用户主目录
        String path = userRoot + File.separator + id;//当前用户的主目录
        File pathFile = new File(path);//以当前用户主目录创建File对象

        String strLong = newFile.getAbsolutePath();
        String strShort = pathFile.getAbsolutePath();
        if (strLong.contains(strShort)) {//如果包含，则移动（也就是说用户只能在自己目录下移动文件）
            return oldFile.renameTo(newFile);
        }
        return false;
    }

    /**
     * @param folderPath 文件路径
     * @param isUserSelf
     * @return 文件列表信息
     * @描述 根据文件路径返回文件列表信息
     */
    public static List<FileVO> getFileVOList(String folderPath, boolean isUserSelf) {
        File folder = new File(folderPath);
        if (!folder.exists()) {//如果文件夹不存在则返回null
            return null;
        }
        File[] files = folder.listFiles();//根据文件夹路径获取的文件列表
        List<FileVO> result = new ArrayList<>();//最后要返回的信息

        //先添加目录的信息
        for (File file : files) {
            if (file.isDirectory()) {
                if (privateCheck(file.getName(), isUserSelf)) {
                    result.add(formatFileVO(file));
                }
            }
        }

        //再添加文件的信息
        for (File file : files) {
            if (file.isFile()) {
                result.add(formatFileVO(file));
            }
        }
        return result;
    }

    /**
     * 私有文件夹拦截 如果不是私有文件夹或者是用户自己，则返回true，否则返回false
     *
     * @return
     */
    private static boolean privateCheck(final String folderName, boolean isUserSelf) {
        return !folderName.endsWith(privateFlag) || isUserSelf;
    }
    private static final String privateFlag = PathUtil.getValueByKey("file.private.flag");//默认为_pass

    /**
     * @描述 将普通File转换成自定义格式的File数据类型，是为了方便存储到List中
     */
    private static FileVO formatFileVO(File file) {
        FileVO fileVO = new FileVO();
        String name = file.getName();//名称直接取
        String lastTime = DateUtil.sdf.format(new Date(file.lastModified()));//格式化最后修改时间
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

    /**
     * 根据文件名删除文件或者递归删除目录
     *
     * @param path
     * @param fileName
     * @return
     */
    public static boolean deleteFile(final String path, String fileName) {
        if (path == null || fileName == null) {
            return false;
        }
        String str = FileUtil.pathCheck(path, fileName);
        FileUtil.deleteFile(str);
        return true;
    }
    
    /**
     * 获取用户主目录的绝对路径
     */
    private static String getUserRoot(){
        String isInProject= PathUtil.getValueByKey("file.user.root.isInProject");
        String directory = PathUtil.getValueByKey("file.user.root.directory");
        
        boolean bool = Boolean.valueOf(isInProject);
        if(bool){
            if(directory == null){
                return "user";
            } else{
                String webRoot = new PathUtil().getWebRootPath();
                return webRoot + "WEB-INF/" + directory;
            }
        } 
        return directory;
    }
}

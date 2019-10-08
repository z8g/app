package com.duqiaoli.apple.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AppleController {

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

//    @RequestMapping(value = "/apple")
//    @ResponseBody
//    public String print(@RequestParam String id){
//        return "Hello" + id;
//    }
//    @RequestMapping(value = "/apple", method = RequestMethod.GET)
//    @ResponseBody
//    public String print(Integer id) {
//        switch (id) {
//            case -2:
//                return "系统异常";
//            case -1:
//                return "未知类型";
//            case 0:
//                return "斑点落叶病";
//            case 1:
//                return "褐斑病";
//            case 2:
//                return "花叶病";
//            case 3:
//                return "灰斑病";
//            case 4:
//                return "锈病";
//            default:
//                return "未知类型";
//        }
//    }
//    @RequestMapping(value = "/apple/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public String print2(@PathVariable Integer id) {
//        switch (id) {
//            case 0:
//                return "斑点落叶病";
//            case 1:
//                return "褐斑病";
//            case 2:
//                return "花叶病";
//            case 3:
//                return "灰斑病";
//            case 4:
//                return "锈病";
//            default:
//                return "未知类型";
//        }
//    }
    /**
     * 返回列表示例(自动注入Jackson)
     *
     * @param id
     * @return JSON形式的数组
     */
//    @RequestMapping(value = "/apple/list")
//    @ResponseBody
//    public List<String> printList(String id) {
//        List<String> result = new ArrayList<>();
//        for (int i = 0; i < 110; i++) {
//            String data = i + "#" + (i % 3);
//            result.add(data);
//        }
//        return result;
//    }
    /**
     * 1. 设置文件名 2. 保存文件 3. 调用Python计算 4. 获取结果
     *
     * @param file
     * @param session
     * @return
     */
    @RequestMapping(value = "/upload")
    @ResponseBody
    public Map<String, String> upload(
            MultipartFile file,
            HttpSession session
    ) {
        Map<String, String> result = new HashMap<>();

        String filePath = saveFile(file);
        result.put("filePath", filePath);

        Integer culResult = run(filePath);
        String category = formatCategory(culResult);
        result.put("category", category);

        return result;
    }

    @RequestMapping(value = "/upload/wechat")
    @ResponseBody
    public String uploadWechat(
            MultipartFile file,
            HttpSession session
    ) {
        String filePath = saveFile(file);
        Integer culResult = run(filePath);
        String category = formatCategory(culResult);

        return category;
    }

    //@Value("python.exe") 
    private String exe = "python";
    //@Value("python.commnd")
    private String command = "D:/2019/20190508/apple/python/predict.py";

    public Integer run(String imgFilePath) {
        //String command = "D:/2019/20190508/apple/python/predict.py";

        String outputString = "-1";
        //String inputPath = String.format("%s/job/%s/%s", pythonRoot, jobId, "input.fasta");
        System.out.printf("[command]:%s\n", command);
        try {
            //String exe1 = "python";
            //python D:/2019/20190508/apple/python/predict.py D:/2019/20190508/apple/service/upload/xxx.png
            String[] cmdArr = new String[]{exe, command, imgFilePath};
            System.out.printf("> %s %s %s\n", exe, command, imgFilePath);
            Process process = Runtime.getRuntime().exec(cmdArr);//调用命令行执行，返回进程
            LineNumberReader br = new LineNumberReader(new InputStreamReader(process.getInputStream()));

            //获取进程的最后一行输出：[1]
            String line;
            while ((line = br.readLine()) != null) {
                outputString = line;
            }
        } catch (IOException ex) {
            System.out.println(ex);
            outputString = "-2";
        }
        Integer result = Integer.valueOf(outputString.replaceAll("\\[", "").replaceAll("]", ""));
        System.out.println(result);
        return result;
    }

    public String saveFile(MultipartFile uploadFile) {
        String result;
        try {
            String folderPath = "upload";

            // 创建upload文件夹
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            folder = null;

            // 设置保存的文件名，因为python读取的是二进制，所以扩展名无所谓
            String saveFileName = new Date().getTime() + ".png";

            // 保存文件
            File saveFile = new File(folderPath, saveFileName);
            uploadFile.transferTo(saveFile.toPath());
            result = saveFile.getAbsolutePath();
        } catch (IOException | IllegalStateException ex) {
            System.out.println(ex);
            result = ex.toString();
        }
        return result;
    }

    private String formatCategory(Integer id) {
        String undefined = "未知类型";
        if (id == null) {
            return undefined;
        }
        switch (id) {
            case -2:
                return "系统异常";
            case -1:
                return "未知类型";
            case 0:
                return "斑点落叶病";
            case 1:
                return "褐斑病";
            case 2:
                return "花叶病";
            case 3:
                return "灰斑病";
            case 4:
                return "锈病";
            default:
                return undefined;
        }
    }
}

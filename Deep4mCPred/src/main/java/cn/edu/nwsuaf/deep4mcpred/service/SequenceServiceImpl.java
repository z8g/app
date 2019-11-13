package cn.edu.nwsuaf.deep4mcpred.service;

import cn.edu.nwsuaf.deep4mcpred.model.InputData;
import cn.edu.nwsuaf.deep4mcpred.tools.CommandUtil;
import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * SequenceService实现类依赖注入
 *
 * @see com.zxy97.blog.controller.VisitorController
 * @date 2019-05-17
 * @author zhaoxuyang
 */
@Service
public class SequenceServiceImpl implements SequenceService {

    static final String FLAG_SUCCESS = "1";
    static final String FLAG_FAIL = "0";
    static final String INFO_SUCCESS = "YES";
    static final String INFO_FAIL = "NO";
    static final String INFO_NULL = "NULL";
    static Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Value("${python.exec}")
    private String pythonExec;

    @Value("${python.folder}")
    private String pythonFolderh;

    @Value("${python.folder.run}")
    private String pythonFolderRun;

    @Value("${job.input.filename}")
    private String jobInputFileName;

    @Value("${job.output.filename}")
    private String jobOutputFileName;

    @Override
    public String test() {
        return pythonExec + "," + pythonFolderh;
    }

    @Override
    public List<InputData> run(List<InputData> inputDataList, Integer category) {
        List<InputData> result = new LinkedList<>();

        // Lambda并行计算，需要Java 8以上版本
        inputDataList.forEach((input) -> {
            try {
                File file = new File("aaa");
                String pythonFilePath = file.getAbsolutePath();
                String command = String.format("python %s %d %s",
                        pythonFilePath, category, input.getSequence());
                int runResult = CommandUtil.nonBlockingRun(command);//运行结果，0表示失败，1表示成功

                String output = INFO_NULL;//解析输出结果
                if (runResult == 0) {
                    output = INFO_SUCCESS;
                } else {
                    output = INFO_FAIL;
                }
                InputData insert = input.clone();//原型模式，避免大量数据下内存溢出
                insert.setResult(output);

                result.add(insert);//添加到返回结果中

            } catch (IOException | CloneNotSupportedException ex) {
                System.out.println(ex);
            }
        });

        return result;
    }

    static final String[] examples = {
        ">P_1\n"
        + "GAAGAAGTAGGCCATTATCTCGAATGAGCCAAACTAGTATTGAAGAAGTAGGCCATTATCTCGAATGAGCCAAACTAGTATT",
        ">P_2\n"
        + "GAAGAAGTAGGCCATTATCTCGAATGAGCCAAACTAGTATTGAAGAAGTAGGCCATTATCTCGAATGAGCCAAACTAGTATT",
        ">P_3\n"
        + "GAAGAAGTAGGCCATTATCTCGAATGAGCCAAACTAGTATTGAAGAAGTAGGCCATTATCTCGAATGAGCCAAACTAGTATT",
        ">P_4\n"
        + "GAAGAAGTAGGCCATTATCTCGAATGAGCCAAACTAGTATTGAAGAAGTAGGCCATTATCTCGAATGAGCCAAACTAGTATT",
        ">P_5\n"
        + "GAAGAAGTAGGCCATTATCTCGAATGAGCCAAACTAGTATTGAAGAAGTAGGCCATTATCTCGAATGAGCCAAACTAGTATT",
        ">P_6\n"
        + "GAAGAAGTAGGCCATTATCTCGAATGAGCCAAACTAGTATTGAAGAAGTAGGCCATTATCTCGAATGAGCCAAACTAGTATT"
    };

    @Override
    public String getExample(Integer id) {
        int index = (id == null) ? 0 : id;
        return examples[index];
    }

    @Override
    public List<InputData> getInputDataListFromString(String sequenceList) {
        //预处理，去掉空行
        List<String> lineList = new LinkedList<>();
        Scanner preScanner = new Scanner(sequenceList);
        while (preScanner.hasNext()) {
            String line = preScanner.nextLine();
            if (notEmpty(line)) {
                lineList.add(line);
            }
        }
        return getInputDataFromLineList(lineList);
    }

    @Override
    public List<InputData> getInputDataListFromFile(String filePath) {
        try {
            List<String> lineList = new LinkedList<>();

            //从文件中读取所有行
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);

            //预处理，去掉空行
            lines.stream().filter((line) -> (notEmpty(line))).forEachOrdered((line) -> {
                lineList.add(line);
            });

            return getInputDataFromLineList(lineList);
        } catch (IOException ex) {
            System.out.println(ex);
            return null;
        }
    }

    /**
     * 从字符串列表中每次读取两行数据，存到InputData中，再将InputData添加到list里
     *
     * @param lineList
     * @return
     */
    private List<InputData> getInputDataFromLineList(List<String> lineList) {
        List<InputData> result = new LinkedList<>();
        for (int i = 0; i < lineList.size();) {
            String title = lineList.get(i++).replaceAll(">", "");
            String sequence = lineList.get(i++);
            InputData inputData = new InputData();
            inputData.setTitle(title);
            inputData.setSequence(sequence);
            result.add(inputData);
        }
        return result;
    }

    /**
     * 判断一个一行字符串是否不为空
     *
     * @param line
     * @return
     */
    private boolean notEmpty(String line) {
        return line != null && line.trim().length() > 0;
    }

}

package cn.edu.nwsuaf.deep4mcpred.service;

import cn.edu.nwsuaf.deep4mcpred.model.InputData;
import java.util.List;

/**
 * 序列-服务接口
 *
 * @author zhaoxuyang
 */
public interface SequenceService {

    public String test();

    /**
     * 计算
     *
     * @param inputDataList 无output域的输入数据列表
     * @param category run的类别
     * @return 含有output域的InputData列表
     */
    public List<InputData> run(List<InputData> inputDataList, Integer category);

    /**
     * 获取样例列表
     *
     * @param id 类别：0,1,2,3,4,5
     * @return
     */
    public String getExample(Integer id);

    /**
     * 格式化表单提交的sequence列表 每条sequence类似这样：
     * <pre>
     * >P_1
     * GAAGAAGTAGGCCATTATCTCGAATGAGCCAAACTAGTATTGAAGAAGTAGGCCATTATCTCGAATGAGCCAAACTAGTATT
     * </pre> 将其格式化后存储到InputData列表中
     *
     * @param sequenceList 表单提交的sequence列表
     * @return InputData列表
     */
    public List<InputData> getInputDataListFromString(String sequenceList);

    /**
     * 根据文件路径获取
     *
     * @param filePath 文件的路径
     * @return InputData列表
     */
    public List<InputData> getInputDataListFromFile(String filePath);

}

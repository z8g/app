package edu.monash.erc.pippin.service;

public interface PythonService {
    
    /**
     * 根据JobId,取出其中的fasta文件，传给Python计算，返回结果
     * @param jobId
     * @return 计算是否成功
     */
    public boolean run(String jobId);
}

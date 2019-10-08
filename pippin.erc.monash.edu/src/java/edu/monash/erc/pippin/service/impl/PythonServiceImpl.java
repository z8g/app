package edu.monash.erc.pippin.service.impl;

import edu.monash.erc.pippin.service.FileService;
import edu.monash.erc.pippin.service.PythonService;
import edu.monash.erc.pippin.util.PathUtil;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PythonServiceImpl implements PythonService {

    @Autowired
    FileService fileService;

    @Override
    public boolean run(String jobId) {
        String pythonRoot = String.format("%sWEB-INF/python", new PathUtil().getWebRootPath());
        String result = "false";
        //String inputPath = String.format("%s/job/%s/%s", pythonRoot, jobId, "input.fasta");
        String command = String.format("%s/%s", pythonRoot, "test_XGBoost.py");

        System.out.printf("[command]:%s\n", command);
        //System.out.printf("[input.fasta]:%s\n", inputPath);
        try {
            String exe1 = "python";
            String[] cmdArr = new String[]{exe1, command, pythonRoot,jobId};
            Process process = Runtime.getRuntime().exec(cmdArr);
            LineNumberReader br = new LineNumberReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                result = line;
            }
        } catch (IOException ex) {
            Logger.getLogger(PythonServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(result);
        return Boolean.valueOf(result);
    }
}

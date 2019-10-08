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
        
        String pythonExec = PathUtil.getValueByKey("python.exec");
        String pythonFile = PathUtil.getValueByKey("python.file");
        String pythonRoot = PathUtil.getValueByKey("python.root");
        
        String result = "false";
        try {
            String[] cmdArr = new String[]{pythonExec, pythonFile, pythonRoot, jobId};
            System.out.printf("%s %s %s %s\n", pythonExec, pythonFile, pythonRoot, jobId);
            Process process = Runtime.getRuntime().exec(cmdArr);
            LineNumberReader br = new LineNumberReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                result = line;
                System.out.println(result);
            }
        } catch (IOException ex) {
            Logger.getLogger(PythonServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(result);
        return Boolean.valueOf(result);
    }
}

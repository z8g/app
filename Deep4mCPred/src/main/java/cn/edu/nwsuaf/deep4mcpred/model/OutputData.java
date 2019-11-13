package cn.edu.nwsuaf.deep4mcpred.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import java.math.BigDecimal;
import java.util.Scanner;

/**
 *
 * @author zhaoxuyang
 */
public class OutputData implements Comparable<OutputData> {

    @Excel(name = "title", orderNum = "0")
    String title;
    
    @Excel(name = "position", orderNum = "0")
    String position;
    
    @Excel(name = "site", orderNum = "0")
    String site;
    
    @Excel(name = "probability", orderNum = "0")
    BigDecimal probability;

    @Excel(name = "protease", orderNum = "0")
    String protease;

    public OutputData() {

    }

    public OutputData(String line) {
        System.out.println(line);
        try (Scanner input = new Scanner(line)) {
            this.title = input.next();
            this.position = input.next();
            this.site = input.next();
            this.probability = new BigDecimal(input.next());
            this.protease = input.next();
            //System.out.println(this);
        }
    }

    @Override
    public String toString() {
        return "OutputData{" + "title=" + title + ", position=" + position + ", site=" + site + ", probability=" + probability + ", protease=" + protease + '}';
    }

    @Override
    public int compareTo(OutputData output) {
        //return -1; //-1表示放在红黑树的左边,即逆序输出  
        //return 1;  //1表示放在红黑树的右边，即顺序输出  
        //return o;  //表示元素相同，仅存放第一个元素
        return output.probability.compareTo(this.probability);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public BigDecimal getProbability() {
        return probability;
    }

    public void setProbability(BigDecimal probability) {
        this.probability = probability;
    }

    public String getProtease() {
        return protease;
    }

    public void setProtease(String protease) {
        this.protease = protease;
    }

}

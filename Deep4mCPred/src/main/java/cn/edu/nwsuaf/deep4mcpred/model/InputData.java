package cn.edu.nwsuaf.deep4mcpred.model;

/**
 * POJO类，用于传输数据
 * @author zhaoxuyang
 */
public class InputData implements Cloneable {

    private String title;
    private String sequence;
    private String result;

    public InputData(String title, String sequence, String output) {
        this.title = title;
        this.sequence = sequence;
        this.result = output;
    }

    public InputData(String title, String sequence) {
        this.title = title;
        this.sequence = sequence;
    }

    public InputData() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "InputData{" + "title=" + title + ", sequence=" + sequence + ", output=" + result + '}';
    }

    @Override
    public InputData clone() throws CloneNotSupportedException {
        return (InputData) super.clone();
    }

}

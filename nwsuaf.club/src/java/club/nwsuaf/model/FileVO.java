package club.nwsuaf.model;


import java.io.Serializable;
/**
 * @名称 显示在客户端的文件模型类
 * @作者 zhaoxuyang
 * @版本 v1.0
 * @日期 2019-01-01
 */
public class FileVO implements Serializable {

    private String type;        //目录/普通文件
    private String name;        //文件名
    private String size;        //文件大小
    private String lastTime;    //最后修改时间
    private String parent;      //所在的文件夹

    @Override
    public String toString() {
        return "FileVO{" + "type=" + type + ", name=" + name + ", size=" + size + ", lastTime=" + lastTime + ", parent=" + parent + '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}

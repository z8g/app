package jalaxy.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 读取XML数据存放到该类中
 *
 * @author hadoop
 */
public class ToolBox {

    private List<Section> sectionList = new LinkedList<>();  

    public List<Section> getSectionList() {
        return sectionList;
    }

    public void setSectionList(List<Section> sectionList) {
        this.sectionList = sectionList;
    }

    public static class Section {

        private List<Tool> toolList;
        private String id;
        private String title;
        
        private Set<String> files;

        public Set<String> getFiles() {
            return files;
        }

        public void setFile(Set<String> files) {
            this.files = files;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<Tool> getToolList() {
            return toolList;
        }

        public void setToolList(List<Tool> toolList) {
            this.toolList = toolList;
        }

        @Override
        public String toString() {
            return "Section{" + "toolList=" + toolList + ", id=" + id + ", title=" + title + ", files=" + files + '}';
        }
        
        

    }

    @Override
    public String toString() {
        return "ToolBox{" + "sectionList=" + sectionList + '}';
    }
    
    
}

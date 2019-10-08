package jalaxy.model;

import java.util.List;

/**
 *
 * @author zhaoxuyang
 */
public class ToolConfig {

    /**
     * 单例模式
     */
    private static class SingletonHolder {

        private static final ToolConfig INSTANCE = new ToolConfig();
    }

    private ToolConfig() {
    }

    public static final ToolConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private ToolBox toolBox;
    
    
    
    private List<Tool> toolList;

    public ToolBox getToolBox() {
        return toolBox;
    }

    public void setToolBox(ToolBox toolBox) {
        this.toolBox = toolBox;
    }

    public List<Tool> getToolList() {
        return toolList;
    }

    public void setToolList(List<Tool> toolList) {
        this.toolList = toolList;
    }

}

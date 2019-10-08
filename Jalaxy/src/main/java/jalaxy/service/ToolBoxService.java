package jalaxy.service;

import jalaxy.model.Tool;
import jalaxy.model.Tool.Input;
import jalaxy.model.Tool.Output;
import jalaxy.model.ToolBox;
import jalaxy.model.ToolBox.Section;
import static jalaxy.util.FileUtil.CONFIG_FOLDER;
import jalaxy.util.LoggerUtil;
import static jalaxy.util.XPathUtil.evaluate;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author zhaoxuyang
 */
public class ToolBoxService {

    public static void main(String[] args) {
        String toolBoxPath = String.format("%s/toolbox.xml", CONFIG_FOLDER);

        createTool("test/tool1.xml");

        ToolBox toolBox = createToolBox(toolBoxPath);
        loadTools(toolBox);

        System.out.println(toolBox);

    }
    static final Logger LOG = LoggerUtil.getDefaultLogger();

    /**
     * 根据toolbox.xml中设置的tool路径，创建各个Tool对象，保存到ToolBox中
     *
     * @param toolBox
     */
    public static void loadTools(ToolBox toolBox) {
        List<Section> sectionList = toolBox.getSectionList();
        sectionList.forEach((section) -> {
            Set<String> toolUriSet = section.getFiles();

            List<Tool> toolList = new LinkedList<>();
            toolUriSet.stream()
                    .map((toolUri) -> createTool(toolUri))
                    .forEachOrdered((tool) -> {
                        toolList.add(tool);
                    });
            section.setToolList(toolList);
        });

        toolBox.setSectionList(sectionList);
    }

    /**
     * 根据toolBox的文件路径，创建ToolBox对象
     *
     * @param toolBoxPath
     * @return
     */
    public static ToolBox createToolBox(String toolBoxPath) {
        ToolBox toolBox = new ToolBox();

        // sectionList
        List<Section> sectionList = new LinkedList<>();
        String expression = "/toolbox/section";
        NodeList sections = (NodeList) evaluate(toolBoxPath, expression);
        for (int i = 0; i < sections.getLength(); i++) {
            ToolBox.Section section = new ToolBox.Section();

            Node sectionNode = sections.item(i);

            NamedNodeMap attributes = sectionNode.getAttributes();
            section.setId(attributes.getNamedItem("id").getNodeValue());
            section.setTitle(attributes.getNamedItem("title").getNodeValue());

            LOG.info(sectionNode.getNodeName());

            NodeList fileNodeList = sectionNode.getChildNodes();
            Set<String> files = new TreeSet<>();

            //会出现#text,tool,#text,tool,#text，只选择tool标签
            for (int j = 0; j < fileNodeList.getLength(); j++) {
                Node node = fileNodeList.item(j);
                if (node.getNodeName().equals("tool")) {
                    NamedNodeMap map = node.getAttributes();
                    files.add(map.getNamedItem("file").getNodeValue());
                }
            }
            section.setFile(files);
            sectionList.add(section);
        }

        toolBox.setSectionList(sectionList);

        LOG.info(toolBox.toString());
        return toolBox;
    }

    /**
     * 根据tool的文件路径，创建Tool对象
     *
     * @param toolUri tools目录下的相对路径
     * @return
     */
    public static Tool createTool(String toolUri) {
        String toolPath = String.format("tools/%s", toolUri);

        Tool tool = new Tool();

        // command
        String expression = "//command";
        NodeList nodeList = (NodeList) evaluate(toolPath, expression);
        tool.setCommand(nodeList.item(0).getTextContent());

        // inputs
        expression = "//input";
        List<Input> inputs = new LinkedList<>();
        nodeList = (NodeList) evaluate(toolPath, expression);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Tool.Input toolInput = new Tool.Input();
            NamedNodeMap attributes = nodeList.item(i).getAttributes();
            toolInput.setName(attributes.getNamedItem("name").getNodeValue());
            toolInput.setValue(attributes.getNamedItem("value").getNodeValue());
            toolInput.setType(attributes.getNamedItem("type").getNodeValue());
            inputs.add(toolInput);
        }
        tool.setInputs(inputs);

        //outputs
        expression = "//output";
        List<Output> outputs = new LinkedList<>();
        nodeList = (NodeList) evaluate(toolPath, expression);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            NamedNodeMap attributes = node.getAttributes();
            Tool.Output toolOutput = new Tool.Output();
            toolOutput.setName(attributes.getNamedItem("name").getNodeValue());
            toolOutput.setType(attributes.getNamedItem("type").getNodeValue());
            outputs.add(toolOutput);
        }
        tool.setOutputs(outputs);

        // help
        expression = "//help";
        nodeList = (NodeList) evaluate(toolPath, expression);
        tool.setHelp(nodeList.item(0).getTextContent());

        LOG.info(tool.toString());
        return tool;
    }

}

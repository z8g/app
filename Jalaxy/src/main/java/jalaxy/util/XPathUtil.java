package jalaxy.util;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XPathUtil {

    static final Logger LOG = LoggerUtil.getDefaultLogger();

    static DocumentBuilderFactory docBuilderFactory;
    static DocumentBuilder docBuilder;
    static XPathFactory xPathFactory;
    static XPath xPath;
    static XPathExpression xPathExpression;

    /**
     * 获取 DocumentBuilderFactory
     *
     * @return
     */
    private static DocumentBuilderFactory getDocumentBuilderFactory() {
        if (docBuilderFactory == null) {
            LOG.info("创建DocumentBuilderFactory");
            docBuilderFactory = DocumentBuilderFactory.newInstance();
            docBuilderFactory.setValidating(false);
            docBuilderFactory.setNamespaceAware(false);
            docBuilderFactory.setIgnoringComments(false);
            docBuilderFactory.setIgnoringElementContentWhitespace(false);
            docBuilderFactory.setCoalescing(false);
            docBuilderFactory.setExpandEntityReferences(true);
        }
        LOG.info("获取DocumentBuilderFactory");
        return docBuilderFactory;
    }

    /**
     * 获取 DocumentBuilder
     *
     * @return
     */
    private static DocumentBuilder getDocumentBuilder() {
        if (docBuilder == null) {
            try {
                LOG.info("创建DocumentBuilder");
                docBuilder = getDocumentBuilderFactory().newDocumentBuilder();
                docBuilder.setErrorHandler(new ErrorHandler() {
                    @Override
                    public void warning(SAXParseException exception) 
                            throws SAXException {
                        System.out.println("warning:" + exception);
                    }

                    @Override
                    public void error(SAXParseException exception) 
                            throws SAXException {
                        System.out.println("error:" + exception);
                    }

                    @Override
                    public void fatalError(SAXParseException exception)
                            throws SAXException {
                        System.out.println("fatalError:" + exception);
                    }
                });
            } catch (ParserConfigurationException ex) {
                //设置DocumentBuilder的异常处理
                LOG.severe(ex.toString());
            }
        }
        LOG.info("获取DocumentBuilder");
        return docBuilder;
    }

    /**
     * 根据 XML 文件路径获取Document对象
     *
     * @param xmlPath
     * @return
     */
    public static Document getDocument(String xmlPath) {
        Document document = null;
        try {
            document = getDocumentBuilder().parse(
                    MethodHandles.lookup().lookupClass().getClassLoader()
                            .getResourceAsStream(xmlPath));//创建Document对象
        } catch (SAXException | IOException ex) {
            LOG.log(Level.SEVERE, "创建Document时发生异常{0}", ex.toString());
        }
        LOG.info("获取Document");
        return document;

    }

    private static XPathFactory getXPathFactory() {
        if (xPathFactory == null) {
            LOG.info("创建xPathFactory");
            xPathFactory = XPathFactory.newInstance();
        }
        LOG.info("获取xPathFactory");
        return xPathFactory;
    }

    public static XPath getXPath() {
        if (xPath == null) {
            LOG.info("创建XPath");
            xPath = getXPathFactory().newXPath();
        }
        LOG.info("获取XPath");
        return xPath;
    }

    public static XPathExpression getXPathExpression(String expression) {
        try {
            LOG.info("创建XPathExpression");
            xPathExpression = getXPath().compile(expression);
        } catch (XPathExpressionException ex) {
            LOG.log(Level.SEVERE, "创建XPathExpression时发生异常{0}", ex.toString());
        }
        LOG.info("获取XPathExpression");
        return xPathExpression;
    }

    public static Object evaluate(String xmlPath, String expression) {
        Object result = null;
        try {
            Document document = XPathUtil.getDocument(xmlPath);
            XPathExpression xpe = getXPathExpression(expression);
            result = xpe.evaluate(document, XPathConstants.NODESET);
        } catch (XPathExpressionException ex) {
            LOG.severe("解析XML出现异常");
        }
        return result;
    }

    public static void main(String[] args) throws XPathExpressionException {
        
        
        Object evaluatingResult = evaluate("tools/tool1.xml", "//inputs/input");

        NodeList nodeList = (NodeList) evaluatingResult;
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            String nodeName = node.getNodeName();
            LOG.info(nodeName);
            NamedNodeMap nameNodeMap = node.getAttributes();

            String name = nameNodeMap.getNamedItem("name").getNodeValue();
            String type = nameNodeMap.getNamedItem("type").getNodeValue();

            LOG.log(Level.INFO, "{0},{1}",new Object[]{name, type});
        }
    }
}

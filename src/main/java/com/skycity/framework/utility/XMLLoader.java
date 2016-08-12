package com.skycity.framework.utility;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.skycity.framework.collection.Mapx;
import com.skycity.framework.collection.Treex;
import com.skycity.framework.collection.Treex.TreeNode;

/**
 * 
 * @ClassName: XMLLoader
 * @Description: 加载XML文件或者文件夹下的所有XML
 * @author dyb
 * @date 2014-4-15 下午3:51:07
 * 
 */
public class XMLLoader {
    private Logger logger = Logger.getLogger(getClass());
	public static void main(String args[]) {
		XMLLoader loader = new XMLLoader();
		loader.load();
		NodeData[] datas = loader.getNodeDataList("Config.database.config");
		for(NodeData data :datas){
			System.out.println(data.getAttributes().get("name"));
		}
	}
	
	public void load() {
		try {
			String path =  URLUtil.getClassesPath()+"config/";
			load(path);
		} catch (RuntimeException e) {
			System.err.println(e.getMessage());
		}
	}
	
    private Treex<NodeData> tree = new Treex<NodeData>();
    
    public void load(String path) {
        File f = new File(path);
        if(f.exists()){
        	load(f);
        }else{
        	logger.error("文件不存在");
        }
    }
    
    //加载xml文件或者目录下的所有xml文件
    public void load(File f) {
        if (f.isFile() && (f.getName().toLowerCase().endsWith(".xml"))) {
            loadOneFile(f);
        } else {
            File[] fs = f.listFiles();
            for (int i = 0; i < fs.length; i++) {
                f = fs[i];
                if ((f.isFile()) && (f.getName().toLowerCase().endsWith(".xml"))) {
                    loadOneFile(f);
                }
            }
        }
    }
    
    public void clear() {
        this.tree = new Treex<NodeData>();
    }
    
    private void loadOneFile(File file) {
        SAXReader reader = new SAXReader(false);
        try {
            reader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            reader.setEntityResolver(new EntityResolver() {
                ByteArrayInputStream bs = new ByteArrayInputStream("".getBytes());
                public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
                    return new InputSource(this.bs);
                }
            });
            Document doc = reader.read(file);
            Element root = doc.getRootElement();
            convertElement(root, this.tree.getRoot());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 递归所有的xml节点
     * @param ele
     * @param parent
     */
    private void convertElement(Element ele, TreeNode<NodeData> parent) {
        String name = ele.getName().toLowerCase();
        NodeData data = new NodeData();
        data.TagName = name;
        data.Body = ele.getTextTrim();
        List<?> list = ele.attributes();
        Mapx<String, String> map = new Mapx<String, String>();
        for (int i = 0; i < list.size(); i++) {
            Attribute attr = (Attribute) list.get(i);
            map.put(attr.getName(), attr.getValue());
        }
        data.Attributes = map;
        TreeNode<NodeData> node = parent.addChild(data);
        data.treeNode = node;
        list = ele.elements();
        for (int i = 0; i < list.size(); i++) {
            Element child = (Element) list.get(i);
            convertElement(child, node);
        }
    }
    
    public NodeData[] getNodeDataList(String path) {
        String[] arr = path.split("\\.");
        Treex.TreeNode<NodeData> current = this.tree.getRoot();
        ArrayList<Treex.TreeNode<NodeData>> list = new ArrayList<TreeNode<NodeData>>();
        list.add(current);
        for (int i = 0; i < arr.length; i++) {
            list = getChildren(list, arr[i]);
            if (list == null) {
                return null;
            }
        }
        if (list.size() == 0) {
            return null;
        }
        NodeData[] datas = new NodeData[list.size()];
        for (int i = 0; i < list.size(); i++) {
            Treex.TreeNode<NodeData> node = (TreeNode<NodeData>) list.get(i);
            datas[i] = ((NodeData) node.getData());
        }
        return datas;
    }
    
    private static ArrayList<TreeNode<NodeData>> getChildren(ArrayList<TreeNode<NodeData>> parentList, String pathPart) {
        ArrayList<Treex.TreeNode<NodeData>> list = new ArrayList<TreeNode<NodeData>>();
        for (int i = 0; i < parentList.size(); i++) {
            TreeNode<NodeData> node = (TreeNode<NodeData>) parentList.get(i);
            Treex.TreeNodeList<NodeData> nodes = node.getChildren();
            for (int j = 0; j < nodes.size(); j++) {
                NodeData data = (NodeData) ((TreeNode<NodeData>) nodes.get(j)).getData();
                if ((pathPart.equals("*")) || (data.getTagName().equalsIgnoreCase(pathPart))) {
                    list.add((TreeNode<NodeData>) nodes.get(j));
                }
            }
        }
        return list;
    }
    
    public String getNodeBody(String path) {
        return getNodeBody(path, null, null);
    }
    
    public String getNodeBody(String path, String attrName, String attrValue) {
        NodeData nd = getNodeData(path, attrName, attrValue);
        if (nd == null) {
            return null;
        }
        return nd.Body;
    }
    
    public NodeData getNodeData(String path) {
        return getNodeData(path, null, null);
    }
    
    public NodeData getNodeData(String path, String attrName, String attrValue) {
        NodeData[] datas = getNodeDataList(path);
        if (datas.length>0) {
            if (attrName == null) {
                return datas[0];
            }
            for (int i = 0; i < datas.length; i++) {
                String v = (String) datas[i].Attributes.get(attrName);
                if (v == null) {
                    if (attrValue == null) {
                        return datas[i];
                    }
                } else if (v.equals(attrValue)) {
                    return datas[i];
                }
            }
        }
        return null;
    }
    
    public static class NodeData {
        
        private Mapx<String, String> Attributes = new Mapx<String, String>();
        
        private String TagName;
        
        private String Body;
        
        private Treex.TreeNode<NodeData> treeNode;
        
        public Mapx<String, String> getAttributes() {
            return this.Attributes;
        }
        
        public String getTagName() {
            return this.TagName;
        }
        
        public String getBody() {
            return this.Body;
        }
        
        public Treex.TreeNode<NodeData> getTreeNode() {
            return this.treeNode;
        }
    }
}

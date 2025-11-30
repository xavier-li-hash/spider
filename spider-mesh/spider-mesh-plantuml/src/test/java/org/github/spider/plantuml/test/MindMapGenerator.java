package org.github.spider.plantuml.test;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MindMapGenerator {

    // Method to generate the draw.io-compatible XML structure
    public static String generateDrawIoXml(TreeNode root) {
        StringBuilder xml = new StringBuilder();
        xml.append("<mxfile>\n<diagram name=\"MindMap\">\n<mxGraphModel dx=\"910\" dy=\"530\">\n<root>\n");
        xml.append("<mxCell id=\"0\"/>\n<mxCell id=\"1\" parent=\"0\"/>\n");

        // Map to store node IDs
        Map<TreeNode, String> nodeIdMap = new HashMap<>();

        // Recursive method to build XML for each node
        generateXmlForNode(root, "1", xml, 100, 100, nodeIdMap);

        xml.append("</root>\n</mxGraphModel>\n</diagram>\n</mxfile>");
        return xml.toString();
    }

    // Recursive function to generate the node XML and connections
    private static void generateXmlForNode(TreeNode node, String parentId, StringBuilder xml, int x, int y, Map<TreeNode, String> nodeIdMap) {
        String nodeId = UUID.randomUUID().toString(); // Generate unique ID for the node
        nodeIdMap.put(node, nodeId); // Store the node ID in the map

        // Add the node to the XML with its position
        xml.append("<mxCell id=\"").append(nodeId).append("\" value=\"").append(node.value)
                .append("\" style=\"ellipse;whiteSpace=wrap;html=1;\" vertex=\"1\" parent=\"").append(parentId)
                .append("\">\n");
        xml.append("<mxGeometry x=\"").append(x).append("\" y=\"").append(y)
                .append("\" width=\"80\" height=\"30\" as=\"geometry\"/>\n</mxCell>\n");

        // Adjust positioning for child nodes
        int childX = x + 150;
        int childY = y - 100;

        // For each child node, add the connection and call recursively
        for (TreeNode child : node.children) {
            // Recursively generate XML for each child node
            generateXmlForNode(child, nodeId, xml, childX, childY, nodeIdMap);

            // Add an edge (connection) between the current node and the child node
            String edgeId = UUID.randomUUID().toString();
            String childId = nodeIdMap.get(child);  // Get child node ID from the map
            xml.append("<mxCell id=\"").append(edgeId)
                    .append("\" style=\"edgeStyle=elbowEdgeStyle;orthogonal=0;jettySize=auto;\" edge=\"1\" parent=\"1\" source=\"")
                    .append(nodeId).append("\" target=\"").append(childId).append("\">\n");
            xml.append("<mxGeometry relative=\"1\" as=\"geometry\"/>\n</mxCell>\n");

            // Adjust vertical spacing for siblings
            childY += 100;
        }
    }

    // Method to write the XML to a file
    public static void saveToFile(String xml, String filePath) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        writer.write(xml);
        writer.close();
    }

    // Main function to test the implementation
    public static void main(String[] args) {
        // Sample TreeNode structure
        TreeNode root = new TreeNode("Root");
        TreeNode child1 = new TreeNode("Child 1");
        TreeNode child2 = new TreeNode("Child 2");
        TreeNode child3 = new TreeNode("Child 3");

        root.addChild(child1);
        root.addChild(child2);
        child1.addChild(child3);

        // Generate XML and save to a file
        String xml = generateDrawIoXml(root);
        try {
            saveToFile(xml, "/Users/aly/temp/mindmap/mindmap.drawio");
            System.out.println("Mindmap XML saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



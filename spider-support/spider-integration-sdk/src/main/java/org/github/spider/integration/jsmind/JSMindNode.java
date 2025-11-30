package org.github.spider.integration.jsmind;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Xavier
 * @date 2024/10/14 17:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JSMindNode {
    private String id;
    private String topic;
    private String expanded;
    private String direction;

    private List<JSMindNode> children;
}

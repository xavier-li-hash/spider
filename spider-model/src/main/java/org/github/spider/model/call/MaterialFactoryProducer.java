package org.github.spider.model.call;

import org.github.spider.model.call.enums.MetaMaterialTypeType;

import java.util.List;

/**
 * @author Xavier
 * @date 2024/9/11 10:01
 */
public class MaterialFactoryProducer {

    public static MaterialFactory getMetaFactory(MetaMaterialTypeType materialType, List<String> filePaths) {
        switch (materialType) {
            case COMPILATION:
            default:
                return new CompilationMaterialFactory(filePaths);
        }
    }

}

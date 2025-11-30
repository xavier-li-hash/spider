# 使用说明

1. 第一种是手工的方式 ， org.github.spider.plantuml.PlantUmlGeneration
   在jar_dir.properties配置所需要扫描的jar绝对路径
2. 第二种是maven的插件方式，org.github.spider.maven.plugin.mojo.PlantumlGenMojo
   从插件中获得包的路径信息
3. 通过maven插件的方式，输出的链路信息在项目的target目录；手工使用的方式统一在target/plantuml

# 模块介绍
- [spider-integrate-vb](spider-integrate-vb) 集成外部数据模块，如spider-integrate-vb-atp对接发布平台，如增加cat的对接则创建spider-integrate-vb-cat
- [spider-material](spider-material) 绘制链路的原材料模块
- [spider-mesh](spider-mesh) 绘制链路图（可视化）模块，不同方式的可视化使用不同的子模块开发
- [spider-model](spider-model) 数据建模模块，对原始数据进行加工与转换为易于可视化的数据，或者进行数据整合、加工等
- [spider-support](spider-support) 支持使用的模块，比如通过maven插件，idea插件等方式来提供易用性时可以在这添加模块
- [spider-utils](spider-utils) 通用的工具模块

# finish list
- 项目骨架与导出链路图文本数据 （Xavier）
- 支持直接输出代码脑图png格式文件 （欣煜）
- 实现maven插件，基于插件的方式使用 （Xavier）
- 支持对链路节点进行模糊搜索，过滤出用户关注的调用链路而忽略不关注的链路，可以用于对特定关键节点的上游接口进行分析 （Xavier）

# todo list
1. 支持微服务之间关联可视化
2. 支持更丰富的统计与分析，比如根据任意层节点搜索入口、服务，包括接入AI分析等
3. 支持更细腻的样式展示，如不同节点不同样式
4. 支持更多类型的关系图表，如时序图等
5. 支持Idea插件化安装使用
6. 支持更多语言或者更多制品作为输入源，如java结构的源码
7. 支持源码与其他平台数据的整合，如cat，granf等，支持更多源码阅读的维度，比如一边看接口一边看QPS
8. 异步调用链路的支持，比如各类MQ

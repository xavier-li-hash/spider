package org.github.spider.intergrate.vb.atp.test;

/**
 * @author Xavier
 * @date 2024/9/19 16:12
 */
public class ParseProjectJsonStr {

    public static String json;
//    public static String json = "[\n" +
//            "        {\n" +
//            "            \"id\": 10761,\n" +
//            "            \"name\": \"ty_web_chatsync\",\n" +
//            "            \"comment\": \"Tiya融云消息回调存储\",\n" +
//            "            \"env\": 7,\n" +
//            "            \"create_time\": 1562828477.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_web_chatsync\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1562828477.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 1321,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 0,\n" +
//            "            \"port\": 7717,\n" +
//            "            \"nginx_confid\": 1000,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 2,\n" +
//            "            \"gitlab_id\": 1609,\n" +
//            "            \"gitlab_url\": \"git@gitlab.lizhi.fm:zhiya/zy-web-chatsync.git\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 7,\n" +
//            "            \"lastuse_image\": 36,\n" +
//            "            \"docker_count\": 20,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"maven\",\n" +
//            "            \"build_mode\": \"build,docker,pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 1,\n" +
//            "            \"service_port\": 24790,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 1321,\n" +
//            "                \"user\": \"曾轼麟\",\n" +
//            "                \"user_name\": \"zengshilin\",\n" +
//            "                \"email\": \"zengshilin@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [],\n" +
//            "            \"merge_relation_data\": [\n" +
//            "                {\n" +
//            "                    \"id\": 178,\n" +
//            "                    \"main_project_id\": 13205,\n" +
//            "                    \"main_project_name\": \"ty_mix_web_serivce\",\n" +
//            "                    \"merged_project_name\": \"ty_web_chatsync\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-web-chatsync.git\",\n" +
//            "                    \"main\": \"fm.zhiya.web.chatsync.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"ports\": [\n" +
//            "                7717\n" +
//            "            ],\n" +
//            "            \"service_ports\": [\n" +
//            "                {\n" +
//            "                    \"port\": 7717,\n" +
//            "                    \"service_port\": 24790\n" +
//            "                }\n" +
//            "            ]\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 10772,\n" +
//            "            \"name\": \"ty_web_ops\",\n" +
//            "            \"comment\": \"tiya项目的打点检测上报等\",\n" +
//            "            \"env\": 7,\n" +
//            "            \"create_time\": 1562840938.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_web_ops\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1562840938.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 1914,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 0,\n" +
//            "            \"port\": 7719,\n" +
//            "            \"nginx_confid\": 1007,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 2,\n" +
//            "            \"gitlab_id\": 2350,\n" +
//            "            \"gitlab_url\": \"git@gitlab.lizhi.fm:zhiya/zy-web-ops.git\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 7,\n" +
//            "            \"lastuse_image\": 2,\n" +
//            "            \"docker_count\": 20,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"maven\",\n" +
//            "            \"build_mode\": \"build,docker,pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 1,\n" +
//            "            \"service_port\": 24811,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 1914,\n" +
//            "                \"user\": \"甘剑东\",\n" +
//            "                \"user_name\": \"ganjiandong\",\n" +
//            "                \"email\": \"ganjiandong@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [],\n" +
//            "            \"merge_relation_data\": [\n" +
//            "                {\n" +
//            "                    \"id\": 176,\n" +
//            "                    \"main_project_id\": 13205,\n" +
//            "                    \"main_project_name\": \"ty_mix_web_serivce\",\n" +
//            "                    \"merged_project_name\": \"ty_web_ops\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-web-ops.git\",\n" +
//            "                    \"main\": \"fm.zhiya.web.ops.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"ports\": [\n" +
//            "                7719\n" +
//            "            ],\n" +
//            "            \"service_ports\": [\n" +
//            "                {\n" +
//            "                    \"port\": 7719,\n" +
//            "                    \"service_port\": 24811\n" +
//            "                }\n" +
//            "            ]\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 12828,\n" +
//            "            \"name\": \"ty_bianque\",\n" +
//            "            \"comment\": \"工程数字化治理后台项目，提高工程治理的效率，提高技术决策效率\",\n" +
//            "            \"env\": 0,\n" +
//            "            \"create_time\": 1650277445.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_bianque\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1650277445.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 298,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 2,\n" +
//            "            \"port\": 7751,\n" +
//            "            \"nginx_confid\": 2464,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 1,\n" +
//            "            \"gitlab_id\": 7889,\n" +
//            "            \"gitlab_url\": \"git@gitlab.lizhi.fm:tiya/tiya-bianque.git\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 0,\n" +
//            "            \"lastuse_image\": 2,\n" +
//            "            \"docker_count\": 10,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"maven\",\n" +
//            "            \"build_mode\": \"pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 1,\n" +
//            "            \"service_port\": 25078,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 298,\n" +
//            "                \"user\": \"李孝赢\",\n" +
//            "                \"user_name\": \"lixiaoying\",\n" +
//            "                \"email\": \"lixiaoying@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [],\n" +
//            "            \"merge_relation_data\": [\n" +
//            "                {\n" +
//            "                    \"id\": 200,\n" +
//            "                    \"main_project_id\": 13099,\n" +
//            "                    \"main_project_name\": \"ty_mix_service\",\n" +
//            "                    \"merged_project_name\": \"ty_bianque\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:tiya/tiya-bianque.git\",\n" +
//            "                    \"main\": \"fm.tiya.bianque.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"ports\": [\n" +
//            "                7751\n" +
//            "            ],\n" +
//            "            \"service_ports\": [\n" +
//            "                {\n" +
//            "                    \"port\": 7751,\n" +
//            "                    \"service_port\": 25078\n" +
//            "                }\n" +
//            "            ]\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 13090,\n" +
//            "            \"name\": \"ty_web_lark_bot\",\n" +
//            "            \"comment\": \"TIYA Bing Chat GPT 接入飞书机器人\",\n" +
//            "            \"env\": 0,\n" +
//            "            \"create_time\": 1677124097.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_web_lark_bot\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1677124096.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 2110,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 2,\n" +
//            "            \"port\": 7766,\n" +
//            "            \"nginx_confid\": -1,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 49,\n" +
//            "            \"gitlab_id\": 10380,\n" +
//            "            \"gitlab_url\": \"git@gitlab.lizhi.fm:chenruiyuan/tiya-lark-bot.git\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 0,\n" +
//            "            \"lastuse_image\": 50,\n" +
//            "            \"docker_count\": 10,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"shell\",\n" +
//            "            \"build_mode\": \"pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 1,\n" +
//            "            \"service_port\": 25178,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 1,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 2110,\n" +
//            "                \"user\": \"无\",\n" +
//            "                \"user_name\": \"\",\n" +
//            "                \"email\": \"\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 59,\n" +
//            "                    \"name\": \"美国Tiya预发\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pre\"\n" +
//            "                },\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [],\n" +
//            "            \"merge_relation_data\": [],\n" +
//            "            \"ports\": [\n" +
//            "                7766\n" +
//            "            ],\n" +
//            "            \"service_ports\": [\n" +
//            "                {\n" +
//            "                    \"port\": 7766,\n" +
//            "                    \"service_port\": 25178\n" +
//            "                }\n" +
//            "            ]\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 13099,\n" +
//            "            \"name\": \"ty_mix_service\",\n" +
//            "            \"comment\": \"tiya服务合并\",\n" +
//            "            \"env\": 0,\n" +
//            "            \"create_time\": 1678242157.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_mix_service\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1678242157.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 298,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 2,\n" +
//            "            \"port\": 7713,\n" +
//            "            \"nginx_confid\": -1,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 36,\n" +
//            "            \"gitlab_id\": 0,\n" +
//            "            \"gitlab_url\": \"\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 0,\n" +
//            "            \"lastuse_image\": 36,\n" +
//            "            \"docker_count\": 10,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"merge\",\n" +
//            "            \"build_mode\": \"pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 1,\n" +
//            "            \"service_port\": 25181,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 298,\n" +
//            "                \"user\": \"李孝赢\",\n" +
//            "                \"user_name\": \"lixiaoying\",\n" +
//            "                \"email\": \"lixiaoying@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [\n" +
//            "                {\n" +
//            "                    \"id\": 197,\n" +
//            "                    \"main_project_id\": 13099,\n" +
//            "                    \"main_project_name\": \"ty_mix_service\",\n" +
//            "                    \"merged_project_name\": \"ty_web_admin\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-web-admin.git\",\n" +
//            "                    \"main\": \"fm.zhiya.admin.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                },\n" +
//            "                {\n" +
//            "                    \"id\": 198,\n" +
//            "                    \"main_project_id\": 13099,\n" +
//            "                    \"main_project_name\": \"ty_mix_service\",\n" +
//            "                    \"merged_project_name\": \"ty_dc_activity\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-dc-activity.git\",\n" +
//            "                    \"main\": \"fm.zhiya.activity.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                },\n" +
//            "                {\n" +
//            "                    \"id\": 199,\n" +
//            "                    \"main_project_id\": 13099,\n" +
//            "                    \"main_project_name\": \"ty_mix_service\",\n" +
//            "                    \"merged_project_name\": \"ty_sns_relation\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-sns-relation.git\",\n" +
//            "                    \"main\": \"fm.zhiya.sns.relation.bootstrap.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                },\n" +
//            "                {\n" +
//            "                    \"id\": 200,\n" +
//            "                    \"main_project_id\": 13099,\n" +
//            "                    \"main_project_name\": \"ty_mix_service\",\n" +
//            "                    \"merged_project_name\": \"ty_bianque\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:tiya/tiya-bianque.git\",\n" +
//            "                    \"main\": \"fm.tiya.bianque.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                },\n" +
//            "                {\n" +
//            "                    \"id\": 201,\n" +
//            "                    \"main_project_id\": 13099,\n" +
//            "                    \"main_project_name\": \"ty_mix_service\",\n" +
//            "                    \"merged_project_name\": \"ty_dc_live_game\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-dc-live-game.git\",\n" +
//            "                    \"main\": \"fm.zhiya.live.game.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_relation_data\": [],\n" +
//            "            \"ports\": [\n" +
//            "                7713,\n" +
//            "                7751\n" +
//            "            ],\n" +
//            "            \"service_ports\": [\n" +
//            "                {\n" +
//            "                    \"port\": 7713,\n" +
//            "                    \"service_port\": 25181\n" +
//            "                },\n" +
//            "                {\n" +
//            "                    \"port\": 7751,\n" +
//            "                    \"service_port\": 24177\n" +
//            "                }\n" +
//            "            ]\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 11054,\n" +
//            "            \"name\": \"ty_dc_security\",\n" +
//            "            \"comment\": \"海外tiya项目风控审核相关dc服务\",\n" +
//            "            \"env\": 0,\n" +
//            "            \"create_time\": 1569293965.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_dc_security\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1569293965.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 1914,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 2,\n" +
//            "            \"port\": 0,\n" +
//            "            \"nginx_confid\": -1,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 2,\n" +
//            "            \"gitlab_id\": 3340,\n" +
//            "            \"gitlab_url\": \"git@gitlab.lizhi.fm:zhiya/zy-dc-security.git\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 7,\n" +
//            "            \"lastuse_image\": 2,\n" +
//            "            \"docker_count\": 20,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"maven\",\n" +
//            "            \"build_mode\": \"build,docker,pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 0,\n" +
//            "            \"service_port\": 0,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 1914,\n" +
//            "                \"user\": \"甘剑东\",\n" +
//            "                \"user_name\": \"ganjiandong\",\n" +
//            "                \"email\": \"ganjiandong@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [],\n" +
//            "            \"merge_relation_data\": [\n" +
//            "                {\n" +
//            "                    \"id\": 153,\n" +
//            "                    \"main_project_id\": 13207,\n" +
//            "                    \"main_project_name\": \"ty_mix_dc_common\",\n" +
//            "                    \"merged_project_name\": \"ty_dc_security\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-dc-security.git\",\n" +
//            "                    \"main\": \"fm.zhiya.security.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"ports\": [],\n" +
//            "            \"service_ports\": []\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 10577,\n" +
//            "            \"name\": \"ty_dc_vc\",\n" +
//            "            \"comment\": \"tiya中东语音通话dc、话题\",\n" +
//            "            \"env\": 7,\n" +
//            "            \"create_time\": 1561620498.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_dc_vc\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1561620498.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 51,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 0,\n" +
//            "            \"port\": 7408,\n" +
//            "            \"nginx_confid\": -1,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 2,\n" +
//            "            \"gitlab_id\": 2293,\n" +
//            "            \"gitlab_url\": \"git@gitlab.lizhi.fm:zhiya/zy-dc-vc.git\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 7,\n" +
//            "            \"lastuse_image\": 2,\n" +
//            "            \"docker_count\": 20,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"maven\",\n" +
//            "            \"build_mode\": \"build,docker,pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 1,\n" +
//            "            \"service_port\": 24805,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 51,\n" +
//            "                \"user\": \"陈秋林\",\n" +
//            "                \"user_name\": \"chenqiulin\",\n" +
//            "                \"email\": \"chenqiulin@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [],\n" +
//            "            \"merge_relation_data\": [\n" +
//            "                {\n" +
//            "                    \"id\": 195,\n" +
//            "                    \"main_project_id\": 13206,\n" +
//            "                    \"main_project_name\": \"ty_mix_dc_party_common\",\n" +
//            "                    \"merged_project_name\": \"ty_dc_vc\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-dc-vc.git\",\n" +
//            "                    \"main\": \"fm.zhiya.vc.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"ports\": [\n" +
//            "                7408\n" +
//            "            ],\n" +
//            "            \"service_ports\": [\n" +
//            "                {\n" +
//            "                    \"port\": 7408,\n" +
//            "                    \"service_port\": 24805\n" +
//            "                }\n" +
//            "            ]\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 10582,\n" +
//            "            \"name\": \"ty_dc_party_core\",\n" +
//            "            \"comment\": \"派对开关播\",\n" +
//            "            \"env\": 7,\n" +
//            "            \"create_time\": 1561687929.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_dc_party_core\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1561687929.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 1914,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 0,\n" +
//            "            \"port\": 0,\n" +
//            "            \"nginx_confid\": -1,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 2,\n" +
//            "            \"gitlab_id\": 1911,\n" +
//            "            \"gitlab_url\": \"git@gitlab.lizhi.fm:zhiya/zy-dc-party-core.git\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 7,\n" +
//            "            \"lastuse_image\": 2,\n" +
//            "            \"docker_count\": 20,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"maven\",\n" +
//            "            \"build_mode\": \"build,docker,pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 0,\n" +
//            "            \"service_port\": 0,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 1914,\n" +
//            "                \"user\": \"甘剑东\",\n" +
//            "                \"user_name\": \"ganjiandong\",\n" +
//            "                \"email\": \"ganjiandong@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [],\n" +
//            "            \"merge_relation_data\": [\n" +
//            "                {\n" +
//            "                    \"id\": 136,\n" +
//            "                    \"main_project_id\": 13208,\n" +
//            "                    \"main_project_name\": \"ty_mix_dc_party_core\",\n" +
//            "                    \"merged_project_name\": \"ty_dc_party_core\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-dc-party-core.git\",\n" +
//            "                    \"main\": \"fm.zhiya.party.core.bootstrap.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"ports\": [],\n" +
//            "            \"service_ports\": []\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 10587,\n" +
//            "            \"name\": \"ty_app_party_core\",\n" +
//            "            \"comment\": \"派对核心appserver\",\n" +
//            "            \"env\": 7,\n" +
//            "            \"create_time\": 1561688101.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_app_party_core\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1561688100.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 1914,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 0,\n" +
//            "            \"port\": 0,\n" +
//            "            \"nginx_confid\": -1,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 2,\n" +
//            "            \"gitlab_id\": 1938,\n" +
//            "            \"gitlab_url\": \"git@gitlab.lizhi.fm:zhiya/zy-app-party-core.git\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 7,\n" +
//            "            \"lastuse_image\": 2,\n" +
//            "            \"docker_count\": 20,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"maven\",\n" +
//            "            \"build_mode\": \"build,docker,pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 0,\n" +
//            "            \"service_port\": 0,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 1914,\n" +
//            "                \"user\": \"甘剑东\",\n" +
//            "                \"user_name\": \"ganjiandong\",\n" +
//            "                \"email\": \"ganjiandong@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [],\n" +
//            "            \"merge_relation_data\": [\n" +
//            "                {\n" +
//            "                    \"id\": 144,\n" +
//            "                    \"main_project_id\": 13210,\n" +
//            "                    \"main_project_name\": \"ty_mix_app_party\",\n" +
//            "                    \"merged_project_name\": \"ty_app_party_core\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-app-party-core.git\",\n" +
//            "                    \"main\": \"fm.zhiya.app.party.core.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"ports\": [],\n" +
//            "            \"service_ports\": []\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 12892,\n" +
//            "            \"name\": \"ty_web_game_bumper\",\n" +
//            "            \"comment\": \"TIYA APP 房间内的碰碰球小游戏帧同步游戏服务端\",\n" +
//            "            \"env\": 0,\n" +
//            "            \"create_time\": 1650525687.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_web_game_bumper\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1650525687.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 95,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 2,\n" +
//            "            \"port\": 7754,\n" +
//            "            \"nginx_confid\": 2483,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 1,\n" +
//            "            \"gitlab_id\": 9080,\n" +
//            "            \"gitlab_url\": \"git@gitlab.lizhi.fm:tiya-fe/node/bumper-ball.git\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 0,\n" +
//            "            \"lastuse_image\": 50,\n" +
//            "            \"docker_count\": 10,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"shell\",\n" +
//            "            \"build_mode\": \"pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 1,\n" +
//            "            \"service_port\": 25089,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 95,\n" +
//            "                \"user\": \"吴永泉\",\n" +
//            "                \"user_name\": \"wuyongquan\",\n" +
//            "                \"email\": \"wuyongquan@lizhi.fm\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 59,\n" +
//            "                    \"name\": \"美国Tiya预发\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pre\"\n" +
//            "                },\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [],\n" +
//            "            \"merge_relation_data\": [],\n" +
//            "            \"ports\": [\n" +
//            "                7754\n" +
//            "            ],\n" +
//            "            \"service_ports\": [\n" +
//            "                {\n" +
//            "                    \"port\": 7754,\n" +
//            "                    \"service_port\": 25089\n" +
//            "                }\n" +
//            "            ]\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 13150,\n" +
//            "            \"name\": \"ty_dc_ai_chatbot\",\n" +
//            "            \"comment\": \"机器人聊天相关业务，AI记忆，角色管理等\",\n" +
//            "            \"env\": 0,\n" +
//            "            \"create_time\": 1684812090.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_dc_ai_chatbot\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1684812090.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 298,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 2,\n" +
//            "            \"port\": 0,\n" +
//            "            \"nginx_confid\": -1,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 60,\n" +
//            "            \"gitlab_id\": 10929,\n" +
//            "            \"gitlab_url\": \"git@gitlab.lizhi.fm:tiya/ty-dc-ai-chatbot.git\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 0,\n" +
//            "            \"lastuse_image\": 58,\n" +
//            "            \"docker_count\": 10,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"maven\",\n" +
//            "            \"build_mode\": \"pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 0,\n" +
//            "            \"service_port\": 0,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 298,\n" +
//            "                \"user\": \"李孝赢\",\n" +
//            "                \"user_name\": \"lixiaoying\",\n" +
//            "                \"email\": \"lixiaoying@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [],\n" +
//            "            \"merge_relation_data\": [\n" +
//            "                {\n" +
//            "                    \"id\": 196,\n" +
//            "                    \"main_project_id\": 13206,\n" +
//            "                    \"main_project_name\": \"ty_mix_dc_party_common\",\n" +
//            "                    \"merged_project_name\": \"ty_dc_ai_chatbot\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:tiya/ty-dc-ai-chatbot.git\",\n" +
//            "                    \"main\": \"fm.tiya.ai.chatbot.provider.MicroservicesApplication\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"ports\": [],\n" +
//            "            \"service_ports\": []\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 10602,\n" +
//            "            \"name\": \"ty_dc_party_amuse\",\n" +
//            "            \"comment\": \"派对上下麦管理\",\n" +
//            "            \"env\": 7,\n" +
//            "            \"create_time\": 1561689076.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_dc_party_amuse\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1561689075.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 51,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 0,\n" +
//            "            \"port\": 0,\n" +
//            "            \"nginx_confid\": -1,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 2,\n" +
//            "            \"gitlab_id\": 1896,\n" +
//            "            \"gitlab_url\": \"git@gitlab.lizhi.fm:zhiya/zy-dc-party-amuse.git\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 7,\n" +
//            "            \"lastuse_image\": 2,\n" +
//            "            \"docker_count\": 20,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"maven\",\n" +
//            "            \"build_mode\": \"build,docker,pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 0,\n" +
//            "            \"service_port\": 0,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 51,\n" +
//            "                \"user\": \"陈秋林\",\n" +
//            "                \"user_name\": \"chenqiulin\",\n" +
//            "                \"email\": \"chenqiulin@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [],\n" +
//            "            \"merge_relation_data\": [\n" +
//            "                {\n" +
//            "                    \"id\": 194,\n" +
//            "                    \"main_project_id\": 13206,\n" +
//            "                    \"main_project_name\": \"ty_mix_dc_party_common\",\n" +
//            "                    \"merged_project_name\": \"ty_dc_party_amuse\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-dc-party-amuse.git\",\n" +
//            "                    \"main\": \"fm.zhiya.party.amuse.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"ports\": [],\n" +
//            "            \"service_ports\": []\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 10607,\n" +
//            "            \"name\": \"ty_app_chat\",\n" +
//            "            \"comment\": \"聊天appserver服务\",\n" +
//            "            \"env\": 7,\n" +
//            "            \"create_time\": 1561690459.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_app_chat\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1561690459.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 1321,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 0,\n" +
//            "            \"port\": 0,\n" +
//            "            \"nginx_confid\": -1,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 2,\n" +
//            "            \"gitlab_id\": 1518,\n" +
//            "            \"gitlab_url\": \"git@gitlab.lizhi.fm:zhiya/zy-app-chat.git\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 7,\n" +
//            "            \"lastuse_image\": 36,\n" +
//            "            \"docker_count\": 20,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"maven\",\n" +
//            "            \"build_mode\": \"build,docker,pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 0,\n" +
//            "            \"service_port\": 0,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 1321,\n" +
//            "                \"user\": \"曾轼麟\",\n" +
//            "                \"user_name\": \"zengshilin\",\n" +
//            "                \"email\": \"zengshilin@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [],\n" +
//            "            \"merge_relation_data\": [\n" +
//            "                {\n" +
//            "                    \"id\": 166,\n" +
//            "                    \"main_project_id\": 13209,\n" +
//            "                    \"main_project_name\": \"ty_mix_app_user\",\n" +
//            "                    \"merged_project_name\": \"ty_app_chat\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-app-chat.git\",\n" +
//            "                    \"main\": \"fm.zhiya.app.chat.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"ports\": [],\n" +
//            "            \"service_ports\": []\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 10610,\n" +
//            "            \"name\": \"ty_dc_msg\",\n" +
//            "            \"comment\": \"消息dc服务\",\n" +
//            "            \"env\": 7,\n" +
//            "            \"create_time\": 1561690510.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_dc_msg\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1561690510.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 1321,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 0,\n" +
//            "            \"port\": 0,\n" +
//            "            \"nginx_confid\": -1,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 36,\n" +
//            "            \"gitlab_id\": 959,\n" +
//            "            \"gitlab_url\": \"git@gitlab.lizhi.fm:zhiya/zy-dc-msg.git\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 7,\n" +
//            "            \"lastuse_image\": 47,\n" +
//            "            \"docker_count\": 20,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"maven\",\n" +
//            "            \"build_mode\": \"build,docker,pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 0,\n" +
//            "            \"service_port\": 0,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 1321,\n" +
//            "                \"user\": \"曾轼麟\",\n" +
//            "                \"user_name\": \"zengshilin\",\n" +
//            "                \"email\": \"zengshilin@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [],\n" +
//            "            \"merge_relation_data\": [\n" +
//            "                {\n" +
//            "                    \"id\": 152,\n" +
//            "                    \"main_project_id\": 13207,\n" +
//            "                    \"main_project_name\": \"ty_mix_dc_common\",\n" +
//            "                    \"merged_project_name\": \"ty_dc_msg\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-dc-msg.git\",\n" +
//            "                    \"main\": \"fm.tiya.msg.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"ports\": [],\n" +
//            "            \"service_ports\": []\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 10614,\n" +
//            "            \"name\": \"ty_app_user\",\n" +
//            "            \"comment\": \"用户基础服务as\",\n" +
//            "            \"env\": 7,\n" +
//            "            \"create_time\": 1561692572.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_app_user\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1561692571.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 1321,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 0,\n" +
//            "            \"port\": 0,\n" +
//            "            \"nginx_confid\": -1,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 36,\n" +
//            "            \"gitlab_id\": 1516,\n" +
//            "            \"gitlab_url\": \"git@gitlab.lizhi.fm:zhiya/zy-app-user.git\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 7,\n" +
//            "            \"lastuse_image\": 36,\n" +
//            "            \"docker_count\": 20,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"maven\",\n" +
//            "            \"build_mode\": \"build,docker,pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 0,\n" +
//            "            \"service_port\": 0,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 1321,\n" +
//            "                \"user\": \"曾轼麟\",\n" +
//            "                \"user_name\": \"zengshilin\",\n" +
//            "                \"email\": \"zengshilin@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [],\n" +
//            "            \"merge_relation_data\": [\n" +
//            "                {\n" +
//            "                    \"id\": 165,\n" +
//            "                    \"main_project_id\": 13209,\n" +
//            "                    \"main_project_name\": \"ty_mix_app_user\",\n" +
//            "                    \"merged_project_name\": \"ty_app_user\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-app-user.git\",\n" +
//            "                    \"main\": \"fm.zhiya.app.user.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"ports\": [],\n" +
//            "            \"service_ports\": []\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 10615,\n" +
//            "            \"name\": \"ty_dc_user2\",\n" +
//            "            \"comment\": \"用户基础服务dc\",\n" +
//            "            \"env\": 7,\n" +
//            "            \"create_time\": 1561692599.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_dc_user2\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1561692598.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 1611,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 0,\n" +
//            "            \"port\": 0,\n" +
//            "            \"nginx_confid\": -1,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 2,\n" +
//            "            \"gitlab_id\": 1736,\n" +
//            "            \"gitlab_url\": \"git@gitlab.lizhi.fm:zhiya/zy-dc-user2.git\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 7,\n" +
//            "            \"lastuse_image\": 36,\n" +
//            "            \"docker_count\": 20,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"maven\",\n" +
//            "            \"build_mode\": \"pipeline,build,docker\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 0,\n" +
//            "            \"service_port\": 0,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 1611,\n" +
//            "                \"user\": \"无\",\n" +
//            "                \"user_name\": \"\",\n" +
//            "                \"email\": \"\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [],\n" +
//            "            \"merge_relation_data\": [],\n" +
//            "            \"ports\": [],\n" +
//            "            \"service_ports\": []\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 10618,\n" +
//            "            \"name\": \"ty_app_voicepair\",\n" +
//            "            \"comment\": \"用户声音瓶基础服务as\",\n" +
//            "            \"env\": 7,\n" +
//            "            \"create_time\": 1561692653.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_app_voicepair\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1561692653.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 298,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 0,\n" +
//            "            \"port\": 0,\n" +
//            "            \"nginx_confid\": -1,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 2,\n" +
//            "            \"gitlab_id\": 1531,\n" +
//            "            \"gitlab_url\": \"git@gitlab.lizhi.fm:zhiya/zy-app-voicepair.git\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 7,\n" +
//            "            \"lastuse_image\": 2,\n" +
//            "            \"docker_count\": 20,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"maven\",\n" +
//            "            \"build_mode\": \"build,docker,pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 0,\n" +
//            "            \"service_port\": 0,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 298,\n" +
//            "                \"user\": \"李孝赢\",\n" +
//            "                \"user_name\": \"lixiaoying\",\n" +
//            "                \"email\": \"lixiaoying@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [],\n" +
//            "            \"merge_relation_data\": [\n" +
//            "                {\n" +
//            "                    \"id\": 167,\n" +
//            "                    \"main_project_id\": 13209,\n" +
//            "                    \"main_project_name\": \"ty_mix_app_user\",\n" +
//            "                    \"merged_project_name\": \"ty_app_voicepair\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-app-voicepair.git\",\n" +
//            "                    \"main\": \"fm.zhiya.app.voicepair.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"ports\": [],\n" +
//            "            \"service_ports\": []\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 12414,\n" +
//            "            \"name\": \"ty_dc_party_match\",\n" +
//            "            \"comment\": \"派对随机房间匹配模块\",\n" +
//            "            \"env\": 0,\n" +
//            "            \"create_time\": 1609149452.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_dc_party_match\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1609149452.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 298,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 2,\n" +
//            "            \"port\": 0,\n" +
//            "            \"nginx_confid\": -1,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 2,\n" +
//            "            \"gitlab_id\": 6185,\n" +
//            "            \"gitlab_url\": \"git@gitlab.lizhi.fm:zhiya/zy-dc-party-match.git\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 7,\n" +
//            "            \"lastuse_image\": 2,\n" +
//            "            \"docker_count\": 10,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"maven\",\n" +
//            "            \"build_mode\": \"upload,build,docker,pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 0,\n" +
//            "            \"service_port\": 0,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 298,\n" +
//            "                \"user\": \"李孝赢\",\n" +
//            "                \"user_name\": \"lixiaoying\",\n" +
//            "                \"email\": \"lixiaoying@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [],\n" +
//            "            \"merge_relation_data\": [],\n" +
//            "            \"ports\": [],\n" +
//            "            \"service_ports\": []\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 10626,\n" +
//            "            \"name\": \"ty_dc_recommend\",\n" +
//            "            \"comment\": \"匹配优待新用户dc\",\n" +
//            "            \"env\": 7,\n" +
//            "            \"create_time\": 1561692820.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_dc_recommend\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1561692819.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 298,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 0,\n" +
//            "            \"port\": 0,\n" +
//            "            \"nginx_confid\": -1,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 2,\n" +
//            "            \"gitlab_id\": 2614,\n" +
//            "            \"gitlab_url\": \"git@gitlab.lizhi.fm:zhiya/zy-dc-recommend.git\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 7,\n" +
//            "            \"lastuse_image\": 2,\n" +
//            "            \"docker_count\": 20,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"maven\",\n" +
//            "            \"build_mode\": \"pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 0,\n" +
//            "            \"service_port\": 0,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 298,\n" +
//            "                \"user\": \"李孝赢\",\n" +
//            "                \"user_name\": \"lixiaoying\",\n" +
//            "                \"email\": \"lixiaoying@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [],\n" +
//            "            \"merge_relation_data\": [\n" +
//            "                {\n" +
//            "                    \"id\": 154,\n" +
//            "                    \"main_project_id\": 13204,\n" +
//            "                    \"main_project_name\": \"ty_mix_dc_data_warehouse\",\n" +
//            "                    \"merged_project_name\": \"ty_dc_recommend\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-dc-recommend.git\",\n" +
//            "                    \"main\": \"fm.zhiya.recommend.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"ports\": [],\n" +
//            "            \"service_ports\": []\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 10627,\n" +
//            "            \"name\": \"ty_sns_relation\",\n" +
//            "            \"comment\": \"用户关系dc\",\n" +
//            "            \"env\": 7,\n" +
//            "            \"create_time\": 1561692844.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_sns_relation\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1561692844.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 1321,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 0,\n" +
//            "            \"port\": 0,\n" +
//            "            \"nginx_confid\": -1,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 2,\n" +
//            "            \"gitlab_id\": 825,\n" +
//            "            \"gitlab_url\": \"git@gitlab.lizhi.fm:zhiya/zy-sns-relation.git\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 7,\n" +
//            "            \"lastuse_image\": 2,\n" +
//            "            \"docker_count\": 20,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"maven\",\n" +
//            "            \"build_mode\": \"build,docker,pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 0,\n" +
//            "            \"service_port\": 0,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 1321,\n" +
//            "                \"user\": \"曾轼麟\",\n" +
//            "                \"user_name\": \"zengshilin\",\n" +
//            "                \"email\": \"zengshilin@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [],\n" +
//            "            \"merge_relation_data\": [\n" +
//            "                {\n" +
//            "                    \"id\": 199,\n" +
//            "                    \"main_project_id\": 13099,\n" +
//            "                    \"main_project_name\": \"ty_mix_service\",\n" +
//            "                    \"merged_project_name\": \"ty_sns_relation\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-sns-relation.git\",\n" +
//            "                    \"main\": \"fm.zhiya.sns.relation.bootstrap.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"ports\": [],\n" +
//            "            \"service_ports\": []\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 11395,\n" +
//            "            \"name\": \"ty_web_push_platform\",\n" +
//            "            \"comment\": \"tiya-web推送平台后台\",\n" +
//            "            \"env\": 0,\n" +
//            "            \"create_time\": 1575881360.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_web_push_platform\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1575881360.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 1914,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 2,\n" +
//            "            \"port\": 7725,\n" +
//            "            \"nginx_confid\": 1417,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 2,\n" +
//            "            \"gitlab_id\": 3777,\n" +
//            "            \"gitlab_url\": \"git@gitlab.lizhi.fm:zhiya/zy-web-push-platform.git\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 7,\n" +
//            "            \"lastuse_image\": 2,\n" +
//            "            \"docker_count\": 20,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"maven\",\n" +
//            "            \"build_mode\": \"build,docker,pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 1,\n" +
//            "            \"service_port\": 24816,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 1914,\n" +
//            "                \"user\": \"甘剑东\",\n" +
//            "                \"user_name\": \"ganjiandong\",\n" +
//            "                \"email\": \"ganjiandong@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [],\n" +
//            "            \"merge_relation_data\": [\n" +
//            "                {\n" +
//            "                    \"id\": 177,\n" +
//            "                    \"main_project_id\": 13205,\n" +
//            "                    \"main_project_name\": \"ty_mix_web_serivce\",\n" +
//            "                    \"merged_project_name\": \"ty_web_push_platform\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-web-push-platform.git\",\n" +
//            "                    \"main\": \"fm.zhiya.web.push.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"ports\": [\n" +
//            "                7725\n" +
//            "            ],\n" +
//            "            \"service_ports\": [\n" +
//            "                {\n" +
//            "                    \"port\": 7725,\n" +
//            "                    \"service_port\": 24816\n" +
//            "                }\n" +
//            "            ]\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 11667,\n" +
//            "            \"name\": \"ty_dc_live_game\",\n" +
//            "            \"comment\": \"派对直播间游戏\",\n" +
//            "            \"env\": 0,\n" +
//            "            \"create_time\": 1585106914.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_dc_live_game\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1585106914.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 51,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 2,\n" +
//            "            \"port\": 0,\n" +
//            "            \"nginx_confid\": -1,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 2,\n" +
//            "            \"gitlab_id\": 4229,\n" +
//            "            \"gitlab_url\": \"git@gitlab.lizhi.fm:zhiya/zy-dc-live-game.git\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 7,\n" +
//            "            \"lastuse_image\": 2,\n" +
//            "            \"docker_count\": 20,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"maven\",\n" +
//            "            \"build_mode\": \"build,docker,pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 0,\n" +
//            "            \"service_port\": 0,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 51,\n" +
//            "                \"user\": \"陈秋林\",\n" +
//            "                \"user_name\": \"chenqiulin\",\n" +
//            "                \"email\": \"chenqiulin@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [],\n" +
//            "            \"merge_relation_data\": [\n" +
//            "                {\n" +
//            "                    \"id\": 201,\n" +
//            "                    \"main_project_id\": 13099,\n" +
//            "                    \"main_project_name\": \"ty_mix_service\",\n" +
//            "                    \"merged_project_name\": \"ty_dc_live_game\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-dc-live-game.git\",\n" +
//            "                    \"main\": \"fm.zhiya.live.game.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"ports\": [],\n" +
//            "            \"service_ports\": []\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 13204,\n" +
//            "            \"name\": \"ty_mix_dc_data_warehouse\",\n" +
//            "            \"comment\": \"tiya数仓服务合并\",\n" +
//            "            \"env\": 0,\n" +
//            "            \"create_time\": 1692845069.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_mix_dc_data_warehouse\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1692845068.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 1914,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 2,\n" +
//            "            \"port\": 0,\n" +
//            "            \"nginx_confid\": -1,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 36,\n" +
//            "            \"gitlab_id\": 0,\n" +
//            "            \"gitlab_url\": \"\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 0,\n" +
//            "            \"lastuse_image\": 36,\n" +
//            "            \"docker_count\": 10,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"merge\",\n" +
//            "            \"build_mode\": \"pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 0,\n" +
//            "            \"service_port\": 0,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 1914,\n" +
//            "                \"user\": \"甘剑东\",\n" +
//            "                \"user_name\": \"ganjiandong\",\n" +
//            "                \"email\": \"ganjiandong@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [\n" +
//            "                {\n" +
//            "                    \"id\": 154,\n" +
//            "                    \"main_project_id\": 13204,\n" +
//            "                    \"main_project_name\": \"ty_mix_dc_data_warehouse\",\n" +
//            "                    \"merged_project_name\": \"ty_dc_recommend\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-dc-recommend.git\",\n" +
//            "                    \"main\": \"fm.zhiya.recommend.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                },\n" +
//            "                {\n" +
//            "                    \"id\": 155,\n" +
//            "                    \"main_project_id\": 13204,\n" +
//            "                    \"main_project_name\": \"ty_mix_dc_data_warehouse\",\n" +
//            "                    \"merged_project_name\": \"ty_dc_mall\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-dc-mall.git\",\n" +
//            "                    \"main\": \"fm.zhiya.mall.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                },\n" +
//            "                {\n" +
//            "                    \"id\": 156,\n" +
//            "                    \"main_project_id\": 13204,\n" +
//            "                    \"main_project_name\": \"ty_mix_dc_data_warehouse\",\n" +
//            "                    \"merged_project_name\": \"ty_dc_data_warehouse_scheduled\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-dc-data-warehouse-scheduled.git\",\n" +
//            "                    \"main\": \"fm.zhiya.data.warehouse.control.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_relation_data\": [],\n" +
//            "            \"ports\": [],\n" +
//            "            \"service_ports\": []\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 11669,\n" +
//            "            \"name\": \"ty_web_live_game\",\n" +
//            "            \"comment\": \"派对直播间游戏web\",\n" +
//            "            \"env\": 0,\n" +
//            "            \"create_time\": 1585107199.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_web_live_game\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1585107199.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 51,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 2,\n" +
//            "            \"port\": 7729,\n" +
//            "            \"nginx_confid\": 1599,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 2,\n" +
//            "            \"gitlab_id\": 4228,\n" +
//            "            \"gitlab_url\": \"git@gitlab.lizhi.fm:zhiya/zy-web-live-game.git\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 7,\n" +
//            "            \"lastuse_image\": 2,\n" +
//            "            \"docker_count\": 20,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"maven\",\n" +
//            "            \"build_mode\": \"build,docker,pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 1,\n" +
//            "            \"service_port\": 24821,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 51,\n" +
//            "                \"user\": \"陈秋林\",\n" +
//            "                \"user_name\": \"chenqiulin\",\n" +
//            "                \"email\": \"chenqiulin@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [],\n" +
//            "            \"merge_relation_data\": [],\n" +
//            "            \"ports\": [\n" +
//            "                7729\n" +
//            "            ],\n" +
//            "            \"service_ports\": [\n" +
//            "                {\n" +
//            "                    \"port\": 7729,\n" +
//            "                    \"service_port\": 24821\n" +
//            "                }\n" +
//            "            ]\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 13205,\n" +
//            "            \"name\": \"ty_mix_web_serivce\",\n" +
//            "            \"comment\": \"微服务合并-web相关服务\",\n" +
//            "            \"env\": 0,\n" +
//            "            \"create_time\": 1693277230.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_mix_web_serivce\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1693277229.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 1914,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 2,\n" +
//            "            \"port\": 0,\n" +
//            "            \"nginx_confid\": -1,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 36,\n" +
//            "            \"gitlab_id\": 0,\n" +
//            "            \"gitlab_url\": \"\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 0,\n" +
//            "            \"lastuse_image\": 36,\n" +
//            "            \"docker_count\": 10,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"merge\",\n" +
//            "            \"build_mode\": \"pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 1,\n" +
//            "            \"service_port\": 0,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 1914,\n" +
//            "                \"user\": \"甘剑东\",\n" +
//            "                \"user_name\": \"ganjiandong\",\n" +
//            "                \"email\": \"ganjiandong@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [\n" +
//            "                {\n" +
//            "                    \"id\": 176,\n" +
//            "                    \"main_project_id\": 13205,\n" +
//            "                    \"main_project_name\": \"ty_mix_web_serivce\",\n" +
//            "                    \"merged_project_name\": \"ty_web_ops\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-web-ops.git\",\n" +
//            "                    \"main\": \"fm.zhiya.web.ops.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                },\n" +
//            "                {\n" +
//            "                    \"id\": 177,\n" +
//            "                    \"main_project_id\": 13205,\n" +
//            "                    \"main_project_name\": \"ty_mix_web_serivce\",\n" +
//            "                    \"merged_project_name\": \"ty_web_push_platform\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-web-push-platform.git\",\n" +
//            "                    \"main\": \"fm.zhiya.web.push.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                },\n" +
//            "                {\n" +
//            "                    \"id\": 178,\n" +
//            "                    \"main_project_id\": 13205,\n" +
//            "                    \"main_project_name\": \"ty_mix_web_serivce\",\n" +
//            "                    \"merged_project_name\": \"ty_web_chatsync\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-web-chatsync.git\",\n" +
//            "                    \"main\": \"fm.zhiya.web.chatsync.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                },\n" +
//            "                {\n" +
//            "                    \"id\": 179,\n" +
//            "                    \"main_project_id\": 13205,\n" +
//            "                    \"main_project_name\": \"ty_mix_web_serivce\",\n" +
//            "                    \"merged_project_name\": \"ty_web_marketing\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-web-marketing.git\",\n" +
//            "                    \"main\": \"fm.zhiya.web.marketing.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_relation_data\": [],\n" +
//            "            \"ports\": [\n" +
//            "                7719,\n" +
//            "                7518,\n" +
//            "                7725,\n" +
//            "                7717\n" +
//            "            ],\n" +
//            "            \"service_ports\": [\n" +
//            "                {\n" +
//            "                    \"port\": 7719,\n" +
//            "                    \"service_port\": 24153\n" +
//            "                },\n" +
//            "                {\n" +
//            "                    \"port\": 7518,\n" +
//            "                    \"service_port\": 24154\n" +
//            "                },\n" +
//            "                {\n" +
//            "                    \"port\": 7725,\n" +
//            "                    \"service_port\": 24156\n" +
//            "                },\n" +
//            "                {\n" +
//            "                    \"port\": 7717,\n" +
//            "                    \"service_port\": 24161\n" +
//            "                }\n" +
//            "            ]\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 13206,\n" +
//            "            \"name\": \"ty_mix_dc_party_common\",\n" +
//            "            \"comment\": \"微服务合并-派对DC相关common服务\",\n" +
//            "            \"env\": 0,\n" +
//            "            \"create_time\": 1693277562.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_mix_dc_party_common\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1693277562.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 1914,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 2,\n" +
//            "            \"port\": 0,\n" +
//            "            \"nginx_confid\": -1,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 36,\n" +
//            "            \"gitlab_id\": 0,\n" +
//            "            \"gitlab_url\": \"\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 0,\n" +
//            "            \"lastuse_image\": 60,\n" +
//            "            \"docker_count\": 10,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"merge\",\n" +
//            "            \"build_mode\": \"pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 1,\n" +
//            "            \"service_port\": 0,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 1914,\n" +
//            "                \"user\": \"甘剑东\",\n" +
//            "                \"user_name\": \"ganjiandong\",\n" +
//            "                \"email\": \"ganjiandong@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [\n" +
//            "                {\n" +
//            "                    \"id\": 194,\n" +
//            "                    \"main_project_id\": 13206,\n" +
//            "                    \"main_project_name\": \"ty_mix_dc_party_common\",\n" +
//            "                    \"merged_project_name\": \"ty_dc_party_amuse\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-dc-party-amuse.git\",\n" +
//            "                    \"main\": \"fm.zhiya.party.amuse.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                },\n" +
//            "                {\n" +
//            "                    \"id\": 195,\n" +
//            "                    \"main_project_id\": 13206,\n" +
//            "                    \"main_project_name\": \"ty_mix_dc_party_common\",\n" +
//            "                    \"merged_project_name\": \"ty_dc_vc\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-dc-vc.git\",\n" +
//            "                    \"main\": \"fm.zhiya.vc.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                },\n" +
//            "                {\n" +
//            "                    \"id\": 196,\n" +
//            "                    \"main_project_id\": 13206,\n" +
//            "                    \"main_project_name\": \"ty_mix_dc_party_common\",\n" +
//            "                    \"merged_project_name\": \"ty_dc_ai_chatbot\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:tiya/ty-dc-ai-chatbot.git\",\n" +
//            "                    \"main\": \"fm.tiya.ai.chatbot.provider.MicroservicesApplication\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_relation_data\": [],\n" +
//            "            \"ports\": [\n" +
//            "                7408\n" +
//            "            ],\n" +
//            "            \"service_ports\": [\n" +
//            "                {\n" +
//            "                    \"port\": 7408,\n" +
//            "                    \"service_port\": 24151\n" +
//            "                }\n" +
//            "            ]\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 13207,\n" +
//            "            \"name\": \"ty_mix_dc_common\",\n" +
//            "            \"comment\": \"微服务合并-DC相关通用服务\",\n" +
//            "            \"env\": 0,\n" +
//            "            \"create_time\": 1693277573.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_mix_dc_common\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1693277573.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 1914,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 2,\n" +
//            "            \"port\": 0,\n" +
//            "            \"nginx_confid\": -1,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 36,\n" +
//            "            \"gitlab_id\": 0,\n" +
//            "            \"gitlab_url\": \"\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 0,\n" +
//            "            \"lastuse_image\": 47,\n" +
//            "            \"docker_count\": 10,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"merge\",\n" +
//            "            \"build_mode\": \"pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 0,\n" +
//            "            \"service_port\": 0,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 1914,\n" +
//            "                \"user\": \"甘剑东\",\n" +
//            "                \"user_name\": \"ganjiandong\",\n" +
//            "                \"email\": \"ganjiandong@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [\n" +
//            "                {\n" +
//            "                    \"id\": 151,\n" +
//            "                    \"main_project_id\": 13207,\n" +
//            "                    \"main_project_name\": \"ty_mix_dc_common\",\n" +
//            "                    \"merged_project_name\": \"ty_dc_user_behavior\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-dc-user-behavior.git\",\n" +
//            "                    \"main\": \"fm.zhiya.user.behavior.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                },\n" +
//            "                {\n" +
//            "                    \"id\": 152,\n" +
//            "                    \"main_project_id\": 13207,\n" +
//            "                    \"main_project_name\": \"ty_mix_dc_common\",\n" +
//            "                    \"merged_project_name\": \"ty_dc_msg\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-dc-msg.git\",\n" +
//            "                    \"main\": \"fm.tiya.msg.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                },\n" +
//            "                {\n" +
//            "                    \"id\": 153,\n" +
//            "                    \"main_project_id\": 13207,\n" +
//            "                    \"main_project_name\": \"ty_mix_dc_common\",\n" +
//            "                    \"merged_project_name\": \"ty_dc_security\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-dc-security.git\",\n" +
//            "                    \"main\": \"fm.zhiya.security.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_relation_data\": [],\n" +
//            "            \"ports\": [],\n" +
//            "            \"service_ports\": []\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 13208,\n" +
//            "            \"name\": \"ty_mix_dc_party_core\",\n" +
//            "            \"comment\": \"微服务合并-派对DC相关服务\",\n" +
//            "            \"env\": 0,\n" +
//            "            \"create_time\": 1693277580.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_mix_dc_party_core\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1693277580.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 1611,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 2,\n" +
//            "            \"port\": 0,\n" +
//            "            \"nginx_confid\": -1,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 36,\n" +
//            "            \"gitlab_id\": 0,\n" +
//            "            \"gitlab_url\": \"\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 0,\n" +
//            "            \"lastuse_image\": 36,\n" +
//            "            \"docker_count\": 10,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"merge\",\n" +
//            "            \"build_mode\": \"pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 0,\n" +
//            "            \"service_port\": 0,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 1611,\n" +
//            "                \"user\": \"无\",\n" +
//            "                \"user_name\": \"\",\n" +
//            "                \"email\": \"\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [\n" +
//            "                {\n" +
//            "                    \"id\": 135,\n" +
//            "                    \"main_project_id\": 13208,\n" +
//            "                    \"main_project_name\": \"ty_mix_dc_party_core\",\n" +
//            "                    \"merged_project_name\": \"ty_dc_alter\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/ty-dc-alter.git\",\n" +
//            "                    \"main\": \"fm.tiya.alter.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                },\n" +
//            "                {\n" +
//            "                    \"id\": 136,\n" +
//            "                    \"main_project_id\": 13208,\n" +
//            "                    \"main_project_name\": \"ty_mix_dc_party_core\",\n" +
//            "                    \"merged_project_name\": \"ty_dc_party_core\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-dc-party-core.git\",\n" +
//            "                    \"main\": \"fm.zhiya.party.core.bootstrap.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                },\n" +
//            "                {\n" +
//            "                    \"id\": 137,\n" +
//            "                    \"main_project_id\": 13208,\n" +
//            "                    \"main_project_name\": \"ty_mix_dc_party_core\",\n" +
//            "                    \"merged_project_name\": \"ty_dc_group\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:tiya/ty-dc-group.git\",\n" +
//            "                    \"main\": \"fm.tiya.group.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_relation_data\": [],\n" +
//            "            \"ports\": [],\n" +
//            "            \"service_ports\": []\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 13209,\n" +
//            "            \"name\": \"ty_mix_app_user\",\n" +
//            "            \"comment\": \"微服务合并-用户AS相关服务\",\n" +
//            "            \"env\": 0,\n" +
//            "            \"create_time\": 1693277590.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_mix_app_user\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1693277590.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 1914,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 2,\n" +
//            "            \"port\": 0,\n" +
//            "            \"nginx_confid\": -1,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 36,\n" +
//            "            \"gitlab_id\": 0,\n" +
//            "            \"gitlab_url\": \"\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 0,\n" +
//            "            \"lastuse_image\": 2,\n" +
//            "            \"docker_count\": 10,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"merge\",\n" +
//            "            \"build_mode\": \"pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 0,\n" +
//            "            \"service_port\": 0,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 1914,\n" +
//            "                \"user\": \"甘剑东\",\n" +
//            "                \"user_name\": \"ganjiandong\",\n" +
//            "                \"email\": \"ganjiandong@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [\n" +
//            "                {\n" +
//            "                    \"id\": 165,\n" +
//            "                    \"main_project_id\": 13209,\n" +
//            "                    \"main_project_name\": \"ty_mix_app_user\",\n" +
//            "                    \"merged_project_name\": \"ty_app_user\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-app-user.git\",\n" +
//            "                    \"main\": \"fm.zhiya.app.user.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                },\n" +
//            "                {\n" +
//            "                    \"id\": 166,\n" +
//            "                    \"main_project_id\": 13209,\n" +
//            "                    \"main_project_name\": \"ty_mix_app_user\",\n" +
//            "                    \"merged_project_name\": \"ty_app_chat\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-app-chat.git\",\n" +
//            "                    \"main\": \"fm.zhiya.app.chat.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                },\n" +
//            "                {\n" +
//            "                    \"id\": 167,\n" +
//            "                    \"main_project_id\": 13209,\n" +
//            "                    \"main_project_name\": \"ty_mix_app_user\",\n" +
//            "                    \"merged_project_name\": \"ty_app_voicepair\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-app-voicepair.git\",\n" +
//            "                    \"main\": \"fm.zhiya.app.voicepair.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_relation_data\": [],\n" +
//            "            \"ports\": [],\n" +
//            "            \"service_ports\": []\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 13210,\n" +
//            "            \"name\": \"ty_mix_app_party\",\n" +
//            "            \"comment\": \"微服务合并-派对AS相关服务\",\n" +
//            "            \"env\": 0,\n" +
//            "            \"create_time\": 1693277600.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_mix_app_party\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1693277600.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 1914,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 2,\n" +
//            "            \"port\": 0,\n" +
//            "            \"nginx_confid\": -1,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 36,\n" +
//            "            \"gitlab_id\": 0,\n" +
//            "            \"gitlab_url\": \"\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 0,\n" +
//            "            \"lastuse_image\": 36,\n" +
//            "            \"docker_count\": 10,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"merge\",\n" +
//            "            \"build_mode\": \"pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 0,\n" +
//            "            \"service_port\": 0,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 1914,\n" +
//            "                \"user\": \"甘剑东\",\n" +
//            "                \"user_name\": \"ganjiandong\",\n" +
//            "                \"email\": \"ganjiandong@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [\n" +
//            "                {\n" +
//            "                    \"id\": 142,\n" +
//            "                    \"main_project_id\": 13210,\n" +
//            "                    \"main_project_name\": \"ty_mix_app_party\",\n" +
//            "                    \"merged_project_name\": \"ty_app_group\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:tiya/ty-app-group.git\",\n" +
//            "                    \"main\": \"fm.tiya.app.group.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                },\n" +
//            "                {\n" +
//            "                    \"id\": 143,\n" +
//            "                    \"main_project_id\": 13210,\n" +
//            "                    \"main_project_name\": \"ty_mix_app_party\",\n" +
//            "                    \"merged_project_name\": \"ty_app_alter\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/ty-app-alter.git\",\n" +
//            "                    \"main\": \"fm.tiya.app.alter.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                },\n" +
//            "                {\n" +
//            "                    \"id\": 144,\n" +
//            "                    \"main_project_id\": 13210,\n" +
//            "                    \"main_project_name\": \"ty_mix_app_party\",\n" +
//            "                    \"merged_project_name\": \"ty_app_party_core\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-app-party-core.git\",\n" +
//            "                    \"main\": \"fm.zhiya.app.party.core.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_relation_data\": [],\n" +
//            "            \"ports\": [],\n" +
//            "            \"service_ports\": []\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 11421,\n" +
//            "            \"name\": \"ty_dc_data_warehouse\",\n" +
//            "            \"comment\": \"Tiya数仓\",\n" +
//            "            \"env\": 0,\n" +
//            "            \"create_time\": 1577093197.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_dc_data_warehouse\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1577093197.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 298,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 2,\n" +
//            "            \"port\": 0,\n" +
//            "            \"nginx_confid\": -1,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 2,\n" +
//            "            \"gitlab_id\": 3874,\n" +
//            "            \"gitlab_url\": \"git@gitlab.lizhi.fm:zhiya/zy-dc-data-warehouse.git\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 7,\n" +
//            "            \"lastuse_image\": 36,\n" +
//            "            \"docker_count\": 20,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"maven\",\n" +
//            "            \"build_mode\": \"build,docker,pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 1,\n" +
//            "            \"service_port\": 25033,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 298,\n" +
//            "                \"user\": \"李孝赢\",\n" +
//            "                \"user_name\": \"lixiaoying\",\n" +
//            "                \"email\": \"lixiaoying@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [],\n" +
//            "            \"merge_relation_data\": [],\n" +
//            "            \"ports\": [],\n" +
//            "            \"service_ports\": []\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 11679,\n" +
//            "            \"name\": \"ty_dc_data_warehouse_scheduled\",\n" +
//            "            \"comment\": \"\",\n" +
//            "            \"env\": 0,\n" +
//            "            \"create_time\": 1585212708.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_dc_data_warehouse_scheduled\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1585212707.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 298,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 2,\n" +
//            "            \"port\": 0,\n" +
//            "            \"nginx_confid\": -1,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 2,\n" +
//            "            \"gitlab_id\": 3874,\n" +
//            "            \"gitlab_url\": \"git@gitlab.lizhi.fm:zhiya/zy-dc-data-warehouse.git\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 7,\n" +
//            "            \"lastuse_image\": 36,\n" +
//            "            \"docker_count\": 30,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"maven\",\n" +
//            "            \"build_mode\": \"build,docker,pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 0,\n" +
//            "            \"service_port\": 0,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 298,\n" +
//            "                \"user\": \"李孝赢\",\n" +
//            "                \"user_name\": \"lixiaoying\",\n" +
//            "                \"email\": \"lixiaoying@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [],\n" +
//            "            \"merge_relation_data\": [\n" +
//            "                {\n" +
//            "                    \"id\": 156,\n" +
//            "                    \"main_project_id\": 13204,\n" +
//            "                    \"main_project_name\": \"ty_mix_dc_data_warehouse\",\n" +
//            "                    \"merged_project_name\": \"ty_dc_data_warehouse_scheduled\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-dc-data-warehouse-scheduled.git\",\n" +
//            "                    \"main\": \"fm.zhiya.data.warehouse.control.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"ports\": [],\n" +
//            "            \"service_ports\": []\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 12453,\n" +
//            "            \"name\": \"ty_dc_group\",\n" +
//            "            \"comment\": \"tiya 群组业务dc项目\",\n" +
//            "            \"env\": 0,\n" +
//            "            \"create_time\": 1614581975.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_dc_group\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1614581975.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 1321,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 2,\n" +
//            "            \"port\": 0,\n" +
//            "            \"nginx_confid\": -1,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 2,\n" +
//            "            \"gitlab_id\": 6479,\n" +
//            "            \"gitlab_url\": \"git@gitlab.lizhi.fm:tiya/ty-dc-group.git\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 0,\n" +
//            "            \"lastuse_image\": 2,\n" +
//            "            \"docker_count\": 10,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"maven\",\n" +
//            "            \"build_mode\": \"pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 0,\n" +
//            "            \"service_port\": 0,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 1321,\n" +
//            "                \"user\": \"曾轼麟\",\n" +
//            "                \"user_name\": \"zengshilin\",\n" +
//            "                \"email\": \"zengshilin@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [],\n" +
//            "            \"merge_relation_data\": [\n" +
//            "                {\n" +
//            "                    \"id\": 137,\n" +
//            "                    \"main_project_id\": 13208,\n" +
//            "                    \"main_project_name\": \"ty_mix_dc_party_core\",\n" +
//            "                    \"merged_project_name\": \"ty_dc_group\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:tiya/ty-dc-group.git\",\n" +
//            "                    \"main\": \"fm.tiya.group.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"ports\": [],\n" +
//            "            \"service_ports\": []\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 12454,\n" +
//            "            \"name\": \"ty_app_group\",\n" +
//            "            \"comment\": \"tiya 群组业务 appServer 服务\",\n" +
//            "            \"env\": 0,\n" +
//            "            \"create_time\": 1614582308.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_app_group\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1614582307.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 1321,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 2,\n" +
//            "            \"port\": 0,\n" +
//            "            \"nginx_confid\": -1,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 2,\n" +
//            "            \"gitlab_id\": 6481,\n" +
//            "            \"gitlab_url\": \"git@gitlab.lizhi.fm:tiya/ty-app-group.git\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 0,\n" +
//            "            \"lastuse_image\": 2,\n" +
//            "            \"docker_count\": 10,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"maven\",\n" +
//            "            \"build_mode\": \"pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 0,\n" +
//            "            \"service_port\": 0,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 1321,\n" +
//            "                \"user\": \"曾轼麟\",\n" +
//            "                \"user_name\": \"zengshilin\",\n" +
//            "                \"email\": \"zengshilin@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [],\n" +
//            "            \"merge_relation_data\": [\n" +
//            "                {\n" +
//            "                    \"id\": 142,\n" +
//            "                    \"main_project_id\": 13210,\n" +
//            "                    \"main_project_name\": \"ty_mix_app_party\",\n" +
//            "                    \"merged_project_name\": \"ty_app_group\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:tiya/ty-app-group.git\",\n" +
//            "                    \"main\": \"fm.tiya.app.group.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"ports\": [],\n" +
//            "            \"service_ports\": []\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 11178,\n" +
//            "            \"name\": \"ty_web_marketing\",\n" +
//            "            \"comment\": \"tiya-营销web项目\",\n" +
//            "            \"env\": 0,\n" +
//            "            \"create_time\": 1572941585.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_web_marketing\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1572941584.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 2286,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 2,\n" +
//            "            \"port\": 7518,\n" +
//            "            \"nginx_confid\": 1318,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 2,\n" +
//            "            \"gitlab_id\": 2642,\n" +
//            "            \"gitlab_url\": \"git@gitlab.lizhi.fm:zhiya/zy-web-marketing.git\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 7,\n" +
//            "            \"lastuse_image\": 2,\n" +
//            "            \"docker_count\": 20,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"maven\",\n" +
//            "            \"build_mode\": \"build,docker,pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 1,\n" +
//            "            \"service_port\": 24815,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 2286,\n" +
//            "                \"user\": \"秦羽纶\",\n" +
//            "                \"user_name\": \"qinyuguan\",\n" +
//            "                \"email\": \"qinyuguan@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [],\n" +
//            "            \"merge_relation_data\": [\n" +
//            "                {\n" +
//            "                    \"id\": 179,\n" +
//            "                    \"main_project_id\": 13205,\n" +
//            "                    \"main_project_name\": \"ty_mix_web_serivce\",\n" +
//            "                    \"merged_project_name\": \"ty_web_marketing\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-web-marketing.git\",\n" +
//            "                    \"main\": \"fm.zhiya.web.marketing.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"ports\": [\n" +
//            "                7518\n" +
//            "            ],\n" +
//            "            \"service_ports\": [\n" +
//            "                {\n" +
//            "                    \"port\": 7518,\n" +
//            "                    \"service_port\": 24815\n" +
//            "                }\n" +
//            "            ]\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 12721,\n" +
//            "            \"name\": \"tiya_web_node_bumper_ball_service\",\n" +
//            "            \"comment\": \"tiya客户端同步帧小游戏项目服务。\",\n" +
//            "            \"env\": 0,\n" +
//            "            \"create_time\": 1635734078.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/tiya_web_node_bumper_ball_service\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1635734078.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 51,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 2,\n" +
//            "            \"port\": 7738,\n" +
//            "            \"nginx_confid\": -1,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 1,\n" +
//            "            \"gitlab_id\": 7804,\n" +
//            "            \"gitlab_url\": \"git@gitlab.lizhi.fm:huangshuai/bumper_ball_service.git\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 0,\n" +
//            "            \"lastuse_image\": 15,\n" +
//            "            \"docker_count\": 10,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"maven\",\n" +
//            "            \"build_mode\": \"pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 1,\n" +
//            "            \"service_port\": 25022,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 51,\n" +
//            "                \"user\": \"陈秋林\",\n" +
//            "                \"user_name\": \"chenqiulin\",\n" +
//            "                \"email\": \"chenqiulin@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 59,\n" +
//            "                    \"name\": \"美国Tiya预发\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pre\"\n" +
//            "                },\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [],\n" +
//            "            \"merge_relation_data\": [],\n" +
//            "            \"ports\": [\n" +
//            "                7738\n" +
//            "            ],\n" +
//            "            \"service_ports\": [\n" +
//            "                {\n" +
//            "                    \"port\": 7738,\n" +
//            "                    \"service_port\": 25022\n" +
//            "                }\n" +
//            "            ]\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 10686,\n" +
//            "            \"name\": \"ty_web_admin\",\n" +
//            "            \"comment\": \"tiya运营后台web服务\",\n" +
//            "            \"env\": 7,\n" +
//            "            \"create_time\": 1562235802.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_web_admin\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1672821377.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 298,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 0,\n" +
//            "            \"port\": 7713,\n" +
//            "            \"nginx_confid\": 967,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 2,\n" +
//            "            \"gitlab_id\": 1344,\n" +
//            "            \"gitlab_url\": \"git@gitlab.lizhi.fm:zhiya/zy-web-admin.git\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 7,\n" +
//            "            \"lastuse_image\": 2,\n" +
//            "            \"docker_count\": 20,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"maven\",\n" +
//            "            \"build_mode\": \"build,docker,pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 1,\n" +
//            "            \"service_port\": 24786,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 298,\n" +
//            "                \"user\": \"李孝赢\",\n" +
//            "                \"user_name\": \"lixiaoying\",\n" +
//            "                \"email\": \"lixiaoying@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [],\n" +
//            "            \"merge_relation_data\": [\n" +
//            "                {\n" +
//            "                    \"id\": 197,\n" +
//            "                    \"main_project_id\": 13099,\n" +
//            "                    \"main_project_name\": \"ty_mix_service\",\n" +
//            "                    \"merged_project_name\": \"ty_web_admin\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-web-admin.git\",\n" +
//            "                    \"main\": \"fm.zhiya.admin.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"ports\": [\n" +
//            "                7713\n" +
//            "            ],\n" +
//            "            \"service_ports\": [\n" +
//            "                {\n" +
//            "                    \"port\": 7713,\n" +
//            "                    \"service_port\": 24786\n" +
//            "                }\n" +
//            "            ]\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 11203,\n" +
//            "            \"name\": \"ty_dc_activity\",\n" +
//            "            \"comment\": \"tiya活动dc服务\",\n" +
//            "            \"env\": 0,\n" +
//            "            \"create_time\": 1573033357.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_dc_activity\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1573033356.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 2286,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 2,\n" +
//            "            \"port\": 0,\n" +
//            "            \"nginx_confid\": -1,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 2,\n" +
//            "            \"gitlab_id\": 1088,\n" +
//            "            \"gitlab_url\": \"git@gitlab.lizhi.fm:zhiya/zy-dc-activity.git\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 7,\n" +
//            "            \"lastuse_image\": 2,\n" +
//            "            \"docker_count\": 20,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"maven\",\n" +
//            "            \"build_mode\": \"build,docker,pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 0,\n" +
//            "            \"service_port\": 0,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 2286,\n" +
//            "                \"user\": \"秦羽纶\",\n" +
//            "                \"user_name\": \"qinyuguan\",\n" +
//            "                \"email\": \"qinyuguan@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [],\n" +
//            "            \"merge_relation_data\": [\n" +
//            "                {\n" +
//            "                    \"id\": 198,\n" +
//            "                    \"main_project_id\": 13099,\n" +
//            "                    \"main_project_name\": \"ty_mix_service\",\n" +
//            "                    \"merged_project_name\": \"ty_dc_activity\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-dc-activity.git\",\n" +
//            "                    \"main\": \"fm.zhiya.activity.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"ports\": [],\n" +
//            "            \"service_ports\": []\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 10952,\n" +
//            "            \"name\": \"ty_dc_user_behavior\",\n" +
//            "            \"comment\": \"Tiya用户行为dc服务\",\n" +
//            "            \"env\": 0,\n" +
//            "            \"create_time\": 1567737744.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_dc_user_behavior\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1567737744.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 298,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 2,\n" +
//            "            \"port\": 0,\n" +
//            "            \"nginx_confid\": -1,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 2,\n" +
//            "            \"gitlab_id\": 3180,\n" +
//            "            \"gitlab_url\": \"git@gitlab.lizhi.fm:zhiya/zy-dc-user-behavior.git\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 7,\n" +
//            "            \"lastuse_image\": 2,\n" +
//            "            \"docker_count\": 20,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"maven\",\n" +
//            "            \"build_mode\": \"build,docker,pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 0,\n" +
//            "            \"service_port\": 0,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 298,\n" +
//            "                \"user\": \"李孝赢\",\n" +
//            "                \"user_name\": \"lixiaoying\",\n" +
//            "                \"email\": \"lixiaoying@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [],\n" +
//            "            \"merge_relation_data\": [\n" +
//            "                {\n" +
//            "                    \"id\": 151,\n" +
//            "                    \"main_project_id\": 13207,\n" +
//            "                    \"main_project_name\": \"ty_mix_dc_common\",\n" +
//            "                    \"merged_project_name\": \"ty_dc_user_behavior\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-dc-user-behavior.git\",\n" +
//            "                    \"main\": \"fm.zhiya.user.behavior.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"ports\": [],\n" +
//            "            \"service_ports\": []\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 12499,\n" +
//            "            \"name\": \"ty_dc_alter\",\n" +
//            "            \"comment\": \"tiya 业务变更处理dc服务\",\n" +
//            "            \"env\": 0,\n" +
//            "            \"create_time\": 1619058367.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_dc_alter\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1619058367.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 51,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 2,\n" +
//            "            \"port\": 0,\n" +
//            "            \"nginx_confid\": -1,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 2,\n" +
//            "            \"gitlab_id\": 6678,\n" +
//            "            \"gitlab_url\": \"git@gitlab.lizhi.fm:zhiya/ty-dc-alter.git\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 0,\n" +
//            "            \"lastuse_image\": 2,\n" +
//            "            \"docker_count\": 10,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"maven\",\n" +
//            "            \"build_mode\": \"upload,build,docker,pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 0,\n" +
//            "            \"service_port\": 0,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 51,\n" +
//            "                \"user\": \"陈秋林\",\n" +
//            "                \"user_name\": \"chenqiulin\",\n" +
//            "                \"email\": \"chenqiulin@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [],\n" +
//            "            \"merge_relation_data\": [\n" +
//            "                {\n" +
//            "                    \"id\": 135,\n" +
//            "                    \"main_project_id\": 13208,\n" +
//            "                    \"main_project_name\": \"ty_mix_dc_party_core\",\n" +
//            "                    \"merged_project_name\": \"ty_dc_alter\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/ty-dc-alter.git\",\n" +
//            "                    \"main\": \"fm.tiya.alter.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"ports\": [],\n" +
//            "            \"service_ports\": []\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 12500,\n" +
//            "            \"name\": \"ty_app_alter\",\n" +
//            "            \"comment\": \"tiya 业务变更处理as服务\",\n" +
//            "            \"env\": 0,\n" +
//            "            \"create_time\": 1619058383.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_app_alter\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1619058383.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 51,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 2,\n" +
//            "            \"port\": 0,\n" +
//            "            \"nginx_confid\": -1,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 2,\n" +
//            "            \"gitlab_id\": 6679,\n" +
//            "            \"gitlab_url\": \"git@gitlab.lizhi.fm:zhiya/ty-app-alter.git\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 0,\n" +
//            "            \"lastuse_image\": 2,\n" +
//            "            \"docker_count\": 10,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"maven\",\n" +
//            "            \"build_mode\": \"upload,build,docker,pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 0,\n" +
//            "            \"service_port\": 0,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 51,\n" +
//            "                \"user\": \"陈秋林\",\n" +
//            "                \"user_name\": \"chenqiulin\",\n" +
//            "                \"email\": \"chenqiulin@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [],\n" +
//            "            \"merge_relation_data\": [\n" +
//            "                {\n" +
//            "                    \"id\": 143,\n" +
//            "                    \"main_project_id\": 13210,\n" +
//            "                    \"main_project_name\": \"ty_mix_app_party\",\n" +
//            "                    \"merged_project_name\": \"ty_app_alter\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/ty-app-alter.git\",\n" +
//            "                    \"main\": \"fm.tiya.app.alter.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"ports\": [],\n" +
//            "            \"service_ports\": []\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"id\": 11261,\n" +
//            "            \"name\": \"ty_dc_mall\",\n" +
//            "            \"comment\": \"Tiya\",\n" +
//            "            \"env\": 0,\n" +
//            "            \"create_time\": 1574316780.0,\n" +
//            "            \"bin_path\": \"/usr/local/lizhi/ty_dc_mall\",\n" +
//            "            \"project_runcmd\": null,\n" +
//            "            \"status\": 0,\n" +
//            "            \"drop_time\": 1574316780.0,\n" +
//            "            \"project_group\": null,\n" +
//            "            \"user_id\": 1200,\n" +
//            "            \"project_wiki\": null,\n" +
//            "            \"log_status\": 0,\n" +
//            "            \"type\": 0,\n" +
//            "            \"log_type\": 2,\n" +
//            "            \"port\": 0,\n" +
//            "            \"nginx_confid\": -1,\n" +
//            "            \"wiki\": null,\n" +
//            "            \"default_image\": 2,\n" +
//            "            \"gitlab_id\": 3634,\n" +
//            "            \"gitlab_url\": \"git@gitlab.lizhi.fm:zhiya/zy-dc-mall.git\",\n" +
//            "            \"docker_switch\": 0,\n" +
//            "            \"external_ips\": \"\",\n" +
//            "            \"runtime_env_id\": 7,\n" +
//            "            \"lastuse_image\": 2,\n" +
//            "            \"docker_count\": 20,\n" +
//            "            \"deploy_mode\": 1,\n" +
//            "            \"pipeline_type\": \"maven\",\n" +
//            "            \"build_mode\": \"build,docker,pipeline\",\n" +
//            "            \"language_version\": \"java1.8\",\n" +
//            "            \"service_mode\": 0,\n" +
//            "            \"service_port\": 0,\n" +
//            "            \"business_id\": 13,\n" +
//            "            \"check_snapshot\": 0,\n" +
//            "            \"non_master_allowd\": 0,\n" +
//            "            \"user\": {\n" +
//            "                \"user_id\": 1200,\n" +
//            "                \"user\": \"陈欣煜\",\n" +
//            "                \"user_name\": \"chenxinyu\",\n" +
//            "                \"email\": \"chenxinyu@vocalbeats.com\"\n" +
//            "            },\n" +
//            "            \"business\": {\n" +
//            "                \"id\": 13,\n" +
//            "                \"name\": \"tiya\",\n" +
//            "                \"comment\": \"TIYA\",\n" +
//            "                \"department\": \"业务技术中心/TIYA技术\",\n" +
//            "                \"department_id\": 547,\n" +
//            "                \"admin_id\": 51,\n" +
//            "                \"state\": 1,\n" +
//            "                \"sns_token\": null,\n" +
//            "                \"lzce_id\": \"5241869596083882623\"\n" +
//            "            },\n" +
//            "            \"envs\": [\n" +
//            "                {\n" +
//            "                    \"id\": 58,\n" +
//            "                    \"name\": \"美国Tiya线上\",\n" +
//            "                    \"business_env\": \"tiya\",\n" +
//            "                    \"deploy_env\": \"pro\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"merge_data\": [],\n" +
//            "            \"merge_relation_data\": [\n" +
//            "                {\n" +
//            "                    \"id\": 155,\n" +
//            "                    \"main_project_id\": 13204,\n" +
//            "                    \"main_project_name\": \"ty_mix_dc_data_warehouse\",\n" +
//            "                    \"merged_project_name\": \"ty_dc_mall\",\n" +
//            "                    \"git\": \"git@gitlab.lizhi.fm:zhiya/zy-dc-mall.git\",\n" +
//            "                    \"main\": \"fm.zhiya.mall.Bootstrap\",\n" +
//            "                    \"branch\": \"master\"\n" +
//            "                }\n" +
//            "            ],\n" +
//            "            \"ports\": [],\n" +
//            "            \"service_ports\": []\n" +
//            "        }\n" +
//            "    ]";

}

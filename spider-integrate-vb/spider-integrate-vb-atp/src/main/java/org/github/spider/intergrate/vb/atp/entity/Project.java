package org.github.spider.intergrate.vb.atp.entity;

import java.util.List;

public class Project {
    private int id;
    private String name;
    private String comment;
    private int env;
    private double create_time;
    private int log_status;
    private int type;
    private int port;
    private int nginx_confid;
    private String wiki;
    private int default_image;
    private String gitlab_url;
    private String external_ips;
    private int docker_count;
    private String build_mode;
    private int service_mode;
    private int service_port;

    private User user;
    private Business business;
    private List<MergeRelationData> merge_relation_data;
    private List<MergeData> merge_data;
    private List<Integer> ports;
    private List<ServicePort> service_ports;


    // 内部类 MergeData
    public static class MergeData {
        private int id;
        private int main_project_id;
        private String main_project_name;
        private String merged_project_name;
        private String git;
        private String main;
        private String branch;

        public MergeData(int id, int main_project_id, String main_project_name, String merged_project_name, String git, String main, String branch) {
            this.id = id;
            this.main_project_id = main_project_id;
            this.main_project_name = main_project_name;
            this.merged_project_name = merged_project_name;
            this.git = git;
            this.main = main;
            this.branch = branch;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMain_project_id() {
            return main_project_id;
        }

        public void setMain_project_id(int main_project_id) {
            this.main_project_id = main_project_id;
        }

        public String getMain_project_name() {
            return main_project_name;
        }

        public void setMain_project_name(String main_project_name) {
            this.main_project_name = main_project_name;
        }

        public String getMerged_project_name() {
            return merged_project_name;
        }

        public void setMerged_project_name(String merged_project_name) {
            this.merged_project_name = merged_project_name;
        }

        public String getGit() {
            return git;
        }

        public void setGit(String git) {
            this.git = git;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public String getBranch() {
            return branch;
        }

        public void setBranch(String branch) {
            this.branch = branch;
        }
    }
    public static class User {
        private String user;

        public User(String user) {
            this.user = user;
        }

        public String getUser() {
            return user;
        }
        public void setUser(String user) {
            this.user = user;
        }
    }

    // 内部类 Business
    public static class Business {
        private String comment;
        private String department;

        public Business(String comment, String department) {
            this.comment = comment;
            this.department = department;
        }

        // Getters and Setters
        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }
    }



    // 内部类 MergeRelationData
    public static class MergeRelationData {
        private int id;
        private int main_project_id;
        private String main_project_name;
        private String merged_project_name;
        private String git;

        public MergeRelationData(int id, int main_project_id, String main_project_name, String merged_project_name, String git) {
            this.id = id;
            this.main_project_id = main_project_id;
            this.main_project_name = main_project_name;
            this.merged_project_name = merged_project_name;
            this.git = git;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMain_project_id() {
            return main_project_id;
        }

        public void setMain_project_id(int main_project_id) {
            this.main_project_id = main_project_id;
        }

        public String getMain_project_name() {
            return main_project_name;
        }

        public void setMain_project_name(String main_project_name) {
            this.main_project_name = main_project_name;
        }

        public String getMerged_project_name() {
            return merged_project_name;
        }

        public void setMerged_project_name(String merged_project_name) {
            this.merged_project_name = merged_project_name;
        }

        public String getGit() {
            return git;
        }

        public void setGit(String git) {
            this.git = git;
        }

        // Getters and Setters
    }

    // 内部类 ServicePort
    public static class ServicePort {
        private int port;
        private int service_port;

        public ServicePort(int port, int service_port) {
            this.port = port;
            this.service_port = service_port;
        }

        // Getters and Setters

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public int getService_port() {
            return service_port;
        }

        public void setService_port(int service_port) {
            this.service_port = service_port;
        }
    }

    // Getters and Setters for Project fields


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getEnv() {
        return env;
    }

    public void setEnv(int env) {
        this.env = env;
    }

    public double getCreate_time() {
        return create_time;
    }

    public void setCreate_time(double create_time) {
        this.create_time = create_time;
    }

    public int getLog_status() {
        return log_status;
    }

    public void setLog_status(int log_status) {
        this.log_status = log_status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getNginx_confid() {
        return nginx_confid;
    }

    public void setNginx_confid(int nginx_confid) {
        this.nginx_confid = nginx_confid;
    }

    public String getWiki() {
        return wiki;
    }

    public void setWiki(String wiki) {
        this.wiki = wiki;
    }

    public int getDefault_image() {
        return default_image;
    }

    public void setDefault_image(int default_image) {
        this.default_image = default_image;
    }

    public String getGitlab_url() {
        return gitlab_url;
    }

    public void setGitlab_url(String gitlab_url) {
        this.gitlab_url = gitlab_url;
    }

    public String getExternal_ips() {
        return external_ips;
    }

    public void setExternal_ips(String external_ips) {
        this.external_ips = external_ips;
    }

    public int getDocker_count() {
        return docker_count;
    }

    public void setDocker_count(int docker_count) {
        this.docker_count = docker_count;
    }

    public String getBuild_mode() {
        return build_mode;
    }

    public void setBuild_mode(String build_mode) {
        this.build_mode = build_mode;
    }

    public int getService_mode() {
        return service_mode;
    }

    public void setService_mode(int service_mode) {
        this.service_mode = service_mode;
    }

    public int getService_port() {
        return service_port;
    }

    public void setService_port(int service_port) {
        this.service_port = service_port;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public List<MergeRelationData> getMerge_relation_data() {
        return merge_relation_data;
    }

    public void setMerge_relation_data(List<MergeRelationData> merge_relation_data) {
        this.merge_relation_data = merge_relation_data;
    }

    public List<MergeData> getMerge_data() {
        return merge_data;
    }

    public void setMerge_data(List<MergeData> merge_data) {
        this.merge_data = merge_data;
    }

    public List<Integer> getPorts() {
        return ports;
    }

    public void setPorts(List<Integer> ports) {
        this.ports = ports;
    }

    public List<ServicePort> getService_ports() {
        return service_ports;
    }

    public void setService_ports(List<ServicePort> service_ports) {
        this.service_ports = service_ports;
    }
}

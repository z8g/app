package com.zxy97.ssm.config;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Config {

    //不可变对象
    private final String jdbcDriverClass;
    private final String jdbcUrl;
    private final String jdbcUsername;
    private final String jdbcPassword;
    private final String src;
    private final String packageRoot;
    private final List<String> classNameList;
    private final List<String> interceptorList;

    private Config(ConfigBuilder builder) {
        jdbcDriverClass = builder.jdbcDriverClass;
        jdbcUrl = builder.jdbcUrl;
        jdbcUsername = builder.jdbcUsername;
        jdbcPassword = builder.jdbcPassword;
        src = builder.src;
        packageRoot = builder.packageRoot;
        classNameList = builder.classNameList;
        interceptorList = builder.interceptorList;
    }

    public String getJdbcDriverClass() {
        return jdbcDriverClass;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public String getJdbcUsername() {
        return jdbcUsername;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public String getSrc() {
        return src;
    }

    public String getPackageRoot() {
        return packageRoot;
    }

    public List<String> getClassNameList() {
        return classNameList;
    }

    public List<String> getInterceptorList() {
        return interceptorList;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Config:")
                .append("\n\tjdbcDriverClass=").append(jdbcDriverClass)
                .append("\n\tjdbcUrl=").append(jdbcUrl)
                .append("\n\tjdbcUsername=").append(jdbcUsername)
                .append("\n\tjdbcPassword=").append(jdbcPassword)
                .append("\n\tsrc=").append(src)
                .append("\n\tpackageRoot=").append(packageRoot)
                .append("\n\tclassNameList=").append(classNameList)
                .append("\n\tinterceptorList=").append(interceptorList)
                .append("\n");
        return result.toString();
    }

    public static class ConfigBuilder implements ObjectBuilder<Config> {

        private String jdbcDriverClass;
        private String jdbcUrl;
        private String jdbcUsername;
        private String jdbcPassword;
        private String src;
        private String packageRoot;
        private List<String> classNameList;
        private List<String> interceptorList;

        public ConfigBuilder jdbcDriverClass(String jdbcDriverClass) {
            this.jdbcDriverClass = jdbcDriverClass;
            return this;
        }

        public ConfigBuilder jdbcUrl(String jdbcUrl) {
            this.jdbcUrl = jdbcUrl;
            return this;
        }

        public ConfigBuilder jdbcUsername(String jdbcUsername) {
            this.jdbcUsername = jdbcUsername;
            return this;
        }

        public ConfigBuilder jdbcPassword(String jdbcPassword) {
            this.jdbcPassword = jdbcPassword;
            return this;
        }

        public ConfigBuilder packageRoot(String packageRoot) {
            this.packageRoot = packageRoot;
            return this;
        }

        public ConfigBuilder src(String src) {
            this.src = src;
            return this;
        }

        public ConfigBuilder classNameList(String classNameList) {
            this.classNameList = split(classNameList);

            return this;
        }

        public ConfigBuilder interceptorList(String interceptorList) {
            this.interceptorList = split(interceptorList);
            return this;
        }

        @Override
        public Config build() {
            return new Config(this);
        }

        private static List<String> split(String splitList) {
            List<String> result = new LinkedList<>();
            if (splitList != null && splitList.trim().length() > 0) {
                String[] strs = splitList.split(";");
                result.addAll(Arrays.asList(strs));
            }
            return result;
        }

    }

}

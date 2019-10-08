package com.zxy97.ssm.template;

import com.zxy97.ssm.config.Config;

public class JdbcPropertiesTemplate extends Template {

    private String getContent(Config config) {
        StringBuilder result = new StringBuilder();
        result.append("# jdbc config info")
                .append("\njdbc.driver=").append(config.getJdbcDriverClass())
                .append("\njdbc.url=").append(config.getJdbcUrl())
                .append("\njdbc.username=").append(config.getJdbcUsername())
                .append("\njdbc.password=").append(config.getJdbcPassword());
        return result.toString();
    }

    private String getFilePath(Config config) {
        StringBuilder result = new StringBuilder();
        result.append(config.getSrc()).append("/jdbc.properties");
        return result.toString();
    }

    @Override
    public void createFile(Config config) {
        write(getFilePath(config), getContent(config));
    }

}

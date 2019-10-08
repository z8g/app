package jalaxy.model;

import java.util.List;

/**
 *
 * @author hadoop
 */
public class Tool {

    String command;//命令，需要解析
    List<Input> inputs;//输入
    List<Output> outputs;//输出
    String help;//使用markdown语法

    @Override
    public String toString() {
        return "Tool{" + "command=" + command + ", inputs=" + inputs + ", outputs=" + outputs + ", help=" + help + '}';
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public List<Input> getInputs() {
        return inputs;
    }

    public void setInputs(List<Input> inputs) {
        this.inputs = inputs;
    }

    public List<Output> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<Output> outputs) {
        this.outputs = outputs;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public static class Input {

        String name;
        String type;
        Object value;//value 如果是文件，则给其ID, 否则给其值

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Input{" + "name=" + name + ", type=" + type + ", value=" + value + '}';
        }
    }

    public static class Output {

        String name;
        String type;
        Object value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("Output{"
                    + "name=%s\n"
                    + "type=%s\n"
                    + "value=%s\n"
                    + "}", name, type, value);
        }

    }
}

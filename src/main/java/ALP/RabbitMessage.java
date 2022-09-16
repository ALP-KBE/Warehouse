package ALP;

import java.io.Serializable;

public class RabbitMessage implements Serializable {
    private String type;
    private String value;
    public RabbitMessage()  {
        this.type="";
        this.value="";
    }
    public RabbitMessage(String type, String value) {
        this.type=type;
        this.value=value;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}

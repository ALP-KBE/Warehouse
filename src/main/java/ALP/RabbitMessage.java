package ALP;

import java.io.Serializable;

public class RabbitMessage implements Serializable {
    private String type;
    private Serializable value;
    private Serializable additionalField;

    public RabbitMessage() {
        this.type = "";
        this.value = "";
    }

    public RabbitMessage(String type, Serializable value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public Serializable getValue() {
        return value;
    }

    public void setAdditionalField(Serializable additionalField) {
        this.additionalField = additionalField;
    }

    public Serializable getAdditionalField() {
        return additionalField;
    }
}

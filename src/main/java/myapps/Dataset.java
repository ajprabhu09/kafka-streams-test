package myapps;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data

public class Dataset {
    private String name;
    private String namespace;
    public Dataset(String name, String namespace) {
        this.name = name;
        this.namespace = namespace;
    }
}

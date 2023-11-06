package yuzhe.com.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class URL implements Serializable {

    private String hostname;
    private Integer port;

    public URL(String hostname, Integer port) {
        this.hostname = hostname;
        this.port = port;
    }
}

package yuzhe.com.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Invocation implements Serializable {

    private String interfaceName;
    private String methodName;
    private Class[] parameterTypes;
    private Object[] parameters;

    public Invocation(String interfaceName, String methodName, Class[] parameterTypes, Object[] parameters) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.parameters = parameters;
    }
}

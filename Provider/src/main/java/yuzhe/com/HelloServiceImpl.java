package yuzhe.com;

public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Hello: " + name;
    }
}

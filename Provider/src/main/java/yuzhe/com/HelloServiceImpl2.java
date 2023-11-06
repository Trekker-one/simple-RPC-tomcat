package yuzhe.com;

public class HelloServiceImpl2 implements HelloService{

    @Override
    public String sayHello(String name) {
        return "Hello 2.0: " + name;
    }
}

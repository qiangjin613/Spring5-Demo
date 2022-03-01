import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class JDKProxy {
    public static void main(String[] args) {
        // 带增强的类
        Class<?>[] interfaces = new Class[] {xxDao.class};
        xxDaoImpl daoImpl = new xxDaoImpl();

        // 使用 JDK 动态代理对方法进行增强
        xxDao xxDao = (xxDao) Proxy.newProxyInstance(JDKProxy.class.getClassLoader(),
                interfaces,
                new MyInvocationHandler(daoImpl));

        // 调用被增强对象的方法
        int result = xxDao.add(1, 2);
        System.out.println(result);
    }
}

class MyInvocationHandler implements InvocationHandler {

    /**
     * 待增强的对象
     */
    private Object obj;

    public MyInvocationHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 1. Before
        System.out.println("方法执行之前。。。" + method.getName() + ":" + Arrays.toString(args));

        // 2. 执行方法：可以在这里进行判断，对特定的方法进行增强
        Object retuen = method.invoke(obj, args);

        // 3. After
        System.out.println("方法执行之后。。。" + obj);

        return retuen;
    }
}

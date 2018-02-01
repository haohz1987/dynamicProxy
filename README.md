# dynamicProxy
>retrofit是一个解耦性非常高的网络请求框架，最近在研究的时候发现了动态代理这个非常强大且实用的技术，
    这篇文章将作为retrofit的前置知识
#动态代理该如何使用
>在java的动态代理机制中，有两个重要的类和接口，一个是 InvocationHandler(Interface)、另一个则是 
    Proxy(Class)，这一个类和接口是实现我们动态代理所必须用到的。    
    每一个动态代理类都必须要实现InvocationHandler这个接口（代码中的中介），并且每个代理类的实例都关联到了
    一个handler，当我们通过代理对象调用一个方法的时候，这个方法的调用就会被转发为由InvocationHandler
    这个接口的 invoke（对方法的增强就写在这里面） 方法来进行调用。    
    Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    我们看到这个方法一共接受三个参数，那么这三个参数分别代表什么呢？    
    Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    //proxy:　　指代我们所代理的那个真实对象
    //method:　　指代的是我们所要调用真实对象的某个方法的Method对象
    //args:　　指代的是调用真实对象某个方法时接受的参数
    接下来我们来看看Proxy这个类    
    public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces,  InvocationHandler h)  throws IllegalArgumentException
    Proxy这个类的作用就是用来动态创建一个代理对象的类，它提供了许多的方法，但是我们用的最多的就是 newProxyInstance 这个方法：    
    public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces,  InvocationHandler h)  throws IllegalArgumentException
    这个方法的作用就是得到一个动态的代理对象，其接收三个参数，我们来看看这三个参数所代表的含义    
    public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h) throws IllegalArgumentException    
    //loader:　　一个ClassLoader对象，定义了由哪个ClassLoader对象来对生成的代理对象进行加载
    //interfaces:　　一个Interface对象的数组，表示的是我将要给我需要代理的对象提供一组什么接口，如果我提供了一组接口给它，那么这个代理对象就宣称实现了该接口(多态)，这样我就能调用这组接口中的方法了
    //h:　　一个InvocationHandler对象，表示的是当我这个动态代理对象在调用方法的时候，会关联到哪一个InvocationHandler对象上
    这样一来，结合上面给出的代码，我们就可以明白动态代理的使用方法了

#动态代理的局限性
>从动态代理的使用方法中我们看到其实可以被增强的方法都是实现了借口的（不实现借口的public方法也可以通过继承被代理类来使用），代码中的HouseOwner继承了RentHouse 。而对于private方法JDK的动态代理无能为力！
    以上的动态代理是JDK的，对于java工程还有大名鼎鼎的CGLib，但遗憾的是CGLib并不能在android中使用，android虚拟机相对与jvm还是有区别的。
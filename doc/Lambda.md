# Lambda

> Java8新特性：Lambda表达式（函数）
>
> 让代码更简洁更紧凑
>
> 又称`糖式语法`，不是java原生支持的语法

## 什么是Lambda表达式

> 新建一个`Factory`接口

```java
@FunctionalInterface
public interface Factory {
    User getUser();
}
```

> 新建一个`User`类,这里用了`lombok`的注解

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
    private int age;
}
```

> 新建一个`FactoryImpl`类，也就是`Factory`接口的实现类

## 什么是函数式编程

- 可以赋值给变量
- 可以作为其他函数的参数进行传递
- 可以作为其他函数的返回值

## Lambda语法

### 基本格式一

> `(params) -> {statements;}`
>
> `params`：函数的参数列表
>
> `statements`：执行语句
>
> `->`：使用执行参数去完成某个功能

<img src="https://gitee.com/rhysni/PicGoImg/raw/master/typora-user-images/image-20220821170856040.png" alt="image-20220821170856040" style="zoom:200%;" />

### 基本格式二

> `(params) -> expression`
>
> `params`：函数的参数列表
>
> `expression`：表达式
>
> `->`：使用执行参数去完成某个功能

<img src="https://gitee.com/rhysni/PicGoImg/raw/master/typora-user-images/image-20220821174855109.png" alt="image-20220821174855109" style="zoom:200%;" />

### 基本格式三

#### 可省略`{}`

> 函数体只包含一个语句，不需要大括号

```java
(String name) -> System.out.println("user" + name);
```

#### 可省略`()`

> 只有一个参数，可省略()，同时可省略类型

```java

```

#### 可省略`return`

> 函数体只有一个表达式，省略return

```java

```

#### 可省略`类型声明`

> 不需要参数类型，编译器可以根据参数值进行类型推断

```java

```

> 那么省略了类型它怎么知道我们参数是定义的什么类型的呢?
>
> - 我们一定有一个定义了类型的`接口/函数`，lambda表达式是按照这个函数的签名进行工作的，这个`签名`包含了函数的`类型`、`参数列表`。

## 使用Lambda表达式前提

### 函数式接口

> - 有且只有一个`抽象方法`的接口
> - 可以使用`@FunctionalInterface`注解标注

<img src="https://gitee.com/rhysni/PicGoImg/raw/master/typora-user-images/image-20220821180819854.png" alt="image-20220821180819854" style="zoom:200%;" />

### 常见函数式接口

#### `Runnable`、`Callable`

<img src="https://gitee.com/rhysni/PicGoImg/raw/master/typora-user-images/image-20220821183635204.png" alt="image-20220821183635204" style="zoom:200%;" />

<img src="https://gitee.com/rhysni/PicGoImg/raw/master/typora-user-images/image-20220821183711134.png" alt="image-20220821183711134" style="zoom:200%;" />

##### 代码示例

```java
/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/21 6:31 下午
 */
public class RunnableTest {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                System.out.println("线程:" + name + " 启动");
            }
        }).start();

        //Lambda实现多线程
        new Thread(() -> {
            String name = Thread.currentThread().getName();
            System.out.println("线程:" + name + " 启动");
        }).start();
    }
}
```

#### `Supplier`、`Consumer`

##### Supplier

> 生产者

###### 代码示例

```java
public class SupplierTest {
    public static void main(String[] args) {
        int arr[] = {1, 45, 6, 23, 76, 11};
        //普通写法
        Supplier<Integer> supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return getMax(arr);
            }
        };
        int max = supplier.get();
        System.out.println(max);

        //定义get方法 简化代码
        int max1 = get(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return getMax(arr);
            }
        });
        System.out.println(max1);

        //lambda表达式写法
        int max2 = get(() -> getMax(arr));
        System.out.println(max2);
    }
		
  	//定义get返回类型方法 简化代码
    public static int get(Supplier<Integer> supplier) {
        return supplier.get();
    }

  	//封装公共逻辑
    public static int getMax(int[] arr) {
        int temp = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > temp) {
                temp = arr[i];
            }
        }
        return temp;
    }
}
```

##### Consumer

> 消费者

###### 代码示例

```java
public class ConsumerTest {
    public static void main(String[] args) {
        //普通写法
        System.out.println("-----------------普通写法调用`accept`-----------------");
        consumer(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

        //lambda表达式写法
        System.out.println("-----------------lambda表达式写法调用`accept`-----------------");
        consumer(System.out::println);

        System.out.println("-----------------普通写法调用默认方法`andThen`-----------------");
        //普通写法调用默认方法`andThen`
        consumer(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s.toUpperCase());
            }
        }, new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s.toLowerCase());
            }
        });
        System.out.println("-----------------lambda表达式写法调用默认方法`andThen`-----------------");
        //lambda表达式写法调用默认方法`andThen`
        consumer(s -> System.out.println(s.toUpperCase()), s -> System.out.println(s.toLowerCase()));
    }

    public static void consumer(Consumer<String> param) {
        param.accept("Hello accept");
    }

    public static void consumer(Consumer<String> param1, Consumer<String> param2) {
        param1.andThen(param2).accept("Hello andThen");
    }
}
```

**函数式接口还可以存在其他方法吗？**

- JDK1.8新特性中还允许`default默认方法`、`静态方法`等

#### `Comparator`

> 比较器

##### 代码示例

```java
public class PredicateTest {
    public static void main(String[] args) {
        //与
        System.out.println("-----------------普通写法-----------------");
        Predicate<String> first = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("a");
            }
        };
        Predicate<String> second = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("d");
            }
        };
        add(first, second);
        System.out.println("-----------------普通简化写法-----------------");
        add(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("a");
            }
        }, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("d");
            }
        });
        System.out.println("-----------------lambda写法-----------------");
        add(s -> s.contains("a"), s -> s.contains("d"));


        //或
        System.out.println("-----------------普通写法-----------------");
        Predicate<String> first1 = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("a");
            }
        };
        Predicate<String> second1 = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("d");
            }
        };
        or(first1, second1);
        System.out.println("-----------------普通简化写法-----------------");
        or(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("a");
            }
        }, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("d");
            }
        });
        System.out.println("-----------------lambda写法-----------------");
        or(s -> s.contains("a"), s -> s.contains("d"));


        //非
        System.out.println("-----------------普通写法-----------------");
        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length() > 5;
            }
        };
        negate(predicate);
        System.out.println("-----------------普通简化写法-----------------");
        negate(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length() > 5;
            }
        });
        System.out.println("-----------------lambda写法-----------------");
        negate(s -> s.length() > 5);
    }

    public static void add(Predicate<String> first, Predicate<String> second) {
        System.out.println("字符串符合与规则吗：" + first.and(second).test("HelloPredicate"));
    }

    public static void or(Predicate<String> first, Predicate<String> second) {
        System.out.println("字符串符合或规则吗：" + first.or(second).test("HelloPredicate"));
    }

    public static void negate(Predicate<String> predicate) {
        System.out.println("字符串很长吗（正确结果取反的结果）：" + predicate.negate().test("Predicate"));
    }
}
```

### `Predicate`

> 判断断言，判断两个事物之间的逻辑关系

#### 代码示例

```java
public class PredicateTest {
    public static void main(String[] args) {
        //与
        add(s -> s.contains("a"),s -> s.contains("d"));
        //或
        or(s -> s.contains("a"),s -> s.contains("d"));
        //非
        negate(s -> s.length()<5);
    }

    public static void add(Predicate<String> first, Predicate<String> second) {
        System.out.println("字符串符合与规则吗：" + first.and(second).test("HelloPredicate"));
    }

    public static void or(Predicate<String> first, Predicate<String> second) {
        System.out.println("字符串符合或规则吗：" + first.or(second).test("HelloPredicate"));
    }

    public static void negate(Predicate<String> predicate) {
        System.out.println("字符串很长吗（正确结果取反的结果）：" + predicate.negate().test("Predicate"));
    }
}
```

### `Function`

> 一个`接受一个参数并产生一个结果`的函数。

#### 代码示例

```java
public classs FunctionTest {
    public static void main(String[] args) {
        System.out.println("-----------------普通写法调用`test`-----------------");
        Function<String, Integer> first = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.parseInt(s) + 10;
            }
        };

        Function<Integer, Integer> second = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer s) {
                return s * 10;
            }
        };
        test(first, second);

        System.out.println("-----------------普通简化写法调用`test`-----------------");
        test(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.parseInt(s) + 10;
            }
        }, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer s) {
                return s * 10;
            }
        });

        System.out.println("-----------------lambda写法调用`test`-----------------");
        test(s -> Integer.parseInt(s) + 10, s -> s * 10);


        //调用andThen
        System.out.println("-----------------普通写法调用`test2`-----------------");
        String str = "Rhys-25";
        Function<String, String> first1 = new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.split("-")[1];
            }
        };

        Function<String, Integer> second1 = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.parseInt(s);
            }
        };

        Function<Integer, Integer> third = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer s) {
                return s + 1;
            }
        };
        int i = test2(str, first1, second1, third);
        System.out.println("Rhys:" + i);

        System.out.println("-----------------普通简化写法调用`test2`-----------------");
        String str1 = "Rhys-26";
        int i1 = test2(str1,
                new Function<String, String>() {
                    @Override
                    public String apply(String s) {
                        return s.split("-")[1];
                    }
                }, new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) {
                        return Integer.parseInt(s);
                    }
                }, new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer s) {
                        return s + 1;
                    }
                });
        System.out.println("Rhys:" + i1);

        System.out.println("-----------------lambda写法调用`test2`-----------------");
        String str2 = "Rhys-27";
        int i2 = test2(str2, s -> s.split("-")[1], Integer::parseInt, s -> s + 1);
        System.out.println("Rhys:" + i2);
    }

    public static void test(Function<String, Integer> first, Function<Integer, Integer> second) {
        //andThen将上一个处理的返回值作为新的参数进行处理
        System.out.println((first.andThen(second).apply("10")) + 20);
    }

    public static int test2(String str, Function<String, String> first, Function<String, Integer> second, Function<Integer, Integer> third) {
        return first.andThen(second).andThen(third).apply(str);
    }
}
```

## Lambda底层原理

### 本质

> 函数式接口的匿名子类的`匿名对象`

### 反编译lambda表达式代码

> 对包含lambda表达式的class文件进行反编译时需要注意：
>
> jad系列的反编译工具不支持jdk1.8，所以这里使用CFR进行反编译。
>
> [cfr下载地址](http://www.benf.org/other/cfr/):<http://www.benf.org/other/cfr/>
>
> 语法：`java -jar cfr-0.145.jar LambdaPrinciple.class --decodelambdas false`

**反编译后可以得到：**

```java
public class LambdaPrinciple {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("\u738b\u603b", "\u660e\u603b", "\u5c0f\u502a");
        PrintStream printStream = System.out;
        printStream.getClass();
        list.forEach((Consumer<String>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)V, println(java.lang.String ), (Ljava/lang/String;)V)((PrintStream)printStream));
    }
}
```

> 在`forEach()`方法中，其实是调用了`java.lang.invoke.LambdaMetafactory#metafactory()`方法，该方法的第5个参数`implMethod`指定了方法实现。可以看到这里其实是调用`lambda$main$0()`方法进行输出。跟踪`metafactory()`方法

### LambdaMetafactory.metafactory()

```java
/**
 * lambda底层原理
 * @author Rhys.Ni
 * @date 2022/8/21
 * @param caller : 调用者(LambdaPrinciple),会有一个对应的有可访问权限的上下文对象，由JVM自动填充
 * @param invokedName ：执行方法名：这边是Consumer的accept方法 ，由JVM自动填充
 * @param invokedType ：执行方法类型签名 ，由JVM自动填充
 * @param samMethodType ：函数式接口抽象方法的签名
 * @param implMethod ：实现方法句柄（反编译lambda表达式后的lambda$main$0方法）真正被调用的方法，签名为																	 MethodHandle(String)void
 * @param instantiatedMethodType ：返回的实例化方法类型 即调用时动态执行的方法签名，可能与samMethodType相同，也可能																		包含了泛型的具体类型，比如(String)void
 * @return java.lang.invoke.CallSite : 调用点
 */
public static CallSite metafactory(MethodHandles.Lookup caller,
                                   String invokedName,
                                   MethodType invokedType,
                                   MethodType samMethodType,
                                   MethodHandle implMethod,
                                   MethodType instantiatedMethodType)
  throws LambdaConversionException {
  AbstractValidatingLambdaMetafactory mf;
  mf = new InnerClassLambdaMetafactory(caller, invokedType,
                                       invokedName, samMethodType,
                                       implMethod, instantiatedMethodType,
                                       false, EMPTY_CLASS_ARRAY, EMPTY_MT_ARRAY);
  mf.validateMetafactoryArgs();
  return mf.buildCallSite();
}
```

### ClassWriter

> 其中`new InnerClassLambdaMetafactory`看起来是创建了一个Lambda相关的*内部类*
>
> `ClassWriter`对象其实暴露了Lambda表达式的底层实现机制：
>
> - ASM技术（Assembly，[Java字节码操作和分析框架](https://asm.ow2.io/)）
> - 用于在程序运行时动态生成和操作字节码文件

```java
public InnerClassLambdaMetafactory(MethodHandles.Lookup caller,
                                       MethodType invokedType,
                                       String samMethodName,
                                       MethodType samMethodType,
                                       MethodHandle implMethod,
                                       MethodType instantiatedMethodType,
                                       boolean isSerializable,
                                       Class<?>[] markerInterfaces,
                                       MethodType[] additionalBridges)
            throws LambdaConversionException {
        //...
        lambdaClassName = targetClass.getName().replace('.', '/') + "$$Lambda$" + counter.incrementAndGet();
        cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        i//...
    }
```

> Lambda表达式底层是通过一个内部类来实现的，这个类由ASM技术在程序运行时动态生成，它实现了函数式接口（例如Consumer等），并重写了对应的抽象方法（如accept）。

### 创建调用点（重点）

> 回到`metafactory()`方法中，跟踪方法结尾的返回语句`mf.buildCallSite();`
>
> 方法在*运行期*会返回一个函数式接口的实例，也就是`Consumer`接口的匿名对象。
>
> 方法体的第一行`spinInnerClass()`，就使用ASM技术生成了一个Class文件，然后使用`sun.misc.Unsafe`将该类加载到JVM（创建并返回该类的Class对象）：

```java
/*
*构建CallSite。定义生成实现该函数接口的类文件
*如果没有参数创建一个实例,调用CallSite函数返回的类的句柄，否则生成句柄调用类的构造函数。
*/
@Override
    CallSite buildCallSite() throws LambdaConversionException {
        final Class<?> innerClass = spinInnerClass();
        //...
            try {
                UNSAFE.ensureClassInitialized(innerClass);
                return new ConstantCallSite(
                        MethodHandles.Lookup.IMPL_LOOKUP
                             .findStatic(innerClass, NAME_FACTORY, invokedType));
            }
            catch (ReflectiveOperationException e) {
                throw new LambdaConversionException("Exception finding constructor", e);
            }
        }
    }
```

### spinInnerClass

#### 源码解读

```java
// ASM class writer
private final ClassWriter cw;                   
/**
 * Generate a class file which implements the functional
 * interface, define and return the class.
 * 生成一个实现函数式接口的类文件，定义并返回该类的Class实例

 * @return a Class which implements the functional interface
 * 返回一个实现函数式接口的Class实例
 */
private Class<?> spinInnerClass() throws LambdaConversionException {
  // ....
  // ClassWriter通过visit方法动态构造类的字节码
  cw.visit(, , lambdaClassName, null, , interfaces); // 生成接口字节码
  // ...
  for ( ; ; ) {
    // 生成域的字节码
    cw.visitField( , , , null, null); 
  }
  // 生成构造器字节码
  generateConstructor(); 
  // ...
  // 生成普通方法字节码
  cw.visitMethod( ,  , , null, null); 
  // ...
  // end
  cw.visitEnd(); 

  //在这个VM中定义生成的类
  final byte[] classBytes = cw.toByteArray();

  //如果请求，则转储到一个文件中以供调试(转储对象)
  if (dumper != null) { 
    
    AccessController.doPrivileged(new PrivilegedAction<Void>() {
      @Override
      public Void run() {
        dumper.dumpClass(lambdaClassName, classBytes);
        return null;
      }
    }, null,new FilePermission("<<ALL FILES>>", "read, write"),
                                  // 创建目录可能需要
                                  new PropertyPermission("user.dir", "read"));
  }
  // 使用Unsafe对象定义并返回该内部类字节码文件对象（Class）
  return UNSAFE.defineAnonymousClass(targetClass, classBytes, null);
}
```

#### 图解

![image-20220822003009349](https://gitee.com/rhysni/PicGoImg/raw/master/typora-user-images/image-20220822003009349.png)

#### 时序图

<img src="https://gitee.com/rhysni/PicGoImg/raw/master/typora-user-images/image-20220822001510974.png" alt="image-20220822001510974" style="zoom:200%;" />

> 这个方法的后半部分，`if (dumper != null)` 代码块给我们提供了将该内部类转储到本地磁盘用以调试的可能，在`LambdaPrinciple`的`main`方法里里添加一行代码，将Lambda表达式对应的内部类转储到指定目录（IDEA）：
>
> `System.setProperty("jdk.internal.lambda.dumpProxyClasses", "target/");`

```java
public class LambdaPrinciple {
    public static void main(String[] args) {
        System.setProperty("jdk.internal.lambda.dumpProxyClasses", "target/");

        List<String> list = Arrays.asList("王总", "明总", "小倪");
        list.forEach(System.out::println);
    }
}
```

> 执行后可得

```java
// 实现函数式接口
final class LambdaPrinciple$$Lambda$1 implements Consumer {
    private final PrintStream arg$1;

    private LambdaPrinciple$$Lambda$1(PrintStream var1) {
        this.arg$1 = var1;
    }

    private static Consumer get$Lambda(PrintStream var0) {
        return new LambdaPrinciple$$Lambda$1(var0);
    }

  	// 重写抽象方法
    @Hidden
    public void accept(Object var1) {
        this.arg$1.println((String)var1);
    }
}
```

#### 结论

> - Lambda表达式底层是用内部类来实现的 
> - 该内部类实现了*某个（根据Lambda所属的代码指定）*函数式接口，并重写了该接口的抽象方法
> - 该内部类是在程序运行时使用ASM技术动态生成的，所以编译期没有对应的.class文件，但是我们可以通过设置系统属性将该内部类文件转储出来

### Lambda表达式编译和运行过程

> - Java7在[JSR（Java Specification Requests，Java 规范提案） 292](https://jcp.org/en/jsr/detail?id=292)中增加了对动态类型语言的支持，使得Java也可以像*C语言*那样将方法作为参数传递，其实现在`java.lang.invoke`包中。它的核心就是`invokedynamic`指令，为后面**函数式编程**和**响应式编程**提供了前置支持。
>
> - `invokedynamic`指令对应的执行方法会关联到一个*动态*调用点对象（`java.lang.invoke.CallSite`），一个调用点（call site）是一个方法句柄（method handle，调用点的目标）的持有者，这个调用点对象会指向一个具体的引导方法（bootstrap method，比如`metafactory()`），引导方法成功调用之后，调用点的目标将会与它持有的方法句柄的引用永久绑定，最终得到一个实现了函数式接口（比如Consumer）的对象。
>
> - Lambda表达式在编译期进行脱糖（desugar），它的主体部分会被转换成一个脱糖方法（desugared method，即`lambda$main$0`），这是一个合成方法，如果Lambda没有用到外部变量，则是一个私有的静态方法，否则将是个私有的实例方法——synthetic 表示不在源码中显示，并在Lambda所属的方法（比如main方法）中生成`invokedynamic`指令。
>
> - **进入运行期**，`invokedynamic`指令会调用引导方法`metafactory()`初始化ASM生成内部类所需的各项属性，然后由`spinInnerClass()`方法组装内部类并用Unsafe加载到JVM，通过构造方法实例化内部类的实例（Lambda的实现内部类的构造是私有的，需要手动设置可访问属性为true），最后绑定到方法句柄，完成调用点的创建。
>
> - 你可以把调用点看成是函数式接口（例如Consumer等）的匿名对象，当然，内部类是确实存在的——比如`final class LambdaPrinciple$$Lambda$1 implements Consumer`。值得注意的是，内部类的实现方法里并没有Lambda表达式的任何操作，它不过是调用了脱糖后定义在调用点目标类（`targetClass`，即`LambdaPrinciple`类）中的合成方法（即`lambda$main$0`）而已，这样做使得内部类的代码量尽可能的减少，降低内存占用，对效率的提升更加稳定和可控。

### Lambda表达式的语法糖结论

> - Lambda表达式在编译期脱去糖衣语法，生成了一个“合成方法”，在运行期，`invokedynamic`指令通过引导方法创建调用点，过程中生成一个实现了函数式接口的内部类并返回它的对象，最终通过调用点所持有的方法句柄完成对合成方法的调用，实现具体的功能。
>
> - Lambda表达式是一个语法糖，但远远不止是一个语法糖。

## 方法引用（进阶）

### 初识方法引用

> 其实在上面部分代码中已经运用到了，可能没有太过意对吧，这边再次提出来细说一下
>
> - 当某些方法已经存在，则可以引用已存在的方法，避免重复逻辑
> - 目的是使代码更加简洁
>
> `对象名::方法名`

#### 代码演示

> 新建`Printable`接口

```java
public interface Printable {
    void print(String content);
}
```

> 新建`MethodReference`类

```java
public class MethodReference {
    public static void main(String[] args) {
        //传统写法
        //printString(s -> {
        //    System.out.println(s);
        //});

        //方法引用写法
        printString(System.out::println);
    }

    public static void printString(Printable printable) {
        printable.print("Hello MethodReference");
    }
}
```

### 底层实现

> 同样反编译对应类得到反编译后的代码

```java
public class MethodReference {
    public static void main(String[] args) {
        PrintStream printStream = System.out;
        printStream.getClass();
        MethodReference.printString((Printable)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/String;)V,
//直接将println方法放到这里
 println(java.lang.String ), 
(Ljava/lang/String;)V)((PrintStream)printStream));
    }

    public static void printString(Printable printable) {
        printable.print("Hello MethodReference");
    }
}
```

> 内部类反编译代码

```java
final class MethodReference$$Lambda$1 implements Printable {
    private final PrintStream arg$1;

    private MethodReference$$Lambda$1(PrintStream var1) {
        this.arg$1 = var1;
    }

    private static Printable get$Lambda(PrintStream var0) {
        return new MethodReference$$Lambda$1(var0);
    }

    @Hidden
    public void print(String var1) {
        this.arg$1.println(var1);
    }
}
```

### 语法格式

> `::`双冒号

#### 哪些方法可以被引用

> - 类方法：`类名::静态方法（Integer::parsInt）`
> - 构造方法：`类名::new（User::new）`
> - 实例方法：`对象::成员方法（User::hello）`
>   - `this::方法名/super::方法名`
>
> **注意:**`被引用方法与函数接口抽象方法的参数列表相同，返回值类型兼容`

### 应用场景 

#### 静态方法引用

新增`Calculable`接口

```java
@FunctionalInterface
public interface Calculable {
    int calAbs(int num);
}
```

> 新增`StaticMethodReference`类

```java
public class StaticMethodReference {
    public static void main(String[] args) {
        int i = method(-10, (num) -> {
            return Math.abs(num);
        });
        System.out.println(i);

        //方法引用优化
        int i2 = method(-20, Math::abs);
        System.out.println(i2);
    }

    public static int method(int num, Calculable calculable) {
        return calculable.calAbs(num);
    }
}
```

#### 构造器方法引用

> 新增`UserBuilder`接口

```java
@FunctionalInterface
public interface UserBuilder {
    User builderUser(String name,int age);
}
```

> 新增`ConstructorReference`类

```java
public class ConstructorReference {
    public static void main(String[] args) {
        printName("Rhys", 25, (name, age) -> new User(name, age));
        //方法引用优化
        printName("Rhys", 26, User::new);
    }

    public static void printName(String name, int age, UserBuilder builder) {
        User user = builder.builderUser(name, age);
        System.out.println(user.getName() + ":" + user.getAge());
    }
}
```

#### 普通方法引用

> 新增`Printable`接口

```java
@FunctionalInterface
public interface Printable {
    void print(String content);
}
```

> 新增`MethodReferenceObject`类

```java
public class MethodReferenceObject {
    /**
     * 定义一个成员方法 将传入字符串格转大写输出
     *
     * @param str
     * @return void
     * @author Rhys.Ni
     * @date 2022/8/22
     */
    public int printUpperCaseString(String str) {
        System.out.println(str.toUpperCase());
        return 0;
    }
}
```

> 新增`ObjectMethodReference`类

```java
public class ObjectMethodReference {
    public static void main(String[] args) {
        printString(s -> {
            MethodReferenceObject referenceObject = new MethodReferenceObject();
            referenceObject.printUpperCaseString(s);
        });

        //方法引用优化
        //引用某个普通的成员方法 对象名::方法名
        MethodReferenceObject referenceObject = new MethodReferenceObject();
        printString(referenceObject::printUpperCaseString);
    }
    
    public static void printString(Printable printable) {
        printable.print("hello method reference");
    }
}
```

#### 父类方法引用

> 新增`Greetable`接口

```java
@FunctionalInterface
public interface Greetable {
    void greet();
}
```

> 新增`Human`类

```java
public class Human {
    public void hello() {
        System.out.println("Hello 我是村花小芳");
    }
}
```

> 新增`Man`类

```java
public class Man extends Human {

    @Override
    public void hello() {
        System.out.println("Hello 我是村草小明");
    }

    public void method(Greetable greetable) {
        greetable.greet();
    }

    public void show() {
        //使用super成员方法引用优化 super::父类方法名
        method(super::hello);
    }
}
```

> 新增`SuperMethodReference`类

```java
public class SuperMethodReference {
    public static void main(String[] args) {
        Man man = new Man();
        man.show();
    }
}
```

#### 当前类方法引用

> 新增`Richable`接口

```java
@FunctionalInterface
public interface Richable {
    void buy();
}
```

> 新增`Husband`类

```java
public class Husband {
    public void buyHouse() {
        System.out.println("小明在上海汤臣一品买了一套房");
    }

    public void marry(Richable richable) {
        richable.buy();
    }

    public void soHappy() {
        marry(() -> this.buyHouse());

        //使用方法引用优化 this::方法名
        marry(this::buyHouse);
    }
}
```

> 新增`ThisMethodReference`类

```java
public class ThisMethodReference {
    public static void main(String[] args) {
        Husband husband = new Husband();
        husband.soHappy();
    }
}
```

#### 数组方法引用

> 新增`ArrayBuilder`接口

```java
@FunctionalInterface
public interface ArrayBuilder {
    int[] builderArray(int length);
}
```

> 新增`ArrayMethodReference`类

```java
public class ArrayMethodReference {
    public static void main(String[] args) {
        int[] array = createArray(10, length -> {
            return new int[length];
        });
        System.out.println(array.length);

        //使用方法引用优化
        //数组的方法引用有且只有构造器引用 数据类型[]::new
        int[] array1 = createArray(10, int[]::new);
        System.out.println(Arrays.toString(array1));
    }

    public static int[] createArray(int length, ArrayBuilder builder) {
        return builder.builderArray(length);
    }
}
```

## Steam

### 初识Stream

> 查找集合中符合条件的人员
>
> - 查找集合中姓张，并且名字长度为3的人
> - 最后打印出来

```java
public class Demo1 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("张晓明");
        list.add("王晓芳");
        list.add("张三丰");
        list.add("小明");
        list.add("小芳");

        //传统写法
        ArrayList<String> listA = new ArrayList<>();
        for (String name : list) {
            if (name.startsWith("张")) {
                listA.add(name);
            }
        }

        ArrayList<String> listB = new ArrayList<>();
        for (String name : listA) {
            if (name.length() == 3) {
                listB.add(name);
            }
        }

        for (String name : listB) {
            System.out.println(name);
        }


        //stream优化
        list.stream()
                .filter(name -> name.startsWith("张"))
                .filter(name -> name.length() == 3)
                .forEach(System.out::println);
    }
}
```

### 特点

> - 专注于对容器对象的`聚合操作`
> - 提供`串行/并行`两种模式
>   - 使用`Fork/Join框架拆分任务`
> - 提高编程效率和可读性

### 使用步骤

> - 获取流
> - 中间操作
> - 终结操作

<img src="https://gitee.com/rhysni/PicGoImg/raw/master/typora-user-images/image-20220822030730455.png" alt="image-20220822030730455" style="zoom:200%;" />

### Lazy

> **延迟处理**
>
> 把所有的操作都加到队列中，只有当终结操作执行的时候才会开始执行操作

### 常用API

#### 获取流

```java
public class GetStream {
    public static void main(String[] args) {
        //把集合转换为Stream流
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        //获取键,存储到一个Set集合中
        Set<String> set = new HashSet<>();
        Stream<String> stream1 = set.stream();

        Map<String, String> map = new HashMap<>();
        //获取值,存储到一个Collection集合中
        Collection<String> values = map.values();
        Stream<String> stream2 = values.stream();
        //获取键值对(键与值的映射关系 entrySet)
        Set<Map.Entry<String, String>> entries = map.entrySet();
        Stream<Map.Entry<String, String>> stream3 = entries.stream();

        //把数组转换为Stream流
        Integer[] arr = {1, 2, 3, 4, 5};
        String[] arr2 = {"a", "bb", "ccc"};
        Stream<Integer> stream4 = Stream.of(arr);
        Stream<String> stream5 = Stream.of(arr2);
    }
}
```

#### 中间操作(Intermediate)

> - 可以有多个
> - 打开流，过滤/映射
> - 返回新的流
> - 交给下一个操作使用
>
> `map(mapToInt,flatMap等)`、 `filter`、`distinct`、`sorte`、`peek`、`limit`、`skip`、`parallel`、`sequential`、`unordered`、`concat`

##### filter

> Stream流中的常用方法_filter:用于对Stream流中的数据进行过滤
> Stream<T> filter(Predicate<? super T> predicate);
> filter方法的参数Predicate是一个函数式接口,所以可以传递Lambda表达式,对数据进行过滤
> Predicate中的抽象方法:
>     boolean test(T t);

```java
public class StreamAndFilter {
    public static void main(String[] args) {
        Stream.of("张晓明", "王晓芳", "张三丰", "小明", "小芳")
                .filter(name -> name.startsWith("张"))
                .forEach(System.out::println);
    }
}
```

##### concat

> Stream流中的常用方法_concat:用于把流组合到一起
> 如果有两个流，希望合并成为一个流，那么可以使用Stream接口的静态方法concat
> static <T> Stream<T> concat(Stream<? extends T> a, Stream<? extends T> b)

```java
public class StreamAndConcat {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("张晓明", "王晓芳", "张三丰", "小明", "小芳");

        String[] arr = {"喜羊羊", "美羊羊", "懒洋洋", "红太狼", "灰太狼"};
        Stream<String> stream1 = Stream.of(arr);

        Stream.concat(stream, stream1).forEach(System.out::println);
    }
}
```

##### map

> Stream流中的常用方法_map:用于类型转换
> 如果需要将流中的元素映射到另一个流中，可以使用map方法.
> <R> Stream<R> map(Function<? super T, ? extends R> mapper);
> 该接口需要一个Function函数式接口参数，可以将当前流中的T类型数据转换为另一种R类型的流。
> Function中的抽象方法:
>     R apply(T t);

```java
public class StreamAndMap {
    public static void main(String[] args) {
        Stream.of("1", "2", "3", "4")
          .map(Integer::parseInt)
          .forEach(System.out::println);
    }
}
```

##### skip

> Stream流中的常用方法_skip:用于跳过元素
> 如果希望跳过前几个元素，可以使用skip方法获取一个截取之后的新流：
> Stream<T> skip(long n);
>     如果流的当前长度大于n，则跳过前n个；否则将会得到一个长度为0的空流。

```java
public class StreamAndSkip {
    public static void main(String[] args) {
        String[] arr = {"美羊羊", "喜洋洋", "懒洋洋", "灰太狼", "红太狼"};
        Stream.of(arr)
          .skip(2)
          .forEach(System.out::println);
    }
}
```

#### 终结操作

> 只能有一个，最后的操作

> `forEach`、`forEachOrdered`、`toArray`、`reduce`、`collect`、`min`、`max`、`count`、`iterator`
>
> 短路操作（只要有任何一个满足就返回）：`anyMatch`、`allMatch`、`noneMatch`、`findFirst`、`findAny`

##### forEach

> Stream流中的常用方法_forEach
> void forEach(Consumer<? super T> action);
> 该方法接收一个Consumer接口函数，会将每一个流元素交给该函数进行处理。
> Consumer接口是一个消费型的函数式接口,可以传递Lambda表达式,消费数据
>
> 简单记:
>     forEach方法,用来遍历流中的数据
>     是一个终结方法,遍历之后就不能继续调用Stream流中的其他方法

```java
public class StreamAndForEach {
    public static void main(String[] args) {
        Stream.of("张晓明", "王晓芳", "张三丰", "小明", "小芳").forEach(System.out::println);
    }
}
```

##### count

> Stream流中的常用方法_count:用于统计Stream流中元素的个数
>     long count();
>     count方法是一个终结方法,返回值是一个long类型的整数
>     所以不能再继续调用Stream流中的其他方法了

```java
public class StreamAndCount {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);

        long count = list.stream().count();
        System.out.println(count);
    }
}
```

##### collect

> Stream流的收集方法
>     R collect(Collector collector)
>
> 它是通过工具类Collectors提供了具体的收集方式
>     public static <T> Collector toList()：把元素收集到List集合中
>     public static <T> Collector toSet()：把元素收集到Set集合中
>     public static Collector toMap(Function keyMapper,Function valueMapper)：把元素收集到Map集合中

```java
public class StreamAndCollect {
    public static void main(String[] args) {
        //List流式操作
        List<String> list = new ArrayList<String>();
        list.add("张晓明");
        list.add("王晓芳");
        list.add("张三丰");
        list.add("小明");
        list.add("小芳");
        //1：得到名字为3个字的流
        Stream<String> stream = list.stream()
                .filter(name -> name.length() == 3);
        //2：把使用Stream流操作完毕的数据收集到List集合中并遍历s
        stream.collect(Collectors.toList())
                .forEach(System.out::println);

        //Set流式操作
        Set<Integer> set = new HashSet<Integer>();
        set.add(10);
        set.add(20);
        set.add(30);
        set.add(33);
        set.add(35);
        //3：得到年龄大于25的流
        Stream<Integer> stream1 = set.stream()
                .filter(age -> age > 25);
        //4：把使用Stream流操作完毕的数据收集到Set集合中并遍历
        stream1.collect(Collectors.toSet())
                .forEach(System.out::println);

        //数组流式操作
        String[] strArray = {"张晓明,30", "王晓芳,35", "张三丰,33", "小明,25"};
        //5：得到字符串中年龄数据大于28的流
        Stream<String> stream2 = Stream.of(strArray)
                .filter(age -> Integer.parseInt(age.split(",")[1]) > 28);
        //6：把使用Stream流操作完毕的数据收集到Map集合中并遍历，字符串中的姓名作键，年龄作值
        stream2.collect(Collectors.toMap(name -> name.split(",")[0], age -> Integer.parseInt(age.split(",")[1])))
                .forEach((name, age) -> System.out.println(name + ":" + age));
    }
}
```

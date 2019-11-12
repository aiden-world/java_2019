Stack栈
==


## 概念
```text
栈(Stack)是一种后进先出（LIFO：Last In First Out）的数据结构,
像弹匣，后进的子弹先出。

```
![](images/弹匣.png)  

## Stack只有入栈和出栈的操作
* 把元素压栈：push(E e)
* 把栈顶的元素"弹出"：E pop()
* 取栈顶元素但不弹出：E peek()


```text
为什么Java的集合类没有单独的Stack接口呢？
因为有个遗留类名字就叫Stack，出于兼容性考虑，
Stack也可以使用，它继承了Vector类

建议不要使用遗留类Stack
所以没办法创建Stack接口，只能用Deque接口来“模拟”一个Stack了.

当我们把Deque作为Stack使用时，注意只调用push()/pop()/peek()方法，
不要调用addFirst()/removeFirst()/peekFirst()方法，这样代码更加清晰
```

### 在Java中，我们用Deque可以实现Stack的功能
* 把元素压栈：push(E e) /addFirst(E e)
* 把栈顶的元素"弹出"：E pop() /E removeFirst()
* 取栈顶元素但不弹出：E peek() /E peekFirst()

### Stack的作用
* 示例1
```text
Stack在计算机中使用非常广泛，
JVM在处理Java方法调用的时候就会通过栈这种数据结构维护方法调用的层次
```

```java
static void main(String[] args) {
    foo(123);
}

static String foo(x) {
    return "F-" + bar(x + 1);
}

static int bar(int x) {
    return x << 2;
}
```

```text
JVM会创建方法调用栈，每调用一个方法时，先将参数压栈，
然后执行对应的方法；当方法返回时，返回值压栈，
调用方法通过出栈操作获得方法返回值。

因为方法调用栈有容量限制，嵌套调用过多会造成栈溢出，
即引发StackOverflowError：
```

```java
// 测试无限递归调用

public class Main {
    public static void main(String[] args) {
        increase(1);
    }

    static int increase(int x) {
        return increase(x) + 1;
    }
}
```

* 示例2：利用栈对整数进行进制的转换。把一个int整数12500转换为十六进制表示的字符串
    1. 首先我们准备一个空栈
        ```text
        │   │
        │   │
        │   │
        │   │
        │   │
        │   │
        │   │
        │   │
        └───┘
        ```
    2. 然后计算12500÷16=781…4，余数是4，把余数4压栈
        ```text
        │   │
        │   │
        │   │
        │   │
        │   │
        │   │
        │   │
        │ 4 │
        └───┘
        ```
    3. 然后计算781÷16=48…13，余数是13，13的十六进制用字母D表示，把余数D压栈
        ```text
        │   │
        │   │
        │   │
        │   │
        │   │
        │ D │
        │   │
        │ 4 │
        └───┘
        ```
    4. 然后计算48÷16=3…0，余数是0，把余数0压栈
        ```text
        │   │
        │   │
        │   │
        │ 0 │
        │   │
        │ D │
        │   │
        │ 4 │
        └───┘
        ```
    5. 最后计算3÷16=0…3，余数是3，把余数3压栈
        ```text
       │   │
       │ 3 │
       │   │
       │ 0 │
       │   │
       │ D │
       │   │
       │ 4 │
       └───┘
        ```
    6. 当商是0的时候，计算结束，我们把栈的所有元素依次弹出，组成字符串30D4，这就是十进制整数12500的十六进制表示的字符串
    
* 示例3：计算中缀表达式
    ```text
    在编写程序的时候，我们使用的带括号的数学表达式实际上是中缀表达式，即运算符在中间，
    例如：1 + 2 * (9 - 5)。
    
    但是计算机执行表达式的时候，它并不能直接计算中缀表达式，
    而是通过编译器把中缀表达式转换为后缀表达式，例如：1 2 9 5 - * +
    ```
    ```text
    看看如何通过栈计算后缀表达式
    ```
    1. 首先准备一个空的栈
        ```text
        │   │
        │   │
        │   │
        │   │
        │   │
        │   │
        │   │
        │   │
        └───┘
        ```
    2. 然后我们依次扫描后缀表达式1 2 9 5 - * +，遇到数字1，就直接扔到栈里
        ```text
        │   │
        │   │
        │   │
        │   │
        │   │
        │   │
        │   │
        │ 1 │
        └───┘
        ```
    3. 紧接着，遇到数字2，9，5，也扔到栈里
        ```text
        │   │
        │ 5 │
        │   │
        │ 9 │
        │   │
        │ 2 │
        │   │
        │ 1 │
        └───┘
        ```
    4. 接下来遇到减号时，弹出栈顶的两个元素，并计算9-5=4，把结果4压栈
        ```text
        │   │
        │   │
        │   │
        │ 4 │
        │   │
        │ 2 │
        │   │
        │ 1 │
        └───┘
        ```
    5. 接下来遇到\*号时，弹出栈顶的两个元素，并计算2\*4=8，把结果8压栈
        ```text
        │   │
        │   │
        │   │
        │   │
        │   │
        │ 8 │
        │   │
        │ 1 │
        └───┘
        ```
    6. 接下来遇到+号时，弹出栈顶的两个元素，并计算1+8=9，把结果9压栈
        ```text
        │   │
        │   │
        │   │
        │   │
        │   │
        │   │
        │   │
        │ 9 │
        └───┘
        ```
    7. 扫描结束后，没有更多的计算了，弹出栈的唯一一个元素，得到计算结果9
    
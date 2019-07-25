package test;

import java.util.Arrays;

public class objOriented {

    private static boolean somebody(int a, int b) {
        boolean boo = true;
        if (a > b) {
            boo = false;
        } else if (a < b) {
            boo = true;
        }
        return boo;
    }

    public static void main(String[] args) {
        System.out.println(somebody(1, 2));

        String [] arr={"duuliy", "赵日天", "张飞"};
        Person ming = new Person(arr,18);
     /*   ming.setName("duuliy", "赵日天", "张飞"); // 设置name
        ming.setAge(18); // 设置age*/
        System.out.println(ming.getName() + ", " + ming.getAge());
        String[] fullname = new String[] { "Homer", "Simpson" };
        String[] copeName= Arrays.copyOf(fullname,fullname.length); //深拷贝处理(一层)
        ming.setName2(copeName);
        fullname[0] = "Bart";
        System.out.println(ming.getName());
        Student stu=new Student();
        System.out.println(stu.hello());
        System.out.println(stu instanceof Student);
    }
}

class Person {
    private String[] name; // 默认初始化为[null]
    protected int      age; // 默认初始化为0
    protected String myName="duuliy";

    public Person() {  //这样定义2个构造函数
                       // 就可以让既要能使用带参数的构造方法，又想保留不带参数的构造方法
    }

    //这样可以 new的时候就传入参数
    public Person(String[] name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return Arrays.toString(this.name);
    }

    public void setName(String... name) {
        this.name = name;
    }

    public void setName2(String[] name) {
        this.name = name;
    }

    @Override
    public Object clone() {
        try {
            Person person = (Person) super.clone();
            person.name = Arrays.copyOf(name,name.length);//深拷贝处理(一层)
            return person;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public int getAge() {
        return this.age;
    }

    public final void getFinal() {
        System.out.println("不允许子类继承");
    }

    public void setAge(int age) {
        if (age < 0 || age > 100) {
            throw new IllegalArgumentException("invalid age value");
        }
        this.age = age;
    }
}

class Student extends Person {
    // 不要重复name和age字段/方法,
    // 只需要定义新增score字段/方法:
    protected String myName="duuliy666"; //不会覆盖父类的属性，父级生效。
    private int score;
    public String hello(){
//        System.out.println(666); //不能直接运行主类以外的类
        return super.myName;
    }

    @Override //让编译器检查是否进行了正确的覆写
    public int getAge() {
        if(super.age>6){
            super.age=666;
        }else{
            super.age=0;
        }
        return super.age;
    }
}


abstract class Person2 {
    public abstract void run();
}

class Student2 extends Person2 {
    @Override
    public void run() {
        System.out.println("Student.run");
    }
}

interface PersonY {
    default void run() {
        System.out.println(getName() + " run");
    }
    String getName();

    default void sayHi(){
        System.out.println("hiY run");
    }
}

interface PersonX {
    default void run() {
        System.out.println(getName() + " run");
    }
    String getName();

    default void sayHi(){
        System.out.println("hiX run");
    }
}

class StudentY implements PersonY,PersonX {
    private String name;

    public StudentY(String name) {
        this.name = name;
    }

    @Override
    public void sayHi(){
        PersonY.super.sayHi();
    }

    @Override
    public void run() {
        System.out.println(this.name + " run");
    }

    @Override
    public String getName() {
        return this.name;
    }
}








/**
 * 对顶层的类是object,他是没有父级的，相当于js原型链最顶端。
 * 面向对象 class和new大致同js 两个instance拥有class定义的name和age字段，且各自都有一份独立的数据，互不干扰。
 *
 * 如果一个类没有定义构造方法，编译器会自动为我们生成一个默认构造方法，它没有参数，也没有执行语句
 *
 * 没有在构造方法中初始化字段时，
 * 引用类型的字段默认是null，
 * 数值类型的字段用默认值，
 * int类型默认值是0，
 * 布尔类型默认值是false：
 *
 * 定义构造函数初始化变量又直接初始化变量的执行顺序是：
 * 1.先初始化字段
 * 2.执行构造方法的代码进行赋值
 *
 * 多构造函数的情况会根据调用的方法自动匹配
 */

/**
 * 方法重载:
 *
 * 这种方法名相同，但各自的参数不同，称为方法重载（Overload）。
 * 比如多构造函数情况，注意：方法重载的返回值类型通常都是相同的。！！！
 *
 * */

/**
 * 继承:
 * Java只允许一个class继承自一个类，因此，一个类有且仅有一个父类。
 * 因为如果c继承a和b,a;b均有ggg()方法，在c里面可以重写a;b的ggg方法,出现菱形问题。。
 *
 * protected 添加这个可以被子类拿到父类的方法，不添或public都行
 * final 给方法定义: 如果父类不允许子类重写某个方法的时候可以用.
 * final 如果标记给类，那么类也会不允许继承
 *
 * super同es6也是指代的父类的this.
 * 如果父类没有默认的构造方法，子类就必须显式调用super()并给出参数以便让编译器定位到父类的一个合适的构造方法。
 * 即子类不会继承任何父类的构造方法。子类默认的构造方法是编译器自动生成的，不是继承的。
 *
 * 子类可以向上转型(object也行) eg: Person gg=new Student()
 * 向下转型可能会失败，因为子类可能比父类多
 *
 * instanceof 判断子变量是否继承于某个父类
 *
 * */

/**
 * 多态：(多态是指，针对某个类型的方法调用，其真正执行的方法取决于运行时期实际类型的方法)
 * 在继承关系中，子类如果定义了一个与父类方法签名完全相同的方法，被称为覆写（Override）。
 * 注意：方法名相同，方法参数相同，但方法返回值不同，也是不同的方法。在Java程序中，出现这种情况，编译器会报错。
 * @Override 覆写就是重写
 * @Overload 是声明一个新的方法。
 *
 *
 *
 * */

/**
 * 抽象类：
 * 把一个方法声明为abstract，表示它是一个抽象方法，本身没有实现任何方法语句。因为这个抽象方法本身是无法执行的，所以，Person类也无法被实例化。编译器会告诉我们，无法编译Person类，因为它包含抽象方法。
 * 必须把Person类本身也声明为abstract，才能正确编译它
 *
 * 感觉没什么用，但是：
 * 因为抽象类本身被设计成只能用于被继承，因此，抽象类可以强迫子类实现其定义的抽象方法，否则编译会报错。因此，抽象方法实际上相当于定义了“规范”。
 *
 * 面向抽象编程的本质就是：
 *
 * 上层代码只定义规范（例如：abstract class Person）；
 * 不需要子类就可以实现业务逻辑（正常编译）;
 * 具体的业务逻辑由不同的子类实现，调用者并不关心。
 *
 * 就跟抽象类的使用没得啥区别
 *
 * */


/**
 * 接口 interface:
 * 跟ts差不多
 * 只能写方法，不能写字段
 *
 * 类继承接口:implements,可继承多个
 * 接口继承接口:extends
 *
 * default方法的目的是，当我们需要给接口新增一个方法时，
 * 会涉及到修改全部子类。如果新增的是default方法，那么子类就不必全部修改，只需要在需要覆写的地方去覆写新增方法。
 * 简而言之 是有了default才能重写
 *
 * 因为interface没有字段，default方法无法访问字段，而抽象类的普通方法可以访问实例字段。
 * */
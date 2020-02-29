# redrock_1
第一周作业





# 1.1



# 一、接口（英文：Interface）

* 接口里的方法全是抽象方法（没有构造方法，只能有public static final变量）
* 接口无法被实例化，但是可以被实现。
* 如果一个类要用接口    就要实现这个接口里的所有方法/这个类是抽象类

ps：接口类型可用来声明一个变量，他可以成为一个**空指针**，或是**被绑定在一个以此接口实现的对象**。	

```
public class Demo {
    public static void main(String[] args) {
   //空指针
        Animal cat = null;
        
        
  //绑定对象
        /*该变量只能调用接口中重写的方法，不能调用类中的方法*/
        Animal dog = new Dog();
        dog.Eat();
    }
}

class Dog implements Animal{

    @Override
    public void Eat() {
        System.out.println("啃骨头");
    }
}

   interface Animal{
       public abstract void Eat();

}
```

​		

# 二、接口回调

## 回调

* 双向调用

* 反馈结果

  ex：龙哥给我布置作业，我完成作业交给他T.T

* 回调的目的主要有两个：其一是传递数据，其二是保持数据的同步更新。

* 常用的有两种形式，一是使用**内部类的形式，得到接口的子类对象**（这个我不太懂），另一种是**直接实现定义的接口。**

#### 内部类形式

* 匿名内部类可以很方便的定义回调🤔

* 可以很好的实现多继承的效果

* 如果该方法仅仅调用一次，使用匿名内部类会简化

* ```
  package Demo;
  public class Demo_2 {
      public static void main(String[] args) {
          Print print = new Print();
          onListener listener= new onListener() {
              @Override
              public void OnListener() {
                  System.out.println("我在匿名内部类里被重写了");
              }
  
          };
          print.setOnListener(listener);
          print.text();
      }
  }
  /*一个类*/
  class Print{
      public void work(){
          System.out.println("go to work");
      }
      private onListener listener;
      public void setOnListener(onListener listener) {
          this.listener = listener;//这是回调的关键  获得上面匿名内部类里重写的方法
      }
  
      public void text(){
      /*判空 防止空指针异常*/
          if (listener != null) {
              listener.OnListener();
              System.out.println("我获得了重写的接口里的方法，我可以继续用了T.T");
          }
      }
  }
  /*定义一个接口*/
  interface onListener {
      void OnListener();
  }
  
  ```

####  直接实现定义的接口回调

* 别骂了我就是照着您写了一遍T。T

* ```
  package Demo;
  
  public class Demo_1 {
      public static void main(String[] args) {
          Teath t = new Teath();
          Student s = new Student();
          t.text(s);
      }
  
  }
  
  interface CallBack{
      public abstract void right();
  }
  
  class Teath implements CallBack{
      public void text(Student s){
          System.out.print("1+1=");
          s.write(this);//'this' 指对象t
      }
  
  /*这是重写的回调方法*/
      @Override
      public void right() {
          System.out.println("You are right!");
      }
  
  }
  class Student{
  
      public void write(CallBack callBack){
          System.out.println("2");
             callBack.right();//这里回调
      }
  }
  ```

#### Android中的接口回调 ex

* 安卓开发中有很多点击事件都需要接口回调，像ListView有自带的接口，但是RecycleView就没有。。。

* ```java
  /*MyAdapter在适配器设置一个接口*/
  
  private MyClickListener myClickListener;
  
    public interface MyClickListener{
          void onClick(View view, int postion);
      }
      public void setOnClickListener(MyClickListener myClickListener){
          this.myClickListener = myClickListener;
      }
  
  /*然后在onBindViewHolder方法中设置itemview的点击事件*/
   holder.itemView.setOnMyClickListener(new View.OnClickListener() {
              @Override
              public boolean onClick(View v) {
                  myClickListener.onClick(v,position);
                  return true;
              }
          });
  
  
  /*最后在Activity方法中设置点击事件*/
  
  //new一个适配器，用适配器调用点击事件的接口
      myAdapter.setOnClickListener(new MyAdapter.MyClickListener() {
              @Override
              public void onClick(View view,int postion) {
  
              }
          });
  ```


//only main function class can be public
//CLASSES CAN NOT BE PRIVATE,PROTECTED
// and if that class is public then file name and class name should be same
//In System.out.println-->
// System is class,out is static reference varibale,println is function in System class
// Scanner(System.in[to connect buffer memory and keyboard])
//nextInt() and more functin like this--> takes memory from buffer and store it in variable;
//private member can be accesed in same class



class Example{
    int x; //object (instance)member variable
    int y;//object member variable
    static int z;// static member variable
    void fun1(){
        System.out.println(x);//this fun1 can acces same class variable also
    }
}
class Example1{
    private int x=3;// if insctance membr are private then we will make function
    // and then thruogh that function we intialize the that insctance member
    private int y=4;
    void fun1(){
        int p;//local variable

        System.out.println(x);
    }
}

public class Cobj {
    public static void main(String args[]){
        Example e1= new Example();
        Example1 e2=new Example1();
        e1.x=2;
        System.out.println(e1.x);
        Example.z=23; // or e1.z=23 (z is static variable in class Example
        // so we can acess z without making object of it)
        e1.fun1();
        e2.fun1();
       
    }
}

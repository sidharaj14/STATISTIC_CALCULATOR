class a{
    private int x;
    private int y;
    int z;
    void fun1(){
        x=2;
        y=3;

    }
    void display(){
        System.out.println(x);
        System.out.println(y);

    }

}

public class createpackage {
    //packages(folder)-> util,lang,io,awt,swing
    //method to import packages-> import(keyword) java.package.classname
    //import all classes from packages-> import java.package.*
    //private member can be accesed in same class

    public static void main(String args[]){
        a a1=new a();
        a1.fun1();
        a1.display();
        
    }
       

    
}

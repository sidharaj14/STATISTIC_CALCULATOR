//polymorphysum-> occurs in method overloading :when complier has to decide which function to call
//(if many function of same name is created )then this process is called polymorphysum
//methodoverloading-> function name same but argument different 

//Constructor->(class name = function name) object is created then function also run
// whenever object is created then default constructer(empty) is also created
//constructoroverloading-> same function name but differnet argumennt name
//to intialisze the value we make constructor

class example{
    example(){
        System.out.println("im constructur");

    }
}
public class constructur {
    public static void main(String args[]){
        example e1= new example();

    }
    
}

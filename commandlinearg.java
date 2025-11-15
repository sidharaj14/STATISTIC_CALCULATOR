 class command{
    public static void main(String args[]){
        
        String s1="amit";// sam as s3 (point to same amit block)
        String s2= new String("amit");// different from s1 and s3
        String s3="amit";// same as s1 (point to same amit block)
        
        // functions of String
        //lenght-> count lenght of string
        //charAt(index)-> give character through index
        //toUpperCase()-> converts string to uppercase
        //toLowerCase-> converts string to lowercase
        //concat(str)-> add two string[s1.concat(s2)]
        //equals()-> check to string values[s1.equals(s2)]
       // equalsIgnoreCase(str)->same as equal but ignore case
      // compareTo(str)->if two string same then return 0,if not then return (-1,1)
      //Substring(start)->give characters from start
      //Substring(start,end)->print characters from start to end(excluding  end)
      //Contains(str)->check str is in string or not?
      //startswith(str)->check string starts with str
      //endsWith(str)-> check string end with str
      //indexOf(ch)->gives the index of ch from string
      //lastIndexOf(ch)->give last index of ch (if ch is in string)
     //trim()->trim space from start and end
    //replace(old,new)->replace old char from new char
    //split-(char)> split from char and stores in array
    //isEmpty()->check string is empty or not
    //CharArray->convert string into array [stores in array].
    }
 }
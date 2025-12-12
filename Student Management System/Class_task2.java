import java.util.*;

//student class
class Student{
    private String id,name;
    private int marks;

    public Student(String id,String name){
        this.id=id;
        this.name=name;
    }
    public Student(String id,String name, int marks){
        this.id=id;
        this.name=name;
        this.marks=marks;
    }
    public String getId(){return  id;}
    public String getName(){return  name;}
    public int getMarks(){return marks;}

    public String getRole(){return "undergrad";};

    @Override
    public String toString(){
        return id+" "+name+" "+"( "+marks+" ) "+getRole();
    }
}

//graduate student class
class GraduateStudent extends Student{
    private String area;
    //won't work without a constructor in child
    public GraduateStudent(String id,String name,int marks,String area){
        super(id,name,marks);
        this.area=area;
    }
    @Override
    public String getRole(){
        return "Grad( "+area+" )";
    };
}

//Teacher Sub class
class Teacher extends Student{
    private String subject;
    public Teacher(String id,String name,String subject){
        super(id,name);
        this.subject=subject;
    }
    @Override
    public String getRole(){
        return "Teacher( "+subject+" ) ";
    }
    @Override
    public String toString(){
        return getId()+" "+getName()+" "+subject+" "+getRole();
    }
}

//Report Generation
class Report{
    public Map<String,Student> data;
    public Report(Map<String,Student> data){
        this.data=data;
    }
    public void generate(){
        for(Map.Entry<String,Student> entry: data.entrySet()){
            System.out.println(entry.getKey()+" "+entry.getValue().getName()+" "+entry.getValue().getMarks());
        }
    }
}
//genric class
class Repository<T>{
    private Map<String, T> data=new HashMap<>();

    public void save(String id,T obj){
        data.put(id,obj);
    }
    public T find(String id){
        return data.get(id);
    }
    public void delete(String id){
        data.remove(id);
    }
    public Map<String,T> report(){return data;};
}
public class Class_task2 {
    public static void main(String[] args) {
        List<Student> list =new ArrayList<>();
        list.add(new Student("s1","Gurveer",99));
        list.add(new Student("s2","Skand",95));
        list.add(new Student("s3","Tarak",85));
        list.add(new GraduateStudent("g1","Ansh",85,"CSE"));
        list.add(new Teacher("t1","Dave","English"));


        Repository<Student> repo= new Repository<>();
        for(Student s:list){
            repo.save(s.getId(),s);
        }

        //Print all
        System.out.println("ALL:");
        list.forEach(System.out::println);

        //Find
        System.out.println("\nLookUp s2: ");
        Student s=repo.find("s2");
        System.out.println(s!=null?s:"Not Found");

        //Report
        System.out.println("\nReport: ");
        Map<String,Student> data=repo.report();
        Report rep=new Report(data);
        rep.generate();


        //Delete
        Iterator<Student> it = list.iterator();
        while (it.hasNext()){
            Student st=it.next();
            if(st.getMarks()<90){
                it.remove();
                repo.delete(st.getId());
            }
        }
        System.out.println("\nAfter Removal: ");
        list.forEach(System.out::println);
    }
}

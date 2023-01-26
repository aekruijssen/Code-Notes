// Java

// Overloading - two methods have same name, different type or # args
public double computeArea(Circle c) {}
public double computeArea(Square s) {}

// Overriding - method has same name and func sig as super class method
public abstract class Shape {
    public void printMe() {
        System.out.println("I am a shape.");
    }
    public abstract double computeArea();
}

public class Circle extends Shape {
    private double rad = 5;
    public void printMe() {
        System.out.println("I am a circle.");
    }

    public double computeArea() {
        return rad * rad * 3.15;
    }
}

public class Ambiguous extends Shape {
    private double area = 10;
    public double computeArea() {
        return area;
    }
}

/*
    Below code output:

    I am a circle.
    78.75
    I am a shape.
    10.0
*/
public class IntroductionOverriding {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[2];
        Circle cirle = new Circle();
        Ambiguous ambiguous = new Ambiguous();

        shapes[0] = circle;
        shapes[1] = ambiguous;

        for (Shape s : shapes) {
            s.printMe();
            System.out.println(s.computeArea());
        }
    }
}


// Collection Framework

// ArrayList:AnArrayList is dynamically resizing array
ArrayList<String> myArr = new ArrayList<String>();
myArr.add("one");
myArr.add("two");
System.out.println(myArr.get(0)); // prints <one>

// Vector: like ArrayList but synchronized
Vector<String> myVect = new Vector<String>();
myVect.add("one");
myVect.add("two");
System.out.println(myVect.get(0));

// LinkedList
LinkedList<String> myLinkedList = new LinkedList<String>();
myLinkedList.add("two");
myLinkedList.addFirst("one");
Iterator<String> iter = myLinkedList.iterator();
while (iter.hasNext()) {
    System.out.println(iter.next());
}

// HashMap
HashMap<String, String> map = new HashMap<String, String>();
map.put("one", "uno");
map.put("two", "dos");
System.out.println(map.get("one"));
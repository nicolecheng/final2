import java.util.*;

public class MyDeque<T>{

    T[]data;
    int start,end; // start and end indices
    int size;

    public void debug(){
	System.out.println("size: "+size);
	System.out.println("arylen: "+data.length);
	System.out.println("start: "+start);
	System.out.println("end: "+end);
	System.out.println("first: "+getFirst());
	System.out.println("last: "+getLast());
	System.out.println("isFull: "+(size==data.length));
	System.out.println(toString());
    }
	
    @SuppressWarnings("unchecked")	    
    public MyDeque(){
	data = (T[]) new Object[10];
	start = 0;
	end = 0;
	size = 0;
    }

    public int size(){
	return size;
    }

    // 0b. You need a private method to grow the array and copy over the values.
    @SuppressWarnings("unchecked")	    
    private void grow(){
	T[]hold = (T[]) new Object[size*2];
	int m = 0;
	for(int i = 0; i < size; i++){
	    if(start+i<size){
		hold[i] = data[start+i];
		m++;
	    }else{
		hold[i] = data[i-m];
	    }
	}
	data = hold;
	start = 0;
	end = size-1;
	//size *= 2;
    }

    
    // 1. void addFirst(T value),  2. void addLast(T value)
    // -When the array is full, re-size, then add. 
    // -No exceptions are required since you will re-size.

    public void addFirst(T value){
	if(size==data.length){
	    grow();
	}
	if(start==0){
	    if(size>0){
		start = data.length-1;
	    }
	}else{
	    start--;
	}
	data[start] = value;
	size++;
    }

    public void addLast(T value){
	if(size==data.length){
	    grow();
	}
	if(end==data.length-1 || data[end] == null){
	    end = 0;
	}else{
	    end++;
	}
	data[end] = value;
	size++;
    }        

    
    // 3. T removeFirst(), 4. T removeLast()  
    // -NoSuchElementException is thrown when there are no elements.

    public T removeFirst(){
	T hold;
	if(size==0){
	    throw new NoSuchElementException();
	}else{
	    hold = data[start];
	    if(start==data.length-1){
		start = 0;
	    }else{
		start++;
	    }
	    size--;
	    return hold;
	}
    }

    public T removeLast(){
	T hold;
	if(size==0){
	    throw new NoSuchElementException();
	}else{
	    hold = data[end];
	    if(end==0){
		end = data.length-1;
	    }else{
		end--;
	    }
	    size--;
	    return hold;
	}
    }

    
    // 5. T getFirst(), 6. T getLast()
    // -NoSuchElementException is thrown when there are no elements. 

    public T getFirst(){
	if(size==0){
	    throw new NoSuchElementException();
	}
	return data[start];
    }

    public T getLast(){
	if(size==0){
	    throw new NoSuchElementException();
	}
	return data[end];
    }

   public String toString(){
        String s="[";
        for(int i = 0; i < size; i++){
	    if(start+i<data.length){
		s += data[start+i];
	    }else{
		s += data[i-(data.length-start)];
	    }
	    if(i < size-1){
		s += ",";
	    }
	}
	return s+"]";
    }

    public String toString1(){
	String s = "[";
	for(int i = 0; i < data.length; i++){
	    s += data[i];
	    if(i < data.length-1){
		s += ",";
	    }
	}
	return s + "]";
    }

    
    public static void main(String[]args){
	MyDeque<Integer> m = new MyDeque<Integer>();
	/*
	m.addLast(9);
	//m.debug();
	m.addFirst(1); // [1,9]
	System.out.println(m.getLast());
	m.removeLast();
	System.out.println(m.getLast());

	
	//m.debug();
	m.addFirst(10);
	System.out.println(m);
	//m.debug();
	
	m.addFirst(0);
	System.out.println(m); // [0,10,1,9]
	m.addFirst(3);
	System.out.println(m); // [3,0,10,1,9]
	m.addLast(2);
	m.addFirst(4); // [4,3,0,10,1,9,2]
	System.out.println(m);
	m.removeLast();
	m.removeLast(); // [4,3,0,10,1]
	System.out.println(m);
	m.removeFirst(); // [3,0,10,1]
	System.out.println(m);
	*/
    }
    
}

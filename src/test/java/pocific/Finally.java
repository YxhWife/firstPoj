package pocific;

public class Finally {
	
	@SuppressWarnings("finally")
	public static int fun1(){
		int a;
		try{
			a = 1;
			return a;
		}finally{
			a = 2;
			return a;
		}
	}
	
	public static void print1(){
		try{
			System.out.println("print1****try()");
		}finally{
			System.out.println("print1****finally()");
		}
	}
	
    public static int fun111(){
        System.out.println("-------fun111()---------");
        return 111;
    }

    public static int fun222(){
        System.out.println("--------fun222()---------");
        return 222;
    }
	
    @SuppressWarnings("finally")
	public static int print2(){
    	try{
    		return fun111();
    	}finally{
    		return fun222();
    	}
    }
    
	public static void main(String args[]){
		System.out.println(fun1());
		print1();
		System.out.println(print2());
	}

}

package test;

public class Exchange {
	
	private Integer num1;
	private Integer num2;
	
	public Exchange(Integer num1,Integer num2){
		this.num1 = num1;
		this.num2 = num2;
	}

	public Integer getNum1() {
		return num1;
	}

	public void setNum1(Integer num1) {
		this.num1 = num1;
	}

	public Integer getNum2() {
		return num2;
	}

	public void setNum2(Integer num2) {
		this.num2 = num2;
	}

	public static void swap(Exchange e){
		Integer temp;
		temp = e.num1;
		e.num1 = e.num2;
		e.num2 = temp;
	}
	
	public static void main(String args[]) {
		Exchange e = new Exchange(1, 2);
		swap(e);
		System.out.println(e.getNum1()+"   "+e.getNum2());
	}
}

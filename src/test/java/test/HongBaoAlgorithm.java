package test;

import java.util.Random;
public class HongBaoAlgorithm {
	
	private Random random;
	public HongBaoAlgorithm() {
		random = new Random();  
		random.setSeed(System.currentTimeMillis());  
	}
      
      
    /** 
     *  
     * @param total   红包总额 
     * @param count   红包个数 
     * @return 存放生成的每个小红包的值的数组 
     */  
    public Long[] generate(long total, int count) {  
        Long[] result = new Long[count];  
  
        long average = total / count;
        long max = (long)(average*2.0);
        long min = (long)(average*0.2);
        
        //这样的随机数的概率实际改变了，产生大数的可能性要比产生小数的概率要小。  
        //这样就实现了大部分红包的值在平均数附近。大红包和小红包比较少。  
  
        for (int i = 0; i < result.length; i++) {  
            //因为小红包的数量通常是要比大红包的数量要多的，因为这里的概率要调换过来。  
            //当随机数<平均值，则产生小红包  
            //当随机数>平均值，则产生大红包  
        	long randomVal = nextLong(min, max);
            if (randomVal < average) {  
                // 在平均线上减钱  
                long temp = min + xRandom(min, average);  
                result[i] = temp;  
                total -= temp;  
            } else {  
                // 在平均线下加钱  
                long temp = average + xRandom(average, max);  
                result[i] = temp;  
                total -= temp;  
            }  
        }  
        // 如果还有余钱，则尝试加到小红包里，如果加不进去，则尝试下一个。  
        while (total > 0) {
            for (int i = 0; i < result.length; i++) {  
                if (total > 0 && result[i] < max) {  
                    result[i]++;  
                    total--;  
                }  
            }  
        }  
        // 如果钱是负数了，还得从已生成的小红包中抽取回来  
        while (total < 0) {  
            for (int i = 0; i < result.length; i++) {  
                if (total < 0 && result[i] > min) {  
                    result[i]--;  
                    total++;  
                }  
            }  
        }  
        return result;  
    }  
  
    /** 
     * 生产min和max之间的随机数，但是概率不是平均的，从min到max方向概率逐渐加大。 
     * 先平方，然后产生一个平方值范围内的随机数，再开方，这样就产生了一种“膨胀”再“收缩”的效果。 
     *  
     * @param min 
     * @param max 
     * @return 
     */  
    private long xRandom(long min, long max) {  
    	long tempSqr = (max - min)*(max - min);
    	long tempNext = nextLong(tempSqr);
        return (long)Math.sqrt(tempNext);  
    }
    
    private long nextLong(long n) {
        return random.nextInt((int) n);  
    }  
  
    private long nextLong(long min, long max) {
        return random.nextInt((int) (max - min + 1)) + min;  
    } 
    
    
    public static int getVal(int total,int num, Random r){
    	if(num == 1){
    		System.out.println(total);
    		return total;
    	}
    	int avg = (int)((total/num)*2);
    	int valTemp = r.nextInt(avg);
    	if(valTemp==0){
    		valTemp++;
    	}
    	//新该——最小值为0.5*avg
    	int halfAvg = (int) ((int)(total/num)*0.5);
    	while(valTemp<halfAvg){
    		valTemp = r.nextInt(avg);
    	}
    	System.out.println(valTemp);
    	return valTemp;
    }
    
    public static int getModVal(int money,int num,Random r){
    	//1_个数为1
    	if(num == 1){
    		System.out.println(money);
    		return money;	
    	}
    	
    	int avg = money/num;
    	
    	//2_5%的概率分得10*avg
//    	int tenRatio = r.nextInt(100);
//    	if(tenRatio<5){
//    		int leftAvg = (money - 10*avg)/(num-1);
//    		if(leftAvg>0){
//    			System.out.println(10*avg);
//    			return 10*avg;
//    		}
//    	}
    	
    	//3_最小值为0.5*avg
    	int doubleAvg = avg*2;
    	int halfAvg = (int) (avg*0.5);
    	int valTemp = halfAvg + r.nextInt(doubleAvg - halfAvg);
    	if(valTemp==0){
    		valTemp++;
    	}
    	System.out.println(valTemp);
    	return valTemp;
    }
    public static void main(String args[]){
    	int N = 10;
    	int totalAmount = 10;
    	int num = N;
    	int val = 0;
    	int count = 0; 
    	Random random = new Random();
    	random.setSeed(System.currentTimeMillis());
    	System.out.println(System.currentTimeMillis());
    	for(int i=0;i<N;i++){
    		val = getModVal(totalAmount, num--, random);
    		count += val;
    		totalAmount -= val;
    		if(totalAmount<=0){
    			break;
    		}
    	}
    	System.out.println(System.currentTimeMillis());
    	System.out.println("count=" + count);
    }
    
}  

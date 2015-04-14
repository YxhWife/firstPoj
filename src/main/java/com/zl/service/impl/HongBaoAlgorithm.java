package com.zl.service.impl;

import java.util.Random;

import org.springframework.stereotype.Component;
import com.zl.service.ActiveStrategy;

@Component
public class HongBaoAlgorithm implements ActiveStrategy{
	
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
  
        Long average = total / count;
        long max = (long)(average*1.5);
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
    
    public static void main(String[] args) throws Exception {  
    	HongBaoAlgorithm hba = new HongBaoAlgorithm();
        Long[] result = hba.generate(17770, 100);  
        long total = 0;  
        for (int i = 0; i < result.length; i++) {  
            System.out.println(result[i]);  
            total += result[i];  
        }  
        //检查生成的红包的总额是否正确  
        System.out.println("total:" + total);  
  
        //统计每个钱数的红包数量，检查是否接近正态分布  
//            int count[] = new int[(int) max];  
//            for (int i = 0; i < result.length; i++) {  
//                count[(int) result[i]] += 1;  
//            }  
//      
//            for (int i = 0; i < count.length; i++) {  
//                System.out.println("" + i + "  " + count[i]);  
//            } 
//            String zl = DesUtils.encrypt("zhanglin");
//            System.out.println("加密之后的值：" + zl);
//            zl = DesUtils.decrypt(zl);
//            System.out.println("解密之后的值：" + zl);
    } 
    
}  

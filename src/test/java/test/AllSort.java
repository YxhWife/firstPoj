package test;

import java.util.ArrayList;
import java.util.List;

public class AllSort {
	private List<Integer[]> list = new ArrayList<Integer[]>();
	
	public List<Integer[]> getList(){
		return list;
	}
	
	public List<Integer[]> allSort(Integer[] a,int begin,int length){
		Integer [] temp;
		if(begin>length-1){
			temp = new Integer[length];
			for(int i = 0; i <begin ; i++){
				temp[i] = a[i];
			}
			list.add(temp);
		}
		for(int i=begin;i<length;i++){
			if(a[begin]!=a[i]||i==begin){
				swap(a, begin, i);
				allSort(a,begin+1,length);
				swap(a, begin, i);
			}
		}
		return list;
	}

	private void swap(Integer[]a,int i,int j){
		Integer temp;
		temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	public static void main(String[] args) {
		AllSort allSort = new AllSort();
		Integer a[] = {1,2,2};
		List<Integer[]> list = allSort.allSort(a, 0, a.length);
		
		for (int i = 0; i < list.size(); i++) {
			Integer[] arr = list.get(i);
			for(int j = 0;j<arr.length;j++){
				System.out.print(arr[j]+",");
			}
			System.out.println();
		}
	}

}

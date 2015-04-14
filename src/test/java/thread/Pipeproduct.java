package thread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PipedOutputStream;

public class Pipeproduct implements Runnable{
	
	private static final String FILE_NAME = "F:/yxh/yxh.php";
	private PipedOutputStream po;
	private File file;
	
	public Pipeproduct(PipedOutputStream po) {
		file = new File(FILE_NAME);
		this.po = po;
	}

	@Override
	public void run() {
		BufferedReader br = null;
		try {

			FileReader fr = new FileReader(file);
			br = new BufferedReader(fr);
			String temp = null;
			while((temp = br.readLine())!=null){
				//管道的读写操作对象都是byte[]
				po.write(temp.getBytes());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
				po.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	} 
}

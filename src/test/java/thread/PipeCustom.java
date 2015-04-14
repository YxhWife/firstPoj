package thread;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PipedInputStream;

public class PipeCustom implements Runnable{
	private static final String FILE_NAME = "f:/zl/zl.php";
	private PipedInputStream pi;
	private File file ;
	
	public PipeCustom(PipedInputStream pi) {
		file = new File(FILE_NAME);
		this.pi = pi;
	}

	@Override
	public void run() {
		BufferedWriter bw = null;
		try {
			
			FileWriter fw = new FileWriter(file);
		    bw = new BufferedWriter(fw);
			byte[] conten = new byte[1000];
			int len = 0;
			while((len = pi.read(conten,0,1000))>-1){
				bw.write(new String(conten,0,len));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				bw.close();
				pi.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}

package com.wanghui.thread10;


/**
 * µ¥Àý  doublecheckÄ£Ê½
 * @author Administrator
 *
 */
public class DoubbleSingletion {

	private static DoubbleSingletion singletion = null;
	
	private DoubbleSingletion(){
		
	}
	
	public static DoubbleSingletion getInstance(){
		if(singletion == null){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized (DoubbleSingletion.class) {
				if(singletion == null){
					singletion = new DoubbleSingletion();
				}
			}
		}
		return singletion;
	}
}

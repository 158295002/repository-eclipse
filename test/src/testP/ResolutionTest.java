package testP;

/**
 * 屏幕分辨率的获取测试
 * @author dingqin
 * @date 2017年8月4日
 *
 */
public class ResolutionTest {

	public static void main(String[] args) {
		int screenWidth=((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
        int screenHeight = ((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().height); 
        System.out.println(screenWidth+""+screenHeight);
	}
}

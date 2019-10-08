
package edu.monash.erc.pippin.service;

public interface LiuruiService {
    /**
     * 屏幕输出一个Hello
     */
    public void method1();
    
    /**
     * 屏幕打印msg
     * @param msg 
     */
    public void method2(String msg);
    
    
    /**
     * 返回left+right
     * @param left
     * @param right
     * @return 
     */
    public int method3(int left,int right);
    
    /**
     * 返回left>right
     * @param left
     * @param right
     * @return 
     */
    public boolean method4(int left,int right);

    /**
     * 屏幕打印inputPath，返回booelan
     * @param inputPath
     * @return 
     */
    public boolean method5(String inputPath);
}





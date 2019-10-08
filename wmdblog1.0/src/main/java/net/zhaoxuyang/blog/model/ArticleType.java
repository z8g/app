/*
__        ____  __ ____    ____  _     ___   ____ 
\ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
 \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
  \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
   \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
 */ 
package net.zhaoxuyang.blog.model;

/**
 *
 * @author zhaoxuyang
 */
public enum ArticleType {
    ORIGINAL("原创",0),
    TRANSPOND("转发",1);
    
    private final String title;
    private final int value;
    
    private ArticleType(String aTitle, int aValue){
        title = aTitle;
        value = aValue;
    }

    public String getTitle() {
        return title;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return title;
    }
    
    
}

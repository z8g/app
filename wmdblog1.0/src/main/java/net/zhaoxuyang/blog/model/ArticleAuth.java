/*
__        ____  __ ____    ____  _     ___   ____ 
\ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
 \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
  \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
   \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
 */ 
package net.zhaoxuyang.blog.model;

/**
 * 文章权限
 *
 * @author zhaoxuyang
 */
public enum ArticleAuth {

    PUBLIC("公开可见", 0),
    FRIEND("好友可见", 1),
    PRIVATE("仅自己可见", 2);

    private final String title;
    private final int value;

    private ArticleAuth(String aTitle, int aValue) {
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

    public static ArticleAuth valueOf(int aValue) {
        switch (aValue) {
            case 0:
                return PUBLIC;
            case 1:
                return FRIEND;
            case 2:
                return PRIVATE;
            default:
                return PUBLIC;
        }
    }

}

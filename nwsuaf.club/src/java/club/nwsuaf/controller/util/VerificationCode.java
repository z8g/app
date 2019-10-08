package club.nwsuaf.controller.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
public class VerificationCode {

    public static final String KEY = "VerificationCode";
    public static boolean check(String checkcode, HttpSession session) {
        boolean result = false;
        if (session != null && checkcode != null) {
            result = checkcode.equals(session.getAttribute("VerificationCode"));
            System.err.println(session.getAttribute("VerificationCode"));
        }
        return result;
    }

    public static void create( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width = 78;
        int height = 20;
        //创建对象
        BufferedImage bim = new BufferedImage(68, 20, BufferedImage.TYPE_INT_RGB);
        //获取图片对象bim的图形上下文对象g，这个g的功能如同一支绘图笔，程序中使用这支笔来
        //绘制、修改图片对象bim。
        Graphics g = bim.getGraphics();
        Random rm = new Random();
        g.setColor(new Color(rm.nextInt(100), 205, rm.nextInt(100)));
        g.fillRect(0, 0, width, height);
        StringBuffer sbf = new StringBuffer("");
        //输出数字
        for (int i = 0; i < 4; i++) {
            g.setColor(Color.black);
            g.setFont(new Font("华文隶书", Font.BOLD | Font.ITALIC, 22));
            int n = rm.nextInt(10);
            sbf.append(n);
            g.drawString("" + n, i * 15 + 5, 18);
        }
        //生成的验证码保存到session中
        HttpSession session = request.getSession();
        session.setAttribute("VerificationCode", sbf);
        //禁止缓存
        response.setHeader("Prama", "no-cache");
        response.setHeader("Coche-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        //将bim图片以“JPG”格式返回给浏览器。
        ImageIO.write(bim, "JPG", response.getOutputStream());
        response.getOutputStream().close();
    }
}

package com.zxy97.aes;

import java.io.File;
import java.util.UUID;

public class ZipCipherUtil {
    /**
     * 对目录srcFile下的所有文件目录进行先压缩后加密,然后保存为destfile
     *
     * @param srcFile 要操作的文件或文件夹
     * @param destfile 压缩加密后存放的文件
     * @param keyStr 密钥
     * @throws java.lang.Exception
     */
    public static void encryptZip(String srcFile, String destfile, String keyStr) throws Exception {
        File temp = new File(UUID.randomUUID().toString() + ".zip");
        temp.deleteOnExit();
        // 先压缩文件
        new ZipUtil().zip(srcFile, temp.getAbsolutePath());
        // 对文件加密
        new CipherUtil().encrypt(temp.getAbsolutePath(), destfile, keyStr);
        temp.delete();
    }

    /**
     * 对文件srcfile进行先解密后解压缩,然后解压缩到目录destfile下
     *
     * @param srcfile 要解密和解压缩的文件名
     * @param destfile 解压缩后的目录
     * @param keyStr  密钥
     * @throws java.lang.Exception
     */
    public static void decryptUnzip(String srcfile, String destfile, String keyStr) throws Exception {
        File temp = new File(UUID.randomUUID().toString() + ".zip");
        temp.deleteOnExit();
        // 先对文件解密
        new CipherUtil().decrypt(srcfile, temp.getAbsolutePath(), keyStr);
        // 解压缩
        new ZipUtil().unZip(temp.getAbsolutePath(),destfile);
        temp.delete();
    }
    
    public static final String FILE_NAME_EXTENSION = "zxy97";
    
    
    public static void main(String[] args) throws Exception {
        long l1 = System.currentTimeMillis();
        ZipCipherUtil.encryptZip("d:\\soft", "d:\\yaxin.zip", "123");//加密
        ZipCipherUtil.decryptUnzip("d:\\yaxin.zip", "d:\\yaxin2", "123");//解密
        long l2 = System.currentTimeMillis();
        System.out.println((l2 - l1) + "毫秒.");
        System.out.println(((l2 - l1) / 1000) + "秒.");
    }
}




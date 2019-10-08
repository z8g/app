
package com.zxy97.dsa;

import com.zxy97.util.Base64Util;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class DSAUtil {

    private static final String algorithm = "DSA";
    private static final String KEY_PUBLIC = "PublicKey";
    private static final String KEY_PRIVATE = "PrivateKey";

    /**
     * 生成公钥串、私钥串
     *
     * @return Base64编码过的公钥串、Base64编码过的私钥串
     * @throws java.lang.Exception
     */
    public static Map<String, String> generatorKey() throws Exception {
        Map<String, String> map = new HashMap<>();
        try {
            KeyPairGenerator keygen = KeyPairGenerator.getInstance(algorithm);
            // 创建随机产生器
            SecureRandom secureRandom = new SecureRandom();
            secureRandom.setSeed("abcdefg".getBytes());
            keygen.initialize(1024, secureRandom);

            KeyPair keys = keygen.genKeyPair();
            // 生成公钥
            DSAPublicKey publicKey = (DSAPublicKey) keys.getPublic();
            map.put(KEY_PUBLIC, Base64Util.encode(publicKey.getEncoded()));
            // 生成私钥
            DSAPrivateKey privateKey = (DSAPrivateKey) keys.getPrivate();
            map.put(KEY_PRIVATE, Base64Util.encode(privateKey.getEncoded()));
        } catch (NoSuchAlgorithmException e) {
        }
        return map;
    }

    /**
     * 用私钥对订单串进行签名
     *
     * @param orderInfo 未加密订单串
     * @param privateKey Base64编码过的私钥串
     *
     * @return Base64编码过的签名串
     */
    public static String generatorSign(String orderInfo, String privateKey) {
        try {
            // 根据私钥byte[]创建PKCS8EncodedKeySpec对象
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64Util.decode(privateKey));
            // 指定DSA加密算法
            KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
            // 取得私钥对象
            PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
            // 用私钥对订单串进行签名
            Signature signature = Signature.getInstance(keyFactory.getAlgorithm());
            signature.initSign(priKey);
            signature.update(orderInfo.getBytes());
            // 对私钥进行Base64编码
            return Base64Util.encode(signature.sign());
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 校验数字签名
     *
     * @param orderInfo 未加密订单串
     * @param publicKey Base64编码过的公钥串
     * @param sign Base64编码过的签名串
     *
     * @return 校验成功返回true, 校验失败返回false
     */
    public static boolean verifySign(String orderInfo, String publicKey, String sign) {
        try {
            // 根据公钥byte[]创建X509EncodedKeySpec对象
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64Util.decode(publicKey));
            // 指定DSA加密算法
            KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
            // 取公钥对象
            PublicKey pubKey = keyFactory.generatePublic(keySpec);
            // 根据公钥和待加密串初始化Signature
            Signature signature = Signature.getInstance(keyFactory.getAlgorithm());
            signature.initVerify(pubKey);
            signature.update(orderInfo.getBytes());
            // 根据公钥和订单串进行签名校验，通过表示正常，否则表示订单串是伪造的。
            return signature.verify(Base64Util.decode(sign));
        } catch (Exception e) {
        }
        return false;
    }

    public static void main(String[] args) {
//		// 产生公钥和私钥，保存起来下次使用
//		Map<String, String> keyMap = generatorKey();
//		// 获得公钥
//		String publicKey = keyMap.get(KEY_PUBLIC);
//		// 获得私钥
//		String privateKey = keyMap.get(KEY_PRIVATE);
//		System.err.println("公钥 : " + publicKey);
//		System.err.println("私钥 : " + privateKey);

        String privateKey = "MIIBSwIBADCCASwGByqGSM44BAEwggEfAoGBAP1/U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7WT2NWPq/xfW6MPbLm1Vs14E7gB00b/JmYLdrmVClpJ+f6AR7ECLCT7up1/63xhv4O1fnxqimFQ8E+4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC/BYHPUCgYEA9+GghdabPd7LvKtcNrhXuXmUr7v6OuqC+VdMCz0HgmdRWVeOutRZT+ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN/C/ohNWLx+2J6ASQ7zKTxvqhRkImog9/hWuWfBpKLZl6Ae1UlZAFMO/7PSSoEFgIUFzPw6lbL1g8H4Nj89G55StSxqPQ=";
        String publicKey = "MIIBtzCCASwGByqGSM44BAEwggEfAoGBAP1/U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7WT2NWPq/xfW6MPbLm1Vs14E7gB00b/JmYLdrmVClpJ+f6AR7ECLCT7up1/63xhv4O1fnxqimFQ8E+4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC/BYHPUCgYEA9+GghdabPd7LvKtcNrhXuXmUr7v6OuqC+VdMCz0HgmdRWVeOutRZT+ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN/C/ohNWLx+2J6ASQ7zKTxvqhRkImog9/hWuWfBpKLZl6Ae1UlZAFMO/7PSSoDgYQAAoGAdFQMgDfhSx4MMvmtjRBvEnS+Q6RVLqbi2aweBsQKpsg48FtnJed/MaPhNWonsXltK/9rdeh59rws61FsoLy7A6AA+jYydqIcOG2vK9u94HDg3ueEttZ5of3/KP6bsFLiivfLqMefi9MOGyIafaffpGA+UPI4tPh1E0GH53TnCaI=";
        // 待签名的订单串
        String orderInfo = "orderId=1001&price=10.1&desc=书本&num=1";
        // 产生签名
        String sign = generatorSign(orderInfo, privateKey);
        System.err.println("签名 : " + sign);

        // 验证签名
        boolean result = verifySign(orderInfo, publicKey, sign);
        System.err.println("签名校验结果: " + result);
    }
}

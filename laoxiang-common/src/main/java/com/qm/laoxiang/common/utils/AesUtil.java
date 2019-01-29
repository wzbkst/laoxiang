package com.qm.laoxiang.common.utils;

import com.google.common.base.Charsets;
import com.qm.laoxiang.common.exception.ServiceException;
import com.qm.laoxiang.common.web.domain.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.commons.codec.binary.Base64;

public class AesUtil {
    private static Logger logger = LoggerFactory.getLogger(AesUtil.class);
    private static final String KEY_FLAG = "AES";
    /**
     * 默认的加密算法
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    /**
     * 用于生成秘钥的key
     *
     */
    public static final String KEY_RULE = "laoxiang";

    /**
     * 加密
     * 1.构造密钥生成器
     * 2.根据encodeRules规则初始化密钥生成器
     * 3.产生密钥
     * 4.创建和初始化密码器
     * 5.内容加密
     * 6.返回字符串
     */
    public static String encrypt(String encodeRules, String content) throws ServiceException {
        try {
            SecretKey key = getSecretKey(encodeRules);
            //6.根据指定算法AES生成密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            //7.初始化密码器
            cipher.init(Cipher.ENCRYPT_MODE, key);
            //8.获取加密内容的字节数组
            byte[] byte_encode = content.getBytes("utf-8");
            //9.根据密码器的初始化方式一一加密，将数据加密
            byte[] byte_AES = cipher.doFinal(byte_encode);
            //10.将加密后的数据使用Base64转化为可见字符串
            String encryptData = new String(Base64.encodeBase64((byte_AES)), Charsets.UTF_8);
            return encryptData;
        }catch (Exception e){
            logger.error("生成加密内容失败", e);
            throw new ServiceException(ResultCode.INTERNAL_ERR.getCode(), "生成加密内容失败");
        }
    }

    /**
     * 解密
     * 1.同加密
     * 2.将加密后的字符串生成byte[]
     * 3.解密
     */
    public static String decrypt(String encodeRules, String encryptedData) throws ServiceException {
        try {
            SecretKey key = getSecretKey(encodeRules);
            //6.根据指定算法AES生成密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            //7.初始化密码器
            cipher.init(Cipher.DECRYPT_MODE, key);
            //8.将加密并编码后的内容解码成字节数组
            byte[] byte_content = Base64.decodeBase64(encryptedData);
            //9.解密
            byte[] byte_decode = cipher.doFinal(byte_content);
            String decryptData = new String(byte_decode, "utf-8");
            return decryptData;
        }catch (Exception e){
            logger.error("解密内容失败", e);
            throw new ServiceException(ResultCode.INTERNAL_ERR.getCode(), "解密内容失败");
        }
    }

    private static SecretKey getSecretKey(String password) throws ServiceException{
        try {
            //1.构造密钥生成器，指定为AES算法，不区分大小写
            KeyGenerator keygen = KeyGenerator.getInstance(KEY_FLAG);
            //2.根据encodeRules规则初始化密钥生成器
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(password.getBytes(Charsets.UTF_8));
            keygen.init(128, random);
            //3.产生原始对称密钥
            SecretKey originalKey = keygen.generateKey();
            //4.获得原始对称密钥的字节数组
            byte[] raw = originalKey.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKey key = new SecretKeySpec(raw, KEY_FLAG);
            return key;
        }catch (Exception e){
            logger.error("生成加密key失败", e);
            throw new ServiceException(ResultCode.INTERNAL_ERR.getCode(), "生成加密key失败");
        }
    }

    public static void main(String[] args) throws ServiceException{
        /**
         * 加密
         */
        String content = "qi";
        String encodeContent = AesUtil.encrypt(AesUtil.KEY_RULE, content);
        System.out.println("encodeContent:" + encodeContent);
        /**
         * 解密
         */
        System.out.println("decodeContent:" + AesUtil.decrypt(AesUtil.KEY_RULE, encodeContent));
    }
}

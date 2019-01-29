package com.qm.laoxiang.common.utils;

import com.qm.laoxiang.common.exception.ServiceException;
import com.qm.laoxiang.common.web.domain.ResultCode;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

public class AesPadding7Utils {
    private static Logger LOGGER = LoggerFactory.getLogger(AesPadding7Utils.class);

    /**
     * 算法名称
     */
    public static final String KEY_ALGORITHM = "AES";
    /**
     * 加解密算法/模式/填充方式
     */
    public static final String AES_CBC_7_PADDING = "AES/CBC/PKCS7Padding";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * AES/CBC/PKCS7Padding解密
     * @param encryptedData  Base64编码后的待解密的加密数据
     * @param key Base64编码后的解密key
     * @param iv Base64编码后的初始向量
     * @return
     * @throws ServiceException
     */
    public static String decrypt(String encryptedData, String key, String iv) throws ServiceException{
        byte[] encryptedBytes = Base64.decodeBase64(encryptedData.getBytes());
        byte[] keyBytes = Base64.decodeBase64(key);
        byte[] ivBytes = Base64.decodeBase64(iv);
        try {
            Cipher cipher = Cipher.getInstance(AES_CBC_7_PADDING);
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
            IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] original = cipher.doFinal(encryptedBytes);
            return new String(original);
        } catch (NoSuchPaddingException | InvalidAlgorithmParameterException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException
                | IllegalBlockSizeException e) {
            e.printStackTrace();
            LOGGER.error("使用AES/CBC/PKCS7Padding解密失败", e);
            throw new ServiceException(ResultCode.INTERNAL_ERR.getCode(), "使用AES/CBC/PKCS7Padding解密失败");
        }
    }
}

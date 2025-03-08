package com.vaddi.asymmetric.encrypt.spring_asymmetric_encryt;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.stereotype.Service;

@Service
public class EncDecService {

	private static final String RSA = "RSA";

    public PrivateKey privateKey ;
    public PublicKey publicKey ;

    public void init() throws NoSuchAlgorithmException {
       KeyPair  keyPair = generateKeyPair();
       privateKey = keyPair.getPrivate();
       publicKey = keyPair.getPublic();
    }

    private KeyPair generateKeyPair() throws NoSuchAlgorithmException {
         KeyPairGenerator keyPairGenerator  = KeyPairGenerator.getInstance(RSA);
        keyPairGenerator.initialize(4096);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
     }

      public String encrypt(String data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
          init();
          
          Cipher cipher = Cipher.getInstance(RSA);
          cipher.init(Cipher.ENCRYPT_MODE,publicKey);
          byte[] encryptedValue  = cipher.doFinal(data.getBytes());
          return Base64.getEncoder().encodeToString(encryptedValue);

    }

    public String decrypt(String encryptedData) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE,privateKey);
        byte[] decryptedValue  = cipher.doFinal(Base64.getMimeDecoder().decode(encryptedData));
        return new String(decryptedValue);

    }
}

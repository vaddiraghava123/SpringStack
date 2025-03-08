package com.vaddi.asymmetric.encrypt.spring_asymmetric_encryt;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EncDecController {

	@Autowired
	private EncDecService service;
	@PostMapping("/enc")
	public String encrypt(@RequestBody String data)
			throws NoSuchPaddingException, IllegalBlockSizeException,
			NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
		return service.encrypt(data);
	}

	@PostMapping("/dec")
	public String decrypt(@RequestBody String encryptedData)
			throws NoSuchPaddingException, IllegalBlockSizeException,
			NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
		return service.decrypt(encryptedData);
	}
}

package com.inagro.springboot.backend.apirest.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class Util {

	
	public static String convertBytes(String pathFile) throws IOException {
		byte[] input_file = Files.readAllBytes(Paths.get(pathFile));
		byte[] encodedBytes = Base64.getEncoder().encode(input_file);
		String encodedString = new String(encodedBytes);

		return encodedString;
	}

	public static void deleteFile(String pathFile) throws Exception {
		File file = new File(pathFile);

		if (!file.delete()) {
			throw new Exception("Ocurrió un problema al eliminar el archivo");
		}

	}
}

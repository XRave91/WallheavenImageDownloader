package com.xrave.wall_downloader;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;

/**
 * Created by 2 on 07.01.2017.
 */
public class HttpLoader implements Loader {
	// кстати, а она будет публичной или приватной? проверь
	static final String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11";

	@Override
	public BufferedInputStream getData(String path) {
		URLConnection connection = null;
		try {
			URL link = new URL(path);
			connection = link.openConnection();
			connection.setRequestProperty("User-Agent", userAgent);//записать в константу
			connection.connect();
		} catch (Exception e) {
			// почему на русском?
			System.out.println("Соединение не удалось. Проверьте соединение, и правильность УРЛ адреса");
			return null;
		}

		// тут было великолепно:
		// если null, вернем null, если нет - вернём значение
		try {
			return new BufferedInputStream(connection.getInputStream());
		} catch (IOException e) {
			return null;
		}
	}
}

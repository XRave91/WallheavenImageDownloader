package com.xrave.wall_downloader;

import java.io.*;

/**
 * Created by 2 on 17.01.2017.
 */
public class FileSaver implements Saver {
	// кстати, ты не проверяешь снаружи, удалось ли сохранить файл
	public boolean saveData(BufferedInputStream data, String filename) {
		if (data == null)
			return false;

		File file = new File(filename);
		File folder = new File(file.getParent());

		if (!folder.exists())
			folder.mkdirs();

		BufferedOutputStream fileStream = null;

		try {
			fileStream = new BufferedOutputStream(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}

		try {
			// тут жесть какая-то
			// имхо, должна быть функция, которая это сделает сама, без цикла
			// обязательно найди её и используй!
			int i;
			while ((fileStream = data.read()) != -1)
				fileStream.write(i);
			
			fileStream.close();
		} catch (IOException e) {
			System.out.println("Cannot save file:" + file.getName());
		}

		try {
			data.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
}

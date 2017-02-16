package com.xrave.wall_downloader;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 2 on 07.01.2017.
 */
public class WallHeavenAggregator {
	enum Format {
		JPG,
		PNG,

		public String pathString() {
			switch (this){
				case JPG : return ".jpg";
				case PNG : return ".png";
			}

			return "UNDEFINED"; // а еще лучше - кинуть исключение
		}
	};

	class Data {
		public final BufferedInputStream data;
		public final Format format;

		public Data(final BufferedInputStream data, final Format format) {
			this.data = data;
			this.format = format;
		}
	}

	final Loader loader;
	final Saver saver;

	// никогда не сокращай названия!
	WallHeavenAggregator(final Loader loader, final Saver saver) {
		this.loader = loader;
		this.saver = saver;
	}

	String getUrl(final String name, final Format format) {
		// это можно вынести в конфиг какой-нибудь
		final String PATH = "https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-";

		return PATH + name + format.pathString();
	}

	Data loadImage(final String name) {
		for (Format format : Format.values()) {
			final BufferedInputStream result = loader.getData(name, format);

			if (result != null)
				return new Data(result, format);
		}

		return null;
	}

	void saveImages(final ArrayList<String> imagesId, final String[] tags) {
		BufferedInputStream inputStream = null;

		// а у меня на маке вообще нет такого пути, что делать?
		// вынести в параметры коммандной строки, например
		final String FILE_PATH = "c:\\wallpapes\\";
		final String tagsInfo = String.join("_", tags) + "_"; // раньше ты это делал каждую итерацию цикла, ппц
		final String pathPrefix = FILE_PATH + tagsInfo;
		final String infoPrefix = "/" + imagesId.size() + " " + tagsInfo;

		for (int i = 0, count = imagesId.size(); i < count; ++i) {
			final String imageId = imagesId.get(i);
			final Data data = loadImage(imageId);

			if (data == null) {
				System.out.println("Cannot dowload file with id" + imageId);
				continue;
			}

			final String imageName = imageId + data.format.pathString();
			saver.saveData(data.data, pathPrefix + imageName);
			System.out.println((i + 1) + infoPrefix + imageName + " downloaded");
		}
	}

	void getImages(final int countOfPictures, final String[] tags) {
		final ArrayList<String> imagesId = getImagesId(countOfPictures, tags);
		saveImages(imagesId, tags);
	}

	ArrayList parse(final Pattern imagePattern, final BufferedInputStream pageToParse, final int imagesCount) {
		ArrayList<String> result = new ArrayList<String>();
		String content = null;
		Scanner sc = new Scanner(pageToParse);
		content = sc.useDelimiter("\\Z").next();
		Matcher matcher = imagePattern.matcher(content);

		while (matcher.find() && imagesCount > 0) {
			imagesCount--;
			result.add(matcher.group(2));
		}

		sc.close();

		return result;
	}

	ArrayList<String> getImagesId(final int imagesCount, final String[] tags) {
		int page = 1;
		ArrayList<String> fileIds = new ArrayList<String>(imagesCount);
		// паттерн больше не нужен нигде
		final Pattern imagePattern = Pattern.compile("(class=\"preview\"[^0-9]*\\/wallpaper\\/)([0-9]+)");

		// это опять лучше вынести в конфиг
		final String PATH_PREFIX = "https://alpha.wallhaven.cc/search?q=" + String.join("+", tags) + "&categories=111&page=";

		while (imagesCount > 0) {
			ArrayList<String> tmp = parse(imagePattern, loader.getData(PATH_PREFIX + page), imagesCount);

			// тут было великолепно: сначала ты обращался к tmparraylist.size(), а на следующей строке проверял его на null
			if (tmp == null)
				break;

			++page;
			imagesCount -= tmp.size();
			fileIds.addAll(tmparraylist);
		}

		return fileIds;
	}
}

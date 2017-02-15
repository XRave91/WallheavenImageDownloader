// глянь сюда https://docs.oracle.com/javase/tutorial/java/package/namingpkgs.html
package com.xrave.wall_downloader;

import java.lang.reflect.MalformedParametersException;

public class Main {
	private class InputParameters {
		private int countOfPictures;
		private String[] tags;

		InputParameters(String[] arguments) throws MalformedParametersException {
			if (arguments.length < 1)
				throw new MalformedParametersException("syntax of this downloader  \"dowl.java %countOfPictures% %tags with space delimiters%\"");

			countOfPictures = Integer.parseInt(arguments[0]);
			if (countOfPictures < 1)
				throw new MalformedParametersException("incorrect count Of Pictures");

			tags = new String[arguments.length - 1];
			for (int i = 1; i < arguments.length; ++i)
				tags[i - 1] = arguments[i];
		}
		
		public int getCountOfPictures() {
			return countOfPictures;
		}

		public String[] getCountOfPictures() {
			// тут все равно можно изменить состояние (содержимое массива)
			// можно сделать как тут: http://stackoverflow.com/questions/2419353/make-arraylist-read-only
			return tags;
		}
	}

	public static void main(String[] args) {
		InputParameters parameters = new InputParameters(args);

		WallHeavenAggregator aggregator = new WallHeavenAggregator(new HttpLoader(), new FileSaver());
		aggregator.getImages(parameters.countOfPictures, parameters.tags);
	}
}

package Xrave;

import java.lang.reflect.MalformedParametersException;

public class Main {
    private static class InputParameters {
        int countOfPictures;
        String[] tags;
        InputParameters(String[] arguments) throws MalformedParametersException{
            if (arguments.length<1){
                throw new MalformedParametersException("syntax of this downloader  \"dowl.java %countOfPictures% %tags with space delimiters%\"");
            }
            countOfPictures=Integer.parseInt(arguments[0]);
            if (countOfPictures<1){
                throw new MalformedParametersException("incorrect count Of Pictures");
            }
            tags=new String[arguments.length-1];
            for (int i =1;i<arguments.length;i++){
                tags[i-1]=arguments[i];
            }
        }
    }
    public static void main(String[] args) {
	WallHeavenAggregator aggregator=new WallHeavenAggregator(new HttpLoader(),new FileSaver());
	InputParameters parameters =new InputParameters(args);
	aggregator.getImages(parameters.countOfPictures,parameters.tags);
    }
}

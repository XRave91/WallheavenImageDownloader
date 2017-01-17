package Xrave;

public class Main {

    public static void main(String[] args) {
	WallHeavenAggregator aggregator=new WallHeavenAggregator(new HttpLoader(),new FileSaver());
	aggregator.getImages();
    }
}

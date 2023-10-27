package dust.clientBase.config;

/**
 * 
 * @author wangxuan
 * 
 */
public class Consts {

	public static int TIME_OUT = 30000;

	public static String cityCode = "029";

	public final static String URL = "http://8.130.80.22:8080/RengNaQu/";

	public final static String URL_IMAGE = "http://8.130.80.22:8080/RengNaQu/upload/";



	public static class APP {

		public static final String RegisterAction = "servlet/RegisterAction";
		public static final String MessageAction = "servlet/MessageAction";

	}

	public static class actionId {
		public static final int resultFlag = 1;
		public static final int resultCode = 2;
		public static final int resultMsg = 3;
		public static final int resultState = 4;
		public static final int resultFive = 5;
		public static final int resultSix = 6;
		public static final int resultSeven = 7;
	}
}

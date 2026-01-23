package cn.willow.demo.utils.http;

import java.util.Arrays;
import java.util.List;

/**
 * HTTP 常用方法／首部／请求属性
 */
public final class HttpConst {
	public static final String GET = "GET";
	public static final String PUT = "PUT";
	public static final String POST = "POST";
	public static final String DELETE = "DELETE";
	
	public static final String HEADER_PRAGMA = "Pragma";
	public static final String HEADER_EXPIRES = "Expires";
	public static final String HEADER_CACHE_CONTROL = "Cache-Control";

	/**
	 * 传入参数特定类型
	 */
	public static final String ENTITY_STRING = "$ENTITY_STRING$";
	public static final String ENTITY_FILE = "$ENTITY_FILE$";
	public static final String ENTITY_BYTES = "$ENTITY_BYTES$";
	public static final List<String> SPECIAL_ENTITIY = Arrays.asList(ENTITY_STRING, ENTITY_BYTES, ENTITY_FILE);


	public static final class Request{
		public static final String ATTRIBUTES_USERINFOP = "ui_";
		public static final String ATTRIBUTES_TEXTPREV = "text_";
		public static final String ATTRIBUTES_TAGPREV = "tag_";
		public static final String ATTRIBUTES_POSTPREV = "post_";
		public static final String ATTRIBUTES_CSRFTOKEN = "csrfToken";
		public static final String ATTRIBUTES_SESSIONID = "DOTCOM_USER_SESSIONID";
		public static final String ATTRIBUTES_USERSESSION = "thisUser";
		public static final String ATTRIBUTES_SERVERCONTEXT = "serverContext";
		public static final String ATTRIBUTES_METADATA_NETWORK = "metadataNetwork";
		public static final String ATTRIBUTES_REDIRECT_SERVICE = "redirectService";
		public static final String ATTRIBUTES_NAVIGATION="navigations";
		public static final String ATTRIBUTES_NAVIGATION_SHOW_INDEX="navigationShowIndex";
	}
}

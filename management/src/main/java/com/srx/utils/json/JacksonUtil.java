package com.srx.utils.json;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.srx.user.model.User;
import com.srx.utils.date.DateUtil;

public class JacksonUtil {
	private static ObjectMapper mapper = new ObjectMapper();
	static{
		// 序列化时将null 过滤成""
		mapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
			@Override
			public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers)
					throws IOException, JsonProcessingException {
				gen.writeString("");
			}
		});
		mapper.setDateFormat(new SimpleDateFormat(DateUtil.DATE_TIME_FORMATTER));
	}
	public static String bean2Json(Object obj) throws IOException {
        StringWriter sw = new StringWriter();
        JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);
        mapper.writeValue(gen, obj);
        gen.close();
        return sw.toString();
    }

    public static <T> T json2Bean(String jsonStr, Class<T> objClass)
            throws JsonParseException, JsonMappingException, IOException {
        return mapper.readValue(jsonStr, objClass);
    }
    
    public static String list2Json(Collection beans) throws JsonGenerationException, JsonMappingException, IOException  {
        return mapper.writeValueAsString(beans);
    }
    /**
     * 
     * @param jsonStr
     * @param clazz		默认为List.class
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static Object json2List(final String jsonStr,Class clazz) throws JsonParseException, JsonMappingException, IOException{
    	if (clazz == null) {
			clazz = List.class;
		} 
    	return mapper.readValue(jsonStr, clazz);
    }
    public static void main(String[] args) {
		User u1 = new User();
		u1.setName("张三");
		u1.setId("111");
		User u2 = new User();
		u2.setName("李四");
		u2.setId("222");
		List<User> list = new ArrayList<User>();
		list.add(u1);
		list.add(u2);
		String list2Json = null;
		try {
			list2Json = list2Json(list);
			System.out.println(list2Json);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			List json2List = (List) JacksonUtil.json2List(list2Json, null);
			System.out.println(Arrays.toString(json2List.toArray()));
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package com.tianyi.helmet.server.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.ConcurrentHashMap;

/**
 * xml操作工具类
 *
 */
public class XmlUtils {
    private static final ConcurrentHashMap<String, JAXBContext> xmlMarshallerMap = new ConcurrentHashMap<String, JAXBContext>();

    public static <T> T xml2Java(Class<T> t, InputStream is) throws JAXBException, IOException {
        Unmarshaller unmarshaller = getUnMarshaller(t);
        return (T) unmarshaller.unmarshal(is);
    }

    private static Unmarshaller getUnMarshaller(Class... clzs) throws JAXBException {
        return getJAXBContext(clzs).createUnmarshaller();
    }

    private static JAXBContext getJAXBContext(Class... clzs) throws JAXBException {
        String t = toClassArray(clzs);
        JAXBContext context;
        context = xmlMarshallerMap.get(t);
        if (context == null) {
            synchronized (xmlMarshallerMap) {
                context = JAXBContext.newInstance(clzs);
                xmlMarshallerMap.putIfAbsent(t, context);
                context = xmlMarshallerMap.get(t);
            }
        }
        return context;
    }

    static final Comparator<String> comparator = new Comparator<String>() {
        @Override
        public int compare(String aClass, String t1) {
            return aClass.compareTo(t1);
        }
    };

    private static String toClassArray(Class... clzs) {
        if (clzs != null && clzs.length > 1) {
            String[] newClzs = new String[clzs.length];
            for (int i = 0, j = clzs.length; i < j; i++)
                newClzs[i] = clzs[i].getName();
            Arrays.sort(newClzs, comparator);
            return Arrays.toString(newClzs);
        } else if (clzs != null && clzs.length == 1 && clzs[0] != null)
            return clzs[0].getName();
        else return null;
    }

}


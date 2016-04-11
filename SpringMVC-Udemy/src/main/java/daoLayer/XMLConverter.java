package daoLayer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Component;

@Component("xmlConverter")
public class XMLConverter {

//	public Marshaller getMarshaller(Class classz) {
//		JAXBContext jaxbContext = JAXBContext.newInstance(classz);
//		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//
//		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//
//		return marshaller;
//	}
//
//	public Unmarshaller getUnmarshaller() {
//		return unmarshaller;
//	}
//
//	public void convertFromObjectToXML(Object object, String filepath) throws IOException {
//
//		FileOutputStream os = null;
//		try {
//			os = new FileOutputStream(filepath);
//			Marshaller marshaller = getMarshaller();
//			marshaller.marshal(object, new StreamResult(os));
//		} finally {
//			if (os != null) {
//				os.close();
//			}
//		}
//	}
//
//	public Object convertFromXMLToObject(String xmlfile) throws IOException {
//
//		FileInputStream is = null;
//		try {
//			is = new FileInputStream(xmlfile);
//			return getUnmarshaller().unmarshal(new StreamSource(is));
//		} finally {
//			if (is != null) {
//				is.close();
//			}
//		}
//	}

}
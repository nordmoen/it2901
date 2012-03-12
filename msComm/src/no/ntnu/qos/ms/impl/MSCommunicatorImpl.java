package no.ntnu.qos.ms.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMContainer;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMXMLBuilderFactory;
import org.apache.axiom.om.OMXMLParserWrapper;

import no.ntnu.qos.ms.MSCommunicator;
import no.ntnu.qos.ms.RoutingInfo;

/**
 * This class reads an xml file and stores RoutingInfo objects in a Map
 * @author Ola Martin
 *
 */
public class MSCommunicatorImpl implements MSCommunicator {
	private final Map<URI, RoutingInfo> routingInfos = new HashMap<URI, RoutingInfo>();
	/**
	 * reads an xml file and stores RoutingInfo in a Map<URI, RoutingInfo>.
	 * @param msXML the path to and file name of the file to read.
	 */
	public MSCommunicatorImpl(String msXML) {
		InputStream in;
		try {
			in = new FileInputStream(new File(msXML));
			OMXMLParserWrapper builder = OMXMLBuilderFactory.createOMBuilder(in);
			OMElement routingInfosEle = builder.getDocumentElement();
			Iterator<OMContainer> routingInfoIterator =
					routingInfosEle.getChildrenWithLocalName("RoutingInfo");
			while(routingInfoIterator.hasNext()){
				OMElement routingInfo = (OMElement)routingInfoIterator.next();

				Iterator lastTRitt = routingInfo.getChildrenWithLocalName("lastTR");
				OMElement lastTRele = (OMElement)lastTRitt.next();
				String lastTR = lastTRele.getText();

				Iterator bandwidthItt = routingInfo.getChildrenWithLocalName("bandwidth");
				OMElement bandwidthEle = (OMElement)bandwidthItt.next();
				double bandwidth = Double.parseDouble(bandwidthEle.getText());
				try {
					routingInfos.put(new URI(routingInfo.getAttributeValue(new QName("destIP"))), 
							new RoutingInfo(lastTR, bandwidth));
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public RoutingInfo getRoutingInfo(URI destIP) {
		if(routingInfos.containsKey(destIP)){
			return routingInfos.get(destIP);			
		}else{
			return new RoutingInfo("", -1);
		}
	}

}

/**
 * 
 */
package net.gcicom.cdr.processor.supplier;

import org.apache.camel.model.dataformat.BindyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import net.gcicom.cdr.processor.RouteNames;
import net.gcicom.cdr.processor.entity.input.BTOpenReachCDR;
import net.gcicom.cdr.processor.entity.mapper.BTOpenReachCDRMapper;
import net.gcicom.cdr.processor.service.CDRErrorHandler;
import net.gcicom.cdr.processor.transformer.BTOpenReachCDRTransformer;

/**
 * A Simple route builder to process BT Openreach CDR feed which has format like this
 * Event: "01782781500","350","2017021618015800",,,,,,,,,,"15695392","239744316","0998002259358","32","07796284821",
 * ,"00:00:05.63","00000001","00000000000000","16396","7","01782781500","41",,"0","3","0455805712","S","1",
 * "Mobile telephone - fm5","MED9616370","voice","4.1",
 *
 */
@Component
public class BTOpenReachCDRProcessor extends BaseProcessor {
	
	
	private static final Logger LOG = LoggerFactory.getLogger(BTOpenReachCDRProcessor.class);
	
	@Value("${gci.btopenreach.file.in.location}")
	private String inFileLocation;

	@Value("${gci.btopenreach.file.name.pattern}")
	private String filePattern;
	
	@Value("${gci.btopenreach.timer}")
	private String cron;
	
	@Value("${gci.btopenreach.autostart}")
	private boolean autostart;
	
	@Autowired
	private CDRErrorHandler handler;

	@Autowired
	private BTOpenReachCDRMapper mapper;
	
	@Autowired
	private BTOpenReachCDRTransformer transformer;
	
	
	@Override
	public void configure() throws Exception {
		
		setAutostart(autostart);
		setInFileLocation(inFileLocation);
		setFilePattern(filePattern);
		setCron(cron);
		setMapper(mapper);
		super.configure();

		/*pollFiles(inFileLocation, filePattern, cron);
		
        moveFileOnError(inFileLocation);

        mapCSVRowToVendorCdr();
        
        addCdr(mapper);*/
	}
	
	/**Maps a csv file row to a {@link BTOpenReachCDR} and send it to 
	 */
	void mapCSVRowToVendorCdr() {
		
		LOG.debug("mapCSVRowToVendorCdr");
		
		String processorName = this.getClass().getCanonicalName();

		from(RouteNames.MAP_CSV_ROW_TO_VENDOR_CDR.concat(processorName))
		.description(RouteNames.MAP_CSV_ROW_TO_VENDOR_CDR.concat(processorName), 
				"This route convert each csv rows to a pojo and passes it to ".concat(RouteNames.ADD_CDR.concat(processorName)), null)
        .onException(IllegalArgumentException.class)
	        .handled(true)
	        .bean(handler, "handleEventInvalidCdr")
	        .useOriginalMessage()
	    .end()
		.bean(transformer, "transform")
		.unmarshal()
		.bindy(BindyType.Csv, BTOpenReachCDR.class)
		.to(RouteNames.ADD_CDR.concat(processorName));
      
	}
	
	


}

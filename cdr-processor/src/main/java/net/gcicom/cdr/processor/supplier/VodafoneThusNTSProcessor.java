package net.gcicom.cdr.processor.supplier;

import org.apache.camel.model.dataformat.BindyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import net.gcicom.cdr.processor.RouteNames;
import net.gcicom.cdr.processor.entity.input.VodafoneThusNTS;
import net.gcicom.cdr.processor.entity.mapper.VodafoneThusNTSMapper;
import net.gcicom.cdr.processor.service.CDRErrorHandler;

@Component
public class VodafoneThusNTSProcessor extends BaseProcessor {

	
	
	private static final Logger LOG = LoggerFactory.getLogger(VodafoneThusNTSProcessor.class);
	
	@Value("${gci.nts.vodathus.file.in.location}")
	private String inFileLocation;

	@Value("${gci.nts.vodathus.file.name.pattern}")
	private String filePattern;
	
	@Value("${gci.nts.vodathus.timer}")
	private String cron;
	
	@Value("${gci.nts.vodathus.autostart}")
	private boolean autostart;
	
	@Autowired
	private CDRErrorHandler eHandler;

	@Autowired
	private VodafoneThusNTSMapper mapper;
	
	
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
	
	/**Maps a csv file row to a {@link VodafoneThusIDA} and send it to 
	 */
	void mapCSVRowToVendorCdr() {
		
		LOG.debug("mapCSVRowToVendorCdr");
		
		String processorName = this.getClass().getCanonicalName();

		from(RouteNames.MAP_CSV_ROW_TO_VENDOR_CDR.concat(processorName))
		.description(RouteNames.MAP_CSV_ROW_TO_VENDOR_CDR.concat(processorName), 
				"This route convert each csv rows to a pojo and passes it to ".concat(RouteNames.ADD_CDR.concat(processorName)), null)
        .onException(IllegalArgumentException.class)
	        .handled(true)
	        .bean(eHandler, "handleEventInvalidCdr")
	        .useOriginalMessage()
	    .end()
		.unmarshal()
		.bindy(BindyType.Csv, VodafoneThusNTS.class)
		.to(RouteNames.ADD_CDR.concat(processorName));
      
	}
	
	



}

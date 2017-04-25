package net.gcicom.cdr.processor;

import net.gcicom.cdr.processor.supplier.BaseProcessor;

/**
 * Defines route's prefixes used in conjunction with {@link BaseProcessor} instance names as suffix to make all route names as unique
 *
 */
public final class RouteNames {

	public static final String MOVE_FILE_ON_ERROR = "direct:move_file_on_error_";
	
	public static final String MAP_CSV_ROW_TO_VENDOR_CDR = "direct:map_csv_row_to_supplier_cdr_";

	public static final String ADD_CDR = "direct:store_cdr_to_db_";
	
	public static final String EXTRACT_GZIP_TAR = "direct:extract_gzip_tar_";

	public static final String EXTRACT_ZIP = "direct:extract_zip_";

	public static final String EXTRACT_GZIP = "direct:extract_gzip_";

	
}

package net.gcicom.cdr.processor.service;

import static net.gcicom.cdr.processor.common.SupplierMap.getSupplierName;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import net.gcicom.cdr.processor.repository.reference.SupplierRepository;
import net.gcicom.domain.reference.Supplier;

@Component
public class SupplierDetailsService {

	
	@Autowired
	private SupplierRepository sRepo;
	
	
	
	/**Gets supplied id for based on input file pattern
	 * @param fileName
	 * @return
	 * @throws ValidationFailedException
	 */
	public Integer getSupplierId(final String fileName) throws ValidationFailedException {

		final String sName = getSupplierName(fileName);
		
		//LOG.debug("For file {} supplier is {}", fileName, sName);
		
		List<Supplier> ss = sRepo.findBySupplierNameAndHasEventFlag(sName, 1);
		
		for (Supplier s : ss) {
			
			if (!ObjectUtils.isEmpty(s.getSupplierID())) {
				
				return s.getSupplierID();
			}
		}
		
		throw new ValidationFailedException(String.format("No supplier exist for given %s file", fileName));
	}
	
}
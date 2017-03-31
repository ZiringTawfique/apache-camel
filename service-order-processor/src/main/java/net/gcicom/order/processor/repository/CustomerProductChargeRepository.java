package net.gcicom.order.processor.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import net.gcicom.domain.allspark.CustomerProductCharge;

public interface CustomerProductChargeRepository  extends JpaRepository<CustomerProductCharge, Long> {

}

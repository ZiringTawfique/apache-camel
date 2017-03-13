package net.gcicom.cdr.processor.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.gcicom.cdr.processor.entity.output.MD5;

public interface Md5Repository extends CrudRepository<MD5, Long>{

	List<MD5> findByHex(String hex);
}

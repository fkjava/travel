package org.fkjava.travel.core.dao;

import org.fkjava.travel.core.domain.Advertise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lwq
 */
@Repository
public interface AdvertiseDao extends JpaRepository<Advertise, String> {

}

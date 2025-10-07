package com.elmazraa.incidentreporting.Repository;

import com.elmazraa.incidentreporting.Model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IncidentRepository extends JpaRepository<Incident, Long> {
}

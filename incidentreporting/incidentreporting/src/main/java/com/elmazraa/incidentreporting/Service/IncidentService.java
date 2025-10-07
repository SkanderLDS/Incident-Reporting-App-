package com.elmazraa.incidentreporting.Service;

import com.elmazraa.incidentreporting.Model.Incident;
import com.elmazraa.incidentreporting.Repository.IncidentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class IncidentService {

    private final IncidentRepository repository;

    public IncidentService(IncidentRepository repository) {
        this.repository = repository;
    }

    public List<Incident> getAllIncidents() {
        return repository.findAll();
    }

    public Incident getIncidentById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Incident createIncident(Incident incident) {
        return repository.save(incident);
    }

    public Incident updateIncident(Long id, Incident updatedIncident) {
        return repository.findById(id).map(incident -> {
            incident.setMachineId(updatedIncident.getMachineId());
            incident.setIssueType(updatedIncident.getIssueType());
            incident.setSeverity(updatedIncident.getSeverity());
            incident.setDescription(updatedIncident.getDescription());
            incident.setStatus(updatedIncident.getStatus());
            incident.setResolutionNotes(updatedIncident.getResolutionNotes());
            incident.setResolvedAt(updatedIncident.getResolvedAt());
            return repository.save(incident);
        }).orElse(null);
    }

    public void deleteIncident(Long id) {
        repository.deleteById(id);
    }
}

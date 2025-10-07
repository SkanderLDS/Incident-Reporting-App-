package com.elmazraa.incidentreporting.Controller;

import com.elmazraa.incidentreporting.Model.Incident;
import com.elmazraa.incidentreporting.Service.FileStorageService;
import com.elmazraa.incidentreporting.Service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/incidents")
@CrossOrigin(origins = "*")  // Allow all origins; adjust for production
public class IncidentController {

    @Autowired
    private final IncidentService service;
    @Autowired
    private final FileStorageService fileStorageService;

    public IncidentController(IncidentService service, FileStorageService fileStorageService) {
        this.service = service;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping
    public List<Incident> getAll() {
        return service.getAllIncidents();
    }

    @GetMapping("/{id}")
    public Incident getById(@PathVariable Long id) {
        return service.getIncidentById(id);
    }

    @PostMapping("/create")
    public Incident create(@RequestBody Incident incident) {
        return service.createIncident(incident);
    }

    @PutMapping("/{id}")
    public Incident update(@PathVariable Long id, @RequestBody Incident updatedIncident) {
        return service.updateIncident(id, updatedIncident);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteIncident(id);
    }

    @PostMapping("/uploadImage")
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);
        // Return the accessible path to the file (we’ll configure access next)
        return "/uploads/" + fileName;
    }
}

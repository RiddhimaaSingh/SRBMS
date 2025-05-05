package service;

import entity.Resource;
import repo.ResourceRepo;

import java.util.List;

public class ResourceService {
    private final ResourceRepo resourceRepo;

    public ResourceService(ResourceRepo resourceRepo) {
        this.resourceRepo = resourceRepo;
    }

    public boolean addResource(Resource resource) {
        if (resourceRepo.exists(resource.getId())) {
            return false;
        }
        resourceRepo.save(resource);
        return true;
    }

    public boolean updateResource(Resource updatedResource) {
        if (!resourceRepo.exists(updatedResource.getId())) {
            return false;
        }
        resourceRepo.save(updatedResource); // overwrite
        return true;
    }

    public boolean deleteResource(String resourceId) {
        return resourceRepo.delete(resourceId);
    }

    public List<Resource> getAllResources() {
        return resourceRepo.findAll();
    }

    public Resource getResourceById(String id) {
        return resourceRepo.findById(id);
    }
}

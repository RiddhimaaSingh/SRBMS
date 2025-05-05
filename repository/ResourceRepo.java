package repository;

import java.util.*;

import entity.Resource;

public class ResourceRepo {
    private Map<String, Resource> resources = new HashMap<>();

    public void save(Resource resource) {
        resources.put(resource.getId(), resource);
    }

    public boolean exists(String resourceId) {
        return resources.containsKey(resourceId);
    }

    public boolean delete(String resourceId) {
        return resources.remove(resourceId) != null;
    }

    public Resource findById(String id) {
        return resources.get(id);
    }

    public List<Resource> findAll() {
        return new ArrayList<>(resources.values());
    }
}
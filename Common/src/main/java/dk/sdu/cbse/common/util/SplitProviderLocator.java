package dk.sdu.cbse.common.util;

import java.lang.module.Configuration;
import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Provider;
import java.util.*;
import java.util.stream.Collectors;

public enum SplitProviderLocator {

    INSTANCE;

    private static final Map<Class, ServiceLoader> loadermap = new HashMap<>();
    private final ModuleLayer moduleLayer;

    SplitProviderLocator() {
        try {
            Path pluginsDir = Paths.get("plugins");
            ModuleFinder plugfinder = ModuleFinder.of(pluginsDir);

            // Looks for all name for all found plugin moduels
            List<String> plugins = plugfinder
                    .findAll()
                    .stream()
                    .map(ModuleReference::descriptor)
                    .map(ModuleDescriptor::name)
                    .collect(Collectors.toList());

            //Creates the configuration
            Configuration pluginsConfig = ModuleLayer
                    .boot()
                    .configuration()
                    .resolve(plugfinder, ModuleFinder.of(), plugins);

            //Creates layer with plugins
            moduleLayer = ModuleLayer
                    .boot()
                    .defineModulesWithOneLoader(pluginsConfig, ClassLoader.getSystemClassLoader());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> locateAll(Class<T> service) {
        ServiceLoader<T> loader = loadermap.get(service);

        boolean printStatement = false;

             if(loader ==null)

        {
            loader = ServiceLoader.load(moduleLayer, service);
            loadermap.put(service, loader);
            printStatement = true;
        }

        List<T> list = new ArrayList<T>();

            if(loader !=null)

        {
            try {
                for (T instance : loader) {
                    list.add(instance);
                }
            } catch (ServiceConfigurationError serviceError) {
                serviceError.printStackTrace();
            }
        }

            if(printStatement)

        {
            System.out.println("Found " + list.size() + " implementations for interface: " + service.getName());
        }
            return list;
    }
}


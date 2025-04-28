package de.elia.api;

import io.papermc.paper.plugin.loader.PluginClasspathBuilder;
import io.papermc.paper.plugin.loader.PluginLoader;
import io.papermc.paper.plugin.loader.library.impl.MavenLibraryResolver;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.graph.Dependency;
import org.eclipse.aether.repository.RemoteRepository;
import org.jetbrains.annotations.NotNull;

public class Loader implements PluginLoader {

    @Override
    public void classloader(@NotNull PluginClasspathBuilder classpathBuilder) {
        MavenLibraryResolver log4jAPI = new MavenLibraryResolver();
        log4jAPI.addDependency(new Dependency(new DefaultArtifact("org.apache.logging.log4j:log4j-api:2.22.0"), null));
        log4jAPI.addRepository(new RemoteRepository.Builder("log4j-api", "default", "https://repo.papermc.io/repository/maven-public/").build());
        classpathBuilder.addLibrary(log4jAPI);
        MavenLibraryResolver log4jCore = new MavenLibraryResolver();
        log4jCore.addDependency(new Dependency(new DefaultArtifact("org.apache.logging.log4j:log4j-core:2.22.0"), null));
        log4jCore.addRepository(new RemoteRepository.Builder("log4j-core", "default", "https://repo.papermc.io/repository/maven-public/").build());
        classpathBuilder.addLibrary(log4jCore);
      MavenLibraryResolver htttpApache5 = new MavenLibraryResolver();
      htttpApache5.addDependency(new Dependency(new DefaultArtifact("org.apache.httpcomponents.client5:httpclient5:5.4.2"), null));
      htttpApache5.addRepository(new RemoteRepository.Builder("httpclient5", "default", "https://repo.papermc.io/repository/maven-public/").build());
      classpathBuilder.addLibrary(htttpApache5);
    }
}

package fr.sii.survival.ut.ext;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import fr.sii.survival.core.ext.EnemyExtension;
import fr.sii.survival.core.ext.annotation.AnnotationDeveloperProvider;
import fr.sii.survival.core.ext.registry.AutoDiscoveryExtensionRegistry;
import fr.sii.survival.core.ext.registry.ExtensionRegistry;
import fr.sii.survival.core.ext.registry.PreFilteredRegistry;
import fr.sii.survival.core.ext.registry.SimpleEnemyExtensionRegistry;
import fr.sii.survival.core.service.extension.DelegateExtensionService;
import fr.sii.survival.core.service.extension.ExtensionService;

@RunWith(MockitoJUnitRunner.class)
public class PreFilterExtensionRegistryTest {

	ExtensionService extensionService;

	ExtensionRegistry registry;

	@Before
	public void setUp() {
		extensionService = new DelegateExtensionService(new AnnotationDeveloperProvider());
		registry = new AutoDiscoveryExtensionRegistry(new PreFilteredRegistry(p -> false, new SimpleEnemyExtensionRegistry()), extensionService, "fr.sii.survival.mock.ext");
	}

	@Test
	public void basicEnemy() {
		List<Class<EnemyExtension>> classes = registry.getEnemyExtensions();
		Assert.assertEquals("should have 0 valid extension", 0, classes.size());
	}
}

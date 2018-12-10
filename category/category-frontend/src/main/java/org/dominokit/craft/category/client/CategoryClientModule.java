package org.dominokit.craft.category.client;

import com.google.gwt.core.client.EntryPoint;
import org.dominokit.domino.api.client.ModuleConfigurator;
import org.dominokit.domino.api.client.annotations.ClientModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ClientModule(name="Category")
public class CategoryClientModule implements EntryPoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryClientModule.class);

	public void onModuleLoad() {
		LOGGER.info("Initializing Category frontend module ...");
		new ModuleConfigurator().configureModule(new CategoryModuleConfiguration());
	}
}
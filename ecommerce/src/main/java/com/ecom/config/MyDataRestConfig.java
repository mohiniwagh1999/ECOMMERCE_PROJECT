package com.ecom.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
	@Autowired
	private EntityManager manager;
	
	
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config,CorsRegistry cors)
	{
		exposeIds(config);
	}
	
	
	private void exposeIds(RepositoryRestConfiguration config)
	{
		
		//get all entity classes from entity manager
		Set<EntityType<?>> entities = manager.getMetamodel().getEntities();
		
		//create array of entity types
		List<Class>entityClasses=new ArrayList<>();
		
		for(EntityType tempEntityType: entities)
		{
			entityClasses.add(tempEntityType.getJavaType());
		}
		//expose ids for domain types
		Class[] domainType = entityClasses.toArray(new Class[0]);
 		config.exposeIdsFor(domainType);
		
		
		
		
	}
}
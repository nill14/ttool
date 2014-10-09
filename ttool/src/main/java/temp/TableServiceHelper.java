package temp;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.metamodel.EntityType;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;

import com.github.nill14.ttool.jsf.mbean.JpaRepositoryDataModel;
import com.github.nill14.ttool.service.ITableService;
import com.google.common.base.Function;

public class TableServiceHelper {

	@Inject
	private ITableService service;
	
	@Inject
	private ApplicationContext applicationContext; 
	
	
	public <T> JpaRepositoryDataModel getDataModel(EntityType<T> entityType) {
		Validate.notNull(entityType, "entityType");
		
		JpaRepository<T, ? extends Serializable> repository = service.getRepository(entityType);
		
		AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
		
		Object bean = beanFactory.createBean(JpaRepositoryDataModel.class, AutowireCapableBeanFactory.AUTOWIRE_NO, false);
		
		JpaRepositoryDataModel model = (JpaRepositoryDataModel) bean;
		
		model.setRepository(repository);
		model.setKeyFunction(new Function<Object, Serializable>() {

			@Override
			public Serializable apply(Object input) {
				return input.toString();
			}
		});
		
		
		return model;
		
	}
}

package com.github.nill14.ttool.jsf.mbean;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.metamodel.EntityType;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;

import com.github.nill14.ttool.entity.security.User;
import com.github.nill14.ttool.sandbox.ColumnModel;
import com.github.nill14.ttool.service.ITableService;
import com.google.common.base.Function;

@Named("tableBrowser")
@Scope("request")
public class TableBrowserBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(TableBrowserBean.class);

	@Inject
	private ITableService tableService;
	
	private List<ColumnModel> columns = new ArrayList<ColumnModel>();
	private JpaRepositoryDataModel model;  
//	private List<User> users;
	private Object selectedItem;
	private String tableName;

    
  
	
	@PostConstruct
	public void init() {
		ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
		tableName = ctx.getRequestParameterMap().get("table");
		
		if (tableName != null) {
			EntityType<?> entityType = tableService.getEntityType(tableName);
			
			columns = tableService.getTableColumns(entityType);
			
//		this.users = Lists.newArrayList(userService.getAllUsers());
			
			model = tableService.getDataModel(entityType);
		}
	}
	
	public String getTitle() {
		
		return tableName;
	}

	public MenuModel getMenu() {
		DefaultMenuModel model = new DefaultMenuModel();

		for (String tableName : tableService.getTableNames()) {
			DefaultMenuItem item = new DefaultMenuItem(tableName);
			String url = MessageFormat.format("/tableBrowser.xhtml?table={0}&faces-redirect=true", tableName);
			item.setUrl(url);
			item.setValue(tableName);
			model.addElement(item);
		}

		
		return model;
	}

	public List<ColumnModel> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnModel> columns) {
		this.columns = columns;
	}

	public JpaRepositoryDataModel getModel() {
		return model;
	}

	public void setModel(JpaRepositoryDataModel model) {
		this.model = model;
	}

	public Object getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(Object selectedItem) {
		this.selectedItem = selectedItem;
	}

	
	

}